using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Hashing
{
    public class MyHashTableLinearProbing
    {
        readonly int hashTblSize;
        const int Empty = -1;
        const int Deleted = -2;
        readonly int[] hashTable;

        public MyHashTableLinearProbing(int _hashTblSize)
        {
            hashTblSize = _hashTblSize;
            hashTable = new int[hashTblSize];
        }

        private int HashFunction(int key)
        {
            return key % hashTblSize;
        }

        public void Insert(int key)
        {
            int probe = HashFunction(key);
            if (hashTable[probe] == Empty || hashTable[probe] == Deleted)
            {
                hashTable[probe] = key;
            }
            else
            {
                int counter = 0;
                probe = (probe + 1) % hashTblSize;

                while (counter < hashTblSize && hashTable[probe] != Empty && hashTable[probe] != Deleted)
                {
                    probe = (probe + 1) % hashTblSize;
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
            int probe = HashFunction(key);
            int counter = 0;
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

                probe = (probe + 1) % hashTblSize;
                counter++;
            }

            return false;
        }

        public bool Delete(int key)
        {
            int probe = HashFunction(key);

            int counter = 0;
            while (counter < hashTblSize)
            {
                if (hashTable[probe] == key)
                {
                    hashTable[probe] = Deleted;

                    return true;
                }

                probe = (probe + 1) % hashTblSize;
                counter++;
            }

            return false;
        }
    }
}
