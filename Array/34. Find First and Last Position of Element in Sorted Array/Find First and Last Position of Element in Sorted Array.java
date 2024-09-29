class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);
        result[0] = left;
        result[1] = right;
        return result;        
    }

    private int binarySearch(int[] nums, int target, boolean isSearchingLeft) {
        int left = 0;
        int right = nums.length - 1;
        int idx = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                idx = mid;
                if (isSearchingLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return idx;
    }

}

/*

Complexity
Time complexity: O(logn)
The time complexity of the binary search algorithm is O(logn) where n is the length of the input array nums. The searchRange method calls binary_search twice, so the overall time complexity remains O(logn).

Space complexity: O(1)
The space complexity is O(1) because the algorithm uses a constant amount of extra space, regardless of the size of the input array. There are no data structures or recursive calls that consume additional space proportional to the input size.

*/
