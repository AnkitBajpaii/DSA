package TwoPointers;

import java.util.*;

public class twoPointers {

    /* Subarray with given sum
    Given an array of positive integers A and an integer B, find and return first continuous subarray which adds to B.

    If the answer does not exist return an array with a single element "-1".

    First sub-array means the sub-array for which starting index in minimum.



    Problem Constraints

    1 <= length of the array <= 100000
    1 <= A[i] <= 109
    1 <= B <= 109



    Input Format

    The first argument given is the integer array A.

    The second argument given is integer B.



    Output Format

    Return the first continuous sub-array which adds to B and if the answer does not exist return an array with a single element "-1".



    Example Input

    Input 1:

    A = [1, 2, 3, 4, 5]
    B = 5
    Input 2:

    A = [5, 10, 20, 100, 105]
    B = 110


    Example Output

    Output 1:

    [2, 3]
    Output 2:

    -1


    Example Explanation

    Explanation 1:

    [2, 3] sums up to 5.
    Explanation 2:

    No subarray sums up to required number.
    */
    public ArrayList<Integer> subArrayWithGivenSum(ArrayList<Integer> A, int B) {

        ArrayList<Integer> res = new ArrayList<Integer>();

        int start = 0, sum = A.get(0);

        for (int end = 1; end < A.size(); end++) {
            sum += A.get(end);
            while (sum > B && start < end) {
                sum -= A.get(start);

                start++;
            }

            if (sum == B) {
                for (int i = start; i <= end; i++) {
                    res.add(A.get(i));
                }

                return res;
            }

        }

        res.add(-1);

        return res;
    }

    /* Pairs with Given Difference
    Given an one-dimensional integer array A of size N and an integer B.

    Count all distinct pairs with difference equal to B.

    Here a pair is defined as an integer pair (x, y), where x and y are both numbers in the array and their absolute difference is B.



    Problem Constraints

    1 <= N <= 104

    0 <= A[i], B <= 105



    Input Format

    First argument is an one-dimensional integer array A of size N.

    Second argument is an integer B.



    Output Format

    Return an integer denoting the count of all distinct pairs with difference equal to B.



    Example Input

    Input 1:

    A = [1, 5, 3, 4, 2]
    B = 3
    Input 2:

    A = [8, 12, 16, 4, 0, 20]
    B = 4
    Input 3:

    A = [1, 1, 1, 2, 2]
    B = 0


    Example Output

    Output 1:

    2
    Output 2:

    5
    Output 3:

    2


    Example Explanation

    Explanation 1:

    There are 2 unique pairs with difference 3, the pairs are {1, 4} and {5, 2} 
    Explanation 2:

    There are 5 unique pairs with difference 4, the pairs are {0, 4}, {4, 8}, {8, 12}, {12, 16} and {16, 20} 
    Explanation 3:

    There are 2 unique pairs with difference 0, the pairs are {1, 1} and {2, 2}.
    */
    public int PairsWithDiff(int[] A, int B) {
        Arrays.sort(A);

        int n=A.length, i=0, j=1, count=0;
        
        while(i<n && j<n)
        {
            int diff = Math.abs(A[i] - A[j]);

            if(diff < B)
            {
                j++;
            } else if(diff > B)
            {
                i++;
            } else {
                count++;
                j++;
                while(j<n && A[j]==A[j-1]){
                    j++;
                }

                i++;
                while(i<n && A[i]==A[i-1]){
                    i++;
                }
            }

            if(i==j) j++;
        }

        return count;
    }

    /* Container With Most Water
    Given n non-negative integers A[0], A[1], …, A[n-1] , where each represents a point at coordinate (i, A[i]).

    N vertical lines are drawn such that the two endpoints of line i is at (i, A[i]) and (i, 0).

    Find two lines, which together with x-axis forms a container, such that the container contains the most water.

    Note: You may not slant the container.



    Problem Constraints

    0 <= N <= 105

    1 <= A[i] <= 105



    Input Format

    Single Argument representing a 1-D array A.



    Output Format

    Return single Integer denoting the maximum area you can obtain.



    Example Input

    Input 1:

    A = [1, 5, 4, 3]
    Input 2:

    A = [1]


    Example Output

    Output 1:

    6
    Output 2:

    0


    Example Explanation

    Explanation 1:

    
    5 and 3 are distance 2 apart. So size of the base = 2. Height of container = min(5, 3) = 3. 
    So total area = 3 * 2 = 6
    Explanation 2:

    
    No container is formed.
    */
    public int containerWithMostWater(int[] A) {
        int n = A.length;

        if(n==0 || n==1) return 0;
        
        int i=0, j=A.length-1;
        int ans = Integer.MIN_VALUE;

        while(i<n && j>=0 && i<j){
            ans = Math.max(ans, (j-i) * Math.min(A[i], A[j]));

            if(A[i] < A[j])
            {
                i++;
            } else {
                j--;
            }
        }

        return ans;
    }

