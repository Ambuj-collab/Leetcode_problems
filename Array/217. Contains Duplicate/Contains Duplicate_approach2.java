/*

Approach 2 -> Set
In the second solution, we use Set. Every time we find a new number, we will check set. And if we have the same number in set, we should return True.

[1,2,3,1]
set = {}
[1,2,3,1]
 ↑

There is not 1 in set. Add 1 to set.
set = {1}
[1,2,3,1]
   ↑

There is not 2 in set. Add 2 to set.
set = {1,2}
[1,2,3,1]
     ↑

There is not 3 in set. Add 3 to set.
set = {1,2,3}
[1,2,3,1]
       ↑

There is 1 in set.
set = {1,2,3}
return True


Time and Space Complexity:
------------------------------
Time complexity: O(n)
Space complexity: O(n)
In the worst case, numbers are all unique.

*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for (int n : nums) {
            if (numSet.contains(n)) {
                return true;
            }
            numSet.add(n);
        }
        
        return false;        
    }
}
