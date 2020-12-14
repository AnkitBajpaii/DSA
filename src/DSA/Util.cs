using System;
using System.Collections.Generic;
using System.Text;

namespace DSA
{
    public static class Util
    {
        public static int Factorial_TailRecursive(int n, int k)
        {
            // in modern compilers, tail recursive functions take less memomry and space as compared to their non-tail recursive counterpart
            if (n == 0 || n == 1)
                return k;

            return Factorial_TailRecursive(n - 1, k * n);
        }

        public static void Swap(int[] A, int i, int j)
        {
            int t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
    }
}
