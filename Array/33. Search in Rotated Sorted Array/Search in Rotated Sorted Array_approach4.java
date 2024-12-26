class Solution {

    public int search(int[] nums, int target) {
        // Length of the array.
        int arrayLength = nums.length;

        // Initialize start and end pointers.
        int start = 0, end = arrayLength - 1;

        // Binary search algorithm to find target.
        while (start < end) {
            // Calculate middle index of the current segment.
            int mid = (start + end) / 2;

            // When middle element is on the non-rotated portion of the array.
            if (nums[0] <= nums[mid]) {

                // Check if the target is also on the non-rotated portion and adjust end
                // accordingly.
                if (nums[0] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }

                // When middle element is on the rotated portion of the array.
            } else {

                // Check if the target is also on the rotated portion and adjust start
                // accordingly.
                if (nums[mid] < target && target <= nums[arrayLength - 1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }

        // After narrowing down to one element, check if it's the target.
        // If nums[start] is the target, return its index, otherwise return -1.
        return nums[start] == target ? start : -1;
    }

}

/*

Time and Space Complexity:
-----------------------------
Time Complexity
The given code performs a binary search over an array. In each iteration of the while loop, the algorithm splits the array into half, investigating either the left or the right side. Since the size of the searchable section of the array is halved with each iteration of the loop, the time complexity of this operation is O(log n), where n is the number of elements in the array nums.

Space Complexity
The space complexity of the algorithm is O(1). The search is conducted in place, with only a constant amount of additional space needed for variables left, right, mid, and n, regardless of the size of the input array nums.

*/