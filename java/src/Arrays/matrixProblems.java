package Arrays;

import java.util.ArrayList;
import java.util.Scanner;

public class matrixProblems {

    /*
     * Pattern Printing -1
     * rint a Pattern According to The Given Value of A.
     * 
     * Example: For A = 3 pattern looks like:
     * 
     * 1 0 0
     * 
     * 1 2 0
     * 
     * 1 2 3
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A <= 1000
     * 
     * 
     * Input Format
     * 
     * First and only argument is an integer denoting A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a two-dimensional array where each row in the returned array
     * represents a row in the pattern.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 3
     * Input 2:
     * 
     * A = 4
     * 
     * 
     * Example Output
     * 
     * Output :1
     * 
     * [
     * [1, 0, 0]
     * [1, 2, 0]
     * [1, 2, 3]
     * ]
     * Output 2:
     * 
     * [
     * [1, 0, 0, 0]
     * [1, 2, 0, 0]
     * [1, 2, 3, 0]
     * [1, 2, 3, 4]
     * ]
     * 
     * 
     * Example Explanation
     * 
     * Explanation 2:
     * 
     * 
     * For A = 4 pattern looks like:
     * 1 0 0 0
     * 1 2 0 0
     * 1 2 3 0
     * 1 2 3 4
     * So we will return it as two-dimensional array.
     */
    public int[][] printPattern(int A) {
        int[][] mat = new int[A][];

        for (int i = 0; i < mat.length; i++) {
            mat[i] = new int[A];

            for (int j = 0; j < i + 1; j++) {
                mat[i][j] = j + 1;
            }
        }

        return mat;
    }

