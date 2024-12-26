class Solution {

    public int largestRectangleArea(int[] heights) {
        // Initialize variables
        int maxRectangleArea = 0; // To store the maximum area
        int[] leftSmaller = new int[heights.length]; // Nearest smaller bar to the left
        int[] rightSmaller = new int[heights.length]; // Nearest smaller bar to the right

        Stack<Integer> indexStack = new Stack<>(); // Stack to store indices

        // Step 1: Calculate leftSmaller array
        for (int i = 0; i < heights.length; i++) {
            while (!indexStack.isEmpty() && heights[indexStack.peek()] >= heights[i]) {
                indexStack.pop(); // Remove larger bars
            }
            // If stack is empty, no smaller bar exists to the left
            leftSmaller[i] = indexStack.isEmpty() ? -1 : indexStack.peek();
            indexStack.push(i); // Push current bar index onto the stack
        }

        // Clear stack for the next calculation
        indexStack.clear();

        // Step 2: Calculate rightSmaller array
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!indexStack.isEmpty() && heights[indexStack.peek()] >= heights[i]) {
                indexStack.pop(); // Remove larger bars
            }
            // If stack is empty, no smaller bar exists to the right
            rightSmaller[i] = indexStack.isEmpty() ? heights.length : indexStack.peek();
            indexStack.push(i); // Push current bar index onto the stack
        }

        // Step 3: Calculate maximum rectangle area
        for (int i = 0; i < heights.length; i++) {
            // Width is determined by the distance between right and left smaller indices
            int width = rightSmaller[i] - leftSmaller[i] - 1;
            int area = heights[i] * width; // Calculate area with current height
            maxRectangleArea = Math.max(maxRectangleArea, area); // Update maximum area
        }

        return maxRectangleArea; // Return the largest rectangle area
    }
}
