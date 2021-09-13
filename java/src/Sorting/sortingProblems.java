package Sorting;

import java.util.*;

public class sortingProblems {

    /* Noble Integer
     * Given an integer array A, find if an integer p exists in the
     * array such that the number of integers greater than p in the array equals to p.
     */
    public int CheckForNobelInteger(int[] A) {
        int n = A.length;

        Arrays.sort(A);

        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                continue;

            if (A[i] == (n - 1 - i))
                return 1;
        }

        if (A[n - 1] == 0)
            return 1;

        return -1;
    }

    /* Arithmetic Progression
    Given an integer array A of size N. Return 1 if the array can be rearranged to form an arithmetic progression, otherwise, return 0.
    A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
    */
    public int CheckForArithmeticProgression(int[] A) {
        if (A.length == 2)
            return 1;

        Arrays.sort(A);

        int d = A[1] - A[0];

        for (int i = 2; i < A.length; i++) {
            if ((A[i] - A[i - 1]) != d)
                return 0;
        }

        return 1;
    }

    /*Sort by Color
    Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
    Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.    
    Note: Using library sort function is not allowed.
    */
    public int[] SortColors(int[] A) {
        int[] B = new int[A.length];

        int[] C = new int[3];

        for (int i = 0; i < A.length; i++) {
            C[A[i]]++;
        }

        int j = 0;

        for (int i = 0; i < 3; i++) {
            while (j < B.length && C[i] > 0) {
                B[j++] = i;
                C[i]--;
            }
        }

        return B;
    }

    /* Kth Smallest Element
    Find the Bth smallest element in given array A .
    NOTE: Users should try to solve it in <= B swaps.
    */
    public int Kthsmallest(final int[] A, int B) {
        int i = 0;

        for (; i < A.length & B > 0; i++) {

            int min = i;

            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int t = A[min];
                A[min] = A[i];
                A[i] = t;
            }

            B--;
        }

        return A[i - 1];
    }

    /* Stepwise Selection Sort!
    Given an integer array A of size N.
    You need to sort the elements in increasing order using SelectionSort. Return a array containing the min value's index position before every iteration.
    NOTE:
    Consider 0 based indexing while looking for min value in each step of selection sort.
    There will be total N - 1 iterations in selection sort so the output array will contain N - 1 integers.
    */
    public int[] StepwiseSelectionSort(int[] A) {
        int n = A.length;
        int[] B = new int[n-1];
        
        for(int i=0; i<n-1; i++){
            int min = i;
            
            for(int j=i+1; j<n; j++){
                if(A[j]<A[min]){
                    min = j;
                }
            }
            
            int t = A[min];
            A[min]=A[i];
            A[i]=t;
            
            B[i]= min;
        }
        
        return B;
    }

    /* Elements Removal
    Given an integer array A of size N. In one operation, you can remove any element from the array, and the cost of this operation is the sum of all elements in the array present before this operation.
    Find the minimum cost to remove all elements from the array.
    */
    public int MinimumCostToRemoveAllElements(int[] A) {

        int sum = 0;

        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
        }

        Arrays.sort(A);

        int cost = 0;

        for (int i = A.length - 1; i >= 0; i--) {
            cost = cost + sum;
            sum = sum - A[i];
        }

        return cost;
    }

    /* Wave Array    Given an array of integers A, sort the array into a wave like array and return it, In other words, arrange the elements into a sequence such that
    a1 >= a2 <= a3 >= a4 <= a5.....
    NOTE : If there are multiple answers possible, return the one that's lexicographically smallest.
     */
    public int[] wave(int[] A) {
        
        int[] res = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            res[i] = A[i];
        }

        Arrays.sort(res);

        for (int i = 0; i < res.length - 1;) {
            int t = res[i];
            res[i] = res[i + 1];
            res[i + 1] = t;

            i = i + 2;
        }

        
        return res;
    }

    /* Largest Number
    Given a array A of non negative integers, arrange them such that they form the largest number.
    Note: The result may be very large, so you need to return a string instead of an integer.
    */
    public String largestNumber(final int[] A) {

        // Check if All 0, then return "0"
        boolean flag = true;

        for (int i = 0; i < A.length; i++) {
            if (A[i] != 0) {
                flag = false;
                break;
            }
        }

        if (flag)
            return "0";

        // Else Copy A into B, and custom sort B
        ArrayList<Integer> B = new ArrayList<Integer>();

        for (int i = 0; i < A.length; i++) {
            B.add(A[i]);
        }

        Collections.sort(B, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String A = String.valueOf(a);
                String B = String.valueOf(b);

                return (B + A).compareTo(A + B);
            }
        });

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < B.size(); i++) {
            res.append(B.get(i));
        }

        return res.toString();
    }

    /* Unique Elements
    You are given an array A of N elements. You have to make all elements unique, to do so in one step you can increase any number by one.
    Find the minimum number of steps.
    */
    public int MinStepsToMakeElementsUnique(int[] A) {
        Arrays.sort(A);

        int count = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                A[i] = A[i] + 1;
                count = count + 1;

            } else if (A[i] < A[i - 1]) {
                int steps = A[i - 1] - A[i] + 1;
                A[i] = A[i] + steps;
                count = count + steps;
            }
        }

        return count;
    }

    /* Minimize Difference
    Given an array of integers A of size, N. Minimize the absolute difference between the maximum and minimum element of the array.

    You can perform two types of operations at most B times in total to change the values in the array.
    Multiple operations can be performed on the same element.
    Increment : A[i] -> A[i] + 1.

    Decrement : A[i] -> A[i] - 1.

    Return the minimum difference possible.
    */
    public int MinDiffPossible(int[] A, int B) {
        int min = A[0], max = A[0];
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        freq.put(A[0], 1);

        for (int i = 1; i < A.length; i++) {
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
            freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);
        }

        while (B > 0 && min < max) {
            if (freq.get(min) < freq.get(max)) {
                if (B < freq.get(min))
                    break;

                freq.put(min + 1, freq.getOrDefault(min + 1, 0) + freq.get(min));
                B = B - freq.get(min);
                min++;
            } else {
                if (B < freq.get(max))
                    break;

                freq.put(max - 1, freq.getOrDefault(max - 1, 0) + freq.get(max));
                B = B - freq.get(max);
                max--;
            }
        }

        return max - min;
    }

    public char[] to_lower(char[] A) {
        char[] res = new char[A.length];
        for(int i=0; i<A.length; i++){
            if(A[i] >=65 && A[i]<=90){
                res[i]=(char)(A[i]+32);
            } else{
                res[i]=A[i];
            }
        }
        
        return res;
    }

}
