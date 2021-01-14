using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
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

        // Reverse Words in String. 
        // I/P: I Love India 
        // O/P: India Love I
        public static string ReverseWordsInString(string s)
        {
            char[] chArr = s.ToCharArray();

            int n = s.Length, start = 0;
            for (int end = 0; end < n; end++)
            {
                if (s[end] == ' ')
                {
                    StringUtil.Reverse(chArr, start, end - 1);
                    start = end + 1;
                }
            }

            StringUtil.Reverse(chArr, start, n - 1);

            StringUtil.Reverse(chArr, 0, n - 1);

            return new String(chArr);
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
        public static void NaivePatternSearching_PatternHasDistinctCharacters(string txt, string pat)
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
        public static void RabinKarpPatternSearchAlgo(string txt, string pat)
        {
            int n = txt.Length, m = pat.Length;
            int d = 5, q = 9973;

            int h = 1;
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
                    int j = 0;

                    for (; j < m; j++)
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

        // Given a string, construct LPS array.
        //O(n*n*n) solution
        public static void FillLPSNaive(string s, int[] lps)
        {
            for (int i = 0; i < s.Length; i++)
            {
                lps[i] = LongestProperPrefixWhichIsAlsoSuffix(s, i + 1);
            }
        }

        //Find Longest proper prefix suffix array i.e LPS array
        static int LongestProperPrefixWhichIsAlsoSuffix(string s, int n)
        {
            // note: longest proper prefix cannot be more than n-1, so we start from string of len n-1 to check if it is potential longest proper prefix which is also a suffix

            for (int len = n - 1; len >= 0; len--)
            {
                bool flag = true;
                for (int i = 0; i < len; i++)
                {
                    if (s[i] != s[n - len + i])
                    {
                        flag = false;
                        break;
                    }
                }

                if (flag == true)
                {
                    return len;
                }
            }

            return 0;
        }

        // Given a string, construct LPS array.
        //O(n) solution
        public static void FillLPSEfficient(string s, int[] lps)
        {
            int i = 0, len = 0, n = s.Length;
            lps[0] = 0;
            while (i < n)
            {
                if (s[i] == s[len])
                {
                    len++;
                    lps[i] = len;
                    i++;
                }
                else
                {
                    if (len == 0)
                    {
                        lps[i] = 0;
                        i++;
                    }
                    else
                    {
                        len = lps[len - 1];
                    }
                }

            }
        }

        // KMP Pattern Matching Algorithm
        public static void KMPAlgorithm(string txt, string pat)
        {
            int n = txt.Length, m = pat.Length;

            int[] lps = new int[m];
            FillLPSEfficient(pat, lps);

            int i = 0, j = 0;
            while (i < n)
            {
                if (pat[j] == txt[i])
                {
                    i++;
                    j++;
                }
                if (j == m)
                {
                    Console.WriteLine(i - j + " ");
                    j = lps[j - 1];
                }
                else if (i < n && pat[j] != txt[i])
                {
                    if (j == 0)
                    {
                        i++;
                    }
                    else
                    {
                        j = lps[j - 1];
                    }
                }
            }
        }

        //Given string s1 and a string s2, write a snippet to say whether s2 is a rotation of s1
        public static bool AreRotations(string s1, string s2)
        {
            if (s1.Length != s2.Length)
            {
                return false;
            }

            return (s1 + s1).IndexOf(s1) >= 0;
        }

        //Given a text and a pattern, the task is to find if there is anagram of pattern present in text
        public static bool IsPatternAnagramInText(string txt, string pat)
        {
            int n = txt.Length, m = pat.Length;
            const int CHAR = 256;
            int[] CT = new int[CHAR];
            int[] CP = new int[CHAR];

            for (int i = 0; i < m; i++)
            {
                CT[txt[i]]++;
                CP[pat[i]]++;
            }

            for (int i = m; i < n; i++)
            {
                if (AreSame(CT, CP, CHAR))
                {
                    return true;
                }

                CT[txt[i]]++;
                CT[txt[i - m]]--;

            }
            return false;
        }

        private static bool AreSame(int[] arr1, int[] arr2, int n)
        {
            for (int i = 0; i < n; i++)
            {
                if (arr1[i] != arr2[i])
                {
                    return false;
                }
            }

            return true;
        }

        //Given a string, we need to find lexicographic rank of a string
        public static int LexographicRank(string s)
        {
            int n = s.Length;
            const int CHAR = 256;
            int[] count = new int[CHAR];
            int mul = Util.Factorial_TailRecursive(n, 1);
            int res = 1;
            for (int i = 0; i < n; i++)
            {
                count[s[i]]++;
            }

            for (int i = 1; i < CHAR; i++)
            {
                count[i] = count[i - 1] + count[i];
            }

            for (int i = 0; i < n - 1; i++)
            {
                mul = mul / (n - i);
                res = res + (count[s[i] - 1] * mul);
                for (int j = s[i]; j < CHAR; j++)
                {
                    count[j]--;
                }
            }

            return res;
        }

        //Given a string, find the longest substring with distinct characters
        public static int LongestSubStrDistinct(string s)
        {
            const int CHAR = 256;
            int[] prev = new int[CHAR];

            Array.Fill(prev, -1);

            int n = s.Length, res = 0;
            int i = 0;
            for (int j = 0; j < n; j++)
            {
                i = Math.Max(i, prev[s[j]] + 1);
                int maxEnd = j - i + 1;
                res = Math.Max(res, maxEnd);
                prev[s[j]] = j;
            }

            return res;
        }

        //Given a binary string S. The task is to count the number of substrings that start and end with 1. 
        //For example, if the input string is “00100101”, then there are three substrings “1001”, “100101” and “101”.
        public static int BinarySubstring(int n, String str)
        {
            int m = 0;
            for (int i = 0; i < n; i++)
            {
                if (str[i] == '1')
                {
                    m++;
                }
            }

            return m * (m - 1) / 2;
        }

        //Given two strings 'str1' and 'str2', check if these two strings are isomorphic to each other.
        //Two strings str1 and str2 are called isomorphic if there is a one to one mapping possible for every character of str1 to every character of str2 while preserving the order.
        //Note: All occurrences of every character in ‘str1’ should map to the same character in ‘str2’
        public static bool AreIsomorphic(string S1, string S2)
        {
            if (S1.Length != S2.Length)
            {
                return false;
            }
            bool[] marked = new bool[256];

            Array.Fill(marked, false);

            int[] index = new int[256];
            Array.Fill(index, -1);

            for (int i = 0; i < S1.Length; i++)
            {
                if (index[S1[i]] == -1)
                {
                    if (marked[S2[i]] == true)
                    {
                        return false;
                    }

                    marked[S2[i]] = true;
                    index[S1[i]] = S2[i];

                }
                else if (index[S1[i]] != S2[i])
                {
                    return false;
                }
            }

            return true;
        }

        //Given a string S of lowercase alphabets, check if it is isogram or not. 
		// An Isogram is a string in which no letter occurs more than once.
        public static bool IsIsogram(string data)
        {
            bool[] visited = new bool[256];

            for (int i = 0; i < data.Length; i++)
            {
                if (visited[data[i]] == true)
                {
                    return false;
                }

                visited[data[i]] = true;
            }
            return true;
        }

        //KeyPad Typing
		//You are given a string S of alphabet characters and the task is to find its matching decimal representation as on the shown keypad. Output the decimal representation corresponding to the string. For ex: if you are given “amazon” then its corresponding decimal representation will be 262966.
        public static String PrintNumber(string s)
        {
            StringBuilder res = new StringBuilder();

            foreach (char ch in s)
            {
                if((ch >= 'a' && ch <= 'c') || ch >= 'A' && ch <= 'C')
                {
                    res.Append(2);
                } else if ((ch >= 'd' && ch <= 'f') || ch >= 'D' && ch <= 'F')
                {
                    res.Append(3);
                }
                else if ((ch >= 'g' && ch <= 'i') || ch >= 'G' && ch <= 'I')
                {
                    res.Append(4);
                }
                else if ((ch >= 'j' && ch <= 'l') || ch >= 'J' && ch <= 'L')
                {
                    res.Append(5);
                }
                else if ((ch >= 'm' && ch <= 'o') || ch >= 'M' && ch <= 'O')
                {
                    res.Append(6);
                }
                else if ((ch >= 'p' && ch <= 's') || ch >= 'P' && ch <= 'S')
                {
                    res.Append(7);
                }
                else if ((ch >= 't' && ch <= 'v') || ch >= 'T' && ch <= 'V')
                {
                    res.Append(8);
                }
                else if ((ch >= 'w' && ch <= 'z') || ch >= 'W' && ch <= 'Z')
                {
                    res.Append(9);
                }
                else
                {
                    continue;
                }
            }

            return res.ToString();
        }

        //Given a string str. The task is to find the maximum occurring character in the string str. If more than one character occurs the maximum number of time then print the lexicographically smaller character.
        public static char GetMaxOccuringChar(String line)
        {
            int n = line.Length;
            int[] index = new int[256];
            int max = 1;
            char c = (char)255;

            for (int i = 0; i < n; i++)
            {
                index[line[i]]++;

                if (index[line[i]] > max)
                {
                    max = index[line[i]];
                    c = line[i];
                }
                else if (index[line[i]] == max)
                {
                    if (line[i] < c)
                    {
                        c = line[i];
                    }
                }
            }

            return c == (char)255 ? ' ' : c;

        }

        //Given a string check if it is Pangram or not. A pangram is a sentence containing every letter in the English Alphabet.
        public static bool CheckPangram(string s)
        {
            bool[] visited = new bool[26];

            Array.Fill(visited, false);

            for (int i = 0; i < s.Length; i++)
            {
                if (s[i] >= 97 && s[i] <= 122)
                {
                    visited[s[i] - 'a'] = true;
                }
                else if (s[i] >= 65 && s[i] <= 90)
                {
                    visited[s[i] - 'A'] = true;
                }
            }

            for (int i = 0; i < 26; i++)
            {
                if (visited[i] == false)
                {
                    return false;
                }
            }

            return true;
        }

        //Given a string str and another string patt. Find the character in patt that is present at the minimum index in str. If no character of patt is present in str then print ‘No character present’.
        public static int MinIndexChar(string str, string pat)
        {
            int n = str.Length;
            int m = pat.Length;

            int[] visited = new int[256];
            for (int i = 0; i < 256; i++)
            {
                visited[i] = -1;
            }

            for (int i = n - 1; i >= 0; i--)
            {
                visited[str[i]] = i;
            }

            int res = Int32.MaxValue;
            for (int i = 0; i < m; i++)
            {
                if (visited[pat[i]] != -1)
                {
                    if (visited[pat[i]] < res)
                    {
                        res = visited[pat[i]];
                    }
                }
            }

            return res == Int32.MaxValue ? -1 : res;
        }

        //Given two strings. Find the smallest window in the first string consisting of all the characters of the second string.
        public static string SmallestWindow(string S, string P)
        {
            int n = S.Length, m = P.Length;
            if (n < m)
            {
                return "-1";
            }

            int[] hash_str = new int[256];
            int[] hash_pat = new int[256];

            for (int i = 0; i < m; i++)
            {
                hash_pat[P[i]]++;
            }

            int count = 0, start = 0, startIndex = -1, minLen = Int32.MaxValue;

            for (int i = 0; i < n; i++)
            {
                if (hash_pat[S[i]] != 0)
                {
                    hash_str[S[i]]++;

                    if (hash_str[S[i]] <= hash_pat[S[i]])
                    {
                        count++;
                    }

                    if (count == m)
                    {
                        while (hash_pat[S[start]] == 0 || hash_str[S[start]] > hash_pat[S[start]])
                        {

                            if (hash_str[S[start]] > hash_pat[S[start]])
                            {
                                hash_str[S[start]]--;
                            }

                            start++;
                        }

                        int len = i - start + 1;
                        if (len < minLen)
                        {
                            minLen = len;
                            startIndex = start;
                        }
                    }
                }
            }

            if (startIndex == -1)
            {
                return "-1";
            }

            return S.Substring(startIndex, startIndex + minLen);
        }

        //Given a string str and the task is to modify the string such that no three consecutive characters are same. In a single operation, any character can be inserted at any position in the string. Find the minimum number of such operations required.
        public static long Modified(string str)
        {
            int n = str.Length;

            int res = 0;
            for (int i = 0; i < n - 2;)
            {
                if (str[i] == str[i + 1] && str[i] == str[i + 2])
                {
                    res++;
                    i = i + 2;
                }
                else
                {
                    i++;
                }
            }

            return res;
        }

        //Given a string S consisting of uppercase and lowercase characters. The task is to sort uppercase and lowercase letters separately such that if the ith place in the original string had an Uppercase character then it should not have a lowercase character after being sorted and vice versa.
        public static string CaseSort(string str)
        {
            if (String.IsNullOrEmpty(str))
            {
                return str;
            }

            string lower = "", upper = "";
            int n = str.Length;
            for (int l = 0; l < n; l++)
            {
                char ch = str[l];
                if (ch >= 65 && ch <= 90)
                {
                    upper = upper + ch;
                }
                else
                {
                    lower = lower + ch;
                }
            }

            char[] temp = lower.ToCharArray();
            Array.Sort(temp);
            lower = temp.ToString();

            temp = upper.ToCharArray();
            Array.Sort(temp);
            upper = temp.ToString();

            int i = 0, j = 0, k = 0;
            string res = "";
            while (k < n)
            {
                char ch = str[k];
                if (ch >= 65 && ch <= 90)
                {
                    res = res + upper[j];
                    j++;
                }
                else
                {
                    res = res + lower[i];
                    i++;
                }

                k++;
            }

            return res;
        }
    }
}
