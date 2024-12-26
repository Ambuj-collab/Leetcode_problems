/*

Discussion points:
--------------------
Imagine this metrics and a word.

["A","A","A","A"]
["A","A","A","B"]
["A","A","B","A"]

word = "AAAAAAAABB"

If we check the word from beginning(= A), we have to move a lot of places. But if we start from end(= B), we can immediately return False in many places.

This is depends on input word, but we might reverse the input word if number of the first character is greater than number of the last character.

I implemented that logic. I think it's a good point to discuss with interviewers in real interview.

*/

class Solution {
    // Main function to check if the word exists on the board
    public boolean exist(char[][] board, String word) {
        int n = board.length; // Number of rows in the board
        int m = board[0].length; // Number of columns in the board

        boolean[][] visited = new boolean[n][m]; // Array to keep track of visited cells

        char[] wordChar = word.toCharArray(); // Convert the word into a character array

        // Quick check: If the length of the word exceeds the total number of cells on
        // the board, it can't exist
        if (wordChar.length > n * m)
            return false;

        int counts[] = new int[256]; // Array to store counts of each character

        // Count the occurrence of each character on the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                counts[board[i][j]]++;
            }
        }

        // Adjust the order of characters in the wordChar array based on their frequency counts to optimize search. This piece of code is added to optimize the code. Even if you remove this, code will work as expected but it will take more time.
        int len = wordChar.length;
        for (int i = 0; i < len / 2; i++) {
            if (counts[wordChar[i]] > counts[wordChar[len - 1 - i]]) {
                for (int j = 0; j < len / 2; j++) {
                    char temp = wordChar[j];
                    wordChar[j] = wordChar[len - 1 - j];
                    wordChar[len - 1 - j] = temp;
                }
                break;
            }
        }

        // Decrease counts of characters in the word from the board
        for (char c : wordChar) {
            if (--counts[c] < 0)
                return false; // If there are more occurrences of a character in the word than on the board, return false
                     
        }

        // Iterate through each cell in the board and start searching for the word
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit(board, wordChar, 0, i, j, n, m, visited))
                    return true; // If the word is found starting from this cell, return true
            }
        }
        return false; // If the loop completes without finding the word, return false
    }

    // Helper function to recursively search for the word starting from a given cell
    private boolean visit(char[][] board, char[] word, int start, int x, int y,
            int n, int m, boolean[][] visited) {
        // Base case: If all characters in the word are found, return true
        if (start == word.length)
            return true;

        // Check for out-of-bounds, already visited cells, and character mismatch
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y])
            return false;

        // If the current character in the word does not match the character on the board, return false
        if (word[start] != board[x][y])
            return false;

        visited[x][y] = true; // Mark the current cell as visited

        // Recursively search in all four directions from the current cell
        boolean found = visit(board, word, start + 1, x + 1, y, n, m, visited)
                || visit(board, word, start + 1, x - 1, y, n, m, visited)
                || visit(board, word, start + 1, x, y + 1, n, m, visited)
                || visit(board, word, start + 1, x, y - 1, n, m, visited);

        visited[x][y] = false; // Backtrack: Unmark the current cell as visited

        return found; // Return whether the word was found starting from the current cell
    }
}

------------------------------------------------------------------------------------------------
												OR
------------------------------------------------------------------------------------------------

class Solution {
    // Main function to check if the word exists on the board
    public boolean exist(char[][] board, String word) {
        int n = board.length; // Number of rows in the board
        int m = board[0].length; // Number of columns in the board

        boolean[][] visited = new boolean[n][m]; // Array to keep track of visited cells

        char[] wordChar = word.toCharArray(); // Convert the word into a character array

        // Quick check: If the length of the word exceeds the total number of cells on
        // the board, it can't exist
        if (wordChar.length > n * m)
            return false;

        int counts[] = new int[256]; // Array to store counts of each character

        // Count the occurrence of each character on the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                counts[board[i][j]]++;
            }
        }

        // Decrease counts of characters in the word from the board
        for (char c : wordChar) {
            if (--counts[c] < 0)
                return false; // If there are more occurrences of a character in the word than on the board,
                              // return false
        }

        // Iterate through each cell in the board and start searching for the word
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit(board, wordChar, 0, i, j, n, m, visited))
                    return true; // If the word is found starting from this cell, return true
            }
        }
        return false; // If the loop completes without finding the word, return false
    }

    // Helper function to recursively search for the word starting from a given cell
    private boolean visit(char[][] board, char[] word, int start, int x, int y,
            int n, int m, boolean[][] visited) {
        // Base case: If all characters in the word are found, return true
        if (start == word.length)
            return true;

        // Check for out-of-bounds, already visited cells, and character mismatch
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y])
            return false;

        // If the current character in the word does not match the character on the
        // board, return false
        if (word[start] != board[x][y])
            return false;

        visited[x][y] = true; // Mark the current cell as visited

        // Recursively search in all four directions from the current cell
        boolean found = visit(board, word, start + 1, x + 1, y, n, m, visited)
                || visit(board, word, start + 1, x - 1, y, n, m, visited)
                || visit(board, word, start + 1, x, y + 1, n, m, visited)
                || visit(board, word, start + 1, x, y - 1, n, m, visited);

        visited[x][y] = false; // Backtrack: Unmark the current cell as visited

        return found; // Return whether the word was found starting from the current cell
    }
}

