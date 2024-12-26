import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Solution {
  
    /*
     * Finds all unique quadruplets in the array which gives the sum of the target.
     *
     * @param nums The input array of integers.
     * @param target The target sum for the quadruplets.
     * @return A list of all unique quadruplets in the array that sum up to the target.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // Number of elements in the input array
        int arraySize = nums.length;
        // Container to store the resulting quadruplets
        List<List<Integer>> quadrupletsList = new ArrayList<>();
      
        // If there are fewer than 4 elements, no quadruplet can be formed
        if (arraySize < 4) {
            return quadrupletsList;
        }
      
        // Sort the input array to enable the two-pointer approach
        Arrays.sort(nums);
      
        // Iterate over the array with the first pointer
        for (int i = 0; i < arraySize - 3; ++i) {
            // Skip duplicate values to ensure uniqueness of the quadruplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
          
            // Iterate over the array with the second pointer
            for (int j = i + 1; j < arraySize - 2; ++j) {
                // Skip duplicate values to ensure uniqueness of the quadruplets
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
              
                // Initialize two pointers, one at the element after the second pointer
                // and another at the end of the array
                int leftPointer = j + 1, rightPointer = arraySize - 1;
              
                // Use a while loop to find all pairs between the left and right pointers
                while (leftPointer < rightPointer) {
                    // Calculate the current sum of the quadruplet
                    long currentSum = (long) nums[i] + nums[j] + nums[leftPointer] + nums[rightPointer];
                  
                    // If the sum is less than target, move the left pointer to the right to increase the sum
                    if (currentSum < target) {
                        ++leftPointer;
                    } 
                    // If the sum is greater than target, move the right pointer to the left to decrease the sum
                    else if (currentSum > target) {
                        --rightPointer;
                    } 
                    // If the sum is equal to the target, a quadruplet is found
                    else {
                        quadrupletsList.add(Arrays.asList(nums[i], nums[j], nums[leftPointer], nums[rightPointer]));
                        // Move the left pointer to the right and the right pointer to the left
                        leftPointer++;
                        rightPointer--;
                        // Skip over any duplicate values for the third and fourth numbers in the quadruplet
                        while (leftPointer < rightPointer && nums[leftPointer] == nums[leftPointer - 1]) {
                            leftPointer++;
                        }
                        while (leftPointer < rightPointer && nums[rightPointer] == nums[rightPointer + 1]) {
                            rightPointer--;
                        }
                    }
                }
            }
        }
        return quadrupletsList;
    }
}


/*

Time and Space Complexity:
----------------------------
The provided code implements the four number sum problem (4Sum), which is an extension of the 3Sum problem. The algorithm searches for all unique quadruplets in an array that sum up to a given target. Here's an analysis of its time complexity and space complexity:

Time Complexity:

1) The time complexity of the code is O(n^3), where n is the number of elements in the array. Here's the breakdown:
	a) The code starts with sorting the input array, which is O(nlogn) using a typical sorting algorithm like quicksort or mergesort.
	b) Next, there are two nested loops iterating through the array.
		i) The outer loop (index i) goes through the elements from 0 to n-4: O(n)
		ii) The inner loop (index j) goes through the elements from i+1 to n-3: O(n)
	c) Inside the inner loop, there is a while loop (with pointers k and l) that could iterate up to n times in the worst case: O(n)
2) Multiplying these nested iterations gives us the overall time complexity:
	a) Sorting: O(n log n)
	b) Three nested loops: O(n^3)
Since O(n^3) dominates O(n log n), the final time complexity is O(n^3).

Space Complexity:

The space complexity of the algorithm is O(m), where m is the number of unique quadruplets that sum up to the target. The space is used to store the ans list which contains the results. In the worst case, where no two quadruplets are the same, the space complexity can be as large as O(n^3) if every combination is a unique quadruplet adding up to the target. However, in average cases, m will be much smaller than n^3.

Other than that, the algorithm only uses a constant amount of extra space for pointers and variables, which does not depend on the input size and is thus O(1).

Overall, the space complexity is the larger of O(m) and O(1), which is O(m).

*/