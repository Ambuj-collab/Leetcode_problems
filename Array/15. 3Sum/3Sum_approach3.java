// Fastest solution

/*

Intuition:
------------
To find all unique triplets in an array that sum to zero, we can leverage sorting and a two-pointer technique. Sorting helps in efficiently finding triplets and avoiding duplicates.

Approach:
------------
1) Sort the Array:
Sort the input array nums to facilitate the two-pointer technique and simplify duplicate handling.

2) Iterate Through the Array:
	a) Use a loop to fix the first element of the triplet, ival. For each ival, set up two pointers, left and right, to find the remaining two elements.
	b) Skip duplicate elements to ensure uniqueness of triplets.

3) Two-Pointer Technique:
	a) For each ival, initialize left to i + 1 and right to nums.length - 1.
	b) Calculate the sum of ival, nums[left], and nums[right].
	c) If the sum is zero, add the triplet to the result list.
	d) If the sum is less than zero, increment left to increase the sum.
	e) If the sum is greater than zero, decrement right to decrease the sum.
	f) Skip duplicate elements for left to avoid duplicate triplets.

4) Return Result:
Return the list of unique triplets that sum to zero.

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> sums = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int ival = nums[i];

            if (ival > 0)
                break;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + ival;

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    sums.add(Arrays.asList(ival, nums[left], nums[right]));

                    left++;

                    while (left < right && nums[left] == nums[left - 1])
                        left++;

                }

            }

        }

        return sums;
    }
}

/*
Time and Space Complexity:
-----------------------------

Time complexity:
The time complexity is O(n^2), where n is the length of the input array. Sorting the array takes O(nlogn) time, and the two-pointer approach takes O(n^2) time in the worst case.

Space complexity:
The space complexity is O(1) for the two-pointer approach, excluding the space required for the output list.
*/