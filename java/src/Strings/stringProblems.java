package Strings;

import java.util.*;

public class stringProblems {

    /*
     * Toggle characters in string without using If Else condition Capital -> Small
     * & Small -> Capital
     */
    public String Toggle(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            res = res + (char) (s.charAt(i) ^ 32);
        }

        return res;
    }

    /*
     * Reverse the String Given a string A of size N. Return the string A after
     * reversing the string word by word. NOTE: A sequence of non-space characters
     * constitutes a word. Your reversed string should not contain leading or
     * trailing spaces, even if it is present in the input string. If there are
     * multiple spaces between words, reduce them to a single space in the reversed
     * string.
     */
    String reverse(String s, int start, int end) {

        char[] ch = new char[end - start + 1];
        for (int i = 0; i < ch.length; i++) {
            ch[i] = s.charAt(start + i);
        }

        int i = 0, j = ch.length - 1;
        while (i < j) {
            char t = ch[i];
            ch[i] = ch[j];
            ch[j] = t;
            i++;
            j--;
        }

        return String.valueOf(ch);
    }

    public String reverseWordByWord(String A) {
        int s = 0, n = A.length();
        StringBuilder sb = new StringBuilder();

        for (int e = 1; e < n; e++) {

            if (A.charAt(e) == ' ') {
                sb.append(reverse(A, s, e - 1));
                sb.append(" ");
                s = e + 1;
            }
        }

        sb.append(reverse(A, s, n - 1));

        String res = reverse(sb.toString(), 0, n - 1);

        return res.trim();
    }

    /*
     * You are given a function to_upper() consisting of a character array A.
     * 
     * Convert each charater of A into Uppercase character if it exists. If the
     * Uppercase of a character does not exist, it remains unmodified. The lowercase
     * letters from a to z is converted to uppercase letters from A to Z
     * respectively.
     * 
     * Return the uppercase version of the given character array.
     */
    public char[] to_upper(char[] A) {
        char[] res = new char[A.length];
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 97 && A[i] <= 122) {
                res[i] = (char) (A[i] - 32);
            } else {
                res[i] = A[i];
            }
        }

        return res;
    }

    /*
     * Closest Palindrome Groot has a profound love for palindrome which is why he
     * keeps fooling around with strings. A palindrome string is one that reads the
     * same backward as well as forward. Given a string A of size N consisting of
     * lowercase alphabets, he wants to know if it is possible to make the given
     * string a palindrome by changing exactly one of its character.
     */
    public String CanMakePalindromeByChangingExactlyOneCharacter(String A) {
        char[] ch = A.toCharArray();
        int n = A.length();

        int count = 0;
        for (int i = 0; i < n / 2; i++) {
            if (ch[i] != ch[n - 1 - i]) {
                count++;

                if (count > 1) {
                    return "NO";
                }
            }
        }

        if (count == 1)
            return "YES";
        if (count == 0 && n % 2 != 0)
            return "YES";

        return "NO";
    }

    /*
     * Longest Common Prefix Given the array of strings A, you need to find the
     * longest string S which is the prefix of ALL the strings in the array.
     * 
     * Longest common prefix for a pair of strings S1 and S2 is the longest string S
     * which is the prefix of both S1 and S2.
     * 
     * For Example: longest common prefix of "abcdefgh" and "abcefgh" is "abc".
     */
    public String longestStringWhichIsPrefixOfAllStrings(String[] A) {
        String minStr = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i].length() < minStr.length()) {
                minStr = A[i];
            }
        }

        String ans = "";
        for (int i = 0; i < minStr.length(); i++) {
            boolean isQualified = true;
            for (int j = 0; j < A.length; j++) {
                if (A[j].charAt(i) != minStr.charAt(i)) {
                    isQualified = false;
                    break;
                }
            }

            if (!isQualified)
                return ans;

            ans = ans + minStr.charAt(i);
        }

        return ans;
    }

    /*
     * Change character Given a string A of size N consisting of lowercase
     * alphabets. You can change at most B characters in the given string to any
     * other lowercase alphabet such that the number of distinct characters in the
     * string is minimized. Find the minimum number of distinct characters in the
     * resulting string.
     * 
     * Approach: Find the character with smallest frequency & change it to element
     * with maximum frequency.
     */
    public int ChangeCharacter(String A, int B) {
        int[] map = new int[26];
        for (int i = 0; i < A.length(); i++) {
            map[A.charAt(i) - 'a']++;
        }

        while (B > 0) {
            int min = -1, max = -1;

            for (int i = 0; i < 26; i++) {
                if (map[i] > 0) {
                    if (min == -1 || map[i] < map[min]) {
                        min = i;
                    }

                    if (max == -1 || map[i] > map[max]) {
                        max = i;
                    }
                }
            }

            if (map[min] > B)
                break;

            map[max] += map[min];
            B = B - map[min];
            map[min] = 0;
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                ans++;
            }
        }

        return ans;
    }

    /*
     * Amazing Subarrays You are given a string S, and you have to find all the
     * amazing substrings of S. Amazing Substring is one that starts with a vowel
     * (a, e, i, o, u, A, E, I, O, U).
     */
    public int AmazingSubarrays(String A) {

        HashSet<Character> set = new HashSet<Character>();

        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        int count = 0, n = A.length();
        for (int i = 0; i < n; i++) {
            if (set.contains(A.charAt(i))) {
                count = (count + (n - i)) % 10003;
            }
        }

        return count % 10003;
    }

    /*
     * Lexicographically Largest You are given a string S. You want to change it to
     * the lexicographically largest possible string by changing some of its
     * characters. But you can only use characters of the string T as a replacement
     * for characters of S. Formally, find the lexicographically largest string you
     * can form by replacing some(or none) of the characters of S by using only the
     * characters of string T. Note: Each character of T can be used at the most
     * once.
     * 
     * Constraints:
     * 
     * 1. 1 <= Length of S and T <= 50 2. All the alphabets of S and T are lower
     * case (a - z) Input: A string A containing S and T separated by "_" character.
     * (See example below)
     * 
     * Output: Lexicographically largest string P that can be formed by changing
     * some or (none) characters of S using the characters of T.
     * 
     * Example:
     * 
     * Input:
     * 
     * A : "A : "abbaca_cbddbc"" This implies S is "abbaca" and T is "cbddbc"".
     * 
     * Output: P = "ddcccb"
     */
    public static String getLargest(String A) {

        String[] arr = A.split("_");

        String S = arr[0];// abb
        String T = arr[1];// c

        int[] freq = new int[26];

        for (int i = 0; i < T.length(); i++) {
            freq[T.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();

        int j = 25;

        for (int i = 0; i < S.length(); i++) {

            int currCharIndex = S.charAt(i) - 'a';

            while(j > currCharIndex) {
                if (freq[j] > 0) {
                    sb.append((char) ('a' + j));
                    freq[j]--;
                    
                    break;
                }

                j--;
            }

            if (j <= currCharIndex) {
                sb.append(S.charAt(i));
            }
        }

        return sb.toString();
    }

    /*
     * Excel Column Number
     * Given a column title as appears in an Excel sheet, return its corresponding
     * column number.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= length of the column title <= 5
     * 
     * 
     * 
     * Input Format
     * 
     * Input a string which represents the column title in excel sheet.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a single integer which represents the corresponding column number.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * AB
     * Input 2:
     * 
     * ABCD
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 28
     * Output 2:
     * 
     * 19010
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     */
    public int ExcelColumnTitleToColumnNumber(String A) {
        int p=1, ans = 0;
        for(int i=A.length()-1; i>=0; i--)
        {
            ans += ((int)(A.charAt(i) - 'A') + 1) * p;
            p = p*26;
        }

        return ans;
    }

    /*
     * Excel Column Title Given a positive integer A, return its corresponding
     * column title as appear in an Excel sheet. i/p A = 3, o/p "C" i/p A = 27, o/p
     * "AA"
     */
    public String ColumnNumberToColumnTitle(int A) {
        Character[] map = new Character[26];

        for (int i = 0; i < 26; i++) {
            map[i] = (char) ('A' + i);
        }

        String res = "";
        int num = 26;
        while (A > 0) {
            int index = (A - 1 + num) % num;
            A = (A - 1) / num;
            res = map[index] + res;
        }

        return res;
    }

    /*
     * Add Binary Strings Given two binary strings, return their sum (also a binary
     * string). Example:
     * 
     * a = "100"
     * 
     * b = "11" Return a + b = "111".
     */
    public String addBinary(String A, String B) {
        int i = A.length() - 1;
        int j = B.length() - 1;

        String result = "";
        int s = 0;
        while (i >= 0 || j >= 0 || s == 1) {
            s += i >= 0 ? A.charAt(i) - '0' : 0;
            s += j >= 0 ? B.charAt(j) - '0' : 0;

            result = (s % 2) + result;

            s = s / 2;

            i--;
            j--;
        }

        return result;
    }

    /*
     * Anagrams Problem Description
     * 
     * Given an array A of N strings, return all groups of strings that are
     * anagrams.
     * 
     * Represent a group by a list of integers representing the index(1-based) in
     * the original list. Look at the sample case for clarification.
     * 
     * NOTE: Anagram : a word, phrase, or name formed by rearranging the letters of
     * another, such as 'spar', formed from 'rasp'.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= N <= 104
     * 
     * 1 <= |A[i]| <= 104
     * 
     * Each string consists only of lowercase characters.
     * 
     * Sum of length of all the strings doesn't exceed 107
     * 
     * 
     * 
     * Input Format
     * 
     * Single Argument A which is an array of strings.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a two-dimensional array where each row describes a group.
     * 
     * Note:
     * 
     * Ordering of the result : You should not change the relative ordering of the
     * strings within the group suppose within a group containing A[i] and A[j],
     * A[i] comes before A[j] if i < j.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = [cat, dog, god, tca] Input 2:
     * 
     * A = [rat, tar, art]
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * [ [1, 4], [2, 3] ] Output 2:
     * 
     * [ [1, 2, 3] ]
     */
    public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
        ArrayList<String> sortedList = new ArrayList<String>();
        for (int i = 0; i < A.size(); i++) {
            String str = A.get(i);

            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            sortedList.add(sorted);
        }

        HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        for (int i = 0; i < sortedList.size(); i++) {
            if (map.containsKey(sortedList.get(i))) {
                ArrayList<Integer> al = map.get(sortedList.get(i));
                al.add(i + 1);
            } else {
                ArrayList<Integer> al = new ArrayList<Integer>();
                al.add(i + 1);
                map.put(sortedList.get(i), al);
            }
        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < sortedList.size(); i++) {
            if (map.containsKey(sortedList.get(i))) {
                ArrayList<Integer> al = map.get(sortedList.get(i));
                res.add(al);
                map.remove(sortedList.get(i));
            }
            ;

            if (map.size() == 0)
                break;
        }

        return res;
    }

    /*
     * Replicating Substring Problem Description
     * 
     * Given a string B, find if it is possible to re-order the characters of the
     * string B so that it can be represented as a concatenation of A similar
     * strings.
     * 
     * Eg: B = aabb and A = 2, then it is possible to re-arrange the string as
     * "abab" which is a concatenation of 2 similar strings "ab".
     * 
     * If it is possible, return 1, else return -1.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= Length of string B <= 1000
     * 
     * 1 <= A <= 1000
     * 
     * All the alphabets of S are lower case (a - z)
     * 
     * 
     * 
     * Input Format
     * 
     * First argument is an integer A. Second argument is a string B.
     * 
     * 
     * 
     * Output Format
     * 
     * Your function should return 1 if it is possible to re-arrange the characters
     * of the string B so that it can be represented as a concatenation of A similar
     * strings. If it is not, return -1.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 2 B = "bbaabb" Input 2:
     * 
     * A = 1 B = "bc"
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 1 Output 2:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * We can re-order the given string into "abbabb". Explanation 2:
     * 
     * String "bc" is already arranged.
     */
    public int ReplicatingSubstring(int A, String B) {
        if (A == 1)
            return 1;

        int[] count = new int[26];
        for (int i = 0; i < B.length(); i++) {
            count[B.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                if (count[i] % A != 0) {
                    return -1;
                }
            }
        }

        return 1;

    }

    /*
     * Little Ponny and 2-Subsequence Problem Description
     * 
     * Little Ponny is given a string A and he wants to find out the
     * lexicographically minimum subsequence from it of size >= 2. Can you help him?
     * 
     * A string a is lexicographically smaller than a string b, if the first
     * different letter in a and b is smaller in a. For example, "abc" is
     * lexicographically smaller than "acc" because the first different letter is
     * 'b' and 'c' which is smaller in "abc".
     * 
     * 
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= |A| <= 105
     * 
     * A only contains lowercase alphabets.
     * 
     * 
     * 
     * Input Format
     * 
     * The first and the only argument of input contains the string, A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return a string representing the answer.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = "abcdsfhjagj" Input 2:
     * 
     * A = "ksdjgha"
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * "aa" Output 2:
     * 
     * "da"
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * "aa" is the lexicographically minimum subsequence from A. Explanation 2:
     * 
     * "da" is the lexicographically minimum subsequence from A.
     */
    public String LexicographicallyMinimumSubsequence(String A) {

        int minFirstOccurrence = 0;
        for(int i=1; i<A.length()-1; i++)
        {
            if(A.charAt(i) < A.charAt(minFirstOccurrence))
            {
                minFirstOccurrence = i;
            }
        }

        int minSecondOccurrence = minFirstOccurrence+1;
        for(int i=minSecondOccurrence+1; i<A.length(); i++)
        {
            if(A.charAt(i) < A.charAt(minSecondOccurrence))
            {
                minSecondOccurrence = i;
            }
        }

        String res = String.valueOf(A.charAt(minFirstOccurrence)) + String.valueOf(A.charAt(minSecondOccurrence));

        return res;  
    }

    /* Smallest Prefix String
    Given 2 strings A and B of size N and M respectively consisting of lowercase alphabets, find the lexicographically smallest string that can be formed by concatenating non-empty prefixes of A and B (in that order).
    Note: The answer string has to start with a non-empty prefix of string A followed by a non-empty prefix of string B.



    Problem Constraints
    1 <= N, M <= 100000



    Input Format
    The first argument is a string A of size N.
    The second argument is a string B of size M.



    Output Format
    Return lexicographically smallest string that can be formed by concatenating non-empty prefixes of A and B (in that order).



    Example Input
    Input 1:

    A = "abba"
    B = "cdd"
    Input 2:

    A = "acd"
    B = "bay"


    Example Output
    Output 1:

    "abbac"
    Output 2:

    "ab"


    Example Explanation
    Explanation 1:

    We can concatenate prefix of A i.e "abba" and prefix of B i.e "c".
    The lexicographically smallest string will be "abbac".
    Explanation 2:

    We can concatenate prefix of A i.e "a" and prefix of B i.e "b".
    The lexicographically smallest string will be "ab".



    See Expected Output
    */
    public String smallestPrefix(String A, String B) {
        String res = "" + A.charAt(0);
        for(int i=1; i<A.length(); i++)
        {
            if(A.charAt(i) < B.charAt(0))
            {
                res = res + A.charAt(i);
            } else {
                break;
            }
        }

        res = res + B.charAt(0);

        return res;
    }

    public int[] fillLps(String S) {
        int[] lps = new int[S.length()];
        lps[0] = 0;

        for (int i = 1; i < S.length(); i++) {
            int x = lps[i - 1];
            while (S.charAt(i) != S.charAt(x)) {
                if (x == 0) {
                    x = -1;
                    break;
                }

                x = lps[x - 1];
            }

            lps[i] = x + 1;
        }

        return lps;
    }

    /* Make String Pallindrome
    Problem Description
    Given a string A of size N consisting only of lowercase alphabets. The only operation allowed is to insert characters in the beginning of the string.

    Find and return how many minimum characters are needed to be inserted to make the string a palindrome string.



    Problem Constraints
    1 <= N <= 106



    Input Format
    The only argument given is a string A.



    Output Format
    Return an integer denoting the minimum characters needed to be inserted in the beginning to make the string a palindrome string.



    Example Input
    Input 1:

    A = "abc"
    Input 2:

    A = "bb"


    Example Output
    Output 1:

    2
    Output 2:

    0


    Example Explanation
    Explanation 1:

    Insert 'b' at beginning, string becomes: "babc".
    Insert 'c' at beginning, string becomes: "cbabc".
    Explanation 2:

    There is no need to insert any character at the beginning as the string is already a palindrome. 
    */
    public String reverse(String S)
    {
        char[] chAr = S.toCharArray();

        int i=0, j = chAr.length - 1;

        while(i < j)
        {
            char t = chAr[i];
            chAr[i] = chAr[j];
            chAr[j] = t;

            i++;
            j--;
        }

        return new String(chAr);
    }

    public int MakeStringPallindrome(String A) {
        String T = A + "$" + reverse(A);

        int[] lps = fillLps(T);

        return A.length() - lps[lps.length - 1];

    }

    /*  Longest Substring Without Repeat
    Problem Description
    Given a string A, find the length of the longest substring without repeating characters.

    Note: Users are expected to solve in O(N) time complexity.



    Problem Constraints
    1 <= size(A) <= 106

    String consists of lowerCase,upperCase characters and digits are also present in the string A.



    Input Format
    Single Argument representing string A.



    Output Format
    Return an integer denoting the maximum possible length of substring without repeating characters.



    Example Input
    Input 1:

    A = "abcabcbb"
    Input 2:

    A = "AaaA"


    Example Output
    Output 1:

    3
    Output 2:

    2


    Example Explanation
    Explanation 1:

    Substring "abc" is the longest substring without repeating characters in string A.
    Explanation 2:

    Substring "Aa" or "aA" is the longest substring without repeating characters in string A.
    */
    public int lengthOfLongestSubstring(String A) {
        int[] freq = new int[256];
        int[] index = new int[256];

        int i=0;
        int ans = 1;
        
        for(int j=0; j<A.length(); j++)
        {
            freq[A.charAt(j)]++;

            if(freq[A.charAt(j)] > 1)
            {
                while(i <= index[A.charAt(j)])
                {
                    freq[A.charAt(i)]--;                    
                    i++;                    
                }                
            }

            index[A.charAt(j)] = j;
            ans = Math.max(ans, j-i+1);  
        }

        return ans;
    }

    /*    Given a string of the following form, “aaabbcccccdeeeaag”,
    * write an algorithm to compress the string into the following form, “a3b2c5d1e3a2g1”
    If the compressed string is not shorter than the original string, return the original string instead
    I.e., compressing the string “abc” should result in the original string being returned
    */
    public String TryCompress(String originalText) {
        // aaabbcccccdeeeaag
        // aaabbcccccdeeeaaa

        int i = 1;
        int count = 1;
        StringBuilder sb = new StringBuilder();

        while (i < originalText.length()) {
            if (originalText.charAt(i) == originalText.charAt(i - 1)) {
                count++;
            } else {
                sb.append(originalText.charAt(i - 1)).append(count);
                count = 1;
            }

            i++;
        }

        sb.append(originalText.charAt(originalText.length() - 1)).append(count);

        String compressedText = sb.toString();
        if (compressedText.length() >= originalText.length()) {
            return originalText;
        }

        return compressedText;
    }
}
