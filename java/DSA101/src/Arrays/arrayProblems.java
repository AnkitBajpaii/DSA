package Arrays;

public class arrayProblems {
    // 1.Search an element in row-wise and column wise sorted matrix
    public boolean SearchElementInRowWiseColumnWiseSortedMatrix(int[][] mat, int n, int m, int k) {

        int row = 0, col = m - 1;// starting from top right corner
        while (row < n && col >= 0) {
            if (mat[row][col] == k) {
                return true;
            }

            if (k > mat[row][col]) {
                row++;
            } else {
                col--;
            }
        }

        return false;

        // TC: O(n+m)
    }

    // 2. Given a binary matrix which is sorted row wise.Find max number of 1's in a
    // row.
    public Integer FindMaxNoOfOnesInRowWiseSortedBinaryMatrix(int[][] mat, int n, int m) {

        int row = 0, col = m - 1;// starting from top right corner

        while (row < n && col >= 0) {
            if (mat[row][col] == 1) {
                col--;
            } else {
                row++;
            }
        }

        col = col + 1;

        return m - col;

        // TC: O(n+m)
    }

    // 3. Given a square matrix print it in spiral order
    public void PrintSquareMatrixInSpriralOrder(int[][] mat, int n) {
        int c = 0;
        int l = n - 1;

        while (l > 0) {
            int i = c, j = c;

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                j++;
            }

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                i++;
            }

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                j--;
            }

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                i--;
            }

            l = l - 2;
            c++;
        }

        if (l == 0) {
            System.out.print(mat[c][c] + " ");
        }

        // TC: O(n*n)
    }
}
