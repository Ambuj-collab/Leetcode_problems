/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * Solution class that contains the method to convert a linked list to a spiral
 * matrix.
 */
class Solution {
    /**
     * Fills a matrix of size m * n with the values from a linked list in spiral
     * order.
     *
     * @param m    The number of rows of the matrix.
     * @param n    The number of columns of the matrix.
     * @param head The head of the linked list.
     * @return A 2D integer array representing the filled spiral matrix.
     */
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        // Initialize the matrix with the desired dimensions
        int[][] resultMatrix = new int[m][n];

        // Fill the matrix with -1 to indicate unfilled cells
        for (int[] row : resultMatrix) {
            Arrays.fill(row, -1);
        }

        // Initialize row and column indices to start from the top left corner
        int row = 0, column = 0;

        // Initialize the direction index
        int directionIndex = 0;

        // Define the directions for right, down, left, and up in a 2D array
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        // Continue to fill the matrix until the linked list is exhausted
        while (head != null) {
            // Fill the current cell with the linked list's node value
            resultMatrix[row][column] = head.val;

            // Move to the next node in the linked list
            head = head.next;

            // Determine the new coordinates based on the current direction
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];

            // Check for boundary conditions and if the next cell is already filled
            if (nextRow < 0 || nextColumn < 0 || nextRow >= m ||
                    nextColumn >= n || resultMatrix[nextRow][nextColumn] >= 0) {
                // Change the direction if we hit a boundary or a filled cell
                directionIndex = (directionIndex + 1) % 4;
                
                nextRow = row + directions[directionIndex][0];
                nextColumn = column + directions[directionIndex][1];
            }
            // Update the row and column indices if the next cell is valid
            row = nextRow;
            column = nextColumn;
        }

        // Return the filled spiral matrix
        return resultMatrix;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time Complexity:
The time complexity of the given code is O(m * n), where m is the number of rows and n is the number of columns in the matrix. This is because the code iterates over each cell of the m x n matrix exactly once to fill it with the values from the linked list. Note that while there are nested loops, the inner loop only serves to change the direction when necessary and doesn't iterate over the matrix again, as the outer loop breaks as soon as the linked list ends.

Space Complexity:
The space complexity of the code is O(m * n) due to the resultMatrix matrix that is created with m rows and n columns, each cell initialized to -1. No other additional significant space is used, except for constant extra space for variables row, column, directionIndex, and directions. The linked list itself is not counted towards space complexity as it's given as part of the input. Therefore, the space consumed by the resultMatrix matrix is the dominant factor.

*/