class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        // Determine the total number of elements in the matrix
        int totalElements = rows * cols;
        // Initialize the answer array with a size equal to the number of elements
        int[][] result = new int[totalElements][2];
        // Starting position is the first element in the result array
        result[0] = new int[] { rStart, cStart };

        // If there's only one element, return the result immediately
        if (totalElements == 1) {
            return result;
        }

        int index = 1; // Start from the second element in the result array

        // Loop indefinitely; the exit condition is when all matrix elements have been
        // added to result
        for (int k = 1;; k += 2) {
            // Directions and step increment: right, down, left, and up
            int[][] directions = new int[][] {
                    { 0, 1, k }, // Move right k steps
                    { 1, 0, k }, // Move down k steps
                    { 0, -1, k + 1 }, // Move left (k+1) steps
                    { -1, 0, k + 1 } // Move up (k+1) steps
            };

            // Iterate through each direction
            for (int[] dir : directions) {
                int rowStep = dir[0], colStep = dir[1], steps = dir[2];

                // Move within the current direction for 'steps' times
                while (steps-- > 0) {
                    // Move to the next cell in the current direction
                    rStart += rowStep;
                    cStart += colStep;

                    // Check if the current cell is within the boundaries of the matrix
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        // Add the current cell to the result
                        result[index++] = new int[] { rStart, cStart };
                        // If we've added all matrix elements to the result, return the result
                        if (index == totalElements) {
                            return result;
                        }
                    }
                }
            }
        }
    }
}

/*

The time and space complexity of the provided solution for generating a spiral matrix can be analyzed as follows:

Time Complexity:
------------------
Outer Loop: The outer loop runs indefinitely until all elements are added to the result. 
Inner Loop: For each direction (right, down, left, up), the inner loop moves through a number of steps that gradually increases. Each complete loop through all four directions visits all N, where N is the total number of elements in the matrix (i.e., N=rows×cols). elements.

Thus, the overall time complexity is O(N), as every cell in the matrix is visited once.

Space Complexity:
------------------
Result Array: The space required for the result array is O(N) because it stores the coordinates of all N elements in the matrix.
Direction Array: The fixed-size direction array (4×3) is negligible in terms of space complexity.

Therefore, the overall space complexity is O(N).

Summary:
----------
Time Complexity: O(N)
Space Complexity: O(N)

*/