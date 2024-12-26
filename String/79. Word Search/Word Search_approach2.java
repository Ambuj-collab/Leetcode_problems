/*

Intuition:
------------
Check 4 directions with counting length of a path.

Approach:
----------
Simply check 4 directions from every place and if we find the next target character, then move to that place.
But problem is that we don't know current length of path(= word), so every time we move to a new place, count 1 as a length of path, so that when the path length is equal to input word, we can return True.

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
    private int rows;
    private int cols;
    private Set<String> visited;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        visited = new HashSet<>();

        Map<Character, Integer> count = new HashMap<>();
        for (char c : word.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        if (count.getOrDefault(word.charAt(0), 0) > count.getOrDefault(word.charAt(word.length() - 1), 0)) {
            word = new StringBuilder(word).reverse().toString();
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int k) {
        if (k == word.length()) {
            return true;
        }

        if (r < 0 || r >= rows || c < 0 || c >= cols || visited.contains(r + "," + c) || board[r][c] != word.charAt(k)) {
            return false;
        }

        visited.add(r + "," + c);
        boolean res = dfs(board, word, r + 1, c, k + 1) ||
                      dfs(board, word, r - 1, c, k + 1) ||
                      dfs(board, word, r, c + 1, k + 1) ||
                      dfs(board, word, r, c - 1, k + 1);
        visited.remove(r + "," + c);
        return res;
    }    
}


-------------------------------------------------------------------------------------------------
												OR
-------------------------------------------------------------------------------------------------


class Solution {
    public boolean exist(char[][] board, String word) {
        if (word.length() > board.length * board[0].length)
            return false;
        // ~ not possible, if board has lower char frequency that target
        // O(n*m)
        int[] F = new int[256];
        for (int r = 0; r < board.length; ++r) {
            for (int c = 0; c < board[0].length; ++c) {
                F[board[r][c]] += 1;
            }
        }

        for (char v : word.toCharArray()) {
            F[v] -= 1;
            if (F[v] < 0)
                return false;
        }

        // ~ check for arrangement. Exponential exploration
        for (int r = 0; r < board.length; ++r) {
            for (int c = 0; c < board[0].length; ++c) {
                boolean res = search(board, word, r, c, 0);
                if (res)
                    return true;
            }
        }
        return false;
    }

    boolean search(char[][] board, String word, int r, int c, int pos) {
        if (pos == word.length())
            return true;
        char v = word.charAt(pos++);
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != v)
            return false;

        board[r][c] ^= '#';
        boolean res = search(board, word, r + 1, c, pos)
                || search(board, word, r, c + 1, pos)
                || search(board, word, r - 1, c, pos)
                || search(board, word, r, c - 1, pos);
        board[r][c] ^= '#';
        return res;
    }
}
