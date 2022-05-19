package Greedy;
import java.util.*;

public class GreedyProblems {
    /* Free Cars
    Problem Description
Given two arrays, A and B of size N. A[i] represents the time by which you can buy the ith car without paying any money.

B[i] represents the profit you can earn by buying the ith car. It takes 1 minute to buy a car, so you can only buy the ith car when the current time <= A[i] - 1.

Your task is to find the maximum profit one can earn by buying cars considering that you can only buy one car at a time.

NOTE:

You can start buying from time = 0.
Return your answer modulo 109 + 7.


Problem Constraints
1 <= N <= 105
1 <= A[i] <= 109
0 <= B[i] <= 109



Input Format
The first argument is an integer array A represents the deadline for buying the cars.
The second argument is an integer array B represents the profit obtained after buying the cars.



Output Format
Return an integer denoting the maximum profit you can earn.



Example Input
Input 1:

 A = [1, 3, 2, 3, 3]
 B = [5, 6, 1, 3, 9]
Input 2:

 A = [3, 8, 7, 5]
 B = [3, 1, 7, 19]


Example Output
Output 1:

 20
Output 2:

 30


Example Explanation
Explanation 1:

 At time 0, buy car with profit 5.
 At time 1, buy car with profit 6.
 At time 2, buy car with profit 9.
 At time = 3 or after , you can't buy any car, as there is no car with deadline >= 4.
 So, total profit that one can earn is 20.
Explanation 2:

 At time 0, buy car with profit 3.
 At time 1, buy car with profit 1.
 At time 2, buy car with profit 7.
 At time 3, buy car with profit 19.
 We are able to buy all cars within their deadline. So, total profit that one can earn is 30.
    */
    public class FreeCarsSolution {
        class Car {
            public int saleEndTime;
            public int profit;

            public Car(int t, int p) {
                this.saleEndTime = t;
                this.profit = p;
            }
        }

        class CarComparator implements Comparator<Car> {

            public int compare(Car c1, Car c2) {
                return c1.saleEndTime - c2.saleEndTime;
            }
        }

        public int solve(int[] A, int[] B) {
            int n = A.length;

            Car[] cars = new Car[n];

            for (int i = 0; i < n; i++) {
                cars[i] = new Car(A[i], B[i]);
            }

            Arrays.sort(cars, new CarComparator());

            int t = 0;
            long maxProfit = 0;
            PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

            for (int i = 0; i < n; i++) {
                Car c = cars[i];

                if (t < c.saleEndTime) {
                    maxProfit += c.profit;
                    minHeap.offer(c.profit);
                    t++;
                } else if (t == c.saleEndTime) {
                    if (!minHeap.isEmpty() && c.profit > minHeap.peek()) {
                        int minProfitSoFar = minHeap.poll();
                        maxProfit -= minProfitSoFar;
                        maxProfit += c.profit;
                        minHeap.offer(c.profit);
                    }
                }
            }

            return (int) (maxProfit % (1000 * 1000 * 1000 + 7));
        }
    }
    
    /* Finish Maximum Jobs
    Problem Description
There are N jobs to be done, but you can do only one job at a time.

Given an array A denoting the start time of the jobs and an array B denoting the finish time of the jobs.

Your aim is to select jobs in such a way so that you can finish the maximum number of jobs.

Return the maximum number of jobs you can finish.



Problem Constraints
1 <= N <= 105

1 <= A[i] < B[i] <= 109



Input Format
The first argument is an integer array A of size N, denoting the start time of the jobs.
The second argument is an integer array B of size N, denoting the finish time of the jobs.



Output Format
Return an integer denoting the maximum number of jobs you can finish.



Example Input
Input 1:

 A = [1, 5, 7, 1]
 B = [7, 8, 8, 8]
Input 2:

 A = [3, 2, 6]
 B = [9, 8, 9]


Example Output
Output 1:

 2
Output 2:

 1


Example Explanation
Explanation 1:

 We can finish the job in the period of time: (1, 7) and (7, 8).
Explanation 2:

 Since all three jobs collide with each other. We can do only 1 job.
    */

