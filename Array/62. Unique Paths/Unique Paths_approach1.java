class Solution {
    public int uniquePaths(int m, int n) {
        int[] aboveRow = new int[n];
        Arrays.fill(aboveRow, 1);

        for (int row = 1; row < m; row++) {
            int[] currentRow = new int[n];
            Arrays.fill(currentRow, 1);
            for (int col = 1; col < n; col++) {
                currentRow[col] = currentRow[col - 1] + aboveRow[col];
            }
            aboveRow = currentRow;
        }

        return aboveRow[n - 1];
    }
}

--------------------------------------------------------------------------------------
										OR
--------------------------------------------------------------------------------------

class Solution {
    public int uniquePaths(int m, int n) {
        int[] aboveRow = new int[n];
        Arrays.fill(aboveRow, 1);

        for (int row = 1; row < m; row++) {
            int[] currentRow = new int[n];
            currentRow[0] = 1;
            for (int col = 1; col < n; col++) {
                currentRow[col] = currentRow[col - 1] + aboveRow[col];
            }
            aboveRow = currentRow;
        }

        return aboveRow[n - 1];
    }
}

--------------------------------------------------------------------------------------
										OR
--------------------------------------------------------------------------------------

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);

        for (int row = 1; row < m; row++) {
            dp[row][0] = 1;
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                dp[row][col] = dp[row][col - 1] + dp[row - 1][col];
            }
        }

        return dp[m - 1][n - 1];
    }
}

---------------------------------------------------------------------------------------
											OR
---------------------------------------------------------------------------------------

/*

Problem Understanding:
------------------------
The problem describes a robot situated on a m×n grid, starting at the top-left corner (i.e., grid[0][0]). The robot can move either to the right or downwards at any given time, and the objective is to reach the bottom-right corner of the grid. The challenge is to find the number of unique paths the robot can take to reach this goal.

Key Points to Consider:
--------------------------
1) Grid Dimensions:
	The grid dimensions are m (rows) and n (columns), with 1≤m,n≤100.

2) Movement Constraints:
	The robot can only move either down or to the right at any given point. It cannot move diagonally or backwards.

3) Dynamic Programming:
	The problem can be solved using the Dynamic Programming approach
	
Approach - Dynamic Programming:
---------------------------------
Intuition and Logic Behind the Solution
	The idea behind this approach is to use a 2D Dynamic Programming (DP) array to store the number of unique paths to each cell. A cell (i,j) can be reached either from (i−1,j) or (i,j−1), and thus the number of unique paths to (i,j) is the sum of the number of unique paths to these two cells.

Step-by-step Explanation:
----------------------------
1) Initialization:
	Create a m×n DP array, initializing the first row and first column to 1 because there's only one way to reach those cells from the starting point.
2) Main Algorithm:
	Iterate over the DP array starting from cell (1,1).
	For each cell (i,j), set dp[i][j]=dp[i−1][j]+dp[i][j−1].

*/

public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}


/*

Time and Space Complexity:
----------------------------
Time complexity: O(m * n)
Space complexity: O(n)

where n is the number of columns in the grid and m is the number of rows in the grid

*/