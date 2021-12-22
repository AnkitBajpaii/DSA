package Maths;

import java.util.*;

public class mathProblems {
    /*
     * Rearrange Array
     * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra
     * space.
     * 
     * Example:
     * 
     * Input : [1, 0]
     * Return : [0, 1]
     * Lets say N = size of the array. Then, following holds true :
     * 
     * All elements in the array are in the range [0, N-1]
     * N * N does not overflow for a signed integer
     * 
     */
    public void ReArrange(ArrayList<Integer> A) {
        int n = A.size();

        for (int i = 0; i < n; i++) {
            A.set(i, A.get(i) * n);
        }

        for (int i = 0; i < n; i++) {
            A.set(i, A.get(i) + A.get(A.get(i) / n) / n);
        }

        for (int i = 0; i < n; i++) {
            A.set(i, A.get(i) % n);
        }
    }

    /*
     * A, B and Modulo
     * Given two integers A and B, find the greatest possible positive M, such that
     * A % M = B % M.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A, B <= 109
     * A != B
     * 
     * 
     * 
     * Input Format
     * 
     * The first argument given is the integer, A.
     * The second argument given is the integer, B.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting greatest possible positive M.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 1
     * B = 2
     * Input 2:
     * 
     * A = 5
     * B = 10
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 1
     * Output 2:
     * 
     * 5
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * 1 is the largest value of M such that A % M == B % M.
     * Explanation 2:
     * 
     * For M = 5, A % M = 0 and B % M = 0.
     * 
     * No value greater than M = 5, satisfies the condition.
     * 
     * 
     */
    public int FindMSuchThatAModMEqualsBModM(int A, int B) {
        return Math.abs(A - B);
    }

    /*
     * Implement Power Function
     * Problem Description
     * 
     * Implement pow(x, n) % d.
     * In other words, given x, n and d,
     * 
     * find (xn % d)
     * 
     * Note that remainders on division cannot be negative. In other words, make
     * sure the answer you return is non negative.
     * 
     * Input : x = 2, n = 3, d = 3
     * Output : 2
     * 
     * 2^3 % 3 = 8 % 3 = 2.
     * 
     */
    public int pow(int A, int B, int C) {

        if (A == 0)
            return 0;

        if (B == 0)
            return 1;

        if (B == 1)
            return (A + C) % C;

        if (B % 2 == 0) {
            long t = pow(A, B / 2, C);
            long ans = (t * t) % C;
            return (int) ans;
        }

        long t = pow(A, (B - 1) / 2, C);
        t = (t * t) % C;
        long ans = (A * t) % C;
        return (int) ans;
    }

    /*
     * Prime Modulo Inverse
     * Problem Description
     * 
     * Given two integers A and B. Find the value of A-1 mod B where B is a prime
     * number and gcd(A, B) = 1.
     * 
     * A-1 mod B is also known as modular multiplicative inverse of A under modulo
     * B.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A <= 109
     * 1<= B <= 109
     * B is a prime number
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an integer A.
     * Second argument is an integer B.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting the modulor inverse
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 3
     * B = 5
     * Input 2:
     * 
     * A = 6
     * B = 23
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 2
     * Output 2:
     * 
     * 4
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Let's say A-1 mod B = X, then (A * X) % B = 1.
     * 3 * 2 = 6, 6 % 5 = 1.
     * Explanation 2:
     * 
     * Similarly, (6 * 4) % 23 = 1.
     */
    public int PrimeModuloInverse(int A, int B) {
        return pow(A, B - 2, B); // fermets little theoram
    }

    /*
     * Pair Sum divisible by M
     * Given an array of integers A and an integer B, find and return the number of
     * pairs in A whose sum is divisible by B.
     * 
     * Since the answer may be large, return the answer modulo (109 + 7).
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= length of the array <= 100000
     * 1 <= A[i] <= 109
     * 1 <= B <= 106
     * 
     * 
     * 
     * Input Format
     * 
     * The first argument given is the integer array A.
     * The second argument given is the integer B.
     * 
     * 
     * 
     * Output Format
     * 
     * Return the total number of pairs for which the sum is divisible by B modulo
     * (109 + 7).
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [1, 2, 3, 4, 5]
     * B = 2
     * Input 2:
     * 
     * A = [5, 17, 100, 11]
     * B = 28
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 4
     * Output 2:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * All pairs which are divisible by 2 are (1,3), (1,5), (2,4), (3,5).
     * So total 4 pairs.
     * 
     */
    public int PairSumDivisibleByM(int[] A, int B) {

        long mod = (long) (1e9 + 7);
        long cnt[] = new long[B];
        for (int x : A)
            cnt[x % B]++;
        long ans = cnt[0] * (cnt[0] - 1) / 2;
        for (int i = 1, j = B - 1; i <= j; i++, j--) {
            if (i == j)
                ans = (ans + cnt[i] * (cnt[i] - 1) / 2) % mod;
            else
                ans = (ans + cnt[i] * cnt[j]) % mod;
        }
        return (int) ans;
    }

