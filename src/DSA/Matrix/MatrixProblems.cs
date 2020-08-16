using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Matrix
{
    public static class MatrixProblems
    {
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

            while (top <= bottom && left <= right)
            {
                // print top row from left to right
                for (int i = left; i <= right; i++)
                {
                    Console.WriteLine(arr[top][i]);
                }

                top++;

                // print right column from top to bottom
                for (int i = top; i <= bottom; i++)
                {
                    Console.WriteLine(arr[i][right]);
                }

                right--;

                // print bottom row from right to left( i.e in reverse order)
                if (top <= bottom)
                {
                    for (int i = right; i >= left; i--)
                    {
                        Console.WriteLine(arr[bottom][i]);
                    }

                    bottom--;
                }

                // print left column from bottom to top
                if (left <= right)
                {
                    for (int i = bottom; i >= top; i--)
                    {
                        Console.WriteLine(arr[i][left]);
                    }

                    left++;
                }
            }

        }

        public static void SearchInRowWiseColumnWiseSortedMatrix(int[][] arr, int x)
        {
            int r = arr.GetLength(0), c = arr.GetLength(1);
            if (x < arr[0][0] || x > arr[r - 1][c - 1])
            {
                Console.WriteLine("Not found");
            }

            if (x == arr[0][0])
            {
                Console.WriteLine($"Found at 0,0");
            }

            if (x == arr[r - 1][c - 1])
            {
                Console.WriteLine($"Found at {r - 1},{c - 1}");
            }

            int i = 0, j = c - 1;
            while (i < r && j >= 0)
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
    }
}
