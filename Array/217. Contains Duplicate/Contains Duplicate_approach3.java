/*

Solution 3 - Check length
I have one more idea.

If the length of the input array created by executing set is shorter than the original input array, it indicates that we have duplicate numbers.

*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        return numSet.size() < nums.length;
    }
}
