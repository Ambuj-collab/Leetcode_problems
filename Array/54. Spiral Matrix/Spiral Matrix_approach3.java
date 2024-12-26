/*

Intuition:
------------
To solve the problem of traversing a matrix in spiral order, you need to manage four boundaries: top, bottom, left, and right. These boundaries help in determining the portion of the matrix that needs to be processed in each step. The idea is to progressively shrink these boundaries as you traverse the matrix in a spiral fashion.

Approach:
------------
Initialize Boundaries: Define boundaries for the rows and columns that control the limits of the current layer of the spiral order. Initialize them as follows:
	top: starting row index (0)
	bottom: ending row index (number of rows - 1)
	left: starting column index (0)
	right: ending column index (number of columns - 1)

Iterate While Boundaries Are Valid: Use a while loop to traverse the matrix while the top boundary is less than or equal to the bottom boundary and the left boundary is less than or equal to the right boundary.

Traverse Right: Add elements from the top row, moving from left to right. After completing this traversal, increment the top boundary to move inward.

Traverse Down: Add elements from the right column, moving from top to bottom. After this, decrement the right boundary to narrow the column space.

Traverse Left: Add elements from the bottom row, moving from right to left. After this, decrement the bottom boundary to move upward.

Traverse Up: Add elements from the left column, moving from bottom to top. After this, increment the left boundary to expand the column space.

Return the List: Once all elements are added to the list in spiral order, return the list.

*/

import java.util.List;
import java.util.ArrayList;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> list = new ArrayList<>();

        // case for empty matrix
        if (row == 0 || col == 0) {
            return list;
        }

        // set boundary for the limits of movement
        int left = 0, right = col - 1, top = 0, bottom = row - 1;

        // now this we will set at actual condition
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++; // to make another row as top

            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            right--; // we are narrowing our adding-element space
			
			// this 'if' statement is used because matrix is not square(i.e., 'row' is now equal to 'col')
            if (top <= bottom) { 
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
            }
			
			// this 'if' statement is used because matrix is not square(i.e., 'row' is now equal to 'col')
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
            }
        }
        return list;
    }
}

----------------------------------------------------------------------------------------------
												OR
----------------------------------------------------------------------------------------------

/*

Intuition:
-------------
The goal is to traverse a matrix in a spiral order. The key to solving this problem is to keep track of the boundaries (top, bottom, left, right) and systematically move through the matrix layer by layer.

Approach:
------------
1) Used four pointers (top, bottom, left, right) to keep track of the boundaries of the matrix as we traverse it in a spiral order.
2) The process:
	a) First, traverse from left to right along the top row, then move the top boundary down.
	b) Next, traverse from top to bottom along the right column, then move the right boundary left.
	c) Then, if there are rows left, traverse from right to left along the bottom row, and move the bottom boundary up.
	d) Finally, if there are columns left, traverse from bottom to top along the left column, and move the left boundary right.
3) Continued this process until all rows and columns were processed.
4) Stored the elements in a list and returned it at the end.

*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Initialize a list to store the elements in spiral order
        List<Integer> elelist = new ArrayList<Integer>();

        // Define four pointers to track the boundaries of the matrix
        int top = 0, bottom = matrix.length - 1; // 'top' tracks the top boundary, 'bottom' tracks the bottom boundary
        int right = matrix[0].length - 1, left = 0; // 'right' tracks the right boundary, 'left' tracks the left boundary

        // Continue the loop until all rows and columns are processed
        while (top <= bottom && left <= right) {
            // Traverse from left to right along the current top row
            for (int j = left; j <= right; j++) {
                elelist.add(matrix[top][j]);
            }
            // Move the top boundary down after processing the top row
            top++;

            // Traverse from top to bottom along the current right column
            for (int i = top; i <= bottom; i++) {
                elelist.add(matrix[i][right]);
            }
            // Move the right boundary left after processing the right column
            right--;

            // Check if there are rows remaining after adjusting the top and right boundaries
            if (top <= bottom) {
                // Traverse from right to left along the current bottom row
                for (int j = right; j >= left; j--) {
                    elelist.add(matrix[bottom][j]);
                }
                // Move the bottom boundary up after processing the bottom row
                bottom--;
            }

            // Check if there are columns remaining after adjusting the left and bottom boundaries
            if (left <= right) {
                // Traverse from bottom to top along the current left column
                for (int i = bottom; i >= top; i--) {
                    elelist.add(matrix[i][left]);
                }
                // Move the left boundary right after processing the left column
                left++;
            }
        }

        // Return the list containing all the elements in spiral order
        return elelist;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(m * n) , where m is the number of rows and n is the number of columns in the matrix. This is because each element is visited once.

Space complexity:O(1) for additional space, except for the output list, which requires O(m * n) space to store the result.

*/