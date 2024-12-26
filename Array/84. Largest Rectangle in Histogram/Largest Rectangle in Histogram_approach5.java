class Solution {

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0; // This variable will store the maximum area found.
        int length = heights.length; // Total number of bars.

        // Stack to keep track of indices of the bars.
        Deque<Integer> stack = new ArrayDeque<>();

        // Arrays to keep track of the left and right boundaries of each bar.
        int[] leftBoundary = new int[length];
        int[] rightBoundary = new int[length];

        // Initialize right boundaries as the length of the array.
        Arrays.fill(rightBoundary, length);

        // Iterate over all bars to calculate left and right boundaries.
        for (int i = 0; i < length; ++i) {
            // Pop elements from the stack until the current bar is taller than the stack's
            // top and set their right boundary to the current bar's index.
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                rightBoundary[stack.pop()] = i;
            }

            // If the stack is empty, then there's no smaller bar to the left.
            // Otherwise, the stack's top is the previous smaller bar's index.
            leftBoundary[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push the current index onto the stack.
            stack.push(i);

        }

        // Calculate the largest rectangle area for each bar using their boundaries.
        for (int i = 0; i < length; ++i) {
            // Calculate width of the current bar's largest rectangle.
            int width = rightBoundary[i] - leftBoundary[i] - 1;

            // Calculate area and update maxArea if it's larger.
            maxArea = Math.max(maxArea, heights[i] * width);
        }

        // Return the maximum area found.
        return maxArea;
    }
}

Time and Space Complexity:
----------------------------
The time complexity of the largestRectangleArea function can be analyzed by looking at the operations performed inside the function.

    a) The function initializes three lists named left, right, and stk, and also iterates over the input heights list twice (once for populating the left and right lists, and once for calculating the maximum area). Each element of the heights list is processed exactly once during these iterations, leading to O(n) time for each loop.

    b) The stack stk is used to keep track of the indices of the rectangles in ascending order of their heights. For each element in heights, the stack may perform a push operation (stk.append(i)). Additionally, while the current height is less than or equal to the height of the rectangle corresponding to the index at the top of the stack, pop operations occur, and the right bound for the rectangle is updated. Despite this, each element is added to the stack once and removed from the stack at most once over the entire run of the loop, leading to a total of O(n) operations.

Based on these points, the overall time complexity of the largestRectangleArea function is O(n).

As for the space complexity:

    The extra space is used for the stk, left, and right lists, each of size n, where n is the number of elements in the input heights list. Thus, the space complexity is O(n), correlating with the size of the input.
