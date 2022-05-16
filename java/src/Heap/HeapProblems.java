package Heap;
import java.util.*;

public class HeapProblems {

    /* Magician and Chocolates
    Problem Description
Given N bags, each bag contains Bi chocolates. There is a kid and a magician.
In a unit of time, the kid can choose any bag i, and eat Bi chocolates from it, then the magician will fill the ith bag with floor(Bi/2) chocolates.

Find the maximum number of chocolates that the kid can eat in A units of time.

NOTE:

floor() function returns the largest integer less than or equal to a given number.
Return your answer modulo 109+7


Problem Constraints
1 <= N <= 100000
0 <= B[i] <= INT_MAX
0 <= A <= 105



Input Format
The first argument is an integer A.
The second argument is an integer array B of size N.



Output Format
Return an integer denoting the maximum number of chocolates the kid can eat in A units of time.



Example Input
Input 1:

 A = 3
 B = [6, 5]
Input 2:

 A = 5
 b = [2, 4, 6, 8, 10]


Example Output
Output 1:

 14
Output 2:

 33


Example Explanation
Explanation 1:

 At t = 1 kid eats 6 chocolates from bag 0, and the bag gets filled by 3 chocolates. 
 At t = 2 kid eats 5 chocolates from bag 1, and the bag gets filled by 2 chocolates. 
 At t = 3 kid eats 3 chocolates from bag 0, and the bag gets filled by 1 chocolate. 
 so, total number of chocolates eaten are 6 + 5 + 3 = 14
Explanation 2:

 Maximum number of chocolates that can be eaten is 33.
    */
    public int nchoc(int A, int[] B) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int b : B) {
            maxHeap.offer(b);
        }

        long ans = 0;
        for (int t = 1; t <= A; t++) {
            int c = maxHeap.poll();
            ans += c;
            if (c / 2 > 0) {
                maxHeap.add(c / 2);
            }

            if (maxHeap.size() == 0)
                break;
        }

        return (int) (ans % (1000 * 1000 * 1000 + 7));
    }

    /* Connect ropes
    Problem Description
You are given an array A of integers that represent the lengths of ropes.

You need to connect these ropes into one rope. The cost of joining two ropes equals the sum of their lengths.

Find and return the minimum cost to connect these ropes into one rope.



Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 1000



Input Format
The only argument given is the integer array A.



Output Format
Return an integer denoting the minimum cost to connect these ropes into one rope.



Example Input
Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [5, 17, 100, 11]


Example Output
Output 1:

 33
Output 2:

 182


Example Explanation
Explanation 1:

 Given array A = [1, 2, 3, 4, 5].
 Connect the ropes in the following manner:
 1 + 2 = 3
 3 + 3 = 6
 4 + 5 = 9
 6 + 9 = 15

 So, total cost  to connect the ropes into one is 3 + 6 + 9 + 15 = 33.
Explanation 2:

 Given array A = [5, 17, 100, 11].
 Connect the ropes in the following manner:
 5 + 11 = 16
 16 + 17 = 33
 33 + 100 = 133

 So, total cost  to connect the ropes into one is 16 + 33 + 133 = 182.
    */

    public int ConnectRopes(int[] A) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for (int x : A) {
            minHeap.offer(x);
        }

        int overAllCost = 0;

        while (minHeap.size() > 1) {
            int c1 = minHeap.poll();
            int c2 = minHeap.poll();

            int c = c1 + c2;

            overAllCost += c;

            minHeap.offer(c);
        }

        return overAllCost;
    }

    /* Product of 3

    Problem Description
Given an integer array A of size N.

You have to find the product of the three largest integers in array A from range 1 to i, where i goes from 1 to N.

Return an array B where B[i] is the product of the largest 3 integers in range 1 to i in array A. If i < 3, then the integer at index i in B should be -1.



Problem Constraints
1 <= N <= 105
0 <= A[i] <= 103



Input Format
First and only argument is an integer array A.



Output Format
Return an integer array B. B[i] denotes the product of the largest 3 integers in range 1 to i in array A.



Example Input
Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [10, 2, 13, 4]


Example Output
Output 1:

 [-1, -1, 6, 24, 60]
Output 2:

 [-1, -1, 260, 520]


Example Explanation
Explanation 1:

 For i = 1, ans = -1
 For i = 2, ans = -1
 For i = 3, ans = 1 * 2 * 3 = 6
 For i = 4, ans = 2 * 3 * 4 = 24
 For i = 5, ans = 3 * 4 * 5 = 60

 So, the output is [-1, -1, 6, 24, 60].
 
Explanation 2:

 For i = 1, ans = -1
 For i = 2, ans = -1
 For i = 3, ans = 10 * 2 * 13 = 260
 For i = 4, ans = 10 * 13 * 4 = 520

 So, the output is [-1, -1, 260, 520].
    */
    public int[] productOf3(int[] A) {
        int[] res = new int[A.length];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i = 0; i < A.length; i++) {
            if (i == 2)
                break;

            res[i] = -1;

            maxHeap.offer(A[i]);
        }

        for (int i = 2; i < A.length; i++) {
            maxHeap.offer(A[i]);

            int a = maxHeap.poll();
            int b = maxHeap.poll();
            int c = maxHeap.poll();

            int product = a * b * c;

            res[i] = product;

            maxHeap.offer(a);
            maxHeap.offer(b);
            maxHeap.offer(c);
        }

        return res;
    }

    /* Maximum array sum after B negations
    Problem Description
Given an array of integers A and an integer B. You must modify the array exactly B number of times. In a single modification, we can replace any one array element A[i] by -A[i].

You need to perform these modifications in such a way that after exactly B modifications, sum of the array must be maximum.



Problem Constraints
1 <= length of the array <= 5*105
1 <= B <= 5 * 106
-100 <= A[i] <= 100



Input Format
The first argument given is an integer array A.
The second argument given is an integer B.



Output Format
Return an integer denoting the maximum array sum after B modifications.



Example Input
Input 1:

 A = [24, -68, -29, -9, 84]
 B = 4
Input 2:

 A = [57, 3, -14, -87, 42, 38, 31, -7, -28, -61]
 B = 10


Example Output
Output 1:

 196
Output 2:

 362


Example Explanation
Explanation 1:

 Final array after B modifications = [24, 68, 29, -9, 84]
Explanation 2:

 Final array after B modifications = [57, -3, 14, 87, 42, 38, 31, 7, 28, 61]
    */
    public int MaximumArraySumAfterBNegations(int[] A, int B) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for (int x : A) {
            minHeap.offer(x);
        }

        while (B > 0) {
            int minElement = minHeap.poll();

            if (minElement < 0) {
                minHeap.offer(-minElement);
            } else {
                if (B % 2 != 0) {
                    minHeap.offer(-minElement);
                } else {
                    minHeap.offer(minElement);
                }

                break;
            }

            B--;
        }

        int ans = 0;

        while (minHeap.size() > 0) {
            ans += minHeap.poll();
        }

        return ans;
    }

    public int[] KSmallestElements(int[] A, int k) {
        int[] res = new int[k];
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            maxHeap.offer(A[i]);
        }

        for (int i = k; i < A.length; i++) {
            if (A[i] < maxHeap.peek()) {
                maxHeap.poll();

                maxHeap.offer(A[i]);
            }
        }

        int i = 0;

        while (!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }

        return res;
    }

    /*
     * Sort K Sorted array
     * Given an array, which is nearly sorted (or what we call K-Sorted i.e every
     * eleemnt is atmost K positions away from it's sorted position).
     * You need to sort the array optimally.
     */
    public void SortKSortedArray(int[] A, int K) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for (int i = 0; i <= K; i++) {
            minHeap.offer(A[i]);
        }

        int i = 0;
        A[i++] = minHeap.poll();

        for (int j = K + 1; j < A.length; j++) {
            minHeap.offer(A[j]);

            A[i++] = minHeap.poll();
        }

        while (!minHeap.isEmpty()) {
            A[i++] = minHeap.poll();
        }
    }

    public void MaxHeapify(int[] A, int index, int hSize) {
        int l = 2 * index + 1;

        int r = 2 * index + 2;

        int maxIndex = index;

        if (l < hSize && A[l] > A[maxIndex]) {
            maxIndex = l;
        }

        if (r < hSize && A[r] > A[maxIndex]) {
            maxIndex = r;
        }

        if (maxIndex != index) {
            MaxHeapify(A, maxIndex, hSize);
        }
    }

    public void BuildMaxHeap(int[] A) {
        // O(N)
        int n = A.length;

        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            MaxHeapify(A, i, n);
        }
    }

    public void HeapSort(int[] A) {
        BuildMaxHeap(A);

        for (int i = A.length - 1; i >= 0; i--) {

            swap(A, 0, i);

            MaxHeapify(A, 0, i);
        }
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /* Given a stream of integers, find the median with every insertion
    */
    public int[] MedianWithEveryInsertion(int[] A)
    {
        PriorityQueue<Integer> left = new PriorityQueue<Integer>(Collections.reverseOrder());

        PriorityQueue<Integer> right = new PriorityQueue<Integer>();

        int[] res = new int[A.length];

        left.offer(A[0]);
        res[0] = A[0];

        for(int i=1; i<A.length; i++)
        {
            if (A[i] <= left.peek()) {
                left.offer(A[i]);
            } else {
                right.offer(A[i]);
            }


            if (right.size() > left.size()) {
                left.offer(right.poll());
            }

            else if (left.size() - right.size() > 1) {
                right.offer(left.poll());

            }

            int median;

            if((left.size() + right.size()) % 2 == 0)
            {
                median = left.peek() + right.peek();

            } else {
                median = left.peek();
            }

            res[i] = median;
        }

        return res;
    }

    /* B Closest Points to Origin
    Problem Description
We have a list A of points (x, y) on the plane. Find the B closest points to the origin (0, 0).

Here, the distance between two points on a plane is the Euclidean distance.

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.)

NOTE: Euclidean distance between two points P1(x1, y1) and P2(x2, y2) is sqrt( (x1-x2)2 + (y1-y2)2 ).



Problem Constraints
1 <= B <= length of the list A <= 105
-105 <= A[i][0] <= 105
-105 <= A[i][1] <= 105



Input Format
The argument given is list A and an integer B.



Output Format
Return the B closest points to the origin (0, 0) in any order.



Example Input
Input 1:

 A = [ 
       [1, 3],
       [-2, 2] 
     ]
 B = 1
Input 2:

 A = [
       [1, -1],
       [2, -1]
     ] 
 B = 1


Example Output
Output 1:

 [ [-2, 2] ]
Output 2:

 [ [1, -1] ]


Example Explanation
Explanation 1:

 The Euclidean distance will be sqrt(10) for point [1,3] and sqrt(8) for point [-2,2].
 So one closest point will be [-2,2].
Explanation 2:

 The Euclidean distance will be sqrt(2) for point [1,-1] and sqrt(5) for point [2,-1].
 So one closest point will be [1,-1].
    */
    public class B_ClosestPointsToOrigin {
        class PairComparator implements Comparator<Pair> {

            public int compare(Pair p1, Pair p2) {
                int ed1 = p1.getEuclideanDistFromOrigin();
                int ed2 = p2.getEuclideanDistFromOrigin();

                if (ed1 > ed2)
                    return -1;

                if (ed1 < ed2)
                    return 1;

                return 0;
            }
        }

        class Pair {
            public int x;
            public int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getEuclideanDistFromOrigin() {
                return this.x * this.x + this.y * this.y;
            }
        }

        public int[][] solve(int[][] A, int B) {

            PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(B, new PairComparator());

            for (int i = 0; i < B; i++) {
                int[] pairArr = A[i];

                Pair pair = new Pair(pairArr[0], pairArr[1]);

                maxHeap.offer(pair);
            }

            for (int i = B; i < A.length; i++) {
                int[] pairArr = A[i];

                Pair currPair = new Pair(pairArr[0], pairArr[1]);

                Pair maxPair = maxHeap.peek();

                if (currPair.getEuclideanDistFromOrigin() < maxPair.getEuclideanDistFromOrigin()) {
                    maxHeap.poll();

                    maxHeap.offer(currPair);
                }
            }

            int[][] res = new int[B][2];
            int i = 0;

            while (!maxHeap.isEmpty()) {
                Pair p = maxHeap.poll();

                res[i][0] = p.x;
                res[i][1] = p.y;

                i++;
            }

            return res;
        }
    }

    /* Misha and Candies
    Problem Description
Misha loves eating candies. She has been given N boxes of Candies.

She decides that every time she will choose a box having the minimum number of candies, eat half of the candies and put the remaining candies in the other box that has the minimum number of candies.
Misha does not like a box if it has the number of candies greater than B so she won't eat from that box. Can you find how many candies she will eat?

NOTE 1: If a box has an odd number of candies then Misha will eat the floor(odd / 2).

NOTE 2: The same box will not be chosen again.



Problem Constraints
1 <= N <= 105

1 <= A[i] <= 105

1 <= B <= 106



Input Format
The first argument is A an Array of Integers, where A[i] is the number of candies in the ith box.
The second argument is B, the maximum number of candies Misha like in a box.



Output Format
Return an integer denoting the number of candies Misha will eat.



Example Input
Input 1:

 A = [3, 2, 3]
 B = 4
Input 2:

 A = [1, 2, 1]
 B = 2


Example Output
Output 1:

 2
Output 2:

 1


Example Explanation
Explanation 1:

 1st time Misha will eat from 2nd box, i.e 1 candy she'll eat and will put the remaining 1 candy in the 1st box.
 2nd time she will eat from the 3rd box, i.e 1 candy she'll eat and will put the remaining 2 candies in the 1st box.
 She will not eat from the 3rd box as now it has candies greater than B.
 So the number of candies Misha eat is 2.
Explanation 2:

 1st time Misha will eat from 1st box, i.e she can't eat any and will put the remaining 1 candy in the 3rd box.
 2nd time she will eat from the 3rd box, i.e 1 candy she'll eat and will put the remaining 1 candies in the 2nd box.
 She will not eat from the 2nd box as now it has candies greater than B.
 So the number of candies Misha eat is 1.
    */
    public int MishaAndCandies(int[] A, int B) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for (int x : A) {
            minHeap.offer(x);
        }

        int ans = 0;

        while (!minHeap.isEmpty()) {
            int x = minHeap.poll();

            if (x <= B) {
                int y = x / 2;

                ans += y;

                if (minHeap.isEmpty())
                    break;

                int remaining = x - y;

                int otherMin = minHeap.poll();

                minHeap.offer((otherMin + remaining));
            }
        }

        return ans;
    }

    /* Kth Smallest Element in a Sorted Matrix
    Problem Description
Given a sorted matrix of integers A of size N x M and an integer B.

Each of the rows and columns of matrix A is sorted in ascending order, find the Bth smallest element in the matrix.

NOTE: Return The Bth smallest element in the sorted order, not the Bth distinct element.



Problem Constraints
1 <= N, M <= 500

1 <= A[i] <= 109

1 <= B <= N * M



Input Format
The first argument given is the integer matrix A.
The second argument given is an integer B.



Output Format
Return the B-th smallest element in the matrix.



Example Input
Input 1:

 A = [ [9, 11, 15],
       [10, 15, 17] ] 
 B = 6
Input 2:

 A = [  [5, 9, 11],
        [9, 11, 13],
        [10, 12, 15],
        [13, 14, 16],
        [16, 20, 21] ]
 B = 12


Example Output
Output 1:

 17
Output 2:

 16


Example Explanation
Explanation 1:

 6th smallest element in the sorted matrix is 17.
Explanation 2:

 12th smallest element in the sorted matrix is 16.
    */
    public int KthSmallestInSortedMatrix(int[][] A, int B) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (maxHeap.size() < B) {
                    maxHeap.offer(A[i][j]);
                } else {
                    if (A[i][j] < maxHeap.peek()) {
                        maxHeap.poll();

                        maxHeap.offer(A[i][j]);
                    }
                }
            }
        }

        return maxHeap.peek();
    }

    /* B-th Smallest Prime Fraction
    Problem Description
Given a sorted array of integers A which contains 1 and some number of primes.
Then, for every p < q in the list, we consider the fraction p / q.

What is the B-th smallest fraction considered?

Return your answer as an array of integers, where answer[0] = p and answer[1] = q.



Problem Constraints
1 <= length(A) <= 2000
1 <= A[i] <= 30000
1 <= B <= length(A)*(length(A) - 1)/2



Input Format
The first argument of input contains the integer array, A.
The second argument of input contains an integer B.



Output Format
Return an array of two integers, where answer[0] = p and answer[1] = q.



Example Input
Input 1:

 A = [1, 2, 3, 5]
 B = 3
Input 2:

 A = [1, 7]
 B = 1


Example Output
Output 1:

 [2, 5]
Output 2:

 [1, 7]


Example Explanation
Explanation 1:

 The fractions to be considered in sorted order are:
 [1/5, 1/3, 2/5, 1/2, 3/5, 2/3]
 The third fraction is 2/5.
Explanation 2:

 The fractions to be considered in sorted order are:
 [1/7]
 The first fraction is 1/7.
    */

    public class BthSmallestPrimeFractionSolution {
        class Pair {
            public int p;
            public int q;

            public Pair(int p, int q) {
                this.p = p;
                this.q = q;
            }
        }

        class PairComparator implements Comparator<Pair> {

            public int compare(Pair p1, Pair p2) {
                double f1 = (double) p1.p / p1.q;
                double f2 = (double) p2.p / p2.q;

                if (f1 < f2)
                    return 1;

                if (f2 < f1)
                    return -1;

                return 0;
            }

        }

        public int[] solve(int[] A, int B) {
            PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(new PairComparator());

            for (int i = 0; i < A.length - 1; i++) {
                for (int j = A.length - 1; j > i; j--) {
                    Pair p = new Pair(A[i], A[j]);

                    if (maxHeap.size() < B) {
                        maxHeap.offer(p);
                    } else {
                        double f = (double) A[i] / A[j];
                        double maxFrac = (double) maxHeap.peek().p / maxHeap.peek().q;
                        if (f < maxFrac) {
                            maxHeap.poll();

                            maxHeap.offer(p);
                        }
                    }
                }
            }

            Pair bthSmallestPair = maxHeap.peek();
            return new int[] { (int) bthSmallestPair.p, (int) bthSmallestPair.q };
        }
    }

    /* Running Median
    Problem Description
Given an array of integers, A denoting a stream of integers. New arrays of integer B and C are formed.
Each time an integer is encountered in a stream, append it at the end of B and append the median of array B at the C.

Find and return the array C.

NOTE:

If the number of elements is N in B and N is odd, then consider the median as B[N/2] ( B must be in sorted order).
If the number of elements is N in B and N is even, then consider the median as B[N/2-1]. ( B must be in sorted order).


Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 109



Input Format
The only argument given is the integer array A.



Output Format
Return an integer array C, C[i] denotes the median of the first i elements.



Example Input
Input 1:

 A = [1, 2, 5, 4, 3]
Input 2:

 A = [5, 17, 100, 11]


Example Output
Output 1:

 [1, 1, 2, 2, 3]
Output 2:

 [5, 5, 17, 11]


Example Explanation
Explanation 1:

 stream          median
 [1]             1
 [1, 2]          1
 [1, 2, 5]       2
 [1, 2, 5, 4]    2
 [1, 2, 5, 4, 3] 3
Explanation 2:

 stream          median
 [5]              5
 [5, 17]          5
 [5, 17, 100]     17
 [5, 17, 100, 11] 11 
    */
    public int[] RunningMedian(int[] A) {
        //max heap
        PriorityQueue<Integer> left = new PriorityQueue<Integer>(Collections.reverseOrder());

        // min heap
        PriorityQueue<Integer> right = new PriorityQueue<Integer>();

        int[] res = new int[A.length];

        left.offer(A[0]);
        res[0] = A[0];

        for(int i=1; i<A.length; i++)
        {
            if (A[i] <= left.peek()) {
                left.offer(A[i]);
            } else {
                right.offer(A[i]);
            }

            if (right.size() > left.size()) {
                left.offer(right.poll());
            }

            else if (left.size() - right.size() > 1) {
                right.offer(left.poll());

            }

            int median;

            if((left.size() + right.size()) % 2 == 0)
            {
                median = left.peek(); // ideally it should be , left max + right min / 2, but we are going with problem constraint

            } else {
                median = left.peek();
            }

            res[i] = median;
        }

        return res;
    }

    /* Ath largest element
    Problem Description
Given an integer array B of size N.

You need to find the Ath largest element in the subarray [1 to i], where i varies from 1 to N. In other words, find the Ath largest element in the sub-arrays [1 : 1], [1 : 2], [1 : 3], ...., [1 : N].

NOTE: If any subarray [1 : i] has less than A elements, then the output should be -1 at the ith index.



Problem Constraints
1 <= N <= 100000
1 <= A <= N
1 <= B[i] <= INT_MAX



Input Format
The first argument is an integer A.
The second argument is an integer array B of size N.



Output Format
Return an integer array C, where C[i] (1 <= i <= N) will have the Ath largest element in the subarray [1 : i].



Example Input
Input 1:

 A = 4  
 B = [1 2 3 4 5 6] 
Input 2:

 A = 2
 B = [15, 20, 99, 1]


Example Output
Output 1:

 [-1, -1, -1, 1, 2, 3]
Output 2:

 [-1, 15, 20, 20]


Example Explanation
Explanation 1:

 for i <= 3 output should be -1.
 for i = 4, Subarray [1:4] has 4 elements 1, 2, 3 and 4. So, 4th maximum element is 1.
 for i = 5, Subarray [1:5] has 5 elements 1, 2, 3, 4 and 5. So, 4th maximum element is 2.
 for i = 6, Subarray [1:6] has 6 elements 1, 2, 3, 4, 5 and 6. So, 4th maximum element is 3.
 So, output array is [-1, -1, -1, 1, 2, 3].
 
Explanation 2:

 for i <= 1 output should be -1.
 for i = 2 , Subarray [1:2] has 2 elements 15 and 20. So, 2th maximum element is 15.
 for i = 3 , Subarray [1:3] has 3 elements 15, 20 and 99. So, 2th maximum element is 20.
 for i = 4 , Subarray [1:4] has 4 elements 15, 20, 99 and 1. So, 2th maximum element is 20.
 So, output array is [-1, 15, 20, 20].
    */
    public int[] solve(int A, int[] B) {

        int[] res = new int[B.length];

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for (int i = 0; i < A; i++) {
            minHeap.offer(B[i]);

            if (i == A - 1) {
                res[i] = minHeap.peek();
            } else {
                res[i] = -1;
            }
        }

        for (int i = A; i < B.length; i++) {
            if (B[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(B[i]);
            }

            res[i] = minHeap.peek();
        }

        return res;
    }

    /* Minimum largest element
    Problem Description
Given an array A of N numbers, you have to perform B operations. In each operation, you have to pick any one of the N elements and add the original value(value stored at the index before we did any operations) to its current value. You can choose any of the N elements in each operation.

Perform B operations in such a way that the largest element of the modified array(after B operations) is minimized.
Find the minimum possible largest element after B operations.



Problem Constraints
1 <= N <= 106
0 <= B <= 105
-105 <= A[i] <= 105



Input Format
The first argument is an integer array A.
The second argument is an integer B.



Output Format
Return an integer denoting the minimum possible largest element after B operations.



Example Input
Input 1:

 A = [1, 2, 3, 4] 
 B = 3
Input 2:

 A = [5, 1, 4, 2] 
 B = 5


Example Output
Output 1:

 4
Output 2:

 5


Example Explanation
Explanation 1:

 Apply operation on element at index 0, the array would change to [2, 2, 3, 4]
 Apply operation on element at index 0, the array would change to [3, 2, 3, 4]
 Apply operation on element at index 0, the array would change to [4, 2, 3, 4]
 Minimum possible largest element after 3 operations is 4.
Explanation 2:

 Apply operation on element at index 1, the array would change to [5, 2, 4, 2]
 Apply operation on element at index 1, the array would change to [5, 3, 4, 2]
 Apply operation on element at index 1, the array would change to [5, 4, 4, 2]
 Apply operation on element at index 1, the array would change to [5, 5, 4, 2]
 Apply operation on element at index 3, the array would change to [5, 5, 4, 4]
 Minimum possible largest element after 5 operations is 5.
    */
    public class MinimumPossibleLargestElementSolution {
        class Pair {
            public int index;
            public int value;

            public Pair(int i, int v) {
                this.index = i;
                this.value = v;
            }
        }

        class PairComparator implements Comparator<Pair> {

            public int compare(Pair p1, Pair p2) {
                return p1.value - p2.value;
            }
        }

        public int solve(int[] A, int B) {
            int[] state = new int[A.length];

            PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(new PairComparator());

            for (int i = 0; i < A.length; i++) {
                state[i] = A[i];

                minHeap.offer(new Pair(i, 2 * state[i]));
            }

            while (B > 0) {
                Pair p = minHeap.poll();

                state[p.index] = p.value;

                p.value += A[p.index];

                minHeap.offer(p);

                B--;
            }

            int max = state[0];

            for (int i = 1; i < state.length; i++) {
                max = Math.max(max, state[i]);
            }

            return max;
        }
    }
    
    /* N max pair combinations
    Problem Description
Given two integers arrays, A and B, of size N each.

Find the maximum N elements from the sum combinations (Ai + Bj) formed from elements in arrays A and B.



Problem Constraints
1 <= N <= 2 * 105

-1000 <= A[i], B[i] <= 1000



Input Format
The first argument is an integer array A.
The second argument is an integer array B.



Output Format
Return an integer array denoting the N maximum element in descending order.



Example Input
Input 1:

 A = [1, 4, 2, 3]
 B = [2, 5, 1, 6]
Input 2:

 A = [2, 4, 1, 1]
 B = [-2, -3, 2, 4]


Example Output
Output 1:

 [10, 9, 9, 8]
Output 2:

 [8, 6, 6, 5]


Example Explanation
Explanation 1:

 4 maximum elements are 10(6+4), 9(6+3), 9(5+4), 8(6+2).
Explanation 2:

 4 maximum elements are 8(4+4), 6(4+2), 6(4+2), 5(4+1).
    */
    public class NMaxPairCombinationsSolution {
        class Pair {
            public int sum;
            public int i;
            public int j;

            public Pair(int sum, int i, int j) {
                this.sum = sum;
                this.i = i;
                this.j = j;
            }

            @Override
            public int hashCode() {
                // overriding hashCode() method to first
                // check the length of the names*/
                return this.i * 10 + this.j;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;

                if (obj == null || this.getClass() != obj.getClass())
                    return false;

                Pair p1 = (Pair) obj;

                return this.i == p1.i && this.j == p1.j;
            }
        }

        class PairComparator implements Comparator<Pair> {
            public int compare(Pair p1, Pair p2) {
                return p2.sum - p1.sum;
            }
        }

        public int[] solve(int[] A, int[] B) {

            Arrays.sort(A);

            Arrays.sort(B);

            int N = A.length;

            HashSet<Pair> set = new HashSet<Pair>();

            PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(new PairComparator());

            Pair maxSumPair = new Pair((A[N - 1] + B[N - 1]), N - 1, N - 1);

            maxHeap.offer(maxSumPair);
            set.add(maxSumPair);

            int[] res = new int[N];

            for (int k = 0; k < res.length; k++) {
                Pair p = maxHeap.poll();

                res[k] = p.sum;

                int i = p.i;
                int j = p.j;

                Pair q = null;

                if (i - 1 >= 0) {
                    q = new Pair(A[i - 1] + B[j], i - 1, j);
                }

                if (q != null && !set.contains(q)) {
                    maxHeap.offer(q);
                    set.add(q);
                }

                if (j - 1 >= 0) {
                    q = new Pair(A[i] + B[j - 1], i, j - 1);
                }

                if (q != null && !set.contains(q)) {
                    maxHeap.offer(q);
                    set.add(q);
                }

            }

            return res;
        }
    }    
    
}
