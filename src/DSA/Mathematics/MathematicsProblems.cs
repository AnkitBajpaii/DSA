using System;
using System.Collections.Generic;

namespace DSA.Mathematics
{
    /// <summary>
    /// Given a number N, count the number of digits in the number N
    /// </summary>
    public class MathematicsProblems
    {
        /// <summary>
        /// Time Complexity = O(d), where d is number of digits in the number,
        /// Space Complexity = O(1)
        /// </summary>        
        public static int CountDigitsIterative(int num)
        {
            int count = 0;
            while (num > 0)
            {
                count++;
                num = num / 10;
            }
            return count;
        }

        /// <summary>
        /// Time Complexity = O(1), 
        /// Space Complexity = O(1)
        /// </summary>
        /// <param name="num"></param>
        /// <returns></returns>
        public static int CountDigitsInConstantTime(int num)
        {
            int res = Convert.ToInt32(Math.Floor(Math.Log10(num) + 1));
            return res;
        }

        public bool IsNumberPalindrome(int n)
        {
            int rev = 0;
            int temp = n;
            while (temp != 0)
            {
                rev = rev * 10 + temp % 10;
                temp = temp / 10;
            }
            return rev == n;
        }

        public int TrailingZerosInFactorialOfNumber(int n)
        {
            int res = 0;

            for (int i = 5; i <= n; i = i * 5)
            {
                res += res + n / i;
            }
            return res;
        }

        public int GCD_Iterative(int a, int b)
        {
            int min = Math.Min(a, b);
            while (a != b)
            {
                if (a > b)
                {
                    a = a - b;
                }
                else
                {
                    b = b - a;
                }
            }

            return a;
        }

        public int GCD_EuclidAlgo(int a, int b)
        {
            if (b == 0)
            {
                return a;
            }

            return GCD_EuclidAlgo(b, a % b);
        }

        public int LCM_Iterative(int a, int b)
        {
            int max = Math.Max(a, b);
            while (true)
            {
                if (max % a == 0 && max % b == 0)
                {
                    return max;
                }

                max++;
            }
        }

        public int LCM(int a, int b)
        {
            return a * b / GCD_EuclidAlgo(a, b);
        }
        public bool IsPrime_Optimized(int n)
        {
            if (n == 0 || n == 1)
            {
                return false;
            }

            if (n % 2 == 0 || n % 3 == 0)
            {
                return true;
            }

            for (int i = 5; i * i <= n; i = i + 6)
            {
                if (n % i == 0 || n % (i + 2) == 0)
                {
                    return false;
                }
            }

            return true;
        }

        public void PrimeFactorsOfNumber(int n)
        {
            if (n <= 1) return;

            for (int i = 2; i * i <= n; i++)
            {
                while (n % i == 0)
                {
                    Console.WriteLine(i);
                    n = n / i;
                }
            }

            if (n > 1)
                Console.WriteLine(n);
        }

        public void PrimeFactorsOfNumberMoreEfficient(int n)
        {
            if (n <= 1) return;

            while (n % 2 == 0)
            {
                Console.WriteLine(2);
                n = n / 2;
            }

            while (n % 3 == 0)
            {
                Console.WriteLine(3);
                n = n / 3;
            }

            for (int i = 5; i * i <= n; i = i + 6)
            {
                while (n % i == 0)
                {
                    Console.WriteLine(i);
                    n = n / i;
                }

                while (n % (i + 2) == 0)
                {
                    Console.WriteLine(i + 2);
                    n = n / (i + 2);
                }
            }

            if (n > 3)
                Console.WriteLine(n);
        }

        public void AllDivisorsOfNumber(int n)
        {
            for (int i = 1; i * i <= n; i++)
            {
                if (n % i == 0)
                {
                    Console.WriteLine(i);

                    if (n / i != i)
                    {
                        Console.WriteLine(n / i);
                    }
                }
            }
        }

        public void AllDivisorsOfNumberInSortedOrder(int n)
        {
            int i = 1;
            for (; i * i < n; i++)
            {
                if (n % i == 0)
                {
                    Console.WriteLine(i);
                }
            }

            for (; i >= 1; i--)
            {
                if (n % i == 0)
                {
                    Console.WriteLine(n / i);
                }
            }
        }

        public void SieveOfEratosthenes(int n)
        {
            bool[] isPrime = new bool[n + 1];
            Array.Fill(isPrime, true);

            for (int i = 2; i * i <= n; i++)
            {
                if (isPrime[i])
                {
                    for (int j = i * i; j <= n; j = j + i) // j can start from 2*i also, but j = i*i, is an optimization to get O(n*LogLogn) else with j = 2*i , it wll be O(n * Sqrt(n))
                    {
                        isPrime[j] = false;
                    }
                }
            }

            for (int i = 2; i <= n; i++)
            {
                if (isPrime[i])
                {
                    Console.WriteLine(i);
                }
            }
        }

        // x to the power of n
        public int Power(int x, int n)
        {
            if (n == 0) return 1;

            int temp = Power(x, n / 2);
            temp = temp * temp;

            if (n % 2 == 0)
            {
                return temp;
            }

            return temp * x;
        }
    }
}
