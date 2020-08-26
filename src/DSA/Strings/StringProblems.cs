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
                if (s[end] == ' ')
                {
                    StringUtil.Reverse(s, start, end - 1);
                    start = end + 1;
                }
            }

            StringUtil.Reverse(s, start, n - 1);

            StringUtil.Reverse(s, 0, n - 1);
        }

        //Given a pattern and a text, we need to print all index's occurrences of the pattern in the text
        public static void NaivePatternSearching(string txt, string pat)
        {
            int n = txt.Length, m = pat.Length;
            for (int i = 0; i <= n - m; i++)
            {
                int j;
                for (j = 0; j < m; j++)
                {
                    if (pat[j] != txt[i + j])
                    {
                        break;
                    }
                }

                if (j == m)
                {
                    Console.WriteLine(i + " ");
                }
            }
        }

        //Given a pattern with distinct characters and a text, we need to print all occurrences of the pattern in the text.
        public static void NaivePatternSearchingWithDistinctPatternCharacters(string txt, string pat)
        {
            int n = txt.Length, m = pat.Length;
            for (int i = 0; i <= n - m;)
            {
                int j;
                for (j = 0; j < m; j++)
                {
                    if (pat[j] != txt[i + j])
                    {
                        break;
                    }
                }

                if (j == m)
                {
                    Console.WriteLine(i + " ");
                }

                if (j == 0)
                {
                    i++;
                }
                else
                {
                    i = i + j;
                }
            }
        }

        //Rabin Karp Algorithm
        public static void RabinKarpPatternSearchAlgo(string txt, string pat, int d, int q)
        {
            int n = txt.Length, m = pat.Length, h = 1;

            // compute pow(d, m-1)
            for (int i = 1; i <= m - 1; i++)
            {
                h = (h * d) % q;
            }

            // compute p and t0
            int p = 0, t = 0;
            for (int i = 0; i < m; i++)
            {
                p = (p * d + pat[i]) % q;
                t = (t * d + txt[i]) % q;
            }

            //slide through txt, compare hashes, validate spurious hits and compute hash for next window
            for (int i = 0; i <= n - m; i++)
            {
                if (p == t)
                {
                    bool flag = true;

                    for (int j = 0; j < m; j++)
                    {
                        if (pat[j] != txt[i + j])
                        {
                            flag = false;
                            break;
                        }
                    }
                    if (flag == true)
                    {
                        Console.WriteLine(i + " ");
                    }
                }

                if (i < n - m)
                {
                    t = (d * (t - txt[i] * h) + txt[i + m]) % q;
                    if (t < 0)
                    {
                        t = t + q;
                    }
                }
            }
        }

    }
}
