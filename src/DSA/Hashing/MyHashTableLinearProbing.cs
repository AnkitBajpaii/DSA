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

        public bool Insert(int key)
        {
            int probe = HashFunction(key);

            if (hashTable[probe] == Empty || hashTable[probe] == Deleted)
            {
                hashTable[probe] = key;
                return true;
            }
            else
            {
                int counter = 0;
                probe = (probe + 1) % hashTblSize;

                while (counter < hashTblSize && hashTable[probe] != Empty && hashTable[probe] != Deleted)
                {
                    probe = (probe + 1) % hashTblSize;
                    if (hashTable[probe] == key)
                    {
                        return false;
                    }

                    counter++;
                }

                if (counter < hashTblSize)
                {
                    hashTable[probe] = key;
                    return true;
                }

                return false;
            }
        }

        public bool Search(int key)
        {
            int probe = HashFunction(key);

            int counter = 0;
            while (counter < hashTblSize)
            {
                if (hashTable[probe] == key)
                {
                    return true;
                }

                if (hashTable[probe] == Empty)
                {
                    return false;
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

                if (hashTable[probe] == Empty)
                {
                    return false;
                }

                probe = (probe + 1) % hashTblSize;
                counter++;
            }

            return false;
        }
    }
}
