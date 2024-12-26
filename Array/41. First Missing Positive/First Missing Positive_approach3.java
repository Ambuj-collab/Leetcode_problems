class Solution {
    public int firstMissingPositive(int[] num) {
        boolean isOnePresent = false;

        for (int i = 0; i < num.length; i++) {
            if (num[i] == 1) {
                isOnePresent = true;
            }
            if (num[i] < 1 || num[i] > num.length) {
                num[i] = 1;
            }
        }

        if (isOnePresent == false) {
            return 1;
        }

        for (int i = 0; i < num.length; i++) {
            int element = Math.abs(num[i]);
            num[element - 1] = -Math.abs(num[element - 1]);
        }

        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0) {
                return i + 1;
            }
        }
        
        return num.length + 1;
    }
}
