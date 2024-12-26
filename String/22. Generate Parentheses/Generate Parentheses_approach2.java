class Solution {
    // List to hold all the valid parentheses combinations
    private List<String> answers = new ArrayList<>();
    // The number of pairs of parentheses
    private int maxPairs;

    /**
     * Generates all combinations of n pairs of well-formed parentheses.
     *
     * @param n the number of pairs of parentheses
     * @return a list of all possible combinations of n pairs of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        this.maxPairs = n;
        // Start the depth-first search with initial values for open and close parentheses count
        generate(0, 0, "");
        return answers;
    }

    /**
     * Helper method to generate the parentheses using depth-first search.
     *
     * @param openCount  the current number of open parentheses
     * @param closeCount the current number of close parentheses
     * @param currentString the current combination of parentheses being built
     */
    private void generate(int openCount, int closeCount, String currentString) {
        // Check if the current counts of open or close parentheses exceed maxPairs or if closeCount exceeds openCount
        if (openCount > maxPairs || closeCount > maxPairs || openCount < closeCount) {
            // The current combination is invalid, backtrack from this path
            return;
        }
        // Check if the current combination is a valid complete set of parentheses
        if (openCount == maxPairs && closeCount == maxPairs) {
            // Add the valid combination to the list of answers
            answers.add(currentString);
            return;
        }
        // Explore the possibility of adding an open parenthesis
        generate(openCount + 1, closeCount, currentString + "(");
        // Explore the possibility of adding a close parenthesis
        generate(openCount, closeCount + 1, currentString + ")");
    }
}
