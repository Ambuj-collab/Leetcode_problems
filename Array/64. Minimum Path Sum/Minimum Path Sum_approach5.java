class Solution {

    // Method to find the minimum path sum in a grid.

    public int minPathSum(int[][] grid) {

        // m and n store the dimensions of the grid.

        int m = grid.length, n = grid[0].length;


        // dp array stores the minimum path sums.

        int[][] dp = new int[m][n];


        // Initialize top-left cell with its own value as this is the starting point.

        dp[0][0] = grid[0][0];


        // Fill in the first column (vertical path) by accumulating values.

        for (int i = 1; i < m; ++i) {

            dp[i][0] = dp[i - 1][0] + grid[i][0];

        }


        // Fill in the first row (horizontal path) by accumulating values.

        for (int j = 1; j < n; ++j) {

            dp[0][j] = dp[0][j - 1] + grid[0][j];

        }


        // Fill in the rest of the grid.

        for (int i = 1; i < m; ++i) {

            for (int j = 1; j < n; ++j) {

                // The cell dp[i][j] is the minimum of the cell above or to the left of it,

                // plus the value in the current cell of the grid.

                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];

            }

        }


        // Return the bottom-right cell which contains the min path sum from top-left to bottom-right.

        return dp[m - 1][n - 1];

    }

}

/*

Time and Space Complexity:
------------------------------
Time Complexity:
The time complexity of the code is determined by the nested loops that iterate over the entire grid matrix. Since we iterate through all rows m and all columns n of the grid, the time complexity is O(m * n).

Space Complexity:
The space complexity of the code is given by the additional 2D array f that we use to store the minimum path sums. This array is of the same size as the input grid, so the space complexity is also O(m * n). We do not use any other significant space that grows with the size of the input.

*/