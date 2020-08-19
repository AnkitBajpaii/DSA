using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Matrix
{
    public static class MatrixProblems
    {
        public static int[][] SumMatrix(int[][] A, int[][] B)
        {
            int n = A.GetLength(0);
            int m = A.GetLength(1);
            int[][] res = new int[n][];

            for (int i = 0; i < n; i++)
            {
                res[i] = new int[m];
                for (int j = 0; j < m; j++)
                {
                    res[i][j] = A[i][j] + B[i][j];
                }
            }

            return res;
        }

        public static List<int> SumTriangles(int[][] matrix, int n)
        {
            List<int> res = new List<int>();

            if (n == 1)
            {
                res.Add(matrix[0][0]);
                res.Add(matrix[0][0]);
                return res;
            }

            int s1 = 0, s2 = 0;
            for (int i = 0; i < n; i++)
            {

                for (int j = i; j < n; j++)
                {
                    s1 = s1 + matrix[i][j];
                }

                for (int j = i; j >= 0; j--)
                {
                    s2 = s2 + matrix[i][j];
                }
            }

            res.Add(s1);
            res.Add(s2);
            return res;
        }

        public static int[][] MultiplyMatrix(int[][] A, int[][] B)
        {
            int n1 = A.GetLength(0);
            int m1 = A.GetLength(1);

            int n2 = B.GetLength(0);
            int m2 = B.GetLength(1);

            if (m1 != n2)
            {
                int[][] result = new int[][] { new int[] { 0 }, new int[] { 0 } };
                return result;
            }

            int[][] res = new int[n1][];

            for (int i = 0; i < n1; i++)
            {
                res[i] = new int[m2];

                for (int j = 0; j < m1; j++)
                {
                    for (int k = 0; k < n2; k++)
                    {
                        res[i][j] = A[i][k] * B[k][j];
                    }
                }
            }
            return res;
        }

        public static void MatrixTraverseInSnakePattern(int[][] arr)
        {
            for (int i = 0; i < arr.GetLength(0); i++)
            {
                if (i % 2 == 0)
                {
                    for (int j = 0; j < arr[i].Length; j++)
                    {
                        Console.WriteLine(arr[i][j]);
                    }
                }
                else
                {
                    for (int j = arr[i].Length - 1; j >= 0; j--)
                    {
                        Console.WriteLine(arr[i][j]);
                    }
                }
            }
        }

        public static void MatrixBoundaryTraversal(int[][] arr)
        {
            int r = arr.GetLength(0);
            int c = arr.GetLength(1);

            //handle corner cases
            if (r == 1)
            {
                for (int i = 0; i < c; i++)
                {
                    Console.WriteLine(arr[0][i]);
                }

            }
            else if (c == 1)
            {
                for (int i = 0; i < r; i++)
                {
                    Console.WriteLine(arr[i][0]);
                }
            }
            else
            {
                // regular case
                for (int i = 0; i < c; i++)
                {
                    Console.WriteLine(arr[0][i]);
                }

                for (int i = 1; i < r; i++)
                {
                    Console.WriteLine(arr[i][c - 1]);
                }

                for (int i = c - 2; i >= 0; i--)
                {
                    Console.WriteLine(arr[r - 1][i]);
                }

                for (int i = r - 2; i >= 0; i--)
                {
                    Console.WriteLine(arr[i][0]);
                }
            }
        }

        public static void TransposeOfNxNMatrix(int[][] arr, int n)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = i + 1; j < n; j++)
                {
                    int t = arr[i][j];
                    arr[i][j] = arr[j][i];
                    arr[j][i] = t;
                }
            }
        }

        public static void RotateBy90Degree(int[][] arr, int n)
        {
            TransposeOfNxNMatrix(arr, n);

            for (int i = 0; i < n; i++)
            {
                int low = 0, high = n - 1;
                while (low < high)
                {
                    int t = arr[low][i];
                    arr[low][i] = arr[high][i];
                    arr[high][i] = t;

                    low++;
                    high--;
                }
            }
        }

        public static void SpiralTraversal(int[][] arr)
        {
            int r = arr.GetLength(0);
            int c = arr.GetLength(1);

            int top = 0, right = c - 1, bottom = r - 1, left = 0;

            List<int> res = new List<int>();

            while (top <= bottom && left <= right)
            {
                // print top row from left to right
                for (int i = left; i <= right; i++)
                {
                    res.Add(arr[top][i]);
                }

                top++;

                // print right column from top to bottom
                for (int i = top; i <= bottom; i++)
                {
                    res.Add(arr[i][right]);
                }

                right--;

                // print bottom row from right to left( i.e in reverse order)
                if (top <= bottom)
                {
                    for (int i = right; i >= left; i--)
                    {
                        res.Add(arr[bottom][i]);
                    }

                    bottom--;
                }

                // print left column from bottom to top
                if (left <= right)
                {
                    for (int i = bottom; i >= top; i--)
                    {
                        res.Add(arr[i][left]);
                    }

                    left++;
                }
            }

        }

        public static void SearchInRowWiseColumnWiseSortedMatrix(int[][] arr, int x)
        {
            int n = arr.GetLength(0), m = arr.GetLength(1);
            if (x < arr[0][0] || x > arr[n - 1][m - 1])
            {
                Console.WriteLine("Not found");
            }

            if (x == arr[0][0])
            {
                Console.WriteLine($"Found at 0,0");
            }

            if (x == arr[n - 1][m - 1])
            {
                Console.WriteLine($"Found at {n - 1},{m - 1}");
            }

            int i = 0, j = m - 1;
            while (i < n && j >= 0)
            {
                if (arr[i][j] == x)
                {
                    Console.WriteLine($"Found at {i},{j}");
                    return;
                }

                if (x < arr[i][j])
                {
                    j--;
                }
                else
                {
                    i++;
                }
            }

            Console.WriteLine("Not found");
        }

        //Given a boolean matrix of size RxC where each cell contains either 0 or 1, modify it such that if a matrix cell matrix[i][j] is 1 then all the cells in its ith row and jth column will become 1.
        public static void BooleanMatrix(int[][] matrix)
        {
            int r = matrix.GetLength(0);
            int c = matrix.GetLength(1);

            int[] row = new int[r];
            int[] col = new int[c];

            for (int i = 0; i < r; i++)
            {
                row[i] = 0;
            }

            for (int i = 0; i < c; i++)
            {
                col[i] = 0;
            }

            for (int i = 0; i < r; i++)
            {
                for (int j = 0; j < c; j++)
                {
                    if (matrix[i][j] == 1)
                    {
                        row[i] = 1;
                        col[j] = 1;
                    }
                }
            }

            for (int i = 0; i < r; i++)
            {
                for (int j = 0; j < c; j++)
                {
                    if (row[i] == 1 || col[j] == 1)
                    {
                        matrix[i][j] = 1;
                    }
                }
            }
        }

        //A beautiful matrix is a matrix in which the sum of elements in each row and column is equal.
        //Given a square matrix of size NxN.Find the minimum number of operation(s) that are required to make the matrix beautiful.In one operation you can increment the value of any one cell by 1.
        public static int FindMinOperation(int[][] matrix, int n)
        {
            int[] sumRow = new int[n];
            int[] sumCol = new int[n];
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    sumRow[i] += matrix[i][j];
                    sumCol[j] += matrix[i][j];
                }
            }

            int maxSum = 0;
            for (int i = 0; i < n; i++)
            {
                maxSum = Math.Max(maxSum, sumRow[i]);
                maxSum = Math.Max(maxSum, sumCol[i]);
            }

            int count = 0;

            for (int i = 0, j = 0; i < n && j < n;)
            {
                int diff = Math.Min(maxSum - sumRow[i], maxSum - sumCol[j]);

                matrix[i][j] += diff;
                sumRow[i] += diff;
                sumCol[j] += diff;

                if (sumRow[i] == maxSum)
                {
                    i++;
                }

                if (sumCol[j] == maxSum)
                {
                    j++;
                }

                count += diff;

            }
            return count;
        }
    }
}
