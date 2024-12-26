/*

Intuition:
-------------
We count zeros and ones in the array and overwrite the original array with 0s and then with 1s and the remaining is overwritten as 2s.

Approach:
-----------
1) Count the occurrences of 0s and 1s, denoted as zeros and ones.
2) Iterate from the 0th index up to zeros, setting each element to 0.
3) Iterate from the zeros index up to zeros + ones, assigning each element to 1.
4) The remaining elements, starting from zeros + ones index, are necessarily 2s, so iterate from this index up to the end of the array, setting each element to 2.

Time and Space Complexity:
------------------------------
Time complexity: O(n)
Space complexity: O(1)

*/

class Solution {
    public void sortColors(int[] nums) {
        int zeros = 0, ones = 0, n = nums.length;
        for (int num : nums) {
            if (num == 0)
                zeros++;
            else if (num == 1)
                ones++;
        }

        for (int i = 0; i < zeros; ++i) {
            nums[i] = 0;
        }

        for (int i = zeros; i < zeros + ones; ++i) {
            nums[i] = 1;
        }

        for (int i = zeros + ones; i < n; ++i) {
            nums[i] = 2;
        }
    }
}
