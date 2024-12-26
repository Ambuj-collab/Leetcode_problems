// We define a recursive function getDiffWays where, getDiffWays(i, j) returns us number of ways to evaluate expression[i...j].

class Solution {
    private boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*');
    }

    private List<Integer> getDiffWays(int i, int j, String expression) {
        int len = j - i + 1;
        List<Integer> res = new ArrayList<>();

        // If length of the substring is 1 or 2
        // we encounter our base case i.e. a number found.
        if (len <= 2) {
            res.add(Integer.parseInt(expression.substring(i, i + len)));
            return res;
        }

        // If it is not a number then it is an expression
        // now we try to evaluate every opertor present in it
        for (int ind = i; ind <= j; ind++) {
            if (isOperator(expression.charAt(ind))) {
                char op = expression.charAt(ind);

                // if char at ind is operator 
                // get all results for its left and right substring using recursion
                List<Integer> left = getDiffWays(i, ind - 1, expression);
                List<Integer> right = getDiffWays(ind + 1, j, expression);

                // try all options for left & right operand
                // and push all results to the answer
                for (int l : left) {
                    for (int r : right) {
                        if (op == '+') {
                            res.add(l + r);
                        } else if (op == '-') {
                            res.add(l - r);
                        } else if (op == '*') {
                            res.add(l * r);
                        }
                    }
                }
            }
        }
        return res;
    }

    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        return getDiffWays(0, n - 1, expression);
    }
}


--------------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------------
/*

If we dry run a example, we observe overalapping subproblems that gets calculated again & again. We can avoid this by introducing a 3D DP array / cache & storing the results we calculate so that we don't need to process same stuff again & again.

*/

class Solution {
    private boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*');
    }

    private List<Integer> getDiffWays(int i, int j, Map<String, List<Integer>> dp, String expression) {
        String key = i + "-" + j;

        // Return cached result if already calculated
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        // If length of the substring is 1 or 2
        // we encounter our base case i.e. a number found.
        int len = j - i + 1;
        if (len <= 2) {
            List<Integer> result = new ArrayList<>();
            result.add(Integer.parseInt(expression.substring(i, j + 1)));
            dp.put(key, result);
            return result;
        }

        // If it is not a number then it is an expression
        // now we try to evaluate every operator present in it
        List<Integer> res = new ArrayList<>();
        for (int ind = i; ind <= j; ind++) {
            if (isOperator(expression.charAt(ind))) {
                char op = expression.charAt(ind);

                // if char at ind is an operator 
                // get all results for its left and right substring using recursion
                List<Integer> left = getDiffWays(i, ind - 1, dp, expression);
                List<Integer> right = getDiffWays(ind + 1, j, dp, expression);

                // try all options for left & right operand
                // and push all results to the answer
                for (int l : left) {
                    for (int r : right) {
                        if (op == '+') {
                            res.add(l + r);
                        } else if (op == '-') {
                            res.add(l - r);
                        } else if (op == '*') {
                            res.add(l * r);
                        }
                    }
                }
            }
        }
        dp.put(key, res);
        return res;
    }

    public List<Integer> diffWaysToCompute(String expression) {
        Map<String, List<Integer>> dp = new HashMap<>();
        return getDiffWays(0, expression.length() - 1, dp, expression);
    }
}


-------------------------------------------------------------------------------------------------
													OR
-------------------------------------------------------------------------------------------------
/*

Tabulation approach:
------------------------
We can eliminate the recursion stack space used in memoization solution by just calculating result for every valid expression substring in bottom up manner i.e. Tabulation.

In memoization, every substring which gets called recursively is valid but in this solution we need to check if given substring is a valid arithemetic expression or not.

*/

import java.util.*;

class Solution {
    private boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*');
    }

    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        List<Integer>[][] dp = new ArrayList[n][n];

        // Function to check if given substring of expression
        // is a valid expression
        BiFunction<Integer, Integer, Boolean> isValidExpression = (i, j) -> 
            (i == 0 || isOperator(expression.charAt(i - 1))) && 
            (j == n - 1 || isOperator(expression.charAt(j + 1)));

        // Get answer for all single digit numbers
        for (int i = 0; i < n; i++) {
            if (isValidExpression.apply(i, i)) {
                dp[i][i] = new ArrayList<>();
                dp[i][i].add(Integer.parseInt(expression.substring(i, i + 1)));
            }
        }

        // Get answer for all 2 digit numbers
        for (int i = 0, j = 1; j < n; i++, j++) {
            if (isValidExpression.apply(i, j)) {
                dp[i][j] = new ArrayList<>();
                dp[i][j].add(Integer.parseInt(expression.substring(i, i + 2)));
            }
        }

        // Get answer for all valid expression substrings in bottom up manner
        for (int len = 3; len <= n; len++) {
            for (int i = 0, j = i + len - 1; j < n; i++, j++) {
                if (!isValidExpression.apply(i, j)) {
                    continue;
                }

                dp[i][j] = new ArrayList<>();
                // Try to evaluate every operator
                for (int ind = i; ind <= j; ind++) {
                    if (isOperator(expression.charAt(ind))) {
                        char op = expression.charAt(ind);

                        // If char at ind is operator, get all results for its left and right substrings
                        List<Integer> left = dp[i][ind - 1];
                        List<Integer> right = dp[ind + 1][j];

                        // Try all options for left & right operands and add all results to the answer
                        for (int l : left) {
                            for (int r : right) {
                                if (op == '+') {
                                    dp[i][j].add(l + r);
                                } else if (op == '-') {
                                    dp[i][j].add(l - r);
                                } else if (op == '*') {
                                    dp[i][j].add(l * r);
                                }
                            }
                        }
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
