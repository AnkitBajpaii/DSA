package DynamicProgramming;

import java.util.*;

public class DynamicProgrammingPronlems {
    int mod = 1000*1000*1000 + 7;

    /* Q1. N Stairs
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

    /* Ways to Decode
    Problem Description
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message denoted by string A containing digits, determine the total number of ways to decode it modulo 109 + 7.



Problem Constraints
1 <= length(A) <= 105



Input Format
The first and the only argument is a string A.



Output Format
Return an integer, representing the number of ways to decode the string modulo 109 + 7.



Example Input
Input 1:

 A = "12"
Input 2:

 A = "8"


Example Output
Output 1:

 2
Output 2:

 1


Example Explanation
Explanation 1:

 Given encoded message "8", it could be decoded as only "H" (8).
 The number of ways decoding "8" is 1.
Explanation 2:

 Given encoded message "12", it could be decoded as "AB" (1, 2) or "L" (12).
 The number of ways decoding "12" is 2.
    */
    public long numDecodingsUtil(String A, int N) {

        if (N == 0 || N == 1) {
            return 1;
        }

        if (A.charAt(0) == '0') {
            return 0;
        }

        long count = 0;

        if (A.charAt(N - 1) > '0') {
            count = numDecodingsUtil(A, N - 1);
        }

        if (A.charAt(N - 2) == '1' || (A.charAt(N - 2) == '2' && A.charAt(N - 1) < '7')) {
            count += numDecodingsUtil(A, N - 2);
        }

        return count;
    }

    public long numDecodingsUtilMemoization(String A, int N, long[] dp) {

        if (dp[N] == -1) {
            if (N == 0 || N == 1) {
                dp[N] = 1;
            } else if (A.charAt(0) == '0') {
                dp[N] = 0;
            } else {
                long count = 0;

                if (A.charAt(N - 1) > '0') {
                    count = numDecodingsUtilMemoization(A, N - 1, dp);
                }

                if (A.charAt(N - 2) == '1' || (A.charAt(N - 2) == '2' && A.charAt(N - 1) < '7')) {
                    count = (count + numDecodingsUtilMemoization(A, N - 2, dp)) % mod;
                }

                dp[N] = count;
            }
        }

        return dp[N];
    }

    public int numDecodingsUtilTabulation(String A, int N) {
        if (N == 0 || A.charAt(0) == '0')
            return 0;

        if (N == 1)
            return 1;

        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = 0;

            if (A.charAt(i - 1) > '0') {
                dp[i] = dp[i - 1];
            }

            if (A.charAt(i - 2) == '1' || (A.charAt(i - 2) == '2' && A.charAt(i - 1) < '7')) {
                dp[i] = (dp[i] + dp[i - 2]) % mod;
            }
        }

