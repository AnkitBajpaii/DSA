using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Strings
{
    public static class StringUtil
    {
        public static string Swap(string str, int low, int high)
        {
            var strArr = str.ToCharArray();

            Char temp = strArr[low];
            strArr[low] = strArr[high];
            strArr[high] = temp;

            return new String(strArr);
        }

        public static void Reverse(char[] s, int low, int high)
        {
            while (low <= high)
            {
                char t = s[low];
                s[low] = s[high];
                s[high] = t;

                low++;
                high--;
            }
        }

        public static string Reverse(string str, int low, int high)
        {
            var strArr = str.ToCharArray();

            Reverse(strArr, low, high);

            return new String(strArr);
        }
    }
}
