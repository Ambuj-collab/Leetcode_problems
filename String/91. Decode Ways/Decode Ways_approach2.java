// Recursion approach => you will get TLE
class Solution {
    public int numDecodings(String s) {
        return s.length() == 0 ? 0 : numDecodings(0, s);
    }

    private int numDecodings(int p, String s) {
        int n = s.length();
        if (p == n)
            return 1;
        if (s.charAt(p) == '0')
            return 0;
        int res = numDecodings(p + 1, s);
        if (p < n - 1 && (s.charAt(p) == '1' || s.charAt(p) == '2' && s.charAt(p + 1) < '7'))
            res += numDecodings(p + 2, s);
        return res;
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

// Memoization => Best code(Beats 100%)
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        Integer[] mem = new Integer[n];  // mem[i] represents number of ways to decode substring starting from i to n  
        return s.length() == 0 ? 0 : numDecodings(0, s, mem);
    }

    private int numDecodings(int p, String s, Integer[] mem) {
        int n = s.length();
        if (p == n)
            return 1;
        if (s.charAt(p) == '0')
            return 0;
        if (mem[p] != null)
            return mem[p];
        int res = numDecodings(p + 1, s, mem);
		// In Java, the && operator has higher precedence than the || operator. So, s.charAt(p) == '2' && s.charAt(p + 1) < '7' will be evaluated first and then the result of it will evaluated with s.charAt(p) == '1'
        if (p < n - 1 && (s.charAt(p) == '1' || s.charAt(p) == '2' && s.charAt(p + 1) < '7'))
            res += numDecodings(p + 2, s, mem);
        return mem[p] = res;
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

// DP => Best code(Beats 100%)
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--)
            if (s.charAt(i) != '0') {
                dp[i] = dp[i + 1];
                if (i < n - 1 && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')))
                    dp[i] += dp[i + 2];
            }
        return dp[0];
    }
}
