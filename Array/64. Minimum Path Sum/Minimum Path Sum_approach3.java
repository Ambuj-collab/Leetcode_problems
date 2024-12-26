/*

Brute Force Approach:
------------------------
A brute force approach to find the minimum path sum in a grid would be to generate all possible paths from the top left corner to the bottom right corner and calculate the sum of each path. Then, we can return the minimum sum.

The recursive approach works by exploring all possible paths from the current cell (i, j) to the bottom-right corner (2, 2) and returning the minimum path sum. At each cell, we have two choices: move right or move down. We calculate the path sum for each choice and return the minimum of the two path sums.

In this approach, we can use recursion to find all possible paths from the top-left corner to the bottom-right corner of the grid, and return the minimum sum among them. However, this approach has an exponential time complexity of O(2^(m+n)) and is not optimal.

As you can see, the recursive approach explores all possible paths from the top-left corner to the bottom-right corner and returns the minimum path sum. However, it has overlapping subproblems, which can be avoided using dynamic programming.

*/


class Solution {
    public int minPathSum(int[][] grid) {
        return calculatePathSum(grid, 0, 0, 0);
    }
	
	/*
	
	The first parameter is the grid, the second and third parameters are the current cell's row and column indices, and the fourth parameter is the current path sum. At each recursive call, we check if we have reached the bottom-right corner. If so, we return the sum of the current cell and the current path sum. Otherwise, we calculate the path sum for moving right and moving down. We add the current cell's value to the path sum and pass it to the recursive calls. Finally, we return the minimum of the two path sums.
	
	*/
    private int calculatePathSum(int[][] grid, int i, int j, int sum) {
        // Base case: we have reached the bottom right corner
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return sum + grid[i][j];
        }

        // Calculate the minimum path sum by moving right and down
        int rightSum = Integer.MAX_VALUE;
        int downSum = Integer.MAX_VALUE;
        if (i < grid.length - 1) {
            downSum = calculatePathSum(grid, i + 1, j, sum + grid[i][j]);
        }
        if (j < grid[0].length - 1) {
            rightSum = calculatePathSum(grid, i, j + 1, sum + grid[i][j]);
        }

        // Return the minimum path sum
        return Math.min(rightSum, downSum);
    }
}

--------------------------------------------------------------------------------------------
											OR
--------------------------------------------------------------------------------------------
// Fastest solution

/*

Dynamic Programming Approach (Top-Down):
------------------------------------------
Dynamic Programming Approach (Top-Down): In this approach, we can use memoization to store the minimum sum to reach each cell of the grid, starting from the bottom-right corner and moving towards the top-left corner. We can then use these values to calculate the minimum sum to reach the top-left corner. This approach has a time complexity of O(m * n) and a space complexity of O(m * n).

In this code, we define a helper function minPathSum that takes in the grid, the current row i, the current column j, and a memoization matrix memo. The memoization matrix is used to store the minimum path sum for each cell so that we don't have to recalculate it again.

We check if we have already calculated the minimum path sum for the current cell in the memoization matrix. If we have, we return the value. Otherwise, we calculate the minimum path sum by moving right and down and store the result in the memoization matrix.

In the minPathSum function, we create the memoization matrix and call the helper function to find the minimum path sum starting from the top left corner.

The main function is the same as before, where we create a m*n grid and call the minPathSum function to find the minimum path sum.

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create a memoization matrix to store the minimum path sum for each cell
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Call the helper function to find the minimum path sum
        return minPathSum(grid, 0, 0, memo);
    }

    private int minPathSum(int[][] grid, int i, int j, int[][] memo) {
        int m = grid.length;
        int n = grid[0].length;

        // Check if we have already calculated the minimum path sum for this cell
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Base case: we have reached the bottom right corner
        if (i == m - 1 && j == n - 1) {
            memo[i][j] = grid[i][j];
            return memo[i][j];
        }

        // Calculate the minimum path sum by moving right and down
        int rightSum = Integer.MAX_VALUE;
        int downSum = Integer.MAX_VALUE;
        if (j < n - 1) {
            rightSum = minPathSum(grid, i, j + 1, memo);
        }
        if (i < m - 1) {
            downSum = minPathSum(grid, i + 1, j, memo);
        }

        // Store the minimum path sum for this cell in the memoization matrix
        memo[i][j] = Math.min(rightSum, downSum) + grid[i][j];

        // Return the minimum path sum
        return memo[i][j];
    }
}

--------------------------------------------------------------------------------------------
											OR
--------------------------------------------------------------------------------------------

/*

Dynamic Programming Approach (Bottom-Up):
--------------------------------------------
Dynamic Programming Approach (Bottom-Up): In this approach, we can traverse the grid from the top-left corner to the bottom-right corner and calculate the minimum sum to reach each cell, using the minimum sum to reach the previous cells in the same row and column. This approach has a time complexity of O(m * n) and a space complexity of O(m * n).

In this code, we create a dp matrix memo to store the minimum path sum for each cell. We initialize the first cell to be the value of the top left cell in the grid. Then, we calculate the minimum path sum for the first row and first column by adding up the values of the cells in the previous row or column.

We then traverse the remaining cells in the grid and calculate the minimum path sum by taking the minimum of the path sum from the cell above or the cell to the left, and adding the value of the current cell. We store the result in the dp matrix.

Finally, we return the minimum path sum to reach the bottom right corner, which is the value in the last cell of the dp matrix.

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create a dpization matrix to store the minimum path sum for each cell
        int[][] dp = new int[m][n];

        // Calculate the minimum path sum for the first row and first column
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Traverse the remaining cells and calculate the minimum path sum
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // Return the minimum path sum to reach the bottom right corner
        return dp[m - 1][n - 1];
    }
}
