package Hashing;

import java.util.*;

public class MyHashDoubleHashing {
    int bucketSize;
    int[] table;
    int size;

    int EMPTY = -1;
    int DELETED = -2;

    public MyHashDoubleHashing(int b) {
        bucketSize = b;
        table = new int[b];
        Arrays.fill(table, EMPTY);
    }

    int H1(int key) {
        return key % bucketSize;
    }

    int H2(int key) {
        return 6 - (key % 6); // If H2(key) & hashTable size is relatively prime, then we will always find the
                              // free slot, if there is a free slot.
    }

    public boolean Insert(int key) {
        if (size == bucketSize) {
            return false;
        }

        int i = 0;
        int probe = H1(key);

        while (table[probe] != EMPTY && table[probe] != DELETED && table[probe] != key) {
            i++;
            if (i == bucketSize) {
                return false;
            }
            probe = H1(key) + i * H2(key);
        }

        if (table[probe] == key)
            return false;

        table[probe] = key;
        size++;
        return true;
    }

    public boolean Search(int key) {
        if (size == 0)
            return false;

        int probe = H1(key);
        int i = 0;
        
        while (table[probe] != EMPTY) {
            if (table[probe] == key) {
                return true;
            }

            i++;
            if (size == bucketSize) {
                return false;
            }

            probe = H1(key) + i * H2(key);
        }

        return false;
    }

    public boolean Delete(int key) {
        if (size == 0)
            return false;

        int probe = H1(key);
        int i = 0;

        while (table[probe] != EMPTY) {
            if (table[probe] == key) {
                table[probe] = DELETED;
                size--;
                return true;
            }

            i++;
            if (size == bucketSize) {
                return false;
            }

            probe = H1(key) + i * H2(key);
        }

        return false;
    }
}
