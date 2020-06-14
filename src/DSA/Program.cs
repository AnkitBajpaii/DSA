using DSA.Arrays;
using DSA.BitwiseManipulation;
using DSA.Rescursion;
using DSA.Searching;
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
            //    string str = Console.ReadLine();
            //    int[] sizes = Array.ConvertAll(str.Split(" "), int.Parse);
            //    int n1 = sizes[0];
            //    int n2 = sizes[1];

            //    str = Console.ReadLine();
            //    var arr1 = Array.ConvertAll(str.Split(" "), int.Parse);

            //    str = Console.ReadLine();
            //    var arr2 = Array.ConvertAll(str.Split(" "), int.Parse);

            //}


            Console.ReadKey();
        }
    }
}
