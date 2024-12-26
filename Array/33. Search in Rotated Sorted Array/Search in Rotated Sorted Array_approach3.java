/*

Intuition:
------------
The intuition behind this solution is to leverage the properties of a rotated sorted array while performing a binary search. At each step, we determine if the left or right half of the array is sorted. If the left half is sorted, we check if the target lies within that range; otherwise, we search in the right half, which must be sorted. This ensures that with each iteration, we discard half of the array, making the search efficient. By continuously narrowing down the search space, we either find the target or determine that it isn't in the array.

*/

/*

Approach 1 : Using Linear Search in array
-------------------------------------------

Time and Space Complexity:
----------------------------
Time complexity:
We are linearly iterating over all elements in input array.
Hence Overall Time Complexity : O(n).

Space complexity:
We are not using any extra data structures.
Hence Overall Space Complexity : O(constant) --> O(1).

*/

class Solution {
    public int search(int[] nums, int target) {
        int idx = 0;

        for (int num : nums) {
            if (num == target)
                return idx;
            idx++;
        }

        return -1;
    }
}

-------------------------------------------------------------------
								OR
-------------------------------------------------------------------

/*

Approach 2 : Using Binary Search in array
--------------------------------------------

Time and Space Complexity:
-----------------------------
Time complexity:
We are using Binary Search to search in input array.
Hence Overall Time Complexity : O(nlogn).

Space complexity:
We are not using any extra data structures.
Hence Overall Space Complexity : O(constant) --> O(1).

*/

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }
}
