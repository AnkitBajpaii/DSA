package Arrays;

import java.util.*;


class PairComparator implements Comparator<Pair>
{
    @Override
    public int compare(Pair p1, Pair p2) {
        return p1.value - p2.value;
    }
}

class Pair
{
    public int value;
    public int index;

    public Pair(int v, int i){
        value = v;
        index = i;
    }
}

public class arrayProblems {

    public static void Swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public static void Reverse(int[] A, int start, int end) {
        if ((start >= 0 && start < A.length) && (end >= 0 && end < A.length)) {
            while (start < end) {
                int t = A[start];
                A[start] = A[end];
                A[end] = t;

                start++;
                end--;
            }
        }
    }

    // Find index of second Largest element using a single loop
    public int SecondLargest(int[] A) {
        int largest = 0, secondLargest = -1;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[largest]) {
                secondLargest = largest;
                largest = A[i];
            } else if (A[i] < A[largest]) {
                if (secondLargest == -1 || A[i] > A[secondLargest]) {
                    secondLargest = i;
                }
            }
        }

        return secondLargest;
    }

    // Check if given array is sorted or not.
    public boolean IsSorted(int[] A) {

        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                return false;
            }
        }

        return true;
    }

    // Remove Duplicates from sorted array
    public int RemoveDuplicatesFromSortedArray(int[] A) {
        int res = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[res]) {
                res++;
                Swap(A, res, i);
            }
        }

        return res + 1;
    }

    /*
     * Leaders in an array
     * Given an integer array A containing N distinct integers, you have to find all
     * the leaders in the array A.
     * 
     * An element is leader if it is strictly greater than all the elements to its
     * right side.
     * 
     * NOTE:The rightmost element is always a leader.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 105
     * 
     * 1 <= A[i] <= 108
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument is an integer array A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer array denoting all the leader elements of the array.
     * 
     * NOTE: Ordering in the output doesn't matter.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [16, 17, 4, 3, 5, 2]
     * Input 2:
     * 
     * A = [1, 2]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [17, 2, 5]
     * Output 2:
     * 
     * [2]
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Element 17 is strictly greater than all the elements on the right side to it.
     * Element 2 is strictly greater than all the elements on the right side to it.
     * Element 5 is strictly greater than all the elements on the right side to it.
     * So we will return this three elements i.e [17, 2, 5], we can also return [2,
     * 5, 17] or [5, 2, 17] or any other ordering.
     * Explanation 2:
     * 
     * Only 2 the rightmost element is the leader in the array.
     */
    public ArrayList<Integer> LeadersInArray(ArrayList<Integer> A) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(A.get(A.size() - 1));

        int max = A.get(A.size() - 1);
        for (int i = A.size() - 2; i >= 0; i--) {
            if (A.get(i) > max) {
                res.add(A.get(i));
                max = A.get(i);
            }
        }

        Collections.reverse(res);

        return res;
    }

    // MOve zeros to the end i/p: A=[10,8,0,0,0,12]
    public void MoveZerosToEnd(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != 0) {
                Swap(A, count, i);
                count++;
            }
        }
    }

    // Right rotate array by K places.
    public void RightRotateArrayByK(int[] A, int k) {
        if (k > A.length) {
            k = k % A.length;
        }

        if (k == A.length)
            return;

        Reverse(A, 0, A.length - 1);

        Reverse(A, 0, k - 1);

        Reverse(A, k, A.length - 1);
    }

    // Find Leaders in Array. An element is called leader if there is nothing that
    // is greater than right of it.
    public void FindLeaders(int[] A) {
        System.out.print(A[A.length - 1] + " ");

        int currLeader = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i++) {
            if (A[i] > currLeader) {
                System.out.print(A[i] + " ");
                currLeader = A[i];
            }
        }
    }

    // Maximum Difference problem is to find the maximum of arr[j] - arr[i] where
    // j>i.
    public int MaxDiff(int[] A) {
        int res = Integer.MIN_VALUE;
        int minValue = A[0];

        for (int j = 1; j < A.length; j++) {
            res = Math.max(res, A[j] - minValue);
            minValue = Math.min(minValue, A[j]);
        }

        return res;
    }

    // Print frequency of each element in a sorted array
    public void PrintFrequencyOFElementsInSortedArray(int[] A) {
        int freq = 1, i = 1;
        while (i < A.length) {
            while (i < A.length && A[i] == A[i - 1]) {
                freq++;
                i++;
            }

            System.out.println(A[i - 1] + " -> " + freq);
            i++;
            freq = 1;
        }

        if (A[A.length - 1] != A[A.length - 2]) {
            System.out.println(A[A.length - 1] + " -> " + 1);
        }
    }

    // Trapping Rain Water Problem
    public int TrappingRainWaterProblem(int[] A) {
        int n = A.length;

        int[] lmax = new int[n];
        lmax[0] = A[0];

        for (int i = 1; i < n; i++) {
            lmax[i] = Math.max(lmax[i - 1], A[i]);
        }

        int[] rmax = new int[n];
        rmax[n - 1] = A[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i + 1], A[i]);
        }

        int ans = 0;
        for (int i = 1; i <= n - 2; i++) {
            ans += Math.min(lmax[i], rmax[i]) - A[i];
        }

        return ans;
    }

    // Find count of maximum consecutive 1s in a binary array.
    public int MaxConsecutiveOnesInBinaryArray(int[] A) {

        int ans = 0, count = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                count = 0;
            } else {
                count++;
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    // Given an array, return sum of all possible sub-arrays
    public long SumOfAllSubArrays(int[] A) {
        // Idea: Contribution technique
        // count contribution of each element in every sub-array,
        // i.e how many times each element in appearing in all sub-arrays
        // an element at index i will appear in (i+1)*(n-i) in all sub-arrays
        long sum = 0;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            sum = sum + A[i] * (i + 1) * (n - i);
        }
        return sum;
    }

    // Maximum subarray sum - Kadane Algorithm
    public int MaxSubArraySum(int[] A) {
        int ans = A[0];

        int maxEnding = A[0];

        for (int i = 1; i < A.length; i++) {
            maxEnding = Math.max(maxEnding + A[i], A[i]);
            ans = Math.max(ans, maxEnding);
        }

        return ans;
    }

    // find the length of the longest subarray that has alternating even odd
    // elements
    public int LongestAlternatingEvenOddSubarray(int[] A) {

        int ans = 1;
        int count = 1;

        for (int i = 1; i < A.length; i++) {
            if ((A[i] % 2 == 0 && A[i - 1] % 2 != 0) || (A[i] % 2 != 0 && A[i - 1] % 2 == 0)) {
                count++;
                ans = Math.max(ans, count);
            } else {
                count = 1;
            }
        }

        return ans;
    }

    // Maximum Circular Sum Subarray. Find maximum circular sum subarray of a given
    // array
    public int MaximumCircularSumSubarray(int[] A) {
        int maxSubArraySum = MaxSubArraySum(A);

        if (maxSubArraySum < 0)
            return maxSubArraySum;

        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
            A[i] = -A[i];
        }

        int minSubArraySum = MaxSubArraySum(A);

        int cicularSubArraySum = sum + minSubArraySum;

        return Math.max(cicularSubArraySum, maxSubArraySum);
    }

    // Find MajortyElement. Majority element is an element that appears more than
    // n/2 times in an array of size n
    public int MajorityElement(int[] A) {
        int res = 0, count = 1;

        // find a candidate
        for (int i = 1; i < A.length; i++) {
            if (A[res] == A[i]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                res = i;
                count = 1;
            }
        }

        // check if candidate is actually a majority
        count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[res] == A[i]) {
                count++;
            }
        }

        if (count <= A.length / 2)
            return -1;

        return res;
    }

    // window sliding technique
    // Find Max sum of K consecutive elements
    public int FindMaxSumOfKConsecutiveElements(int[] A, int k) {

        int currSum = 0;
        for (int i = 0; i < k; i++) {
            currSum += A[i];
        }

        int maxSum = currSum;

        for (int i = k; i < A.length; i++) {
            currSum = (currSum - A[i - k]) + A[i];
            maxSum = Math.max(currSum, maxSum);
        }

        return maxSum;
    }

    // Given Array of Positive integers, Find subarray with given sum
    public boolean FindSubArrayWithGivenSum(int[] A, int sum) {
        int s = 0, n = A.length;
        int currSum = A[0];

        for (int e = 1; e <= n; e++) {
            while (currSum > sum && s < e - 1) {
                currSum = currSum - A[s];
                s++;
            }

            if (currSum == sum) {
                System.out.println("Sum found between " + s + " and " + (e - 1));
                return true;
            }

            if (e < n) {
                currSum = currSum + A[e];
            }
        }

        return false;

    }

    // Print M N-nonacci numbers
    public void PrintNBonacciMTimes(int n, int m) {
        int[] A = new int[m];

        for (int i = 0; i < n - 1; i++) {
            A[i] = 0;
        }

        A[n - 1] = 1;

        int curr = 1;

        for (int i = n; i < m; i++) {
            A[i] = curr;
            curr = curr + A[i] - A[i - n];
        }

        // Printing result
        for (int i = 0; i < m; i++)
            System.out.print(A[i] + " ");
    }

    // Find if Array has Equilibrium point
    public boolean HasEquilibriumPoint(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
        }

        int lSum = 0;
        for (int i = 0; i < A.length; i++) {
            int rSum = sum - A[i];
            if (lSum == rSum) {
                return true;
            }

            lSum = lSum + A[i];
            sum = sum - A[i];
        }

        return false;
    }

    // Search an element in row-wise and column wise sorted matrix
    public boolean SearchElementInRowWiseColumnWiseSortedMatrix(int[][] mat, int n, int m, int k) {

        int row = 0, col = m - 1;// starting from top right corner
        while (row < n && col >= 0) {
            if (mat[row][col] == k) {
                return true;
            }

            if (k > mat[row][col]) {
                row++;
            } else {
                col--;
            }
        }

        return false;

        // TC: O(n+m)
    }

    // Given a binary matrix which is sorted row wise.Find max number of 1's in a
    // row.
    public Integer FindMaxNoOfOnesInRowWiseSortedBinaryMatrix(int[][] mat, int n, int m) {

        int row = 0, col = m - 1;// starting from top right corner

        while (row < n && col >= 0) {
            if (mat[row][col] == 1) {
                col--;
            } else {
                row++;
            }
        }

        col = col + 1;

        return m - col;

        // TC: O(n+m)
    }

    // 3. Given a square matrix print it in spiral order
    public void PrintSquareMatrixInSpriralOrder(int[][] mat, int n) {
        int c = 0;
        int l = n - 1;

        while (l > 0) {
            int i = c, j = c;

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                j++;
            }

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                i++;
            }

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                j--;
            }

            for (int k = 0; k < l; k++) {
                System.out.print(mat[i][j] + " ");
                i--;
            }

            l = l - 2;
            c++;
        }

        if (l == 0) {
            System.out.print(mat[c][c] + " ");
        }

        // TC: O(n*n)
    }

    // Give a N * N square matrix A, return an array of its anti-diagonals
    public int[][] antiDiagonal(int[][] A) {
        int N = A.length;

        int[][] mat = new int[2 * N - 1][N];
        
        int r = 0;
        for (int col = 0; col < N; col++) {
            int i = 0, j = col;

            while (i < N && j >= 0) {
                mat[r][i] = A[i][j];

                i++;
                j--;
            }
            r++;
        }

        for (int row = 1; row < N; row++) {
            int i = row, j = N - 1, col = 0;

            while (i < N && j >= 0) {
                mat[r][col] = A[i][j];

                i++;
                j--;
                col++;
            }
            r++;
        }

        return mat;
    }

    /*
     * Special Subsequences "AG" You have given a string A having Uppercase English
     * letters. You have to find that how many times subsequence "AG" is there in
     * the given string. I/P: A = "ABCGAG" O/P: 3
     */
    public int FindCountOfSubsequenceAG(String A) {
        int countOfG = 0;
        int ans = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == 'G') {
                countOfG++;
            } else if (A.charAt(i) == 'A') {
                ans += countOfG;
            }
        }

        return ans;
    }

    /*
     * Closest MinMax Problem Description
     * 
     * Given an array A. Find the size of the smallest subarray such that it
     * contains atleast one occurrence of the maximum value of the array
     * 
     * and atleast one occurrence of the minimum value of the array.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= |A| <= 2000
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument is vector A
     * 
     * 
     * 
     * Output Format
     * 
     * Return the length of the smallest subarray which has atleast one occurrence
     * of minimum and maximum element of the array
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [1, 3] Input 2:
     * 
     * A = [2]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 2 Output 2:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Only choice is to take both elements. Explanation 2:
     * 
     * Take the whole array.
     * 
     */
    public int SmallestSubArrayWithMinAndMax(int[] A) {

        int min = A[0], max = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] < min) {
                min = A[i];
            } else if (A[i] > max) {
                max = A[i];
            }
        }

        if (min == max)
            return 1;

        int minIndx = -1, maxIndx = -1, ans = A.length;

        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] == min) {
                minIndx = i;

                if (maxIndx != -1) {
                    ans = Math.min(ans, maxIndx - minIndx + 1);
                }
            } else if (A[i] == max) {
                maxIndx = i;

                if (minIndx != -1) {
                    ans = Math.min(ans, minIndx - maxIndx + 1);
                }
            }
        }

        return ans;
    }

    /*
     * Length of longest consecutive ones Given a binary string A. It is allowed to
     * do at most one swap between any 0 and 1. Find and return the length of the
     * longest consecutive 1’s that can be achieved.
     * 
     * 
     * Input Format
     * 
     * The only argument given is string A. Output Format
     * 
     * Return the length of the longest consecutive 1’s that can be achieved.
     * Constraints
     * 
     * 1 <= length of string <= 1000000 A contains only characters 0 and 1. For
     * Example
     * 
     * Input 1: A = "111000" Output 1: 3
     * 
     * Input 2: A = "111011101" Output 2: 7
     */
    public int LengthOfLongestConsecutiveOnes(String A) {
        int[] L = new int[A.length()];
        L[0] = A.charAt(0) == '0' ? 0 : 1;
        int countTotalOnes = L[0];
        for (int i = 1; i < A.length(); i++) {
           if (A.charAt(i) == '1') {
               L[i] = L[i - 1] + 1;
               countTotalOnes++;
           }
       }

       int[] R = new int[A.length()];
        R[A.length() - 1] = A.charAt(A.length() - 1) == '0' ? 0 : 1;
        for (int i = A.length() - 2; i >= 0; i--) {
           if (A.charAt(i) == '1') {
               R[i] = R[i + 1] + 1;
           }
       }

       int ans = 1;// to handle edge case like "01"

       for (int i = 1; i < A.length() - 1; i++) {
           if (A.charAt(i) == '1') {
               ans = Math.max(ans, L[i - 1] + R[i + 1] + 1);
           } else {
               if (L[i - 1] + R[i + 1] < countTotalOnes) {// check if we can spare extra one
                   ans = Math.max(ans, L[i - 1] + R[i + 1] + 1); 
               } else {
                   // L[i - 1] + R[i + 1] == countTotalOnes
                   ans = Math.max(ans, L[i - 1] + R[i + 1]);
               }

           }
       }

       return ans;
    }

    /*
     * Rearrange Array Rearrange a given array so that Arr[i] becomes Arr[Arr[i]]
     * with O(1) extra space. ets say N = size of the array. Then, following holds
     * true : All elements in the array are in the range [0, N-1] N * N does not
     * overflow for a signed integer
     */
    public void ReArrange(ArrayList<Integer> A) {
        int n = A.size();
        for (int i = 0; i < n; i++) {
            A.set(i, A.get(i) * n);
        }

        for (int i = 0; i < n; i++) {
            int idx = A.get(i) / n;
            A.set(i, (A.get(i) + A.get(idx) / n));
        }

        for (int i = 0; i < n; i++) {
            A.set(i, A.get(i) % n);
        }
    }

    /*
     * Pascal Triangle Write a program to input an integer N from user and print
     * pascal triangle up to N rows.
     */
    public int[][] pascalTriangle(int A) {
        int[][] B = new int[A][A];
        if (A == 0)
            return B;

        B[0][0] = 1;

        for (int i = 1; i < A; i++) {
            for (int j = 0; j < A; j++) {
                int sum = B[i - 1][j];
                if (j - 1 >= 0) {
                    sum = sum + B[i - 1][j - 1];
                }

                B[i][j] = sum;

                if (B[i][j] == 0)
                    break;
            }
        }

        return B;
    }

    /*
     * Pick from both sides
     * Problem Description
     * 
     * Given an integer array A of size N.
     * 
     * You can pick B elements from either left or right end of the array A to get
     * maximum sum.
     * 
     * Find and return this maximum possible sum.
     * 
     * NOTE: Suppose B = 4 and array A contains 10 elements then
     * 
     * You can pick first four elements or can pick last four elements or can pick 1
     * from front and 3 from back etc . you need to return the maximum possible sum
     * of elements you can pick.
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 105
     * 
     * 1 <= B <= N
     * 
     * -103 <= A[i] <= 103
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an integer array A.
     * 
     * Second argument is an integer B.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting the maximum possible sum of elements you picked.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [5, -2, 3 , 1, 2]
     * B = 3
     * Input 2:
     * 
     * A = [1, 2]
     * B = 1
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 8
     * Output 2:
     * 
     * 2
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Pick element 5 from front and element (1, 2) from back so we get 5 + 1 + 2 =
     * 8
     * Explanation 2:
     * 
     * Pick element 2 from end as this is the maximum we can get
     */
    public int pickBElementsFromBothSides(int[] A, int B) {
        int sum = 0, N = A.length;

        for (int i = 0; i < B; i++) {
            sum = sum + A[i];
        }

        int ans = sum;

        for (int i = 1; i <= B; i++) {
            sum = sum - A[B - i] + A[N - i];
            ans = Math.max(ans, sum);
        }

        return ans;
    }

    /*
     * Beggars Outside Temple
     * There are N (N > 0) beggars sitting in a row outside a temple. Each beggar
     * initially has an empty pot. When the devotees come to the temple, they donate
     * some amount of coins to these beggars. Each devotee gives a fixed amount of
     * coin(according to his faith and ability) to some K beggars sitting next to
     * each other.
     * 
     * Given the amount donated by each devotee to the beggars ranging from i to j
     * index, where 1 <= i <= j <= N, find out the final amount of money in each
     * beggar's pot at the end of the day, provided they don't fill their pots by
     * any other means.
     * 
     * Example:
     * 
     * Input:
     * N = 5, D = [[1, 2, 10], [2, 3, 20], [2, 5, 25]]
     * 
     * Return: [10, 55, 45, 25, 25]
     * 
     * Explanation:
     * => First devotee donated 10 coins to beggars ranging from 1 to 2. Final
     * amount in each beggars pot after first devotee: [10, 10, 0, 0, 0]
     * 
     * => Second devotee donated 20 coins to beggars ranging from 2 to 3. Final
     * amount in each beggars pot after second devotee: [10, 30, 20, 0, 0]
     * 
     * => Third devotee donated 25 coins to beggars ranging from 2 to 5. Final
     * amount in each beggars pot after third devotee: [10, 55, 45, 25, 25]
     */
    public int[] beggarsOutsideTemple(int A, int[][] B) {
        int[] res = new int[A];
        for (int i = 0; i < B.length; i++) {
            int l = B[i][0] - 1;
            int r = B[i][1] - 1;
            int k = B[i][2];

            res[l] += k;

            if (r + 1 < A) {
                res[r + 1] -= k;
            }
        }

        for (int i = 1; i < A; i++) {
            res[i] = res[i] + res[i - 1];
        }

        return res;
    }

    /*
     * Generate All Sub-sets of a given array of size n Note: There will be 2^n
     * sub-sets.
     */
    public ArrayList<ArrayList<Integer>> GenerateSubSets(int[] A, int n) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int subSetsCount = 1 << n;// i.e 2^n
        for (int i = 0; i < subSetsCount; i++) {
            ArrayList<Integer> sets = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 1) {
                    sets.add(A[j]);
                }
            }

            res.add(sets);
        }

        return res;
    }

    /*
     * Count sub-sets with given sum K Note: There will be 2^n sub-sets.
     */
    public int CountSubSetsWithSumK(int[] A, int n, int k) {
        int count = 0;
        int subSetsCount = 1 << n;// i.e 2^n
        for (int i = 0; i < subSetsCount; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 1) {
                    sum += A[j];
                }
            }

            if (sum == k) {
                count++;
            }
        }

        return count;
    }

    /*
     * Find sum of all-subsets in O(n) time We will use contribution technique.
     */
    public int SumOfAllSubsets(int[] A, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
        }

        return sum * (1 << n - 1); // i/e sum * 2^(n-1)
    }

    /*
     * Maximum Difference Given an array of integers A and an integer B. Find and
     * return the maximum value of | s1 - s2 |
     * 
     * where s1 = sum of any subset of size B, s2 = sum of elements of A - sum of
     * elemets of s1
     * 
     * Note |x| denotes the absolute value of x. Hint: Given n array elements, we
     * need to pick any K elements such that |sum of k picked elements - sum of
     * remaining elements| is maximized
     */
    public int PickKElementsToMaximizeSum(int[] A, int B) {
        Arrays.sort(A);

        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }

        int sumKSmallest = 0;
        for (int i = 0; i < B; i++) {
            sumKSmallest += A[i];
        }

        int sumRemaining1 = sum - sumKSmallest;

        int sumKLargest = 0;
        for (int i = A.length - B; i < A.length; i++) {
            sumKLargest += A[i];
        }

        int sumRemaining2 = sum - sumKLargest;

        return Math.max(sumKSmallest - sumRemaining1, sumKLargest - sumRemaining2);
    }

    /*
     * Find subsequence Given two strings A and B, find if A is a subsequence of B.
     */
    public String CheckSubSequence(String A, String B) {
        int i = 0, j = 0;
        while (i < A.length() && j < B.length()) {
            if (A.charAt(i) == B.charAt(j)) {
                i++;
            }

            j++;
        }

        return i == A.length() ? "YES" : "NO";
    }

    /*
     * Generate Sub-sequences of an array. There will be 2^n sub-sequences
     */
    public void GenerateAllSubSequences(int[] A) {
        int n = A.length;
        double size = Math.pow(2, n);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (i << j)) != 0) {
                    System.out.print(A[j] + " ");
                }
            }

            System.out.println();
        }

    }

    /*
     * Sum the Difference Given an integer array A of size N. You have to find all
     * possible non-empty subsequence of the array of numbers and then, for each
     * subsequence, find the difference between the largest and smallest numbers in
     * that subsequence Then add up all the differences to get the number. Return
     * answer mod 1000000007 NOTE: Subsequence can be non-contiguous.
     */
    public long pow(long x, int y, int k) {
        long result = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                result = (result * x) % k;
                y--;
            }
            y >>= 1;
            x = (x * x) % k;
        }
        return result;
    }

    public int solve(int[] A) {
        Arrays.sort(A);
        int n = A.length;

        long sumOfMax = 0, sumOfMin = 0;
        for (int i = 0; i < n; i++) {
            sumOfMax = (sumOfMax + (A[i] * pow(2, i, 1000000007)) % 1000000007) % 1000000007;
            sumOfMin = (sumOfMin + (A[i] * pow(2, n - i - 1, 1000000007)) % 1000000007) % 1000000007;

        }

        return (int) (sumOfMax - sumOfMin + 1000000007) % 1000000007;
    }

    /*
     * Subsequence-Sum Problem Problem Description
     * 
     * You are given an array of integers of N size.
     * 
     * You have to find that there is any subsequence exists or not whose sum is
     * equal to B. Problem Constraints
     * 
     * 1 <= N <= 20 1 <= A[i] <= 100000 0 <= B <= 1e9 Input Format
     * 
     * First Argument is array of integers A Second Argument is B
     * 
     * Output Format
     * 
     * Return 1 if any subsequence sum is equal to B otherwise return 0.
     */

    public int SubSequenceSum(int[] A, int B) {
        int n = A.length;
        double size = Math.pow(2, n);

        for (int i = 0; i < size; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (i << j)) != 0) {
                    sum += A[j];

                    if (sum > B)
                        break;

                    if (sum == B)
                        return 1;
                }
            }
        }

        return 0;
    }

    /*
     * Given a binary aray with 0 and 1, Count Sub-arrays having OR as 1 Idea: Use
     * contribution technique but ina different way. Answer would be total number of
     * sub-arrays - count of subarrays whose OR is 0
     */
    public int CountSuArraysHavingOrAs1(int[] A) {
        int n = A.length;

        int countSubArraysHavingOrAsZero = 0;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] == 0) {
                count++;
            } else {
                countSubArraysHavingOrAsZero += count * (count + 1) / 2;
                count = 0;
            }
        }

        int totalSubArrays = n * (n + 1) / 2;

        int ans = totalSubArrays - countSubArraysHavingOrAsZero;

        return ans;
    }

    /*
     * Odd Even Subsequences
     * Given an array of integers A of size N. Find the longest subsequence of A
     * which is odd-even.
     * 
     * A subsequence is said to odd-even in the following cases:
     * 
     * The first element of the subsequence is odd, second element is even, third
     * element is odd and so on. For example: [5, 10, 5, 2, 1, 4], [1, 2, 3, 4, 5]
     * 
     * The first element of the subsequence is even, second element is odd, third
     * element is even and so on. For example: [10, 5, 2, 1, 4, 7], [10, 1, 2, 3, 4]
     * 
     * Return the maximum possible length of odd-even subsequence.
     * 
     * Note: An array B is a subsequence of an array A if B can be obtained from A
     * by deletion of several (possibly, zero or all) elements.
     * 
     * 
     * Input Format
     * 
     * The only argument given is the integer array A.
     * Output Format
     * 
     * Return the maximum possible length of odd-even subsequence.
     * Constraints
     * 
     * 1 <= N <= 100000
     * 1 <= A[i] <= 10^9
     * For Example
     * 
     * Input 1:
     * A = [1, 2, 2, 5, 6]
     * Output 1:
     * 4
     * Explanation 1:
     * Maximum length odd even subsequence is [1, 2, 5, 6]
     * 
     * Input 2:
     * A = [2, 2, 2, 2, 2, 2]
     * Output 2:
     * 1
     * Explanation 2:
     * Maximum length odd even subsequence is [2]
     */
    public int LongestEvenOddSubsequence(int[] A) {
        int count = 1;
        String prev = A[0] % 2 == 0 ? "even" : "odd";
        for (int i = 1; i < A.length; i++) {
            String curr = A[i] % 2 == 0 ? "even" : "odd";
            if ((prev == "odd" && curr == "even") || prev == "even" && curr == "odd") {
                count++;
                prev = curr;
            }
        }

        return count;
    }

    /*
     * Smaller and Greater You are given an Array A of size N.
     * 
     * Find for how many elements, there is a strictly smaller element and a
     * strictly greater element.
     * 
     * 
     * 
     * Input Format
     * 
     * Given only argument A an Array of Integers. Output Format
     * 
     * Return an Integer X, i.e number of elements. Constraints
     * 
     * 1 <= N <= 1e5 1 <= A[i] <= 1e6 For Example
     * 
     * Example Input: A = [1, 2, 3]
     * 
     * Example Output: 1
     * 
     * Explanation: only 2 have a strictly smaller and strictly greater element, 1
     * and 3 respectively.
     */
    public int smallerAndGreater(int[] A) {
        int n = A.length;

        int min = A[0], max = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] < min) {
                min = A[i];
            }

            if (A[i] > max) {
                max = A[i];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] > min && A[i] < max) {
                ans++;
            }
        }

        return ans;
    }

    /*
     * Elements which have at-least two greater elements Problem Description You are
     * given an array of distinct integers A, you have to find and return all
     * elements in array which have at-least two greater elements than themselves.
     * 
     * NOTE: The result should have the order in which they are present in the
     * original array.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 3 <= |A| <= 105
     * 
     * -109 <= A[i] <= 109
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument is an integer array A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer array containing the elements of A which have at-least two
     * greater elements than themselves in A.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [1, 2, 3, 4, 5] Input 2:
     * 
     * A = [11, 17, 100, 5]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [1, 2, 3] Output 2:
     * 
     * [11, 5]
     */
    public ArrayList<Integer> ElementsHavingAtLeastTwoGreaterElements(ArrayList<Integer> A) {

        ArrayList<Integer> res = new ArrayList<Integer>();

        int largest = A.get(0);

        for (int i = 1; i < A.size(); i++) {
            if (A.get(i) > largest) {
                largest = A.get(i);
            }
        }

        int secondLargest = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) > secondLargest && A.get(i) != largest) {
                secondLargest = A.get(i);
            }
        }

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != largest && A.get(i) != secondLargest) {
                res.add(A.get(i));
            }
        }

        return res;
    }

    /*
     * Time to equality Problem Description
     * 
     * Given an integer array A of size N. In one second you can increase the value
     * of one element by 1.
     * 
     * Find the minimum time in seconds to make all elements of the array equal.
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 1000000 1 <= A[i] <= 1000
     * 
     * 
     * Input Format
     * 
     * First argument is an integer array A.
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting the minimum time to make all elements equal.
     * 
     * 
     * Example Input
     * 
     * A = [2, 4, 1, 3, 2]
     * 
     * 
     * Example Output
     * 
     * 8
     * 
     * 
     * Example Explanation
     * 
     * We can change the array A = [4, 4, 4, 4, 4]. The time required will be 8
     * seconds. Since we can only increase the element by 1. We should increase all
     * element upto the maximum element. We can find the maximum element and for
     * finding the minimum number of moves we should find the summation of absolute
     * difference of all elements with maximum element.
     * 
     */
    public int timeToEquality(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {

            res = res + (max - A[i]);
        }
        return res;
    }

    /*
     * Add One To Number Problem Description
     * 
     * Given a non-negative number represented as an array of digits, add 1 to the
     * number ( increment the number represented by the digits ).
     * 
     * The digits are stored such that the most significant digit is at the head of
     * the list.
     * 
     * NOTE: Certain things are intentionally left unclear in this question which
     * you should practice asking the interviewer. For example: for this problem,
     * following are some good questions to ask :
     * 
     * Q : Can the input have 0's before the most significant digit. Or in other
     * words, is 0 1 2 3 a valid input? A : For the purpose of this question, YES Q
     * : Can the output have 0's before the most significant digit? Or in other
     * words, is 0 1 2 4 a valid output? A : For the purpose of this question, NO.
     * Even if the input has zeroes before the most significant digit.
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= size of the array <= 1000000
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an array of digits.
     * 
     * 
     * 
     * Output Format
     * 
     * Return the array of digits after adding one.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * [1, 2, 3]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [1, 2, 4]
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * Given vector is [1, 2, 3]. The returned vector should be [1, 2, 4] as 123 + 1
     * = 124.
     */
    public ArrayList<Integer> addOneToNumber(ArrayList<Integer> A) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        int sum = 0;
        for (int i = A.size() - 1; i >= 0; i--) {
            sum += A.get(i) + (i == A.size() - 1 ? 1 : 0);
            al.add(sum % 10);
            sum = sum / 10;
        }

        if (sum != 0) {
            al.add(sum % 10);
        }

        int j = al.size() - 1;
        while (j >= 0 && al.get(j) == 0) {
            j--;
        }

        al = new ArrayList<Integer>(al.subList(0, j + 1));
        Collections.reverse(al);
        return al;
    }

    /*
     * Pattern Printing -2 Problem Description
     * 
     * Print a Pattern According to The Given Value of A.
     * 
     * Example: For A = 3 pattern looks like:
     * 
     *      1
          2 1
        3 2 1
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A <= 1000
     * 
     * 
     * Input Format
     * 
     * First and only argument is an integer denoting A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a two-dimensional array where each row in the returned array
     * represents a row in the pattern.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 3 Input 2:
     * 
     * A = 4
     * 
     * 
     * Example Output
     * 
     * Output :1
     * 
     * [ [0, 0, 1] [0, 2, 1] [3, 2, 1] ] Output 2:
     * 
     * [ [0, 0, 0, 1] [0, 0, 2, 1] [0, 3, 2, 1] [4, 3, 2, 1] ]
     */
    public int[][] patternPrinting2(int A) {
        int[][] res = new int[A][A];

        for (int i = 0; i < A; i++) {

            for (int j = 0; j < i + 1; j++) {
                res[i][A - 1 - j] = j + 1;
            }
        }
        return res;
    }

    /*
     * Maximum Absolute Difference Problem Description Lecture: DSA: Arrays-1 You
     * are given an array of N integers, A1, A2, …. AN.
     * 
     * Return the maximum value of f(i, j) for all 1 ≤ i, j ≤ N. f(i, j) is defined
     * as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 100000
     * 
     * -109 <= A[i] <= 109
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an integer array A of size N.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting the maximum value of f(i, j).
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [1, 3, -1] Input 2:
     * 
     * 
     * A = [2]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 5 Output 2:
     * 
     * 0
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * f(1, 1) = f(2, 2) = f(3, 3) = 0 f(1, 2) = f(2, 1) = |1 - 3| + |1 - 2| = 3
     * f(1, 3) = f(3, 1) = |1 - (-1)| + |1 - 3| = 4 f(2, 3) = f(3, 2) = |3 - (-1)| +
     * |2 - 3| = 5
     * 
     * So, we return 5. Explanation 2:
     * 
     * Only possibility is i = 1 and j = 1. That gives answer = 0.
     */
    public int MaximumAbsoluteDifference(int[] A) {

        // Observation:
        // 1. ignore, i==j case.
        // 2. f(i,j) = f(j,i). which means consider i<j case or i>j case.
        // lets go with i<j case.
        // since i<j , f(i,j) = |A[i]-A[j]| + (j-i);// |i-j| = -(i-j), since i<j
        //open mod for  f(i,j) = |A[i]-A[j]| + (j-i)
        // case 1:  f(i,j) = A[i]-A[j] + (j-i) // A[i]>=A[j]
        //case 2:  f(i,j) =  A[j]-A[i] + (j-i) // A[i]<A[j]
        // i.e 1. f(i,j) = (A[i]-i) - (A[j] - j)
        // 2. f(i,j) = (A[j] + j) - (A[i] + i)

        int xmax = A[0], xmin = A[0];
        for (int i = 1; i < A.length; i++) {
            int xi = A[i] + i;
            if (xi > xmax) {
                xmax = xi;
            }

            if (xi < xmin) {
                xmin = xi;
            }
        }

        int ymax = A[0], ymin = A[0];
        for (int i = 1; i < A.length; i++) {
            int yi = A[i] - i;
            if (yi > ymax) {
                ymax = yi;
            }

            if (yi < ymin) {
                ymin = yi;
            }
        }

        return Math.max(xmax - xmin, ymax - ymin);
    }

    /*
     * First Missing Integer Problem Description
     * 
     * Given an unsorted integer array A of size N. Find the first missing positive
     * integer.
     * 
     * Note: Your algorithm should run in O(n) time and use constant space.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 1000000
     * 
     * -109 <= A[i] <= 109
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
     * Return an integer denoting the first missing positive integer.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * [1, 2, 0] Input 2:
     * 
     * [3, 4, -1, 1] Input 3:
     * 
     * [-8, -7, -6]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 3 Output 2:
     * 
     * 2 Output 3:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * A = [1, 2, 0] First positive integer missing from the array is 3.
     */
    public int firstMissingPositive(int[] A) {
        int N = A.length;

        boolean[] map = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            if (A[i] > N || A[i] <= 0)
                continue;

            map[A[i]] = true;
        }

        for (int i = 1; i < map.length; i++) {
            if (map[i] == false)
                return i;
        }

        return N + 1;

        // TC: O(N), SC: O(N)
    }

    public int firstMissingPositive_SpaceOptimized(int[] A) {
        int N = A.length;

        for (int i = 0; i < N; i++) {
            while ((A[i] > 0 && A[i] <= N) && A[i] != A[A[i] - 1]) {

                int t = A[i];
                A[i] = A[A[i] - 1];
                A[A[i] - 1] = t;
            }
        }

        for (int i = 0; i < N; i++) {
            if (A[i] - 1 != i)
                return i + 1;
        }

        return N + 1;
    }

    /*
     * Max Non Negative SubArray Given an array of integers, A of length N, find out
     * the maximum sum sub-array of non negative numbers from A.
     * 
     * The sub-array should be contiguous i.e., a sub-array created by choosing the
     * second and fourth element and skipping the third element is invalid.
     * 
     * Maximum sub-array is defined in terms of the sum of the elements in the
     * sub-array.
     * 
     * Find and return the required subarray.
     * 
     * NOTE:
     * 
     * 1. If there is a tie, then compare with segment's length and return segment
     * which has maximum length. 2. If there is still a tie, then return the segment
     * with minimum starting index.
     * 
     * 
     * Input Format:
     * 
     * The first and the only argument of input contains an integer array A, of
     * length N. Output Format:
     * 
     * Return an array of integers, that is a subarray of A that satisfies the given
     * conditions. Constraints:
     * 
     * 1 <= N <= 1e5 -INT_MAX < A[i] <= INT_MAX Examples:
     * 
     * Input 1: A = [1, 2, 5, -7, 2, 3]
     * 
     * Output 1: [1, 2, 5]
     * 
     * Explanation 1: The two sub-arrays are [1, 2, 5] [2, 3]. The answer is [1, 2,
     * 5] as its sum is larger than [2, 3].
     * 
     * Input 2: A = [10, -1, 2, 3, -4, 100]
     * 
     * Output 2: [100]
     * 
     * Explanation 2: The three sub-arrays are [10], [2, 3], [100]. The answer is
     * [100] as its sum is larger than the other two.
     */
    public ArrayList<Integer> MaxNonNegativeSubArray(ArrayList<Integer> A) {
        long maxSum = 0;
        long newSum = 0;
        ArrayList<Integer> maxArray = new ArrayList<Integer>();
        ArrayList<Integer> newArray = new ArrayList<Integer>();
        for (Integer i : A) {
            if (i >= 0) {
                newSum += i;
                newArray.add(i);
            } else {
                newSum = 0;
                newArray = new ArrayList<Integer>();
            }
            if ((maxSum < newSum) || ((maxSum == newSum) && (newArray.size() > maxArray.size()))) {
                maxSum = newSum;
                maxArray = newArray;
            }
        }
        return maxArray;
    }

    /*
     * Equilibrium index of an array
     * You are given an array A of integers of size N.
     * 
     * Your task is to find the equilibrium index of the given array
     * 
     * Equilibrium index of an array is an index such that the sum of elements at
     * lower indexes is equal to the sum of elements at higher indexes.
     * 
     * NOTE:
     * 
     * Array indexing starts from 0.
     * If there is no equilibrium index then return -1.
     * If there are more than one equilibrium indexes then return the minimum index.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1<=N<=1e5
     * -1e5<=A[i]<=1e5
     * 
     * 
     * Input Format
     * 
     * First arugment contains an array A .
     * 
     * 
     * Output Format
     * 
     * Return the equilibrium index of the given array. If no such index is found
     * then return -1.
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * A=[-7, 1, 5, 2, -4, 3, 0]
     * Input 2:
     * 
     * A=[1,2,3]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 3
     * Output 2:
     * 
     * -1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 3 is an equilibrium index, because:
     * A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
     * Explanation 1:
     * 
     * There is no such index.
     */
    public int EquilibriumIndex(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }

        int lsum = 0;
        for (int i = 0; i < A.length; i++) {
            int rsum = sum - A[i];

            if (lsum == rsum)
                return i;

            sum = sum - A[i];
            lsum = lsum + A[i];
        }

        return -1;
    }

    /*
     * Merge Intervals
     * Problem Description
     * 
     * Given a set of non-overlapping intervals, insert a new interval into the
     * intervals (merge if necessary).
     * 
     * You may assume that the intervals were initially sorted according to their
     * start times.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 0 <= |intervals| <= 105
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is the vector of intervals
     * 
     * second argument is the new interval to be merged
     * 
     * 
     * 
     * Output Format
     * 
     * Return the vector of intervals after merging
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * Given intervals [1, 3], [6, 9] insert and merge [2, 5] .
     * Input 2:
     * 
     * Given intervals [1, 3], [6, 9] insert and merge [2, 6] .
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [ [1, 5], [6, 9] ]
     * Output 2:
     * 
     * [ [1, 9] ]
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * (2,5) does not completely merge the given intervals
     * Explanation 2:
     * 
     * (2,6) completely merges the given intervals
     * public class Interval {
     * public int start;
     * public int end;
     * 
     * public Interval() {
     * 
     * }
     * 
     * public Interval(int s, int e) {
     * start = s;
     * end = e;
     * }
     * 
     * }
     */
    boolean doesIntervalsOverlap(Interval i1, Interval i2) {
        return i1.end >= i2.start && i2.end >= i1.start;
    }

    public ArrayList<Interval> MergeIntervals(ArrayList<Interval> intervals, Interval newInterval) {

        int n = intervals.size();
        ArrayList<Interval> res = new ArrayList<Interval>();

        // If intervals is empty
        if (n == 0) {
            res.add(newInterval);
            return res;
        }

        // If new interval to be inserted at corners
        if (newInterval.end < intervals.get(0).start || newInterval.start > intervals.get(n - 1).end) {
            if (newInterval.end < intervals.get(0).start) {
                res.add(newInterval);
            }

            for (int i = 0; i < n; i++) {
                res.add(intervals.get(i));
            }

            if (newInterval.start > intervals.get(n - 1).end) {
                res.add(newInterval);
            }

            return res;
        }

        // if new intervals covers all intervals
        if (newInterval.start <= intervals.get(0).start && newInterval.end >= intervals.get(n - 1).end) {
            res.add(newInterval);
            return res;
        }

        // merge overlapping intervals 
        for (int i = 0; i < n;) {
            boolean overlap = doesIntervalsOverlap(intervals.get(i), newInterval);
            if (!overlap) {
                res.add(intervals.get(i));

                if (newInterval.start > intervals.get(i).end && newInterval.end < intervals.get(i + 1).start) {
                    res.add(newInterval);
                }

                i++;

                continue;
            }

            Interval temp = new Interval();
            temp.start = Math.min(intervals.get(i).start, newInterval.start);

            while (i < n && overlap) {
                temp.end = Math.max(intervals.get(i).end, newInterval.end);
                if (i == n - 1) {
                    overlap = false;
                } else {
                    overlap = doesIntervalsOverlap(intervals.get(i + 1), newInterval);
                }

                i++;
            }

            res.add(temp);
        }

        return res;
    }

    /* Non Decreasing Sub array
        Given an array and Q queries, where each query has start and end of a range, you need to find
        if given array is a non decreasing array within that range.
        Brute for approach will take O(N*Q) time
        Optimized approach is use prefix sum technique
    */
    public void IsNonDreasing(int[] A, ArrayList<Query> queries)
    {
        int[] arr = new int[A.length];

        arr[0] = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                arr[i] = 1; // mark decrement points
            }
        }

        // take prefix sum of array containing decrement points
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }

        for (Query Q : queries) {
            int start = Q.start;
            int end = Q.end;

            if (arr[end] - arr[start] == 0) {// we didnt took start - 1 because, starting element we have to exclude
                System.out.println("Range from " + start + " to " + end + " is Non decreasing");
            } else {
                System.out.println("Range from " + start + " to " + end + " is not non decreasing");
            }
        }
    }

    /*
     * Xor queries
     * Problem Description
     * 
     * You are given an array A (containing only 0 and 1) as element of N length.
     * Given L and R, you need to determine value of XOR of all elements from L to R
     * (both inclusive) and number of unset bits (0's) in the given range of the
     * array.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1<=N,Q<=100000
     * 1<=L<=R<=N
     * 
     * 
     * Input Format
     * 
     * First argument contains the array of size N containing 0 and 1 only.
     * Second argument contains a 2D array with Q rows and 2 columns, each row
     * represent a query with 2 columns representing L and R.
     * 
     * 
     * Output Format
     * 
     * Return an 2D array of Q rows and 2 columns i.e the xor value and number of
     * unset bits in that range respectively for each query.
     * 
     * 
     * Example Input
     * 
     * A=[1,0,0,0,1]
     * B=[ [2,4],
     * [1,5],
     * [3,5] ]
     * 
     * 
     * Example Output
     * 
     * [[0 3]
     * [0 3]
     * [1 2]]
     * 
     * 
     * Example Explanation
     * 
     * In the given case the bit sequence is of length 5 and the sequence is 1 0 0 0
     * 1.
     * For query 1 the range is (2,4), and the answer is (array[2] xor array[3] xor
     * array[4]) = 0, and number of zeroes are 3, so output is 0 3.
     * Similarly for other queries.
     */
    public int[][] xorQueries(int[] A, int[][] B) {
        int[] psxor = new int[A.length];
        psxor[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            psxor[i] = psxor[i - 1] ^ A[i];
        }

        int[] pscount = new int[A.length];
        pscount[0] = A[0] == 0 ? 1 : 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == 0) {
                pscount[i] = pscount[i - 1] + 1;
            } else {
                pscount[i] = pscount[i - 1];
            }
        }

        int[][] res = new int[B.length][];

        for (int i = 0; i < B.length; i++) {
            int start = B[i][0] - 1;
            int end = B[i][1] - 1;
            res[i] = new int[2];

            if (start > 0) {
                res[i][0] = psxor[end] ^ psxor[start - 1];
                res[i][1] = pscount[end] - pscount[start - 1];
            } else {
                res[i][0] = psxor[end];
                res[i][1] = pscount[end];
            }
        }

        return res;
    }

    /*
     * Count ways to make sum of odd and even indexed elements equal     
     * Problem Description
     * 
     * Given an array, arr[] of size N, the task is to find the count of array
     * indices such that removing an element from these indices makes the sum of
     * even-indexed and odd-indexed array elements equal.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1<=n<=1e5
     * -1e5<=A[i]<=1e5
     * 
     * 
     * Input Format
     * 
     * First argument contains an array A of integers of size N
     * 
     * 
     * Output Format
     * 
     * Return the count of array indices such that removing an element from these
     * indices makes the sum of even-indexed and odd-indexed array elements equal.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * A=[2, 1, 6, 4]
     * Input 2:
     * 
     * A=[1, 1, 1]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 1
     * Output 2:
     * 
     * 3
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * Removing arr[1] from the array modifies arr[] to { 2, 6, 4 } such that,
     * arr[0] + arr[2] = arr[1].
     * Therefore, the required output is 1.
     * Explanation 2:
     * 
     * Removing arr[0] from the given array modifies arr[] to { 1, 1 } such that
     * arr[0] = arr[1]
     * Removing arr[1] from the given array modifies arr[] to { 1, 1 } such that
     * arr[0] = arr[1]
     * Removing arr[2] from the given array modifies arr[] to { 1, 1 } such that
     * arr[0] = arr[1]
     * Therefore, the required output is 3.
     */
    int prefixSumLtoR(int[] ps, int l, int r) {
        if (l == 0)
            return ps[r];

        return ps[r] - ps[l - 1];
    }

    public int CountWaysToMakeSumOfOddAndEvenIndexedElementsEqual_WithExtraSpace(int[] A) {
        int n = A.length;

        int[] pfe = new int[n];
        int[] pfo = new int[n];

        pfe[0] = A[0];
        pfo[0] = 0;

        for(int i=1; i<n; i++)
        {
            if(i%2 == 0)
            {
                pfe[i] = pfe[i-1] + A[i];
                pfo[i] = pfo[i-1];
            } else {
                pfo[i] = pfo[i-1] + A[i];
                pfe[i] = pfe[i-1];
            }
        }

        int count=0;

        for(int i=0; i<n; i++)
        {
            int eb = i == 0 ? 0 : pfe[i-1];
            int ea = i == n-1 ? 0 : pfe[n-1] -  pfe[i];

            int ob = i == 0 ? 0 : pfo[i-1];
            int oa = i == n-1 ? 0 : pfo[n-1] -  pfo[i];

            if(eb+oa == ob+ea) count++;
        }

        return count;
    }

    public int CountWaysToMakeSumOfOddAndEvenIndexedElementsEqual_WithoutExtraSpace(int[] A) {
        int n = A.length;
        int sumEven = A[0], sumOdd = 0;        

        for(int i=1; i<n; i++)
        {
            if(i%2 == 0)
            {
                sumEven += A[i];
            } else{
                sumOdd += A[i];
            }
        }

        int currEven = 0, currOdd = 0;
        int count=0;
        for(int i=0; i<n; i++)        
        {
            int evenBefore, evenAfter, oddBefore, oddAfter;

            if(i%2 == 0)
            {
                currEven += A[i];
                evenBefore = currEven - A[i];
                evenAfter = sumEven - currEven;
                oddBefore = currOdd;
                oddAfter = sumOdd - currOdd;
            } else{
                currOdd += A[i];
                evenBefore = currEven;
                evenAfter = sumEven - currEven;
                oddBefore = currOdd - A[i];
                oddAfter = sumOdd - currOdd;
            }

            if(evenBefore + oddAfter == oddBefore + evenAfter)
            {
                count++;
            }

        }

        return count;
    }

    /*
     * Flip
     * Problem Description
     * 
     * You are given a binary string A(i.e. with characters 0 and 1) consisting of
     * characters A1, A2, …, AN. In a single operation, you can choose two indices L
     * and R such that 1 ≤ L ≤ R ≤ N and flip the characters AL, AL+1, …, AR. By
     * flipping, we mean change character 0 to 1 and vice-versa.
     * 
     * Your aim is to perform ATMOST one operation such that in final string number
     * of 1s is maximised.
     * 
     * If you don't want to perform the operation, return an empty array. Else,
     * return an array consisting of two elements denoting L and R. If there are
     * multiple solutions, return the lexicographically smallest pair of L and R.
     * 
     * NOTE: Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or,
     * if a == c and b < d.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= size of string <= 100000
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument is a string A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an array of integers denoting the answer.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = "010"
     * Input 2:
     * 
     * A = "111"
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [1, 1]
     * Output 2:
     * 
     * []
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * A = "010"
     * 
     * Pair of [L, R] | Final string
     * _______________|_____________
     * [1 1] | "110"
     * [1 2] | "100"
     * [1 3] | "101"
     * [2 2] | "000"
     * [2 3] | "001"
     * 
     * We see that two pairs [1, 1] and [1, 3] give same number of 1s in final
     * string. So, we return [1, 1].
     * Explanation 2:
     * 
     * No operation can give us more than three 1s in final string. So, we return
     * empty array [].
     */
    public ArrayList<Integer> flip(String A) {

        int sum = 0, maxSum = 0;
        int l = -1, r = -1;
        int bestL = -1, bestR = -1;

        for (int i = 0; i < A.length(); i++) {
            sum += (A.charAt(i) == '0' ? 1 : -1);
            if (sum < 0) {
                sum = 0;
                l = r = -1;
            } else {
                if (l == -1) {
                    l = i;
                }

                r = i;

                if (sum > maxSum) {
                    maxSum = sum;
                    bestL = l;
                    bestR = r;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (bestL != -1 && bestR != -1) {
            ans.add(bestL + 1);
            ans.add(bestR + 1);
        }

        return ans;
    }

    /*
     * Max Distance
     * Given an array A of integers of size N. Find the maximum of value of j - i
     * such that A[i] <= A[j].
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 1000000
     * 
     * -109 <= A[i] <= 109
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an integer array A of size N.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting the maximum value of j - i.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [3, 5, 4, 2]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 2
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * For A[0] = 3 and A[2] = 4, the ans is (2 - 0) = 2.
     * 
     * 
     * See Expected Output
     * 
     * 
     */
    public int maximumGap(final int[] A) {
        Pair[] pairs = new Pair[A.length];

        for(int i=0; i<A.length; i++)
        {
            pairs[i] = new Pair(A[i], i);
        }

        Arrays.sort(pairs, new PairComparator());

        int maxj = pairs[pairs.length-1].index;
        int ans = Integer.MIN_VALUE;

        for(int i=pairs.length-1; i>=0; i--)
        {
            ans = Math.max(ans, maxj - pairs[i].index);
            maxj = Math.max(maxj, pairs[i].index);
        }

        return ans;
    }

    /*
     * Minimum Swaps
     * Given an array of integers A and an integer B, find and return the minimum
     * number of swaps required to bring all the numbers less than or equal to B
     * together.
     * 
     * Note: It is possible to swap any two elements, not necessarily consecutive.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= length of the array <= 100000
     * -109 <= A[i], B <= 109
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
     * Return the minimum number of swaps.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [1, 12, 10, 3, 14, 10, 5]
     * B = 8
     * Input 2:
     * 
     * A = [5, 17, 100, 11]
     * B = 20
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
     * A = [1, 12, 10, 3, 14, 10, 5]
     * After swapping 12 and 3, A => [1, 3, 10, 12, 14, 10, 5].
     * After swapping the first occurence of 10 and 5, A => [1, 3, 5, 12, 14, 10,
     * 10].
     * Now, all elements less than or equal to 8 are together.
     * Explanation 2:
     * 
     * A = [5, 17, 100, 11]
     * After swapping 100 and 11, A => [5, 17, 11, 100].
     * Now, all elements less than or equal to 20 are together.
     * 
     */
    public int minimumSwaps(int[] A, int B) {
        int k = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= B) {
                k++;
            }
        }

        if (k == 1)
            return 0;

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (A[i] > B)
                count++;
        }

        int ans = count;
        for (int i = k; i < A.length; i++) {
            if (A[i] > B)
                count++;
            if (A[i - k] > B)
                count--;

            ans = Math.min(ans, count);
        }

        return ans;
    }

    /*  N/3 Repeat Number
    You're given a read only array of n integers. Find out if any integer occurs more than n/3 times in the array in linear time and constant additional space.
    If so, return the integer. If not, return -1.

    If there are multiple solutions, return any one.

    Example:

    Input: [1 2 3 1 1]
    Output: 1 
    1 occurs 3 times which is more than 5/3 times.
    */
    public int repeatedNumber(final List<Integer> a) {
        if (a.size() == 0)
            return -1;

        Integer maj1 = null, maj2 = null;
        int count1 = 0, count2 = 0;

        for (int i = 0; i < a.size(); i++) {
            if (maj1 == null) {
                maj1 = a.get(i);
                count1 = 1;
            }

            else if (maj2 == null) {
                maj2 = a.get(i);
                count2 = 1;
            } else {
                if (a.get(i) == maj1) {
                    count1++;
                } else if (a.get(i) == maj2) {
                    count2++;
                } else {
                    count1--;
                    count2--;

                    if (count1 == 0) {
                        maj1 = null;
                    }

                    if (count2 == 0) {
                        maj2 = null;
                    }
                }
            }
        }

        if (maj1 == null && maj2 == null)
            return -1;

        count1 = 0;
        count2 = 0;
        
        for (int i = 0; i < a.size(); i++) {
            if (maj1 != null && a.get(i) == maj1) {
                count1++;

                if (count1 > a.size() / 3)
                    return a.get(i);
            }

            if (maj2 != null && a.get(i) == maj2) {
                count2++;

                if (count2 > a.size() / 3)
                    return a.get(i);
            }
        }

        return -1;
    }
}