    /* 3 Sum Zero
    Given an array A of N integers, are there elements a, b, c in S such that a + b + c = 0

    Find all unique triplets in the array which gives the sum of zero.

    Note:

    Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c) The solution set must not contain duplicate triplets.



    Problem Constraints

    0 <= N <= 7000

    -108 <= A[i] <= 108



    Input Format

    Single argument representing a 1-D array A.



    Output Format

    Output a 2-D vector where each row represent a unique triplet.



    Example Input

    A = [-1,0,1,2,-1,4]


    Example Output

    [ [-1,0,1],
    [-1,-1,2] ]


    Example Explanation

    Out of all the possible triplets having total sum zero,only the above two triplets are unique.
    */
    public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> A) {
        Collections.sort(A);
        int n = A.size();
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<=n-3; i++)
        {
            if (i > 0 && A.get(i).intValue() == A.get(i - 1).intValue())
	            continue;

            int j=i+1, k = n-1;
            while(j<n && k>=0 && j<k)
            {
                if(A.get(i) + A.get(j) + A.get(k) == 0)
                {
                    ArrayList<Integer> al = new ArrayList<Integer>();
                    al.add(A.get(i));
                    al.add(A.get(j));
                    al.add(A.get(k));
                    res.add(al);

                    int prev = j;
                    while(j<=k && A.get(j) == A.get(prev))
                    {
                        j++;
                    }

                } else if(A.get(i) + A.get(j) + A.get(k) > 0)
                {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return res;
    }

    /* Pairs with given sum II
    Given a sorted array of integers (not necessarily distinct) A and an integer B, find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.

    Since the number of such pairs can be very large, return number of such pairs modulo (109 + 7).



    Problem Constraints

    1 <= |A| <= 100000

    1 <= A[i] <= 10^9

    1 <= B <= 10^9



    Input Format

    The first argument given is the integer array A.

    The second argument given is integer B.



    Output Format

    Return the number of pairs for which sum is equal to B modulo (10^9+7).



    Example Input

    Input 1:

    A = [1, 1, 1]
    B = 2
    Input 2:

    
    A = [1, 1]
    B = 2


    Example Output

    Output 1:

    3
    Output 2:

    1


    Example Explanation

    Explanation 1:

    Any two pairs sum up to 2.
    Explanation 2:

    only pair (1, 2) sums up to 2.
    */
    public int CountPairsWithGivenSumWhenArrayHasDuplicates(int[] A, int B) {
        int n = A.length;

        int i = 0, j = n - 1;

        long count = 0;

        int mod = 1000 * 1000 * 1000 + 7;

        while (i < n && j >= 0 && i < j) {
            long sum = A[i] + A[j];

            if (sum > B) {
                j--;
            } else if (sum < B) {
                i++;
            } else {
                int ii = i, jj = j;
                int xx = A[i], yy = A[j];

                while (j > ii && A[j] == yy) {
                    j--;
                }

                while (i < jj && A[i] == xx) {
                    i++;
                }

                if (xx == yy && i > j) {
                    long t = jj - ii + 1;
                    count += (t * (t - 1) / 2);
                } else {
                    count += ((long) (i - ii)) * (jj - j);
                }

                count = (count % mod);
            }
        }

        return (int) (count % mod);
    }

    /* Count of pairs with the given sum
    Given a sorted array of distinct integers A and an integer B, find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.


    Input Format

    The first argument given is the integer array A.
    The second argument given is integer B.
    Output Format

    Return the number of pairs for which sum is equal to B.
    Constraints

    1 <= length of the array <= 100000
    1 <= A[i] <= 10^9 
    1 <= B <= 10^9
    For Example

    Input 1:
        A = [1, 2, 3, 4, 5]
        B = 5
    Output 1:
        2

    Input 2:
        A = [5, 10, 20, 100, 105]
        B = 110
    Output 2:
        2

    */
    public int CountPairsWithGivenSumInArrayWithDistinctIntegers(int[] A, int B) {
        int n = A.length;

        int i = 0, j = n - 1;

        long count = 0;

        while (i < n && j >= 0 && i < j) {
            long sum = A[i] + A[j];

            if (sum > B) {
                j--;
            } else if (sum < B) {
                i++;
            } else {
                count++;
                i++;
                j--;
            }
        }

        return (int) (count);
    }

    /* Another Count Rectangles
    Given a sorted array of distinct integers A and an integer B, find and return how many rectangles with distinct configurations can be created using elements of this array as length and breadth whose area is lesser than B.

    (Note that a rectangle of 2 x 3 is different from 3 x 2 if we take configuration into view)



    Problem Constraints

    1 <= |A| <= 100000
    1 <= A[i] <= 109
    1 <= B <= 109



    Input Format

    The first argument given is the integer array A.

    The second argument given is integer B.



    Output Format

    Return the number of rectangles with distinct configurations with area less than B modulo (109 + 7).



    Example Input

    Input 1:

    A = [1, 2]
    B = 5
    Input 2:

    A = [1, 2]
    B = 1


    Example Output

    Output 1:

    4
    Output 2:

    0


    Example Explanation

    Explanation 1:

    All 1X1, 2X2, 1X2 and 2X1 have area less than 5.
    Explanation 2:

    No Rectangle is valid.

     */
    public int anotherRectangle(int[] A, int B) {
        int n = A.length;
        if (n == 1) {
            long area = 1L * A[0] * A[0];
            if (area < B)
                return 1;

            return 0;
        }

        int i = 0, j = n - 1;
        int mod = 1000 * 1000 * 1000 + 7;
        long count = 0;

        while (i <= j) {
            long area = A[i] * 1L * A[j];

            if (area >= B) {
                j--;
            } else {
                count = (count + (2 * (j - i + 1) - 1) % mod) % mod;
                i++;
            }
        }

        return (int) (count % mod);
    }

    /* 3 Sum
    Given an array A of N integers, find three integers in A such that the sum is closest to a given number B. Return the sum of those three integers.

    Assume that there will only be one solution.



    Problem Constraints

    -108 <= B <= 108
    1 <= N <= 104
    -108 <= A[i] <= 108


    Input Format

    First argument is an integer array A of size N.

    Second argument is an integer B denoting the sum you need to get close to.



    Output Format

    Return a single integer denoting the sum of three integers which is closest to B.



    Example Input

    Input 1:

    A = [-1, 2, 1, -4]
    B = 1
    Input 2:

    
    A = [1, 2, 3]
    B = 6


    Example Output

    Output 1:

    2
    Output 2:

    6


    Example Explanation

    Explanation 1:

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
    Explanation 2:

    Take all elements to get exactly 6.
    */
    public int threeSumClosest(int[] A, int B) {

        Arrays.sort(A);

        int n = A.length;

        int ans = Integer.MAX_VALUE, minDist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int j = i + 1, k = n - 1;

            while (j < k) {
                int currSum = A[i] + A[j] + A[k];

                if (currSum == B) {
                    return B;
                }

                int currDist = Math.abs(currSum - B);
                if (currDist < minDist) {
                    minDist = currDist;
                    ans = currSum;
                }

                if (currSum < B) {
                    j++;

                } else if (currSum > B) {
                    k--;
                }
            }
        }

        return ans;
    }

    /* Sort by Color - Dutch National Flag Problem
    Given an array with N objects colored red, white, or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

    We will use the integers 0, 1, and 2 to represent red, white, and blue, respectively.

    Note: Using the library sort function is not allowed.



    Problem Constraints
    1 <= N <= 1000000
    0 <= A[i] <= 2


    Input Format
    First and only argument of input contains an integer array A.


    Output Format
    Return an integer array in asked order


    Example Input
    Input 1 :
        A = [0 1 2 0 1 2]
    Input 2:

        A = [0]


    Example Output
    Output 1:
        [0 0 1 1 2 2]
    Output 2:

        [0]


    Example Explanation
    Explanation 1:
        [0 0 1 1 2 2] is the required order.
    */
    public int[] sortColors(int[] A) {
        int n = A.length;

        int lo = 0, mid = 0, hi = n - 1;
        while (mid <= hi) {
            switch (A[mid]) {
                case 0: {
                    int t = A[mid];
                    A[mid] = A[lo];
                    A[lo] = t;
                    lo++;
                    mid++;
                    break;
                }

                case 1: {
                    mid++;
                    break;
                }

                case 2: {
                    int t = A[mid];
                    A[mid] = A[hi];
                    A[hi] = t;

                    hi--;
                    break;
                }

                default:
                    break;
            }
        }

        return A;
    }
    
}
