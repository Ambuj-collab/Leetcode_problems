/*

1. Intuition:
---------------
The key idea is to break the expression down recursively at every operator (+, -, *). By treating each operator as a potential point to divide the expression into two parts, we calculate all possible results from both sides, then combine them. This recursive exploration generates all possible ways to parenthesize the expression.

Imagine an expression as a tree, where operators are the nodes and numbers are the leaves. Each split of the tree is a new way of grouping the numbers and operators, which yields a different result. Our goal is to explore every possible tree and return all results.

2. Approach:
--------------
a) Divide & Conquer:
	i) For each operator (+, -, *) in the expression, split the string into two parts: the left and right sub-expressions.
	ii) Recursively compute the results of both the left and right sub-expressions.
b) Combine:
	i) Once you have the results of the left and right sub-expressions, combine them based on the current operator.
	ii) For each result in the left part and each result in the right part, compute a new result using the operator.
c) Base Case:
	If the expression contains no operators (i.e., just a number), return that number as a result.

*/

import java.util.*;

class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < expression.length(); ++i) {
            char oper = expression.charAt(i);
            if (oper == '+' || oper == '-' || oper == '*') {
                List<Integer> s1 = diffWaysToCompute(expression.substring(0, i));
                List<Integer> s2 = diffWaysToCompute(expression.substring(i + 1));
                for (int a : s1) {
                    for (int b : s2) {
                        if (oper == '+')
                            res.add(a + b);
                        else if (oper == '-')
                            res.add(a - b);
                        else if (oper == '*')
                            res.add(a * b);
                    }
                }
            }
        }
        if (res.isEmpty())
            res.add(Integer.parseInt(expression));
        return res;
    }
}

--------------------------------------------------------------------------------------------------
													OR
--------------------------------------------------------------------------------------------------

class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' ||
                    expression.charAt(i) == '+') {
                String part1 = expression.substring(0, i);
                String part2 = expression.substring(i + 1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 : part1Ret) {
                    for (Integer p2 : part2Ret) {
                        int c = 0;
                        switch (expression.charAt(i)) {
                            case '+':
                                c = p1 + p2;
                                break;
                            case '-':
                                c = p1 - p2;
                                break;
                            case '*':
                                c = p1 * p2;
                                break;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(expression));
        }
        return ret;
    }
}


/*

Time and Space Complexity:
=============================

Time Complexity:
------------------
The expression has n characters, where each operator can split the expression into two parts. The number of ways to split an expression grows exponentially (like the Catalan number sequence). This results in O(2^n) recursive calls in the worst case.

Space Complexity:
-------------------
The space complexity depends on the depth of the recursion tree, which can go as deep as n. The additional space used to store intermediate results (in the res array) also adds up. So, the space complexity is O(2^n), primarily due to the recursive stack and storing results.

*/