class Solution {
    public String intToRoman(int num) {
        // Define arrays for Roman numeral characters and their corresponding values.
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
      
        // Initialize a StringBuilder to build the Roman numeral string.
        StringBuilder romanString = new StringBuilder();
      
        // Iterate over the Roman numerals and values to construct the Roman numeral.
        for (int i = 0; i < romanNumerals.length; i++) {
            while (num >= values[i]) { // While the number is greater than the current value
                num -= values[i]; // Subtract the value from the number
                romanString.append(romanNumerals[i]); // Append the corresponding Roman numeral to the string
            }
        }

        // Return the constructed Roman numeral string.
        return romanString.toString();
    }
}

/*

Time and Space Complexity:
----------------------------
The time complexity of the given Java function intToRoman is O(1), because the number of operations performed does not depend on the size of the input number but on the fixed set of Roman numeral symbols. Since Roman numerals have a finite number of symbols (cs) that are considered to provide the greatest value in the Roman numeral system, the loop within the function will iterate at most a constant number of times, which is equivalent to the number of symbols in the set cs (13 symbols in this case).

The space complexity of the function is also O(1) for similar reasons. The amount of memory used by the program does not increase proportionally to the input num. It rather depends on the size of the two constant arrays cs(i.e., romanNumerals) and vs(i.e., values), and the list ans. Regardless of the size of num, ans will have at most a constant number of elements related to the number of possible Roman numeral symbols. Therefore, the space used by the list ans will not grow arbitrarily with the input value.

To summarize, both the time complexity and the space complexity of the function are constant, represented as:

Time Complexity: O(1)
Space Complexity: O(1)

*/
