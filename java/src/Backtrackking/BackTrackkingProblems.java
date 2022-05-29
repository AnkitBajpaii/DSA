package Backtrackking;

import java.util.*;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

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

    /* Permutations
    Problem Description
Given an integer array A of size N denoting collection of numbers , return all possible permutations.

NOTE:

No two entries in the permutation sequence should be the same.
For the purpose of this problem, assume that all the numbers in the collection are unique.
Return the answer in any order
WARNING: DO NOT USE LIBRARY FUNCTION FOR GENERATING PERMUTATIONS. Example : next_permutations in C++ / itertools.permutations in python.
If you do, we will disqualify your submission retroactively and give you penalty points.


Problem Constraints
1 <= N <= 9



Input Format
Only argument is an integer array A of size N.



Output Format
Return a 2-D array denoting all possible permutation of the array.



Example Input
A = [1, 2, 3]


Example Output
[ [1, 2, 3]
  [1, 3, 2]
  [2, 1, 3] 
  [2, 3, 1] 
  [3, 1, 2] 
  [3, 2, 1] ]


Example Explanation
All the possible permutation of array [1, 2, 3].
    */
    void swap(ArrayList<Integer> A, int i, int j) {
        int t = A.get(i);
        A.set(i, A.get(j));
        A.set(j, t);
    }

    void generatePermutation(ArrayList<Integer> A, int index, ArrayList<ArrayList<Integer>> answer) {
        if (index == A.size()) {
            answer.add(new ArrayList<Integer>(A));
            return;
        }

        for (int i = index; i < A.size(); i++) {
            swap(A, i, index);

            generatePermutation(A, index + 1, answer);

            swap(A, i, index);
        }

    }

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();

        generatePermutation(A, 0, ans);

        return ans;
    }

    /* Combination Sum
    Problem Description
Given an array of candidate numbers A and a target number B, find all unique combinations in A where the candidate numbers sums to B.

The same repeated number may be chosen from A unlimited number of times.

Note:

1) All numbers (including target) will be positive integers.

2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).

3) The combinations themselves must be sorted in ascending order.

4) CombinationA > CombinationB iff (a1 > b1) OR (a1 = b1 AND a2 > b2) OR ... (a1 = b1 AND a2 = b2 AND ... ai = bi AND ai+1 > bi+1)

5) The solution set must not contain duplicate combinations.



Problem Constraints
1 <= |A| <= 20

1 <= A[i] <= 50

1 <= B <= 500



Input Format
The first argument is an integer array A.

The second argument is integer B.



Output Format
Return a vector of all combinations that sum up to B.



Example Input
Input 1:

A = [2, 3]
B = 2
Input 2:

A = [2, 3, 6, 7]
B = 7


Example Output
Output 1:

[ [2] ]
Output 2:

[ [2, 2, 3] , [7] ]


Example Explanation
Explanation 1:

All possible combinations are listed.
Explanation 2:

All possible combinations are listed.
    */
    void combinationSumUtil(ArrayList<Integer> A, int index, int sum, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> output) {
        if (sum == 0) {
            ans.add(new ArrayList<Integer>(output));
            return;
        }

        for (int i = index; i < A.size(); i++) {
            if (sum - A.get(i) >= 0) {
                output.add(A.get(i));
                combinationSumUtil(A, i, sum - A.get(i), ans, output);
                output.remove(A.get(i));
            }
        }
    }

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        HashSet<Integer> set = new HashSet<Integer>(A);
        A.clear();
        A.addAll(set);

        Collections.sort(A);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();

        combinationSumUtil(A, 0, B, ans, new ArrayList<Integer>());

        return ans;
    }

    /* Combination Sum II
    Problem Description

Given an array of size N of candidate numbers A and a target number B. Return all unique combinations in A where the candidate numbers sums to B.

Each number in A may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
Warning:

DO NOT USE LIBRARY FUNCTION FOR GENERATING COMBINATIONS.

Example : itertools.combinations in python. If you do, we will disqualify your submission and give you penalty points.



Problem Constraints

1 <= N <= 20



Input Format

First argument is an integer array A denoting the collection of candidate numbers.
Second argument is an integer which represents the target number.



Output Format

Return all unique combinations in A where the candidate numbers sums to B.



Example Input

Input 1:

 A = [10, 1, 2, 7, 6, 1, 5]
 B = 8
Input 2:

 A = [2, 1, 3]
 B = 3


Example Output

Output 1:

 [ 
  [1, 1, 6 ],
  [1, 2, 5 ],
  [1, 7 ], 
  [2, 6 ] 
 ]
Output 2:

 [
  [1, 2 ],
  [3 ]
 ]


Example Explanation

Explanation 1:

 1 + 1 + 6 = 8
 1 + 2 + 5 = 8
 1 + 7 = 8
 2 + 6 = 8
 All the above combinations sum to 8 and are arranged in ascending order.
Explanation 2:

 1 + 2 = 3
 3 = 3
 All the above combinations sum to 3 and are arranged in ascending order.
    */
    void combinationSumUtilII(ArrayList<Integer> A, int index, int sum, ArrayList<Integer> output,
            ArrayList<ArrayList<Integer>> ans) {
        if (sum == 0) {
            ans.add(new ArrayList<Integer>(output));
            return;
        }

        if (index == A.size()) {
            return;
        }

        for (int i = index; i < A.size(); i++) {
            if (sum - A.get(i) < 0)
                continue;

            if (i > index && A.get(i) == A.get(i - 1))
                continue;

            output.add(A.get(i));
            combinationSumUtilII(A, i + 1, sum - A.get(i), output, ans);
            output.remove(A.get(i));
        }
    }

    public ArrayList<ArrayList<Integer>> combinationSumII(ArrayList<Integer> A, int B) {

        Collections.sort(A);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();

        combinationSumUtilII(A, 0, B, new ArrayList<Integer>(), ans);

        return ans;
    }

    /* Letter Phone
    Problem Description
Given a digit string A, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



The digit 0 maps to 0 itself. The digit 1 maps to 1 itself.

NOTE: Make sure the returned strings are lexicographically sorted.



Problem Constraints
1 <= |A| <= 10



Input Format
The only argument is a digit string A.



Output Format
Return a string array denoting the possible letter combinations.



Example Input
Input 1:

 A = "23"
Input 2:

 A = "012"


Example Output
Output 1:

 ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
Output 2:

 ["01a", "01b", "01c"]


Example Explanation
Explanation 1:

 There are 9 possible letter combinations.
Explanation 2:

 Only 3 possible letter combinations.
    */
    private void letterCombinationsUtil(String digits, int index, HashMap<Integer, String> map, String curr,
            ArrayList<String> answer) {
        if (index == digits.length()) {
            answer.add(curr);
            return;
        }

        int digit = digits.charAt(index) - '0';

        for (int i = 0; i < map.get(digit).length(); i++) {
            String prev = curr;
            curr += map.get(digit).charAt(i);

            letterCombinationsUtil(digits, index + 1, map, curr, answer);

            curr = prev;
        }

    }

    public ArrayList<String> letterCombinations(String A) {
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        map.put(0, "0");
        map.put(1, "1");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        ArrayList<String> answer = new ArrayList<String>();

        letterCombinationsUtil(A, 0, map, "", answer);

        return answer;
    }

    /* Subsets II
    Problem Description
Given a collection of integers denoted by array A of size N that might contain duplicates, return all possible subsets.

NOTE:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
The subsets must be sorted lexicographically.


Problem Constraints
0 <= N <= 16



Input Format
Only argument is an integer array A of size N.



Output Format
Return a 2-D vector denoting all the possible subsets.



Example Input
Input 1:

 A = [1, 2, 2]
Input 2:

 A = [1, 1]


Example Output
Output 1:

 [
    [],
    [1],
    [1, 2],
    [1, 2, 2],
    [2],
    [2, 2]
 ]
Output 2:

 [
    [],
    [1],
    [1, 1]
 ]


Example Explanation
Explanation 1:

All the subsets of the array [1, 2, 2] in lexicographically sorted order.
    */
    void findSubSetII(ArrayList<Integer> A, int index, ArrayList<Integer> output, ArrayList<ArrayList<Integer>> answer)
    {
        if(index >= A.size())
        {
            answer.add(new ArrayList<Integer>(output));
            return;
        }

        int currIndex = index + 1;
        while(currIndex < A.size() && A.get(currIndex) == A.get(index))
        {
            currIndex++;
        }

        int k = currIndex - index;

        for(int i=0; i<=k; i++)
        {
            for(int j=0; j<i; j++)
            {
                output.add(A.get(index));
            }

            findSubSetII(A, currIndex, output, answer);

            for(int j=0; j<i; j++)
            {
                output.remove(output.size() - 1);
            }
        }
        
    }

    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> A) {

        Collections.sort(A);

        ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>();

        findSubSetII(A, 0, new ArrayList<Integer>(), answer);

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

        public int[][] RatInMaze(int[][] A) {

            int N = A.length, M = A[0].length;

            int[][] sol = new int[N][M];

            maze(A, 0, 0, sol);
            
            return sol;
        }
    }

    /* Given a matrix & start point of rat, end point, blocked cells, cells filled with cheese.
    count no of path from start to end such that rat can eat all the cheese available in the maze without stepping on same cell
    twice in a single path.
    start: si, sj
    end: ei, ej
    cheese: 0
    blocked: 1
    empty: 2
    */
    int countPathsUtil(int[][] mat, int si, int sj, int ei, int ej, int currCheeseCount, int totalCheeseCount) {
        if (si == ei && sj == ej) {
            if (currCheeseCount == totalCheeseCount)
                return 1;

            return 0;
        }

        // is safe check
        if (si < 0 || si >= mat.length || ej < 0 || ej >= mat[0].length || mat[si][sj] == 1)
            return 0;

        int prevState = mat[si][sj];

        int cheeseCount = currCheeseCount;
        
        if (prevState == 0) { // 0 means cell has cheese
            cheeseCount++;
        }

        mat[si][sj] = 1;// 1 means blocked.

        int ans = countPathsUtil(mat, si, sj - 1, ei, ej, cheeseCount, totalCheeseCount)
                + countPathsUtil(mat, si, sj + 1, ei, ej, cheeseCount, totalCheeseCount)
                + countPathsUtil(mat, si - 1, sj, ei, ej, cheeseCount, totalCheeseCount)
                + countPathsUtil(mat, si + 1, sj, ei, ej, cheeseCount, totalCheeseCount);

        mat[si][sj] = prevState;

        return ans;
    }

    public int countPaths(int[][] mat, int si, int sj, int ei, int ej) {
        int totalCheeseCount = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    totalCheeseCount++;
                }
            }
        }

        return countPathsUtil(mat, si, sj, ei, ej, 0, totalCheeseCount);
    }

    /* N Queens
    The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
    Check if you can place N queens(wazir) in this board, following above constraint. 
    */
    boolean isCellSafeForQueen(int[][] mat, int i, int j) {
        if (i < 0 || i >= mat.length || j < 0 || j >= mat.length) {
            return false;
        }

        for (int col = 0; col < mat.length; col++) {
            if (mat[i][col] == 1) {
                return false;
            }
        }

        for (int row = 0; row < mat.length; row++) {
            if (mat[row][j] == 1) {
                return false;
            }
        }

        int r = i - 1, c = j - 1;
        while (r >= 0 && c >= 0) {
            if (mat[r][c] == 1)
                return false;
            r--;
            j--;
        }

        r = i + 1;
        c = j + 1;
        while (r < mat.length && c < mat.length) {
            if (mat[r][c] == 1)
                return false;
            r++;
            j++;
        }

        r = i - 1;
        c = j + 1;
        while (r >= 0 && c < mat.length) {
            if (mat[r][c] == 1)
                return false;
            r--;
            j++;
        }

        r = i + 1;
        c = j - 1;
        while (r < mat.length && c >= 0) {
            if (mat[r][c] == 1)
                return false;
            r++;
            j--;
        }

        return true;
    }

    boolean solveNQueensUtil(int[][] mat, int row) {
        if (row == mat.length)
            return true;

        for (int col = 0; col < mat.length; col++) {
            if (isCellSafeForQueen(mat, row, col)) {
                mat[row][col] = 1;

                if (solveNQueensUtil(mat, row + 1)) {
                    return true;
                }
                mat[row][col] = 0;
            }
        }

        return false;
    }

    public boolean solveNQueens(int[][] mat) {
        return solveNQueensUtil(mat, 0);
    }

    /* NQueens
    The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer A, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
The final list should be generated in such a way that the indices of the queens in each list should be in reverse lexicographical order.


Problem Constraints
1 <= A <= 10



Input Format
First argument is an integer n denoting the size of chessboard



Output Format
Return an array consisting of all distinct solutions in which each element is a 2d char array representing a unique solution.



Example Input
Input 1:

A = 4
Input 2:

A = 1


Example Output
Output 1:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Output 1:

[
 [Q]
]


Example Explanation
Explanation 1:

There exist only two distinct solutions to the 4-queens puzzle:
Explanation 1:

There exist only one distinct solutions to the 1-queens puzzle:

    */
    boolean isSafe(char[][] mat, int i, int j, HashSet<Integer> rowHash, HashSet<Integer> colHash,
            HashSet<Integer> leftDiagonal, HashSet<Integer> rightDiagonal) {
        if (i < 0 || i >= mat.length || j < 0 || j >= mat.length
                || rowHash.contains(i) || colHash.contains(j)) {
            return false;
        }

        if (leftDiagonal.contains(j - i)) {
            return false;
        }

        if (rightDiagonal.contains(i + j)) {
            return false;
        }

        return true;
    }

    void solveNQueensUtil(char[][] mat, int row, ArrayList<ArrayList<String>> ans, HashSet<Integer> rowHash,
            HashSet<Integer> colHash, HashSet<Integer> leftDiagonal, HashSet<Integer> rightDiagonal) {
        if (row == mat.length) {
            ArrayList<String> al = new ArrayList<String>();

            for (int i = 0; i < mat.length; i++) {
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < mat[i].length; j++) {
                    sb.append(mat[i][j]);
                }

                al.add(sb.toString());
            }

            ans.add(al);

            return;
        }

        for (int col = 0; col < mat.length; col++) {
            if (isSafe(mat, row, col, rowHash, colHash, leftDiagonal, rightDiagonal)) {
                mat[row][col] = 'Q';

                rowHash.add(row);
                colHash.add(col);
                leftDiagonal.add(col - row);
                rightDiagonal.add(col + row);

                solveNQueensUtil(mat, row + 1, ans, rowHash, colHash, leftDiagonal, rightDiagonal);

                mat[row][col] = '.';
                rowHash.remove(row);
                colHash.remove(col);
                leftDiagonal.remove(col - row);
                rightDiagonal.remove(col + row);
            }
        }
    }

    public ArrayList<ArrayList<String>> solveNQueens(int A) {
        ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();

        char[][] mat = new char[A][A];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = '.';
            }
        }

        HashSet<Integer> colHash = new HashSet<Integer>();
        HashSet<Integer> rowHash = new HashSet<Integer>();
        HashSet<Integer> leftDiagonal = new HashSet<Integer>();
        HashSet<Integer> rightDiagonal = new HashSet<Integer>();

        solveNQueensUtil(mat, 0, ans, rowHash, colHash, leftDiagonal, rightDiagonal);

        return ans;
    }

    /* Sudoku
    Problem Description
Write a program to solve a Sudoku puzzle by filling the empty cells. Empty cells are indicated by the character '.' You may assume that there will be only one unique solution.



A sudoku puzzle,



and its solution numbers marked in red.



Problem Constraints
N = 9


Input Format
First argument is an array of array of characters representing the Sudoku puzzle.


Output Format
Modify the given input to the required answer.


Example Input
Input 1:

A = [[53..7....], [6..195...], [.98....6.], [8...6...3], [4..8.3..1], [7...2...6], [.6....28.], [...419..5], [....8..79]]


Example Output
Output 1:

[[534678912], [672195348], [198342567], [859761423], [426853791], [713924856], [961537284], [287419635], [345286179]]


Example Explanation
Explanation 1:

Look at the diagrams given in the question.
    */
    boolean isSafe(ArrayList<ArrayList<Character>> mat, int row, int col, char n) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            return false;
        }
        // row check
        for (int j = 0; j < mat.get(row).size(); j++) {
            if (mat.get(row).get(j) == n) {
                return false;
            }
        }

        // column check
        for (int i = 0; i < mat.size(); i++) {
            if (mat.get(i).get(col) == n) {
                return false;
            }
        }

        int start = (row / 3) * 3, end = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat.get(start + i).get(end + j) == n) {
                    return false;
                }
            }
        }

        return true;
    }

    boolean solveSudokuUtil(ArrayList<ArrayList<Character>> mat, int row, int col) {
        if (col == mat.size()) {
            row++;
            col = 0;
        }

        if (row == mat.size()) {
            return true;
        }

        if (mat.get(row).get(col) != '.') {
            return solveSudokuUtil(mat, row, col + 1);
        }

        for (int i = '1'; i <= '9'; i++) {
            if (isSafe(mat, row, col, (char) i)) {
                mat.get(row).set(col, (char) i);

                if (solveSudokuUtil(mat, row, col + 1)) {
                    return true;
                }

                mat.get(row).set(col, '.');
            }
        }

        return false;
    }

    public void solveSudoku(ArrayList<ArrayList<Character>> a) {

        solveSudokuUtil(a, 0, 0);
    }
}
