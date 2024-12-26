class Solution {

    // Method to sort the array containing 0s, 1s, and 2s
    public void sortColors(int[] nums) {
        // Initialize pointers for the current element (currIndex),
        // the last position of 0 (lastZeroIndex) and the first position of 2
        // (firstTwoIndex)
        int lastZeroIndex = -1;
        int firstTwoIndex = nums.length;
        int currIndex = 0;

        // Process elements until currIndex reaches firstTwoIndex
        while (currIndex < firstTwoIndex) {
            if (nums[currIndex] == 0) {
                // If the current element is 0, swap it to the position after the last 0 we
                // found
                swap(nums, ++lastZeroIndex, currIndex++);
            } else if (nums[currIndex] == 2) {
                // If the current element is 2, swap it with the element at the position
                // just before the first 2 we found
                swap(nums, --firstTwoIndex, currIndex);
            } else {
                // If the current element is 1, just move to the next element
                ++currIndex;
            }
        }
    }

    // Helper method to swap two elements in an array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
