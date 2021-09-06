package Hashing;

import java.util.*;

public class MyHashChaining {

    int bucketSize;
    ArrayList<LinkedList<Integer>> table;

    public MyHashChaining(int b) {
        bucketSize = b;
        table = new ArrayList<LinkedList<Integer>>(b);

        for (int i = 0; i < b; i++) {
            table.add(new LinkedList<Integer>());
        }
    }

    int Hash(int key) {
        return key % bucketSize;
    }

    public void Insert(int key) {

        if (!Search(key)) {
            int i = Hash(key);
            table.get(i).add(key);
        }
    }

    public boolean Search(int key) {

        int i = Hash(key);

        return table.get(i).contains(key);
    }

    public void Delete(int key) {

        int i = Hash(key);

        table.get(i).remove(key);
    }
}
