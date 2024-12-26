class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Dimensions of the 2D matrix
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        // Direction vectors for right, down, left, and up
        int[] directionRow = { 0, 1, 0, -1 };
        int[] directionCol = { 1, 0, -1, 0 };
        // Starting point
        int row = 0, col = 0;
        // Index for the direction vectors
        int directionIndex = 0;
        // List to hold the spiral order
        List<Integer> result = new ArrayList<>();
        // 2D array to keep track of visited cells
        boolean[][] visited = new boolean[rowCount][colCount];

        for (int h = rowCount * colCount; h > 0; --h) {
            // Add the current element to the result
            result.add(matrix[row][col]);
            // Mark the current cell as visited
            visited[row][col] = true;
            // Compute the next cell position
            int nextRow = row + directionRow[directionIndex];
            int nextCol = col + directionCol[directionIndex];
            // Check if the next cell is out of bounds or visited
            if (nextRow < 0 || nextRow >= rowCount || nextCol < 0 || nextCol >= colCount || visited[nextRow][nextCol]) {
                // Update the direction index to turn right in the spiral order
                directionIndex = (directionIndex + 1) % 4;
                // Recompute the next cell using the new direction
                nextRow = row + directionRow[directionIndex];
                nextCol = col + directionCol[directionIndex];
            }
            // Move to the next cell
            row = nextRow;
            col = nextCol;
        }
        return result;
    }
}

/*

Time and Space Complexity:
----------------------------
The time complexity of the function spiralOrder is O(m * n) where m is the number of rows and n is the number of columns in the input matrix. This is because the function iterates over every element in the matrix exactly once.

The space complexity of the function, however, is not O(1) as stated in the reference answer. Instead, it is O(m * n) because the function uses a 2D array(i.e, visited) to track visited elements, which in the worst-case scenario, can grow to contain every element in the matrix.

*/