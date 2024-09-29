class Solution {
    public int majorityElement(int[] nums) {
        int res = 0;
        int majority = 0;

        for (int n : nums) {
            if (majority == 0) {
                res = n;
            }

            majority += n == res ? 1 : -1;
        }

        return res;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(logn)
The time complexity of the binary search algorithm is O(logn) where n is the length of the input array nums. The searchRange method calls binary_search twice, so the overall time complexity remains O(logn).

Space complexity: O(1)
The space complexity is O(1) because the algorithm uses a constant amount of extra space, regardless of the size of the input array. There are no data structures or recursive calls that consume additional space proportional to the input size.

*/
