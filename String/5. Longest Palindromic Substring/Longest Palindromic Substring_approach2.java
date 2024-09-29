/*
Approach 2: Expand Around Center
------------------------------------
Intuition :
To enumerate all palindromic substrings of a given string, we first expand a given string at each possible starting position of a palindrome and also at each possible ending position of a palindrome and keep track of the length of the longest palindrome we found so far.

Approach :
1) We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2n - 1 such centers.
2) You might be asking why there are 2n - 1 but not n centers? The reason is the center of a palindrome can be in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.'
3) Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2).

Algorithm :
1) At starting we have maz_str = s[0] and max_len = 1 as every single character is a palindrome.
2) Now, we will iterate over the string and for every character we will expand around its center.
3) For odd length palindrome, we will consider the current character as the center and expand around it.
4) For even length palindrome, we will consider the current character and the next character as the center and expand around it.
5) We will keep track of the maximum length and the maximum substring.
6) Print the maximum substring.

Complexity Analysis:
-------------------------
Time complexity : O(n^2). Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2).

Space complexity : O(1).

*/

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int odd = expandAroundCenter(s, i, i);
            int even = expandAroundCenter(s, i, i + 1);
            int max_len = Math.max(odd, even);

            if (max_len > end - start) {
                start = i - (max_len - 1) / 2;
                end = i + max_len / 2;
            }
        }

        return s.substring(start, end + 1);        
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }    
}


---------------------------------------------------------------------------------------------
											OR
---------------------------------------------------------------------------------------------


public class Solution {
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        String maxStr = s.substring(0, 1);

        for (int i = 0; i < s.length() - 1; i++) {
            String odd = expandFromCenter(s, i, i);
            String even = expandFromCenter(s, i, i + 1);

            if (odd.length() > maxStr.length()) {
                maxStr = odd;
            }
            if (even.length() > maxStr.length()) {
                maxStr = even;
            }
        }

        return maxStr;
    }

    private String expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}