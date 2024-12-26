/*

Intuition:
------------
The problem can be solved using dynamic programming. We can define a dynamic programming array dp, where dp[i] represents the number of ways to decode the substring between 0th and ith index. We can iterate through the string and update the dp array based on the current digit and the previous digits.

Approach:
-----------
1) Check if the input string s is empty or starts with '0'. If so, return 0 because a valid decoding is not possible.
2) Initialize a dynamic programming array dp of size n + 1, where n is the length of the input string. Set dp[0] and dp[1] to 1, as there is one way to decode an empty string and a string of length 1.
3) Iterate through the string starting from index 2 up to n + 1.
	a) Convert the current one-digit and two-digit substrings to integers.
	b) If the one-digit substring is not '0', update dp[i] by adding dp[i - 1] because we can consider the current digit as a single character.
	c) If the two-digit substring is between 10 and 26 (inclusive), update dp[i] by adding dp[i - 2] because we can consider the current two digits as a single character.
4) The final result is stored in dp[n], where n is the length of the input string.

*/

public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; ++i) {
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit != 0) {
                dp[i] += dp[i - 1];
            }

            if (10 <= twoDigits && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------


class Solution {
    public int numDecodings(String s) {
        int strLen = s.length();

        int[] dp = new int[strLen + 1];
        // there is only one way to decode an empty string
        dp[0] = 1;

        // the first element of the dp array is 1 if the first
        // character of the string is not '0',
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        } else {
            // there's no way to decode a string that starts
            // with '0'
            return 0;
        }

        // iterate through the input string starting from the
        // 2nd character
        for (int i = 2; i <= strLen; ++i) {
            // if the current character is not '0', add the
            // number of ways to decode the substring without
            // the current character
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // if the substring of the current and previous
            // characters is a valid two-digit number, add the
            // number of ways to decode the substring without
            // the current and previous characters
            if (s.charAt(i - 2) == '1' ||
                    (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[strLen];
    }
}

/*

Complexity:
--------------
Time complexity: O(n) where n is the length of the input string. We iterate through the string once.
Space complexity: O(n) for the dynamic programming array 'dp'.

*/