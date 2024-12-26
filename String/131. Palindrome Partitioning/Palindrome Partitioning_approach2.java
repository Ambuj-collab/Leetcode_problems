/*

Dynamic Programming:
======================

Thinking Behind the Solution:
--------------------------------
The dynamic programming approach aims to optimize the palindrome checking process by precomputing whether substrings are palindromes. This avoids redundant palindrome checks during the partitioning process. The method leverages a DP table to store the palindrome status of substrings, which significantly reduces the overall time complexity.

Approach:
-----------
1) DP Table Initialization: Create a 2D list dp where dp[i][j] indicates whether the substring s[i:j+1] is a palindrome.

2) Populate DP Table:
	a) All single characters are palindromes, so set dp[i][i] to True for all i.
	b) For substrings of length 2, set dp[i][i+1] to True if s[i] is equal to s[i+1].
	c) For longer substrings, use the relation: if s[i] is equal to s[j] and dp[i+1][j-1] is True, then dp[i][j] is True.

3) Recursive Backtracking:
Define a recursive function backtrack(start, path):
	a) If start reaches the end of the string s, append the current path to the result list.
	b) Iterate over all possible end positions from start to the length of the string.
	c) For each end position, use the precomputed DP table to check if the substring s[start:end+1] is a palindrome.
	d) If it is, recursively call backtrack with the updated start position (end + 1) and the current path plus the new palindrome substring.

4) Initialization: Initialize an empty list result to store all valid partitions. Start the backtracking process from the beginning of the string with an empty path.

5) Return Result: Return the result list containing all possible palindrome partitions.

*/

public class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // Initialize the DP table for single characters and pairs
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j) && (length == 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result, dp);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result, boolean[][] dp) {
        // If we've reached the end of the string, add the current partition to the
        // result list
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        // Explore all possible partitions
        for (int end = start; end < s.length(); end++) {
            // Use the DP table to check if the substring s[start:end+1] is a palindrome
            if (dp[start][end]) {
                path.add(s.substring(start, end + 1));
                // Recur to find other partitions
                backtrack(s, end + 1, path, result, dp);
                // Backtrack to explore other partitions
                path.remove(path.size() - 1);
            }
        }
    }
}

-------------------------------------------------------------------------------------------------
												OR
-------------------------------------------------------------------------------------------------

class Solution {
    private int stringLength;
    private String inputString;
    private boolean[][] palindromeTable;
    private List<String> currentPartition = new ArrayList<>();
    private List<List<String>> allPartitions = new ArrayList<>();

    public List<List<String>> partition(String s) {
        stringLength = s.length();
        inputString = s;
        palindromeTable = new boolean[stringLength][stringLength];

        // Initialize the palindrome table with true for all entries
        for (int i = 0; i < stringLength; ++i) {
            Arrays.fill(palindromeTable[i], true);
        }

        // Populate the palindrome table with actual palindrome information
        for (int i = stringLength - 1; i >= 0; --i) {
            for (int j = i + 1; j < stringLength; ++j) {
                palindromeTable[i][j] = (s.charAt(i) == s.charAt(j)) && palindromeTable[i + 1][j - 1];
            }
        }

        // Start the depth-first search from the beginning of the string
        performDfs(0);
        return allPartitions;
    }

    private void performDfs(int startIndex) {
        // If the current start index reaches the end of the string, we've found a complete partition
        if (startIndex == inputString.length()) {
            allPartitions.add(new ArrayList<>(currentPartition));
            return;
        }

        // Explore further partitions starting from the current index
        for (int endIndex = startIndex; endIndex < stringLength; ++endIndex) {
            // If the substring starting at startIndex and ending at endIndex is a palindrome
            if (palindromeTable[startIndex][endIndex]) {
                // Add the palindrome substring to the current partition
                currentPartition.add(inputString.substring(startIndex, endIndex + 1));

                // Continue searching for palindromes from the next index after the current palindrome
                performDfs(endIndex + 1);

                // Backtrack and remove the last added palindrome from the current partition
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }
}