        return (int) (dp[N] % mod);
    }

    public int numDecodings(String A) {

        int N = A.length();

        return numDecodingsUtilTabulation(A, N);
        
        // if (N == 0 || A.charAt(0) == '0')
        //     return 0;

        // long[] dp = new long[N+1];
        // for(int i=0; i<=N; i++)
        // {
        //     dp[i] = -1;
        // }

        // long ans = numDecodingsUtilMemoization(A, N, dp);

        // return (int) (ans % mod);
    }

    /* Let's Party
    Problem Description

In Danceland, one person can party either alone or can pair up with another person.

Can you find in how many ways they can party if there are A people in Danceland?

Note: Return your answer modulo 10003, as the answer can be large.



Problem Constraints

1 <= A <= 105



Input Format

Given only argument A of type Integer, number of people in Danceland.



Output Format

Return an integer denoting the number of ways people of Danceland can party.



Example Input

Input 1:

 A = 3
Input 2:

 A = 5


Example Output

Output 1:

 4
Output 2:

 26


Example Explanation

Explanation 1:

 Let suppose three people are A, B, and C. There are only 4 ways to party
 (A, B, C) All party alone
 (AB, C) A and B party together and C party alone
 (AC, B) A and C party together and B party alone
 (BC, A) B and C party together and A
 here 4 % 10003 = 4, so answer is 4.
 
Explanation 2:

 Number of ways they can party are: 26.
    */
    long waysToPartyMemoization(int A, long[] dp) {
        if (dp[A] == -1) {
            if (A == 1 || A == 2) {
                dp[A] = A;
            } else {
                dp[A] = (waysToPartyMemoization(A - 1, dp) + ((A - 1) * waysToPartyMemoization(A - 2, dp)) % 10003)
                        % 10003;
            }
        }

        return dp[A];
    }

    long waysToPartyTabulation(int A) {
        if (A == 1 || A == 2)
            return A;

        long[] dp = new long[A + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= A; i++) {
            dp[i] = (dp[i - 1] + ((i - 1) * dp[i - 2]) % 10003) % 10003;
        }

        return dp[A];
    }

    public int NoOfwaysToParty(int A) {

        // long[] dp = new long[A+1];
        // for(int i=0; i<=A; i++)
        // {
        // dp[i] = -1;
        // }

        // waysToPartyMemoization(A, dp);
        // long ans = dp[A];

        long ans = waysToPartyTabulation(A);

        return (int) (ans % 10003);
    }

    /* Dice throw
    Problem Description

Given sum A, number of ways you can get that sum with dice roll[1-6].

As the number of ways can be large return its modulo by 1e9 + 7.



Problem Constraints

1 <= A <= 10 2


Input Format

The first argument is the integer A.


Output Format

Return an integer .


Example Input

Input 1:
A = 3
Input 2:

A = 4


Example Output

Output 1:
4
Output 2:

8


Example Explanation

Explanation 1:

The four possible ways to obtain 3 are: [1, 1, 1], [1, 2], [2, 1] and [3].




Explanation 2:

The eight possible ways to obrain 8 are: [1, 1, 1, 1], [1, 1, 2], [1, 2, 1], [2, 1, 1], [1, 3], [3, 1], [2, 2], [4].
    */
    long waysToObtainSumByThrowingDice(int N) {
        if (N == 0)
            return 1;

        if (N <= 2)
            return N;

        long ans = 0;

        for (int i = 1; i <= Math.min(6, N); i++) {
            ans = (ans + waysToObtainSumByThrowingDice(N - i)) % mod;
        }

        return ans;
    }

    long waysToObtainSumByThrowingDiceTabulation(int N) {
        if (N == 0)
            return 1;

        if (N <= 2)
            return N;

        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = 0;

            for (int j = 1; j <= Math.min(i, 6); j++) {
                dp[i] = (dp[i] + dp[i - j]) % mod;
            }
        }

        return dp[N];
    }

    public int diceThrow(int A) {

        long ans = waysToObtainSumByThrowingDiceTabulation(A);

        return (int) ans;
    }

    /* Ways to send the signal
    Problem Description

You are trying to send signals to aliens using a linear array of A laser lights. You don't know much about how the aliens are going to percieve the signals, but what you know is that if two consecutive lights are on then the aliens might take it as a sign of danger and destroy the earth.

Find and return the total number of ways in which you can send a signal without compromising the safty of the earth. Return the ans % 109 + 7.



Problem Constraints

1 <= A <= 105



Input Format

The only argument given is integer A.



Output Format

Return the ans%(109+7).



Example Input

Input 1:

 A = 2
Input 2:

 A = 3


Example Output

Output 1:

 3
Output 2:

 5


Example Explanation

Explanation 1:

 OFF OFF
 OFF ON 
 ON OFF
All lights off is also a valid signal which probably means 'bye'

Explanation 2:

 OFF OFF OFF
 OFF OFF ON
 OFF ON OFF 
 ON OFF OFF
 ON OFF ON 
    */
    public int waysToSendSignal(int A) {
        int mod = 1000 * 1000 * 1000 + 7;

        long[][] dp = new long[A + 1][2];

        for (int i = 0; i <= A; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if (i == 1) {
                dp[i][0] = 1;
                dp[i][1] = 1;
            } else {
                dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
                dp[i][1] = dp[i - 1][0];
            }
        }

        return (int) ((dp[A][0] + dp[A][1]) % mod);
    }

    /* Unique Paths in a Grid
    Problem Description
Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to reach (n, m). At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).

Now consider if some obstacles are added to the grids. How many unique paths would there be? An obstacle and empty space is marked as 1 and 0 respectively in the grid.



Problem Constraints
1 <= n, m <= 100
A[i][j] = 0 or 1



Input Format
Firts and only argument A is a 2D array of size n * m.



Output Format
Return an integer denoting the number of unique paths from (1, 1) to (n, m).



Example Input
Input 1:

 A = [
        [0, 0, 0]
        [0, 1, 0]
        [0, 0, 0]
     ]
Input 2:

 A = [
        [0, 0, 0]
        [1, 1, 1]
        [0, 0, 0]
     ]


Example Output
Output 1:

 2
Output 2:

 0


Example Explanation
Explanation 1:

 Possible paths to reach (n, m): {(1, 1), (1, 2), (1, 3), (2, 3), (3, 3)} and {(1 ,1), (2, 1), (3, 1), (3, 2), (3, 3)}  
 So, the total number of unique paths is 2. 
Explanation 2:

 It is not possible to reach (n, m) from (1, 1). So, ans is 0.
    */
    int uniquePathsWithObstaclesRecursive(int[][] A, int i, int j) {
        // no of ways to reach (i,j) from (0,0)
        if (i < 0 || j < 0) {
            return 0;
        }

        if (A[i][j] == 1) {
            return 0;
        }

        if (i == 0 && j == 0) {
            return 1;
        }

        return uniquePathsWithObstaclesRecursive(A, i - 1, j) + uniquePathsWithObstaclesRecursive(A, i, j - 1);
    }

    int uniquePathsWithObstaclesTabulation(int[][] A) {
        int N = A.length, M = A[0].length;
        if (A[0][0] == 1)
            return 0;

        int[][] dp = new int[N][M];

        dp[0][0] = 1;

        int j = 1;
        for (; j < M; j++) {
            if (A[0][j] == 0) {
                dp[0][j] = 1;
            } else {
                break;
            }
        }

        for (; j < M; j++) {
            dp[0][j] = 0;
        }

        int i = 1;
        for (; i < N; i++) {
            if (A[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }

        for (; i < N; i++) {
            dp[i][0] = 0;
        }

        for (i = 1; i < N; i++) {
            for (j = 1; j < M; j++) {
                if (A[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[N - 1][M - 1];
    }

    public int uniquePathsWithObstacles(int[][] A) {
        // int N = A.length, M = A[0].length;

        // return uniquePathsWithObstaclesRecursive(A, N-1, M-1);
        return uniquePathsWithObstaclesTabulation(A);
    }

    /*  Min Sum Path in Matrix
    Problem Description
Given a M x N grid A of integers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Return the minimum sum of the path.

NOTE: You can only move either down or right at any point in time.



Problem Constraints
1 <= M, N <= 2000

-1000 <= A[i][j] <= 1000



Input Format
First and only argument is a 2-D grid A.



Output Format
Return an integer denoting the minimum sum of the path.



Example Input
Input 1:

 A = [
       [1, 3, 2]
       [4, 3, 1]
       [5, 6, 1]
     ]
Input 2:

 A = [
       [1, -3, 2]
       [2, 5, 10]
       [5, -5, 1]
     ]


Example Output
Output 1:

 8
Output 2:

 -1


Example Explanation
Explanation 1:

 The path will be: 1 -> 3 -> 2 -> 1 -> 1.
Input 2:

 The path will be: 1 -> -3 -> 5 -> -5 -> 1.
    */
    int minPathSumTabulation(int[][] A) {
        int N = A.length, M = A[0].length;

        int[][] dp = new int[N][M];
        dp[0][0] = A[0][0];

        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + A[0][j];
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + A[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + A[i][j];
            }
        }

        return dp[N - 1][M - 1];
    }

    public int minPathSum(int[][] A) {
        return minPathSumTabulation(A);
    }

    /* Dungeon Princess
    Problem Description
The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Given a 2D array of integers A of size M x N. Find and return the knight's minimum initial health so that he is able to rescue the princess.



Problem Constraints
1 <= M, N <= 500

-100 <= A[i] <= 100



Input Format
First and only argument is a 2D integer array A denoting the grid of size M x N.



Output Format
Return an integer denoting the knight's minimum initial health so that he is able to rescue the princess.



Example Input
Input 1:

 A = [ 
       [-2, -3, 3],
       [-5, -10, 1],
       [10, 30, -5]
     ]
Input 2:

 A = [ 
       [1, -1, 0],
       [-1, 1, -1],
       [1, 0, -1]
     ]


Example Output
Output 1:

 7
Output 2:

 1


Example Explanation
Explanation 1:

 Initially knight is at A[0][0].
 If he takes the path RIGHT -> RIGHT -> DOWN -> DOWN, the minimum health required will be 7.
 At (0,0) he looses 2 health, so health becomes 5.
 At (0,1) he looses 3 health, so health becomes 2.
 At (0,2) he gains 3 health, so health becomes 5.
 At (1,2) he gains 1 health, so health becomes 6.
 At (2,2) he looses 5 health, so health becomes 1.
 At any point, the health point doesn't drop to 0 or below. So he can rescue the princess with minimum health 7.
 
Explanation 2:

 Take the path DOWN -> DOWN ->RIGHT -> RIGHT, the minimum health required will be 1.
*/
    int calculateMinimumHPTabulation(int[][] A) {
        int N = A.length, M = A[0].length;

        int[][] dp = new int[N][M];

        dp[N - 1][M - 1] = Math.max(1, 1 - A[N - 1][M - 1]);

        for (int i = N - 2; i >= 0; i--) {
            dp[i][M - 1] = Math.max(1, dp[i + 1][M - 1] - A[i][M - 1]);
        }

        for (int j = M - 2; j >= 0; j--) {
            dp[N - 1][j] = Math.max(1, dp[N - 1][j + 1] - A[N - 1][j]);
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i][j + 1], dp[i + 1][j]) - A[i][j]);
            }
        }

        return dp[0][0];
    }

    public int calculateMinimumHP(int[][] A) {
        return calculateMinimumHPTabulation(A);
    }

    /* Min Sum Path in Triangle
    Problem Description
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Adjacent numbers for jth number of row i is jth and (j+1)th numbers of row i+1 is



Problem Constraints
|A| <= 1000

A[i] <= 1000



Input Format
First and only argument is the vector of vector A defining the given triangle



Output Format
Return the minimum sum



Example Input
Input 1:

 
A = [ 
         [2],
        [3, 4],
       [6, 5, 7],
      [4, 1, 8, 3]
    ]
Input 2:

 A = [ [1] ]


Example Output
Output 1:

 11
Output 2:

 1


Example Explanation
Explanation 1:

 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
Explanation 2:

 Only 2 can be collected.
 */
    int minimumTotalInTriangleTabulation(ArrayList<ArrayList<Integer>> a) {
        int N = a.size();

        ArrayList<ArrayList<Integer>> dp = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N; i++) {
            dp.add(new ArrayList<Integer>());
        }

        dp.get(0).add(a.get(0).get(0));

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < a.get(i).size(); j++) {
                int x = Integer.MAX_VALUE;
                if (j - 1 >= 0) {
                    x = dp.get(i - 1).get(j - 1);
                }

                int y = Integer.MAX_VALUE;
                if (j < dp.get(i - 1).size()) {
                    y = dp.get(i - 1).get(j);
                }

                dp.get(i).add(a.get(i).get(j) + Math.min(x, y));
            }
        }

        int ans = dp.get(N - 1).get(0);
        for (int j = 1; j < dp.get(N - 1).size(); j++) {
            ans = Math.min(ans, dp.get(N - 1).get(j));
        }

        return ans;
    }

    public int minimumTotalInTriangle(ArrayList<ArrayList<Integer>> a) {

        return minimumTotalInTriangleTabulation(a);
    }
}