    /*
     * Delete one
     * Given an integer array A of size N. You have to delete one element such that
     * the GCD(Greatest common divisor) of the remaining array is maximum.
     * 
     * Find the maximum value of GCD.
     * Problem Constraints
     * 
     * 2 <= N <= 105
     * 1 <= A[i] <= 109
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an integer array A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting the maximum value of GCD.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [12, 15, 18]
     * Input 2:
     * 
     * A = [5, 15, 30]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 6
     * Output 2:
     * 
     * 15
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * 
     * If you delete 12, gcd will be 3.
     * If you delete 15, gcd will be 6.
     * If you delete 18, gcd will 3.
     * Maximum vallue of gcd is 6.
     * Explanation 2:
     * 
     * If you delete 5, gcd will be 15.
     * If you delete 15, gcd will be 5.
     * If you delete 30, gcd will be 5.
     */
    int gcd(int a, int b)
    {
        if(b==0) return a;

        return gcd(b, a%b);
    }

    public int DeleteOne(int[] A) {
        int[] l = new int[A.length];
        int[] r = new int[A.length];

        l[0] = A[0];
        for(int i=1; i<A.length; i++)
        {
            l[i] = gcd(l[i-1], A[i]);
        }

        r[r.length-1] = A[A.length-1];
        for(int i=A.length-2; i>=0; i--)
        {
            r[i] = gcd(r[i+1], A[i]);
        }

        int ans = Math.max(l[l.length-2], r[1]);
        for(int i=1; i<l.length-1; i++)
        {
            ans = Math.max(ans, gcd(l[i-1], r[i+1]));
        }
        return ans;
    }

    /*
     * Pubg
     * Problem Description
     * 
     * There are N players each with strength A[i]. when player i attack player j,
     * player j strength reduces to max(0, A[j]-A[i]). When a player's strength
     * reaches zero, it loses the game and the game continues in the same manner
     * among other players until only 1 survivor remains.
     * 
     * Can you tell the minimum health last surviving person can have?
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 100000
     * 
     * 1 <= A[i] <= 1000000
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument of input contains a single integer array A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a single integer denoting minimum health of last person.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [6, 4]
     * Input 2:
     * 
     * A = [2, 3, 4]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 2
     * Output 2:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Given strength array A = [6, 4]
     * Second player attack first player, A = [2, 4]
     * First player attack second player twice. [2, 0]
     * Explanation 2:
     * 
     * Given strength array A = [2, 3, 4]
     * First player attack third player twice. [2, 3, 0]
     * First player attack second player. [2, 1, 0]
     * Second player attack first player twice. [0, 1, 0]
     */
    public int pubG(int[] A) {
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            ans = gcd(ans, A[i]);
        }

