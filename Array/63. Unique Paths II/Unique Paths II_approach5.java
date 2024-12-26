/*

Explanation:
---------------
1) Grid and DP Table Initialization:
	a) int m = obstacleGrid.length;: Gets the number of rows (m) from the obstacleGrid array.
	b) int n = obstacleGrid[0].length;: Gets the number of columns (n) from the first row of obstacleGrid.
	c) int[][] dp = new int[m][n];: Creates a 2D integer array dp to store the number of unique paths reaching each cell, considering obstacles.

2) Iterating Through the Grid:
	Nested loops iterate through the rows and columns of the grid and dp table.
		a) for (int i = 0; i < m; i++) { ... }: Iterates through rows.
		b) for (int j = 0; j < n; j++) { ... }: Iterates through columns within each row.

3) Handling Obstacles and Base Case:
	a) if (obstacleGrid[i][j] == 1) { ... }: Checks if the current cell in obstacleGrid is an obstacle (value 1). If so, it sets the corresponding cell in dp to 0, indicating no paths can go through that cell.
	b) else if (i == 0 && j == 0) { ... }: Base case - if it's the starting cell (top-left corner) and there's no obstacle, it sets dp[i][j] to 1, indicating one path (not moving).

4) Calculating Paths Considering Obstacles:
	a) int up = 0;: Initializes up to store the number of paths coming from above (previous row).
	b) if (i > 0) up = dp[i - 1][j];: If the current row (i) is not the first row and there's no obstacle in the cell above (dp[i - 1][j] != 1), it retrieves the number of paths from the cell above.
	c) int left = 0;: Initializes left to store the number of paths coming from the left (previous column).
	d) if (j > 0) left = dp[i][j - 1];: If the current column (j) is not the first column and there's no obstacle in the cell to the left (dp[i][j - 1] != 1), it retrieves the number of paths from the cell to the left.
	e) dp[i][j] = up + left;: If there are no obstacles blocking the paths from above and left, it calculates the total number of unique paths reaching the current cell by adding up and left. The result is stored in dp[i][j].

5) Returning the Result:
	return dp[m - 1][n - 1];: After filling the dp table considering obstacles, the code returns the value at dp[m - 1][n - 1], which represents the number of unique paths reaching the bottom-right corner cell if there's a valid path considering all obstacles.

*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = 0;
                    int left = 0;

                    if (i > 0)
                        up = dp[i - 1][j];

                    if (j > 0)
                        left = dp[i][j - 1];

                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}


---------------------------------------------------------------------
								OR
---------------------------------------------------------------------

/*

Idea:
-------
The naive approach here would be to try every path with a recursive depth first search (DFS) approach. That would involve duplicating the processing used for repeating subpaths, however, which would quickly lead to a TLE result. When faced with repeating subproblems, we should be thinking of a dynamic programming (DP) approach to store completed subproblem and avoid any unnecessary duplication of processing.

In this situation, we can create a DP matrix (dp) in the same dimensions as our input matrix (obstacleGrid). (Note: We can choose to use an in-place approach here and use obstacleGrid as our DP matrix in order to reduce the space complexity of our solution to O(1).) Each cell in dp will represent the number of paths that lead to the corresponding cell in obstacleGrid. Since the robot can only move either to the right or down, we can perform a bottom-up DP solution, working from the initial cell and iterating downward and rightward through obstacleGrid.

Each cell in obstacleGrid (obstacleGrid[i][j]) can potentially reached by only two previously-visited cells (obstacleGrid[i-1][j] & obstacleGrid[i][j-1]), so the number of ways to reach the current cell (dp[i][j]) should be the sum of the ways to reach those other two cells (dp[i-1][j] + dp[i][j-1]), should they exist.

Since any cell representing an obstacle cannot be a part of a path, its value in dp should be 0. We'll also need to seed the initial starting position with a value of 1 to represent the single initial path. Once we're done building dp, the value of the bottom-right cell should be our answer.

*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)
            return 0;

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (obstacleGrid[i][j] == 1 || (i == 0 && j == 0))
                    continue;
                else
                    dp[i][j] = (i > 0 ? dp[i - 1][j] : 0) + (j > 0 ? dp[i][j - 1] : 0);

        return dp[m - 1][n - 1];
    }
}

/*

Time Complexity: O(N * M) where N and M are the dimensions of the input matrix
Space Complexity: O(N * M) for the DP matrix

*/