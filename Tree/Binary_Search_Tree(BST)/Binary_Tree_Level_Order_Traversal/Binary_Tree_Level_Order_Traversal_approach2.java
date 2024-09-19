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
        // Check if the root is null
        if (root == null) {
            return new ArrayList<>(); // Return an empty list if the tree is empty
        }

        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        List<List<Integer>> traversal = new ArrayList<>();

        queue.add(root); // Add the root node to the queue

        // Start level-order traversal
        while (!queue.isEmpty()) {
            List<Integer> currLevel = new ArrayList<>();
            int currSize = queue.size(); // Get the number of nodes at the current level

            // Process all nodes at the current level
            for (int i = 0; i < currSize; i++) {
                TreeNode currNode = queue.poll(); // Get the next node

                // Add its value to the current level's list
                currLevel.add(currNode.val);

                // Add the children to the queue if they are not null
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            // Add the current level's list to the result
            traversal.add(currLevel);
        }

        return traversal;
    }
}