    public class FinishMaximumJobsSolution {
        class Job {
            public int startTime;
            public int endTime;

            public Job(int s, int e) {
                this.startTime = s;
                this.endTime = e;
            }
        }

        class JobComparator implements Comparator<Job> {

            public int compare(Job j1, Job j2) {
                return j1.endTime - j2.endTime;
            }
        }

        public int solve(int[] A, int[] B) {
            int n = A.length;

            Job[] jobs = new Job[n];

            for (int i = 0; i < n; i++) {
                jobs[i] = new Job(A[i], B[i]);
            }

            Arrays.sort(jobs, new JobComparator());

            int res = 1;
            Job prev = jobs[0];

            for (int i = 1; i < n; i++) {
                Job j = jobs[i];

                if (j.startTime >= prev.endTime) {
                    res++;
                    prev = j;
                }
            }

            return res;
        }
    }
    
    /* Distribute Candy
    Problem Description
N children are standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum number of candies you must give?



Problem Constraints
1 <= N <= 105
-109 <= A[i] <= 109



Input Format
The first and only argument is an integer array A representing the rating of children.



Output Format
Return an integer representing the minimum candies to be given.



Example Input
Input 1:

 A = [1, 2]
Input 2:

 A = [1, 5, 2, 1]


Example Output
Output 1:

 3
Output 2:

 7


Example Explanation
Explanation 1:

 The candidate with 1 rating gets 1 candy and candidate with rating 2 cannot get 1 candy as 1 is its neighbor. 
 So rating 2 candidate gets 2 candies. In total, 2 + 1 = 3 candies need to be given out.
Explanation 2:

 Candies given = [1, 3, 2, 1]
    */
    public int distributeCandy(int[] A) {

        int n = A.length;

        int[] cl = new int[n];
        int[] cr = new int[n];

        cl[0] = 1;
        cr[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                cl[i] = cl[i - 1] + 1;
            } else {
                cl[i] = 1;
            }

            if (A[n - i - 1] > A[n - i]) {
                cr[n - i - 1] = cr[n - i] + 1;
            } else {
                cr[n - i - 1] = 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(cl[i], cr[i]);
        }

        return ans;
    }