    /*
     * Stair Pattern
     * Given an integer N, print the corresponding stair pattern for N.
     * 
     * For example if N = 4 then stair pattern will be like:
     *
     **
     ***
     ****
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 100
     * 
     * 
     * 
     * Input Format
     * 
     * First and only line of input contains a single integer N.
     * 
     * 
     * 
     * Output Format
     * 
     * Output the stair pattern corresponding to the given N.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * 2
     * Input 2:
     * 
     * 3
     * 
     * 
     * Example Output
     * 
     * Output 1:
     *
     **
     * 
     * Output 2:
     *
     **
     ***
     * 
     */
    public void PrintStairPattern() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    /*
     * Numeric Stair Pattern
     * Given an integer N, print the corresponding pattern for N.
     * 
     * For example if N = 4 then pattern will be like:
     * 
     * 1
     * 1 2
     * 1 2 3
     * 1 2 3 4
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 100
     * 
     * 
     * 
     * Input Format
     * 
     * First and only line of input contains a single integer N.
     * 
     * 
     * 
     * Output Format
     * 
     * Output the pattern corresponding to the given N.
     * 
     * NOTE: There should be no extra spaces after last integer and before first
     * integer in any row and all integers in any row in the pattern are space
     * separated.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * 2
     * Input 2:
     * 
     * 3
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 1
     * 1 2
     * Output 2:
     * 
     * 1
     * 1 2
     * 1 2 3
     */
    public void PrintNumericStairPattern() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) {
                    System.out.print(j);

                } else {
                    System.out.print(" " + j);

                }

            }

            System.out.println();
        }
    }

    /*
     * Inverted Half Pyramid
     * Given an integer N, print the corresponding Inverted Half Pyramid pattern for
     * N
     * Problem Constraints
     * 
     * 1 <= N <= 100
     * 
     * 
     * 
     * Input Format
     * 
     * First and only line of input contains a single integer N.
     * 
     * 
     * 
     * Output Format
     * 
     * Output the Inverted Half Pyramid pattern corresponding to the given N.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * 2
     * Input 2:
     * 
     * 3
     * 
     * 
     * Example Output
     * 
     * Output 1:
     **
     *
     * 
     * Output 2:
     ***
     **
     *
     * 
     */
    public void PrintInvertedHalfPyramid() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = n; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    /* Full pyramid
    Given an integer N, print the corresponding Full Pyramid pattern for N.

            For example if N = 5 then pattern will be like:

                * 
            * * 
            * * * 
            * * * * 
            * * * * * 
            NOTE: There should be exactly one extra space after each * for each row.



            Problem Constraints

            2 <= N <= 100



            Input Format

            First and only line of input contains a single integer N.



            Output Format

            Output the Full Pyramid pattern corresponding to the given N.



            Example Input

            Input 1:

            2
            Input 2:

            3
            Input 3:

            4


            Example Output
            Output 1:

                * 
               * * 
            Output 2:

                * 
               * * 
              * * * 
    */
    public void PrintFullPyramid()
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n-i; j++)
            {
                System.out.print(" ");
            }

            for(int j=1; j<=i; j++){
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    /*
     * Inverted Full Pyramid
     * Given an integer N, print the corresponding Inverted Full Pyramid pattern for
     * N.
     * 
     * For example if N = 5 then pattern will be like:
     * 
     * * * * *
     * * * *
     * * *
     * *
     * 
     * NOTE: There should be exactly one extra space after each * for each row.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 2 <= N <= 100
     * 
     * 
     * 
     * Input Format
     * 
     * First and only line of input contains a single integer N.
     * 
     * 
     * 
     * Output Format
     * 
     * Output the Inverted Full Pyramid pattern corresponding to the given N.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * 2
     * Input 2:
     * 
     * 3
     * Input 3:
     * 
     * 4
     * 
     * 
     * Example Output
     * 
     * Output 1:

        * * 
         *  
        Output 2:

        * * * 
         * *  
          * 
        Output 3:

        * * * * 
         * * *  
          * *
           * 
     */
    public void PrintInvertedFullPyramid()
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i=n; i>=1; i--)
        {
            for(int j=1; j<=n-i; j++)
            {
                System.out.print(" ");
            }

            for(int j=1; j<=i; j++){
                System.out.print("* ");
            }

            System.out.println();
        }
    }
    
    /* Matrix Multiplication
    You are given two integer matrices A(having M X N size) and B(having N X P). You have to multiply matrix A with B and return the resultant matrix. (i.e. return the matrix AB).
    */
    public int[][] matrixMultiplication(int[][] A, int[][] B) {

        int M = A.length;
        int N = A[0].length;
        int P = B[0].length;

        int[][] mat = new int[M][P];

        for(int row=0; row<M; row++)
        {
            for(int col = 0; col<P; col++)
            {
                int sum=0;

                for(int j=0; j<N; j++)
                {
                    sum += A[row][j] * B[j][col];
                }

                mat[row][col] = sum;
            }
        }

        return mat;
    }

    /*
     * Spiral Order Matrix II Problem Description
     * 
     * Given an integer A, generate a square matrix filled with elements from 1 to
     * A2 in spiral order.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A <= 1000
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument is integer A
     * 
     * 
     * Output Format
     * 
     * Return a 2-D matrix which consists of the elements in spiral order.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * 1 Input 2:
     * 
     * 2
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [ [1] ] Output 2:
     * 
     * [ [1, 2], [4, 3] ]
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * 
     * Only 1 is to be arranged. Explanation 2:
     * 
     * 1 --> 2 | | 4<--- 3
     */
    public int[][] spiralOrderForSquareMatrix(int A) {
        int[][] mat = new int[A][A];

        int top=0, right = A-1, bottom = A-1, left = 0, count = 1;

        while(top <= bottom && left <= right){

            // fill top row from left to right
            for(int k=left; k<=right; k++){
                mat[top][k]=count++;
            }

            top++;

            // fill right column from top to bottom
            for(int k=top; k<=bottom; k++){
                mat[k][right]=count++;
            }

            right--;

            //fill bottom row from right to left
            for(int k=right; k>=left; k--){
                mat[bottom][k]=count++;
            }

            bottom--;

            //fill left column from bottom to top
            for(int k=bottom; k>=top; k--){
                mat[k][left]=count++;
            }

            left++;
        }

        return mat;
    }

    /*
     * Rotate Matrix Problem Description
     * 
     * You are given a n x n 2D matrix A representing an image.
     * 
     * Rotate the image by 90 degrees (clockwise).
     * 
     * You need to do this in place.
     * 
     * Note: If you end up using an additional array, you will only receive partial
     * score.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= n <= 1000
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is a 2D matrix A of integers
     * 
     * 
     * 
     * Output Format
     * 
     * Return the 2D rotated matrix.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * [ [1, 2], [3, 4] ] Input 2:
     * 
     * [ [1] ]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [ [3, 1], [4, 2] ] Output 2:
     * 
     * [ [1] ]
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * After rotating the matrix by 90 degree: 1 goes to 2, 2 goes to 4 4 goes to 3,
     * 3 goes to 1 Explanation 2:
     * 
     * 2D array remains the ssame as there is only element.
     */
    public void Transpose(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A[i].length; j++) {
                int t = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = t;
            }
        }
    }

    public void Reverse(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            int t = A[i];
            A[i] = A[j];
            A[j] = t;

            i++;
            j--;
        }
    }

    public void RotateMatrixBy90Degree(int[][] A) {
        Transpose(A);

        for (int i = 0; i < A.length; i++) {
            Reverse(A[i]);
        }
    }

    /*
     * Pascal Triangle Problem Description
     * 
     * Write a program to input an integer N from user and print pascal triangle up
     * to N rows.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 25
     * 
     * 
     * 
     * Input Format
     * 
     * First line is an integer N.
     * 
     * 
     * 
     * Output Format
     * 
     * N lines whose each row contains N+1 space separated integers.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * 3 Input 2:
     * 
     * 5
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 1 0 0 1 1 0 1 2 1 Output 2:
     * 
     * 1 0 0 0 0 1 1 0 0 0 1 2 1 0 0 1 3 3 1 0 1 4 6 4 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Row 1 = 1 0 0 0 0 Row 2 = 1C0 1C1 0 0 0= 1 1 0 0 0 Row 3 = 2C0 2C1 2C2 0 0= 1
     * 2 1 0 0 Row 4 = 3C0 3C1 3C2 3C3 0= 1 3 3 1 0
     * 
     * IDEA: num at position i = number at position i in prev row + number at
     * position (i - 1) in previous row.
     * 
     */
    public int[][] PrintPascalTriangle(int A) {
        int[][] B = new int[A][A];
        if (A == 0)
            return B;

        B[0][0] = 1;

        for (int i = 1; i < A; i++) {
            for (int j = 0; j < A; j++) {
                int sum = B[i - 1][j];
                if (j - 1 >= 0) {
                    sum = sum + B[i - 1][j - 1];
                }

                B[i][j] = sum;

                if (B[i][j] == 0)
                    break;
            }
        }

        return B;
    }

    /*
     * Row with maximum number of ones Problem Description
     * 
     * Given a binary sorted matrix A of size N x N. Find the row with the maximum
     * number of 1.
     * 
     * NOTE:
     * 
     * If two rows have the maximum number of 1 then return the row which has a
     * lower index. Rows are numbered from top to bottom and columns are numbered
     * from left to right. Assume 0-based indexing. Assume each row to be sorted by
     * values. Expected time complexity is O(rows).
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 1000
     * 
     * 0 <= A[i] <= 1
     * 
     * 
     * 
     * Input Format
     * 
     * The only argument given is the integer matrix A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return the row with the maximum number of 1.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [ [0, 1, 1] [0, 0, 1] [0, 1, 1] ] Input 2:
     * 
     * A = [ [0, 0, 0, 0] [0, 1, 1, 1] ]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 0 Output 2:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Row 0 has maximum number of 1s. Explanation 2:
     * 
     * Row 1 has maximum number of 1s.
     * 
     * IDEA: Either skip a row or skip a column
     */
    public int RowWithMaxNumberOf1InBinarySortedMatrix(int[][] A) {
        int r=A.length, c=A[0].length;

        int i=0, j=c-1, ans = -1;
        while(i<r && j>=0)
        {
            if(A[i][j] == 1)
            {
                ans = i;
                j--;                
            } else{
                i++;
            }
        }

        return ans;
    }

    /*
     * Search in a row wise and column wise sorted matrix Problem Description
     * 
     * Given a matrix of integers A of size N x M and an integer B. In the given
     * matrix every row and column is sorted in increasing order. Find and return
     * the position of B in the matrix in the given form: If A[i][j] = B then return
     * true If B is not present return false
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N, M <= 1000 -100000 <= A[i] <= 100000 -100000 <= B <= 100000
     * 
     * 
     * Input Format
     * 
     * The first argument given is the integer matrix A. The second argument given
     * is the integer B.
     * 
     * 
     * Output Format
     * 
     * Return the position of B and if it is not present in A return -1 instead.
     * 
     * 
     * Example Input
     * 
     * A = [ [1, 2, 3] [4, 5, 6] [7, 8, 9] ] B = 2
     * 
     * IDEA: Either skip a row or skip a column TC: O(N+M)
     */
    public boolean SearchInRowWiseAndColumnWiseSortedMatrix(int[][] A, int B) {
        int n = A.length;

        int i = 0, j = A[0].length - 1;

        while (i < n && j >= 0) {

            if (A[i][j] == B) {
                return true;
            } else {
                if (A[i][j] < B) {
                    i++; // skip row
                } else {
                    j--;// skip col
                }
            }
        }

        return false;
    }

    /*
     * Anti Diagonals Problem Description
     * 
     * Give a N * N square matrix A, return an array of its anti-diagonals. Look at
     * the example for more details.
     * 
     * 
     * Problem Constraints
     * 
     * 1<= N <= 1000 1<= A[i][j] <= 1e9
     * 
     * 
     * Input Format
     * 
     * First argument is an integer N, denoting the size of square 2D matrix. Second
     * argument is a 2D array A of size N * N.
     * 
     * 
     * Output Format
     * 
     * Return a 2D integer array of size (2 * N-1) * N, representing the
     * anti-diagonals of input array A. The vacant spaces in the grid should be
     * assigned to 0.
     * 
     * 
     * Example Input
     * 
     * Input 1: 3 1 2 3 4 5 6 7 8 9 Input 2:
     * 
     * 1 2 3 4
     * 
     * 
     * Example Output
     * 
     * Output 1: 1 0 0 2 4 0 3 5 7 6 8 0 9 0 0 Output 2:
     * 
     * 1 0 2 3 4 0
     */
    public int[][] AntiDiagonal(int[][] A) {
        int n = A.length;

        int[][] B = new int[2 * n - 1][n];

        int br = 0;
        for (int k = 0; k < n; k++) {
            int i = 0, j = k, bc = 0;
            while (i < n && j >= 0) {
                B[br][bc] = A[i][j];
                i++;
                j--;
                bc++;
            }

            br++;
        }

        for (int k = 1; k < n; k++) {
            int i = k, j = n - 1, bc = 0;
            while (i < n && j >= 0) {
                B[br][bc] = A[i][j];
                i++;
                j--;
                bc++;
            }

            br++;
        }

        return B;
    }

    /*Print submatrix of given matrix*/
    public void PrintSubMatrix(int[][] mat, int r1, int c1, int r2, int c2)
    {
        for(int i=r1; i<=r2; i++)
        {
            for(int j=c1; j<=c2; j++)
            {
                System.out.print(mat[i][j]+ " ");
            }

            System.out.println();
        }
    }

    /*Find Sum of a submatrix of given matrix*/
    public int SumOfSubMatrix(int[][] mat, int r1, int c1, int r2, int c2)
    {
        int sum = 0;
        for(int i=r1; i<=r2; i++)
        {
            for(int j=c1; j<=c2; j++)
            {
                sum += mat[i][j];
            }
        }

        return sum;
    }

    /* Generate Print All submatrices of a given matrix
    */
    public void PrintAllSubMatricesBruteForce(int[][] mat)
    {
        int N = mat.length, M = mat[0].length;
        for (int r1 = 0; r1 < N; r1++) {
            for (int c1 = 0; c1 < M; c1++) {
                for (int r2 = r1; r2 < N; r2++) {
                    for (int c2 = c1; c2 < M; c2++) {

                        PrintSubMatrix(mat, r1, c1, r2, c2);
                    }
                }
            }
        }
        // TC: O(N*M * N*M * N*M)
    }

    /* Sum of All Submatrices Brute Force
    */
    public int SumOfAllSubMatricesBruteForce(int[][] mat){
        int N = mat.length, M = mat[0].length;
        int sum = 0;
        for (int r1 = 0; r1 < N; r1++) {
            for (int c1 = 0; c1 < M; c1++) {
                for (int r2 = r1; r2 < N; r2++) {
                    for (int c2 = c1; c2 < M; c2++) {

                        sum += SumOfSubMatrix(mat, r1, c1, r2, c2);
                    }
                }
            }
        }

        return sum;
        // TC: O(N*M * N*M * N*M)
    }

    /* Generate Prefix Sum Matrix for given matrix
    */
    public int[][] GeneratePrefixSumMatrix(int[][] mat)
    {
        int N = mat.length, M = mat[0].length;
        int[][] pf = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) {
                    pf[i][j] = mat[i][j];
                } else if (i == 0) {
                    pf[i][j] = pf[i][j - 1] + mat[i][j];
                } else if (j == 0) {
                    pf[i][j] = pf[i - 1][j] + mat[i][j];
                } else {
                    pf[i][j] = pf[i - 1][j] + pf[i][j - 1] - pf[i - 1][j - 1] + mat[i][j];
                }
            }
        }

        return pf;
    }

    /*Given a prefix sum matrix and top left and bottom right corner of a matrix, Find Sum of a submatrix Using Prefix Sum Matrix*/
    public int SumOfSubMatrixUsingPrefixSumMatrix(int[][] pf, int r1, int c1, int r2, int c2)
    {
        int sum = pf[r2][c2];
        if (r1 - 1 >= 0) {
            sum -= pf[r1 - 1][c2];
        }

        if (c1 - 1 >= 0) {
            sum -= pf[r2][c1 - 1];

        }

        if (r1 - 1 >= 0 && c1 - 1 >= 0) {
            sum += pf[r1 - 1][c1 - 1];
        }
        return sum;
    }

    /* Sum of All Submatrices Optimized using Prefix Sum
    // TC: O(N*M * N*M)
    */
    public int SumOfAllSubMatricesOptimizedUsingPrefixSum(int[][] mat) {
        int N = mat.length, M = mat[0].length;
        int sum = 0;

        int[][] pf = GeneratePrefixSumMatrix(mat);

        for (int r1 = 0; r1 < N; r1++) {
            for (int c1 = 0; c1 < M; c1++) {
                for (int r2 = r1; r2 < N; r2++) {
                    for (int c2 = c1; c2 < M; c2++) {
                        sum += SumOfSubMatrixUsingPrefixSumMatrix(pf, r1, c1, r2, c2);
                    }
                }
            }
        }

        return sum;
        // TC: O(N*M * N*M)
    }

    /* Sum of All Submatrices Optimized using Contribution Technique
    // TC: O(N*M)
    */
    public int SumOfAllSubMatricesOptimizedUsingContributionTechnique(int[][] mat) {
        int N = mat.length, M = mat[0].length;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                sum += mat[i][j] * ((i + 1) * (j + 1) * (N - i) * (M - j));
            }
        }

        return sum;
        // TC: O(N*M)
    }

    /*
     * Maximum Sum Square SubMatrix of Size B x B
     * Given a 2D integer matrix A of size N x N find a B x B submatrix where B<= N
     * and B>= 1, such that sum of all the elements in submatrix is maximum.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 103.
     * 
     * 1 <= B <= N
     * 
     * -102 <= A[i][j] <= 102.
     * 
     * 
     * 
     * Input Format
     * 
     * First arguement is an 2D integer matrix A.
     * 
     * Second argument is an integer B.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a single integer denoting the maximum sum of submatrix of size B x B.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [
     * [1, 1, 1, 1, 1]
     * [2, 2, 2, 2, 2]
     * [3, 8, 6, 7, 3]
     * [4, 4, 4, 4, 4]
     * [5, 5, 5, 5, 5]
     * ]
     * B = 3
     * Input 2:
     * 
     * A = [
     * [2, 2]
     * [2, 2]
     * ]
     * B = 2
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 48
     * Output 2:
     * 
     * 8
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Maximum sum 3 x 3 matrix is
     * 8 6 7
     * 4 4 4
     * 5 5 5
     * Sum = 48
     * Explanation 2:
     * 
     * Maximum sum 2 x 2 matrix is
     * 2 2
     * 2 2
     * Sum = 8
     */
    public int MaximumSumSquareSubMatrixOfSizeB(int[][] A, int B) {
        int N = A.length;

        int ans = Integer.MIN_VALUE;

        int[][] pf = GeneratePrefixSumMatrix(A);

        for (int r1 = 0; r1 <=N-B; r1++) {
            for (int c1 = 0; c1 <=N-B; c1++) {
                ans = Math.max(ans, SumOfSubMatrixUsingPrefixSumMatrix(pf, r1, c1, r1+B-1, c1+B-1));
            }
        }

        return ans;
    }

    
}
