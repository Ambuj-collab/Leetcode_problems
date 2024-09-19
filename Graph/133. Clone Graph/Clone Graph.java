/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Integer, Node> mp = new HashMap<>();
        return getClonedGraph(node, mp);
    }

    private Node getClonedGraph(Node node, Map<Integer, Node> mp) {
        Node root = new Node(node.val);
        mp.put(node.val, root);
        for (Node child : node.neighbors) {
            Node childClone = null;
            if (mp.get(child.val) == null) {
                childClone = getClonedGraph(child, mp);
            } else {
                childClone = mp.get(child.val);
            }
            root.neighbors.add(childClone);
        }
        return root;
    }
}