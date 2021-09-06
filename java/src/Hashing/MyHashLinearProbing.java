package Hashing;

import java.util.*;

public class MyHashLinearProbing {
    int bucketSize;
    int[] table;
    int size;

    int EMPTY = -1;
    int DELETED = -2;

    public MyHashLinearProbing(int b) {
        bucketSize = b;
        table = new int[b];
        Arrays.fill(table, EMPTY);
    }

    int Hash(int key) {
        return key % bucketSize;
    }

    public boolean Insert(int key) {
        if (size == bucketSize) {
            return false;
        }

        int probe = Hash(key);

        while (table[probe] != EMPTY && table[probe] != DELETED && table[probe] != key) {
            probe = Hash(probe + 1);
        }

        if (table[probe] == key)
            return false;

        table[probe] = key;
        size++;
        return true;
    }

    public boolean Search(int key) {
        if(size == 0) return false;

        int i = Hash(key);

        int probe = i;

        while (table[probe] != EMPTY) {
            if (table[probe] == key) {
                return true;
            }

            probe = Hash(probe + 1);

            if (probe == i) {
                return false;
            }
        }

        return false;
    }

    public boolean Delete(int key) {
        if(size == 0) return false;

        int i = Hash(key);
        int probe = i;

        while (table[probe] != EMPTY) {
            if (table[probe] == key) {
                table[probe] = DELETED;
                size--;
                return true;
            }

            probe = Hash(probe + 1);

            if (probe == i) {
                return false;
            }
        }

        return false;
    }
}
