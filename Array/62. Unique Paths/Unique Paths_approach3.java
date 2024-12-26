/*

Question Understanding:
-------------------------
Given are only two valid moves:
	Move right
	Move down
Hence, it is clear that number of paths from cell (i,j) = sum of paths from cell( i+1,j) and cell(i,j+1)

It becomes a DP problem.
But implementing DP directly will lead to TLE.
So memoization is useful -> Storing a result so that we donot need to calculate it again.

dp[i][j] = d[i+1][j] + dp[i][j+1]

BASE CASES
If we are in last row, i == m-1, we only have the choice to move RIGHT, Hence number of moves will be 1.
If we are in last column, j == n-1, we only have the choice to move DOWN, Hence number of moves will be 1.

*/

Solutions:
-----------------------------
// Bottom up Approach

/*

Bottom-Up Dynamic Programming:
--------------------------------
Step 1: Initialize the DP Table
	a) Create a 2D DP (dynamic programming) table of size m x n to store the number of unique paths for each cell.
	b) Initialize the rightmost column and bottom row of the DP table to 1 because there's only one way to reach each cell in those rows/columns (by moving all the way right or all the way down).

Step 2: Fill in the DP Table
	a) Start from the second-to-last row and second-to-last column (i.e., i = m - 2 and j = n - 2).
	b) For each cell (i, j) in the grid:
		i) Calculate the number of unique paths to reach (i, j) as the sum of the unique paths from the cell below (i+1, j) and the cell to the right (i, j+1). Use this formula: dp[i][j] = dp[i+1][j] + dp[i][j+1].
		ii) Continue filling in the DP table row by row and column by column until you reach the top-left corner (dp[0][0]).

Step 3: Return the Result
	Return the value stored in the top-left corner of the DP table (dp[0][0]), which represents the number of unique paths from the top-left corner to the bottom-right corner.

*/

class Solution {
    public int uniquePaths(int m, int n) {
        // Create a 2D DP array filled with zeros
        int[][] dp = new int[m][n];
        
        // Initialize the rightmost column and bottom row to 1
        for (int i = 0; i < m; i++) {
            dp[i][n-1] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[m-1][j] = 1;
        }
        
        // Fill in the DP array bottom-up
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        
        // Return the result stored in the top-left corner
        return dp[0][0];
    }
}

--------------------------------------------------------------------------------------
											OR
--------------------------------------------------------------------------------------

// Top-Down Approach:

/*

Top-Down Dynamic Programming:
-------------------------------
Step 1: Initialize the Memoization Table
	Create a memoization table (an auxiliary 2D array) of size m x n to store computed results. Initialize all entries to -1 to indicate that no results have been computed yet.

Step 2: Recursive Function
	a) Implement a recursive function, say uniquePathsRecursive(x, y, m, n, memo), which calculates the number of unique paths to reach cell (x, y) from the top-left corner.
	b) In this function:
		i) Check if (x, y) is the destination cell (m - 1, n - 1). If yes, return 1 since there is one way to reach the destination.
		ii) Check if the result for (x, y) is already computed in the memoization table (memo[x][y] != -1). If yes, return the stored result.
		iii) Otherwise, calculate the number of unique paths by recursively moving right and down (if valid) and adding the results. Use the following logic:
			-> If (x, y) can move right (i.e., x < m - 1), calculate rightPaths = uniquePathsRecursive(x + 1, y, m, n, memo).
			-> If (x, y) can move down (i.e., y < n - 1), calculate downPaths = uniquePathsRecursive(x, y + 1, m, n, memo).
			-> The total unique paths to (x, y) are rightPaths + downPaths.
			-> Store the result in the memoization table (memo[x][y]) and return it.

Step 3: Invoke the Recursive Function
	Call the recursive function with the initial arguments (0, 0, m, n, memo) to find the number of unique paths.

Step 4: Return the Result
	The result obtained from the recursive function call represents the number of unique paths from the top-left corner to the bottom-right corner.

*/

class Solution {
    public int uniquePaths(int m, int n) {
        // Create a memoization table to store computed results
        int[][] memo = new int[m][n];
        
        // Initialize the memoization table with -1 to indicate uncomputed results
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        // Call the recursive function to compute unique paths
        return uniquePathsRecursive(0, 0, m, n, memo);
    }
    
    public int uniquePathsRecursive(int x, int y, int m, int n, int[][] memo) {
        // If we reach the destination (bottom-right corner), return 1
        if (x == m - 1 && y == n - 1) {
            return 1;
        }
        
        // If we have already computed the result for this cell, return it from the memo table
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        
        // Calculate the number of unique paths by moving right and down
        int rightPaths = 0;
        int downPaths = 0;
        
        // Check if it's valid to move right
        if (x < m - 1) {	//  if (x+1 < m) {
            rightPaths = uniquePathsRecursive(x + 1, y, m, n, memo);
        }
        
        // Check if it's valid to move down
        if (y < n - 1) {	//  if (y+1 < n) {
            downPaths = uniquePathsRecursive(x, y + 1, m, n, memo);
        }
        
        // Store the result in the memo table and return it
        memo[x][y] = rightPaths + downPaths;
        return memo[x][y];
    }
}


/*

Time and Space Complexity:
----------------------------
Bottom-Up Dynamic Programming:
	Time Complexity (TC): The bottom-up approach fills in the DP table iteratively, visiting each cell once. There are m rows and n columns in the grid, so the TC is O(m * n).
	
	Space Complexity (SC): The space complexity is determined by the DP table, which is of size m x n. Therefore, the SC is O(m * n) to store the DP table.

Top-Down Dynamic Programming (with Memoization):
	Time Complexity (TC): O(m * n).
	Space Complexity (SC): O(m * n).

*/