class Solution {
    public int minDistance(String word1, String word2) {
        // Lengths of the input strings
        int lenWord1 = word1.length();
        int lenWord2 = word2.length();

        // Create a 2D array to store the subproblem results
        int[][] dpTable = new int[lenWord1 + 1][lenWord2 + 1];

        // Initialize the first column, representing insertions needed to transform an empty string into word2
        for (int indexWord2 = 1; indexWord2 <= lenWord2; ++indexWord2) {
            dpTable[0][indexWord2] = indexWord2;
        }

        // Fill out the dpTable for all subproblems
        for (int indexWord1 = 1; indexWord1 <= lenWord1; ++indexWord1) {
            // First row represents deletions needed to transform word1 into an empty string
            dpTable[indexWord1][0] = indexWord1;

            for (int indexWord2 = 1; indexWord2 <= lenWord2; ++indexWord2) {
                // If the characters are the same, take the value from the diagonal (no operation needed)
                if (word1.charAt(indexWord1 - 1) == word2.charAt(indexWord2 - 1)) {
                    dpTable[indexWord1][indexWord2] = dpTable[indexWord1 - 1][indexWord2 - 1];
                } else {
                    // If the characters are different, take the minimum operations from left (insert), top (delete), or diagonal (replace) and add 1
                    int insertOps = dpTable[indexWord1][indexWord2 - 1];
                    int deleteOps = dpTable[indexWord1 - 1][indexWord2];
                    int replaceOps = dpTable[indexWord1 - 1][indexWord2 - 1];

                    dpTable[indexWord1][indexWord2] = Math.min(insertOps, Math.min(deleteOps, replaceOps)) + 1;
                }
            }
        }

        // The bottom-right cell gives the final result
        return dpTable[lenWord1][lenWord2];
    }
}

/*

Time and Space Complexity:
----------------------------
The provided code snippet is an implementation of the dynamic programming approach to solve the problem of finding the minimum number of operations required to convert one word into another, where operations can be insertion, deletion, or substitution of a single character.

Time Complexity: The time complexity of this algorithm is O(m * n) where m is the length of word1 and n is the length of word2. This time complexity arises because the algorithm iterates through all characters of word1 using the variable i and all characters of word2 using the variable j. For each pair of characters (i, j), a constant amount of work is done to compute f[i][j]. Since the two for-loops are nested, each of the m * n pairs is considered exactly once, leading to the overall time complexity of O(m * n).

Space Complexity: The space complexity of the algorithm is also O(m * n) due to the utilization of a two-dimensional array f that has (m + 1) * (n + 1) elements. Each element in f represents the minimum number of operations required to convert the first i characters of word1 to the first j characters of word2. Since the array f has a size proportional to the product of m and n, the space complexity is O(m * n).

*/