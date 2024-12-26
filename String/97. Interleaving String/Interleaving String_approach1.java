/*

Approach:
-----------
1) The solve function is a recursive helper function that takes the current indices ind1 and ind2 for strings s1 and s2 respectively, along with a memoization table dp. The purpose of this function is to check if it's possible to create the remaining part of s3 (starting from ind1+ind2 position) using the remaining parts of s1 (starting from ind1 position) and s2 (starting from ind2 position).

2) The base case for the recursion is when the sum of ind1 and ind2 equals the length of s3, meaning all characters of s3 have been matched successfully. In this case, the function returns true.

3) Before proceeding with the actual computation, the function checks if the result for the current ind1 and ind2 indices has already been computed and stored in the memoization table dp. If so, it returns the precomputed result.

4) The function initializes a boolean variable ans to false. It then checks two conditions:
	a) If ind1 is within bounds of s1 and the character at s1[ind1] matches the character at s3[ind1+ind2], it recursively calls solve by moving the index ind1 of s1 one step forward.
	b) If ind2 is within bounds of s2 and the character at s2[ind2] matches the character at s3[ind1+ind2], it recursively calls solve by moving the index ind2 of s2 one step forward.

The ans is updated using the bitwise OR operation (|) to retain any previous true value and to combine the results of the two recursive calls.

5) Finally, the function stores the computed ans in the memoization table dp for the current ind1 and ind2 indices and returns this result.

6) The isInterleave function is the main function that's called to determine whether s3 can be formed by interleaving characters from s1 and s2. It first checks if the total length of s1 and s2 matches the length of s3. If not, it returns false as it's impossible to form s3.

7) It initializes a 2D vector dp to store the memoization table. The dimensions of this table are (s1.size() + 1) rows and (s2.size() + 1) columns, with all values initialized to -1.

8) It then calls the solve function with initial indices ind1 and ind2 set to 0, along with the memoization table dp. The result of this call indicates whether it's possible to form s3 by interleaving s1 and s2.

9) The isInterleave function returns the result obtained from the solve function.

*/

class Solution {
    public boolean solve(String s1, String s2, String s3, int ind1, int ind2, int[][] dp) {
        if (ind1 + ind2 == s3.length()) return true;
        if (dp[ind1][ind2] != -1) return dp[ind1][ind2] == 1;
        boolean ans = false;
        
        if (ind1 < s1.length() && s1.charAt(ind1) == s3.charAt(ind1 + ind2)) {
            ans |= solve(s1, s2, s3, ind1 + 1, ind2, dp);
        }
        
        if (ind2 < s2.length() && s2.charAt(ind2) == s3.charAt(ind1 + ind2)) {
            ans |= solve(s1, s2, s3, ind1, ind2 + 1, dp);
        }
        
        dp[ind1][ind2] = ans ? 1 : 0;
        return ans;
    }
    
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        
        return solve(s1, s2, s3, 0, 0, dp);
    }
}

/*
Time and Space Complexity:
----------------------------
Time complexity:O(n * m)
Space complexity:O(n * m)
*/


-------------------------------------------------------------------------------------------------
												OR
-------------------------------------------------------------------------------------------------
// Efficient solution - Beats 100%

/*

To solve this problem, let's look at if s1[0 ~ i] s2[0 ~ j] can be interleaved to s3[0 ~ k].
	1) Start from indices0, 0, 0 and compare s1[i] == s3[k] or s2[j] == s3[k]
	2) Return valid only if either i or j match k and the remaining is also valid
	3) Caching is the key to performance. This is very similar to top down dp
	4) Only need to cache invalid[i][j] since most of the case s1[0 ~ i] and s2[0 ~ j] does not form s3[0 ~ k]. Also tested caching valid[i][j] the run time is also 1ms
	5) Many guys use substring but it's duplicate code since substring itself is checking char by char. We are already doing so

I think the efficiency of using cache dfs is case-by-case. Here because most of the cases are invalid, so choosing to cache the invalid cases is very efficient and it avoids most of the recursion calls. Here, caching valid[i][j] takes much longer time than invalid[i][j].

*/
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length())
            return false;
        return dfs(c1, c2, c3, 0, 0, 0, new boolean[m + 1][n + 1]);
    }

    public boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
        if (invalid[i][j])
            return false;
        if (k == c3.length)
            return true;
        boolean valid = i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j, k + 1, invalid) ||
                j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid);
        if (!valid)
            invalid[i][j] = true;
        return valid;
    }
}


-------------------------------------------------------------------------------------------------
												OR
-------------------------------------------------------------------------------------------------
// Efficient solution - Beats 100%

class Solution {
    Boolean[][] cache;

    public boolean isInterleave(String s1, String s2, String s3) {
        cache = new Boolean[s1.length() + 1][s2.length() + 1];
        return find(s1, s2, s3, 0, 0, 0);
    }

    boolean find(String s1, String s2, String s3, int i, int j, int k) {
        if (k == s3.length())
            return i == s1.length() && j == s2.length();
        if (cache[i][j] != null)
            return cache[i][j];
        boolean ans = false;
		
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k))
            ans = find(s1, s2, s3, i + 1, j, k + 1);
		
        if (ans)
            return ans;
		
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k))
            ans = find(s1, s2, s3, i, j + 1, k + 1);
		
        cache[i][j] = ans;
        return ans;
    }
}
