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

    public int SearchInSortedAndRotatedArray(int[] A, int x) {
        // base cases
        if (A[0] == x)
            return 0;

        if (A[A.length - 1] == x)
            return A.length - 1;

        int low = 0, high = A.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (A[mid] == x)
                return mid;

            if (A[low] < A[mid]) {
                if (x >= A[low] && x < A[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (x > A[mid] && x <= A[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;

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
}
