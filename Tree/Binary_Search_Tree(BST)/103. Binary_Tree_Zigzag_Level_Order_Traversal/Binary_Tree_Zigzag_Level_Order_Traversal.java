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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Stack<TreeNode> leftToRight = new Stack<>();
        Stack<TreeNode> rightToLeft = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();

        leftToRight.add(root);
        while (!leftToRight.isEmpty() || !rightToLeft.isEmpty()) {
            TreeNode temp = null;
            List<Integer> ls = new ArrayList<>();

            if (rightToLeft.isEmpty()) {
                int size = leftToRight.size();
                for (int i = 0; i < size; i++) {
                    temp = leftToRight.pop();
                    ls.add(temp.val);

                    if (temp.left != null)
                        rightToLeft.add(temp.left);
                    if (temp.right != null)
                        rightToLeft.add(temp.right);
                }

            } else if (leftToRight.isEmpty()) {
                int size = rightToLeft.size();
                for (int i = 0; i < size; i++) {
                    temp = rightToLeft.pop();
                    ls.add(temp.val);

                    if (temp.right != null)
                        leftToRight.add(temp.right);
                    if (temp.left != null)
                        leftToRight.add(temp.left);
                }
            }

            res.add(ls);
        }
        return res;
    }
}