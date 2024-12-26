// Fastest solution

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[][] spiralMatrix(int rows, int columns, ListNode head) {
        int[][] matrix = new int[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = new int[columns];
            Arrays.fill(matrix[i], -1);
        }

        int topRow = 0, bottomRow = rows - 1, leftColumn = 0, rightColumn = columns - 1;
        while (head != null) {

            for (int col = leftColumn; col <= rightColumn && head != null; col++) {
                matrix[topRow][col] = head.val;
                head = head.next;
            }
            topRow++;

            for (int row = topRow; row <= bottomRow && head != null; row++) {
                matrix[row][rightColumn] = head.val;
                head = head.next;
            }
            rightColumn--;

            for (int col = rightColumn; col >= leftColumn && head != null; col--) {
                matrix[bottomRow][col] = head.val;
                head = head.next;
            }
            bottomRow--;

            for (int row = bottomRow; row >= topRow && head != null; row--) {
                matrix[row][leftColumn] = head.val;
                head = head.next;
            }
            leftColumn++;
        }

        return matrix;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity:
The time complexity is O(m x n). This is because every cell in the matrix is processed exactly once, either by filling it with a value from the linked list or leaving it as -1 if the linked list runs out of values.

Space complexity:
The space complexity is also O(m x n). This includes the space required for the output matrix, which is of size m x n.

where m = number of rows, n = number of columns

*/

------------------------------------------------------------------------------
										OR
------------------------------------------------------------------------------

/*

Intuition:
------------
The problem requires filling a matrix in spiral order using values from a linked list. My first thought was to use a method to traverse the matrix in a spiral fashion (starting from the top-left corner and moving clockwise). Since we are dealing with boundaries, I can manage the traversal using four pointers (top, bottom, left, right) and reduce these boundaries as I fill the matrix.

Approach:
-----------
1) Start by initializing the m x n matrix with all values set to -1, which will serve as a placeholder for any unfilled spaces.
2) Use four pointers (top, bottom, left, right) to define the current boundaries of the matrix. These will shrink as we move inward in a spiral pattern.
3) Traverse the matrix in four steps:
	a) Move left to right along the top boundary.
	b) Move top to bottom along the right boundary.
	c) Move right to left along the bottom boundary.
	d) Move bottom to top along the left boundary.
4) After each traversal, update the respective boundary pointers to move closer to the center of the matrix.
5) As the values from the linked list are inserted into the matrix, advance the pointer to the next node.
6) If the linked list is fully processed before the matrix is completely filled, leave the remaining cells as -1.
7) Return the filled matrix.

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        ListNode curr = head;
        int[][] ans = new int[m][n];

        // Initialize the matrix with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = -1;
            }
        }

        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        // Process until the linked list is exhausted or the matrix bounds are exceeded
        while (curr != null && top <= bottom && left <= right) {

            // Fill top row (left to right)
            for (int i = left; i <= right && curr != null; i++) {
                ans[top][i] = curr.val;
                curr = curr.next;
            }
            top++;

            // Fill right column (top to bottom)
            for (int i = top; i <= bottom && curr != null; i++) {
                ans[i][right] = curr.val;
                curr = curr.next;
            }
            right--;

            // Fill bottom row (right to left)
            for (int i = right; i >= left && curr != null; i--) {
                ans[bottom][i] = curr.val;
                curr = curr.next;
            }
            bottom--;

            // Fill left column (bottom to top)
            for (int i = bottom; i >= top && curr != null; i--) {
                ans[i][left] = curr.val;
                curr = curr.next;
            }
            left++;
        }
        return ans;
    }
}

/*

Complexity
Time complexity:
The time complexity is O(m × n), where m is the number of rows and n is the number of columns in the matrix. This is because you are traversing each cell of the m x n matrix exactly once to fill it with values from the linked list or -1 when the list is exhausted. Each traversal step takes constant time, so the overall complexity is linear in the number of cells.

Space complexity:
The space complexity is O(m × n) as well, because you need to allocate a 2D matrix of size m x n to store the result. Besides the matrix, no extra space proportional to the input size is used (the linked list is given as input and not counted toward space complexity).

*/