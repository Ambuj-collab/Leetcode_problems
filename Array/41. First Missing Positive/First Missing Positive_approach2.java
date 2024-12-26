class Solution {
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = 0;

        for (int i : nums) {
            set.add(i);
            max = Math.max(i, max);
        }

        for (int i = 1; i < max; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return max + 1;
    }
}
