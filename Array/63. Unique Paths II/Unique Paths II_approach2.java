class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] == 0)
                dp[i][0] = 1;
            else
                break;
        }
        
        for (int j = 0; j < m; j++) {
            if (obstacleGrid[0][j] == 0)
                dp[0][j] = 1;
            else
                break;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }
}

------------------------------------------------------------------------------
										OR
------------------------------------------------------------------------------

/*

Intuition:
------------
This is a Java solution for finding the number of unique paths in a grid with obstacles. The task is to move only down or right from the top-left corner to the bottom-right corner of the grid, but there are obstacles in some positions that cannot be crossed.

Approach:
-----------
The approach used in this solution is similar to the previous solution, with some modifications to handle the obstacles. The idea is to create a 2D dp table to store the number of unique paths to reach each position in the grid, but if there is an obstacle in a position, the number of paths to that position is set to zero. The number of unique paths to reach a position (i,j) is the sum of the number of unique paths to reach the position (i-1,j) and (i,j-1), but only if there is no obstacle in that position.

The dp table is filled up row by row, starting from the top-left corner of the grid. The left column and top row are initialized separately, because there is only one way to reach any position in them (unless there is an obstacle in the way).

Time and Space Complexity:
----------------------------
Time complexity: O(mn), where m is the number of rows and n is the number of columns in the grid.

Space complexity: O(mn), since we are using a 2D dp table to store the number of unique paths.

*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0)
            return 0;
		
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
		
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;
		
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
		
        // left column
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
		
        // top row
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
		
        // fill up cells inside
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
		
        return dp[m - 1][n - 1];
    }
}

/*

Time and Space Complexity:
-----------------------------
Space Complexity: O(mn)
Time Complexity: O(mn)

where m is the number of rows and n is the number of columns in the grid.

*/