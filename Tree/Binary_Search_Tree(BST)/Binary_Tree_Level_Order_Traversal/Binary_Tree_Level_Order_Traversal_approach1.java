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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            getLevelOrder(root, qu, res);
        }
        return res;
    }

    private void getLevelOrder(TreeNode root, Queue<TreeNode> qu, List<List<Integer>> res) {
        qu.add(root);
        qu.add(null);
        List<Integer> temp = new ArrayList<>();
        while (qu.size() > 0) {
            TreeNode top = qu.poll();
            if (top == null) {
                if (qu.size() > 0) {
                    res.add(new ArrayList<>(temp));
                    qu.add(null);
                    temp.clear();
                }
                continue;
            }
            temp.add(top.val);

            if (top.left != null)
                qu.add(top.left);
            if (top.right != null)
                qu.add(top.right);
        }
        res.add(temp);
    }

}