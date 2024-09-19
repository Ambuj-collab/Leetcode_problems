/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean valid(TreeNode node, long minimum, long maximum) {
        if (node == null)
            return true;

        if (!(node.val > minimum && node.val < maximum))
            return false;

        return valid(node.left, minimum, node.val) && valid(node.right, node.val, maximum);
    }
}

/*
        10
       /  \
      8    15
          /  \
         12  18
    
    negative infinity < root < infinity

    10 → -i < 10 < i
    8 → -i <  8 < 10
    15 → 10 < 15 < i
    12 → 10 < 12 < 15
    18 → 15 < 18 < i

    i is infinity
*/
