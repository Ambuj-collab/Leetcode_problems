/*

Intuition:
------------
The problem asks for finding all unique quadruplets (four numbers) in an array that add up to a given target. A brute force approach would involve checking every combination of four numbers, but this would be inefficient. Instead, we can leverage sorting and the two-pointer technique, which is often used for the 3-sum problem, to optimize the search for quadruplets.

Approach:
-----------
1) Sort the array: Sorting helps in efficiently applying the two-pointer technique and also allows us to skip duplicate elements.
2) Two loops for the first two numbers:
	a) Use two nested loops: the outer loop iterates over the first element of the quadruplet (i), and the inner loop iterates over the second element (j).
	b) Skip duplicate elements in both loops to avoid generating duplicate quadruplets.
3) Two-pointer technique for the remaining two numbers:
	For the remaining two numbers, use a two-pointer approach:
		i) Start one pointer k from just after j and another pointer l from the end of the array.
		ii) Calculate the sum of the four elements: arr[i] + arr[j] + arr[k] + arr[l].
		iii) If the sum equals the target, add the quadruplet to the result and move both pointers inward while skipping duplicates.
		iv) If the sum is less than the target, move the left pointer (k) to increase the sum.
		v) If the sum is greater than the target, move the right pointer (l) to decrease the sum.
4) Return the result: The result list contains all unique quadruplets whose sum equals the target.

*/

class Solution {
    public List<List<Integer>> fourSum(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = arr.length;

        Arrays.sort(arr);

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && arr[i - 1] == arr[i])
                continue;

            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && arr[j - 1] == arr[j])
                    continue;

                int k = j + 1;
                int l = len - 1;

                while (k < l) {
                    long sum = arr[i] + arr[j];
                    sum += arr[k] + arr[l];

                    if (sum == target) {
                        ans.add(Arrays.asList(arr[i], arr[j], arr[k], arr[l]));
                        k++;
                        l--;

                        while (k < l && arr[k - 1] == arr[k])
                            k++;
                        
                        while (k < l && arr[l + 1] == arr[l])
                            l--;
                    
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }

        return ans;
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity:
Sorting the array takes O(nlogn), and we use two nested loops and a two-pointer technique, which results in a total time complexity of O(n^3), where n is the length of the array.

Space complexity:
The space complexity is O(1) for extra space since we only use a few variables for the two-pointer technique. However, the space complexity for the result list will depend on the number of quadruplets found.

*/