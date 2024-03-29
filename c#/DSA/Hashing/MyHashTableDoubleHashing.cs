﻿using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Hashing
{
    public class MyHashTableDoubleHashing
    {
		int currSize;
        readonly int hashTblSize;
        const int Empty = -1;
        const int Deleted = -2;
        readonly int[] hashTable;
        const int PRIME = 11;

        public MyHashTableDoubleHashing(int _hashTblSize)
        {
            hashTblSize = _hashTblSize;
            hashTable = new int[hashTblSize];
			currSize = 0;
        }

		public bool IsFull(){
			return currSize == size;
		}
		
        private int HashFunction1(int key)
        {
            return key % hashTblSize;
        }

        private int HashFunction2(int key)
        {
            return PRIME - key % PRIME; // if hashTblSize and HashFunction2 are relatively prime then algo guarantees a free slot, if there is a free slot
        }


        public bool Insert(int key)
        {
			if(IsFull()) return;
			
            int probe = HashFunction1(key);

            if (hashTable[probe] == Empty || hashTable[probe] == Deleted)
            {
                hashTable[probe] = key;
				currSize++;
                return true;
            }
            else
            {
                int offset = HashFunction2(key);

                int counter = 0;
                probe = (probe + offset) % hashTblSize;

                while (hashTable[probe] != Empty && hashTable[probe] != Deleted)
                {
                    probe = (probe + offset) % hashTblSize;

                    if (hashTable[probe] == key)
                    {
                        return false;
                    }

                    counter++;
                }

                hashTable[probe] = key;
				currSize++;

                return true;
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