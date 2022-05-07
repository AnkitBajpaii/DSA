package Trie;

import java.util.*;

public class TrieProblems {

    // Insert a given word into Trie
    public TrieNode Insert(String S, TrieNode root) {
        if (root == null) {
            root = new TrieNode();
        }

        TrieNode curr = root;

        for (int i = 0; i < S.length(); i++) {
            if (!curr.map.containsKey(S.charAt(i))) {
                curr.map.put(S.charAt(i), new TrieNode());
            }

            curr = curr.map.get(S.charAt(i));
        }

        curr.isEndOfWord = true;

        return root;
    }

    // Search for a given word into Trie
    public boolean Search(String S, TrieNode root) {
        TrieNode curr = root;

        for (int i = 0; i < S.length(); i++) {
            if (!curr.map.containsKey(S.charAt(i))) {
                return false;
            }

            curr = curr.map.get(S.charAt(i));
        }

        return curr.isEndOfWord;
    }

    // Delete a given word into Trie
    public TrieNode Delete(String S, TrieNode root) {
        if (root == null) {
            return null;
        }

        // Delete word: to do
        TrieNode curr = root;

        for (int i = 0; i < S.length(); i++) {
            if (!curr.map.containsKey(S.charAt(i))) {
                return root;
            }

            curr = curr.map.get(S.charAt(i));
        }

        if (curr.isEndOfWord) {
            curr.isEndOfWord = false;

        }

        return root;
    }

    /* Spelling Checker
    Problem Description
Given an array of words A (dictionary) and another array B (which contain some words).

You have to return the binary array (of length |B|) as the answer where 1 denotes that the word is present in the dictionary and 0 denotes it is not present.

Formally, for each word in B, you need to return 1 if it is present in Dictionary and 0 if not.

Such problems can be seen in real life when we work on any online editor (like Google Documnet), if the word is not valid it is underlined by a red line.

NOTE: Try to do this in O(n) time complexity.



Problem Constraints
1 <= |A| <= 1000

1 <= sum of all strings in A <= 50000

1 <= |B| <= 1000



Input Format
First argument is array of strings A.

First argument is array of strings B.



Output Format
Return the binary array of integers according to the given format.



Example Input
Input 1:

A = [ "hat", "cat", "rat" ]
B = [ "cat", "ball" ]
Input 2:

A = [ "tape", "bcci" ]
B = [ "table", "cci" ]


Example Output
Output 1:

[1, 0]
Output 2:

[0, 0]


Example Explanation
Explanation 1:

Only "cat" is present in the dictionary.
Explanation 2:

None of the words are present in the dictionary.
    */
    public ArrayList<Integer> SpellingChecker(ArrayList<String> A, ArrayList<String> B) {
        TrieNode root = new TrieNode();

        for (String s : A) {
            root = Insert(s, root);
        }

        ArrayList<Integer> res = new ArrayList<Integer>();

        for (String s : B) {
            if (Search(s, root)) {
                res.add(1);
            } else {
                res.add(0);
            }
        }

        return res;

    }

    /* Shortest Unique Prefix
    Problem Description
Given a list of N words, find the shortest unique prefix to represent each word in the list.

NOTE: Assume that no word is the prefix of another. In other words, the representation is always possible



Problem Constraints
1 <= Sum of length of all words <= 106



Input Format
First and only argument is a string array of size N.



Output Format
Return a string array B where B[i] denotes the shortest unique prefix to represent the ith word.



Example Input
Input 1:

 A = ["zebra", "dog", "duck", "dove"]
Input 2:

A = ["apple", "ball", "cat"]


Example Output
Output 1:

 ["z", "dog", "du", "dov"]
Output 2:

 ["a", "b", "c"]


Example Explanation
Explanation 1:

 Shortest unique prefix of each word is:
 For word "zebra", we can only use "z" as "z" is not any prefix of any other word given.
 For word "dog", we have to use "dog" as "d" and "do" are prefixes of "dov".
 For word "du", we have to use "du" as "d" is prefix of "dov" and "dog".
 For word "dov", we have to use "dov" as "d" and do" are prefixes of "dog".  
 
Explanation 2:

 "a", "b" and c" are not prefixes of any other word. So, we can use of first letter of each to represent.
    */
    class ShortestUniquePrefixSolution {

