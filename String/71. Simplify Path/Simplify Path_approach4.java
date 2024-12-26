class Solution {
    public String simplifyPath(String path) {
        // Use a deque as a stack to hold the directory names.
        Deque<String> stack = new ArrayDeque<>();

        // Split the path by "/" and iterate over the segments.
        for (String segment : path.split("/")) {
            // If the segment is empty or a single ".", just ignore it.
            if (segment.isEmpty() || ".".equals(segment)) {
                continue;
            }
            // If the segment is "..", pop an element from the stack if available.
            if ("..".equals(segment)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else {
                // Push the directory name onto the stack.
                stack.offerLast(segment);
            }
        }
      
        // Join all the elements in the stack with "/", prepended by a "/" to form the simplified path.
        String simplifiedPath = "/" + String.join("/", stack);
      
        // Return the simplified absolute path.
        return simplifiedPath;
    }
}
