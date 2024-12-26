// Solution 1 - Sorting
class Solution {
    public int firstMissingPositive(int[] nums) {
        int[] filteredNums = Arrays.stream(nums).filter(n -> n > 0).toArray();

        Arrays.sort(filteredNums);

        int target = 1;
        for (int n : filteredNums) {
            if (n == target) {
                target++;
            } else if (n > target) {
                return target;
            }
        }

        return target;
    }
}

/*

Time and Space Complexity:
------------------------------
    Time complexity: O(nlogn)
    Space complexity: O(n)
	
*/

---------------------------------------------------------------------------------------------------
												OR
---------------------------------------------------------------------------------------------------

/*

Bonus
---------
Reduce a loop.

*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);

        int target = 1;
        for (int n : nums) {
            if (n > 0 && n == target) {
                target++;
            } else if (n > target) {
                return target;
            }
        }

        return target;
    }
}

---------------------------------------------------------------------------------------------------
												OR
---------------------------------------------------------------------------------------------------

// Solution 2 - 3 loops
class Solution {
    public int firstMissingPositive(int[] nums) {
        List<Integer> numsList = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                numsList.add(nums[i]);
            }
        }

        for (int i = 0; i < numsList.size(); i++) {
            int n = Math.abs(numsList.get(i));
            if (n <= numsList.size() && numsList.get(n - 1) > 0) {
                numsList.set(n - 1, -numsList.get(n - 1));
            }
        }

        for (int i = 0; i < numsList.size(); i++) {
            if (numsList.get(i) > 0) {
                return i + 1;
            }
        }

        return numsList.size() + 1;
    }
}

/*

Time and Space Complexity:
-----------------------------
    Time complexity: O(n)
    Space complexity: O(n)

*/
