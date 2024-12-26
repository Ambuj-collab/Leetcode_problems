class Solution {
    public void rotate(int[][] matrix) {
        // Obtain the length of the matrix which is a square (same width and height)
        int n = matrix.length;

        // Step 1: Perform a vertical flip of the matrix
        // Loop over the first half of the rows (vertically)
        for (int row = 0; row < (n >> 1); ++row) {
            // Loop over all columns
            for (int col = 0; col < n; ++col) {
                // Swap the element at the current position with the element at the mirrored row
                // across the horizontal axis
                int temp = matrix[row][col];
                matrix[row][col] = matrix[n - row - 1][col];
                matrix[n - row - 1][col] = temp;
            }
        }

        // Step 2: Transpose the matrix by flipping it along its diagonal
        // Loop over all rows
        for (int row = 0; row < n; ++row) {
            // Loop over the columns up to the current row (to avoid re-flipping)
            for (int col = 0; col < row; ++col) {
                // Swap the element at (row, col) with the element at (col, row)
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    }
}

/*

Time and Space Complexity:
----------------------------
The time complexity of the given code is O(n^2), where n is the length of the matrix. This is because there are two nested loops that iterate over the elements of the matrix. The first set of nested loops is responsible for the vertical flipping of the matrix (first part of the rotation), and it iterates over half of the matrix rows (hence n/2) for all columns, but since we drop constants when expressing time complexity, it simplifies to O(n^2).

The second part of the rotation is swapping elements across the diagonal, again involving a nested loop, but this time it only processes elements in the upper triangle (excluding the diagonal) of the matrix, which still leads to a total of n*(n-1)/2 swaps. Despite the fact that only roughly half of the matrix elements are being swapped (upper triangle), this still results in a time complexity of O(n^2) because the leading term n^2 dominates as n grows large.

As for the space complexity, the reference answer correctly states it is O(1). The algorithm only uses a constant amount of extra space for variable storage, regardless of the size of the input matrix. The rotation is done in place, therefore no additional space proportional to the input size is required.

*/