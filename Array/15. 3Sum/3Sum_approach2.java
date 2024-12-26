/*

Intuition of this Problem:
-----------------------------
Set is used to prevent duplicate triplets and parallely we will use two pointer approach to maintain J and k.

Approach for this Problem:
-----------------------------
1) Sort the input array
2) Initialize a set to store the unique triplets and an output list to store the final result
3) Iterate through the array with a variable i, starting from index 0.
4) Initialize two pointers, j and k, with j starting at i+1 and k starting at the end of the array.
5) In the while loop, check if the sum of nums[i], nums[j], and nums[k] is equal to 0. If it is, insert the triplet into the set and increment j and decrement k to move the pointers.
6) If the sum is less than 0, increment j. If the sum is greater than 0, decrement k.
7) After the while loop, iterate through the set and add each triplet to the output list.
8) Return the output list

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int target = 0;
        Arrays.sort(nums);
        Set<List<Integer>> s = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    s.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        output.addAll(s);
        return output;
    }
}

/*

Time Complexity and Space Complexity:
----------------------------------------
Time complexity: O(n^2) // where n is the size of array
Sorting takes O(nlogn) time and loop takes O(n^2) time, So the overall time complexity is O(nlogn + n^2) ~= O(n^2)

Space complexity: O(n) // for taking hashset.

*/