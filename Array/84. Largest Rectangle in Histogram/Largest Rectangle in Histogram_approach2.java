// This solution uses monotonic stack.
class Solution {

    public int largestRectangleArea(int[] heights) {
        int maxRectangleArea = 0; // To track the maximum rectangle area

        Stack<Integer> indexStack = new Stack<>(); // Stack to store indices of bars

        // Loop through all bars, including an extra iteration for edge case
        for (int currentIndex = 0; currentIndex <= heights.length; currentIndex++) {
            // When stack is not empty and current bar is smaller than top of the stack
            while (!indexStack.isEmpty() && 
                  (currentIndex == heights.length || heights[indexStack.peek()] >= heights[currentIndex])) {

                // Calculate height of the rectangle
                int height = heights[indexStack.pop()];

                // Calculate width of the rectangle
                int width;
                if (indexStack.isEmpty()) {
                    // If stack is empty, width spans from 0 to the current index
                    width = currentIndex;
                } else {
                    // If stack is not empty, width is between current index and the new top of stack
                    width = currentIndex - indexStack.peek() - 1;
                }

                // Update the maximum area
                maxRectangleArea = Math.max(maxRectangleArea, width * height);
            }

            // Push the current index onto the stack
            indexStack.push(currentIndex);
        }

        return maxRectangleArea; // Return the largest rectangle area
    }
}

/*

Time Complexity:
--------------------
    Time Complexity: O(n)
        Each bar is pushed onto and popped from the stack once.
    Space Complexity: O(n)
        For the stack, which stores the indices.

*/