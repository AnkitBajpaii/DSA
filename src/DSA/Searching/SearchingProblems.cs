using System;

namespace DSA.Searching
{
    public static class SearchingProblems
    {
        // Binary Search Algorithm Iterative version
        public static int BinarySearchIterative(int[] arr, int x)
        {
            int n = arr.Length;
            int l = 0, h = n - 1;

            while (l <= h)
            {
                int mid = l + (h - l) / 2;
                if (arr[mid] == x)
                    return mid;

                if (x < arr[mid])
                {
                    h = mid - 1;
                }
                else
                {
                    l = mid + 1;
                }
            }
            return -1;
        }

        // Binary Search Algorithm Recursive version
        public static int BinarySearchRecursive(int[] arr, int l, int h, int x)
        {
            if (l > h)
            {
                return -1;
            }

            int mid = l + (h - l) / 2;
            if (arr[mid] == x)
                return mid;

            if (x < arr[mid])
            {
                return BinarySearchRecursive(arr, l, mid - 1, x);
            }
            else
            {
                return BinarySearchRecursive(arr, mid + 1, h, x);
            }
        }

        // Find first occurence or left most index of an element in a sorted array
        public static int FirstOccurence(int[] arr, int l, int h, int x)
        {
            if (l > h)
            {
                return -1;
            }

            int mid = l + (h - l) / 2;
            if (arr[mid] == x && (mid == 0 || arr[mid - 1] != x))
            {
                return mid;
            }

            if (x <= arr[mid])
            {
                return FirstOccurence(arr, l, mid - 1, x);
            }
            else
            {
                return FirstOccurence(arr, mid + 1, h, x);
            }
        }

        // Find last occurence or right most index of an element in a sorted array
        public static int LastOccurence(int[] arr, int l, int h, int x)
        {
            if (l > h)
            {
                return -1;
            }

            int mid = l + (h - l) / 2;
            int n = arr.Length;
            if (arr[mid] == x && (mid == n - 1 || arr[mid + 1] != x))
            {
                return mid;
            }

            if (x >= arr[mid])
            {
                return LastOccurence(arr, mid + 1, h, x);
            }
            else
            {
                return LastOccurence(arr, l, mid - 1, x);
            }
        }

        // Find no. of occurences of an element in a sorted array
        public static int CountOccurences(int[] arr, int x)
        {
            int n = arr.Length;

            int first = FirstOccurence(arr, 0, n - 1, x);
            if(first == -1)
            {
                return 0;
            }

            int last = LastOccurence(arr, 0, n - 1, x);

            return last - first + 1;
        }

        // Find no. of one's in a binary sorted increasing array
        public static int CountOnesInBinarySortedArray(int[] arr, int x)
        {
            int n = arr.Length;

            int first = FirstOccurence(arr, 0, n - 1, 1);

            return n - first + 1;
        }

        //Given a binary sorted non-increasing array arr of size N.You need to print the count of 1's in the binary array.
        public static int CountOnesInSortedDecreasingArray(int[] arr, int n)
        {

            int low = 0;
            int high = n - 1;

            while (low <= high)
            {
                int mid = low + ((high - low) / 2);

                if (arr[mid] == 1 && (mid == n - 1 || arr[mid + 1] != 1))
                {
                    return mid + 1;
                }

                if (arr[mid] == 0)
                {
                    high = mid - 1;
                }
                else
                {
                    low = mid + 1;
                }
            }

            return 0;

        }

        // Find an element in an infinite size sorted array
        public static int FindInInfiniteSizedSortedArr(int[] arr, int x)
        {
            if (arr[0] == x)
            {
                return 0;
            }
            int i = 1;
            while (arr[i] < x)
            {
                i = i * 2;
            }

            if (arr[i] == x)
            {
                return i;
            }

            return BinarySearchRecursive(arr, i / 2 + 1, i - 1, x);
        }

