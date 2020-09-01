using DSA.Arrays;
using DSA.BitwiseManipulation;
using DSA.Hashing;
using DSA.LinkedList;
using DSA.Rescursion;
using DSA.Searching;
using DSA.Sorting;
using DSA.Stacks;
using System;
using System.Collections;
using System.Collections.Generic;

namespace DSA
{
    class Program
    {
        static void Print(int[] A)
        {
            Console.Write("[ ");
            for (int i = 0; i < A.Length; i++)
            {
                if (i == A.Length - 1)
                {
                    Console.Write($"{A[i]}");
                }
                else
                {
                    Console.Write($"{A[i]} , ");
                }
            }
            Console.Write(" ]");
            Console.WriteLine();
        }

        static void Main(string[] args)
        {
            //int T = Convert.ToInt32(Console.ReadLine());

            //for (int i = 1; i <= T; i++)
            //{
            //    int size = Convert.ToInt32(Console.ReadLine().Trim());

            //    string inputArr = Console.ReadLine().Trim();
            //    int[] arr = Array.ConvertAll(inputArr.Split(" "), int.Parse);
            //    int res = HashingProblems.FirstRepeatingElement(arr, size);
            //    Console.WriteLine(res);
            //}

            //int[] arr = new int[] { };
            //var res = SortingProblems.Merge3SortedArrays(arr1, arr2, arr3);
            //Print(res);

            StackProblems.IsBalanced("({[])}");

            Console.ReadKey();
        }

        
    }
}
