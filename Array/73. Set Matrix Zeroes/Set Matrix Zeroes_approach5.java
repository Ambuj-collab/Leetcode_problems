class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] isOriginalZero = new boolean[rows][cols];

        // First pass: record the position of original zeros in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    isOriginalZero[i][j] = true;
                }
            }
        }

        // Second pass: set the corresponding rows and columns to zero
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isOriginalZero[i][j]) {
                    // Set the entire row to zero
                    for (int c = 0; c < cols; c++) {
                        matrix[i][c] = 0;
                    }
                    // Set the entire column to zero
                    for (int r = 0; r < rows; r++) {
                        matrix[r][j] = 0;
                    }
                }
            }
        }
    }
}

------------------------------------------------------------------------------------------
											OR
------------------------------------------------------------------------------------------
// Beats 100%, but not space optimized

class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] row = new boolean[rows];
        boolean[] col = new boolean[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

------------------------------------------------------------------------------------------
											OR
------------------------------------------------------------------------------------------

class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstrowto0 = false;
        boolean firstcolumnto0 = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0) {
                        firstrowto0 = true;
                    }
                    if (j == 0) {
                        firstcolumnto0 = true;
                    }
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstcolumnto0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firstrowto0) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}

------------------------------------------------------------------------------------------
											OR
------------------------------------------------------------------------------------------

class Solution {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        List<Pair<Integer, Integer>> V = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0)
                    V.add(new Pair<>(i, j));
            }
        }

        for (Pair<Integer, Integer> p : V) {
            int row = p.getKey();
            int col = p.getValue();

            for (int i = 0; i < m; i++)
                matrix[row][i] = 0;

            for (int i = 0; i < n; i++)
                matrix[i][col] = 0;
        }
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity: O(m*n), where m is the number of rows and n is the number of columns in the matrix. This arises because the algorithm needs to traverse the entire matrix to find zeroes and then again to set rows and columns to zero.

Space complexity: O(k), where k is the number of zeroes in the matrix. This space is used to store the positions of zeroes. In the worst case, if the matrix is filled with zeroes, the space complexity could approach O(m*n), but typically it would be much less.

*/