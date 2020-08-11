using System;

namespace DSA.BitwiseManipulation
{
    public class BitwiseProblems
    {
        /// <summary>
        /// Given a number N, check if it's Kth bit (from right side) is set or not.
        /// K <= no. of bits in binary representation of N
        /// </summary>        
        public static bool CheckKthBitSetOrNot_UsingLeftShift(int N, int K)
        {
            // perform bitwise and of number n with number whose only kth bit is set.
            // a number whose only kth bit is set => (1 << (K - 1))
            return (N & (1 << (K - 1))) != 0;
        }

        /// <summary>
        /// Solution 2 for 
        /// Given a number N, check if it's Kth bit is set or not.
        /// K <= no. of bits in binary representation of N
        /// </summary>    
        public static bool CheckKthBitSetOrNot_UsingRightShift(int N, int K)
        {
            // right shift number N by K-1 bits , so that kth bit moves to rightmost side
            // then perform bitwise and with 1

            return ((N >> (K - 1)) & 1) == 1;
        }

        /// <summary>
        /// Given a number N, count how many bits are set.
        /// Ex: I/P 5
        ///     O/P 2
        /// </summary>
        public static int CountSetBits_NaiveSolution(int N)
        {
            int count = 0;

            while (N > 0)
            {
                count = count + (N & 1); // if N is even (N & 1) is 0 
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
        public static int CountSetBit_BrianKerninghamAlgo(int N)
        {
            // when we substract 1 from a number, then all the bits which are zero, after the last set bit(towards left), becomes 1 and the last set bit becomes 0.
            int count = 0;

            while (N > 0)
            {
				count++;
                N = N & (N - 1);                
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
            count += table[N & 0xff];

            N = N >> 8;
            count += table[N & 0xff];

            N = N >> 8;
            count += table[N & 0xff];

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
                if (num % 2 != 0)// n & 1 == 1 i.e no. is odd
                {
                    return false;
                }

                num = num / 2; // n = n >> 1;
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

        // ex: I/P : n = 101100 ,   O/P : 101000
        public static int RemoveRightMostSetBit(int n)
        {
            return n & (n - 1);
        }

        // ex: I/P : n = 101100 ,   O/P : 000100
        public static int FindRightMostSetBit(int n)
        {
            return n & ~(n - 1);
        }

        // ex: I/P : n = 100100 , k = 4   O/P : 101100
        public static int SetKthBitInNumber(int n, int k)
        {
            return n | (1 << (k - 1));
        }

        // ex: I/P : n = 101100 , k = 4   O/P : 100100
        public static int RemoveKthBitInNumber(int n, int k)
        {
            return n & ~(1 << (k - 1));
        }

        // ex: I/P : n = 101100 , k = 4   O/P : 100100
        // ex: I/P : n = 100100 , k = 4   O/P : 101100
        public static int ToggleKthBitInNumber(int n, int k)
        {
            return n ^ (1 << (k - 1));
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

            // find the first set bit(rightmost set bit)
            int k = FindRightMostSetBit(xor);
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

        /// <summary>
        /// Given an integer an N. 
        /// The task is to print the position of first set bit found from right side in the binary representation of the number.
        /// </summary>
        /// <param name="n"></param>
        /// <returns></returns>
        public static int GetFirstSetBitPosition(int n)
        {
            if(n == 0)
            {
                return 0;
            }

            int firstSetBit = FindRightMostSetBit(n);

            int position =  (int)Math.Log2(firstSetBit) + 1;

            return position;
            // O(1)
        }

        /// <summary>
        /// Given two numbers M and N. 
        /// The task is to find the position of rightmost different bit in binary representation of numbers.
        /// </summary>        
        public static int PosOfRightMostDifferentBit(int m, int n)
        {
            if (m == n)
            {
                return -1;
            }
            int xor = m ^ n;
            int pos = GetFirstSetBitPosition(xor);
            return pos;
        }

        /// <summary>
        /// You are given two numbers A and B. Write a program to count number of bits needed to be flipped to convert A to B.
        /// </summary>        
        public static int CountBitsFlip(int a, int b)
        {

            int xor = a ^ b;
            int countSetBits = CountSetBit_TableLookUp(xor);
            return countSetBits;

        }

        /// <summary>
        /// Given a number N.  The task is to check whether it is sparse or not. 
        /// A number is said to be a sparse number if no two or more consecutive bits are set in the binary representation.
        /// </summary>        
        public static bool IsSparse(int n)
        {
            if((n & (n >> 1)) >= 1){
                return false;
            }

            return true ;

        }

        /// <summary>
        /// Given a number N. The task is to find the length of the longest consecutive 1s in its binary representation.
        /// </summary>
        public static int MaxConsecutiveOnes(int x)
        {
            //The idea is based on the concept that if we AND a bit sequence with a shifted version of itself, we’re effectively removing the trailing 1 from every sequence of consecutive 1s.
            int count = 0;
            while(x != 0)
            {
                x = x & (x << 1);
                count++;
            }
            return count;
        }

        /// <summary>
        /// Given an unsigned integer N. The task is to swap all odd bits with even bits. 
        /// For example, if the given number is 23 (00010111), it should be converted to 43(00101011). 
        /// Here, every even position bit is swapped with adjacent bit on right side and every odd position bit is swapped with adjacent on left side.
        /// </summary>
        /// Hint: 
        /// Right shift all even bits,
        /// Left shift all odd bits
        /// Combine the result
        public static int SwapBits(int n)
        {
            int evenSetBits = n & 0xAAAAAA; //get only even set bits in n
            int oddSetBits = n & 0x55555555;//get only odd set bits in n

            evenSetBits = evenSetBits >> 1;
            oddSetBits = oddSetBits << 1;
            int res = evenSetBits | oddSetBits;
            return res;

        }

        /// <summary>
        /// Finf Log base 2 of a number
        /// </summary>        
        public static int FindLog2(int x)
        {
            int res = 0;
            while (x > 0)
            {
                x = x >> 1;
                res++;
            }
                
            return res;
        }

        /// <summary>
        /// Swap without using temporary variable
        /// </summary>        
        public static void SwapTwoNumbersWithoutUsingTempVariable(int a, int b)
        {
            a ^= b;
            b ^= a;
            a ^= b;
        }

        /// <summary>
        /// Given a number N, find the most significant set bit in the given number
        /// </summary>        
        public static int FindMostSignificantSetBit(int n)
        {
            int posOfMSB = (int)Math.Log2(n);
            int res = (int)Math.Pow(2, posOfMSB);
            return res;
        }

        public static int FindMostSignificantSetBit_Approach2(int n)
        {
            int res = 0;
            while(n != 0)
            {
                res = n;
                n = n & (n - 1);
            }
            return res;
        }
		
		public static int BinaryToGreyCodeConverter(int n) {
        
			//The Most Significant Bit (MSB) of the gray code is always equal to the MSB of the given binary code.
			//Other bits of the output gray code can be obtained by XORing binary code bit at that index and previous index.
			return n ^ (n >> 1); 
		
		}
    }
}
