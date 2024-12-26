// Dynamic Programming - Tabulation (in-place dp 2D array)

/*

Approach:
------------
    The code implements a dynamic programming approach to find the minimum path sum in a grid.

    The algorithm uses a 2D array to store the minimum path sum to reach each position (i, j) in the grid, where i represents the row and j represents the column.

    The minimum path sum to reach each position (i, j) is computed by taking the minimum of the path sum to reach the position above (i-1, j) and the position to the left (i, j-1), and adding the cost of the current position (i, j).

    The minimum path sum to reach the bottom-right corner of the grid is stored in the last element of the array (grid[m-1][n-1]), where m is the number of rows and n is the number of columns in the grid.

Intuition:
-------------
    The intuition behind the dynamic programming approach is that the minimum path sum to reach a position (i, j) in the grid can be computed by considering the minimum path sum to reach the positions (i-1, j) and (i, j-1).

    This is because the only two possible ways to reach the position (i, j) are either by moving down from (i-1, j) or moving right from (i, j-1).

    By computing the minimum path sum to reach each position in the grid, the algorithm can find the minimum path sum to reach the bottom-right corner of the grid by simply looking at the last element of the array (grid[m-1][n-1]).

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[m - 1][n - 1];
    }
}

/*

Time and Space Complexity:
----------------------------
The time complexity of the given algorithm is O(m*n), where m and n are the dimensions of the input grid. This is because we traverse each cell of the matrix only once.

The space complexity of the algorithm is O(1) since we are modifying the input grid in place and not using any extra space other than the variables m and n.

Therefore, the algorithm has both time and space complexity of O(m*n) and O(1) respectively.

*/

---------------------------------------------------------------------------------
										OR
---------------------------------------------------------------------------------

class Solution {
    public int minPathSum(int[][] cost) {
        int m = cost.length;
        int n = cost[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = cost[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + cost[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + cost[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = cost[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

}

