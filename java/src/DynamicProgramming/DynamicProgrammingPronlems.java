package DynamicProgramming;

public class DynamicProgrammingPronlems {
    /* Q1. Stairs
    Problem Description
You are climbing a stair case and it takes A steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Problem Constraints
1 <= A <= 36



Input Format
The first and the only argument contains an integer A, the number of steps.



Output Format
Return an integer, representing the number of ways to reach the top.



Example Input
Input 1:

 A = 2
Input 2:

 A = 3


Example Output
Output 1:

 2
Output 2:

 3


Example Explanation
Explanation 1:

 Distinct ways to reach top: [1, 1], [2].
Explanation 2:

 Distinct ways to reach top: [1 1 1], [1 2], [2 1].

     */
    public class ClimbStairsSolution {
        int[] dp;

        int climbStairsUtil(int A) {
            if (dp[A] == 0) {
                if (A <= 1) {
                    dp[A] = 1;

                } else {
                    dp[A] = climbStairsUtil(A - 1) + climbStairsUtil(A - 2);
                }
            }

            return dp[A];
        }

        public int climbStairs(int A) {
            dp = new int[A + 1];

            int ans = climbStairsUtil(A);

            return ans;
        }
    }

    /* Fibonacci Number
    Problem Description
Given a positive integer A, write a program to find the Ath Fibonacci number.

In a Fibonacci series, each term is the sum of the previous two terms and the first two terms of the series are 0 and 1. i.e. f(0) = 0 and f(1) = 1. Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on.

NOTE: 0th term is 0. 1th term is 1 and so on.



Problem Constraints
0 <= A <= 44



Input Format
First and only argument is an integer A.



Output Format
Return an integer denoting the Ath Fibonacci number.



Example Input
Input 1:

 A = 4
Input 2:

 A = 6


Example Output
Output 1:

 3
Output 2:

 8


Example Explanation
Explanation 1:

 Terms of Fibonacci series are: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on.
 0th term is 0 So, 4th term of Fibonacci series is 3. 
Explanation 2:

 6th term of Fibonacci series is 8.
    */
    int fibonacci(int N)
    {
        if(N == 0) return 0;

        if(N == 1) return 1;

        int a = 0, b=1;
        int c = 0;

        for(int i=2; i<=N;i++)
        {
            c = a + b;

            a = b;
            b = c;
        }

        return c;
    }

    /* Max Sum Without Adjacent Elements
    Problem Description

Given a 2 x N grid of integer, A, choose numbers such that the sum of the numbers is maximum and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.

Note: You can choose more than 2 numbers.



Problem Constraints

1 <= N <= 20000
1 <= A[i] <= 2000



Input Format

The first and the only argument of input contains a 2d matrix, A.



Output Format

Return an integer, representing the maximum possible sum.



Example Input

Input 1:

 A = [   
        [1]
        [2]    
     ]
Input 2:

 A = [   
        [1, 2, 3, 4]
        [2, 3, 4, 5]    
     ]


Example Output

Output 1:

 2
Output 2:

 8


Example Explanation

Explanation 1:

 We will choose 2.
Explanation 2:

 We will choose 3 and 5.
    */
    public class MaxSumWithoutAdjacentElementsSolution {

        int adjacentUtil(int[][] A, int N, int[] dp)
        {
            if(N < 0) return 0;
    
            if(dp[N] == -1)
            {
                if(N ==0 ){
                    dp[N] = 0;
                } else {
    
                    int x = adjacentUtil(A, N-2, dp);
    
                    int ans = Math.max(A[0][N-1] + x, A[1][N-1] + x);
    
                    dp[N] = Math.max(ans, adjacentUtil(A, N-1, dp));
                }
            }
            
            return dp[N];
        }
    
        public int adjacent(int[][] A) {
    
            int N = A[0].length;
    
            int[] dp = new int[N+1];
    
            for(int i=0; i<=N; i++)
            {
                dp[i] = -1;
            }
    
            adjacentUtil(A, N, dp);
    
            return dp[N];
        }
    }

    /* Minimum Number of Squares
    Problem Description
Given an integer A. Return minimum count of numbers, sum of whose squares is equal to A.



Problem Constraints
1 <= A <= 105



Input Format
First and only argument is an integer A.



Output Format
Return an integer denoting the minimum count.



Example Input
Input 1:

 A = 6
Input 2:

 A = 5


Example Output
Output 1:

 3
Output 2:

 2


Example Explanation
Explanation 1:

 Possible combinations are : (12 + 12 + 12 + 12 + 12 + 12) and (12 + 12 + 22).
 Minimum count of numbers, sum of whose squares is 6 is 3. 
Explanation 2:

 We can represent 5 using only 2 numbers i.e. 12 + 22 = 5
    */
    public class CountMinSquaresSolution {
        public int countMinSquaresUtilMemoization(int A, int[] dp) {

            if (dp[A] == -1) {
                if (A == 0) {
                    dp[A] = 0;
                } else {
                    int ans = Integer.MAX_VALUE;

                    for (int i = (int) Math.sqrt(A); i > 0; i--) {
                        int x = i * i;

                        ans = Math.min(ans, 1 + countMinSquaresUtilMemoization(A - x, dp));
                    }

                    dp[A] = ans;
                }
            }

            return dp[A];
        }

        public int countMinSquaresUtilTabulation(int A) {

            int[] dp = new int[A + 1];

            dp[0] = 0;

            dp[1] = 1;

            for (int i = 2; i <= A; i++) {
                dp[i] = i;

                for (int x = 1; x * x <= i; x++) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - x * x]);
                }
            }

            return dp[A];
        }

        public int countMinSquares(int A) {

            return countMinSquaresUtilTabulation(A);
        }
    }
}

