using System;

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
            while(num > 0)
            {
                count++;
                num = num / 10;
            }
            return count;
        }

        /// <summary>
        /// Time Complexity = O(1),
        /// Space Complexity = O(d), where d is number of digits in the number
        /// </summary>
        public static int CountDigitsRecursive(int num)
        {
            if(num == 0)
            {
                return 0;
            }

            return 1 + CountDigitsRecursive(num / 10);
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
    }
}
