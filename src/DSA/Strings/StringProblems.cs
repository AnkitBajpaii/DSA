using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DSA.Strings
{
    public class StringProblems
    {
        //Given two strings, we need to check if these strings are Anagram of each other or not.
        public static bool CheckAnagram(string s1, string s2)
        {
            if (s1.Length != s2.Length)
            {
                return false;
            }

            const int CHAR = 256;
            int[] count = new int[CHAR];

            for (int i = 0; i < s1.Length; i++)
            {
                count[s1[i]]++;
                count[s2[i]]--;
            }

            for (int i = 0; i < 256; i++)
            {
                if (count[i] != 0)
                {
                    return false;
                }
            }

            return true;
        }

        //Given a string, the task is to find the first character (whose leftmost appearance is first) that repeats.
        public static int LeftMostRepeatingIndex(string s)
        {
            const int CHAR = 256;
            bool[] visited = new bool[CHAR];
            int res = -1;
            for (int i = s.Length - 1; i >= 0; i--)
            {
                if (visited[s[i]])
                {
                    res = i;
                }
                else
                {
                    visited[s[i]] = true;
                }
            }

            return res;
        }

        //Given a string, the task is to find the leftmost character that does not repeat.
        public static int LeftMostNonRepeatingIndex(string s)
        {
            const int CHAR = 256;
            int[] fIndex = new int[CHAR];

            Array.Fill(fIndex, -1);

            for (int i = 0; i < s.Length; i++)
            {
                if (fIndex[s[i]] == -1)
                {
                    fIndex[s[i]] = i;
                }
                else
                {
                    fIndex[s[i]] = -2;
                }
            }

            int res = Int32.MaxValue;
            for (int i = 0; i < CHAR; i++)
            {
                if (fIndex[i] >= 0)
                {
                    res = Math.Min(res, fIndex[i]);
                }
            }

            return res == Int32.MaxValue ? -1 : res;
        }

        // Reverse Words in String. I/P: I Love India O/P: India Love I
        public static void ReverseWordsInString(char[] s)
        {
            int n = s.Length, start = 0;
            for (int end = 0; end < n; end++)
            {
                if(s[end] == ' ')
                {
                    StringUtil.Reverse(s, start, end - 1);
                    start = end + 1;
                }
            }

            StringUtil.Reverse(s, start, n - 1);

            StringUtil.Reverse(s, 0, n - 1);
        }


    }
}
