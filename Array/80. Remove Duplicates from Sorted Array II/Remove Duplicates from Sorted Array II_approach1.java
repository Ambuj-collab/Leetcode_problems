class Solution {
    public int removeDuplicates(int[] nums) {
        // 'k' is the index for placing the next unique element
        // or the second occurrence of an existing element
        int index = 0;

        // Iterate over each element in the array
        for (int num : nums) {
            // If the current position is less than 2 (i.e., we are at the start of the
            // array)
            // or if the current element is different than the element two positions behind
            // then consider it for inclusion in the array
            if (index < 2 || num != nums[index - 2]) {
                // Place the current element at the 'index' position and increment 'index'
                nums[index] = num;
                index++;
            }
        }

        // The 'index' represents the length of the array without duplicates
        // allowing up to two occurrences
        return index;
    }
}

/*

Time and Space Complexity:
------------------------------
Time Complexity
The time complexity of the code is O(n), where n is the number of elements in the input list nums. This is because the code consists of a single loop that goes through all elements of the list exactly once.

Space Complexity
The space complexity of the code is O(1). No additional space is required that is dependent on the input size. The variable k is used to keep track of the position in the array while overwriting duplicates, but this does not scale with the size of the input.

*/