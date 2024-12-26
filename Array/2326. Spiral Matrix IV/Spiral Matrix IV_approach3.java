/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        for (int[] row : matrix) {
            Arrays.fill(row, -1);
        }

        String flag = "r";
        int row = 0, col = 0;

        while (head != null) {
            matrix[row][col] = head.val;

            // Determine the next direction
            if (flag.equals("r") && (col + 1 >= n || matrix[row][col + 1] != -1))
                flag = "d";
            else if (flag.equals("d") && (row + 1 >= m || matrix[row + 1][col] != -1))
                flag = "l";
            else if (flag.equals("l") && (col - 1 < 0 || matrix[row][col - 1] != -1))
                flag = "u";
            else if (flag.equals("u") && (row - 1 < 0 || matrix[row - 1][col] != -1))
                flag = "r";

            // Move based on the current direction
            if (flag.equals("r"))
                col++;
            else if (flag.equals("d"))
                row++;
            else if (flag.equals("l"))
                col--;
            else if (flag.equals("u"))
                row--;

            // If the next position is already filled, break
            if (row >= m || col >= n || row < 0 || col < 0 || matrix[row][col] != -1)
                break;

            head = head.next;
        }
        return matrix;
    }
}
