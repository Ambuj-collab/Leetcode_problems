class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the graph of courses
        List<Integer>[] graph = new List[numCourses];
        Arrays.setAll(graph, x -> new ArrayList<>());

        // Create an array to keep track of the number of incoming edges (in-degrees) to
        // each node (course)
        int[] inDegree = new int[numCourses];

        // Build the graph and update in-degree count
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph[prerequisiteCourse].add(course);
            ++inDegree[course];
        }

        // Queue for courses with no prerequisites (in-degree of 0)
        Deque<Integer> queue = new ArrayDeque<>();

        // Add all courses with no incoming edges to the queue
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Array for storing the course ordering
        int[] order = new int[numCourses];
        int courseCount = 0;

        // Perform BFS on the graph
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            order[courseCount++] = currentCourse;

            // Go through all the adjacent courses and reduce their in-degrees
            for (int adjacentCourse : graph[currentCourse]) {
                if (--inDegree[adjacentCourse] == 0) {
                    // If in-degree becomes 0, add it to the queue
                    queue.offer(adjacentCourse);
                }
            }
        }

        // If we have added all courses in order, return the order.
        // Otherwise, there is a cycle and we return an empty array.
        return courseCount == numCourses ? order : new int[0];
    }
}