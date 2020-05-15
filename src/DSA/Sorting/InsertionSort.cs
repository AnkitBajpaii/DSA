using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Sorting
{
    public class InsertionSort
    {
        void Swap(int[] A, int i, int j)
        {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        public static void Sort(int[] A)
        {
            int n = A.Length;

            for (int i = 1; i < n; i++)
            {
                int key = A[i];

                int j = i;
                while (j > 0 && A[j - 1] > key)
                {
                    A[j] = A[j - 1];
                    j--;
                }

                A[j] = key;
            }

        }
    }
}
