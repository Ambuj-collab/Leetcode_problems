class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // List for storing the result which are the root nodes of the MHTs
        List<Integer> minHeightTrees = new ArrayList<>();

        // Base case: when there's only one node, return it as the root
        if (n == 1) {
            minHeightTrees.add(0);
            return minHeightTrees;
        }

        // Initialize the adjacency list
        List<Integer>[] adjacencyList = new List[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Initialize the degree array to keep track of the degree of each node
        int[] degrees = new int[n];

        // Build the graph by populating the adjacency list and degree array
        for (int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];

            adjacencyList[nodeA].add(nodeB);
            adjacencyList[nodeB].add(nodeA);

            degrees[nodeA]++;
            degrees[nodeB]++;
        }

        // Queue for holding the leaves nodes
        Queue<Integer> leavesQueue = new LinkedList<>();

        // Add initial leaves to queue - those are nodes with degree 1
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                leavesQueue.offer(i);
            }
        }

        // Process leaves until there are potentially 2 or less nodes left
        while (!leavesQueue.isEmpty()) {
            // Clear the previous result
            minHeightTrees.clear();

            // Number of leaves at the current level
            int leavesCount = leavesQueue.size();

            // Process each leaf node
            for (int i = 0; i < leavesCount; i++) {
                int leafNode = leavesQueue.poll();

                // Add the leaf node to the result
                minHeightTrees.add(leafNode);

                // Visit all neighboring nodes
                for (int neighbor : adjacencyList[leafNode]) {
                    // Decrease the degree as we are removing the leaf node
                    degrees[neighbor]--;
                    // If this makes the neighbor a new leaf, add it to queue
                    if (degrees[neighbor] == 1) {
                        leavesQueue.offer(neighbor);
                    }
                }
            }
        }

        // Returns the list of rooted trees with minimal height
        return minHeightTrees;
    }
}