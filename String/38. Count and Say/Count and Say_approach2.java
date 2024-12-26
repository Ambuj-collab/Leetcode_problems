class Solution {
    public String countAndSay(int n) {
        // The initial string is "1".
        String current = "1";

        // Iterate until we reach the requested sequence iteration (n).
        while (--n > 0) {
            // StringBuilder to build the next sequence.
            StringBuilder nextSequence = new StringBuilder();

            // Loop through the characters of the current sequence.
            for (int i = 0; i < current.length();) {
                int count = 0; // Initialize a counter for the character grouping.
                char ch = current.charAt(i); // Current character to be counted.

                // Count consecutive similar characters.
                while (i < current.length() && current.charAt(i) == ch) {
                    i++;
                    count++;
                }

                // Append the count and the character to the next sequence.
                nextSequence.append(count).append(ch);
            }

            // Prepare for the next iteration by updating the current sequence.
            current = nextSequence.toString();
        }

        // Return the final sequence after 'n' iterations.
        return current;
    }
}
