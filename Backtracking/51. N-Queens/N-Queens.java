class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] temp = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(temp[i], '.');
        }
        getDistinctNQueens(n, res, temp, 0);
        return res;
    }

    public void getDistinctNQueens(int n, List<List<String>> res, char[][] temp, int row) {

        if (row == n) {
            addToRes(res, temp);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (canQueenBePlaced(temp, row, col)) {
                temp[row][col] = 'Q';
                getDistinctNQueens(n, res, temp, row + 1);
                temp[row][col] = '.';
            }
        }
    }

    public boolean canQueenBePlaced(char[][] temp, int row, int col) {
        for (int i = 1; i < temp.length; i++) {
            if ((row - i >= 0 && col - i >= 0) && temp[row - i][col - i] == 'Q') {
                return false;
            }

            if ((row + i < temp.length && col + i < temp.length) && temp[row + i][col + i] == 'Q') {
                return false;
            }

            if ((row - i >= 0 && col + i < temp.length) && temp[row - i][col + i] == 'Q') {
                return false;
            }

            if ((row + i < temp.length && col - i >= 0) && temp[row + i][col - i] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            if (temp[i][col] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public void addToRes(List<List<String>> res, char[][] temp) {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < temp[0].length; j++) {
                sb.append(temp[i][j]);
            }
            ls.add(sb.toString());
        }
        res.add(ls);
    }
}