    /* Assign Mice to Holes
    Problem Description
N Mice and N holes are placed in a straight line. Each hole can accommodate only one mouse.

The positions of Mice are denoted by array A, and the position of holes is denoted by array B.

A mouse can stay at his position, move one step right from x to x + 1, or move one step left from x to x − 1. Any of these moves consume 1 minute.

Assign mice to holes so that the time when the last mouse gets inside a hole is minimized.



Problem Constraints
1 <= N <= 105

-109 <= A[i], B[i] <= 109



Input Format
The first argument is an integer array A.

The second argument is an integer array B.



Output Format
Return an integer denoting the minimum time when the last nouse gets inside the holes.



Example Input
Input 1:

 A = [-4, 2, 3]
 B = [0, -2, 4]
Input 2:

 A = [-2]
 B = [-6]


Example Output
Output 1:

 2
Output 2:

 4


Example Explanation
Explanation 1:

 Assign the mouse at position (-4 to -2), (2 to 0) and (3 to 4).
 The number of moves required will be 2, 2 and 1 respectively.
 So, the time taken will be 2.
Explanation 2:

 Assign the mouse at position -2 to -6.
 The number of moves required will be 4.
 So, the time taken will be 4.
    */
    public int mice(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.abs(B[i] - A[i]));
        }

        return ans;
    }

    /* Seats
    Problem Description
There is a row of seats represented by string A. Assume that it contains N seats adjacent to each other.
There is a group of people who are already seated in that row randomly. i.e. some are sitting together & some are scattered.

An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')

Now your target is to make the whole group sit together i.e. next to each other, without having any vacant seat between them in such a way that the total number of hops or jumps to move them should be minimum.

In one jump a person can move to the adjacent seat (if available).

NOTE: 1. Return your answer modulo 107 + 3.



Problem Constraints
1 <= N <= 1000000
A[i] = 'x' or '.'



Input Format
The first and only argument is a string A of size N.



Output Format
Return an integer denoting the minimum number of jumps required.



Example Input
Input 1:

 A = "....x..xx...x.."
Input 2:

 A = "....xxx"


Example Output
Output 1:

 5
Output 2:

 0


Example Explanation
Explanation 1:

 Here is the row having 15 seats represented by the String (0, 1, 2, 3, ......... , 14) 
                 . . . . x . . x x . . . x . . 
 Now to make them sit together one of approaches is -
                 . . . . . . x x x x . . . . .
 Steps To achieve this:
 1) Move the person sitting at 4th index to 6th index: Number of jumps by him =   (6 - 4) = 2
 2) Bring the person sitting at 12th index to 9th index: Number of jumps by him = (12 - 9) = 3
 So, total number of jumps made: 2 + 3 = 5 which is the minimum possible.

 If we other ways to make them sit together but the number of jumps will exceed 5 and that will not be minimum.
 
Explanation 2:

 They are already together. So, the cost is zero.
    */
    public int seats(String A) {
        int mod = 1000 * 1000 * 10 + 3;

        ArrayList<Integer> pos = new ArrayList<Integer>();

        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == 'x') {
                pos.add(i);
            }
        }

        int size = pos.size();

        int mid = size / 2;

        int left = mid - 1, right = mid + 1, k = 1;

        long res = 0L;
        while (left >= 0) {
            res += pos.get(mid) - k - pos.get(left);
            left--;
            k++;
        }

        k = 1;
        while (right < pos.size()) {
            res += pos.get(right) - (pos.get(mid) + k);
            right++;
            k++;
        }

        return (int) (res % mod);

    }

    /* Another Coin Problem
    Problem Description
The monetary system in DarkLand is really simple and systematic. The locals-only use coins. The coins come in different values. The values used are:

 1, 5, 25, 125, 625, 3125, 15625, ...
Formally, for each K >= 0 there are coins worth 5K.

Given an integer A denoting the cost of an item, find and return the smallest number of coins necessary to pay exactly the cost of the item (assuming you have a sufficient supply of coins of each of the types you will need).



Problem Constraints
1 <= A <= 2×109



Input Format
The only argument given is integer A.



Output Format
Return the smallest number of coins necessary to pay exactly the cost of the item.



Example Input
Input 1:

 A = 47
Input 2:

 A = 9


Example Output
Output 1:

 7
Output 2:

 5


Example Explanation
Explanation 1:

 Representation of 7 coins will be : (1 + 1 + 5 + 5 + 5 + 5 + 25).
Explanation 2:

 Representation of 5 coins will be : (1 + 1 + 1 + 1 + 5).
    */
    public int AnotherCoinProblem(int A) {
        int i=1;
        while(i<A)
        {
            i = i*5;
        }

        if(i==A) return 1;

        i = i/5;

        int res = 0;

        while(i>0)
        {
            res += A/i;
            A = A % i;
            i = i/5;
        }

        return res;        
    }

    /* The ship company
    Problem Description
The local ship renting service has a special rate plan:

It is up to a passenger to choose a ship.
If the chosen ship has X (X > 0) vacant places at the given moment, then the ticket for such a ship costs X.
The passengers buy tickets in turn, the first person in the queue goes first, then the second one, and so on up to A-th person.

You need to tell the maximum and the minimum money that the ship company can earn if all A passengers buy tickets.



Problem Constraints
1 ≤ A ≤ 3000
1 ≤ B ≤ 1000
1 ≤ C[i] ≤ 1000
It is guaranteed that there are at least A empty seats in total.



Input Format
First argument is a integer A denoting the number of passengers in the queue.
Second arugument is a integer B deonting the number of ships.
Third argument is an integer array C of size B where C[i] denotes the number of empty seats in the i-th ship before the ticket office starts selling tickets.



Output Format
Return an array of size 2 denoting the maximum and minimum money that the ship company can earn.



Example Input
Input 1:

 A = 4
 B = 3
 C = [2, 1, 1]
Input 2:

 A = 4
 B = 3
 C = [2, 2, 2]


Example Output
Output 1:

 [5, 5]
Output 2:

 [7, 6]


Example Explanation
Explantion 1:

 Maximum money can be earned if the passenger choose : 2(first ship) + 1(first ship) + 1(second ship) + 1(third ship).
 So, the cost will be 5.
 Minimum money can be earned if the passenger choose : 1(senocd ship) + 2(first ship) + 1(first ship) + 1(third ship).
 So, the cost will be 5.
Explanation 2:

 Maximum money can be earned if the passenger choose : 2(first ship) + 2(second ship) + 2(third ship) + 1(first ship).
 So, the cost will be 7.
 Minimum money can be earned if the passenger choose : 2(senocd ship) + 2(first ship) + 1(first ship) + 1(second ship).
 So, the cost will be 6.
    */
    public int[] shipCompany(int A, int B, int[] C) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i = 0; i < C.length; i++) {
            minHeap.offer(C[i]);

            maxHeap.offer(C[i]);
        }

        int minCost = 0, maxCost = 0;
        for (int i = 1; i <= A; i++) {
            if (!maxHeap.isEmpty()) {
                int x = maxHeap.poll();

                maxCost += x;
                x--;
                if (x > 0) {
                    maxHeap.offer(x);
                }
            }

            if (!minHeap.isEmpty()) {
                int x = minHeap.poll();

                minCost += x;
                x--;
                if (x > 0) {
                    minHeap.offer(x);
                }
            }
        }

        return new int[] { maxCost, minCost };
    }

    /* Binary Strings
    Problem Description
You are given a string A consisting of 1's and 0's. Now the task is to make the string consisting of only 1's. But you are allowed to perform only the following operation:

Take exactly B consecutive string elements and change 1 to 0 and 0 to 1.
Each operation takes 1 unit time, so you have to determine the minimum time required to only make the string of 1's. If not possible, return -1.



Problem Constraints
2 ≤ length of A ≤ 105
2 ≤ B ≤ (length of A)



Input Format
The first argument is a string A consisting of 1's and 0's.
The second argument is an integer B which represents the number of consecutive elements which can be changed.



Output Format
Return an integer which is the minimum time to make the string of 1's only or -1 if not possible.



Example Input
Input 1:

 A = "00010110"
 B = 3
Input 2:

 A = "011"
 B = 3


Example Output
Output 1:

 3
Output 2:

 -1


Example Explanation
Explanation 1:

 You can get 1 by first changing the leftmost 3 elements, getting to 11110110, then the rightmost 3, getting to 11110001, 
 and finally the 3 left out 0's to 11111111; In 3 unit time.
Explanation 2:

It's not possible to convert the string into string of all 1's.
    */
    public int BinaryStrings(String A, int B) {
        int n = A.length();

        // create prefix array, initiall all set to 0. If we do an operation at index i,
        // we will do pf[i]++;
        int[] pf = new int[n];

        int ans = 0;

        for (int i = 0; i <= n - B; i++) {
            pf[i] = i == 0 ? pf[i] : pf[i - 1] + pf[i];

            if (pf[i] % 2 == 0) {
                if (A.charAt(i) == '0') {
                    ans++;
                    pf[i]++;
                    if (i + B < n) {
                        pf[i + B] = -1;
                    }
                } else {
                    continue;
                }
            } else {
                if (A.charAt(i) == '0') {
                    continue;
                } else {
                    ans++;
                    pf[i]++;
                    if (i + B < n) {
                        pf[i + B] = -1;
                    }
                }
            }
        }

        for (int i = n - B + 1; i < n; i++) {
            pf[i] = pf[i - 1] + pf[i];
        }

        for (int i = 0; i < n; i++) {
            if (pf[i] % 2 != 0) {
                if (A.charAt(i) != '0')
                    return -1;
            } else {

                if (A.charAt(i) != '1')
                    return -1;
            }
        }

        return ans;
    }

}
