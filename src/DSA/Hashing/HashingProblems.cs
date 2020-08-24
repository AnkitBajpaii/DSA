using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DSA.Hashing
{
    class Node
    {
        public int FirstRepeatingIndex { get; set; }
        public int Count { get; set; }
    }

    public static class HashingProblems
    {
        //Separate chaining technique in hashing allows to us to use a linked list at each hash slot to handle the problem of collisions. That is, every slot of the hash table is a linked list, so whenever a collision occurs, the element can be appened as a node to the linked list at the slot.
        public static List<List<int>> SeparateChaining(int[] arr, int n, int hashSize)
        {
            List<List<int>> s = new List<List<int>>(hashSize);

            for (int i = 0; i < hashSize; i++)
            {
                s.Add(new List<int>());
            }

            for (int i = 0; i < n; i++)
            {
                int hashKey = arr[i] % hashSize;

                s[hashKey].Add(arr[i]);
            }

            return s;
        }

        //Linear probing is a collision handling technique in hashing.Linear probing says that whenever a collision occurs, search for the immediate next position.
        //Given an array of integers and a hash table size. Fill the array elements into a hash table using Linear Probing to handle collisions.
        public static int[] LinearProbing(int hash_size, int[] arr, int N)
        {
            int[] hash_table = new int[hash_size];

            for (int i = 0; i < hash_size; i++)
            {
                hash_table[i] = -1;
            }

            for (int i = 0; i < N; i++)
            {
                int probe = arr[i] % hash_size;
                if (hash_table[probe] == -1)//If the place is empty then insert arr[i] at that place
                {
                    hash_table[probe] = arr[i];
                }
                else
                {
                    //else linearly move from the filled position to find an empty place.
                    //Mod by hashSize ensures that we remain inside the hashTable
                    int counter = 0;
                    probe = (arr[i] + 1) % hash_size;
                    while (counter < hash_size && hash_table[probe] != -1)
                    {
                        probe = (probe + 1) % hash_size;
                        counter++;
                    }
                    if (counter < hash_size)//If we find a position where arr[i] can be inserted then we insert
                        hash_table[probe] = arr[i];
                }
            }
            return hash_table;
        }

        public static int[] quadraticProbing(int hash_size, int[] arr, int N)
        {
            int[] hash_table = new int[hash_size];

            for (int i = 0; i < hash_size; i++)
            {
                hash_table[i] = -1;
            }

            for (int i = 0; i < N; i++)
            {
                int probe = arr[i] % hash_size;
                if (hash_table[probe] == -1)//If the place is empty then insert arr[i] at that place
                {
                    hash_table[probe] = arr[i];
                }
                else
                {
                    int j = 1;
                    while (hash_table[(probe + j * j) % hash_size] != -1)
                    {
                        j++;
                    }
                    hash_table[(probe + j * j) % hash_size] = arr[i];
                }
            }

            return hash_table;
        }

        public static long CountNonRepeated(int[] arr, int n)
        {
            Dictionary<int, int> hashTable = new Dictionary<int, int>();
            for (int i = 0; i < n; i++)
            {
                if (hashTable.ContainsKey(arr[i]))
                {
                    hashTable[arr[i]]++;
                }
                else
                {
                    hashTable.Add(arr[i], 1);
                }
            }

            return hashTable.Where(kvp => kvp.Value == 1).LongCount();
        }

        public static int FirstRepeatingElement(int[] arr, int n)
        {
            Dictionary<int, Node> hashTable = new Dictionary<int, Node>();

            for (int i = 0; i < n; i++)
            {
                if (hashTable.ContainsKey(arr[i]))
                {
                    hashTable[arr[i]].Count++;
                }
                else
                {
                    hashTable.Add(arr[i], new Node { FirstRepeatingIndex = i + 1, Count = 1 });
                }
            }


            Node res = new Node { Count = Int32.MaxValue, FirstRepeatingIndex = Int32.MaxValue };
            foreach (var kvp in hashTable)
            {
                if (kvp.Value.Count > 1)
                {
                    if (kvp.Value.FirstRepeatingIndex < res.FirstRepeatingIndex)
                    {
                        res = kvp.Value;
                    }
                }
            }

            return res.FirstRepeatingIndex == Int32.MaxValue ? -1 : res.FirstRepeatingIndex;
        }

        public static int NumberofElementsInIntersection(int[] a, int[] b, int n, int m)
        {
            HashSet<int> s = new HashSet<int>();

            for (int i = 0; i < n; i++)
            {
                s.Add(a[i]);
            }

            int res = 0;
            for (int i = 0; i < m; i++)
            {
                if (s.Contains(b[i]))
                {
                    res++;
                    s.Remove(b[i]);
                }
            }
            return res;
        }

        public static int NumberofElementsInUnion(int[] a, int n, int[] b, int m)
        {
            HashSet<int> s = new HashSet<int>();

            for (int i = 0; i < n; i++)
            {
                s.Add(a[i]);
            }


            for (int i = 0; i < m; i++)
            {
                s.Add(b[i]);
            }
            return s.Count;
        }

        public static int CheckIfPairWithGivenSumExist(int[] arr, int sizeOfArray, int sum)
        {
            HashSet<int> s = new HashSet<int>();

            for (int i = 0; i < sizeOfArray; i++)
            {
                if (s.Contains(sum - arr[i]))
                {
                    return 1;
                }

                s.Add(arr[i]);
            }

            return 0;

        }

        public static bool CheckSubArrayWithZeroSum(int[] arr, int n)
        {
            HashSet<int> s = new HashSet<int>();
            int preSum = 0;
            for (int i = 0; i < n; i++)
            {
                preSum += arr[i];
                if (preSum == 0)
                {
                    return true;
                }

                if (s.Contains(preSum))
                {
                    return true;
                }

                s.Add(preSum);
            }

            return false;
        }

        //Given an unsorted array of integers and a sum. The task is to count the number of subarray which adds to the given sum.
        //N = 5
        //arr[] = {10,2,-2,-20,10}
        //sum = -10
        //Output: 3
        public static int CountSubArrayWithGivenSum(int[] arr, int n, int sum)
        {
            Dictionary<int, int> m = new Dictionary<int, int>();
            int preSum = 0, count = 0;

            for (int i = 0; i < n; i++)
            {
                preSum = preSum + arr[i];
                if (preSum == sum)
                {
                    count++;
                }

                if (m.ContainsKey(preSum - sum))
                {
                    count = count + m[preSum - sum];
                }

                if (m.ContainsKey(preSum))
                {
                    m[preSum]++;
                }
                else
                {
                    m.Add(preSum, 1);
                }
            }

            return count;
        }

        //Given an array of distinct integers, find all the pairs having negative and positive value of a number that exists in the array.
        //Input:
        //N = 8
        //arr[] = {1,3,6,-2,-1,-3,2,7}
        //Output: -1 1 -3 3 -2 2
        //Explanation: 1, 3 and 2 are present
        //pairwirse postive and negative. 6 and
        //7 have no pair.
        public static List<int> FindPairs(int[] arr, int n)
        {
            List<int> res = new List<int>();

            Dictionary<int, bool> m = new Dictionary<int, bool>();
            for (int i = 0; i < n; i++)
            {
                if (!m.ContainsKey(-arr[i]))
                {
                    m.Add(arr[i], false);
                }
                else
                {
                    m.Add(arr[i], true);
                }
            }

            foreach (var kvp in m)
            {
                int key = kvp.Key;
                bool value = kvp.Value;

                if (value)
                {
                    if (key < 0)
                    {
                        key = key * -1;
                    }

                    res.Add(-key);
                    res.Add(key);
                }
            }

            return res;
        }

        public static int LongestSubArrayWithGivenSum(int[] arr, int n, int sum)
        {
            Dictionary<int, int> map = new Dictionary<int, int>();
            int res = 0, preSum = 0;
            for (int i = 0; i < n; i++)
            {
                preSum = preSum + arr[i];
                if (preSum == sum)
                {
                    res = i + 1;
                }

                if (map.ContainsKey(preSum - sum))
                {
                    res = Math.Max(res, i - map[preSum - sum]);
                }

                if (!map.ContainsKey(preSum))
                {
                    map.Add(preSum, i);
                }
            }

            return res;
        }

        // Given binary array, count sub array with equal no of 0 and 1's.
        public static int CountSubarrWithEqualZeroAndOne(int[] arr, int n)
        {
            Dictionary<int, int> m = new Dictionary<int, int>();
            int preSum = 0, count = 0;
            for (int i = 0; i < n; i++)
            {
                preSum = preSum + ((arr[i] == 0) ? -1 : 1);

                if (preSum == 0)
                {
                    count++;
                }

                if (m.ContainsKey(preSum))
                {
                    count = count + m[preSum];

                }

                if (m.ContainsKey(preSum))
                {
                    m[preSum]++;
                }
                else
                {
                    m.Add(preSum, 1);
                }
            }
            return count;
        }

        //Given two integer arrays. Sort the first array such that all the relative positions of the elements in the first array are the same as the elements in the second array.
        //See example for better understanding.
        //Input:
        //N = 11, M = 4
        //A1[] = {2,1,2,5,7,1,9,3,6,8,8}
        //A2[] = {2,1,8,3}
        //Output: 2 2 1 1 8 8 3 5 6 7 9
        public static void RelativeSorting(int[] A1, int N, int[] A2, int M)
        {
            Dictionary<int, int> map = new Dictionary<int, int>();

            for (int i = 0; i < N; i++)
            {
                if (map.ContainsKey(A1[i]))
                {
                    map[A1[i]]++;
                }
                else
                {
                    map.Add(A1[i], 1);
                }
            }

            int index = 0;
            for (int i = 0; i < M; i++)
            {
                if (map.ContainsKey(A2[i]))
                {
                    int count = map[A2[i]];
                    for (int j = 1; j <= count; j++)
                    {
                        A1[index] = A2[i];
                        index++;
                    }

                    map.Remove(A2[i]);
                }
            }

            List<int> al = new List<int>();

            foreach (var kvp in map)
            {
                int key = kvp.Key;
                int count = kvp.Value;
                for (int i = 1; i <= count; i++)
                {
                    al.Add(key);
                }
            }

            al.Sort();

            foreach (int n in al)
            {
                A1[index] = n;
                index++;
            }
        }

        //Given an array of positive integers. Find the length of the longest sub-sequence such that elements in the subsequence are consecutive integers, the consecutive numbers can be in any order.
        //Input:
        //N = 7
        //a[] = {2,6,1,9,4,5,3}
        //Output: 6
        //Explanation: The consecutive numbers
        //here are 1, 2, 3, 4, 5, 6. These 6 
        //numbers form the longest consecutive
        //subsquence.
        public static int FindLongestConseqSubseq(int[] arr, int N)
        {
            HashSet<int> s = new HashSet<int>();

            for (int i = 0; i < N; i++)
            {
                s.Add(arr[i]);
            }

            int res = 0;
            for (int i = 0; i < N; i++)
            {
                if (!s.Contains(arr[i] - 1))
                {
                    int j = arr[i];
                    while (s.Contains(j))
                    {
                        j++;
                    }
                    if (res < (j - arr[i]))
                    {
                        res = (j - arr[i]);
                    }
                }
            }
            return res;
        }
    }
}