        // Find an element in a sorted rotated array
        public static int FindInSortedRotatedArr_approach1(int[] arr, int x)
        {
            int n = arr.Length;
            int low = 0, high = n - 1;
            while (low <= high)
            {
                int mid = low + (high - low) / 2;

                if (arr[mid] == x)
                {
                    return mid;
                }

                if (arr[mid] > arr[low]) // left half is sorted
                {
                    if (x >= arr[low] && x < arr[mid])
                    {
                        high = mid - 1;
                    }
                    else
                    {
                        low = mid + 1;
                    }
                }
                else // right half is sorted
                {
                    if (x > arr[mid] && x <= arr[high])
                    {
                        low = mid + 1;
                    }
                    else
                    {
                        high = mid - 1;
                    }
                }
            }

            return -1;
        }

        // Find pivot in sorted rotated array
        public static int FindPivot(int[] arr, int low, int high)
        {
            if (low > high) return -1;

            if (low == high) return low;

            int mid = low + (high - low) / 2;
            //check if middle element is pivot

            if (mid < high && arr[mid] > arr[mid + 1])
            {
                return mid;
            }

            if (mid > low && arr[mid] < arr[mid - 1])
            {
                return (mid - 1);
            }

            if (arr[mid] <= arr[low])
            {
                return FindPivot(arr, low, mid - 1);
            }

            return FindPivot(arr, mid + 1, high);
        }

        // Find an element in a sorted rotated array
        public static int PivotedBinarySearch(int[] arr, int x)
        {
            int n = arr.Length;
            int pivot = FindPivot(arr, 0, n - 1);

            if (pivot == -1)
            {
                return BinarySearchRecursive(arr, 0, n - 1, x);
            }

            if (arr[pivot] == x)
            {
                return pivot;
            }

            if (x >= arr[0])
            {
                return BinarySearchRecursive(arr, 0, pivot - 1, x);
            }

            return BinarySearchRecursive(arr, pivot + 1, n - 1, x);
        }

        // Given an integer x. The task is to find the square root of x. If x is not a perfect square, then return floor(√x).
        public static long FindSquaroot(long x)
        {
            if (x == 0 || x == 1) return x;
            long start = 1, end = x, ans = 1;
            while (start <= end)
            {
                long mid = start + (end - start) / 2;
                if (mid * mid == x)
                {
                    return mid;
                }

                if (mid * mid < x)
                {
                    ans = mid;
                    start = mid + 1;
                }
                else
                {
                    end = mid - 1;
                }
            }

            return ans;
        }

        // Find peak element element in a sorted  array
        public static int FindPeakElement(int[] arr, int n, int low, int high)
        {
            int mid = low + (high - low) / 2;
            if ((mid == 0 || arr[mid] >= arr[mid - 1]) && (mid == n - 1 || arr[mid] >= arr[mid + 1]))
            {
                return mid;
            }

            if (mid > 0 && arr[mid - 1] > arr[mid])
            {
                return FindPeakElement(arr, n, low, high - 1);
            }

            return FindPeakElement(arr, n, mid + 1, high);
        }

        //Given an array, check if there exists a pair, whose sum is given sum
        public static bool IsPairWithGivenSum(int[] arr, int start, int end, int x)
        {
            while (start <= end)
            {
                int sum = arr[start] + arr[end];
                if (sum == x)
                {
                    return true;
                }

                if (sum > x)
                {
                    end--;
                }
                else
                {
                    start++;
                }
            }

            return false;
        }

        //Given an array, check if there exists a triplet, whose sum is given sum
        public static bool IsTripletWithGivenSum(int[] arr, int x)
        {
            Array.Sort(arr);

            int n = arr.Length;

            for (int i = 0; i < n - 2; i++)
            {
                if (IsPairWithGivenSum(arr, i + 1, n - 1, x - arr[i]))
                {
                    return true;
                }
            }

            return false;
        }

