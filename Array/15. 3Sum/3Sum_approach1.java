/*

Intuition:
Using two pointers

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total > 0) {
                    k--;
                } else if (total < 0) {
                    j++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                }
            }
        }
        return res;
    }
}

------------------------------------------------------------------------------------------------
												OR
------------------------------------------------------------------------------------------------

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // Sort the array
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate elements for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    // Found a triplet with zero sum
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // Skip duplicate elements for j
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }

                    // Skip duplicate elements for k
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }

                    // Move the pointers
                    j++;
                    k--;
                } else if (sum < 0) {
                    // Sum is less than zero, increment j to increase the sum
                    j++;
                } else {
                    // Sum is greater than zero, decrement k to decrease the sum
                    k--;
                }
            }
        }
        return ans;
    }
}

------------------------------------------------------------------------------------------------
												OR
------------------------------------------------------------------------------------------------

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array to make the two-pointer technique applicable
        Arrays.sort(nums);
      
        // Initialize the list to store the triplets
        List<List<Integer>> triplets = new ArrayList<>();
      
        // Get the length of the array
        int length = nums.length;
      
        // Iterate over the array, looking for the first element of the triplet
        for (int first = 0; first < length - 2 && nums[first] <= 0; ++first) {
            // Skip duplicate elements to avoid duplicate triplets
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
          
            // Initialize the second and third pointers
            int second = first + 1;
            int third = length - 1;
          
            // Use two-pointer technique to find the remaining two elements
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
              
                // If the sum is less than zero, move the second pointer to the right
                if (sum < 0) {
                    ++second;
                }
                // If the sum is greater than zero, move the third pointer to the left
                else if (sum > 0) {
                    --third;
                }
                // If the sum is zero, we've found a valid triplet
                else {
                    triplets.add(List.of(nums[first], nums[second], nums[third]));
                  
                    // Move the second pointer to the right and skip duplicates
                    while (second < third && nums[second] == nums[second + 1]) {
                        ++second;
                    }
                    // Move the third pointer to the left and skip duplicates
                    while (second < third && nums[third] == nums[third - 1]) {
                        --third;
                    }
                  
                    // Move both pointers for the next potential triplet
                    ++second;
                    --third;
                }
            }
        }
        // Return the list of triplets
        return triplets;
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(n^2)
Space complexity: O(n)

*/
