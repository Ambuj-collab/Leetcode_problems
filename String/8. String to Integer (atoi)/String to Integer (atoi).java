class Solution {
    public int myAtoi(String str) {
        // Ensure the input string is not null
        if (str == null) {
            return 0;
        }

        int length = str.length();

        // If the string is empty, return 0
        if (length == 0) {
            return 0;
        }

        int index = 0;

        // Skip whitespace characters
        while (index < length && str.charAt(index) == ' ') {
            index++;
        }

        // If we reached the end of string after skipping spaces, return 0
        if (index == length) {
            return 0;
        }

        // Determine the sign based on the current character
        int sign = 1;
        if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (str.charAt(index) == '+') {
            index++;
        }

        int result = 0;
        // Pre-calculate the threshold to check for overflow
        int threshold = Integer.MAX_VALUE / 10;
      
        // Convert the number
        while (index < length) {
            char currentChar = str.charAt(index);
          
            // Break if the current character is not a digit
            if (currentChar < '0' || currentChar > '9') {
                break;
            }

            // Check for overflow when adding a new digit
            if (result > threshold || (result == threshold && currentChar > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Update result with the new digit
            result = result * 10 + (currentChar - '0');
            index++;
        }

        // Apply the determined sign to the result and return
        return sign * result;
    }
}


/*

Time Complexity:
-------------------
The time complexity of the given function is O(n), where n is the length of the string. Here's the breakdown:

We iterate once over the string to trim whitespaces, which takes O(n) in the worst case (if all characters are spaces).
Checking if the character is a sign ('+' or '-') is O(1).
The following while loop iterates over the rest of the string, but it runs at most n times, which is also O(n) in the worst case.
Each operation inside the while loop, including checking if a character is a digit, converting it to an integer, checking for overflow, and updating the result, is done in constant time O(1).
Therefore, the dominating factor in the time complexity is the length of the string n, resulting in O(n) overall.

Space Complexity:
--------------------
The space complexity of the function is O(1) because we use a fixed number of integer variables (i, sign, res, flag, and n) and they do not depend on the size of the input string. No additional structures are allocated that would grow with the input size. The space used by s is not counted since it is an input to the function and not additional space allocated by the function itself.

*/