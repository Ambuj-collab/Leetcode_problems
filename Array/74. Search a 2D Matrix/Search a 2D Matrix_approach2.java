class Solution {

    // Method to search for a target value in a matrix.
    public boolean searchMatrix(int[][] matrix, int target) {
        // Get the number of rows and columns from the matrix.
        int rows = matrix.length, cols = matrix[0].length;

        // Initialize the left and right pointers for the binary search.
        int left = 0, right = rows * cols - 1;

        // Loop until the search space is reduced to one element.
        while (left <= right) {
            // Calculate the middle point of the current search space.
            int mid = (left + right) / 2; // Shift operator can also be used (left + right) >> 1

            // Map the middle index to a 2D position in the matrix.
            int x = mid / cols, y = mid % cols;

            // Compare the middle element with the target.
            if (matrix[x][y] == target) {
                // If the middle element is equal to the target, then return true
                return true;
            } else if (matrix[x][y] < target) {
                // If the middle element is less than the target,
                // narrow the search to the right half excluding mid.
                left = mid + 1;
            } else {
				// If the middle element is greater than the target,
                // narrow the search to the left half excluding mid.
                right = mid - 1;
            }
        }

        // If we reach here, that means target is not there in the matrix
        return false;
    }
}

---------------------------------------------------------------------------------
										   OR
---------------------------------------------------------------------------------

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int guess = matrix[row][col];

            if (guess == target) {
                return true;
            } else if (guess < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