        return ans;
    }

    /*
     * Largest Coprime Divisor
     * Problem Description
     * 
     * You are given two positive numbers A and B . You need to find the maximum
     * valued integer X such that:
     * 
     * X divides A i.e. A % X = 0
     * X and B are co-prime i.e. gcd(X, B) = 1
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A, B <= 109
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an integer A.
     * Second argument is an integer B.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer maximum value of X as descibed above.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 30
     * B = 12
     * Input 2:
     * 
     * A = 5
     * B = 10
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 5
     * Output 2:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * All divisors of A are (1, 2, 3, 5, 6, 10, 15, 30).
     * The maximum value is 5 such that A%5 == 0 and gcd(5,12) = 1
     * Explanation 2:
     * 
     * 1 is the only value such that A%5 == 0 and gcd(1,10) = 1
     */
    public int cpFact(int A, int B) {

        int t = gcd(A, B);

        while (t != 1) {
            A = A / t;
            t = gcd(A, B);
        }

        return A;
    }

    /*
     * Count of divisors
     * Given an array of integers A, find and return the count of divisors of each
     * element of the array.
     * 
     * NOTE: Order of the resultant array should be same as the input array.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= length of the array <= 100000
     * 1 <= A[i] <= 106
     * 
     * 
     * 
     * Input Format
     * 
     * The only argument given is the integer array A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return the count of divisors of each element of the array in the form of an
     * array.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [2, 3, 4, 5]
     * Input 2:
     * 
     * A = [8, 9, 10]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [2, 2, 3, 2]
     * Output 1:
     * 
     * [4, 3, 4]
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * The number of divisors of 2 : [1, 2], 3 : [1, 3], 4 : [1, 2, 4], 5 : [1, 5]
     * So the count will be [2, 2, 3, 2].
     * Explanation 2:
     * 
     * The number of divisors of 8 : [1, 2, 4, 8], 9 : [1, 3, 9], 10 : [1, 2, 5, 10]
     * So the count will be [4, 3, 4].
     */
    ;
    
    int[] sieve_smallestPrimeFactorForAllNumbersFrom1ToN(int N){
        
        int[] spf = new int[N+1];
        
        for(int i=1; i<=N; i++){
            spf[i] = i;
        }

        for(int i=2; i*i<=N; i++)
        {
            if(spf[i] == i)
            {
                for(int j=i*i; j<=N; j=j+i)
                {
                    spf[j] = Math.min(spf[j], i);
                }
            }
        }

        return spf;
    }

    int countDivisor_optimized(int n, int[] spf)
    {
        int ans = 1;
        while(n>1)
        {
            int count = 0;
            int x = spf[n];
            while(n%x == 0)
            {
                count++;
                n = n/x;
            }

            ans = ans * (count+1);
        }

        return ans;
    }

    public int[] countDivisors(int[] A) {

        int n = 1000000; // some very big n

        int[] spf = sieve_smallestPrimeFactorForAllNumbersFrom1ToN(n);

        int[] res = new int[A.length];        

        for(int i=0; i<A.length; i++)
        {
            res[i] = countDivisor_optimized(A[i], spf);            
        }

        return res;
    }
    /* Find all prime numbers from 1 to N
       Idea: Sieve algorithm
    */
    public ArrayList<Integer> findAllPrimeNumbersFrom1ToN(int N){

        ArrayList<Integer> ans = new ArrayList<Integer>();

        boolean[] prime = new boolean[N+1];
        prime[0] = false;
        prime[1] = false;
        for(int i=2; i<=N; i++)
        {
            prime[i] = true;
        }

        for(int i=2; i*i<=N; i++) // optimized outer loop 
        {
            if(prime[i])
            {
                ans.add(i);

                for(int j=i*i; j<=N; j=j+i) // optimized inner loop to start from i*i
                {
                    prime[j] = false; // will be executed Log(Log(N)) times N/2+N/3+N/5+N/7+....N/K
                }
            }
        }

        return ans;

        //TC: O(N*Log(Log(N)))
    }

    /*
     * Prime Sum
     * Given an even number A ( greater than 2 ), return two prime numbers whose sum
     * will be equal to given number.
     * 
     * Goldbach's conjecture states that every even integer greater than 2 can be expressed as the sum of two primes.
     * 
     * If there are more than one solutions possible, return the lexicographically
     * smaller solution.
     * 
     * If [a, b] is one solution with a <= b, and [c,d] is another solution with c
     * <= d, then
     * [a, b] < [c, d], If a < c OR a==c AND b < d.
     * NOTE: A solution will always exist. Read Goldbach's conjecture.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 4 <= A <= 2*107
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument of input is an even number A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a integer array of size 2 containing primes whose sum will be equal to
     * given number.
     * 
     * 
     * 
     * Example Input
     * 
     * 4
     * 
     * 
     * Example Output
     * 
     * [2, 2]
     * 
     * 
     * Example Explanation
     * 
     * There is only 1 solution for A = 4.
     */
    public int[] primesum(int A) {
        boolean[] prime = new boolean[A + 1];
        for (int i = 2; i <= A; i++) {
            prime[i] = true;
        }

        for (int i = 2; i * i <= A; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= A; j = j + i) {
                    prime[j] = false;
                }
            }
        }

        int[] res = new int[2];

        for (int i = 2; i < A; i++) {
            if (prime[i]) {
                int p1 = i;
                int p2 = A - p1;
                if (prime[p2]) {
                    res[0] = p1;
                    res[1] = p2;
                    break;
                }
            }
        }

        return res;
    }

    /*
     * Lucky Numbers
     * A lucky number is a number which has exactly 2 distinct prime divisors.
     * 
     * You are given a number A and you need to determine the count of lucky numbers
     * between the range 1 to A (both inclusive).
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A <= 50000
     * 
     * 
     * 
     * Input Format
     * 
     * The first and only argument is an integer A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer i.e the count of lucky numbers between 1 and A, both
     * inclusive.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 8
     * Input 2:
     * 
     * A = 12
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 1
     * Output 2:
     * 
     * 3
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Between [1, 8] there is only 1 lucky number i.e 6.
     * 6 has 2 distinct prime factors i.e 2 and 3.
     * Explanation 2:
     * 
     * Between [1, 12] there are 3 lucky number: 6, 10 and 12.
     */
    public int luckyNumbers(int A) {        

        int[] cnt = new int[A+1];

        for(int i=2; 2*i<=A; i++)
        {
            if(cnt[i] == 0)
            {
                for(int j=2*i; j<=A; j=j+i)
                {
                    cnt[j]++;
                }
            }
        }

        int ans = 0;
        for(int i=2; i<=A; i++)
        {
            if(cnt[i] == 2)
            {
                ans++;
            }
        }

        return ans;
    }

    /*
     * Number Of Open Doors
     * Given an integer A which denotes the number of doors in a row numbered 1 to
     * A. All the doors are closed initially.
     * 
     * A person moves to and fro changing the states of the doors as follows: the
     * person opens a door that is already closed and closes a door that is already
     * opened.
     * 
     * In the first go, he/she alters the states of doors numbered 1, 2, 3, … , A.
     * In the second go, he/she alters the states of doors numbered 2, 4, 6 ….
     * In the third go, he/she alters the states of doors numbered 3, 6, 9 …
     * This continues till the A'th go in which you alter the state of the door
     * numbered A.
     * 
     * Find and return the number of open doors at the end of the procedure.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A <= 109
     * 
     * 
     * 
     * Input Format
     * 
     * The only argument given is integer A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return the number of open doors at the end of the procedure.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 5
     * Input 2:
     * 
     * A = 6
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 2
     * Output 2:
     * 
     * 2
     * 
     * 
     * Example Explanation
     * 
     * Input 1:
     * 
     * In the first go, he/she alters the states of doors numbered 1, 2, 3, 4, 5.
     * Now, all doors are open.
     * In the second go, he/she closes the doors numbered 2, 4.
     * In the third go, he/she closes the door numbered 3.
     * In the fourth go, he/she open the door numbered 4.
     * In the fifth go, he/she closes the door numbered 5.
     * Doors opened at the end are 1 and 4.
     * Input 2:
     * 
     * In the first go, he/she alters the states of doors numbered 1, 2, 3, 4, 5, 6.
     * Now, all doors are open.
     * In the second go, he/she closes the doors numbered 2, 4, 6.
     * In the third go, he/she closes the door numbered 3 and opens door 6.
     * In the fourth go, he/she open the door numbered 4.
     * In the fifth go, he/she closes the door numbered 5.
     * In the sixth go, he/she closes the door numbered 6.
     * Doors opened at the end are 1 and 4.
     */
    public int noOfOpenDoors(int A) {

        /*
         * We observes that the number of time a door X alter its state is the number of
         * factors of that door X.
         * If the number of factor is even then door will be closed else it will be
         * open.
         * 
         * So, we need to find the numbers between 1 to A for which the number of factor
         * is odd.
         * 
         * This leads to a very interesting observation that only the number which are
         * perfect square have odd number of factors.
         * How?
         * If ‘a’ is a factor of ‘X’ then there will be a ‘b’ such that ‘a’ * ‘b’ = X.
         * Only a number which is perfect square has a factor ‘a’ such that ‘a’ * ‘a’ =
         * X.
         * 
         * So we will count the number of perfect squares between 1 and A and that will
         * be sqrt(A).
         */

        return (int) Math.sqrt(A);
    }

    /*
     * Distinct Primes
     * You have given an array A having N integers. Let say G is the product of all
     * elements of A.
     * 
     * You have to find the number of distinct prime divisors of G.
     * 
     * 
     * 
     * Input Format
     * 
     * The first argument given is an Array A, having N integers.
     * Output Format
     * 
     * Return an Integer, i.e number of distinct prime divisors of G.
     * Constraints
     * 
     * 1 <= N <= 1e5
     * 1 <= A[i] <= 1e5
     * For Example
     * 
     * Input:
     * A = [1, 2, 3, 4]
     * Output:
     * 2
     * 
     * Explanation:
     * here G = 1 * 2 * 3 * 4 = 24
     * and distinct prime divisors of G are [2, 3]
     */
    public int distinctPrimes(int[] A) {

        int[] spf = sieve_smallestPrimeFactorForAllNumbersFrom1ToN(100000);
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < A.length; i++) {
            int n = A[i];

            if (n > 1) {

                while (n > 1) {
                    int x = spf[n];
                    while (n % x == 0) {
                        n = n / x;
                    }

                    set.add(x);
                }
            }
        }

        return set.size();
    }

}
