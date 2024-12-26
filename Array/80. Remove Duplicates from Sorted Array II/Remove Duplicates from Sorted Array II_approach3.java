/*

Approach:
------------
The solution approach involves iterating through the input array nums and maintaining two pointers, index and occurrence. The index pointer represents the length of the modified array, and the occurrence pointer tracks the number of occurrences of the current element.

1) Initialize index to 1 (for the first element) and occurrence to 1.
2) Iterate through the array, starting from index 1.
3) If the current element is equal to the previous one, increment occurrence.
4) If not, reset occurrence to 1.
5) If occurrence is less than or equal to 2, update nums[index] with the current element and increment index.
6) Continue until the end of the array.

*/

class Solution {
    public int removeDuplicates(int[] nums) {

        int index = 1;
        int occurance = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                occurance++;
            } else {
                occurance = 1;
            }

            if (occurance <= 2) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;

    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(n)
Space complexity: O(1)

*/