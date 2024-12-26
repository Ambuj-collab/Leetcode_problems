// Memoization approach

class Solution {
    int [][] dp;
    public int minDistance(String word1, String word2) {
        dp=new int [word1.length()+1][word2.length()+1];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                dp[i][j]=-1;
            }
        }
        return solve(word1.length() - 1, word2.length() - 1, word1, word2); 
    }

    public int solve(int index1, int index2, String word1, String word2) {
        if (index1 < 0) {
            return index2 + 1;
        }
        if (index2 < 0) {
            return index1 + 1;
        }
        if(dp[index1][index2]!=-1){
            return dp[index1][index2];
        }
       
        if (word1.charAt(index1) == word2.charAt(index2)) {
            return dp[index1][index2]=solve(index1 - 1, index2 - 1, word1, word2);
        } else {
            return dp[index1][index2]=Math.min(1 + solve(index1 - 1, index2 - 1, word1, word2),
                            Math.min(1 + solve(index1, index2 - 1, word1, word2), 
                                     1 + solve(index1 - 1, index2, word1, word2)));
        }
    }
}

/*

Definition of dp in this problem:
------------------------------------
dp[i][j] represents/stores minimum number of operations required to convert word1, starting from index i to end, to word2, starting from index j to end

*/