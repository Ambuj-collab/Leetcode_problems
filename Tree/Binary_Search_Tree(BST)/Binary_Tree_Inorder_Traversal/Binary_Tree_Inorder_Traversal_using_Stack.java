class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        // Initialize an ArrayList to store the result (in-order traversal)
        List<Integer> res = new ArrayList<>();
        
        // Initialize a Stack for iterative traversal
        Stack<TreeNode> stack = new Stack<>();

        // Loop until either the current node is not null or the stack is not empty
        while (root != null || !stack.isEmpty()) {
            // Traverse to the leftmost node and push each encountered node onto the stack
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // Pop the last node from the stack (most recently left-visited node)
            root = stack.pop();
            
            // Append the value of the popped node to the result list
            res.add(root.val);
            
            // Move to the right subtree to continue the in-order traversal
            root = root.right;
        }

        // Return the final result list
        return res;        
    }
}