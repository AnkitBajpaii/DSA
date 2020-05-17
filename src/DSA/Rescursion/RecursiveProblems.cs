﻿using System;

namespace DSA.Rescursion
{
    public class RecursiveProblems
    {
        /// <summary>
        /// Sum of digit of a number using recursion
        /// </summary>        
        public static int SumOfDigits(int n)
        {
            if (n < 10)
            {
                return n;
            }

            int lastDigit = n % 10;
            int remainingNum = n / 10;
            return lastDigit + SumOfDigits(remainingNum);
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

        public static int Josephus(int n, int k)
        {
            if (n == 1)
            {
                return 0;
            }

            int res = Josephus(n - 1, k);
            int adjustedValue = (res + k) % n;
            return adjustedValue;
            //O(N)
        }

        /// <summary>
        /// Given an array and a sum, find count of subsets of this array, whose sum equals given sum.
        /// </summary>        
        public static int SubSetSum(int[] arr, int n, int sum)
        {
            if (n == 0)
            {
                return (sum == 0 ? 1 : 0);
            }

            return SubSetSum(arr, n - 1, sum) +
                   SubSetSum(arr, n - 1, sum - arr[n - 1]);
        }
    }
}
