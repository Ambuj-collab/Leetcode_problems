class Solution {

    public int firstMissingPositive(int[] nums) {

        int size = nums.length;

        // Iterate over the array elements.
        for (int i = 0; i < size; ++i) {
            // While the current number is in the range [1, size] and it is not in the correct position
            // (Which means nums[i] does not equal to nums[nums[i] - 1])
            while (nums[i] > 0 && nums[i] <= size && nums[i] != nums[nums[i] - 1]) {
                // Swap nums[i] with nums[nums[i] - 1] 
                // The goal is to place each number in its corresponding index based on its value.
                swap(nums, i, nums[i] - 1);
            }
        }

        // Now that nums is reorganized, loop through the array
        // to find the first missing positive number.
        for (int i = 0; i < size; ++i) {
            // If the number doesn't match its index (+1 because we are looking for positive numbers),
            // that index (+1) is the first missing positive number.
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If no missing number found within [1, size], return size + 1 as the first missing positive number
        return size + 1;
    }

    // Helper method to swap two elements in an array
    private void swap(int[] nums, int firstIndex, int secondIndex) {
        int temp = nums[firstIndex];
        nums[firstIndex] = nums[secondIndex];
        nums[secondIndex] = temp;
    }
}
