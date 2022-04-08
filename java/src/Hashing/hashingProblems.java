package Hashing;

import java.util.*;

public class hashingProblems {
    // Q. Valid Sudoku. Determine if a Sudoku is valid, according to:
    // http://sudoku.com.au/TheRules.aspx
    // The Sudoku board could be partially filled, where empty cells are filled with
    // the character '.'.

    // The input corresponding to the above configuration :
    // ["53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1",
    // "7...2...6", ".6....28.", "...419..5", "....8..79"]
    // A partially filled sudoku which is valid.

    // Note:
    // • A valid Sudoku board (partially filled) is not necessarily solvable. Only
    // the filled cells need to be validated.
    // Return 0 / 1 ( 0 for false, 1 for true ) for this problem

    public boolean isSudokuRowValid(String[] A, int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int i = rowStart; i <= rowEnd; i++) {

            HashSet<Character> s = new HashSet<Character>();

            for (int j = colStart; j <= colEnd; j++) {

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

        for (int j = colStart; j <= colEnd; j++) {

            HashSet<Character> s = new HashSet<Character>();

            for (int i = rowStart; i <= rowEnd; i++) {
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

        for (int i = rowStart; i <= rowEnd; i++) {

            for (int j = colStart; j <= colEnd; j++) {

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
        // check if each row is unique
        if (!isSudokuRowValid(A, 0, 8, 0, 8))
            return 0;

        // check if each column is unique
        if (!isSudokuColumnValid(A, 0, 8, 0, 8))
            return 0;

        // check if each 3x3 tile is valid
        if (!isSudokuMatrixValid(A, 0, 2, 0, 2))
            return 0;
        if (!isSudokuMatrixValid(A, 0, 2, 3, 5))
            return 0;
        if (!isSudokuMatrixValid(A, 0, 2, 6, 8))
            return 0;

        if (!isSudokuMatrixValid(A, 3, 5, 0, 2))
            return 0;
        if (!isSudokuMatrixValid(A, 3, 5, 3, 5))
            return 0;
        if (!isSudokuMatrixValid(A, 3, 5, 6, 8))
            return 0;

        if (!isSudokuMatrixValid(A, 6, 8, 0, 2))
            return 0;
        if (!isSudokuMatrixValid(A, 6, 8, 3, 5))
            return 0;
        if (!isSudokuMatrixValid(A, 6, 8, 6, 8))
            return 0;

        return 1;
    }

    /*
     * Permutations of A in B Problem Description
     * 
     * You are given two strings A and B of size N and M respectively.
     * 
     * You have to find the count of all permutations of A present in B as a
     * substring. You can assume a string will have only lowercase letters.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N < M <= 105
     * 
     * 
     * 
     * Input Format
     * 
     * Given two argument, A and B of type String.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a single Integer, i.e number of permutations of A present in B as a
     * substring.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = "abc" B = "abcbacabc" Input 2:
     * 
     * A = "aca" B = "acaa"
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 5 Output 2:
     * 
     * 2
     */
    private boolean isSame(int[] A, int[] B) {
        if (A.length != B.length)
            return false;

        for (int i = 0; i < A.length; i++) {
            if (A[i] != B[i])
                return false;
        }

        return true;
    }

    public int CountOfAllAnagramsA_In_B(String A, String B) {
        int[] count1 = new int[26];
        for(int i=0; i<A.length(); i++)
        {
            count1[A.charAt(i) - 'a']++;
        }

        int[] count2 = new int[26];
        for(int i=0; i<A.length(); i++)
        {
            count2[B.charAt(i) - 'a']++;
        }

        int ans = 0;       

        for(int i=A.length(); i<B.length(); i++)
        {
            if(isSame(count1, count2))
            {
                ans++;
            }

            count2[B.charAt(i) - 'a']++;
            count2[B.charAt(i - A.length()) - 'a']--;
        }

        if(isSame(count1, count2))
            {
                ans++;
            }

        return ans;
    }

    /*
     * Diffk II Check if there exists pair with diff K Given an array A of integers
     * and another non negative integer k, find if there exists 2 indices i and j
     * such that A[i] - A[j] = k, i != j. A : [1 5 3] k : 2 O/P: 1
     */
    public int checkPairWithDiffK(final int[] A, int K) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }

        if (K == 0) {
            return set.size() < A.length ? 1 : 0;
        }

        for (int i = 0; i < A.length; i++) {
            // a-b=k => for given a, search b = a-k in map
            if (set.contains(A[i] - K))
                return 1;
        }

        return 0;
    }

    /*
     * Find count of pairs with diff K( an extension to above problem)
     */
    public int FindPairsWithDifferenceEqualToK(int[] A, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int x : A) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int count = 0;
        if (k == 0) {
            // idea : if a number repeats n times, there will be n-1 + n-2 + ..2 + 1 ->
            // n*(n-1)/2 pairs with difference 0
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int n = entry.getValue();
                count += n * (n - 1) / 2;
            }

        } else {
            for (int i = 0; i < A.length; i++) {
                int a = A[i];
                int b = a - k;

                // a-b=k => for given a, search b = a-k in map
                if (map.containsKey(b)) {
                    count += map.get(b);
                }
            }
        }

        return count;
    }

    // Given N array elements, and Q queries, for each query (i,j), find sum of all
    // elements in range i to j, where i<=j
    public void SumOfElementsInGivenRange(int[] A, int[] start, int[] end) {
        int[] pf = new int[A.length];

        pf[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            pf[i] = pf[i - 1] + A[i];
        }

        for (int i = 0; i < start.length; i++) {
            System.out.println("Sum of all elements from " + start[i] + " to " + end[i] + " is ");

            System.out.print(pf[end[i]] - pf[start[i]] + A[i]);
        }
    }

    // Given n array elements, check if there exist a sub array with zero sum 
    //( Sub-Array with 0 sum)
    public int CheckIfThereExistSubArrayWithZeroSum(int[] A) {

        HashSet<Integer> s = new HashSet<Integer>();       

        int prefixSum = 0;
        s.add(prefixSum);

        for (int i = 0; i < A.length; i++) {
            prefixSum = prefixSum + A[i];

            if (s.contains(prefixSum)) {
                return 1;
            }

            s.add(prefixSum);
        }

        return 0;
    }

    /*
     * Subarray with given sum Given an array of positive integers A and an integer
     * B, find and return first continuous subarray which adds to B. If the answer
     * does not exist return an array with a single element "-1". First sub-array
     * means the sub-array for which starting index in minimum. Problem Constraints
     * 1 <= length of the array <= 100000 1 <= A[i] <= 10^9 1 <= B <= 10^9 Input
     * Format The first argument given is the integer array A. The second argument
     * given is integer B. Output Format Return the first continuous sub-array which
     * adds to B and if the answer does not exist return an array with a single
     * element "-1". Example Input A = [1, 2, 3, 4, 5] B = 5, O/P: [2, 3]
     */
    public ArrayList<Integer> SubArrayWithGivenSum(ArrayList<Integer> A, int B) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        Long pSum = 0L;

        for (int i = 0; i < A.size(); i++) {
            pSum += A.get(i);
            if (pSum == B) {
                for (int j = 0; j <= i; j++) {
                    res.add(A.get(j));
                }

                return res;
            }

            if (map.containsKey(pSum - B)) {
                for (int j = map.get(pSum - B) + 1; j <= i; j++) {
                    res.add(A.get(j));
                }

                return res;
            }

            map.put(pSum, i);
        }

        res.add(-1);
        return res;
    }

