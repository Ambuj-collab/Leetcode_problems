class Solution {
    public String countAndSay(int n) {
        String out = "1";

        for (int i = 0; i < n - 1; i++) {
            StringBuilder str = new StringBuilder();
            char prevChar = out.charAt(0);
            int count = 1;

            for (int j = 1; j < out.length(); j++) {
                if (out.charAt(j) == prevChar) {
                    count++;
                } else {
                    str.append(count).append(prevChar);
                    prevChar = out.charAt(j);
                    count = 1;
                }
            }

            str.append(count).append(prevChar);
            out = str.toString();
        }

        return out;
    }
}
