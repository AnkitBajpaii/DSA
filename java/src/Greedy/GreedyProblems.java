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
    
}
