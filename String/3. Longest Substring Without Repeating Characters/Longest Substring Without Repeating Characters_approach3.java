/*

Approach 3 - Unordered Map:
------------------------------
1) We improve upon the first solution by using an unordered map (charMap) instead of a set.
2) The map stores characters as keys and their indices as values.
3) We still maintain the left and right pointers and the maxLength variable.
4) We iterate through the string using the right pointer.
5) If the current character is not in the map or its index is less than left, it means it is a new unique character. We update the charMap with the character's index and update the maxLength if necessary.
6) If the character is repeating within the current substring, we move the left pointer to the next position after the last occurrence of the character.
7) We update the index of the current character in the charMap and continue the iteration.
8) At the end, we return the maxLength as the length of the longest substring without repeating characters.

*/

// In HashMap, we keep each character as a key and the last position where each character was seen as a value.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (!charMap.containsKey(s.charAt(right)) || charMap.get(s.charAt(right)) < left) {
                charMap.put(s.charAt(right), right);
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                left = charMap.get(s.charAt(right)) + 1;
                charMap.put(s.charAt(right), right);
            }
        }
        
        return maxLength;
    }
}


-----------------------------------------------------------------------
								OR
-----------------------------------------------------------------------


//In HashMap, we keep each character as a key and frequency of the characters as a value.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> count = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count.put(c, count.getOrDefault(c, 0) + 1);
            
            while (count.get(c) > 1) {
                char leftChar = s.charAt(left);
                count.put(leftChar, count.get(leftChar) - 1);
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;       
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity: O(n)
Space complexity: O(1)

*/


-------------------------------------------------------------
							OR
-------------------------------------------------------------


// In HashMap, we keep each character as a key and the last position where each character was seen as a value.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            lastSeen.put(c, right);
        }

        return maxLength;       
    }
}

/*

Time and Space Complexity:
-----------------------------
Time complexity: O(n)
Space complexity: O(1)

*/
