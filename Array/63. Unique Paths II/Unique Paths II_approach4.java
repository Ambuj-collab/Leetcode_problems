/*

Approach - Recursion with Memoization:
-----------------------------------------
The f function recursively calculates the number of unique paths from a given row and column to the bottom-right corner.
The base cases handle out-of-bounds cells (returning 0) and the top-left corner (returning 1).
Memoization is used to avoid redundant calculations by storing results in the dp array.
The recursive calls explore the two possible paths (down and right) and sum the results.

*/

class Solution {

    public int uniquePathsWithObstacles(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1); // Initialize each cell of the array individually
        }
        return f(arr, m - 1, n - 1, dp);
    }

    public int f(int[][] arr, int r, int c, int[][] dp) {
        if (r < 0 || c < 0 || arr[r][c] == 1) {
            return 0; // If current cell is out of bounds or an obstacle, return 0
        }
        if (r == 0 && c == 0) {
            return 1; // Base case: top-left cell
        }
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        int up = f(arr, r - 1, c, dp);
        int left = f(arr, r, c - 1, dp);
        return dp[r][c] = up + left;
    }
}

-----------------------------------------------------------------------------
										OR
-----------------------------------------------------------------------------

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        // Initialize the memoization array with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return dfs(obstacleGrid, 0, 0, memo);
    }

    private int dfs(int[][] obstacleGrid, int i, int j, int[][] memo) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (i >= m || j >= n || obstacleGrid[i][j] == 1) {
            return 0;
        }
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        memo[i][j] = dfs(obstacleGrid, i + 1, j, memo) + dfs(obstacleGrid, i, j + 1, memo);
        return memo[i][j];
    }
}


/*

Time Complexity:
------------------
Both approaches have a time complexity of O(m * n), where m is the number of rows and n is the number of columns. This is because the dp array is filled once, and the iterations are linear.  

Space Complexity:
-------------------
Recursion with Memoization: O(m * n) due to the dp array.
Tabulation: O(m * n) due to the dp array.

*/