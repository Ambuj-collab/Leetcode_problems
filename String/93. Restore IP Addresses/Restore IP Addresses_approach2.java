/*

Approach and Explanation:
---------------------------
1) This code defines a class called "Solution" that contains a single public method called "restoreIpAddresses". This method takes a single input, a string called "s", and returns a list of strings.

2) The method first initializes two lists, "addresses" and "address". "addresses" will ultimately hold all of the valid IP addresses that can be created from the input string "s", and "address" is a temporary list used to store each individual segment of an IP address (i.e., the four numbers that make up an IP address) as the code generates them.

3) The method then calls a private helper function called "rec". This function takes four inputs: the input string "s", an integer "i" that keeps track of the current position in the string, the "address" list, and the "addresses" list.

4) The function starts by checking if the "address" list has a size of 4. If it does, this means that all four segments of the IP address have been generated and it checks if i is equal to the length of the input string. If both of these conditions are true, it means that all of the characters in the input string have been used to generate a valid IP address, so the function converts the "address" list to a string and adds it to the "addresses" list.

5) If the "address" list does not have a size of 4, the function enters a loop. The loop iterates from i+1 to i+3 (or until it reaches the end of the input string) and for each iteration, it creates a new string called "nextInt" which is a substring of the input string "s" starting from index i and ending at index j. Then it checks if the integer value of nextInt is less than or equal to 255 and either nextInt is equal to "0" or it doesn't start with "0", then it adds this nextInt to the "address" list and recursively calls the function with updated inputs. After this call, it removes the last element of "address" list.

6) This process continues until the function generates all possible valid IP addresses from the input string. The final list of valid IP addresses is returned by the "restoreIpAddresses" method.

*/

class Solution {
    public static List<String> restoreIpAddresses(String s) {
        // Initialize the list to hold all valid IP addresses
        List<String> addresses = new ArrayList<>();
        // Initialize the list to hold the current IP address being built
        List<String> address = new ArrayList<>();
        // Call the recursive helper function to generate all possible IP addresses
        rec(s, 0, address, addresses);
        // Return the list of all valid IP addresses
        return addresses;
    }

    private static void rec(String s, int i, List<String> address, List<String> addresses) {
        // If the current address has 4 segments, check if we have reached the end of
        // the input string
        if (address.size() == 4) {
            if (i == s.length()) {
                // If we have reached the end of the input string, add the current address to
                // the list of valid addresses
                addresses.add(String.join(".", address));
            }
        } else {
            // Try all possible next segments for the current address
            for (int j = i + 1; j <= i + 3 && j <= s.length(); j++) {
                String nextInt = s.substring(i, j);
                // Check if the next segment is valid (between 0 and 255, and not starting with
                // 0 unless it is 0)
                if (Integer.parseInt(nextInt) <= 255 && (nextInt.equals("0") || !nextInt.startsWith("0"))) {
                    // Add the next segment to the current address
                    address.add(nextInt);
                    // Recursively call the function to generate the next segment
                    rec(s, j, address, addresses);
                    // Remove the last segment from the current address
                    address.remove(address.size() - 1);
                }
            }
        }
    }
}


--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

class Solution {
    private final int LOWEST = 0, BIGGEST = 255, MAX_LENGTH = 3, DIGITS = 4;
    private final Set<String> ipAddresses = new HashSet<>();

    public List<String> restoreIpAddresses(String s) {
        slv(0, new StringBuilder(), DIGITS, s);
        return new ArrayList<>(ipAddresses);
    }

    private void slv(int index, StringBuilder current, int numDigits, String str) {
		// 'current' will contain dot(i.e., '.') also
		/*
		if ((str.length() - (current.length() - (DIGITS - numDigits))) > numDigits * MAX_LENGTH)
            return;
		*/
        if ((str.length() - current.length() + (DIGITS - numDigits)) > numDigits * MAX_LENGTH)
            return;
		
        if (numDigits == 0) {
            ipAddresses.add(current.substring(0, current.length() - 1));
            return;
        }
        int count = 1;
        for (int i = index; i < Math.min(str.length(), index + MAX_LENGTH); i++) {
            String part = str.substring(index, i + 1);
            if (isValidDigit(part)) {
                current.append(part).append(".");
                slv(i + 1, current, numDigits - 1, str);
                current.delete(current.length() - count - 1, current.length());
            }
            count++;
        }
    }

    private boolean isValidDigit(String part) {
        if (part.length() > 1 && part.startsWith("0"))
            return false;
        int value = Integer.parseInt(part);
        return value >= LOWEST && value <= BIGGEST;
    }
}


--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> addresses = new ArrayList<>();
        // if the length of the string is longer than 12 which can not form a valid IP
        if (s.length() > 12 || s.length() == 0)
            return addresses;
        backtracking(addresses, new ArrayList<String>(), s, 0);
        return addresses;
    }

    private void backtracking(List<String> addresses, List<String> temp, String s, int start) {
        // once reach to end of the string and the have four num inside the list
        // then join these four nums and add to result list
        if (start == s.length() && temp.size() == 4) {
            addresses.add(String.join(".", temp));
            return;
        }

        // each number is between 0 and 255
        // which is 1 digit to 3 digit
        // so we have three different choices for each number
        // 1. substring from start to start+1
        // 2. substring from start to start+2
        // 3. substring from start to start+3
        for (int i = 1; i <= 3; i++) {
            if (start + i > s.length())
                return;
            String address = s.substring(start, start + i);
            // check the address validation
            if (validAddress(address)) {
                // add valid num into List
                // start a new backtracking
                temp.add(address);
                backtracking(addresses, temp, s, start + i);
                temp.remove(temp.size() - 1);

            }
        }
    }

    // To valid the given address
    // each integer is between 0 and 255 and can not start with 0
    private boolean validAddress(String address) {
        return !((address.charAt(0) == '0' && address.length() > 1) || Integer.parseInt(address) > 255);
    }
}
