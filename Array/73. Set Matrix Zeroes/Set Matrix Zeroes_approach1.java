// Beats 100% with Space optimized
// Fastest solution

class Solution {
    public void setZeroes(int[][] matrix) {
        int rowCount = matrix.length; // number of rows in the matrix
        int colCount = matrix[0].length; // number of columns in the matrix
        boolean firstRowHasZero = false; // flag to check if the first row contains a zero
        boolean firstColHasZero = false; // flag to check if the first column contains a zero

        // Check if the first row has any zeros
        for (int col = 0; col < colCount; ++col) {
            if (matrix[0][col] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // Check if the first column has any zeros
        for (int row = 0; row < rowCount; ++row) {
            if (matrix[row][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        // Use the first row and column as markers.
        // Set matrix[i][0] and matrix[0][j] to 0 if matrix[i][j] is 0
        for (int row = 1; row < rowCount; ++row) {
            for (int col = 1; col < colCount; ++col) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        // Iterate over the matrix again using the first row and column as reference,
        // and set the elements to 0 accordingly.
        for (int row = 1; row < rowCount; ++row) {
            for (int col = 1; col < colCount; ++col) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        // Nullify the first row if needed
        if (firstRowHasZero) {
            for (int col = 0; col < colCount; ++col) {
                matrix[0][col] = 0;
            }
        }

        // Nullify the first column if needed
        if (firstColHasZero) {
            for (int row = 0; row < rowCount; ++row) {
                matrix[row][0] = 0;
            }
        }
    }
}

/*

Time and Space Complexity:
-----------------------------
The given code has a time complexity of O(m * n) where m is the number of rows and n is the number of columns in the given matrix. This is because the algorithm visits each cell twice, once to mark the rows and columns that should be zeroed and once to actually set the zeros.

For space complexity, the algorithm is O(1) as it uses constant extra space regardless of the size of the matrix. This is achieved by using the first row and first column of the matrix to store flags indicating whether a row or column should be set to zero.

*/
