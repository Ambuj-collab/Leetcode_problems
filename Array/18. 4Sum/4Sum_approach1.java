/*

Intuition of this Problem:
-----------------------------
Set is used to prevent duplicate quadruplets and parallely we will use two pointer approach to maintain k and l.

Approach for this Problem:
-----------------------------
1) Sort the input array of integers nums.
2) Initialize an empty set s, and an empty 2D List(i.e., list of list) output.
3) Use nested loops to iterate through all possible combinations of quadruplets in nums.
4) For each combination, use two pointers (k and l) to traverse the sub-array between the second and second-to-last elements of the combination.
5) At each iteration of the innermost while loop, calculate the sum of the current quadruplet and check if it is equal to the target.
6) If the sum is equal to the target, insert the quadruplet into the set s and increment both pointers (k and l).
7) If the sum is less than the target, increment the pointer k.
8) If the sum is greater than the target, decrement the pointer l.
9) After all quadruplets have been checked, iterate through the set s and add each quadruplet to the output list.
10) Return the output list.

*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> s = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if (sum == target) {
                        s.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        output.addAll(s);
        return output;
    }
}

--------------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------------

import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> resultSet = new HashSet<>(); 
        
        Arrays.sort(nums); 
        
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                
                while (left < right) {
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> arr = new ArrayList<>();
                        arr.add(nums[i]);
                        arr.add(nums[j]);
                        arr.add(nums[left]);
                        arr.add(nums[right]);
                        
                        if (!resultSet.contains(arr)) {
                            resultSet.add(arr); 
                        }
                        
                        left++;
                        right--;
                    } else if (sum <= target) {
                        left++;
                    } else if(sum > target){
                        right--;
                    }
                }
            }
        }
        
        result.addAll(resultSet); 
        
        return result;
    }
}

/*

Time and Space Complexity:
-----------------------------------------
Time complexity: O(n^3) // where n is the size of array
The outer two loops have a time complexity of O(n^2) and the inner while loop has a time complexity of O(n). The total time complexity is therefore O(n^2) * O(n) = O(n^3)

Space complexity: O(n)
The set 's' stores all unique quadruplets, which in the worst case scenario is O(n).
The output list stores the final output, which is also O(n).
The total space complexity is therefore O(n) + O(n) = O(n)

*/