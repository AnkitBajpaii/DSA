using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Sorting
{
    public class SortingProblems
    {
        void Swap(int[] A, int i, int j)
        {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        public static void InsertionSort(int[] A, int n)
        {
            for (int i = 1; i < n; i++)
            {
                int key = A[i];

                int j = i;
                while (j > 0 && A[j - 1] > key)
                {
                    A[j] = A[j - 1];
                    j--;
                }

                A[j] = key;
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

            int i = 0;
            for (i = 0; i < n1; i++)
            {
                L[i] = arr[l + i];
            }

            i = 0;
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

        //Intersection of two sorted arrays. Print only distinct common elements
        public static void IntersectionOfTwoSortedArray(int[] a, int m, int[] b, int n)
        {
            int i = 0, j = 0;
            while (i < m && j < n)
            {
                if (i > 0 && a[i - 1] == a[i])
                {
                    i++;
                    continue;
                }

                if (a[i] < b[j])
                {
                    i++;
                }
                else if (b[j] < a[i])
                {
                    j++;
                }
                else
                {
                    Console.WriteLine(a[i]);
                    i++;
                    j++;
                }
            }
        }

        // Union of two sorted arrays. 
        public static void UnionOfTwoSortedArray(int[] a, int m, int[] b, int n)
        {
            int i = 0, j = 0;
            while (i < m && j < n)
            {
                if (i > 0 || a[i] == a[i - 1])
                {
                    i++;
                    continue;
                }

                if (j > 0 || b[j] == b[j - 1])
                {
                    j++;
                    continue;
                }

                if (a[i] < b[j])
                {
                    Console.WriteLine(a[i]);
                    i++;
                }
                else if (b[j] < a[i])
                {
                    Console.WriteLine(b[j]);
                    j++;
                }
                else
                {
                    Console.WriteLine(a[i]);
                    i++;
                    j++;
                }
            }

            while (i < m)
            {
                if (i == 0 || a[i] != a[i - 1])
                {
                    Console.WriteLine(a[i]);
                }

                i++;
            }

            while (j < n)
            {
                if (j == 0 || b[j] != b[j - 1])
                {
                    Console.WriteLine(b[j]);
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

        // Lomuto Partion
        public static int LomutoPartition(int[] arr, int l, int h)
        {
            int pivot = arr[h];
            int i = l - 1;
            for (int j = l; j <= h - 1; j++)
            {
                if (arr[j] < pivot)
                {
                    i++;
                    Util.Swap(arr, i, j);
                }
            }

            Util.Swap(arr, i + 1, h);
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
    }
}
