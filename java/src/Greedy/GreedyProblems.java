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
    
}
