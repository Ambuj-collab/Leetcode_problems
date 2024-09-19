class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (word.length() == 1) {
                        return true;
                    } else if (isExist(board, word, visited, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isExist(char[][] board, String word, boolean[][] visited, int row, int col, int wordPos) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        if (wordPos == word.length()) {
            return true;
        }

        if (board[row][col] != word.charAt(wordPos)) {
            return false;
        }

        if (visited[row][col] == true) {
            return false;
        }

        visited[row][col] = true;

        boolean a = isExist(board, word, visited, row, col + 1, wordPos + 1);
        boolean b = isExist(board, word, visited, row, col - 1, wordPos + 1);
        boolean c = isExist(board, word, visited, row + 1, col, wordPos + 1);
        boolean d = isExist(board, word, visited, row - 1, col, wordPos + 1);

        visited[row][col] = false;

        if (a || b || c || d) {
            return true;
        }

        return false;
    }
}