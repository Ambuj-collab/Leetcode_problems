class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // Continue searching while the window is valid
        while (left < right) {
            int mid = left + (right - left) / 2; // Avoid potential overflow of (left + right)

            // If middle element is greater than the rightmost element, the pivot is in the
            // right half
            if (nums[mid] > nums[right]) {
                // If target lies within the left sorted portion
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid; // Narrow down to left half
                } else {
                    left = mid + 1; // Search in the right half
                }
            }

            // If middle element is less than the rightmost element, the left half is sorted
            // properly
            else if (nums[mid] < nums[right]) {
                // If target lies within the right sorted portion
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Narrow down to right half
                } else {
                    right = mid; // Search in the left half
                }
            }

            // If middle element equals the rightmost element, we can't determine the pivot
            // so we reduce the search space by moving the right pointer one step to the
            // left
            else {
                right--;
            }
        }

        // After the loop ends, left == right,
        // checking if we have found the target
        return nums[left] == target;
    }
}

/*

Time and Space Complexity:
----------------------------
Time Complexity
The time complexity of the given algorithm can primarily be considered as O(log n) in the case of a typical binary search scenario without duplicates because the function repeatedly halves the size of the list it's searching. However, in the worst-case scenario where the list contains many duplicates which are all the same as the target, the algorithm degrades to O(n) because the else clause where r -= 1 could potentially be executed for a significant portion of the array before finding the target or determining it's not present.

Space Complexity
The space complexity of the code is O(1) because it uses a fixed number of variables, regardless of the input size. No additional data structures are used that would depend on the size of the input array.

*/

-------------------------------------------------------------------------
									OR
-------------------------------------------------------------------------

class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[lo] < nums[mid]) {
                if (target >= nums[lo] && target < nums[mid])
                    hi = mid - 1;
                else
                    lo = mid + 1;
            } else if (nums[lo] > nums[mid]) {
                if (target > nums[mid] && target <= nums[hi])
                    lo = mid + 1;
                else
                    hi = mid - 1;
            } else { // skip the duplicate and move 'lo' up
                lo++;
            }
        }

        return false;
    }
}
