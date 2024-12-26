/*

Intuition:
------------
1) Transpose the matrix by swapping elements along the main diagonal, then symmetrically flip it for the desired rotation.
	1  2  3             
	4  5  6
	7  8  9
2) While transpose, it will be swap(matrix[i][j], matrix[j][i])
	1  4  7
	2  5  8
	3  6  9
3) Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])
	7  4  1
	8  5  2
	9  6  3

Approach:
------------
1) Transpose Step:
	a) The transpose method swaps the elements across the main diagonal of the matrix.
	b) It iterates through the upper triangle of the matrix (matrix[i][j] and matrix[j][i]) and exchanges the values.
2) Reverse Rows Step:
	a) The reverseRows method then reverses each row of the transposed matrix.
	b) For each row (matrix[r]), it uses two pointers (left and right) to swap elements symmetrically across the center.
3) Combination in rotate Method:
	a) The rotate method combines these two steps to achieve a 90-degree clockwise rotation.
	b) It first transposes the matrix and then reverses the rows of the transposed matrix.

*/


class Solution {
    private void transpose(int[][] matrix) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=i; j<matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    private void reverseRows(int[][] matrix) {
        for(int r=0; r<matrix.length; r++) {
            int left = 0;
            int right = matrix.length-1;

            while(left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;

                left++;
                right--;
            }
        }

    }
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reverseRows(matrix);
    }
}

/*

Space and Time Complexity:
-----------------------------
Time complexity:O(n * n)
Space complexity:O(1)

*/