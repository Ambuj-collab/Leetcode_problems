/*

Intuition:
------------
The problem requires visiting all cells in a matrix in a spiral order starting from a given starting position. The key insight is to simulate the spiral movement while ensuring boundary conditions are handled correctly. We will utilize a direction vector to facilitate movement in the right, down, left, and up directions sequentially.

Approach:
-----------
1) Initialize:
	a) A result list to store the coordinates of the visited cells.
	b) A direction 2D array to facilitate movement: right, down, left, and up.
	c) A variable to track the number of steps to move in the current direction.
	d) Variables for the current position and direction.

2) Simulate Movement:
	a) Start at the given starting position and add it to the result.
	b) Use a loop to continue moving in a spiral pattern until all cells are visited.
	c) Change direction after moving the required steps in the current direction and increase the number of steps every two turns.

3) Boundary Check:
	Ensure that only valid coordinates within the matrix bounds are added to the result.

*/

class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        List<int[]> result = new ArrayList<>();
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int steps = 1;
        int d = 0;
        int r = rStart, c = cStart;
        result.add(new int[] { r, c });

        while (result.size() < rows * cols) {
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < steps; ++j) {
                    r += directions[d][0];
                    c += directions[d][1];
                    if (r >= 0 && r < rows && c >= 0 && c < cols) {
                        result.add(new int[] { r, c });
                    }
                }
                d = (d + 1) % 4;
            }
            ++steps;
        }

        return result.toArray(new int[result.size()][]);
    }
}

/*

Time and Space Complexity:
----------------------------
Time Complexity: O(rows * cols) because we visit each cell exactly once.
Space Complexity: O(rows * cols) to store the result.

*/