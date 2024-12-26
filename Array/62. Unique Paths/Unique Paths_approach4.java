class Solution {
    public int uniquePaths(int m, int n) {
        // Create an array to store the number of unique paths to each cell in the bottom row.
        int[] pathCounts = new int[n];
      
        // Initialize the bottom row with 1s since there's only one way to reach each cell in the bottom row
        // when only moving right.
        Arrays.fill(pathCounts, 1);

        // Loop over each cell starting from the second row up to the top row (since the bottom row is already filled).
        for (int row = 1; row < m; ++row) {
            // For each cell in a row, start from the second column since the first column of any row
            // will only have one unique path (i.e., moving down from the cell above).
            for (int col = 1; col < n; ++col) {
                // The number of unique paths to the current cell is the sum of the unique paths to the cell
                // directly above it and to the cell to the left of it.
                pathCounts[col] += pathCounts[col - 1];
            }
        }
      
        // Return the number of unique paths to the top-right corner of the grid.
        return pathCounts[n - 1];
    }
}

/*

Time and Space Complexity:
----------------------------
The time complexity of the provided solution is O(m * n), where m is the number of rows and n is the number of columns. This is because there are two nested loops: the outer loop runs m - 1 times (as the first row's values are all initialized to 1), and the inner loop runs n - 1 times for each iteration of the outer loop, resulting in a total of (m - 1) * (n - 1) iterations of the inner loop's body. However, since we're only interested in big O notation, this simplifies to O(m * n).

The space complexity of the solution is O(n), as it uses a single list f of size n to store intermediary results. This list is updated in-place, and no additional space that is dependent on m or n is used. As a result, the space complexity does not change with m, only with n.

*/