        // Count pair with a given sum in sorted array
        public static int CountPairWithGivenSum(int[] arr, int start, int end, int givenSum)
        {
            int count = 0;
            while (start < end)
            {
                int currSum = arr[start] + arr[end];
                if (currSum == givenSum)
                {
                    count++;
                }

                if (currSum > givenSum)
                {
                    end--;
                }
                else
                {
                    start++;
                }
            }

            return count;
        }

        // Count triplet with a given sum in an array
        public static int CountTripletsWithGivenSum(int[] arr, int sum)
        {
            Array.Sort(arr);

            int n = arr.Length;
            int totalCount = 0;
            for (int i = 0; i < n; i++)
            {
                int count = CountPairWithGivenSum(arr, i + 1, n - 1, sum - arr[i]);
                totalCount += count;

            }

            return totalCount;
        }

        // check if array has pythagoras triplets
        public static bool CheckForPythagorasTriplet(int[] arr)
        {

            Array.Sort(arr);

            int n = arr.Length;
            for (int i = 0; i < n; i++)
            {
                arr[i] = arr[i] * arr[i];
            }

            for (int i = 0; i < n - 1; i++)
            {
                if (IsPairWithGivenSum(arr, i + 1, n - 1, arr[i]))
                {
                    return true;
                }
            }

            return false; ;
        }

        // Get median of two sorted arrays
        public static double GetMedianOfTwoSortedArrays(int[] arr1, int[] arr2)
        {
            int n1 = arr1.Length;
            int n2 = arr2.Length;

            int begin1 = 0, end1 = n1;
            double res = 0;
            while (begin1 <= end1)
            {
                int i1 = begin1 + ((end1 - begin1) / 2);
                int i2 = n1 + (n2 - n1 + 1) / 2 - i1;

                int min1 = i1 == n1 ? Int32.MaxValue : arr1[i1];
                int max1 = i1 == 0 ? Int32.MinValue : arr1[i1 - 1];

                int min2 = i2 == n2 ? Int32.MaxValue : arr2[i2];
                int max2 = i2 == 0 ? Int32.MinValue : arr2[i2 - 1];

                if (max1 <= min2 && max2 <= min1)
                {
                    if ((n1 + n2) % 2 == 0)
                    {
                        res = (double)(Math.Max(max1, max2) + Math.Min(min1, min2)) / 2;
                    }
                    else
                    {
                        res = (double)Math.Max(max1, max2);
                    }

                    return res;
                }
                else if (max1 > min2)
                {
                    end1 = i1 - 1;
                }
                else
                {
                    begin1 = i1 + 1;
                }
            }

            return res;
        }

        //Given a sorted array arr[] of size N without duplicates, and given a value x. Find the floor of x in given array. Floor of x is defined as the largest element K in arr[] such that K is smaller than or equal to x.
        public static int FindFloor(long[] arr, int left, int right, long x)
        {
            int res = -1;
            while (left <= right)
            {
                int mid = left + (right - left) / 2;

                if (arr[mid] == x)
                {
                    res = mid;
                    break;
                }

                if (arr[mid] > x)
                {
                    right = mid - 1;
                }
                else
                {
                    res = mid;
                    left = mid + 1;
                }
            }
            return res;
        }

        //Minimum Number in a sorted rotated array
        public static int MinInSortedRotatedArr(int[] arr, int low, int high)
        {
            int pivot = FindPivot(arr, low, high);
            int n = high - low + 1;
            return arr[(pivot + 1) % n];
        }

        //You are given an array of N+2 integer elements. All elements of the array are in range 1 to N. Also, all elements occur once except two numbers which occur twice. Find the two repeating numbers.
        public static void TwoRepeated(int[] arr, int n)
        {

            String res = "";
            for (int i = 0; i < n; i++)
            {
                int index = Math.Abs(arr[i]) - 1;
                int val = arr[index];
                if (val < 0)
                    res += " " + arr[i];
                else
                    arr[index] = -arr[index];
            }

            Console.WriteLine(res.Trim());
        }