-------------------------------------------------------------------------------------------------
											OR
-------------------------------------------------------------------------------------------------

// Best code for this problem. Runtime = 0 ms, Beats 100.00%
class Solution {
    // Main function to check if the word exists on the board
    public boolean exist(char[][] board, String word) {
        int n = board.length; // Number of rows in the board
        int m = board[0].length; // Number of columns in the board

        boolean[][] visited = new boolean[n][m]; // Array to keep track of visited cells

        char[] wordChar = word.toCharArray(); // Convert the word into a character array

        // Quick check: If the length of the word exceeds the total number of cells on
        // the board, it can't exist
        if (wordChar.length > n * m)
            return false;

        int counts[] = new int[256]; // Array to store counts of each character

        // Count the occurrence of each character on the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                counts[board[i][j]]++;
            }
        }

        // Adjust the order of characters in the wordChar array based on their frequency
        // counts to optimize search
        int len = wordChar.length;
        if (counts[wordChar[0]] > counts[wordChar[len - 1]]) {
            for (int j = 0; j < len / 2; j++) {
                char temp = wordChar[j];
                wordChar[j] = wordChar[len - 1 - j];
                wordChar[len - 1 - j] = temp;
            }
        }

        // Decrease counts of characters in the word from the board
        for (char c : wordChar) {
            if (--counts[c] < 0)
                return false; // If there are more occurrences of a character in the word than on the board,
                              // return false
        }

        // Iterate through each cell in the board and start searching for the word
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit(board, wordChar, 0, i, j, n, m, visited))
                    return true; // If the word is found starting from this cell, return true
            }
        }
        return false; // If the loop completes without finding the word, return false
    }

    // Helper function to recursively search for the word starting from a given cell
    private boolean visit(char[][] board, char[] word, int start, int x, int y,
            int n, int m, boolean[][] visited) {
        // Base case: If all characters in the word are found, return true
        if (start == word.length)
            return true;

        // Check for out-of-bounds, already visited cells, and character mismatch
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y])
            return false;

        // If the current character in the word does not match the character on the
        // board, return false
        if (word[start] != board[x][y])
            return false;

        visited[x][y] = true; // Mark the current cell as visited

        // Recursively search in all four directions from the current cell
        boolean found = visit(board, word, start + 1, x + 1, y, n, m, visited)
                || visit(board, word, start + 1, x - 1, y, n, m, visited)
                || visit(board, word, start + 1, x, y + 1, n, m, visited)
                || visit(board, word, start + 1, x, y - 1, n, m, visited);

        visited[x][y] = false; // Backtrack: Unmark the current cell as visited

        return found; // Return whether the word was found starting from the current cell
    }
}

-------------------------------------------------------------------------------------------------
												OR
-------------------------------------------------------------------------------------------------

class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;

        if (m * n < word.length())
            return false;

        char[] wrd = word.toCharArray();
        int[] boardf = new int[128];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ++boardf[board[i][j]];
            }
        }

        if (boardf[wrd[0]] > boardf[wrd[wrd.length - 1]])
            reverse(wrd);
        
        for (char ch : wrd) {
            if (--boardf[ch] < 0) {
                return false;
            }
        }
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (wrd[0] == board[i][j]
                        && found(board, i, j, wrd, new boolean[m][n], 0))
                    return true;
            }
        }
        return false;
    }

    private void reverse(char[] word) {
        int n = word.length;
        for (int i = 0; i < n / 2; ++i) {
            char temp = word[i];
            word[i] = word[n - i - 1];
            word[n - i - 1] = temp;
        }
    }

    private static final int[] dirs = { 0, -1, 0, 1, 0 };

    private boolean found(char[][] board, int row, int col, char[] word,
            boolean[][] visited, int index) {

        if (index == word.length)
            return true;
        
        if (row < 0 || col < 0 || row == board.length || col == board[0].length
                || board[row][col] != word[index] || visited[row][col])
            return false;
        
        visited[row][col] = true;
        for (int i = 0; i < 4; ++i) {
            if (found(board, row + dirs[i], col + dirs[i + 1],
                    word, visited, index + 1))
                return true;
        }
        visited[row][col] = false;
        
        return false;
    }
}
												