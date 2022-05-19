package Backtrackking;

import java.util.ArrayList;

public class BackTrackkingProblems {

    /*
     * Print all N digit numbers, starting with [1, 2]
     */
    public void PrintNDigitNumbers(int N) {
        int[] currList = new int[N];

        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();

        PrintNDigitNumbersUtil(N, 0, currList, output);
    }

    private void PrintNDigitNumbersUtil(int N, int index, int[] currList, ArrayList<ArrayList<Integer>> output) {
        if (index == N) {
            ArrayList<Integer> al = new ArrayList<>();

            for (int i = 0; i < currList.length; i++) {
                output.add(al);
            }

            return;
        }

        currList[index] = 1;
        PrintNDigitNumbersUtil(N, index + 1, currList, output);

        currList[index] = 2;
        PrintNDigitNumbersUtil(N, index + 1, currList, output);
    }

    /* Given a set of integers, find all its subsets. 
    */
    public void GenerateAllSubsets(int[] A)
    {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();

        GenerateAllSubsetsUtil(A, 0, new ArrayList<Integer>(), ans);
    }

    private void GenerateAllSubsetsUtil(int[] A, int index, ArrayList<Integer> output, ArrayList<ArrayList<Integer>> ans){

        if(index == A.length)
        {
            ans.add(output);
            return;
        }

         // Not Including Value which is at Index
        GenerateAllSubsetsUtil(A, index + 1, new ArrayList<Integer>(output), ans);

         // Including Value which is at Index
        output.add(A[index]);
        GenerateAllSubsetsUtil(A, index + 1, new ArrayList<Integer>(output), ans);
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
