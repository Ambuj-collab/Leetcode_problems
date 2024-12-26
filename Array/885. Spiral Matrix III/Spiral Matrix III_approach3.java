// fastest solution

/*

Intuition:
------------
The clockwise movement of the spiral follows the pattern :
1 Right
1 Down
2 Left
2 Up
3 Right
3 Down
and so on

Thus we can easily simulate this process with the help of two inner loops, one for the horizontal axis (left / right) and one for vertical axis (down / up).

Then we will simply reverse their direction by multiplying by -1 after each loop and increment their counter.

Algorithm:
------------
1) Create a helper method add() to add coordinates to result matrix
2) Initialize Result Matrix:
	Create a result matrix of size rows * cols to store the coordinates.
3) Initialize Directions and Counters:
	a) Set horizontal to 1 (right) and vertical to 1 (down).
	b) Initialize horizontal step counter hCtr to 1 and vertical step counter vCtr to 1.
4) Set Initial Index:
	Set index i to 0.
5) Add Initial Coordinates:
	Call add(result, i++, rStart, cStart) to add the starting coordinates to the result matrix.
6) Main Loop:
	a) Loop until all rows in the result matrix are filled (i < rows * cols):
		i) Horizontal Movement:
			Loop j from 0 to hCtr:
				Update cStart by adding horizontal (move left/right).
				If rStart and cStart are within bounds, add (rStart, cStart) to the result matrix.
		ii) Reverse horizontal direction (horizontal *= -1).
		iii) Increment hCtr by 1.
	b) Vertical Movement:
		i) Loop k from 0 to vCtr:
			Update rStart by adding vertical (move up/down).
			If rStart and cStart are within bounds, add (rStart, cStart) to the result matrix.
		ii) Reverse vertical direction (vertical *= -1).
		iii) Increment vCtr by 1.
7) Return Result:
	Return the result matrix containing the spiral order coordinates.

*/

class Solution {

    // Function to add coordinates in matrix
    public void add(int matrix[][], int i, int row, int col) {
        matrix[i][0] = row;
        matrix[i][1] = col;
    }

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {

        // Create result matrix
        int result[][] = new int[cols * rows][2];

        // Initialize directions, +1 horizontal = right, -1 horizontal = left
        // Similarly, +1 vertical = down, -1 vertical = up
        int horizontal = 1, vertical = 1;

        // Initialize axis counters hCtr, vCtr
        int hCtr = 1, vCtr = 1;

        // Initialize index of result row matrix
        int i = 0;

        // Add initial coordinates
        add(result, i++, rStart, cStart);

        // Loop runs until all rows are filled in result matrix
        while (i < cols * rows) {

            // Loop until horizontal counter to move left or right desired no. of times
            for (int j = 0; j < hCtr; j++) {
                // Add horizontal to cStart
                // Increments cStart in case of right, decrements cStart in case of left
                cStart += horizontal;

                // Check for out of bounds case then add coordinates
                if (rStart < rows && rStart >= 0 && cStart >= 0 && cStart < cols)
                    add(result, i++, rStart, cStart);
            }

            // Reverse the horizontal direction
            horizontal *= -1;
            // Increment horizontal counter
            hCtr++;

            // Similary, repeat the steps for vertical direction
            for (int k = 0; k < vCtr; k++) {
                rStart += vertical;
                if (rStart < rows && rStart >= 0 && cStart >= 0 && cStart < cols)
                    add(result, i++, rStart, cStart);
            }
            vertical *= -1;
            vCtr++;
        }
        return result;
    }
}
