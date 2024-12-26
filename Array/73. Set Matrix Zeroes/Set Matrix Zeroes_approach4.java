/*

Method 1: Brute force
------------------------
Using another matrix (let's say it matrix2)

1) We can copy all the elements of given matrix to matrix2
2) While traversing given matrix whenever we encounter 0, we will make the entire row and column of the matrix2 to 0
3) Finally we can again copy all the elements of matrix2 to given matrix

*/

class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;
        int matrix2[][] = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                matrix2[i][j] = matrix[i][j];
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < n; k++)
                        matrix2[i][k] = 0;

                    for (int k = 0; k < m; k++)
                        matrix2[k][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                matrix[i][j] = matrix2[i][j];
        }
    }
}

--------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------

/*

Method 2: (Better than Method 1)
-----------------------------------
1) We can use two separate arrays, one for rows (rowsArray) and one for columns (colsArray) and initialize them to 1
2) While traversing the given matrix whenever we encounter 0 at (i,j), we will set rowsArray[i]=0 and colsArray[j]=0
3) After completion of step 2, again iterate through the matrix and for any (i,j), if rowsArray[i] or colsArray[j] is 0 then update matrix[i][j] to 0.

*/

class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;
        int rowsArray[] = new int[m];
        int colsArray[] = new int[n];

        Arrays.fill(rowsArray, 1);
        Arrays.fill(colsArray, 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowsArray[i] = 0;
                    colsArray[j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowsArray[i] == 0 || colsArray[j] == 0)
                    matrix[i][j] = 0;
            }
        }
    }
}

--------------------------------------------------------------------------------------------
												OR
--------------------------------------------------------------------------------------------

/*

Method 3: (Optimal approach)
--------------------------------
We can use the 0th row and 0th column of the given matrix itself instead of using two separate arrays

1) First we will traverse the 0th row and 0th column of the given matrix and if we encounter any 0 then we will set the isRow0/isCol0 variable to true which indicates that the 0th row/0th column of the given matrix will become 0
2) Next, we will traverse the remaining matrix except 0th row and 0th column and if we encounter any 0, we will make the corresponding row number and column number equal to 0 in the 0th column and 0th row respectively
3) Now we will update the values of the matrix except first row and first column to 0 if matrix[i][0]=0 or matrix[0][j]=0 for any (i,j).
4) Finally we will traverse the 0th row and 0th column and if we find isRow0/isCol0 to be true, we will make the whole row/ whole column equal to 0

*/

class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;
        boolean isRow0 = false, isCol0 = false;

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0)
                isRow0 = true;
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0)
                isCol0 = true;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (isRow0) {
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }

        if (isCol0) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }
}
