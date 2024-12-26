class Solution {

    // Function to calculate the unique paths in a grid with obstacles
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Get the dimensions of the grid
        int numRows = obstacleGrid.length;
        int numCols = obstacleGrid[0].length;

        // Initialize a DP table with dimensions equivalent to the obstacle grid
        int[][] dp = new int[numRows][numCols];
      
        // Set up the first column of the DP table. If there is an obstacle,
        // paths beyond that point are not possible, so the loop will break.
        for (int row = 0; row < numRows && obstacleGrid[row][0] == 0; ++row) {
            dp[row][0] = 1;
        }
      
        // Set up the first row of the DP table. If there is an obstacle,
        // paths beyond that point are not possible, so the loop will break.
        for (int col = 0; col < numCols && obstacleGrid[0][col] == 0; ++col) {
            dp[0][col] = 1;
        }
      
        // Iterate over the grid starting from cell (1, 1) to calculate the
        // number of unique paths to each cell, considering the obstacles.
        for (int row = 1; row < numRows; ++row) {
            for (int col = 1; col < numCols; ++col) {
                // If the current cell is not an obstacle
                if (obstacleGrid[row][col] == 0) {
                    // Number of paths to current cell is the sum of paths to the
                    // cell above it and the cell to the left of it.
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                }
                // If the current cell is an obstacle, dp[row][col] remains 0
            }
        }
      
        // Return the number of unique paths to the bottom-right corner of the grid
        return dp[numRows - 1][numCols - 1];
    }
}

/*

Time and Space Complexity:
-----------------------------
The time complexity of the provided code is O(m*n) where m is the number of rows and n is the number of columns in the obstacleGrid. This is because the code contains two nested loops that iterate over each cell in the m x n grid exactly once, and the operations inside the loop are constant time operations.

The space complexity of the provided code is also O(m*n) since it uses a 2D list dp with the same dimensions as the obstacleGrid to store the number of unique paths to each cell.

*/