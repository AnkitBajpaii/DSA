using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Arrays
{
    public static class ArrayProblems
    {
        // insert element at given position
        public static void InsertAt(int[] A, int pos, int size, int capacity, int key)
        {
            //1, 3, 5, 8
            if (size == capacity)
            {
                return;
            }

            int index = pos - 1;
            for (int i = size - 1; i >= index; i--)
            {
                A[i + 1] = A[i];
            }

            A[index] = key;
        }

        // find and delete element
        public static int Delete(int[] A, int size, int key)
        {
            int pos = 0;
            for (int i = 0; i <= size - 1; i++)
            {
                if (A[i] == key)
                {
                    pos = i;
                    break;
                }
            }

            if (pos == size)
            {
                return size;
            }

            for (int i = pos; i < size - 1; i++)
            {
                A[i] = A[i + 1];
            }

            return size - 1;
        }

        public static void ReverseArray(int[] A, int low, int high)
        {
            // to reverse entire array, pass low = 0 and high = size
            while (low < high)
            {
                Util.Swap(A, low, high);

                low++;
                high--;
            }
        }

        public static void ReArrangeAlternate(int[] A)
        {
            int n = A.Length;
            for (int i = 0; i < n; i++)
            {
                ReverseArray(A, i, n - 1);
            }
        }

        public static int RemoveDuplicatesFromSortedArray(int[] A)
        {
            int size = A.Length;
            if (size == 1)
            {
                return size;
            }

            int res = 1;
            for (int i = 1; i < size; i++)
            {
                if (A[i] != A[res - 1])
                {
                    A[res] = A[i];
                    res++;
                }
            }

            return res;
        }

        public static void LeftRotateByOne(int[] A)
        {
            // Left rotate means counter clockwise direction
            int size = A.Length;
            int temp = A[0];
            for (int i = 1; i < size; i++)
            {
                A[i - 1] = A[i];
            }

            A[size - 1] = temp;
        }

        public static void LeftRotateBy_K_Places(int[] A, int k)
        {
            // Left rotate means counter clockwise direction
            int size = A.Length;
            if ((k % size) == 0)
            {
                return;
            }

            int d = (k % size);
            ReverseArray(A, 0, d - 1);
            ReverseArray(A, d, size - 1);
            ReverseArray(A, 0, size - 1);
        }

        public static void LeadersInArray(int[] A)
        {
            int n = A.Length;
            int currLeader = A[n - 1];
            Console.WriteLine(currLeader);
            for (int i = n - 2; i >= 0; i--)
            {
                if (A[i] > currLeader)
                {
                    currLeader = A[i];
                    Console.WriteLine(currLeader);
                }
            }
        }

        public static int MaxDiffProblem(int[] A)
        {
            int n = A.Length;
            if (n == 1)
            {
                return A[0];
            }

            int res = A[1] - A[0];
            int min = A[0];
            for (int j = 1; j < n; j++)
            {
                res = Math.Max(res, A[j] - min);
                min = Math.Min(min, A[j]);
            }

            return res;
        }

        public static int StockBuyAndSell(int[] price)
        {
            int n = price.Length;

            int profit = 0;

            for (int i = 1; i < n; i++)
            {
                if (price[i] > price[i - 1])
                {
                    profit = profit + (price[i] - price[i - 1]);
                }
            }
            return profit;
        }

        public static int TrappingRainWater(int[] A)
        {
            int n = A.Length;
            int res = 0;

            int[] lMax = new int[n];
            lMax[0] = A[0];
            for (int i = 1; i < n; i++)
            {
                lMax[i] = Math.Max(lMax[i - 1], A[i]);
            }

            int[] rMax = new int[n];
            rMax[n - 1] = A[n - 1];
            for (int i = n - 2; i >= 0; i--)
            {
                rMax[i] = Math.Max(rMax[i + 1], A[i]);
            }

            for (int i = 1; i <= n - 2; i++)
            {
                int storageAtithBar = Math.Min(lMax[i], rMax[i]) - A[i];
                res = res + storageAtithBar;
            }

            return res;
            // time complexity: O(N)
            // space complexity: O(N)
        }

        public static int MaximumConsecutiveOnes(int[] A)
        {
            int n = A.Length, res = 0, counter = 0;

            for (int i = 0; i < n; i++)
            {
                if (A[i] == 0)
                {
                    counter = 0;
                }
                else
                {
                    counter++;
                    res = Math.Max(res, counter);
                }
            }

            return res;
        }

        // kadanes algo
        public static int MaximumSumSubArray(int[] A)
        {
            int n = A.Length, res = A[0], maxEnding = A[0];
            for (int i = 1; i < n; i++)
            {
                maxEnding = Math.Max((maxEnding + A[i]), A[i]);
                res = Math.Max(res, maxEnding);
            }

            return res;
        }

        public static int MinimumSumSubArray(int[] A)
        {
            int n = A.Length;
            int res = A[0], minEnding = A[0];
            for (int i = 1; i < n; i++)
            {
                minEnding = Math.Min((minEnding + A[i]), A[i]);
                res = Math.Min(res, minEnding);
            }
            return res;
        }

        public static int MaxEvenOddSubArray(int[] A)
        {
            int n = A.Length, res = 1, curr = 1;
            for (int i = 1; i < n; i++)
            {
                if ((A[i] % 2 == 0 && A[i - 1] % 2 != 0) ||
                    (A[i] % 2 != 0) && A[i - 1] % 2 == 0)
                {
                    curr++;
                    res = Math.Max(res, curr);
                }
                else
                {
                    curr = 1;
                }
            }

            return res;
        }

        public static int MaxCircularSumSubArray(int[] A)
        {
            int n = A.Length;
            int normalMaxSumSubArr = MaximumSumSubArray(A);
            if (normalMaxSumSubArr < 0)
            {
                return normalMaxSumSubArr;
            }

            int arrSum = 0;
            for (int i = 0; i < n; i++)
            {
                arrSum = arrSum + A[i];
                A[i] = -A[i];
            }

            int minimumSumSubArr = MaximumSumSubArray(A);
            int maxCircularSumSubArr = arrSum + minimumSumSubArr; // because we inverted the array we did addition here

            int res = Math.Max(normalMaxSumSubArr, maxCircularSumSubArr);

            return res;
        }

        /// <summary>
        /// MajorityElement is one which appears more than n/2 times, where n is the length of array
        /// </summary>
        /// <param name="A">Array</param>
        /// <returns>Index of majority element</returns>
        public static int MajorityElement(int[] A)
        {
            int n = A.Length, candidate = 0, count = 1;

            // find candidate
            for (int i = 1; i < n; i++)
            {
                if (A[i] == A[candidate])
                {
                    count++;
                }
                else
                {
                    count--;
                }

                if (count == 0)
                {
                    candidate = i;
                    count = 1;
                }
            }

            // check if candidate is majority
            count = 0;
            for (int i = 0; i < n; i++)
            {
                if (A[i] == A[candidate])
                {
                    count++;
                }
            }

            if (count <= n / 2)
            {
                return -1;
            }

            return candidate;
        }

        //Given a binary array, we need to find the minimum of number of group flips to make all array elements same. 
        //In a group flip, we can flip any set of consecutive 1s or 0s.
        public static void MinimumConsecutiveFlips(int[] A)
        {
            int n = A.Length;
            for (int i = 1; i < n; i++)
            {
                if (A[i] != A[i - 1])
                {
                    if (A[i] != A[0])
                    {
                        Console.Write($"From {i} to ");
                    }
                    else
                    {
                        Console.Write($"{i - 1}");
                        Console.WriteLine();
                    }
                }
            }

            if (A[n - 1] != A[0])
            {
                Console.Write($"{n - 1}");
            }
        }

        public static int MaxSubArraySumSizeK(int[] A, int k)
        {
            // Use Sliding window technique
            int n = A.Length;
            int currSum = 0;
            for (int i = 0; i < k; i++)
            {
                currSum += A[i];
            }

            int maxSum = currSum;
            for (int i = k; i < n; i++)
            {
                currSum = currSum + A[i] - A[i - k];
                maxSum = Math.Max(currSum, maxSum);
            }

            return maxSum;

        }

        // Given array of size n, check if there exist a sub-array of size k with given sum
        // here window size is given and that is k.
        public static bool IsThereSubArrayOfSizeKWithGivenSum(int[] A, int sum, int k)
        {
            // Use Sliding window technique
            int n = A.Length;
            int currSum = 0;
            for (int i = 0; i < k; i++)
            {
                currSum += A[i];
            }

            if (currSum == sum)
            {
                return true;
            }

            for (int i = k; i < n; i++)
            {
                currSum = currSum + A[i] - A[i - k];

                if (currSum == sum)
                {
                    return true;
                }
            }

            return false;

        }

        // Given an array of size n, check if there exist a sub-array with given sum.
        // Note that here the window size is Not given, unlike previous problem.
        public static bool IsSubArrayWithGivenSumExist(int[] A, int sum)
        {
            // Use Sliding window technique
            int n = A.Length, currSum = A[0], start = 0;

            for (int end = 1; end <= n; end++)
            {
                while (currSum > sum && start < end - 1)
                {
                    currSum = currSum - A[start];
                    start++;
                }

                if (currSum == sum)
                {
                    return true;
                }

                if (end < n)
                    currSum = currSum + A[end];
            }

            return currSum == sum;
        }

        public static int[] Generate_N_BonacciNumbers(int n, int m)
        {
            // Use Sliding window technique
            int[] arr = new int[m];
            for (int i = 0; i < n - 1; i++)
            {
                arr[i] = 0;
            }

            arr[n - 1] = 1;

            int currSum = 1;

            for (int i = n; i < m; i++)
            {
                arr[i] = currSum;
                currSum = currSum + arr[i] - arr[i - n];
            }

            return arr;
        }

        public static int[] PrintDistictElementsInWindowOfSizeK(int[] A, int k)
        {
            // Use Sliding window technique

            int n = A.Length;

            Dictionary<int, int> map = new Dictionary<int, int>();

            Func<Dictionary<int, int>, int> getDistinctCount = (_map) =>
            {
                int count = 0;
                foreach (var kvp in _map)
                {
                    if (kvp.Value > 0)
                    {
                        count++;
                    }
                }
                return count;
            };

            for (int i = 0; i < k && i < n; i++)
            {
                if (map.ContainsKey(A[i]))
                {
                    map[A[i]] = map[A[i]] + 1;
                }
                else
                {
                    map.Add(A[i], 1);
                }
            }

            List<int> res = new List<int>();
            res.Add(getDistinctCount(map));

            for (int i = k; i < n; i++)
            {
                if (map.ContainsKey(A[i]))
                {
                    map[A[i]] = map[A[i]] + 1;
                }
                else
                {
                    map.Add(A[i], 1);
                }

                int val = map[A[i - k]];
                map[A[i - k]] = val > 0 ? val - 1 : 0;

                res.Add(getDistinctCount(map));
            }

            int[] final = res.ToArray();
            return final;
        }

        public static int[] ComputePrefixSum(int[] A)
        {
            int[] prefixSum = new int[A.Length];
            prefixSum[0] = A[0];
            for (int i = 1; i < A.Length; i++)
            {
                prefixSum[i] = prefixSum[i - 1] + A[i];
            }
            return prefixSum;
        }

        // Given a fixed array and multiple queries of following types on array, how to efficiently perform these queries? I/P : Array A of size n, and list of operation GetSum(l, r) where 0<=l<=r<=n, computes sum of elements from index l to index r.
        public static int GetSumUtil(int[] A, int[] prefixSum, int l, int r)
        {
            // prefixSum computed using ComputePrefixSum method from caller
            if (l != 0)
            {
                return prefixSum[r] - prefixSum[l - 1];
            }
            else
            {
                return prefixSum[r];
            }
        }

        public static bool HasEquilibriumPoint(int[] A)
        {
            // Prefix sum techniquie is used.
            int n = A.Length, sum = 0;
            for (int i = 0; i < n; i++)
            {
                sum += A[i];
            }

            int lSum = 0;
            for (int i = 0; i < n; i++)
            {
                if (lSum == (sum - A[i]))
                {
                    return true;
                }

                lSum = lSum + A[i];
                sum = sum - A[i];
            }

            return false;
        }

        public static int MaxIndexDiff(int[] arr)
        {
            int n = arr.Length;
            int[] lMin = new int[n];
            int[] rMax = new int[n];

            lMin[0] = arr[0];
            rMax[n - 1] = arr[n - 1];

            for (int m = 1; m < n; m++)
            {
                lMin[m] = Math.Min(lMin[m - 1], arr[m]);
            }

            for (int k = n - 2; k >= 0; k--)
            {
                rMax[k] = Math.Max(rMax[k + 1], arr[k]);
            }

            int i = 0, j = 0;
            int res = Int32.MinValue;
            while (i < n && j < n)
            {
                if (lMin[i] <= rMax[j])
                {
                    res = Math.Max(res, j - i);
                    j++;
                }
                else
                {
                    i++;
                }
            }
            return res;
        }

        /// <summary>
        /// Given an array arr[] of size N where every element is in the range from 0 to n-1.   /// Rearrange the given array so that arr[i] becomes arr[arr[i]]. 
        /// </summary>        
        public static void ReArrange(int[] arr, int n)
        {
            for (int i = 0; i < n; i++)
            {
                arr[i] = arr[i] + (arr[arr[i]] % n) * n;
            }

            for (int i = 0; i < n; i++)
            {
                arr[i] = arr[i] / n;
            }
        }

        public static List<int> ReverseInGroups(List<int> mv, int n, int k)
        {
            int start = 0;
            List<int> res = new List<int>();

            while (start < n)
            {
                int end = start + k - 1;
                if (end > n - 1)
                {
                    end = n - 1;
                }
                for (int i = end; i >= start; i--)
                {
                    res.Add(mv[i]);

                }
                start = start + k;
            }

            return res;
        }

        // You are given an array arr[] of N integers including 0. The task is to find the smallest positive number missing from the array.
        public static int MissingNumber(int[] arr, int size)
        {
            int shift = Segregate(arr, size);

            int[] arr2 = new int[size - shift];
            int j = 0;
            for (int i = shift; i < size; i++)
            {
                arr2[j] = arr[i];
                j++;
            }

            return FindMissingPositive(arr2, j);
        }

        static int Segregate(int[] a, int n)
        {
            int j = 0;

            for (int i = 0; i < n; i++)
            {
                if (a[i] < 0)
                {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                    j++;
                }
            }
            return j;
        }

        static int FindMissingPositive(int[] arr, int size)
        {
            int i;

            // Mark arr[i] as visited by making 
            // arr[arr[i] - 1] negative. Note that 
            // 1 is subtracted because index start 
            // from 0 and positive numbers start from 1 
            for (i = 0; i < size; i++)
            {
                int x = Math.Abs(arr[i]);
                if (x > 0 && x - 1 < size && arr[x - 1] > 0)
                    arr[x - 1] = -arr[x - 1];
            }

            // Return the first index value at which 
            // is positive 
            for (i = 0; i < size; i++)
                if (arr[i] > 0)
                    return i + 1; // 1 is added becuase indexes // start from 0 

            return size + 1;
        }

        // Frequencies of Limited Range Array Elements
        //Given an array A[] of N positive integers which can contain integers from 1 to N where elements can be repeated or can be absent from the array. Your task is to count the frequency of all elements from 1 to N.
        // Input: arr[] = {2, 3, 3, 2, 5}
        // O/P {0,2,2,0,1}
        public static void FindCounts(int[] arr)
        {
            int n = arr.Length;
            for (int i = 0; i < n; i++)
            {
                arr[i] = arr[i] - 1;
            }

            for (int i = 0; i < n; i++)
            {
                arr[arr[i] % n] = arr[arr[i] % n] + n;
            }

            for (int i = 0; i < n; i++)
            {
                Console.WriteLine(i + 1 + "->" + arr[i] / n);
            }
        }
    }
}