        // You are given an array of N integers including 0. The task is to find the smallest positive number missing from the array.
        public static int MissingSmallestPositiveNum(int[] arr, int n)
        {
            // seggregate negavtives to left side and positive to right side
            int j = 0;
            for (int i = 0; i < n; i++)
            {
                if (arr[i] <= 0)
                {
                    Util.Swap(arr, i, j);
                    j++;
                }
            }

            int[] arr2 = new int[n - j];
            int k = 0;
            for (int i = j; i < n; i++)
            {
                arr2[k] = arr[i];
                k++;
            }

            // idea: if we treat array element as index's of array and mark those indexes then
            // the first index which is not marked will represent index + 1 the smallest positive missing no. that is missing

            // Mark arr[i] as visited by making 
            // arr[arr[i] - 1] negative. Note that 
            // 1 is subtracted because index start 
            // from 0 and positive numbers start from 1 
            for (int i = 0; i < k; i++)
            {
                int x = Math.Abs(arr2[i]);
                if (x > 0 && x - 1 < k && arr2[x - 1] > 0)
                {
                    arr2[x - 1] = -arr2[x - 1];
                }
            }

            // Return the first index value at which 
            // is positive 
            for (int i = 0; i < k; i++)
            {
                if (arr2[i] > 0)
                {
                    return i + 1;
                }
            }

            return k + 1;
        }

        // You are given heights of consecutive buildings. You can move from the roof of a building to the roof of next adjacent building. 
        //You need to find the maximum number of consecutive steps you can put forward such that you gain an increase in altitude with each step.
        public static int MaxStep(int[] arr, int n)
        {

            if (n == 1)
            {
                return 0;
            }

            int count = 0, max = Int32.MinValue;
            for (int i = 1; i < n; i++)
            {
                if (arr[i - 1] < arr[i])
                {
                    count++;
                }
                else
                {
                    max = Math.Max(max, count);
                    count = 0;
                }
            }

            return Math.Max(max, count);
        }

        // Given an integer array representing the heights of N buildings, the task is to delete N-2 buildings such that the water that can be trapped between the remaining two building is maximum.
        public static int MaxWaterStored(int[] arr, int n)
        {
            int i = 0, j = n - 1;
            int res = Int32.MinValue;
            while (i < j)
            {
                res = Math.Max(res, (j - i - 1) * Math.Min(arr[i], arr[j]));

                if (arr[i] < arr[j])
                    i++;
                else if (arr[j] < arr[i])
                    j--;
                else
                {
                    i++;
                    j--;
                }
            }
            return res;
        }

        //Given an array of positive integers, where elements are consecutive (sorted). 
        //Also, there is a single element which is repeating X (any variable) number of times. 
        //Now, the task is to find the element which is repeated and number of times it is repeated.
        public static void FindRepeatingInConsecutiveSorted(int[] arr, int n)
        {
            int l = 0, h = n - 1;
            int mid = 0;
            while (l < h)
            {
                mid = l + (h - l) / 2;
                if (arr[mid] < (arr[0] + mid))
                {
                    h = mid;
                }
                else
                {
                    l = mid + 1;
                }
            }

            // if no elements were repeated then we can write
            // n = a[n-1] - a[0] + 1
            // but if an element is repeated say x number of times then
            // n - x = a[n-1] - a[0]

            // no of time repeated
            int count = (arr[0] + n - arr[n - 1]);
            Console.WriteLine(arr[l] + " " + count);
        }

        // Given an unsorted array A of size N of non-negative integers, find a continuous sub-array which adds to a given number S.
        public static void SubArraySum(int[] arr, int n, int sum)
        {
            int currSum = arr[0], s = 0, e;
            
            for (e = 1;  e <= n; e++)
            {
                while (currSum > sum && s < e - 1)
                {
                    currSum = currSum - arr[s];
                    s++;
                }
                if(currSum == sum)
                {
                    break;
                }

                if(e<n)
                    currSum = currSum + arr[e];
            }

            if (currSum == sum)
            {
                Console.WriteLine((s + 1) + " " + e);                
            } else {
                Console.WriteLine(-1);
            }
        }
    }
}
