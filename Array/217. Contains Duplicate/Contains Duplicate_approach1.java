/*

Approach 1 -> Sorting
Simply, if we sort all numbers, we can solve this question easily, because all duplicate numbers will be adjacent numbers.

[1,2,3,1]
↓
[1,1,2,3]
Then we iterate through the input array from index 1 and check previous index every time.

⭐️ Points

We are at index 1, compare index 1 and index 0.
We are at index 2, compare index 2 and index 1.
We are at index 3, compare index 3 and index 2.


Time and Space Complexity:
-----------------------------
Time complexity: O(nlogn)
Space complexity: O(n)
It depends on language you use.

*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        
        return false;        
    }
}
