using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Hashing
{
    public class MyHashTableDoubleHashing
    {
        readonly int hashTblSize;
        const int Empty = -1;
        const int Deleted = -2;
        readonly int[] hashTable;

        public MyHashTableDoubleHashing(int _hashTblSize)
        {
            hashTblSize = _hashTblSize;
            hashTable = new int[hashTblSize];
        }

        private int HashFunction1(int key)
        {
            return key % hashTblSize;
        }

        private int HashFunction2(int key)
        {
            return 6 - key % 6;
        }

        public void Insert(int key)
        {
            int probe = HashFunction1(key);

            if (hashTable[probe] == Empty || hashTable[probe] == Deleted)
            {
                hashTable[probe] = key;
            }
            else
            {
                int offset = HashFunction2(key);

                int counter = 0;
                probe = (probe + offset) % hashTblSize;

                while (counter < hashTblSize && hashTable[probe] != Empty && hashTable[probe] != Deleted)
                {
                    probe = (probe + offset) % hashTblSize;
                    counter++;
                }

                if (counter < hashTblSize)
                {
                    hashTable[probe] = key;
                }
            }
        }

        public bool Search(int key)
        {
            int probe = HashFunction1(key);
            int counter = 0;
            int offset = HashFunction2(key);
            while (counter < hashTblSize)
            {
                if (hashTable[probe] == Empty)
                {
                    return false;
                }

                if (hashTable[probe] == key)
                {
                    return true;
                }

                probe = (probe + offset) % hashTblSize;
                counter++;
            }

            return false;
        }

        public bool Delete(int key)
        {
            int probe = HashFunction1(key);

            int counter = 0;

            int offset = HashFunction2(key);

            while (counter < hashTblSize)
            {
                if (hashTable[probe] == key)
                {
                    hashTable[probe] = Deleted;

                    return true;
                }

                probe = (probe + offset) % hashTblSize;
                counter++;
            }

            return false;
        }
    }
}