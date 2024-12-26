class Solution {

    public int trap(int[] height) {

        // The length of the given height array.
        int length = height.length;

        // Arrays to store the maximum height to the left and right of every bar.
        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];

        // Initialize the first element of maxLeft with the first height
        // as there's nothing to the left of it.
        maxLeft[0] = height[0];

        // Initialize the last element of maxRight with the last height
        // as there's nothing to the right of it.
        maxRight[length - 1] = height[length - 1];

        // Populate the maxLeft array by finding the maximum height to the left
        // of the current position, including itself.
        for (int i = 1; i < length; ++i) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }

        // Populate the maxRight array by finding the maximum height to the right
        // of the current position, including itself. This loop runs backwards.
        for (int i = length - 2; i >= 0; --i) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }

        // Variable to store the total amount of trapped water.
        int totalWater = 0;

        // Calculate the trapped water at each position by finding the
        // minimum of maxLeft and maxRight at that position (which is the maximum
        // water level the position can hold) and subtracting the height of the bar.
        for (int i = 0; i < length; ++i) {
            totalWater += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        // Return the total trapped water.
        return totalWater;
    }
}

/*

Time and Space Complexity:
----------------------------
The provided code implements a solution to calculate the amount of water that can be trapped between the bars of different heights represented by the height list.

Time complexity: The time complexity of the solution is O(n) where n is the number of elements in the height list. The reasoning behind this time complexity is as follows:

    a) Creating the left and right lists takes O(n) each as they are initialized based on the first and last element respectively.
    b) Populating the left and right lists with the maximum height encountered so far from the left and right involves a single pass through the height list from left to right and right to left, which again takes O(n) time each.
    c) Finally, the for loop to calculate the trapped water at each position is also linear, as it involves a single pass through the array, for a total of O(n) time.

Overall, as all these steps are sequential and each of them takes O(n) time, the total time complexity is O(n).

Space complexity: The space complexity of the solution is also O(n). This is because additional space is used to store the left and right lists which both have the same length as the input list height. No other significant storage is used that depends on n (the length of the height list), therefore, the space complexity is O(n).

To summarize, the code provided efficiently computes the water trapping problem with a linear time complexity and uses linear space to store interim results.

*/