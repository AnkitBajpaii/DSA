package linkedList;

import java.util.HashMap;

public class LRUCache {
    public class Node {
        public int key;
        public int data;
        public Node prev;
        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.data = val;
            this.prev = this.next = null;
        }
    }

    Node head, tail;
    HashMap<Integer, Node> map;

    int size, capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>();
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node n = this.map.get(key);

        this.deleteNode(n);

        this.addToFront(n);

        return n.data;
    }

    public void set(int key, int value) {

        Node n;
        if (map.containsKey(key)) {
            n = this.map.get(key);
            n.data = value;

            this.deleteNode(n);
        } else {
            n = new Node(key, value);

            this.map.put(key, n);

            if (isCacheFull()) {
                this.map.remove(this.tail.key);

                this.deleteNode(this.tail);

                this.size--;
            }

            this.size++;
        }

        this.addToFront(n);
    }

    private boolean isCacheFull() {
        return this.size == this.capacity;
    }

    private void addToFront(Node n) {
        if (this.head == null) {
            this.head = n;
            this.tail = n;
        } else {
            n.next = this.head;
            this.head.prev = n;
            this.head = n;
        }
    }

    private void deleteNode(Node n) {
        Node prev = n.prev, next = n.next;
        if (prev != null) {
            prev.next = next;
        }

        if (next != null) {
            next.prev = prev;
        }

        n.prev = null;
        n.next = null;

        if (prev == null) {
            this.head = next;
        }

        if (next == null) {
            this.tail = prev;
        }
    }
}