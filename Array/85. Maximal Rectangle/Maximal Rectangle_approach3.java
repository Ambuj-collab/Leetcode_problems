class Solution {

    public int maximalRectangle(char[][] matrix) {
        // Get the number of columns in the matrix
        int numColumns = matrix[0].length;

        // Create an array to store the heights of '1's in histogram-like form
        int[] heights = new int[numColumns];

        // Variable to store the maximum area of rectangle
        int maxArea = 0;

        // Iterate over each row of the matrix
        for (char[] row : matrix) {
            // Update the heights array to reflect the current row
            for (int j = 0; j < numColumns; ++j) {	
                // Adjacent '1's in a column are treated as consecutive bar heights in the histogram
                heights[j] = row[j] == '1' ? heights[j] + 1 : 0;
            }
            // Find the largest rectangle in the current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {

        // Variable for the maximum area found
        int maxArea = 0;

        // Stack to keep track of the indices of the heights array
        Deque<Integer> stack = new ArrayDeque<>();

        // Array to keep track of the left boundary of each bar
        int[] left = new int[heights.length];

        // Array to keep track of the right boundary of each bar
        int[] right = new int[heights.length];

        // Initialize all right boundaries to point to the end of the heights array
        Arrays.fill(right, heights.length);

        // Iterate over the heights array to compute the left and right boundaries for each bar
        for (int i = 0; i < heights.length; ++i) {
            // Remove all bars from the stack which are taller than the current bar
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                // Set the right boundary for the bar at the top of the stack
                right[stack.pop()] = i;
            }

            // Set the left boundary for the current bar
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            // Push the current index onto the stack
            stack.push(i);
        }

        // Compute the area based on the heights and boundaries of each bar
        for (int i = 0; i < heights.length; ++i) {
            maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] - 1));
        }

        // Return the maximum area found
        return maxArea;
    }
}

/*

Time and Space Complexity:
============================
The code provided has two parts that contribute to the overall computational complexity: the iteration through the matrix to calculate the height of histograms (maximalRectangle function) and the calculation of the largest rectangle in a histogram (largestRectangleArea function).

Time Complexity:
-------------------
    1) For maximalRectangle: The function iterates through each element in the matrix once. If the matrix size is m * n, where m is the number of rows and n is the number of columns, this part has a time complexity of O(m*n).

    2) For largestRectangleArea within maximalRectangle: For each row, we calculate the largest rectangle area, which involves iterating through the histogram heights twice (once for calculating left limits and once for right limits), both of which are O(n). Then we compute the area for each bar, also in O(n). As this process is repeated for every row m times, the total time complexity for this part is O(m*n).

Thus, the overall time complexity of the code is O(m*n + m*n), which simplifies to O(m*n).

Space Complexity:
--------------------
    1) The space required for the heights, left, right, and stk in largestRectangleArea is n each, which amounts to 4*n. Since n is the size of the largest dimension (number of columns), the space complexity is O(n).

    2) No additional space proportional to the size of the input matrix is required outside the largestRectangleArea function.

Therefore, the total space complexity of the code is O(n).

*/
