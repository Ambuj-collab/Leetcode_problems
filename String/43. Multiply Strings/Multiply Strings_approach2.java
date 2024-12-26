class Solution {
    public String multiply(String num1, String num2) {
        // If either number is 0, the product will be 0.
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
      
        // Get lengths of both numbers.
        int length1 = num1.length(), length2 = num2.length();
      
        // Initialize an array to store the product of each digit multiplication.
        int[] productArray = new int[length1 + length2];

        // Loop over each digit in num1 and num2 and multiply them.
        for (int i = length1 - 1; i >= 0; --i) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = length2 - 1; j >= 0; --j) {
                int digit2 = num2.charAt(j) - '0';
              
                // Add the product of the two digits to the corresponding position.
                productArray[i + j + 1] += digit1 * digit2;
            }
        }
      
        // Normalize the productArray so that each position is a single digit.
        for (int i = productArray.length - 1; i > 0; --i) {
            productArray[i - 1] += productArray[i] / 10; // Carry over the tens to the next left cell.
            productArray[i] %= 10; // Keep the units in the current cell.
        }
      
        // Skip the leading 0 in the product array if it exists.
        int startIndex = productArray[0] == 0 ? 1 : 0;
      
        // Convert the product array into a string.
        StringBuilder product = new StringBuilder();
        for (int i = startIndex; i < productArray.length; ++i) {
            product.append(productArray[i]);
        }
        return product.toString();
    }
}
