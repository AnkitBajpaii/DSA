using DSA.Strings;
using System;

namespace DSA.Rescursion
{
    public class RecursiveProblems
    {
        public static int Factorial_NonTailRecursive(int n)
        {
            if (n == 0 || n == 1)
                return 1;
            return n * Factorial_NonTailRecursive(n - 1);

        }

        public static int Factorial_TailRecursive(int n, int k)
        {
            // in modern compilers, tail recursive functions take less memomry and space as compared to their non-tail recursive counterpart
            if (n == 0 || n == 1)
                return k;

            return Factorial_TailRecursive(n - 1, k * n);
        }

        public static bool RecursiveLinearSearch(int[] A, int start, int end, int key)
        {
            if (start > end)
            {
                return false;
            }

            if (A[start] == key || A[end] == key)
            {
                return true;
            }

            return RecursiveLinearSearch(A, start + 1, end - 1, key);
        }

        /// <summary>
        /// Recursive program to check if given string is palindrome
        /// </summary>        
        public static bool IsPalindrome(string str, int s, int e)
        {
            if (s >= e)
            {
                return true;
            }

            return str[s] == str[e] && IsPalindrome(str, s+1, e-1);
        }

        /// <summary>
        /// Sum of digit of a number using recursion
        /// </summary>        
        public static int SumOfDigits(int n)
        {
            if (n < 10)
            {
                return n;
            }
            return n % 10 + SumOfDigits(n / 10);
        }

        /// <summary>
        /// Given a rope of length n, you need to find maximum number of pieces you can make such that length of every piece is in set {a,b,c} for given three values a, b, c
        /// </summary>
        public static int RodCutting(int n, int a, int b, int c)
        {
            if (n == 0)
                return 0;

            if (n < 0)
                return -1;

            int cutsWhenChoosingA = RodCutting(n - a, a, b, c);
            int cutsWhenChoosingB = RodCutting(n - b, a, b, c);
            int cutsWhenChoosingC = RodCutting(n - c, a, b, c);

            int res = Math.Max(Math.Max(cutsWhenChoosingA, cutsWhenChoosingB), cutsWhenChoosingC);

            if (res == -1)
                return -1;

            return res + 1;
            //Time Complexity: O(3^N)
        }

        /// <summary>
        /// Given a string print all subsets of it(in any order).
        /// </summary>        
        public static void PrintSubSets(string str, string curr = "", int index = 0)
        {
            if (index == str.Length)
            {
                Console.WriteLine(curr);
                return;
            }

            PrintSubSets(str, curr, index + 1);
            PrintSubSets(str, curr + str[index], index + 1);
        }

        /// <summary>
        ///Tower of Hanoi is a mathematical puzzle where we have three rods and n disks. The objective of the puzzle is to move the entire stack to another rod, obeying the following simple rules:
        ///1) Only one disk can be moved at a time.
        ///2) Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack i.e.a disk can only be moved if it is the uppermost disk on a stack.
        ///3) No disk may be placed on top of a smaller disk.
        /// Sample I/P TowerOfHanoi(3, 'A', 'B', 'C');
        /// </summary>
        public static void TowerOfHanoi(int n, char A, char B, char C)
        {
            if (n == 1)
            {
                Console.WriteLine($"Move 1th disc from {A} to {C}");
                return;
            }

            TowerOfHanoi(n - 1, A, C, B);

            Console.WriteLine($"Move {n}th disc from {A} to {C}");

            TowerOfHanoi(n - 1, B, A, C);
        }

        /// <summary>
        /// There are N person standing in circle, you need to kill Kth person in every iteration. And this has to be done in circular manner
        /// Find the position of last survivor.
        /// Assume circle position starts from index 0, kth person will be (k-1)th person starting from zero.
        /// </summary>        
        public static int Josephus(int n, int k)
        {
            if (n == 1)
            {
                return 0;
            }

            return (Josephus(n - 1, k) + k) % n;
            //O(N)
        }

        /// <summary>
        /// Given an array and a sum, find count of subsets of this array, whose sum equals given sum.
        /// </summary>        
        public static int CountSubSet(int[] arr, int n, int sum)
        {
            if (n == 0)
            {
                return (sum == 0 ? 1 : 0);
            }

            return CountSubSet(arr, n - 1, sum) +
                   CountSubSet(arr, n - 1, sum - arr[n - 1]);
        }

        /// <summary>
        /// Find All permuation of given string
        /// </summary>        
        public static void PermutationOfString(string curr, string str)
        {
            if (str.Length == 0)
            {
                Console.WriteLine(curr);
            }
            for (int i = 0; i < str.Length; i++)
            {
                PermutationOfString(curr + str[i], str.Substring(0, i) + str.Substring(i + 1));
            }
        }

        /// <summary>
        /// Find All permuation of given string
        /// </summary>    
        public static void PermutationOfString_Method2(string str, int l, int r)
        {
            if (l == r)
            {
                Console.WriteLine(str);
                return;
            }

            for (int i = l; i <= r; i++)
            {
                str = StringUtil.Swap(str, i, l);

                PermutationOfString_Method2(str, l + 1, r);

                str = StringUtil.Swap(str, i, l);
            }
        }

        static void PossibleWordsUtil(int[] a, int N, char[] output, int currDigit, string[] keypad)
        {
            if (currDigit == N)
            {
                Console.Write(new String(output));
                Console.Write(" ");
                return;
            }
            for (int i = 0; i < keypad[a[currDigit]].Length; i++)
            {
                output[currDigit] = keypad[a[currDigit]][i];
                PossibleWordsUtil(a, N, output, currDigit + 1, keypad);
                if (a[currDigit] == 0 || a[currDigit] == 1)
                {
                    return;
                }
            }
        }

        /// <summary>
        /// Possible words from phone digit
        /// </summary>        
        public static void PossibleWords(int[] a, int N)
        {
            char[] output = new char[N];
            string[] keypad = { "", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };
            PossibleWordsUtil(a, N, output, 0, keypad);
        }
    }
}
