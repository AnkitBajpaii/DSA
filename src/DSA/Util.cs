using System;
using System.Collections.Generic;
using System.Text;

namespace DSA
{
    public static class Util
    {
        public static string Swap(string str, int i, int j)
        {
            var strArr = str.ToCharArray();

            Char temp = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = temp;

            return new String(strArr);
        }

        public static void Swap(int[] A, int low, int high)
        {
            int t = A[low];
            A[low] = A[high];
            A[high] = t;
        }
    }
}
