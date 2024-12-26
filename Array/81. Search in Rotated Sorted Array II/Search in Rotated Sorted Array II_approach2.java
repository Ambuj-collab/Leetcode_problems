class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return true;

            if (nums[low] == nums[mid]) {
                low++;
                continue;
            }

            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target <= nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            } else {
                if (nums[mid] <= target && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return false;
    }
}

------------------------------------------------------------------------------------
											OR
------------------------------------------------------------------------------------

class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return true;

            if (nums[low] == nums[mid]) {
                low++;
                continue;
            }

            if (nums[low] < nums[mid]) {
                if (nums[low] <= target && target < nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return false;
    }
}

/*

Time and Space Complexity:
------------------------------
Time Complexity: O(logn) for the best case (unique elements). However, in the worst-case scenario (many duplicates), the complexity can degrade to O(n).

Space Complexity: O(1) as we only use a constant amount of space.

*/

------------------------------------------------------------------------------
										OR
------------------------------------------------------------------------------

/*

Intuition:
-------------
The problem involves searching for a target value in a rotated sorted array that may contain duplicates. The key is to handle the rotation and the duplicates efficiently.

Approach:
------------
1) Initialization: Start with low pointing to the beginning of the array and high pointing to the end.
2) Binary Search: Use a modified binary search to handle the rotated sorted array:
	i) Compute the mid index.
	ii) If nums[mid] is the target, return true.
	iii) If nums[low], nums[mid], and nums[high] are equal, increment low and decrement high to skip duplicates.
	iv) Determine which part of the array is sorted:
		a) If the left part is sorted, check if the target lies within this range and adjust the pointers accordingly.
		b) If the right part is sorted, do the same for the right part.
3) Termination: If the loop exits without finding the target, return false.

*/

class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return true;
            }

            // When duplicates are present, we can skip the duplicates
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
            } else if (nums[low] <= nums[mid]) {
                // Left part is sorted
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // Right part is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity: O(n) in the worst case, where all elements are the same except one. In the average case, it's O(log n).
Space complexity: O(1), as no extra space is used.

*/