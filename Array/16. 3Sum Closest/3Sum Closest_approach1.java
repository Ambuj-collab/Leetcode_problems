/*

Approach 1: Sorting and Two Pointers
	1) Sort the array.
	2) Iterate through the array and use a two-pointer technique for each element to find the closest sum.
	3) Track the closest sum encountered.

*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest_sum = Integer.MAX_VALUE / 2; // A large value but not overflow

        for (int i = 0; i < nums.length - 2; ++i) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int current_sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(current_sum - target) < Math.abs(closest_sum - target)) {
                    closest_sum = current_sum;
                }
                if (current_sum < target) {
                    ++left;
                } else if (current_sum > target) {
                    --right;
                } else {
                    return current_sum;
                }
            }
        }

        return closest_sum;
    }
}


-------------------------------------------------------------------------------------------------------
												OR
-------------------------------------------------------------------------------------------------------

/*

Approach 2: Brute Force
-----------------------
1) Iterate through all possible triplets in the array.
2) Calculate the sum for each triplet.
3) Track the closest sum to the target.

*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int closest_sum = Integer.MAX_VALUE / 2;  // A large value but not overflow
        
        for (int i = 0; i < nums.length - 2; ++i) {
            for (int j = i + 1; j < nums.length - 1; ++j) {
                for (int k = j + 1; k < nums.length; ++k) {
                    int current_sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(current_sum - target) < Math.abs(closest_sum - target)) {
                        closest_sum = current_sum;
                    }
                }
            }
        }
        
        return closest_sum;
    }
}


/* 

Explanation:
--------------

Approach 1:
	a) Sorting: Helps to efficiently find the closest sum using two pointers.
	b) Two Pointers: Allows narrowing down the search space for each element efficiently.

Time Complexity: O(n^2) due to the nested loops after sorting.
Space Complexity: O(1) as it only requires a few extra variables.

Approach 2:
	a) Brute Force: Checks all possible combinations of triplets.

Time Complexity: O(n^3) due to the triple nested loops.
Space Complexity: O(1) as it only requires a few extra variables.

Both approaches will provide the same correct result, but Approach 1 is significantly more efficient.

*/
