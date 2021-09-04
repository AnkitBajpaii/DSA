package Arrays;

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
        int[] lMax = new int[A.length];
        int[] rMax = new int[A.length];

        lMax[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            lMax[i] = Math.max(lMax[i - 1], A[i]);
        }

        rMax[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], A[i]);
        }

        int ans = 0;
        for (int i = 1; i < A.length; i++) {
            ans += Math.min(lMax[i], rMax[i]) - A[i];
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
        int n = A.length;

        int[][] B = new int[2 * n - 1][n];

        int br = 0;

        for (int c = 0; c < n; c++) {

            int i = 0, j = c, bc = 0;
            while (i < n && j >= 0) {
                B[br][bc] = A[i][j];
                i++;
                j--;
                bc++;
            }
            br++;
        }

        for (int r = 1; r < n; r++) {

            int i = r, j = n - 1, bc = 0;
            while (i < n && j >= 0) {
                B[br][bc] = A[i][j];
                i++;
                j--;
                bc++;
            }
            br++;
        }

        return B;
    }

    // You have given a string A having Uppercase English letters.
    // You have to find that how many times subsequence "AG" is there in the given
    // string.
    // I/P: A = "ABCGAG" O/P: 3
    public int FindCountOfSubsequenceAG(String A) {
        int ans = 0;

        int count = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == 'G') {
                count++;
            } else {
                if (A.charAt(i) == 'A') {
                    ans = ans + count;
                }
            }

        }

        return ans % 1000000007;
    }

    // Given an array A. Find the size of the smallest subarray such that it
    // contains atleast one occurrence of the maximum value of the array
    // and atleast one occurrence of the minimum value of the array.
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

        int minIndx = -1, maxIndx = -1, ans = Integer.MAX_VALUE;

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

    // Length of longest consecutive ones
    // Given a binary string A. It is allowed to do at most one swap between any 0
    // and 1. Find and return the length of the longest consecutive 1’s that can be
    // achieved.
    public int LengthOfLongestConsecutiveOnes(String A) {
        int[] L = new int[A.length()];
        int[] R = new int[A.length()];

        L[0] = A.charAt(0) == '0' ? 0 : 1;

        for (int i = 1; i < A.length(); i++) {
            if (A.charAt(i) == '1') {
                L[i] = L[i - 1] + 1;
            }
        }

        R[A.length() - 1] = A.charAt(A.length() - 1) == '0' ? 0 : 1;

        for (int i = A.length() - 2; i >= 0; i--) {
            if (A.charAt(i) == '1') {
                R[i] = R[i + 1] + 1;
            }
        }

        int countTotalOnes = 0;

        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '1') {
                countTotalOnes++;
            }
        }

        int ans = 1;// to handle edge case like "01"

        for (int i = 1; i < A.length() - 1; i++) {
            if (A.charAt(i) == '1') {
                ans = Math.max(ans, L[i - 1] + R[i + 1] + 1);
            } else {
                if (L[i - 1] + R[i + 1] < countTotalOnes) {
                    ans = Math.max(ans, L[i - 1] + R[i + 1] + 1);
                } else {
                    ans = Math.max(ans, L[i - 1] + R[i + 1]);
                }

            }
        }

        return ans;
    }
}
