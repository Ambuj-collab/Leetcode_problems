/*

IDEA : ALWAYS REMEMBER WHENEVER YOU FEEL LIKE THERE IS A NEED TO FIND ALL POSSIBLE WAYS, THERE'S A RECURSION(which can further be improved).
The state equation is S[i][j] = min(S[i - 1][j], S[i][j - 1]) + grid[i][j]

Here, to get a path, we need to travel from grid[0][0] to grid[row - 1][col - 1]. So let's set grid[0][0] as the basic case. This is when we jump out of recursion. On the other hand, grid[row - 1][col - 1] would be the starting point. We need to consider that things could happen that we reached the first row or column and we gotta make sure that we stay within the array index limit.
We will move in grid for every row and column, looking for minimum path sum.

*/

class Solution {
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        return min(grid, m - 1, n - 1);

    }

    public int min(int[][] grid, int m, int n) {

        // this is the exit of the recursion
        if (m == 0 && n == 0)
            return grid[m][n];
        
        /** when we reach the first row, we could only move horizontally. */
        if (m == 0)
            return grid[m][n] + min(grid, m, n - 1);
        
        /** when we reach the first column, we could only move vertically. */
        if (n == 0)
            return grid[m][n] + min(grid, m - 1, n);
        
        /** we want the min sum path so we pick the cell with the less value */
        return grid[m][n] + Math.min(min(grid, m - 1, n), min(grid, m, n - 1));

    }
}

/*

The provided solution employs a recursive approach to calculate the minimum path sum in a grid. Let's analyze the time and space complexity.

Time Complexity:
------------------
Recursive Calls:
The recursive function min() computes the minimum path sum by exploring two possibilities:

    Moving up (to grid[m-1][n])
    Moving left (to grid[m][n-1])

In the worst-case scenario, for each cell (m, n), the function is called twice, once for the cell above it and once for the cell to the left. This results in an exponential number of recursive calls. Specifically, the number of recursive calls grows exponentially with the size of the grid.

    For an m x n grid, in the worst case, the number of calls will be O(2^(m + n)) since for each call, you recursively explore both directions (up and left) until reaching the base case (0, 0).

Thus, the time complexity of this recursive solution is exponential: O(2^(m+n)), where m is the number of rows and n is the number of columns in the grid.

Space Complexity:
-------------------
Recursion Depth:
The recursion depth is limited by the maximum depth of the recursive calls, which corresponds to the longest path from the top-left corner (0, 0) to the bottom-right corner (m-1, n-1). This path could go all the way down the grid, either down the rows or along the columns, so the maximum depth of the recursion stack is m + n - 1.

Thus, the space complexity due to recursion depth is: O(m+n), where m is the number of rows and n is the number of columns.

Optimizing the Solution:
--------------------------
To avoid the exponential time complexity, we can use dynamic programming to memoize the results of previously computed subproblems. This would reduce the time complexity to O(m * n) and the space complexity to O(m * n) as well.

*/

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

