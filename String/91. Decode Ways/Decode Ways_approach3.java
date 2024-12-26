class Solution {

    // Method to calculate the number of ways to decode a message
    public int numDecodings(String s) {
        // String length
        int length = s.length();
        // Variables to hold previous and current number of decodings
        int prevCount = 0, currentCount = 1;

        // Loop through each character in the string
        for (int i = 1; i <= length; ++i) {
            // Initialize the next count as 0
            int nextCount = 0;
          
            // If the current character is not '0', it can stand alone, so add current count to next count
            if (s.charAt(i - 1) != '0') {
                nextCount = currentCount;
            }
          
            // If there are more than one characters and the substring of two characters can represent a valid alphabet
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26) {
                // Add the previous count to next count
                nextCount += prevCount;
            }
          
            // Update prevCount and currentCount for the next iteration
            prevCount = currentCount;
            currentCount = nextCount;
        }
      
        // Return the total count of decodings for the entire string
        return currentCount;
    }
}

/*

Time and Space Complexity:
----------------------------
Time Complexity:
The time complexity of the code is primarily determined by the single loop that iterates over the input string s. Inside the loop, all operations (condition checking, integer conversion, and arithmetic operations) are executed with constant time complexity O(1). Since the loop runs for each character in the input string, the time complexity is directly proportional to the length of the string. Hence, the time complexity of the code can be given as O(n), where n is the length of the input string s.

Space Complexity:
The space complexity of the code relates to the amount of memory used in relation to the input size. In this code, we use only a fixed number of variables f, g, and h, which are independent of the input size. This leads to a constant space usage, regardless of the length of the input string. Consequently, the space complexity of the algorithm is O(1), indicating that it requires constant space.

*/