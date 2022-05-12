package Trie;

import java.util.HashMap;

public class TrieNode {
    public HashMap<Character, TrieNode> map;
    public boolean isEndOfWord;
    public int count;
    public int weight;

    public TrieNode() {
        map = new HashMap<Character, TrieNode>();
        isEndOfWord = false;
    }
}
