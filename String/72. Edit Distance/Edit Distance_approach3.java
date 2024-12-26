// Recursion approach  --> You will get TLE as input string length constraints is high

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        return f(word1, word2, n - 1, m - 1);
    }

    public int f(String s1, String s2, int ind1, int ind2) {
        if (ind1 < 0)
            return ind2 + 1; // base case, here we will be deleting the remaining elements to match the other
                             // string
        if (ind2 < 0)
            return ind1 + 1;

        if (s1.charAt(ind1) == s2.charAt(ind2)) { // if matched then no operation needed
            return 0 + f(s1, s2, ind1 - 1, ind2 - 1);
        } else {
            int insert = f(s1, s2, ind1, ind2 - 1); // hypothetically inserting so just imagine the it was added and
                                                    // stay ind1 on same posi
            int delete = f(s1, s2, ind1 - 1, ind2); // deleted so move ind1 - 1
            int replace = f(s1, s2, ind1 - 1, ind2 - 1); // replaced so moved voth ind by -1
            return 1 + Math.min(insert, Math.min(delete, replace));
        }
    }
}


---------------------------------------------------------------------------------------------------------------------------
															OR
---------------------------------------------------------------------------------------------------------------------------

// Recursion approach with Memoization(i.e., DP) to avoid TLE(i.e., Time Limit Exceeded)

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n][m];
        for (int[] rows : dp) {
            Arrays.fill(rows, -1);
        }
        return f(word1, word2, n - 1, m - 1, dp);
    }

    public int f(String s1, String s2, int ind1, int ind2, int[][] dp) {
        if (ind1 < 0)
            return ind2 + 1;
        if (ind2 < 0)
            return ind1 + 1;

        if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }

        if (s1.charAt(ind1) == s2.charAt(ind2)) {
            return dp[ind1][ind2] = 0 + f(s1, s2, ind1 - 1, ind2 - 1, dp);
        } else {
            int insert = f(s1, s2, ind1, ind2 - 1, dp);
            int delete = f(s1, s2, ind1 - 1, ind2, dp);
            int replace = f(s1, s2, ind1 - 1, ind2 - 1, dp);
            return dp[ind1][ind2] = 1 + Math.min(insert, Math.min(delete, replace));
        }
    }
}


/*

Notes:
--------
1) Recursion: The f() function/method recursively calculates the minimum number of operations needed to convert s1 to s2 from given indices.

2) Memoization: A 2D 'dp' array is used to store the calculated results for subproblems. The result is stored in the 'dp' array for future reference.

3) Tabulation: A 2D 'dp' array is initialized with the base cases: the first row and column are filled with the corresponding indices (0, 1, 2, ...) to represent the operations needed to convert an empty string to a string of that length. The 'dp' array is filled iteratively, calculating each cell's value based on the previous cells and the current characters in 'word1' and 'word2' strings.

*/
