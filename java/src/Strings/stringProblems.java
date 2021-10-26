package Strings;

import java.util.*;

public class stringProblems {

    /* Toggle characters in string without using If Else condition
    * Capital -> Small & Small -> Capital
    */
    public String Toggle(String s){
        String res = "";
        for(int i=0; i<s.length(); i++){
            res = res + (char)(s.charAt(i) ^ 32);
        }

        return res;
    }

     /* Reverse the String Given a string A of size N. Return the string A after
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
        int s=0, n=A.length();
        StringBuilder sb = new StringBuilder();
        
        for(int e=1; e<n; e++){
            
            if(A.charAt(e) == ' '){
                sb.append(reverse(A, s, e-1));
                sb.append(" ");
                s=e+1;
            }
        }
        
        sb.append(reverse(A, s, n-1)); 
        
        String res = reverse(sb.toString(), 0, n-1);
        
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
    public String longestCommonPrefix(String[] A) {
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
     * Output:
     * P = "ddcccb"
     */
    public static String getLargest(String A) {

        String[] arr = A.split("_");

        String S = arr[0];// abb
        String T = arr[1];// c

        int[] count = new int[26];

        for (int i = 0; i < T.length(); i++) {
            count[T.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();

        int j = 25;

        for (int i = 0; i < S.length(); i++) {

            int x = S.charAt(i) - 'a';

            for (; j > x; j--) {
                if (count[j] > 0 && ('a' + j) > S.charAt(i)) {
                    sb.append((char) ('a' + j));
                    count[j]--;
                    break;
                }
            }

            if (j <= x) {
                sb.append(S.charAt(i));
            }
        }

        return sb.toString();
    }

    /*
     * Excel Column Number Given a column title as appears in an Excel sheet, return
     * its corresponding column number.
     */
    public int ExcelColumnTitleToColumnNumber(String A) {
        int[] map = new int[26];

        for (int i = 0; i < 26; i++) {
            map[i] = i + 1;
        }

        int n = A.length();

        int res = 0;
        int mul = 1;
        for (int i = n - 1; i >= 0; i--) {
            res += map[A.charAt(i) - 'A'] * mul;
            mul = mul * 26;
        }

        return res;
    }

    /* Excel Column Title
    Given a positive integer A, return its corresponding column title as appear in an Excel sheet.
    */
    public String ColumnNumberToColumnTitle(int A) {
        Character[] map = new Character[26];
        
        for(int i=0;i<26;i++){
            map[i]=(char)('A'+i);
        }
        
        if(A>=1 && A<=9){
            return map[A-1].toString();
        }

        StringBuilder sb = new StringBuilder();
        A=A-1;
        while(A>=0){
            int r = A%26;
            sb.append(map[r]);
            
            A = A/26;
            
            A = A-1;
            if(A<0) break;
        }
        
        sb = sb.reverse();
        
        return sb.toString();
    }

    /* Sorted Permutation Rank
    Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
    Assume that no characters are repeated.

    Note: The answer might not fit in an integer, so return your answer % 1000003

    */

    /* Add Binary Strings
    Given two binary strings, return their sum (also a binary string).
    Example:

    a = "100"

    b = "11"
    Return a + b = "111".
    */
    public String addBinary(String A, String B) {
        int i = A.length() - 1;
        int j = B.length() - 1;

        String result = "";
        int s = 0;
        while (i >= 0 || j >= 0 || s == 1) {
            s += i >= 0 ? A.charAt(i) - '0' : 0;
            s += j >= 0 ? B.charAt(j) - '0' : 0;

            result = (char) (s % 2 + '0') + result;

            s = s / 2;

            i--;
            j--;
        }

        return result;
    }

}
