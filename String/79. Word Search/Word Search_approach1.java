class Solution {
    // Class level variables to hold the dimensions of the board, the word, and the board itself
    private int rows;
    private int cols;
    private String targetWord;
    private char[][] gameBoard;

    // Method to determine if the target word exists in the board
    public boolean exist(char[][] board, String word) {
        rows = board.length;       // Number of rows in the board
        cols = board[0].length;    // Number of columns in the board
        targetWord = word;         // The word we are searching for
        gameBoard = board;         // The game board
      
        // Iterate over every cell in the board
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // If the first letter matches and dfs search is successful, return true
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
      
        // If we have not returned true at this point, the word does not exist in the board
        return false;
    }

    // Helper method to perform Depth First Search (DFS)
    private boolean dfs(int row, int col, int index) {
        // Check if we are at the last character of the word
        if (index == targetWord.length() - 1) {
            return gameBoard[row][col] == targetWord.charAt(index);
        }
      
        // Check if current cell does not match the character at index in word
        if (gameBoard[row][col] != targetWord.charAt(index)) {
            return false;
        }
      
        // Temporarily mark the current cell as visited by replacing its value
        char tempChar = gameBoard[row][col];
        gameBoard[row][col] = '0';
      
        // Define an array of directions (up, right, down, left)
        int[] directions = {-1, 0, 1, 0, -1};
      
        // Explore all possible adjacent cells (up, right, down, left)
        for (int d = 0; d < 4; ++d) {
            int newRow = row + directions[d];
            int newCol = col + directions[d + 1];
          
            // Check if the new position is within bounds and not visited
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && gameBoard[newRow][newCol] != '0') {
                // If the dfs search from the adjacent cell is successful, return true
                if (dfs(newRow, newCol, index + 1)) {
                    return true;
                }
            }
        }
      
        // Reset the cell's value back from '0' to its original character
        gameBoard[row][col] = tempChar;
      
        // If none of the adjacent cells leads to a solution, return false
        return false;
    }
}

------------------------------------------------------------------------------------------------
												OR
------------------------------------------------------------------------------------------------

class Solution {
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (depthFirstSearch(board, word, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean depthFirstSearch(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(index)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '*'; // Mark the cell as visited

        // Explore the four neighboring directions: right, down, left, up
        int[][] offsets = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];
            if (depthFirstSearch(board, word, newRow, newCol, index + 1)) {
                return true;
            }
        }

        board[row][col] = temp; // Restore the cell's original value
        return false;
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

/*

Intuition:
-----------
Imagine a word search puzzle where you try to find words by connecting letters.
This code is like a super-smart helper that checks if a word can be found in the puzzle.

1. Check if the word is even possible:
	a) Make sure the word isn't too long to fit in the puzzle.
	b) Count the letters in the puzzle to see if there are enough to make the word.

2. Search like a detective:
	a) Start at each letter in the puzzle and pretend to build the word.
	b) Move up, down, left, or right (but not diagonally) to try to find the next letter.
	c) Keep track of where you've been so you don't repeat letters.
	Found it!
	
If you can build the entire word, shout "Eureka! I found the word!"

*/
class Solution {
    // Main function to check if the word exists in the maze
    public boolean exist(char[][] maze, String word) {
        // Iterate through each cell in the maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                // If the current cell matches the first character of the word, start searching
                if (maze[i][j] == word.charAt(0)) {
                    boolean ans = search(maze, word, i, j, 0);
                    if (ans) {
                        return ans; // If the word is found, return true
                    }
                }
            }
        }
        return false; // If the word is not found in the maze, return false
    }
    
    // Recursive function to search for the word starting from a given position in the maze
    public static boolean search(char[][] maze, String word, int row, int col, int idx) {
        // Base case: If the entire word has been found, return true
        if (idx == word.length()) {
            return true;
        }

        // Check for out-of-bounds or mismatched characters
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || maze[row][col] != word.charAt(idx)) {
            return false;
        }

        // Mark the current cell as visited
        maze[row][col] = '*';

        // Define the possible directions to move in the maze
        int[] r = { -1, 1, 0, 0 };
        int[] c = { 0, 0, -1, 1 };

        // Recursively search in all four directions from the current cell
        for (int i = 0; i < c.length; i++) {
            boolean ans = search(maze, word, row + r[i], col + c[i], idx + 1);
            if (ans == true) {
                return ans; // If the word is found, return true
            }
        }

        // Backtrack: Restore the original character in the maze
        maze[row][col] = word.charAt(idx);
        return false; // If the word is not found starting from the current cell, return false
    }
}
