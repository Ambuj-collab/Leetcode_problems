// Solution 1

class Solution {
    public void sortColors(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        count.put(0, 0);
        count.put(1, 0);
        count.put(2, 0);

        for (int num : nums) {
            count.put(num, count.get(num) + 1);
        }

        int idx = 0;
        for (int color = 0; color < 3; color++) {
            int freq = count.get(color);
            for (int j = 0; j < freq; j++) {
                nums[idx] = color;
                idx++;
            }
        }
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(n)
Space complexity: O(1) [We have only red, white and blue colors]

*/

---------------------------------------------------------------------------------
											OR
---------------------------------------------------------------------------------
// Solution 2

class Solution {
    public void sortColors(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = nums.length - 1;

        while (white <= blue) {
            if (nums[white] == 0) {
                int temp = nums[white];
                nums[white] = nums[red];
                nums[red] = temp;
                red++;
                white++;
            } else if (nums[white] == 1) {
                white++;
            } else {
                int temp = nums[white];
                nums[white] = nums[blue];
                nums[blue] = temp;
                blue--;
            }
        }
    }
}

---------------------------------------------------------------------------------
										OR
---------------------------------------------------------------------------------

/*

Intuition:
-------------
1) The problem of sorting an array of 0s, 1s, and 2s can be visualized as partitioning the array into three distinct sections:
	a) All 0s at the beginning.
	b) All 1s in the middle.
	c) All 2s at the end.

2) The intuition behind the Dutch National Flag algorithm is to maintain three pointers (or indices) that help in effectively placing the numbers in their respective sections with a single pass through the array.

Approach:
------------
1) Initialization: Set three pointers:
	a) low: points to the position where the next 0 should go.
	b) mid: is used to traverse the array.
	c) high: points to the position where the next 2 should go.
2) Traversal:
a) While mid is less than or equal to high:
	i) If nums[mid] is 0:
		-> Swap nums[mid] with nums[low].
		-> Increment both low and mid.
	ii) If nums[mid] is 1:
		-> Increment mid (since 1s are already in the correct section).
	iii) If nums[mid] is 2:
		-> Swap nums[mid] with nums[high].
		-> Decrement high (without incrementing mid, since we need to check the newly swapped value at mid).
3) Completion: The loop continues until mid surpasses high, at which point all 0s, 1s, and 2s are sorted in their respective sections.

Example/Dry Run:
-------------------
1) Lets take nums = [2, 0, 2, 1, 1, 0]:
	a) Initial Setup
		Pointers: low = 0, mid = 0, high = 5
2) Iterations
	a) Iteration 1: nums[mid] is 2. Swap with nums[high] → [0, 0, 2, 1, 1, 2], high = 4.
	b) Iteration 2: nums[mid] is 0. Swap with nums[low] → [0, 0, 2, 1, 1, 2], low = 1, mid = 1.
	c) Iteration 3: nums[mid] is 0. Swap with nums[low] → [0, 0, 2, 1, 1, 2], low = 2, mid = 2.
	d) Iteration 4: nums[mid] is 2. Swap with nums[high] → [0, 0, 1, 1, 2, 2], high = 3.
	e) Iteration 5: nums[mid] is 1. Increment mid → [0, 0, 1, 1, 2, 2], mid = 3.
	f) Iteration 6: nums[mid] is 1. Increment mid → [0, 0, 1, 1, 2, 2], mid = 4.
3) Final Output
	The sorted array is: [0, 0, 1, 1, 2, 2].

*/

class Solution {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
	
    public void sortColors(int[] nums) {
        int mid=0, low=0, high=nums.length-1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, mid, low);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity: O(n) [The algorithm only passes through the array once, where n is the length of the array]
Space complexity: O(1)

*/