        class TrieNode {
            public HashMap<Character, TrieNode> map;
            public boolean isEndOfWord;
    
            public int count;
    
            public TrieNode() {
                map = new HashMap<Character, TrieNode>();
                isEndOfWord = false;
                count = 0;
            }
        }
    
        TrieNode Insert(String S, TrieNode root) {
            if (root == null) {
                root = new TrieNode();
            }
    
            TrieNode curr = root;
    
            for (int i = 0; i < S.length(); i++) {
                if (!curr.map.containsKey(S.charAt(i))) {
                    curr.map.put(S.charAt(i), new TrieNode());
                }
    
                curr = curr.map.get(S.charAt(i));
                curr.count++;
            }
    
            curr.isEndOfWord = true;
    
            return root;
        }

        public ArrayList<String> ShortestUniquePrefix(String[] A) {
        
            ArrayList<String> res = new ArrayList<String>();

            TrieNode root = new TrieNode();
    
            for(String s : A)
            {
                root = Insert(s, root);
            }
    
            for(String s : A)
            {
                TrieNode curr = root;
    
                String ans = "";
                for(int i=0; i<s.length(); i++)
                {
                    if(curr.map.get(s.charAt(i)).count == 1)
                    {
                        ans = ans + s.charAt(i);

                        res.add(ans);
                        break;                   
                    }

                    ans = ans + s.charAt(i);
    
                    curr = curr.map.get(s.charAt(i));
                }
            }
    
            return res;
        }

    }

    /* Maximum XOR
    Problem Description

Given an array of integers A, find and return the maximum result of A[i] XOR A[j], where i, j are the indexes of the array.



Problem Constraints

1 <= length of the array <= 100000
0 <= A[i] <= 109



Input Format

The only argument given is the integer array A.



Output Format

Return an integer denoting the maximum result of A[i] XOR A[j].



Example Input

Input 1:

 A = [1, 2, 3, 4, 5]
Input 2:

 A = [5, 17, 100, 11]


Example Output

Output 1:

 7
Output 2:

 117


Example Explanation

Explanation 1:

 Maximum XOR occurs between element of indicies(0-based) 1 and 4 i.e. 2 ^ 5 = 7.
Explanation 2:

 Maximum XOR occurs between element of indicies(0-based) 1 and 2 i.e. 17 ^ 100 = 117.
    */
    public class MaxXORSolution1 {
        class TrieNode {
            public boolean isEnd;
            public HashMap<Character, TrieNode> map;

            public TrieNode() {
                map = new HashMap<Character, TrieNode>();
                isEnd = false;
            }
        }

        TrieNode insert(String S, TrieNode root) {
            if (root == null) {
                root = new TrieNode();
            }

            TrieNode curr = root;

            for (int i = 0; i < S.length(); i++) {
                if (!curr.map.containsKey(S.charAt(i))) {
                    curr.map.put(S.charAt(i), new TrieNode());
                }

                curr = curr.map.get(S.charAt(i));
            }

            curr.isEnd = true;

            return root;

        }

        public int solve(int[] A) {
            int ans = 0;

            TrieNode root = new TrieNode();

            for (int i = 0; i < A.length; i++) {
                int n = A[i];

                String S = "";
                for (int j = 31; j >= 0; j--) {
                    int k = n >> j;
                    if ((k & 1) > 0)
                        S += "1";
                    else
                        S += "0";
                }

                insert(S, root);
            }

            for (int i = 0; i < A.length; i++) {
                int n = A[i];

                String S = "";
                TrieNode curr = root;

                for (int j = 31; j >= 0; j--) {
                    int k = n >> j;
                    if ((k & 1) > 0) {
                        if (curr.map.containsKey('0')) {
                            S += "0";
                            curr = curr.map.get('0');
                        } else {
                            S += "1";
                            curr = curr.map.get('1');
                        }

                    } else {
                        if (curr.map.containsKey('1')) {
                            S += "1";
                            curr = curr.map.get('1');
                        } else {
                            S += "0";
                            curr = curr.map.get('0');
                        }
                    }
                }

                int m = Integer.parseInt(S, 2);

                ans = Math.max(ans, n ^ m);
            }

            return ans;
        }
    }

}
