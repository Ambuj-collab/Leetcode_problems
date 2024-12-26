public class Solution {

    public int[][] generateMatrix(int n) {
        // Initialize the matrix to be filled.
        int[][] matrix = new int[n][n];

        // Starting point for the spiral is (0,0), top-left corner of the matrix.
        int row = 0, col = 0;

        // 'dirIndex' is used to determine the current direction of the spiral.
        int dirIndex = 0;

        // Define directions for right, down, left, up movement.
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        // Fill up the matrix with values from 1 to n squared.
        for (int value = 1; value <= n * n; ++value) {
            // Place the value into the matrix.
            matrix[row][col] = value;

            // Calculate the next position using the current direction.
            int nextRow = row + directions[dirIndex][0];
            int nextCol = col + directions[dirIndex][1];

            // Check boundary conditions and whether the cell is already filled.
            if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n || matrix[nextRow][nextCol] > 0) {
                // Change direction: right -> down -> left -> up -> right ...
                dirIndex = (dirIndex + 1) % 4;
                // Calculate the position again after changing direction.
                nextRow = row + directions[dirIndex][0];
                nextCol = col + directions[dirIndex][1];
            }

            // Move to the next cell.
            row = nextRow;
            col = nextCol;
        }

        // Return the filled spiral matrix.
        return matrix;
    }

}
