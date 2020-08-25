using System;
using System.Collections.Generic;
using System.Text;

namespace DSA
{
    public static class Util
    {
        public static void Swap(int[] A, int low, int high)
        {
            int t = A[low];
            A[low] = A[high];
            A[high] = t;
        }
    }
}
