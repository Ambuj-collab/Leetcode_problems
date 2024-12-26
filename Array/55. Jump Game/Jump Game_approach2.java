class Solution {
    public boolean canJump(int[] nums) {
        int maxReachable = 0; // Initialize the maximum reachable index to 0

        // Iterate over each index in the array
        for (int i = 0; i < nums.length; ++i) {
            // If the current index is greater than the maximum reachable index,
            // it means we cannot proceed further, so return false.
            if (maxReachable < i) {
                return false;
            }

            // Update the maximum reachable index if the reachable index
            // from the current position is greater than the previous max.
            maxReachable = Math.max(maxReachable, i + nums[i]);
        }
      
        // If the loop completes without returning false, it means we can
        // reach the last index, so return true.
        return true;
    }
}

/*

Time and Space Complexity:
-----------------------------
The given Java code aims to determine whether it is possible to jump to the last index of the given list nums. The function canJump() works by iterating through each element in the list, calculating the maximum distance that can be reached from the current position, and checking whether that distance is sufficient to continue progressing through the array.

Time Complexity: The time complexity of the code is O(n), where n is the length of the array nums. This is because the function involves a single loop that goes through the array once, making a constant-time check and update at each step.

Space Complexity: The space complexity of the code is O(1). The algorithm uses a fixed amount of additional space (the variable mx), regardless of the input size, so the space used does not grow with the size of the input array.

*/
