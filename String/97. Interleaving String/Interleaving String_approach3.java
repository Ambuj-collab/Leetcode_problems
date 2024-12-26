/*
Solution 1: Brute Force (Recursion) - Time Limit Exceeded

Time complexity: O(2^(m+n))
Space complexity: O(m+n)
*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        return helper(s1, s2, s3, 0, 0);
    }

    public boolean helper(String s1, String s2, String s3, int i, int j) {
        if (i + j == s3.length())
            return true;

        if (i < s1.length() && s3.charAt(i + j) == s1.charAt(i)) {
            boolean flag = helper(s1, s2, s3, i + 1, j);
            if (flag)
                return true;
        }

        if (j < s2.length() && s3.charAt(i + j) == s2.charAt(j)) {
            boolean flag = helper(s1, s2, s3, i, j + 1);
            if (flag)
                return true;
        }

        return false;
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------
/*
Solution 2: Top Down DP (Recursion + Memoization)

Time complexity: O(m*n)
Space complexity: O(m*n)
*/

//Efficient solution => Beats 100%
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];

        return helper(s1, s2, s3, 0, 0, memo);
    }

    public boolean helper(String s1, String s2, String s3, int i, int j, Boolean[][] memo) {
        if (i + j == s3.length())
            return true;

        if (memo[i][j] != null)
            return memo[i][j];

        if (i < s1.length() && s3.charAt(i + j) == s1.charAt(i)) {
            boolean flag = helper(s1, s2, s3, i + 1, j, memo);
            memo[i][j] = flag;
            if (flag)
                return true;
        }

        if (j < s2.length() && s3.charAt(i + j) == s2.charAt(j)) {
            boolean flag = helper(s1, s2, s3, i, j + 1, memo);
            memo[i][j] = flag;
            if (flag)
                return true;
        }

        memo[i][j] = false;
        return false;
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------
/*
Solution 3: Bottom UP DP (2D)

Time complexity: O(m*n)
Space complexity: O(m*n)
*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;

                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);

                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);

                else
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[s1.length()][s2.length()];
    }
}


--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------
/*
Solution 4: Bottom UP DP (1D)

Time complexity: O(m*n)
m is the length of string s1 and n is the length of string s2. This is because we iterate through each character of s1 and s2 once while constructing the dynamic programming matrix.

Space complexity: O(n)
n is the length of string s2. We only use a dynamic programming array of length n+1 to store the state transitions.
*/

/*

Approach:
-----------
1) Check total length: If the sum of the lengths of s1 and s2 is not equal to the length of s3, return False since it's impossible for s1 and s2 to interleave to form s3.

2) Initialize dp array: Create an array dp of size [len(s2) + 1] to store whether substrings of s1 and s2 can interleave to form substrings of s3.

3) Initialization: Set dp[0] to True to indicate that an empty s1 and empty s2 can interleave to form an empty s3.

4) Loop through s1 and s2: Use nested loops to iterate through all possible combinations of substrings of s1 and s2 to check if they can interleave to form s3.

5) Base cases handling:
	a) If i is 0 and j is 0, it means both s1 and s2 are empty. Set dp[j] to True.
	b) If i is 0, update dp[j] using the previous value of dp[j - 1] and check if the character in s2 at index j - 1 matches the character in s3 at index i + j - 1.
	c) If j is 0, update dp[j] using the current value of dp[j] and check if the character in s1 at index i - 1 matches the character in s3 at index i + j - 1.

6) General case:
For all other cases (when both i and j are not 0), update dp[j] using the following conditions:
	a) dp[j] should be the result of (dp[j] and s1[i - 1] == s3[i + j - 1]), meaning that the current character in s1 matches the current character in s3, and the previous substring also interleave to form the previous part of s3.
	b) dp[j - 1] should be the result of (dp[j - 1] and s2[j - 1] == s3[i + j - 1]), meaning that the current character in s2 matches the current character in s3, and the previous substring of s2 can interleave to form the previous part of s3.

7) Return result: The final result is stored in dp[len(s2)], which indicates whether s1 and s2 can interleave to form s3.

8) The function returns the value of dp[len(s2)] as the final result.

In summary, the algorithm uses dynamic programming to determine whether substrings of s1 and s2 can be interleaved to form substrings of s3. The dp array stores whether the substrings can interleave at each position.

*/

class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean dp[] = new boolean[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0)
                    dp[j] = true;

                else if (i == 0)
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);

                else if (j == 0)
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);

                else
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[s2.length()];
    }
}
