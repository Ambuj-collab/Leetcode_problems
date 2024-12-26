/*

The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can reach. Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps, as the following:

*/

class Solution {
    public int jump(int[] nums) {
        int jumps = 0, currEnd = 0, currFarthest = 0;
        for (int curBegin = 0; curBegin < nums.length - 1; curBegin++) {
            currFarthest = Math.max(currFarthest, curBegin + nums[curBegin]);
            if (curBegin == currEnd) {
                currEnd = currFarthest;
                jumps++;
            }
        }
        return jumps;
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity: O(N)
Space complexity: O(1)

*/