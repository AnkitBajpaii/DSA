package Sorting;

import java.util.*;

public class sortingProblems {

    /* Noble Integer
     * Given an integer array A, find if an integer p exists in the
     * array such that the number of integers greater than p in the array equals to p.
     */
    public int CheckForNobelInteger(int[] A) {
        
        Arrays.sort(A);

        for (int i = 0; i < A.length; i++) {
            if (i == A.length - 1 && A[i] == 0)
                return 1;

            if (A[i] >= 0 && i + 1 < A.length && A[i] != A[i + 1] && A[i] == A.length - 1 - i) {
                return 1;
            }
        }

        return -1;
    }

    /* Arithmetic Progression
    Given an integer array A of size N. Return 1 if the array can be rearranged to form an arithmetic progression, otherwise, return 0.
    A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
    */
    public int CheckForArithmeticProgression(int[] A) {
        Arrays.sort(A);

        int d = A[1] - A[0];

        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] != d)
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

        int cost = 0;

        // approach 1
        {
            cost = 0;

            int sum = 0;

            for (int i = 0; i < A.length; i++) {
                sum = sum + A[i];
            }
    
            Arrays.sort(A);
    
            for (int i = A.length - 1; i >= 0; i--) {
                cost = cost + sum;
                sum = sum - A[i];
            }
        }

        // approach 2
        {
            cost = 0;

            Arrays.sort(A);

            for (int i = A.length - 1; i >= 0; i--) {
                cost += A[i] * (A.length - i);
            }

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
    i/p: [3,30,34,5,9]
    o/p: "9534330"
    */
    public String largestNumber(final int[] A) {
        
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

        String str = res.toString();
        int pos=0;
        while(pos + 1 < str.length() && str.charAt(pos) == '0'){
            pos++;
        }
        return str.substring(pos);
    }

    /* Unique Elements
    You are given an array A of N elements. You have to make all elements unique, to do so in one step you can increase any number by one.
    Find the minimum number of steps.
    */
    public int MinStepsToMakeElementsUnique(int[] A) {
        Arrays.sort(A);

        int count = 0;

        for (int i = 1; i < A.length; i++) {
            if(A[i] == A[i-1] || A[i] < A[i-1]){
                int steps = A[i-1] - A[i]+1;
                count = count + steps;
                A[i] = A[i-1]+1;
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
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();

        for (int i = 0; i < A.length; i++) {
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

    /* Sort the Unsorted Array
    Problem Description

    You are given an integer array A having N integers.

    You have to find the minimum length subarray A[l..r] such that sorting this subarray makes the whole array sorted.



    Problem Constraints

    1 <= N <= 105

    -109 <= A[i] <= 109



    Input Format

    First and only argument is an integer array A.



    Output Format

    Return an integer denoting the minimum length.



    Example Input

    Input 1:

    A = [0, 1, 15, 25, 6, 7, 30, 40, 50] 
    Input 2:

    A = [10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60] 


    Example Output

    Output 1:

    4 
    Output 2:

    6 


    Example Explanation

    Explanation 1:

    The smallest subarray to be sorted to make the whole array sorted =  A[3..6] i.e, subarray lying between positions 3 and 6. 
    Explanation 2:

    The smallest subarray to be sorted to make the whole array sorted =  A[4..9] i.e, subarray lying between positions 4 and 9. 
    */
    public int minimumLengthSubarraySort(int[] A)
    {
        int[] B = A.clone();
        Arrays.sort(B);

        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] != B[i] && A[j] != B[j])
                break;

            if (A[i] == B[i])
                i++;

            if (A[j] == B[j])
                j++;

        }

        return j - i + 1;
    }

    /* Minimize the absolute difference
    Given three sorted arrays A, B and Cof not necessarily same sizes.

Calculate the minimum absolute difference between the maximum and minimum number from the triplet a, b, c such that a, b, c belongs arrays A, B, C respectively. i.e. minimize | max(a,b,c) - min(a,b,c) |.

Example :

Input:

A : [ 1, 4, 5, 8, 10 ]
B : [ 6, 9, 15 ]
C : [ 2, 3, 6, 6 ]
Output:

1
Explanation: We get the minimum difference for a=5, b=6, c=6 as | max(a,b,c) - min(a,b,c) | = |6-5| = 1.
    */
    int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public int solve(int[] A, int[] B, int[] C) {
        int i = 0, j = 0, k = 0;
        int ans = Integer.MAX_VALUE;

        while (i < A.length && j < B.length && k < C.length) {
            int val = max(A[i], B[j], C[k]) - min(A[i], B[j], C[k]);
            ans = Math.min(ans, val);

            int minVal = min(A[i], B[j], C[k]);

            if (minVal == A[i])
                i++;

            else if (minVal == B[j])
                j++;

            else
                k++;

        }

        return ans;
    }

}
