/*

Approach:
-----------
a) Initialize an empty matrix of size n x n, where n is the given positive integer.
b) Initialize a variable cnt to keep track of the values to be filled in the matrix, starting from 1.
c) Initialize four variables top=0, bottom=n-1, left=0, and right=n-1 to represent the boundaries of the current spiral.
d) Fill the elements of the matrix while adjusting the boundaries of the spiral with the increase of the cnt variable.

*/

class Solution {
    public int[][] generateMatrix(int n) {
        // Create matrix of size n*n
        int[][] mat = new int[n][n];

        // Initialize variables for the boundaries of the matrix and the counter
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int cnt = 1;

        // Loop until all elements are filled
        while (top <= bottom && left <= right) {
            // Fill the top row from left to right
            for (int i = left; i <= right; i++) {
                mat[top][i] = cnt++;
            }
            top++;

            // Fill the rightmost column from top to bottom
            for (int i = top; i <= bottom; i++) {
                mat[i][right] = cnt++;
            }
            right--;

            // Fill the bottom row from right to left
            for (int i = right; i >= left; i--) {
                mat[bottom][i] = cnt++;
            }
            bottom--;

            // Fill the leftmost column from bottom to top
            for (int i = bottom; i >= top; i--) {
                mat[i][left] = cnt++;
            }
            left++;
        }

        return mat;
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity:O(n * n)
Space complexity:O(n * n)

*/