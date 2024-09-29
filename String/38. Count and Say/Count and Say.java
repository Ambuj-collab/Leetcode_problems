class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String temp = countAndSay(n - 1);
        return getRLE(temp);
    }

    // This function provides/returns the Run-length encoding(RLE) of the passed string
    private String getRLE(String str) {
        StringBuilder sb = new StringBuilder();

        char prev = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (prev != str.charAt(i)) {
                sb.append(count + "" + prev);
                count = 1;
                prev = str.charAt(i);
            } else {
                count++;
            }
        }
        sb.append(count + "" + prev);
        return sb.toString();
    }
}
