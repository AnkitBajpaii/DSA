using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Sorting
{
    public class Pair
    {
        public int Start { get; set; }
        public int End { get; set; }
        public Pair(int x, int y)
        {
            this.Start = x;
            this.End = y;
        }
    }

    public class SortingProblems
    {
        public static void BubbleSort(int[] arr, int n)
        {
            for (int i = 0; i < n - 1; i++)
            {
                for (int j = 0; j < n - i - 1; j++)
                {
                    if (arr[j] > arr[j + 1])
                    {
                        Util.Swap(arr, j, j + 1);
                    }
                }

            }
        }

        public static void InsertionSort(int[] arr, int n)
        {
            for (int i = 1; i < n; i++)
            {
                int key = arr[i];

                int j = i - 1;
                while (j >= 0 && arr[j] > key)
                {
                    arr[j + 1] = arr[j];
                    j--;
                }

                arr[j + 1] = key;
            }
        }

        //Shell Sort is variation of insertion sort where we compare elements which are distance apart rather than adjacent
        public static void ShellSort(int[] arr, int n)
        {
            for (int gap = n / 2; gap > 0; gap = gap / 2)
            {
                for (int i = gap; i < n; i++)
                {
                    int key = arr[i];
                    int j = i - gap;
                    while (j >= 0 && arr[j] > key)
                    {
                        arr[j + gap] = arr[j];
                        j = j - gap;
                    }

                    arr[j + gap] = key;
                }
            }
        }

        public static void MergeSort(int[] arr, int l, int r)
        {
            if (l < r)
            {
                int m = l + (r - l) / 2;
                MergeSort(arr, l, m);
                MergeSort(arr, m + 1, r);
                Merge(arr, l, m, r);
            }
        }

        // Merge Function of Merge Sort
        public static void Merge(int[] arr, int l, int m, int r)
        {
            int n1 = m - l + 1;
            int n2 = r - m;

            int[] L = new int[n1];
            int[] R = new int[n2];

            int i;
            for (i = 0; i < n1; i++)
            {
                L[i] = arr[l + i];
            }

            for (i = 0; i < n2; i++)
            {
                R[i] = arr[m + 1 + i];
            }

            i = 0;
            int j = 0, k = l;

            while (i < n1 && j < n2)
            {
                if (L[i] <= R[j])
                {
                    arr[k++] = L[i++];
                }
                else
                {
                    arr[k++] = R[j++];
                }
            }

            while (i < n1)
            {
                arr[k++] = L[i++];
            }

            while (j < n2)
            {
                arr[k++] = R[j++];
            }
        }

        //O(m*n) solution
        public static void MergeWithOutUsingExtraSpace(int[] arr1, int[] arr2)
        {
            int m = arr1.Length;
            int n = arr2.Length;

            for (int i = n - 1; i >= 0; i--)
            {
                int last = arr1[m - 1];
                int j = m - 2;
                while (j >= 0 && arr1[j] > arr2[i])
                {
                    arr1[j + 1] = arr1[j];
                    j--;
                }
                arr1[j + 1] = arr2[i];
                arr2[i] = last;
            }
        }

        public static int[] Merge3SortedArrays(int[] arr1, int[] arr2, int[] arr3)
        {
            int p = arr1.Length, q = arr2.Length, r = arr3.Length;
            int[] res = new int[p + q + r];
            int i = 0, j = 0, k = 0, index = 0;
            while (i < p && j < q && k < r)
            {
                if (arr1[i] <= arr2[j] && arr1[i] <= arr3[k])
                {
                    res[index] = arr1[i];
                    i++;

                }
                else if (arr2[j] <= arr1[i] && arr2[j] <= arr3[k])
                {
                    res[index] = arr2[j];
                    j++;
                }
                else if (arr3[k] <= arr1[i] && arr3[k] <= arr2[j])
                {
                    res[index] = arr3[k];
                    k++;
                }

                index++;
            }

            if (i == p)
            {
                while (j < q && k < r)
                {
                    if (arr2[j] <= arr3[k])
                    {
                        res[index] = arr2[j];
                        j++;
                    }
                    else
                    {
                        res[index] = arr3[k];
                        k++;
                    }

                    index++;
                }

                while (j < q)
                {
                    res[index] = arr2[j];
                    j++;
                    index++;
                }

                while (k < r)
                {
                    res[index] = arr3[k];
                    k++;
                    index++;
                }
            }

            else if (j == q)
            {
                while (i < p && k < r)
                {
                    if (arr1[i] <= arr3[k])
                    {
                        res[index] = arr1[i];
                        i++;

                    }
                    else
                    {
                        res[index] = arr3[k];
                        k++;
                    }

                    index++;
                }

                while (i < p)
                {
                    res[index] = arr1[i];
                    i++;
                    index++;
                }

                while (k < r)
                {
                    res[index] = arr3[k];
                    k++;
                    index++;
                }
            }

            else
            {
                while (i < p && j < q)
                {
                    if (arr1[i] <= arr2[j])
                    {
                        res[index] = arr1[i];
                        i++;

                    }
                    else
                    {
                        res[index] = arr2[j];
                        j++;
                    }

                    index++;
                }

                while (i < p)
                {
                    res[index] = arr1[i];
                    i++;
                    index++;
                }

                while (j < q)
                {
                    res[index] = arr2[j];
                    j++;
                    index++;
                }
            }

            return res;
        }

        // Lomuto Partion
        public static int LomutoPartition(int[] arr, int low, int high)
        {
            // we can also pick a random pivot and swap it with last element and can then use this algo.
            int pivot = arr[high];
            int i = low - 1;
            for (int j = low; j < high; j++)
            {
                if (arr[j] < pivot)
                {
                    i++;
                    Util.Swap(arr, i, j);
                }
            }

            Util.Swap(arr, i + 1, high);
            return i + 1;
        }

        // Hoare's Partion
        public static int HoareParition(int[] arr, int l, int h)
        {
            int pivot = arr[l];
            int i = l - 1, j = h + 1;
            while (true)
            {
                do
                {
                    i++;
                } while (arr[i] < pivot);

                do
                {
                    j--;
                } while (arr[j] > pivot);

                if (i >= j)
                {
                    return j;
                }

                Util.Swap(arr, i, j);
            }
        }

        // Quick Sort using Lomuto Partitioning
        public static void QuickSort_UsingLomuto(int[] arr, int l, int h)
        {
            if (l < h)
            {
                int q = LomutoPartition(arr, l, h);
                QuickSort_UsingLomuto(arr, l, q - 1);
                QuickSort_UsingLomuto(arr, q + 1, h);
            }
        }

        // Quick Sort using Hoare's Partitioning
        public static void QuickSort_UsingHoare(int[] arr, int l, int h)
        {
            if (l < h)
            {
                int q = HoareParition(arr, l, h);
                QuickSort_UsingHoare(arr, l, q); // this is where it is diffferent from Lomuto Partition
                QuickSort_UsingHoare(arr, q + 1, h);
            }
        }

        //Intersection of two sorted arrays. Print only distinct common elements
        public static void IntersectionOfTwoSortedArray(int[] arr1, int m, int[] arr2, int n)
        {
            int i = 0, j = 0;
            while (i < m && j < n)
            {
                if (i > 0 && arr1[i - 1] == arr1[i])
                {
                    i++;
                    continue;
                }

                if (j > 0 && arr2[j - 1] == arr2[j])
                {
                    j++;
                    continue;
                }

                if (arr1[i] < arr2[j])
                {
                    i++;
                }
                else if (arr2[j] < arr1[i])
                {
                    j++;
                }
                else
                {
                    Console.WriteLine(arr1[i]);
                    i++;
                    j++;
                }
            }
        }

        // Union of two sorted arrays. 
        public static void UnionOfTwoSortedArray(int[] arr1, int m, int[] arr2, int n)
        {
            int i = 0, j = 0;
            while (i < m && j < n)
            {
                if (i > 0 || arr1[i] == arr1[i - 1])
                {
                    i++;
                    continue;
                }

                if (j > 0 || arr2[j] == arr2[j - 1])
                {
                    j++;
                    continue;
                }

                if (arr1[i] < arr2[j])
                {
                    Console.WriteLine(arr1[i]);
                    i++;
                }
                else if (arr2[j] < arr1[i])
                {
                    Console.WriteLine(arr2[j]);
                    j++;
                }
                else
                {
                    Console.WriteLine(arr1[i]);
                    i++;
                    j++;
                }
            }

            while (i < m)
            {
                if (i == 0 || arr1[i] != arr1[i - 1])
                {
                    Console.WriteLine(arr1[i]);
                }

                i++;
            }

            while (j < n)
            {
                if (j == 0 || arr2[j] != arr2[j - 1])
                {
                    Console.WriteLine(arr2[j]);
                }

                j++;
            }
        }

        // Count inversions in an array.
        // A pair (a[i],a[j]) forms an inversion when i < j and a[i] > a[j]
        public static int CountInversions(int[] arr, int l, int r)
        {
            int res = 0;

            if (l < r)
            {
                int m = l + (r - l) / 2;
                res = res + CountInversions(arr, l, m);
                res = res + CountInversions(arr, m + 1, r);
                res = res + CountAndMerge(arr, l, m, r);
            }
            return res;
        }

        public static int CountAndMerge(int[] arr, int l, int m, int r)
        {
            int n1 = m - l + 1, n2 = r - m;
            int[] L = new int[n1];
            int[] R = new int[n2];

            int i = 0;
            for (; i < n1; i++)
            {
                L[i] = arr[l + i];
            }

            i = 0;
            for (; i < n2; i++)
            {
                R[i] = arr[m + 1 + i];
            }

            i = 0;
            int j = 0, res = 0, k = 0;
            while (i < n1 && j < n2)
            {
                if (L[i] < R[j])
                {
                    arr[k++] = L[i++];
                }
                else
                {
                    arr[k++] = R[j++];
                    res = res + (n1 - i);
                }
            }

            while (i < n1)
            {
                arr[k++] = L[i++];
            }

            while (j < n2)
            {
                arr[k++] = R[j++];
            }

            return res;
        }

        public static int FindKthSmallestElement(int[] arr, int n, int k)
        {
            int low = 0, high = n - 1;
            while (low <= high)
            {
                int p = LomutoPartition(arr, low, high);

                if (p == k - 1)
                {
                    return p;
                }

                if (p > k - 1)
                {
                    high = p - 1;
                }
                else
                {
                    low = p + 1;
                }
            }

            return -1;
        }

        public static int SegregatePositiveAndNegatives(int[] arr, int n)
        {
            // using hoare's partition
            int i = -1, j = n;
            while (true)
            {
                do
                {
                    i++;
                } while (arr[i] < 0);

                do
                {
                    j--;
                } while (arr[j] >= 0);

                if (i >= j)
                {
                    return j;
                }

                Util.Swap(arr, i, j);
            }
        }

        public static int SegregateEvenAndOdd(int[] arr, int n)
        {
            // using hoare's partition
            int i = -1, j = n;
            while (true)
            {
                do
                {
                    i++;
                } while (arr[i] % 2 != 0);

                do
                {
                    j--;
                } while (arr[j] % 2 == 0);

                if (i >= j)
                {
                    return j;
                }

                Util.Swap(arr, i, j);
            }
        }

        // also called binary sort
        public static int SegregateZerosAndOnes(int[] arr, int n)
        {
            // using hoare's partition
            int i = -1, j = n;
            while (true)
            {
                do
                {
                    i++;
                } while (i < n && arr[i] == 0);

                do
                {
                    j--;
                } while (j >= 0 && arr[j] == 1);

                if (i >= j)
                {
                    return j;
                }

                Util.Swap(arr, i, j);
            }
        }

        //Three way partition algorithm OR Dutch national flag algorithm
        // Sort array of 0, 1, and 2
        // three way partition when multiple occurrences of pivot exist
        // partition around range.
        public static void SortArrayOfZeroOnesAndTwos(int[] arr, int n)
        {
            int low = 0, high = n - 1, mid = 0;
            while (mid <= high)
            {
                switch (arr[mid])
                {
                    case 0:
                        Util.Swap(arr, low, mid);
                        low++;
                        mid++;
                        break;
                    case 1:
                        mid++;
                        break;
                    case 2:
                        Util.Swap(arr, mid, high);
                        high--;
                        break;
                    default:
                        break;
                }
            }
        }

        public static void PartionAroundRange(int[] arr, int n, int a, int b)
        {
            int low = 0, high = n - 1, mid = 0;
            while (mid <= high)
            {
                if (arr[mid] < a)
                {
                    Util.Swap(arr, low, mid);
                    low++;
                    mid++;
                }
                else if (arr[mid] <= b)
                {
                    mid++;
                }
                else
                {
                    Util.Swap(arr, mid, high);
                    high--;
                }
            }
        }

        public static bool FindTripletWithZeroSum(int[] arr, int n)
        {
            Array.Sort(arr);

            for (int i = 0; i < n - 2; i++)
            {
                int start = i + 1;
                int end = n - 1;

                while (start < end)
                {
                    int sum = arr[start] + arr[end];
                    if (sum == -arr[i])
                    {
                        return true;
                    }

                    if (sum > -arr[i])
                    {
                        end--;
                    }
                    else
                    {
                        start++;
                    }
                }
            }

            return false;
        }

        public static char[] ArrangeLettersInLexographicOrder(char[] seq)
        {
            int n = seq.Length;

            char[] res = new char[n];

            int[] c = new int[26];
            for (int i = 0; i < n; i++)
            {
                c[seq[i] - 'a']++;
            }

            for (int i = 1; i < 26; i++)
            {
                c[i] = c[i - 1] + c[i];
            }

            for (int i = n - 1; i >= 0; i--)
            {
                c[seq[i] - 'a']--;
                res[c[seq[i] - 'a']] = seq[i];

            }

            return res;
        }

        public static int MergeOverLappingInterval(Pair[] pairs, int n)
        {
            int res = 0;

            Array.Sort<Pair>(pairs, (p1, p2) => p1.Start - p2.Start);

            for (int i = 1; i < n; i++)
            {
                if (pairs[i].Start <= pairs[res].End)
                {
                    pairs[res].Start = Math.Min(pairs[i].Start, pairs[res].Start);
                    pairs[res].End = Math.Max(pairs[i].End, pairs[res].End);
                }
                else
                {
                    res++;
                    pairs[res] = pairs[i];
                }
            }

            return res;
        }

        //Given arrival and departure times of all trains that reach a railway station, the task is to find the minimum number of platforms required for the railway station so that no train waits.
        //Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
        //dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
        //Output: 3
        public static int MinimumPlatforms(int[] arr, int[] dep)
        {
            int n = arr.Length;
            Array.Sort(arr);
            Array.Sort(dep);

            int i = 1, j = 0, curr = 1, res = 1;

            while (i < n && j < n)
            {
                if (arr[i] <= dep[j])
                {
                    curr++;
                    i++;
                }
                else
                {
                    curr--;
                    j++;
                }

                res = Math.Max(res, curr);
            }

            return res;
        }
    }
}
