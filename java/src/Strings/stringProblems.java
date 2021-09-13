package Strings;
import java.util.*;

public class stringProblems {
    /* Reverse the String
    Given a string A of size N.
    Return the string A after reversing the string word by word.
    NOTE: A sequence of non-space characters constitutes a word.
    Your reversed string should not contain leading or trailing spaces, even if it is present in the input string.
    If there are multiple spaces between words, reduce them to a single space in the reversed string.
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

        for (int e = 0; e < n; e++) {
            if (e > 0 && A.charAt(e) == ' ' && A.charAt(e - 1) == ' ') {
                s = e + 1;
                continue;
            }

            if (A.charAt(e) == ' ') {
                if (s < e) {
                    sb.append(reverse(A, s, e - 1));
                }
                sb.append(" ");
                s = e + 1;
            }
        }

        if (s < n) {
            sb.append(reverse(A, s, n - 1));
        }

        String res = reverse(sb.toString(), 0, n - 1);

        return res.trim();
    }

    /*
    You are given a function to_upper() consisting of a character array A.

    Convert each charater of A into Uppercase character if it exists. If the Uppercase of a character does not exist, it remains unmodified.
    The lowercase letters from a to z is converted to uppercase letters from A to Z respectively.

    Return the uppercase version of the given character array.
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

    /* Closest Palindrome
    Groot has a profound love for palindrome which is why he keeps fooling around with strings.
    A palindrome string is one that reads the same backward as well as forward.
    Given a string A of size N consisting of lowercase alphabets, he wants to know if it is possible to make the given string a palindrome by changing exactly one of its character.
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
    
}
