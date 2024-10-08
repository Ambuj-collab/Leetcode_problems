/*

Approach 4 - Integer Array:
-----------------------------
1) This solution uses an integer array charIndex to store the indices of characters.
2) We eliminate the need for an unordered map by utilizing the array.
3) The maxLength, left, and right pointers are still present.
4) We iterate through the string using the right pointer.
5) We check if the current character has occurred within the current substring by comparing its index in charIndex with left.
6) If the character has occurred, we move the left pointer to the next position after the last occurrence of the character.
7) We update the index of the current character in charIndex.
8) At each step, we update the maxLength by calculating the length of the current substring.
9) We continue the iteration until reaching the end of the string.
10) Finally, we return the maxLength as the length of the longest substring without repeating characters.

*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] charIndex = new int[128];
        Arrays.fill(charIndex, -1);
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (charIndex[s.charAt(right)] >= left) {
                left = charIndex[s.charAt(right)] + 1;
            }
            charIndex[s.charAt(right)] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
