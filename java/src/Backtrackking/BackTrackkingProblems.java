package Backtrackking;

import java.util.*;

public class BackTrackkingProblems {

    /* Generate all N digit numbers, using digits only 1 & 2
     */
    public void GenerateNDigitNumbersUsing1And2(int N) {
        int[] currList = new int[N];

        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();

        GenerateNDigitNumbersUsing1And2Util(N, 0, currList, output);
    }

    private void GenerateNDigitNumbersUsing1And2Util(int N, int index, int[] currList, ArrayList<ArrayList<Integer>> output) {
        if (index == N) {
            ArrayList<Integer> al = new ArrayList<>();

            for (int i = 0; i < currList.length; i++) {
                output.add(al);
            }

            return;
        }

        currList[index] = 1;
        GenerateNDigitNumbersUsing1And2Util(N, index + 1, currList, output);

        currList[index] = 2;
        GenerateNDigitNumbersUsing1And2Util(N, index + 1, currList, output);
    }

    /* SubSet
    Problem Description
Given a set of distinct integers A, return all possible subsets.

NOTE:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Also, the subsets should be sorted in ascending ( lexicographic ) order.
The list is not necessarily sorted.


Problem Constraints
1 <= |A| <= 16
INTMIN <= A[i] <= INTMAX


Input Format
First and only argument of input contains a single integer array A.



Output Format
Return a vector of vectors denoting the answer.



Example Input
Input 1:

A = [1]
Input 2:

A = [1, 2, 3]


Example Output
Output 1:

[
    []
    [1]
]
Output 2:

[
 []
 [1]
 [1, 2]
 [1, 2, 3]
 [1, 3]
 [2]
 [2, 3]
 [3]
]


Example Explanation
Explanation 1:

 You can see that these are all possible subsets.
Explanation 2:

You can see that these are all possible subsets.
    */
    void findSubSet(ArrayList<Integer> A, int index, ArrayList<Integer> output, ArrayList<ArrayList<Integer>> answer)
    {
        if(index == A.size())
        {
            answer.add(output);
            return;
        }

        findSubSet(A, index+1, new ArrayList<Integer>(output), answer);

        output.add(A.get(index));

        findSubSet(A, index+1, new ArrayList<Integer>(output), answer);
    }

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {

        Collections.sort(A);
        ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();

        findSubSet(A, 0, new ArrayList<Integer>(), answer);

        Collections.sort(answer, new Comparator<ArrayList<Integer>>() {

            @Override
            public int compare(ArrayList<Integer> al1, ArrayList<Integer> al2)
            {
                if(al1.size() == 0) return -1;

                if(al2.size() == 0) return 1;
                
                int i=0;

                while(i<al1.size() && i<al2.size())
                {
                    if(al1.get(i) < al2.get(i))
                    {
                        return -1;
                    }

                    if(al1.get(i) > al2.get(i))
                    {
                        return 1;
                    }

                    i++;
                }

                if(al1.size() < al2.size()) return -1;

                if(al1.size() > al2.size()) return 1;

                return 0;
            }

        });

        return answer;
    }

    /*
     * Given a set of integers, count sub-set with given sum.
     */
    public int countSubSetWithGivenSum(int[] A, int sum) {
        return countSubSetWithGivenSumUtil(A, 0, sum);
    }

    private int countSubSetWithGivenSumUtil(int[] A, int index, int sum) {
        if (index == A.length) {
            return sum == 0 ? 1 : 0;
        }

        return countSubSetWithGivenSumUtil(A, index + 1, sum)
                + countSubSetWithGivenSumUtil(A, index + 1, sum - A[index]);
    }

    /* SIXLETS
    Problem Description
Given a array of integers A of size N and an integer B.

Return number of non-empty subsequences of A of size B having sum <= 1000.



Problem Constraints
1 <= N <= 20

1 <= A[i] <= 1000

1 <= B <= N



Input Format
The first argument given is the integer array A.

The second argument given is the integer B.



Output Format
Return number of subsequences of A of size B having sum <= 1000.



Example Input
Input 1:

    A = [1, 2, 8]
    B = 2
Input 2:

    A = [5, 17, 1000, 11]
    B = 4


Example Output
Output 1:

3
Output 2:

0


Example Explanation
Explanation 1:

 {1, 2}, {1, 8}, {2, 8}
Explanation 1:

 No valid subsequence
    */
    int countSubsequenceOfGivenSizeWithSumLessThan1000Util(ArrayList<Integer> A, int index, int count, int sum) {
        if (sum > 1000)
            return 0;

        if (count == 0)
            return 1;

        if (index == A.size())
            return 0;

        return countSubsequenceOfGivenSizeWithSumLessThan1000Util(A, index + 1, count, sum)
                + countSubsequenceOfGivenSizeWithSumLessThan1000Util(A, index + 1, count - 1, sum + A.get(index));

    }

    public int countSubsequenceOfGivenSizeWithSumLessThan1000(ArrayList<Integer> A, int B) {
        return countSubsequenceOfGivenSizeWithSumLessThan1000Util(A, 0, B, 0);
    }

    /* Rat In a Maze
    Problem Description

Given a grid A, a rat is at position (1, 1). He wants to go to the position (n, n) where n is size of the square matrix A.

1 represents a traversable cell and 0 represents a non-traversable cell. Rat can only move right or down.

Return a path that the rat can take.



Problem Constraints

1 <= |A| <= 4



Input Format

First and only argument is the vector of vectors A.



Output Format

Return a vector of vectors that denotes a path that can be taken.



Example Input

Input 1:

A = [   [1, 0]
        [1, 1]
    ]
Input 2:

A = [    [1, 1, 1]
         [1, 0, 1]
         [0, 0, 1]
    ]


Example Output

Output 1:

[   [1, 0]
    [1, 1]
]
Output 2:

[    [1, 1, 1]
     [0, 0, 1]
     [0, 0, 1]
]


Example Explanation

Explanation 1:

 Path is shown in output.
Explanation 2:

 Path is shown in output.
    */
    public class RatInMazeSolution {
        boolean isSafe(int[][] A, int x, int y) {
            return x >= 0 && x < A.length && y >= 0 && y < A[0].length && A[x][y] == 1;
        }

        public boolean maze(int[][] A, int x, int y, int[][] sol) {
            if (x == A.length - 1 && y == A[0].length - 1 && A[x][y] == 1) {
                sol[x][y] = 1;
                return true;
            }

            if (isSafe(A, x, y)) {
                sol[x][y] = 1;

                if (maze(A, x, y + 1, sol) || maze(A, x + 1, y, sol)) {
                    return true;
                }

                sol[x][y] = 0;
            }

            return false;
        }

        public int[][] solve(int[][] A) {

            int N = A.length, M = A[0].length;

            int[][] sol = new int[N][M];

            maze(A, 0, 0, sol);
            
            return sol;
        }
    }
}
