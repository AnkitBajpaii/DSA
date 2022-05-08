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
    public class MaximumXORSolution {
        class TrieNode {
            public TrieNode left;
            public TrieNode right;

            public TrieNode() {
                left = null;
                right = null;
            }
        }

        public int solve(int[] A) {
            int ans = 0;

            TrieNode root = new TrieNode();

            for (int i = 0; i < A.length; i++) {
                int n = A[i];

                TrieNode curr = root;

                for (int j = 31; j >= 0; j--) {

                    int k = (n >> j) & 1;

                    if (k == 1) {
                        if (curr.right == null) {
                            curr.right = new TrieNode();
                        }

                        curr = curr.right;

                    } else {
                        if (curr.left == null) {
                            curr.left = new TrieNode();
                        }

                        curr = curr.left;
                    }
                }
            }

            for (int i = 0; i < A.length; i++) {
                int n = A[i];

                int x = 0;

                TrieNode curr = root;

                for (int j = 31; j >= 0; j--) {

                    int k = (n >> j) & 1;

                    if (k == 1) {
                        if (curr.left != null) {
                            curr = curr.left;
                        } else {
                            curr = curr.right;
                            x += 1 << j;
                        }
                    } else {
                        if (curr.right != null) {
                            curr = curr.right;
                            x += 1 << j;
                        } else {
                            curr = curr.left;
                        }
                    }
                }

                ans = Math.max(ans, n ^ x);
            }

            return ans;
        }
    }

    /* Contact Finder
    Problem Description

We want to make a custom contacts finder applications as part of our college project. The application must perform two types of operations:

Type 1: Add name B[i] ,denoted by 0 in vector A where B[i] is a string in vector B denoting a contact name. This must store B[i] as a new contact in the application.

Type 2: Find partial for B[i] ,denoted by 1 in vector A where B[i] is a string in vector B denoting a partial name to search the application for. It must count the number of contacts starting with B[i].

You have been given sequential add and find operations. You need to perform each operation in order.

And return as an array of integers, answers for each query of type 2(denoted by 1 in A) .



Problem Constraints

1 <= |A| <= 10000

1 <= |length of strings in B| <= 10



Input Format

First argument is the vector A, which denotes the type of query.

Second argument is the vector B, which denotes the string for corresponding query.



Output Format

Return an array of integers, denoting answers for each query of type 1.



Example Input

Input 1:

A = [0, 0, 1, 1]
B = ["hack", "hacker", "hac", "hak"]
Input 2:

A = [0, 1]
B = ["abcde", "abc"]


Example Output

Output 1:

 
[2, 0]
Output 2:

[1]


Example Explanation

Explanation 1:

 
We perform the following sequence of operations:
Add a contact named "hack".
Add a contact named "hacker".
Find the number of contact names beginning with "hac". There are currently two contact names in the application and both of them start with "hac", so we have 2.
Find the number of contact names beginning with "hak". There are currently two contact names in the application but neither of them start with "hak", so we get0.
Explanation 2:

 
Add "abcde"
Find words with prefix "abc". We have answer as 1.
    */
    public class ContactFinderSolution {
        class TrieNode {
            public HashMap<Character, TrieNode> map;
            public boolean isEnd;
            public int count;

            public TrieNode() {
                this.map = new HashMap<Character, TrieNode>();
                this.isEnd = false;
                this.count = 0;
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
                curr.count++;
            }

            curr.isEnd = true;

            return root;
        }

        int getCountOfStringBeginingWith(String S, TrieNode root) {
            TrieNode curr = root;

            for (int i = 0; i < S.length(); i++) {
                if (!curr.map.containsKey(S.charAt(i))) {
                    return 0;
                }

                curr = curr.map.get(S.charAt(i));
            }

            return curr.count;
        }

        public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<String> B) {

            TrieNode root = new TrieNode();

            ArrayList<Integer> res = new ArrayList<Integer>();

            for (int i = 0; i < B.size(); i++) {
                if (A.get(i) == 0) {
                    root = insert(B.get(i), root);
                } else {
                    int count = getCountOfStringBeginingWith(B.get(i), root);

                    res.add(count);
                }
            }

            return res;
        }
    }

    /* Modified Search
    Problem Description

Given two arrays of strings A of size N and B of size M.

Return a binary string C where C[i] = '1' if B[i] can be found in dictionary A using exactly one modification in B[i], Else C[i] = '0'.

NOTE: modification is defined as converting a character into another character.



Problem Constraints

1 <= N <= 30000

1 <= M <= 2500

1 <= length of any string either in A or B <= 20

strings contains only lowercase alphabets



Input Format

First argument is the string arrray A.

Second argument is the string array B.



Output Format

Return a binary string C where C[i] = '1' if B[i] can be found in dictionary A using one modification in B[i], Else C[i] = '0'.



Example Input

Input 1:

 A = [data, circle, cricket]
 B = [date, circel, crikket, data, circl]
Input 2:

 A = [hello, world]
 B = [hella, pello, pella]


Example Output

Output 1:

 "10100"
Output 2:

 "110"


Example Explanation

Explanation 1:

 1. date = dat*(can be found in A)
 2. circel =(cannot be found in A using exactly one modification)
 3. crikket = cri*ket(can be found in A)
 4. data = (cannot be found in A using exactly one modification)
 5. circl = (cannot be found in A using exactly one modification)
Explanation 2:

 Only pella cannot be found in A using only one modification.
    */

}
