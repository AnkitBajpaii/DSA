using System;

namespace DSA
{
    class Program
    {
        static void Print(int[] A)
        {
            Console.Write("[ ");
            for (int i = 0; i < A.Length; i++)
            {
                if(i == A.Length - 1)
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
            
            Console.ReadKey();
        }
    }
}
