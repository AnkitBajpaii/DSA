using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Hashing
{
    public class MyHashTableChaining
    {
        int hashTblSize;
        List<List<int>> hashTable;
        public MyHashTableChaining(int _hashTblSize)
        {
            hashTblSize = _hashTblSize;
            hashTable = new List<List<int>>();

            for (int i = 0; i < hashTblSize; i++)
            {
                hashTable.Add(new List<int>());
            }
        }

        private int HashFunction(int key)
        {
            return key % hashTblSize;
        }

        public void Insert(int key)
        {
			// if key is already present then do not insert
			if(Search(key)) return;
			
            int slotIdx = HashFunction(key);

            hashTable[slotIdx].Add(key);
        }

        public bool Search(int key)
        {
            int slotIdx = HashFunction(key);

            for (int i = 0; i < hashTable[slotIdx].Count; i++)
            {
                if(hashTable[slotIdx][i] == key)
                {
                    return true;
                }
            }

            return false;
        }

        public void Delete(int key)
        {
            int slotIdx = HashFunction(key);

            hashTable[slotIdx].Remove(key);
        }
    }
}
