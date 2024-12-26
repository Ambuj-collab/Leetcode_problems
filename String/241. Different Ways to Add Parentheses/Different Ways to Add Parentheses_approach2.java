import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    HashMap<String, List<Integer>> m = new HashMap<>();

    public List<Integer> solveTopDown(String s) {
        if (m.containsKey(s))
            return m.get(s);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = solveTopDown(s.substring(0, i));
                List<Integer> right = solveTopDown(s.substring(i + 1));

                for (int l : left) {
                    for (int r : right) {
                        if (c == '+')
                            ans.add(l + r);
                        else if (c == '-')
                            ans.add(l - r);
                        else
                            ans.add(l * r);
                    }
                }
            }
        }
        if (ans.isEmpty())
            ans.add(Integer.parseInt(s));

        m.put(s, ans);
        return ans;
    }

    public List<Integer> diffWaysToCompute(String expression) {
        return solveTopDown(expression);
    }
}

--------------------------------------------------------------------------------------------------
											OR
--------------------------------------------------------------------------------------------------
/*

Intuition and Approach:
-------------------------
The key to solving this problem is breaking down the string into smaller parts recursively by splitting at every operator (+, -, *). For each operator, we evaluate all possible values from the left and right substrings, and then combine them based on the operator.

This is essentially a divide-and-conquer problem, where at each step we break the expression into smaller sub-expressions, compute the results for both parts, and then combine the results based on the operator between them.

We also make use of memoization to store results for sub-expressions we've already computed, which helps avoid redundant calculations and speeds up the solution.

Algorithm:
------------
1) Base Case: If the expression consists only of a single number, we return that number as the only result.

2) Recursive Step: For each character in the string, if it's an operator, we:
	a) Split the expression into two parts: left and right.
	b) Recursively calculate all possible values for the left and right parts.
	c) Combine the results from the left and right parts using the current operator.

3) Memoization: We store the results of each sub-expression in a map, so that if the same sub-expression is encountered again, we can retrieve the result in constant time instead of recalculating it.

*/

import java.util.*;

class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        Map<String, List<Integer>> mem = new HashMap<>(); // Initialize the memoization map
        return ways(expression, mem);
    }

    private List<Integer> ways(String s, Map<String, List<Integer>> mem) {
        // Check if the result for the current string is already memoized
        if (mem.containsKey(s))
            return mem.get(s);

        List<Integer> ans = new ArrayList<>();

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            // If the character is an operator
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*') {
                // Recursively compute results for left and right parts
                List<Integer> left = ways(s.substring(0, i), mem);
                List<Integer> right = ways(s.substring(i + 1), mem);
                
                // Combine results from left and right based on the operator
                for (int a : left) {
                    for (int b : right) {
                        if (s.charAt(i) == '+')
                            ans.add(a + b);
                        else if (s.charAt(i) == '-')
                            ans.add(a - b);
                        else if (s.charAt(i) == '*')
                            ans.add(a * b);
                    }
                }
            }
        }

        // If there are no operators, it means the current string is a number
        if (ans.isEmpty())
            ans.add(Integer.parseInt(s));

        // Memoize the result for the current string
        mem.put(s, ans);
        return ans;
    }
}


/*

Time and Space Complexity:
============

Time Complexity:
------------------
O(2^n): The complexity arises from the number of ways to split the expression at operators and the recursive nature of the solution.

Space Complexity:
-------------------
O(n): Space for storing intermediate results in the memoization table and recursive stack depth.

*/