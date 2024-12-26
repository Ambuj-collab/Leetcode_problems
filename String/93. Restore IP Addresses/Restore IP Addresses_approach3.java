// Fastest solution: Beats 99.75, Runtime: 1 ms

/*
The provided solution uses a depth-first search (DFS) recursive algorithm to explore all possible combinations of the digits in the string that could result in a valid IP address.
*/
class Solution {
    private int stringLength; // Length of the input string
    private String inputString; // The input string representing the digits of the IP address
    private List<String> validIPAddresses = new ArrayList<>(); // List to hold the valid IP addresses
    private List<String> currentSegment = new ArrayList<>(); // List to hold the current segments of the IP address
                                                             // being constructed

    // Public method to restore IP addresses from the given string.
    public List<String> restoreIpAddresses(String s) {
        stringLength = s.length();
        inputString = s;
        backtrack(0); // Begin the depth-first search (DFS) from the first character of the string
        return validIPAddresses;
    }

    // Helper method to perform a DFS to build all valid IP addresses.
    private void backtrack(int index) {
        // Check if we have processed the entire string and we have exactly 4 segments
        if (index >= stringLength && currentSegment.size() == 4) {
            // Join the segments and add the resulting IP address to the list
            validIPAddresses.add(String.join(".", currentSegment));
            return;
        }
        // If we've processed the entire string or have more than 4 segments, backtrack
        if (index >= stringLength || currentSegment.size() >= 4) {
            return;
        }

        // Initialize an integer to store the numeric value of current segment
        int segmentValue = 0;
        // Consider 1 to 3 digit long segments (as an IP segment ranges from 0 to 255)
        for (int j = index; j < Math.min(index + 3, stringLength); ++j) {
            segmentValue = segmentValue * 10 + inputString.charAt(j) - '0'; // Convert current segment to integer

            // Check for leading zeroes and if segmentValue is greater than 255
            if (segmentValue > 255 || (inputString.charAt(index) == '0' && index != j)) {
                break; // If any of those checks fail, stop exploring further and backtrack
            }

            // Add the current segment to our list and continue the search
            currentSegment.add(inputString.substring(index, j + 1));
            backtrack(j + 1); // Explore further by calling backtrack recursively
            currentSegment.remove(currentSegment.size() - 1); // Remove the last added segment to backtrack
        }
    }
}

/*

Time and Space Complexity:
---------------------------
The given Python code defines a method to restore possible IP addresses from a string by implementing a depth-first search (DFS) algorithm.

Time Complexity
The time complexity of the algorithm can be considered as O(1) in terms of the input string's length, since an IP address consists of 4 parts, and each part can have a maximum of 3 digits. The check function is called at each step of the DFS and runs in O(1) time since it operates on a constant size substring (at most 3 characters).

The DFS function will attempt to place a dot after every 1 to 3 digits, but since IP addresses are fixed length (4 parts of at most 3 digits each), the maximum depth of the recursive call stack will be 4, and there will be at most 3^4 possible combinations to check (3 choices at each of the 4 levels of the decision tree). This results in a total of 81 iterations in the worst case, each taking constant time.

Thus, the overall time complexity is O(1) since the size of the input is not a factor beyond a certain length (the length must be between 4 and 12 for a valid IP address).

Space Complexity
The space complexity of the solution mainly depends on the size of the recursive call stack and the space used to store the intermediate and final solutions. As previously mentioned, the recursive call stack will have at most 4 levels due to the nature of IP addresses. Plus, a single path t in the recursion tree is a list that can have at most 4 strings, each up to 3 characters long.

The list ans will contain all the valid IP addresses we find. In the worst-case scenario, every partitioning will lead to a valid IP address, but this is highly unlikely. However, if we consider every single character as a digit and each digit forms a valid part of an IP address, the maximum number of valid IP addresses would be 3^4 (though actually it would be less due to the leading zero and value >255 restrictions).

Hence, the space complexity for the output list is O(1), and the overall space complexity of the algorithm including the recursive call stack and the temporary list t is also O(1), since the problem's constraints limit the input size and, consequently, the recursion depth and output size.

*/

/*

Notes:
--------
In the context of the Solution class's restoreIpAddresses method, the condition chosen as 'index >= stringLength' instead of 'index == stringLength' is used to check if the entire input string has been processed because it is a safeguard against any cases where the index might exceed the string length due to some unintended behavior in the code or a logical error.

While in practice, if the code is correct, index should be exactly equal to stringLength when the end of the string is reached, using >= ensures that even if for some reason the index advances beyond the string length (which shouldn't happen in a correct implementation), the check will still catch the situation where we have gone past the end of the input string.

This is a defensive programming practice where the condition is made slightly more robust to safeguard against potential bugs. It ensures that as soon as the end of the string is reached or exceeded, the function will recognize that it should stop processing further. It doesn't rely on the index incrementing in perfect lockstep with the string length, thus providing a more generalized and safer condition to prevent over-indexing, which can lead to out-of-bounds errors or infinite loops.

*/