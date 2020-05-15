using System;

namespace DSA.BitwiseManipulation
{
    public class BitwiseProblems
    {
        /// <summary>
        /// Given a number N, check if it's Kth bit (from right side) is set or not.
        /// K <= no. of bits in binary representation of N
        /// </summary>        
        public static bool CheckKthBitSetOrNot_1(int N, int K)
        {
            if ((N & (1 << (K - 1))) != 0)
            {
                return true;
            }

            return false;
        }

        /// <summary>
        /// Solution 2 for 
        /// Given a number N, check if it's Kth bit is set or not.
        /// K <= no. of bits in binary representation of N
        /// </summary>    
        public static bool CheckKthBitSetOrNot_2(int N, int K)
        {
            if (((N >> (K - 1)) & 1) == 1)
            {
                return true;
            }

            return false;
        }

        /// <summary>
        /// Given a number N, count how many bits are set.
        /// Ex: I/P 5
        ///     O/P 2
        /// </summary>        
        public static int CountSetBits_1(int N)
        {
            int count = 0;

            while (N > 0)
            {
                if ((N & 1) == 1)
                {
                    count++;
                }

                N = N >> 1;
            }
            return count;
            // Time Complexity: O(total bits in N)
        }

        /// <summary>
        /// Given a number N, count how many bits are set.
        /// Ex: I/P 5
        ///     O/P 2
        /// </summary>
        public static int CountSetBits_2(int N)
        {
            int count = 0;

            while (N > 0)
            {
                count = count + (N & 1);
                N = N >> 1;
            }
            return count;
            // Time Complexity: O(total bits in N)
        }

        /// <summary>
        /// Given a number N, count how many bits are set.
        /// Ex: I/P 5
        ///     O/P 2
        /// </summary>
        public static int CountSetBit_BrianKerningamAlgo(int N)
        {
            int count = 0;

            while (N > 0)
            {
                N = N & (N - 1);
                count++;
            }
            return count;
        }

        /// <summary>
        /// Given a number N, count how many bits are set.
        /// Ex: I/P 27651
        ///     O/P 6
        /// </summary>
        public static int CountSetBit_TableLookUp(int N)
        {
            int[] table = new int[256];
            table[0] = 0;
            table[1] = 1;

            for (int i = 2; i < table.Length; i++)
            {
                table[i] = (i & 1) + table[i >> 1];
                // or can also write
                //table[i] = (i & 1) + table[i/2];
            }

            int count = table[N & 0xff];

            N = N >> 8;
            count = table[N & 0xff];

            N = N >> 8;
            count = table[N & 0xff];

            N = N >> 8;
            count = table[N & 0xff];

            return count;
        }

        /// <summary>
        /// Given an umber N, find if it is a power of 2
        /// </summary>

        public static bool IsPowerOf2_NaiveSolution(int num)
        {
            if (num == 0)
            {
                return false;
            }

            if (num == 1)
            {
                return true;
            }

            while (num != 1)
            {
                if (num % 2 != 0)
                {
                    return false;
                }

                num = num / 2;
            }

            return true;

            //Time Complexity: O(logN);
        }

        public static bool IsPowerOf2_InConstantTime(int N)
        {
            if (N == 0)
                return false;

            return ((N & (N - 1)) == 0);

            //Time Complexity: O(1);
        }

        /// <summary>
        /// Given an array, where all number appear even number of times except one number, which appear odd nmber of time.
        /// Find this odd appearing number
        /// </summary>        
        public static int FindOnlyOddOccuringNumber(int[] A)
        {
            int res = 0;
            for (int i = 0; i < A.Length; i++)
            {
                res = res ^ A[i];
            }

            return res;

            //Time Complexity: O(N)
        }

        /// <summary>
        /// Given an array on N numbers, that has values in range [1.. N+1]. Every number appears exactly once, hence one number is missing
        /// Find the missing number
        /// </summary>
        /// <param name="A"></param>
        /// <param name="n"></param>
        /// <returns></returns>
        public static int FindMissingNumber(int[] A, int N)
        {
            int x = 0;
            for (int i = 0; i < A.Length; i++)
            {
                x ^= A[i];
            }

            int y = 0;
            for (int i = 1; i <= N + 1; i++)
            {
                y ^= i;
            }

            return x ^ y;

            //Time Complexity: O(N)
        }

        /// <summary>
        /// Given an array A of size n, all the elements in array appear even number of times, except two numbers which appearch odd number of time.
        /// Find the two numbers which appear odd number of times.
        /// </summary>
        /// <param name="A"></param>
        /// <param name="n"></param>
        public static void FindTwoOddAppearingNumbers(int[] A, int n)
        {
            int xor = 0, res1 = 0, res2 = 0;
            //find xor of all numbers
            for (int i = 0; i < n; i++)
            {
                xor = xor ^ A[i];
            }

            // xor will now be xor of two odd appearing number; ex xor = x ^ y

            // find the kth bit that is set xor
            int k = xor & ~(xor - 1);
            for (int i = 0; i < n; i++)
            {
                if ((A[i] & k) != 0)
                {
                    res1 = res1 ^ A[i];
                }
                else
                {
                    res2 = res2 ^ A[i];
                }
            }
            Console.WriteLine($"Number 1: {res1}, Number 2:{res2}");
        }

        /// <summary>
        /// Generate all subset of given string.
        /// I/P: "abc"
        /// O/P: "", "a", "b", "c", "ab", "ac","bc", "abc"
        /// </summary>
        public static void GeneratePowerSetOfString(string str)
        {
            int n = str.Length;
            double p = Math.Pow(2, n);

            Console.Write("[");
            for (int i = 0; i < p; i++)
            {
                string temp = "";
                for (int j = 1; j <= n; j++)
                {
                    if ((i & (1 << (j - 1))) != 0)
                    {
                        temp = temp + str[j - 1];
                    }
                }
                if (i == p - 1)
                {
                    Console.Write(temp);
                }
                else
                {
                    Console.Write(temp + ", ");
                }
            }
            Console.Write("]");

            //Time Complexity: O(2^n * n) where n is length of string
        }
    }
}
