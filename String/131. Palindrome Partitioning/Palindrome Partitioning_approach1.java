/*

Problem Explaination:
-----------------------
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome is a string that reads the same backward as forward.

Input:
--------
A string s consisting of lowercase English letters.

Output:
---------
A list of lists containing all possible palindrome partitioning of s. Each inner list represents a partitioning where the substrings in it are palindromes.

Methods To Solve This Problem:
--------------------------------
I'll be covering two different methods to solve this problem:

1) Backtracking
2) Dynamic Programming

Approach 1 - Backtracking:
============================

Thinking Behind the Solution:
-------------------------------
The backtracking approach explores all possible ways to partition the string and checks if each partitioned substring is a palindrome. The key idea is to use a recursive function to try every possible partition and backtrack to explore other possibilities when a valid partition is found. This method ensures that all possible partitions are considered, but it may be less efficient due to redundant checks.

Approach:
-----------
1) Palindrome Check: Create a helper function is_palindrome to check if a given substring is a palindrome.
2) Recursive Backtracking:
Define a recursive function backtrack(start, path):
	a) If start reaches the end of the string s, append the current 'path' to the 'result' list.
	b) Iterate over all possible end positions from start + 1 to the length of the string.
	c) For each end position, check if the substring s[start:end] is a palindrome.
	d) If it is, recursively call backtrack with the updated start position (end) and the current path plus the new palindrome substring.

3) Initialization: Initialize an empty list 'result' to store all valid partitions. Start the backtracking process from the beginning of the string with an empty path.
4) Return Result: Return the result list containing all possible palindrome partitions.

*/
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        // If we've reached the end of the string, add the current partition to the
        // result list
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        // Explore all possible partitions
        for (int end = start + 1; end <= s.length(); end++) {
            // If the current substring is a palindrome, add it to the current path
            if (isPalindrome(s, start, end - 1)) {
                path.add(s.substring(start, end));
                // Recur to find other partitions
                backtrack(s, end, path, result);
                // Backtrack to explore other partitions
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        // Check if the substring s[left:right+1] is a palindrome
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
