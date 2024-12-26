class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(n)
Space complexity: O(1)

*/

------------------------------------------------------------------------------------
											OR
------------------------------------------------------------------------------------

/*

Intuition:
------------
Use two pointers, one for the current element, another for the previous two element.

Approach:
------------
1) Check the length, return length if length is >= 2 because we allow maximum 2 duplicates
2) Start from index 2 because we allow maximum 2 duplicates: current index i=2; checking index j=2;
3) Check if current index element does not equal to the previous two element nums[i] != nums[j-2];
4) Keep nums[i] if nums[i] != nums[j-2] is true: nums[j] = nums[i]; Increase j in order to keep it the same as i
5) j is the valid length

*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int j = 2;
        for (int i = 2; i < n; i++) {
            if (nums[i] != nums[j - 2]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity:O(n)
Space complexity:O(1)

*/

------------------------------------------------------------------------------------
											OR
------------------------------------------------------------------------------------

/*

Intuition:
-------------
The key insight is to compare each element with the element two positions before the current insertion point. This allows us to keep at most two occurrences of each element while maintaining the relative order.

Approach:
------------
1) Handle edge case for arrays with 2 or fewer elements.
2) Use an insertIndex starting at 2 (first two elements are always correct).
3) Iterate through the array from the third element.
4) If the current element is different from the element two positions before insertIndex, it's a new element or valid duplicate.
5) In this case, place the current element at insertIndex and increment insertIndex.
6) Return insertIndex as the new length of the modified array.

Time and Space Complexity:
-----------------------------
Time complexity:
O(n), where n is the length of the input array. We iterate through the array once.

Space complexity:
O(1), as we modify the array in-place and use only a constant amount of extra space.

*/

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        
        int insertIndex = 2; 
        
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[insertIndex - 2]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        
        return insertIndex;
    }
}

------------------------------------------------------------------------------------
											OR
------------------------------------------------------------------------------------

class Solution {
    public int removeDuplicates(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        int k = 0;

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            if (count.get(num) <= 2) {
                nums[k] = num;
                k++;
            }
        }

        return k;
    }
}
