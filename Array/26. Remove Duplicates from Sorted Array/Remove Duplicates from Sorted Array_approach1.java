class Solution {
    // Method to remove duplicates from sorted array
    // and return the length of the array after duplicates have been removed.
    public int removeDuplicates(int[] nums) {
        // Initialize the count for unique elements
        int uniqueCount = 0;

        // Iterate over each element in the array
        for (int currentNum : nums) {
            // If it's the first element or is not equal to the previous element
            // (which means it's not a duplicate)
            if (uniqueCount == 0 || currentNum != nums[uniqueCount - 1]) {
                // Assign the current number to the next unique position in the array
                nums[uniqueCount++] = currentNum;
            }
        }

        // Return the count of unique elements, which is also the new length of the
        // array
        return uniqueCount;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time Complexity:
The given algorithm iterates through each element of the list exactly once. During each iteration, the algorithm performs a constant number of operations: a comparison, an assignment (when necessary), and an increment of the k counter. The time complexity is therefore linear relative to the length of the input list nums. If n represents the number of elements in nums, the time complexity can be expressed as O(n).

Space Complexity:
The algorithm modifies the list nums in place and does not require any additional space that grows with the size of the input, except for the counter variable k. Therefore, the auxiliary space requirement is constant, and the space complexity is O(1).

*/