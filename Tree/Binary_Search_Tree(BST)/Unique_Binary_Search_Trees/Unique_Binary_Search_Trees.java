class Solution {
    public int numTrees(int n) {
        // Refer https://leetcode.com/problems/unique-binary-search-trees-ii/description/?envType=problem-list-v2&envId=binary-tree first before understanding this solution 
		
        // Create 'sol' array to store the solution...
        int[] sol = new int[n + 1];
        sol[0] = sol[1] = 1;

        // Run a loop from 2 to n...
        for (int i = 2; i <= n; i++) {
            // Within the above loop, run a nested loop from 1 to i...
            for (int j = 1; j <= i; j++) {
                // Update the i-th position of the array by adding the multiplication of the
                // respective index...
                sol[i] += sol[i - j] * sol[j - 1];
            }
        }
        // Return the value of the nth index of the array to get the solution...
        return sol[n];
    }
}