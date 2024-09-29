class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        
        // [1] longest substring is the one with the largest 
        //    difference between positions of repeated characters; 
        //    thus, we should create a storage for such positions 
        int[] pos = new int[128];

        // [2] while iterating through the string (i.e., moving 
        //    the end of the sliding window), we should also 
        //    update the start of the window 
        int start = 0, end = 0;

        for (char ch : s.toCharArray())
        {
            // [3] get the position for the start of sliding window 
            //    with no other occurences of 'ch' in it 
            start  = Math.max(start, pos[ch]);

            // [4] update maximum length 
            maxLen = Math.max(maxLen, end-start+1);

            // [5] set the position to be used in [3] on next iterations
            pos[ch] = end + 1;
            
            end++;
        }

        return maxLen;
    }
}
