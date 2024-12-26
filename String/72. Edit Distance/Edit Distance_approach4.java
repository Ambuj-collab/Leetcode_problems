class Solution {
    public int minDistance(String word1, String word2) {
        Map<String, Integer> cache = new HashMap<>();
        return dp(word1, word2, 0, 0, cache);
    }

    private int dp(String word1, String word2, int i, int j, Map<String, Integer> cache) {
        String key = i + "->" + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (i >= word1.length()) {
            return word2.length() - j;
        }
        if (j >= word2.length()) {
            return word1.length() - i;
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            return dp(word1, word2, i + 1, j + 1, cache);
        }
        int insert = 1 + dp(word1, word2, i, j + 1, cache);
        int delete = 1 + dp(word1, word2, i + 1, j, cache);
        int replace = 1 + dp(word1, word2, i + 1, j + 1, cache);
        int res = Math.min(insert, Math.min(delete, replace));
        cache.put(key, res);
        return res;
    }
}
