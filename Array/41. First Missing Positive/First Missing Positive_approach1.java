class Solution {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int k = 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                continue;
            } else if (nums[i] == k) {
                if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    continue;
                } else {
                    k++;
                }
            } else {
                return k;
            }
        }

        return k;
    }
}
