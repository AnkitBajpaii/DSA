package Searching;

import java.util.Arrays;
import java.util.HashSet;

public class searchingProblems {

    public int BinarySearchUtil(int[] A, int x, int low, int high) {
        // Assume A is sorted
        if (A[0] == x)
            return 0;

        if (A[A.length - 1] == x)
            return A.length - 1;

        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (A[mid] == x)
                return mid;

            if (x < A[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    public int BinarySearch(int[] A, int x) {
        return BinarySearchUtil(A, x, 0, A.length - 1);
    }

    public int FirstOccurence(int[] A, int x) {
        // Assume A is sorted
        int res = -1;
        int low = 0, high = A.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (A[mid] == x && (mid == 0 || A[mid] != A[mid - 1]))
                return mid;

            if (x > A[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return res;
    }

    public int LastOccurence(int[] A, int x) {
        // Assume A is sorted
        int res = -1;
        int low = 0, high = A.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (A[mid] == x && (mid == A.length - 1 || A[mid] != A[mid + 1]))
                return mid;

            if (x < A[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    public int CountOnesInBinarySortedArray(int[] A) {
        int low = 0, high = A.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (A[mid] == 0) {
                low = mid + 1;
            } else {
                if (mid == 0 || A[mid - 1] == 0) {
                    return A.length - mid;
                }
                high = mid - 1;
            }
        }

        return 0;
    }

    public int SquareRootFloor(int x) {
        int low = 1, high = x;
        int ans = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int sq = mid * mid;

            if (sq == x)
                return mid;

            if (sq > x) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }

        return ans;
    }

    public int SearchInInfiniteSortedArray(int[] A, int x) {
        // Assume A is sorted and has infinite no. of elements

        if (A[0] == x)
            return 0;

        int i = 1;
        while (A[i] < x) {
            i = i * 2;
        }

        if (A[i] == x)
            return i;

        return BinarySearchUtil(A, x, i / 2 + 1, i - 1);
    }

    // Find Peak Element. i.e Element which is not smaller than it's neighbours
    public int FindPeakElement(int[] A) {
        int low = 0, high = A.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if ((mid == 0 || A[mid] >= A[mid - 1]) && (mid == A.length - 1 || A[mid] <= A[mid + 1])) {
                return mid;
            }

            if (mid > 0 && A[mid - 1] >= A[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public void FindPairWithGivenSumInUnsortedArray(int arr[], int sum) {

        HashSet<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < arr.length; ++i) {
            int temp = sum - arr[i];

            if (s.contains(temp)) {
                System.out.println("Pair with given sum " + sum + " is (" + arr[i] + ", " + temp + ")");
            }
            s.add(arr[i]);
        }
    }

    public boolean FindPairWithGivenSumInSortedArray(int A[], int sum) {
        // Asuming A is sorted
        int low = 0, high = A.length - 1;
        while (low < high) {
            if (A[low] + A[high] == sum) {
                return true;
            }

            if (A[low] + A[high] > sum) {
                high--;
            } else {
                low++;
            }
        }

        return false;
    }

    public boolean FindTripletWithGivenSumInSortedArray(int A[], int sum) {
        // Asuming A is sorted
        for (int i = 0; i < A.length; i++) {
            int low = i + 1, high = A.length - 1;
            while (low < high) {
                if (A[low] + A[high] == sum - A[i]) {
                    return true;
                }

                if (A[low] + A[high] > sum - A[i]) {
                    high--;
                } else {
                    low++;
                }
            }
        }

        return false;
    }

    // Median of two sorted arrays
    public double MedianOfTwoSortedArrays(int[] A1, int[] A2) {
        int n1 = A1.length, n2 = A2.length;

        if (n2 < n1) {// Making sure A1 is smaller
            int[] t = A1;
            A1 = A2;
            A2 = t;
        }

        int low = 0, high = n1;

        while (low < high) {
            int i1 = (low + high) / 2;
            int i2 = (n1 + n2 + 1) / 2 - i1;

            int min1 = i1 == n1 ? Integer.MAX_VALUE : A1[i1];
            int max1 = i1 == 0 ? Integer.MIN_VALUE : A1[i1 - 1];

            int min2 = i2 == n2 ? Integer.MAX_VALUE : A2[i2];
            int max2 = i2 == 0 ? Integer.MIN_VALUE : A2[i2 - 1];

            if (max1 <= min2 && max2 <= min1) {
                if ((n1 + n2) % 2 == 0) {
                    return (double) (Math.max(max1, max2) + Math.min(min1, min2)) / 2;

                } else {
                    return (double) Math.max(max1, max2);
                }
            }

            if (max2 > min1) {
                high = i1 - 1;

            } else {
                low = i1 + 1;
            }
        }

        return -1;
    }

    // Median of a Row Wise Sorted Matrix Java
    public int MedianOfRowWiseSortedMatrix(int[][] mat, int r, int c) {
        int min = mat[0][0], max = mat[0][c - 1];

        for (int i = 0; i < r; i++) {
            if (mat[i][0] < min) {
                min = mat[i][0];
            }

            if (mat[i][c - 1] > max) {
                max = mat[i][c - 1];
            }
        }

        int medianPos = (r * c + 1) / 2;
        while (min < max) {
            int mid = (min + max) / 2;

            int midPos = 0;

            for (int i = 0; i < r; i++) {
                int pos = Arrays.binarySearch(mat[i], mid) + 1;
                midPos += pos;
            }

            if (midPos < medianPos) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return min;
    }

    public boolean isSudokuRowValid(String[] A, int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int i = rowStart; i < rowEnd; i++) {

            HashSet<Character> s = new HashSet<Character>();

            for (int j = colStart; j < colEnd; j++) {

                char ch = A[i].charAt(j);
                if (ch != '.') {
                    if (s.contains(ch)) {
                        return false;
                    }

                    s.add(ch);
                }
            }
        }
        return true;
    }

    public boolean isSudokuColumnValid(String[] A, int rowStart, int rowEnd, int colStart, int colEnd) {

        for (int j = colStart; j < colEnd; j++) {

            HashSet<Character> s = new HashSet<Character>();

            for (int i = rowStart; i < rowEnd; i++) {
                char ch = A[i].charAt(j);

                if (ch != '.') {
                    if (s.contains(ch)) {
                        return false;
                    }

                    s.add(ch);
                }
            }
        }

        return true;
    }

    public boolean isSudokuMatrixValid(String[] A, int rowStart, int rowEnd, int colStart, int colEnd) {
        HashSet<Character> s = new HashSet<Character>();

        for (int i = rowStart; i < rowEnd; i++) {

            for (int j = colStart; j < colEnd; j++) {

                char ch = A[i].charAt(j);
                if (ch != '.') {
                    if (s.contains(ch)) {
                        return false;
                    }

                    s.add(ch);
                }
            }
        }

        return true;
    }

    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int isValidSudoku(final String[] A) {

        if (!isSudokuRowValid(A, 0, 9, 0, 9))
            return 0;

        if (!isSudokuColumnValid(A, 0, 9, 0, 9))
            return 0;

        if (!isSudokuMatrixValid(A, 0, 3, 0, 3))
            return 0;

        if (!isSudokuMatrixValid(A, 0, 3, 3, 6))
            return 0;

        if (!isSudokuMatrixValid(A, 0, 3, 6, 9))
            return 0;

        int rowStart = 0, rowEnd = 3;

        while (rowEnd <= 9) {

            if (!isSudokuMatrixValid(A, rowStart, rowEnd, 0, 3))
                return 0;

            if (!isSudokuMatrixValid(A, rowStart, rowEnd, 3, 6))
                return 0;

            if (!isSudokuMatrixValid(A, rowStart, rowEnd, 6, 9))
                return 0;

            rowStart = rowStart + 3;
            rowEnd = rowEnd + 3;
        }

        return 1;
    }

    /* Find a peak element
    Given an array of integers A, find and return the peak element in it. An array element is peak if it is NOT smaller than its neighbors.

    For corner elements, we need to consider only one neighbor. We ensure that answer will be unique.

    NOTE: Users are expected to solve this in O(log(N)) time.



    Problem Constraints
    1 <= |A| <= 100000

    1 <= A[i] <= 109



    Input Format
    The only argument given is the integer array A.



    Output Format
    Return the peak element.



    Example Input
    Input 1:

    A = [1, 2, 3, 4, 5]
    Input 2:

    A = [5, 17, 100, 11]


    Example Output
    Output 1:

    5
    Output 2:

    100


    Example Explanation
    Explanation 1:

    5 is the peak.
    Explanation 2:

    100 is the peak.
    */
    public int findPeakElement(int[] A) {
        int n = A.length;
        if(n==1) return A[0];

        if(A[0] >= A[1]) return A[0];

        if(A[n-1] >= A[n-2]) return A[n-1];

        int l=1, r=n-2;
        
        while(l<=r)
        {
            int m = (l+r)/2;
            if(A[m] > A[m-1] && A[m] > A[m+1]) return A[m];

            if(A[m-1] > A[m])
            {
                r = m - 1;
            } else{
                l = m + 1;
            }
        }

        return -1;
    }

    /* Single Element in a Sorted Array
    Problem Description
    Given a sorted array of integers A where every element appears twice except for one element which appears once, find and return this single element that appears only once.

    NOTE: Users are expected to solve this in O(log(N)) time.



    Problem Constraints
    1 <= |A| <= 100000

    1 <= A[i] <= 10^9



    Input Format
    The only argument given is the integer array A.



    Output Format
    Return the single element that appears only once.



    Example Input
    Input 1:

    A = [1, 1, 7]
    Input 2:

    A = [2, 3, 3]


    Example Output
    Output 1:

    7
    Output 2:

    2


    Example Explanation
    Explanation 1:

    7 appears once
    Explanation 2:

    2 appears once
    */
    public int SingleElementInSortedArray(int[] A) {
        int n = A.length;

        if (A[n - 1] != A[n - 2])
            return A[n - 1];

        if (A[0] != A[1])
            return A[0];

        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (A[mid] != A[mid - 1] && A[mid] != A[mid + 1]) {
                return A[mid];
            }

            if (A[mid] == A[mid - 1]) {
                if (mid % 2 != 0) {
                    l = mid + 1;
                } else {
                    r = mid - 2;
                }

            } else {
                if (mid % 2 == 0) {
                    l = mid + 2;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1;
    }

    /* Square Root of Integer
    Given an integer A.

    Compute and return the square root of A.

    If A is not a perfect square, return floor(sqrt(A)).

    DO NOT USE SQRT FUNCTION FROM THE STANDARD LIBRARY.

    NOTE: Do not use the sqrt function from the standard library. Users are expected to solve this in O(log(A)) time.



    Problem Constraints
    0 <= A <= 1010



    Input Format
    The first and only argument given is the integer A.



    Output Format
    Return floor(sqrt(A))



    Example Input
    Input 1:

    11
    Input 2:

    9


    Example Output
    Output 1:

    3
    Output 2:

    3


    Example Explanation
    Explanation:

    When A = 11 , square root of A = 3.316. It is not a perfect square so we return the floor which is 3.
    When A = 9 which is a perfect square of 3, so we return 3.
    */
    public int sqrt(int A) {
        int l = 1, r = A;
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            // mid * mid could overflow to avoid it, use trick
            if (mid == A / mid)
                return mid;

            if (mid > A / mid) {
                r = mid - 1;

            } else {
                ans = mid;
                l = mid + 1;
            }
        }

        return ans;
    }

    /* Rotated Sorted Array Search
    Given a sorted array of integers A of size N and an integer B.

    array A is rotated at some pivot unknown to you beforehand.

    (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2 ).

    You are given a target value B to search. If found in the array, return its index otherwise, return -1.

    You may assume no duplicate exists in the array.

    NOTE: Users are expected to solve this in O(log(N)) time.



    Problem Constraints
    1 <= N <= 1000000

    1 <= A[i] <= 10^9

    all elements in A are distinct.



    Input Format
    The first argument given is the integer array A.

    The second argument given is the integer B.



    Output Format
    Return index of B in array A, otherwise return -1



    Example Input
    Input 1:

    A = [4, 5, 6, 7, 0, 1, 2, 3]
    B = 4 
    Input 2:

    A = [1]
    B = 1


    Example Output
    Output 1:

    0 
    Output 2:

    0


    Example Explanation
    Explanation 1:

    
    Target 4 is found at index 0 in A. 
    Explanation 2:

    
    Target 1 is found at index 0 in A.
    */
    
    public int SearchInSortedAndRotatedArray(final int[] A, int B) {
        if(B == A[0]) return 0;

        int n = A.length;

        if(B == A[n-1]) return n-1;

        int p = findPivotInSortedAndRotatedArray(A);

        if(p == -1)
        {
            return BinarySearchUtil(A, B, 0, n-1);
        }

        if(B > A[0])
        {
            // lies in first half
            return BinarySearchUtil(A, B, 0, p-1);
        } 
        
        // lies in second half
        return BinarySearchUtil(A, B, p, n-1);
    }

    /* Find Pivot in sorted and rotated array*/
    public int findPivotInSortedAndRotatedArray(int[] A)
    {
        // pivot is the index of smallest element in sorted and rotated array A
        int l=0, r=A.length-1;

        while(l<=r)
        {
            int mid = (l+r)/2;

            if(A[mid] > A[0])
            {
                // mid is in first half and pivot cannot be in first half
                l = mid + 1;
            } else {
                // mid is in second half
                if(A[mid] < A[mid-1]) return mid;

                r = mid - 1;                
            }
        }

        return -1;
    }
}
