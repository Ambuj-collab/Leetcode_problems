/*

Approach: Dynamic Programming - Memoization:
----------------------------------------------
This solution uses dynamic programming with memoization to calculate the number of unique paths in a m x n grid. The goal is to move from the top-left corner to the bottom-right corner, only moving right or down.

Steps:
--------
1) Initialization:
	-> Create a 2D array a of size m x n to store the number of unique paths to each cell.
	-> Initialize the array with zeros.

2) Recursive method (dest):
	-> If the current position (i, j) is out of bounds (i >= m or j >= n), return 0 because it's not a valid path.
	-> If the current position (i, j) is the bottom-right corner of the grid (i == m-1 and j == n-1), return 1 because we've found a valid path.
	-> If the value at a[i][j] is greater than 0, return the stored value. This avoids redundant calculations (memoization).
	-> Otherwise, calculate the number of paths from the current cell by summing the number of paths from the cell to the right (i, j+1) and the cell below (i+1, j).
	-> Store the result in a[i][j] and return it.

*/

class Solution {
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        return dest(dp, m, n, 0, 0);
    }
	
	// 'i' is for row and 'j' is for column
    public int dest(int dp[][], int m, int n, int i, int j) {
        if (i >= m || j >= n)
            return 0;
        
        if (i == m - 1 && j == n - 1)
            return 1;
        
        if (dp[i][j] > 0)
            return dp[i][j];
        
        return dp[i][j] = dest(dp, m, n, i + 1, j) + dest(dp, m, n, i, j + 1);
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity: O(m*n)
The time complexity of this solution is O(m*n) because each cell '(i,j)' in the 'm x n' grid is visited at most once. Memoization ensures that each subproblem is solved only once.

Space complexity: O(m*n)
The space complexity is also O(m*n) due to the storage required for the 'dp' array, which is used to store the number of unique paths to each cell.
Additionally, the recursion stack depth can go up to O(m+n) in the worst case, but this is dominated by the space required for the array.

*/