class Solution {
    public int minDistance(String word1, String word2) {
        final int m = word1.length();// first word length
        final int n = word2.length();/// second word length
        // dp[i][j] := min # of operations to convert word1[0..i) to word2[0..j)
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i)
            dp[i][0] = i;

        for (int j = 1; j <= n; ++j)
            dp[0][j] = j;

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (word1.charAt(i - 1) == word2.charAt(j - 1))// same characters
                    dp[i][j] = dp[i - 1][j - 1];// no operation
                else
                    int replace = dp[i - 1][j - 1];
					int delete = dp[i - 1][j];
					int insert = dp[i][j - 1];
					cost[i + 1][j + 1] = Math.min(replace, Math.min(delete, insert)) + 1;

        return dp[m][n];
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

class Solution {
    public int minDistance(String word1, String word2) {
        final int m = word1.length();// first word length
        final int n = word2.length();/// second word length
        // dp[i][j] := min # of operations to convert word1[0..i) to word2[0..j)
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i)
            dp[i][0] = i;

        for (int j = 1; j <= n; ++j)
            dp[0][j] = j;

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (word1.charAt(i - 1) == word2.charAt(j - 1))// same characters
                    dp[i][j] = dp[i - 1][j - 1];// no operation
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1; // replace //delete //insert

        return dp[m][n];
    }
}

--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        return cost[m][n];
    }
}

/*

Time complexity : If n is the length of word1, m of word2, because of the two indented loops, it is O(n * m)
Space Complexity: O(m * n) due to the 'dp' array.

*/
