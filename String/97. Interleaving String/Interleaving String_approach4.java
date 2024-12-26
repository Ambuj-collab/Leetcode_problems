class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {

        // Get the lengths of the strings
        int m = s1.length(), n = s2.length();

        // If the combined length of s1 and s2 does not equal the length of s3, return false
        if (m + n != s3.length()) {
            return false;
        }

        // Create a boolean array to keep track of the interleavings
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // Iterate over each character of both s1 and s2
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {

                // Index k for matching characters in s3
                int k = i + j - 1;

                // If there are remaining characters in s1, check if they match s3's characters
                if (i > 0) {
                    dp[j] &= s1.charAt(i - 1) == s3.charAt(k);
                }

                // If there are remaining characters in s2, check if they match s3's characters
                if (j > 0) {
                    dp[j] |= (dp[j - 1] & (s2.charAt(j - 1) == s3.charAt(k)));
                }
            }
        }

        // Return whether it's possible to interleave s1 and s2 to get s3
        return dp[n];
    }
}


/*

Time and Space Complexity
The given Python code provides a solution to determine if a string s3 is formed by the interleaving of two other strings s1 and s2. Let's analyze both the time complexity and space complexity of the code.

Time Complexity
The outer loop in the code runs m + 1 times, where m is the length of s1. Within this loop, there's an inner loop that runs n + 1 times, where n is the length of s2. However, observe that for each outer iteration, the inner loop starts at 1 (since j ranges from 0 to n), so the combined iterations for the inner loop are actually m * (n + 1).

Each iteration of the inner loop consists of constant time checks and assignment operations, so its time complexity is O(1). Thus, the total time complexity for the double loop structure is O(m * (n + 1)). Simplifying, this is equivalent to O(m * n) since the addition of a constant 1 does not change the order of growth.

Therefore, the overall time complexity of the code is O(m * n).

Space Complexity
Space complexity considers the additional space used by the algorithm excluding the input sizes. In the code, a one-dimensional Boolean array f is initialized with n + 1 elements. The space usage of this array dominates the space complexity. There is no other data structure that grows with the input size. Thus, the space complexity is based on the size of f, which is O(n).

To summarize:

Time complexity: O(m * n)
Space complexity: O(n)

*/