    // Given an array, find length of longest subarray with zero sum
    public int LengthOfLongestSubArrayWithZeroSum(int[] A) {
        int prefixSum = 0;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;

        for (int i = 0; i < A.length; i++) {
            prefixSum = prefixSum + A[i];

            if (prefixSum == 0) {

                res = i + 1;
            }

            if (map.containsKey(prefixSum)) {
                res = Math.max(res, i - map.get(prefixSum));
            } else {
                map.put(prefixSum, i);
            }
        }

        return res;
    }

    // Given an array, Count the number of subarrays with given sum
    public int CountSubArrayWithGivenSum(int[] A, int sum) {
        int prefixSum = 0;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;

        for (int i = 0; i < A.length; i++) {
            prefixSum = prefixSum + A[i];

            if (prefixSum == sum) {
                res++;
            }

            if (map.containsKey(prefixSum - sum)) {
                res = res + map.get(prefixSum - sum);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return res;
    }

    // Given an array, find length of longest subarray with sum K
    public int LengthOfLongestSubArrayWithGivenSum(int[] A, int K) {
        int prefixSum = 0;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;

        for (int i = 0; i < A.length; i++) {
            prefixSum = prefixSum + A[i];

            if (prefixSum == K) {
                res = i + 1;
            }

            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }

            if (map.containsKey(prefixSum - K)) {
                res = Math.max(res, i - map.get(prefixSum - K));
            }
        }

        return res;
    }

    // Given a binary array, find length of longest subarray with equal zero and one
    public int LengthOfLongestSubArrayWithEqualZeroAndOne(int[] A) {
        int prefixSum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            prefixSum = prefixSum + (A[i] == 0 ? -1 : A[i]);

            if (prefixSum == 0) {

                res = Math.max(res, i + 1);
            }

            if (map.containsKey(prefixSum)) {
                res = Math.max(res, i - map.get(prefixSum));
            } else {
                map.put(prefixSum, i);
            }
        }

        return res;
    }

