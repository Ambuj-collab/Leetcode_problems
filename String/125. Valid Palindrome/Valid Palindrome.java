class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
        	return true;
        }
        int start = 0;
        int last = s.length() - 1;
        while(start <= last) {
        	char currFirst = s.charAt(start);
        	char currLast = s.charAt(last);
        	if (!Character.isLetterOrDigit(currFirst )) {
        		start++;
        	} else if(!Character.isLetterOrDigit(currLast)) {
        		last--;
        	} else {
        		if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
        			return false;
        		}
        		start++;
        		last--;
        	}
        }
        return true;
    }
}

/*

Time and Space Complexity:
----------------------------
Time complexity:
The time complexity of this solution is O(n), where n is the length of the string. This is because, in the worst case, all characters in the string need to be checked once, so the number of operations is proportional to the length of the string.

Space complexity:
The space complexity of this solution is O(1), as no additional data structures are used, and only a constant amount of memory is required for the start and last pointers and a few variables.

*/
