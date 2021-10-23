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

    // Q. You are given two strings A and B of size N and M respectively.
    // You have to find the count of all permutations of A present in B as a
    // substring. You can assume a string will have only lowercase letters.
    // A = "abc" B = "abcbacabc" . O/P: 5
    private boolean compare(int[] A, int[] B) {
        for (int i = 0; i < 26; i++) {
            if (A[i] != B[i])
                return false;
        }
        return true;
    }

    public int CountOfAllAnagramsA_In_B(String A, String B) {
        int n = A.length();
        int m = B.length();
        if (m < n)
            return 0;
        int[] countA = new int[26];
        for (int i = 0; i < n; i++) {
            countA[A.charAt(i) - 'a']++;
        }
        int[] countB = new int[26];
        for (int i = 0; i < n; i++) {
            countB[B.charAt(i) - 'a']++;
        }
        int ans = 0;
        if (compare(countA, countB)) {
            ans++;
        }
        for (int i = n; i < m; i++) {
            countB[B.charAt(i) - 'a']++;
            countB[B.charAt(i - n) - 'a']--;
            if (compare(countA, countB)) {
                ans++;
            }
        }
        return ans;
    }

    // Find Pairs with difference k
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
                // a-b=k => for given a, search b = a-k in map
                if (map.containsKey(A[i] - k)) {
                    count += map.get(A[i] - k);
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
    public int CheckIfThereExistSubArrayWithZeroSum(int[] A) {

        HashSet<Integer> s = new HashSet<Integer>();

        int prefixSum = 0;

        for (int i = 0; i < A.length; i++) {
            prefixSum = prefixSum + A[i];

            if (prefixSum == 0)
                return 1;

            if (s.contains(prefixSum)) {
                return 1;
            }

            s.add(prefixSum);
        }

        return 0;
    }

    // Get Subarray with given sum
    // Given an array of positive integers A and an integer B, find and return first
    // continuous subarray which adds to B.

    // If the answer does not exist return an array with a single element "-1".

    // First sub-array means the sub-array for which starting index in minimum.
    // Return the first continuous sub-array which adds to B and if the answer does
    // not exist return an array with a single element "-1".
    public ArrayList<Integer> SubArrayWithGivenSum(ArrayList<Integer> A, int B) {
        Long prefixSum = 0L;
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();

        for (int i = 0; i < A.size(); i++) {
            prefixSum = prefixSum + A.get(i);

            if (prefixSum == B) {

                ArrayList<Integer> res = new ArrayList<Integer>();
                for (int j = 0; j <= i; j++) {
                    res.add(A.get(j));
                }
                return res;
            }

            if (map.containsKey(prefixSum - B)) {

                ArrayList<Integer> res = new ArrayList<Integer>();
                for (int j = map.get(prefixSum - B) + 1; j <= i; j++) {
                    res.add(A.get(j));
                }
                return res;
            }

            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }

        ArrayList<Integer> res = new ArrayList<Integer>();
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

    public void CountDistinctElementsInWindowOfSizeK(int[] A, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < k; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        System.out.print(map.size() + " ");

        for (int i = k; i < A.length; i++) {
            map.put(A[i - k], map.get(A[i - k]) - 1);

            if (map.get(A[i - k]) <= 0) {
                map.remove(A[i - k]);
            }

            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            System.out.print(map.size() + " ");
        }
    }

    // Longest Consecutive Subsequence
    // Given an array, we need to find the longest subsequence that has consecutive
    // elements. These consecutive elements may appear in any order in the
    // subsequence.
    public int LongestConsecutiveSubsequence(int[] A) {

        HashSet<Integer> s = new HashSet<Integer>();

        for (int i = 0; i < A.length; i++) {
            s.add(A[i]);
        }

        int res = 0;

        for (int i = 0; i < A.length; i++) {
            if (!s.contains(A[i] - 1)) {
                int count = 1;
                while (s.contains(A[i] + count)) {
                    count++;
                }
                res = Math.max(res, count);
            }
        }

        return res;
    }

    /* Perfect Cards
    Problem Description

    Tom and Harry are given N numbers, with which they play a game as a team.

    Initially, Tom chooses a particular number P from the N numbers, and he takes away all the numbers that are equal to P.

    Next, Harry chooses a different number Q, different from what Tom chose, and takes away all the numbers equal to Q from the remaining N numbers.

    They win the game if they can take all the numbers by doing the above operation once and if each of them has the same amount of numbers towards the end.

    If they win, return the string "WIN", else return "LOSE".
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

    /* Is Dictionary?
    Problem Description

    In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

    Given an array of words A of size N written in the alien language, and the order of the alphabet denoted by string B of size 26, return 1 if and only if the given words are sorted lexicographicaly in this alien language else return 0.
    */
    public boolean isLexographical(String A, String B, HashMap<Character,Integer> map)
    {
        int i=0,j=0;
        
        while(i<A.length() && j<B.length())
        {
            if(map.get(A.charAt(i)) < map.get(B.charAt(j))) return true;
            
            if(map.get(A.charAt(i)) > map.get(B.charAt(j))) return false;
            
            i++;
            j++;
        }
        
        if(i==A.length() && j==B.length()) return true;
        
        if(i==A.length()) return true;
        
        return false;
    }
    
    public int solve(String[] A, String B) {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        
        for(int i=0; i<B.length(); i++){
            map.put(B.charAt(i), i);
        }
        
        int i=0,j=1;
        while(i<A.length-1 && j<A.length){
            if(!isLexographical(A[i], A[j], map)) return 0;
            
            i++;
            j++;
        }
        
        return 1;
    }

    /* Colorful Number
    Problem Description

    For Given Number A find if its COLORFUL number or not.

    If number A is a COLORFUL number return 1 else return 0.

    What is a COLORFUL Number:

    A number can be broken into different contiguous sub-subsequence parts. 
    Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245. 
    And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different.
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

}