    // Longest common span with same sum in binary array
    public int LengthOfLongestCommonSpanWithSameSum(int[] A, int[] B) {
        int[] temp = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            temp[i] = A[i] - B[i];
        }

        int res = LengthOfLongestSubArrayWithZeroSum(temp);

        return res;
    }

    /*
     * Perfect Cards Problem Description
     * 
     * Tom and Harry are given N numbers, with which they play a game as a team.
     * 
     * Initially, Tom chooses a particular number P from the N numbers, and he takes
     * away all the numbers that are equal to P.
     * 
     * Next, Harry chooses a different number Q, different from what Tom chose, and
     * takes away all the numbers equal to Q from the remaining N numbers.
     * 
     * They win the game if they can take all the numbers by doing the above
     * operation once and if each of them has the same amount of numbers towards the
     * end.
     * 
     * If they win, return the string "WIN", else return "LOSE".
     */
    public String perfectCards(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int x : A) {
            map.put(x, map.getOrDefault(x, 0) + 1);

            if (map.size() > 2) {
                return "LOSE";
            }
        }

        int freq1 = -1, freq2 = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (freq1 == -1) {
                freq1 = entry.getValue();
            }

            else if (freq2 == -1) {
                freq2 = entry.getValue();
            }
        }

        if (freq1 == freq2)
            return "WIN";

        return "LOSE";

    }

    /*
     * Is Dictionary? In an alien language, surprisingly they also use english
     * lowercase letters, but possibly in a different order. The order of the
     * alphabet is some permutation of lowercase letters. Given an array of words A
     * of size N written in the alien language, and the order of the alphabet
     * denoted by string B of size 26, return 1 if and only if the given words are
     * sorted lexicographicaly in this alien language else return 0. Problem
     * Constraints: 1 <= N, length of each word <= 10^5 Sum of length of all words
     * <= 2 * 10^6 O/P: Return 1 if and only if the given words are sorted
     * lexicographicaly in this alien language else return 0.
     */
    public boolean isLexographicallySmaller(String A, String B, HashMap<Character, Integer> map) {
        int i = 0, j = 0;
        while (i < A.length() && j < B.length()) {
            if (map.get(A.charAt(i)) < map.get(B.charAt(j)))
                return true;
            if (map.get(B.charAt(j)) < map.get(A.charAt(i)))
                return false;

            i++;
            j++;
        }

        if (i == A.length() && j == B.length())
            return true;

        if (i == A.length())
            return true;

        return false;

    }

    public int areStringsInLexographicalOrder(String[] A, String B) {
        // qwertyuiopasdfghjklzxcvbnm
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < B.length(); i++) {
            map.put(B.charAt(i), i);
        }

        for (int i = 1; i < A.length; i++) {
            if (!isLexographicallySmaller(A[i - 1], A[i], map)) {
                return 0;
            }
        }

        return 1;
    }

    /*
     * Colorful Number Problem Description
     * 
     * For Given Number A find if its COLORFUL number or not.
     * 
     * If number A is a COLORFUL number return 1 else return 0.
     * 
     * What is a COLORFUL Number:
     * 
     * A number can be broken into different contiguous sub-subsequence parts.
     * Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324
     * 245. And this number is a COLORFUL number, since product of every digit of a
     * contiguous subsequence is different.
     */
    public int colorful(int A) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (A > 0) {
            nums.add(A % 10);
            A = A / 10;
        }

        Collections.reverse(nums);

        HashSet<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < nums.size(); i++) {
            int product = 1;

            for (int j = i; j < nums.size(); j++) {

                product = product * nums.get(j);
                if (set.contains(product))
                    return 0;

                set.add(product);
            }
        }

        return 1;
    }

    /*
     * Shaggy and distances  ( Minimum distinct between any 2 same elements)
     * Shaggy has an array A consisting of N elements. We call
     * a pair of distinct indices in that array as a special pair if elements at
     * that index in the array are equal.
     * 
     * Shaggy wants you to find a special pair such that distance between that pair
     * is minimum. Distance between two indices is defined as |i-j|. If there is no
     * special pair in the array then return -1. Return one integer corresponding to
     * the minimum possible distance between a special pair.
     */
    public int ShaggyAndDistances(int[] A) {
        int ans = Integer.MAX_VALUE;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                ans = Math.min(ans, i - map.get(A[i]));
            }

            map.put(A[i], i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /*
     Problem Description

    Given two integer array A and B of size N and M respectively. Your task is to find all the common elements in both the array.

    NOTE:

    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.


    Problem Constraints

    1 <= N, M <= 105

    1 <= A[i] <= 109



    Input Format

    First argument is an integer array A of size N.

    Second argument is an integer array B of size M.



    Output Format

    Return an integer array denoting the common elements.



    Example Input

    Input 1:

    A = [1, 2, 2, 1]
    B = [2, 3, 1, 2]
    Input 2:

    A = [2, 1, 4, 10]
    B = [3, 6, 2, 10, 10]


    Example Output

    Output 1:

    [1, 2, 2]
    Output 2:

    [2, 10]


    Example Explanation

    Explanation 1:

    Elements (1, 2, 2) appears in both the array. Note 2 appears twice in both the array.
    Explantion 2:

    Elements (2, 10) appears in both the array.
     */
    public ArrayList<Integer> commonElements(ArrayList<Integer> A, ArrayList<Integer> B) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int x:A){
            map.put(x, map.getOrDefault(x,0)+1);
        }
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        for(int x:B){
            if(map.containsKey(x)){
                res.add(x);
                map.put(x, map.get(x) - 1);
                if(map.get(x) == 0)
                {
                    map.remove(x);
                }
            }
        }
        
        return res;
    }

    /*
     * Check Palindrome! Problem Description * Given a string A consisting of
     * lowercase characters. Check if characters of the given string can be
     * rearranged to form a palindrome.* Return 1 if it is possible to rearrange the
     * characters of the string A such that it becomes a palindrome else return 0.
     * Problem Constraints 1 <= |A| <= 10^5* A consists only of lower-case
     * characters. Input Format First argument is an string A.* Output Format Return
     * 1 if it is possible to rearrange the characters of the string A such that it
     * becomes a palindrome else return 0. Example Input 1: A = "abcde" Input 2: A =
     * "abbaee" Example Output Output 1: 0, Output 2: 1*
     * 
     * Example Explanation Explanation 1: No possible rearrangement to make the
     * string palindrome. Explanation 2: Given string "abbaee" can be rearranged to
     * "aebbea" to form a palindrome.
     */
    public int checkPalindrome(String A) {
        
        if (A.length() == 1)
            return 1;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < A.length(); i++) {
            map.put(A.charAt(i), map.getOrDefault(A.charAt(i), 0) + 1);
        }

        if (map.size() == A.length())
            return 0;

        int count = 0;
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if (e.getValue() % 2 != 0) {
                count++;
            }
        }

        return count > 1 ? 0 : 1;
    }

    /* 
     * Longest Consecutive Sequence (Longest Consecutive Sub Sequence) Longest
     * Consecutive Sequence Given an unsorted integer array A of size N. Find the
     * length of the longest set of consecutive elements from the array A. Input: A
     * = [100, 4, 200, 1, 3, 2] O/P: 4
     */
    public int longestConsecutiveSubsequence(final int[] A) {
        // 100, 4, 200, 1, 3, 2
        HashSet<Integer> set = new HashSet<Integer>();

        int ans = Integer.MIN_VALUE;

        for(int i=0; i<A.length; i++)
        {
            set.add(A[i]);
        }

        for(int i=0; i<A.length; i++)
        {
            if(set.contains(A[i] - 1))
            {
                continue;
            }

            int x = A[i];
            while(set.contains(x))
            {
                x++;
            }

            ans = Math.max(ans, x - A[i]);
        }

        return ans;
    }

    /*
     * Distinct Numbers in Window 
     * Problem Description You are given an array of N
     * integers, A1, A2 ,..., AN and an integer B. Return the of count of distinct
     * numbers in all windows of size B. Formally, return an array of size N-B+1
     * where i'th element in this array contains number of distinct elements in
     * sequence Ai, Ai+1 ,..., Ai+B-1. NOTE: if B > N, return an empty array. input:
     * A = [1, 2, 1, 3, 4, 3], B = 3 output: [2, 3, 3, 2]
     */
    public int[] CountDistinctElementsInWindowOfSizeK(int[] A, int B) {
        int N = A.length;
        int[] res = new int[N - B + 1];
        if (B > N)
            return res;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < B; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        for (int i = B; i < N; i++) {
            res[i - B] = map.size();

            map.put(A[i], map.getOrDefault(A[i], 0) + 1);

            map.put(A[i - B], map.get(A[i - B]) - 1);

            if (map.get(A[i - B]) == 0) {
                map.remove(A[i - B]);
            }
        }

        res[N - B] = map.size();

        return res;
    }

    /* Largest Continuous Sequence Zero Sum
    Problem Description

    Given an array A of N integers.

    Find the largest continuous sequence in a array which sums to zero.



    Problem Constraints

    1 <= N <= 106

    -107 <= A[i] <= 107



    Input Format

    Single argument which is an integer array A.



    Output Format

    Return an array denoting the longest continuous sequence with total sum of zero.

    NOTE : If there are multiple correct answers, return the sequence which occurs first in the array.



    Example Input

    A = [1,2,-2,4,-4]


    Example Output

    [2,-2,4,-4]


    Example Explanation

    [2,-2,4,-4] is the longest sequence with total sum of zero.

    */
    public ArrayList<Integer> LargestContinuousSequenceZeroSum(ArrayList<Integer> A) {
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        Long pSum = 0L;
        int maxDist = -1;
        int start = -1, end = -1;

        map.put(pSum, -1);

        for (int i = 0; i < A.size(); i++) {
            pSum += A.get(i);
            if (pSum == 0) {
                start = 0;
                end = i;
            }

            if (map.containsKey(pSum)) {
                if (i - map.get(pSum) > maxDist) {
                    start = map.get(pSum) + 1;
                    end = i;
                    maxDist = i - map.get(pSum);
                }

            } else {
                map.put(pSum, i);
            }
        }

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (start != -1) {
            for (int i = start; i <= end; i++) {
                res.add(A.get(i));
            }
        }

        return res;
    }

    /* Sort Array in given Order
    Given two arrays of integers A and B, Sort A in such a way that the relative order among the elements will be the same as those are in B.
    For the elements not present in B, append them at last in sorted order.

    Return the array A after sorting from the above method.

    NOTE: Elements of B are unique.



    Problem Constraints
    1 <= length of the array A <= 100000

    1 <= length of the array B <= 100000

    -10^9 <= A[i] <= 10^9



    Input Format
    The first argument given is the integer array A.

    The second argument given is the integer array B.



    Output Format
    Return the array A after sorting as described.



    Example Input
    Input 1:

    A = [1, 2, 3, 4, 5]
    B = [5, 4, 2]
    Input 2:

    A = [5, 17, 100, 11]
    B = [1, 100]


    Example Output
    Output 1:

    [5, 4, 2, 1, 3]
    Output 2:

    [100, 5, 11, 17]


    Example Explanation
    Explanation 1:

    Simply sort as described.
    Explanation 2:

    Simply sort as described.
    */
    public ArrayList<Integer> sortArrayInGivenOrder(ArrayList<Integer> A, ArrayList<Integer> B) {
        HashMap<Integer, Integer> originalOrderMap = new HashMap<Integer, Integer>();
        for(int i=0; i<A.size(); i++)
        {
            originalOrderMap.put(A.get(i), i);
        }

        HashMap<Integer, Integer> relativeOrderMap = new HashMap<Integer, Integer>();
        for(int i=0; i<B.size(); i++)
        {
            relativeOrderMap.put(B.get(i), i);
        }

        A.sort(new Comparator<Integer>() {

            @Override
            public int compare(Integer i1, Integer i2) {
                Integer pos1 = relativeOrderMap.get(i1);
                Integer pos2 = relativeOrderMap.get(i2);

                if(pos1 == null && pos2 == null)
                {
                    pos1 = i1;
                    pos2 = i2;
                }

                if(pos1 == null)
                {
                    return 1;
                }

                if(pos2 == null)
                {
                    return -1;
                }                

                if(pos1 < pos2)
                {
                    return -1;
                }

                if(pos1 > pos2)
                {
                    return 1;
                }
                
                return 0;
            }
            
        });

        return A;
    }

    /* Count Rectangles
    Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in a 2-D Cartesian plane.

    Find and return the number of unordered quadruplet (i, j, k, l) such that (A[i], B[i]), (A[j], B[j]), (A[k], B[k]) and (A[l], B[l]) form a rectangle with the rectangle having all the sides parallel to either x-axis or y-axis.


    Problem Constraints
    1 <= N <= 2000
    0 <= A[i], B[i] <= 109

    Input Format
    The first argument given is the integer array A.
    The second argument given is the integer array B.

    Output Format
    Return the number of unordered quadruplets that form a rectangle.

    Example Input
    Input 1:

    A = [1, 1, 2, 2]
    B = [1, 2, 1, 2]
    Input 1:

    A = [1, 1, 2, 2, 3, 3]
    B = [1, 2, 1, 2, 1, 2]


    Example Output
    Output 1:

    1
    Output 2:

    3

    Example Explanation
    Explanation 1:

    All four given points make a rectangle. So, the answer is 1.
    Explanation 2:

    3 quadruplets which make a rectangle are: (1, 1), (2, 1), (2, 2), (1, 2)
                                            (1, 1), (3, 1), (3, 2), (1, 2)
                                            (2, 1), (3, 1), (3, 2), (2, 2)

    */
    public int CountRectangles(int[] A, int[] B) {
        class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + x;
                result = prime * result + y;
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null)
                    return false;

                return (this.x == ((Pair) obj).x) && (this.y == ((Pair) obj).y);
            }
        }

        int c = 0;

        HashSet<Pair> set = new HashSet<Pair>();

        for (int i = 0; i < A.length && i < B.length; i++) {
            set.add(new Pair(A[i], B[i]));
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < B.length; j++) {
                int x1 = A[i], y1 = B[i], x2 = A[j], y2 = B[j];

                if (x1 != x2 && y1 != y2) {
                    Pair p1 = new Pair(x1, y2);
                    Pair p2 = new Pair(x2, y1);

                    if (set.contains(p1) && set.contains(p2)) {
                        c++;
                    }
                }
            }
        }

        return c / 2;
    }

    /* Count Right Triangles
    Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.

    Find and return the number of unordered triplets (i, j, k) such that (A[i], B[i]), (A[j], B[j]) and (A[k], B[k]) form a right-angled triangle with the triangle having one side parallel to the x-axis and one side parallel to the y-axis.

    NOTE: The answer may be large so return the answer modulo (109 + 7).

    Problem Constraints
    1 <= N <= 105

    0 <= A[i], B[i] <= 109

    Input Format
    The first argument given is an integer array A.
    The second argument given is the integer array B.

    Output Format
    Return the number of unordered triplets that form a right angled triangle modulo (109 + 7).

    Example Input
    Input 1:

    A = [1, 1, 2]
    B = [1, 2, 1]
    Input 2:

    A = [1, 1, 2, 3, 3]
    B = [1, 2, 1, 2, 1]


    Example Output
    Output 1:

    1
    Output 2:

    6


    Example Explanation
    Explanation 1:

    All three points make a right angled triangle. So return 1.
    Explanation 2:

    6 triplets which make a right angled triangle are:    (1, 1), (1, 2), (2, 2)
                                                        (1, 1), (3, 1), (1, 2)
                                                        (1, 1), (3, 1), (3, 2)
                                                        (2, 1), (3, 1), (3, 2)
                                                        (1, 1), (1, 2), (3, 2)
                                                        (1, 2), (3, 1), (3, 2)
    */    
    public int CountRightAngleTriangles_BruteForce(int[] A, int[] B) {
        int c = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < A.length; k++) {
                    if (i == k || j == k)
                        continue;

                    if (A[i] == A[k] && B[i] == B[j]) {
                        c++;
                    }
                }
            }
        }

        return c;
    }

    public int CountRightAngleTriangles_Better(int[] A, int[] B) {
        class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + x;
                result = prime * result + y;
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null)
                    return false;

                return (this.x == ((Pair) obj).x) && (this.y == ((Pair) obj).y);
            }
        }

        HashSet<Pair> set = new HashSet<Pair>();

        for (int i = 0; i < A.length && i < B.length; i++) {
            set.add(new Pair(A[i], B[i]));
        }

        int c = 0, mod = 1000 * 1000 * 1000 + 7;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < B.length; j++) {
                int x1 = A[i], y1 = B[i];
                int x2 = A[j], y2 = B[j];
                if (x1 == x2 || y1 == y2)
                    continue;

                if (set.contains(new Pair(x1, y2))) {
                    c = (c + 1) % mod;
                }
                if (set.contains(new Pair(x2, y1))) {
                    c = (c + 1) % mod;
                }
            }
        }

        return c;
    }

    public int CountRightAngleTriangles_Optimized(int[] A, int[] B) {
        
        HashMap<Integer, Integer> mapA = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> mapB = new HashMap<Integer, Integer>();

        for (int i = 0; i < A.length; i++) {
            mapA.put(A[i], mapA.getOrDefault(A[i], 0) + 1);
            mapB.put(B[i], mapB.getOrDefault(B[i], 0) + 1);
        }

        int c = 0, mod = 1000 * 1000 * 1000 + 7;
        for (int i = 0; i < A.length; i++) {
            int x = A[i], y = B[i];

            int n = mapA.get(x) - 1, m = mapB.get(y) - 1;

            c = (c + (n % mod * m % mod) % mod) % mod;
        }

        return c;
    }

    /* Replicating Substring
    Given a string B, find if it is possible to re-order the characters of the string B so that it can be represented as a concatenation of A similar strings.

    Eg: B = aabb and A = 2, then it is possible to re-arrange the string as "abab" which is a concatenation of 2 similar strings "ab".

    If it is possible, return 1, else return -1.



    Problem Constraints

    1 <= Length of string B <= 1000

    1 <= A <= 1000

    All the alphabets of S are lower case (a - z)



    Input Format

    First argument is an integer A.
    Second argument is a string B.



    Output Format

    Your function should return 1 if it is possible to re-arrange the characters of the string B so that it can be represented as a concatenation of A similar strings. If it is not, return -1.



    Example Input

    Input 1:

    A = 2
    B = "bbaabb"
    Input 2:

    A = 1
    B = "bc"


    Example Output

    Output 1:

    1
    Output 2:

    1


    Example Explanation

    Explanation 1:

    We can re-order the given string into "abbabb".
    Explanation 2:

    String "bc" is already arranged.
    */
    public int ReplicatingSubstring(int A, String B) {

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < B.length(); i++) {
            map.put(B.charAt(i), map.getOrDefault(B.charAt(i), 0) + 1);
        }
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if (e.getValue() % A != 0)
                return -1;
        }

        return 1;
    }

    /* Points on same line

    */
    int gcd(int a, int b) {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // base case
        if (a == b)
            return a;

        // a is greater
        if (a > b)
            return gcd(a - b, b);
        return gcd(a, b - a);
    }

    public int PointsOnSameLine(int[] A, int[] B) {

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            int overlap = 0, vertical = 0, currMax = 0;

            for (int j = i + 1; j < B.length; j++) {
                int x1 = A[i], y1 = B[i], x2 = A[j], y2 = B[j];

                if (y2 == y1 && x2 == x1) {
                    overlap++;
                } else if (x1 == x2) {
                    vertical++;
                } else {
                    int dy = y2 - y1, dx = x2 - x1;
                    String sign = "";
                    if ((dy < 0 && dx < 0) || (dy > 0 && dx > 0)) {
                        sign = "";
                    } else {
                        sign = "-";
                    }
                    int GCD = gcd(Math.abs(dy), Math.abs(dx));

                    dy = dy / GCD;
                    dx = dx / GCD;

                    String slopeKey = sign + String.valueOf(Math.abs(dy)) + "/" + String.valueOf(Math.abs(dx));

                    map.put(slopeKey, map.getOrDefault(slopeKey, 0) + 1);

                    currMax = Math.max(currMax, map.get(slopeKey));
                }

                currMax = Math.max(currMax, vertical);
            }

            ans = Math.max(ans, currMax + overlap);
        }

        return ans + 1;
    }

    /* Compare Sorted Subarrays
    Given an array A of length N. You have to answer Q queries.

    Each query will contain four integers l1, r1, l2, and r2. If sorted segment from [l1, r1] is the same as the sorted segment from [l2 r2], then the answer is 1 else 0.

    NOTE The queries are 0-indexed.



    Problem Constraints
    0 <= A[i] <= 100000
    1 <= N <= 100000
    1 <= Q <= 100000



    Input Format
    The first argument is an array A.
    The second is a 2D array B denoting queries with dimension Q * 4.
    Consider ith query as l1 = B[i][0], r1 = B[i][1], l2 = A[i][2], r2 = B[i][3].



    Output Format
    Return an array of length Q with answers to the queries in the same order as the input.



    Example Input
    Input 1:

    A = [1, 7, 11, 8, 11, 7, 1]
    B = [ 
        [0, 2, 4, 6]
        ]
    Input 2:

    A = [1, 3, 2]
    B = [
        [0, 1, 1, 2]
        ] 


    Example Output
    Output 1:

    [1]
    Output 2:

    [0]


    Example Explanation
    Explanation 1:

    (0, 2) -> [1, 7, 11]
    (4, 6) -> [11, 7, 1]
    Both are same when sorted hence 1.
    Explanation 2:

    (0, 1) -> [1, 3]
    (1, 2) -> [3, 2] 
    Both are different when sorted hence 0.

    */
    public int[] CompareSortedSubarrays(int[] A, int[][] B) {

        HashMap<Integer, Long> map = new HashMap<Integer, Long>();
        long min = 0;
        long max = 1L * 1000000 * 1000000 * 10;

        long[] pf = new long[A.length];

        for (int i = 0; i < A.length; i++) {
            if (!map.containsKey(A[i])) {
                long hash = min + (long) (1L * Math.random() * (max - min));
                map.put(A[i], hash);
            }

            pf[i] = i == 0 ? map.get(A[i]) : pf[i - 1] + map.get(A[i]);
        }

        int[] res = new int[B.length];

        for (int i = 0; i < B.length; i++) {
            int l1 = B[i][0];
            int r1 = B[i][1];
            int l2 = B[i][2];
            int r2 = B[i][3];

            if ((pf[r1] - (l1 == 0 ? 0 : pf[l1 - 1])) == (pf[r2] - (l2 == 0 ? 0 : pf[l2 - 1]))) {
                res[i] = 1;
            }
        }

        return res;
    }
}