/*

DP - Memoization:
simply memoization to handle the recursion
----------------------100% faster-----------------------1ms---------------------------

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        return find(grid, m - 1, n - 1, memo);
    }

    private int find(int[][] grid, int m, int n, int[][] memo) {
        if (m == 0 && n == 0)
            return grid[0][0];
        else if (m < 0 || n < 0)
            return Integer.MAX_VALUE;
        else if (memo[m][n] != 0)
            return memo[m][n];
        
        return memo[m][n] = grid[m][n] + Math.min(find(grid, m - 1, n, memo), find(grid, m, n - 1, memo));
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------
/*

Let's optimize the solution using dynamic programming (DP) to reduce the time complexity from exponential to polynomial.
Optimized Approach Using Dynamic Programming

Instead of recalculating the same subproblems multiple times (as done in the recursive solution), we can store the results of subproblems in a 2D DP table and refer to them when needed.

The idea is to iteratively build up the solution for the grid, starting from the top-left corner (0, 0) and working our way down to the bottom-right corner (m-1, n-1).

Here's how we can do that:

    1) DP Table: Let dp[i][j] represent the minimum path sum to reach cell (i, j) from the top-left corner (0, 0).
    2) Transition Formula:
        a) If you're at cell (i, j), you can come either from the left (i, j-1) or from above (i-1, j).
        b) The minimum path sum to reach (i, j) will be:
			dp[i][j]=grid[i][j] + min(dp[i−1][j],dp[i][j−1])
    3) Base Case:
        a) For the first row, you can only move horizontally (from the left).
        b) For the first column, you can only move vertically (from above).
    
	4) Final Answer: The bottom-right corner of the DP table, dp[m-1][n-1], will give the minimum path sum.

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // DP table to store the minimum path sums
        int[][] dp = new int[m][n];

        // Initialize the top-left cell
        dp[0][0] = grid[0][0];

        // Fill the first row (can only come from the left)
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Fill the first column (can only come from above)
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Fill the rest of the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // The bottom-right corner holds the result
        return dp[m - 1][n - 1];
    }
}

/*

Time Complexity:
------------------
Filling the DP table: We visit each cell exactly once and perform constant work (a single comparison and addition) at each cell. There are m * n cells in the grid, and for each cell, we do constant work.

Thus, the time complexity is:
	O(m*n)
where m is the number of rows and n is the number of columns.

Space Complexity:
-------------------
DP table: We use an m x n DP table to store the minimum path sums for each cell.

Thus, the space complexity is:
O(m×n)
O(m×n)

where m is the number of rows and n is the number of columns.

*/

-------------------------------------------------------------------------------------------
											OR
-------------------------------------------------------------------------------------------

/*

Always Remember:
-------------------
If tabulation approach contains something like (i+1) or (i-1), that means you can always space optimized it.
i+1 or i-1 means we are using last calculated results, which we can store in the input array itself too, and very easily you can do space optimization.

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 1; i < m; i++)
            grid[i][0] += grid[i - 1][0];
        
        for (int j = 1; j < n; j++)
            grid[0][j] += grid[0][j - 1];
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        
        return grid[m - 1][n - 1];
    }
}

-------------------------------------------------------------------------------------------
											OR
-------------------------------------------------------------------------------------------

/*

We can optimize the space complexity by using only a 1D array instead of a full 2D DP table. Since the current cell only depends on the cell directly above it and the cell to the left of it, we can use a single 1D array to keep track of the minimum path sums for the current row, updating it as we go.

Space Optimized Version:
--------------------------
Instead of using an m x n DP table, we will use a single 1D array of size n (representing the current row) and update it as we iterate through the grid.

Key Idea:
------------
    We maintain an array dp where dp[j] stores the minimum path sum to reach cell (i, j), which means we only need to store the results of the current row and update it for the next row.

Transition:
-------------
    1) First cell (0, 0): Initialize the first element of the dp array with grid[0][0].
    2) First row: Can only move from the left, so update the array in place:
        dp[j] = dp[j-1] + grid[0][j] for j = 1 to n-1.
    3) Subsequent rows:
        For each cell (i, j), update dp[j] based on the minimum of the top cell (dp[j] from the previous row) and the left cell (dp[j-1] from the same row).

Space Complexity:
-------------------
We now only need an array of size n (the number of columns), so the space complexity is O(n).

*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Use a 1D array to store the dp values for the current row
        int[] dp = new int[n];

        // Initialize the first cell (dp[0] is the same as grid[0][0])
        dp[0] = grid[0][0];

        // Fill the first row (can only come from the left)
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // Process the rest of the grid
        for (int i = 1; i < m; i++) {
            // First cell in the row (can only come from above)
            dp[0] = dp[0] + grid[i][0];

            // Update the rest of the cells in the row
            for (int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }

        // The result will be in the last cell of the dp array
        return dp[n - 1];
    }
}

/*

Time Complexity:
-------------------
    The time complexity remains O(m * n) because we still visit each cell exactly once, and for each cell, we perform constant work.
		(where m is the number of rows and n is the number of columns)

Space Complexity:
-------------------
    The space complexity is now reduced to O(n), where n is the number of columns in the grid, as we are only using a 1D array of size n.

*/
