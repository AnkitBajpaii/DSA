package BitManipulation;

public class bitwiseProblems {
    /*
     * Count Total Set Bits Problem Description
     * 
     * Given a positive integer A, the task is to count the total number of set bits
     * in the binary representation of all the numbers from 1 to A.
     * 
     * Return the count modulo 109 + 7.
     * 
     * 
     * 
     * Problem Constraints
     * 
     * 1 <= A <= 109
     * 
     * 
     * 
     * Input Format
     * 
     * First and only argument is an integer A.
     * 
     * 
     * 
     * Output Format
     * 
     * Return an integer denoting the ( Total number of set bits in the binary
     * representation of all the numbers from 1 to A )modulo 109 + 7.
     * 
     * 
     * 
     * Example Input
     * 
     * Input 1:
     * 
     * A = 3 Input 2:
     * 
     * A = 1
     * 
     * 
     * Example Output
     * 
     * Output 1:
     * 
     * 4 Output 2:
     * 
     * 1
     * 
     * 
     * Example Explanation
     * 
     * Explanation 1:
     * 
     * DECIMAL BINARY SET BIT COUNT 1 01 1 2 10 1 3 11 2 1 + 1 + 2 = 4 Answer = 4 %
     * 1000000007 = 4 Explanation 2:
     * 
     * A = 1 DECIMAL BINARY SET BIT COUNT 1 01 1 Answer = 1 % 1000000007 = 1
     * 
     * IDEA: Contribution technique. Contribution bit by bit 
     */
    public int countTotalSetBits(int A) {

        long p = 1;
        int count = 0;

        // Ci = ((n+1)/2^(i+1)) * 2^i + incompleteCycle
        // incompleteCycle = (n+1)%(2^(i+1) - 2^i) -- do not consider negative values.
        for (int i = 0; i <= 30; i++) {

            long p1 = p << i;
            long p2 = p << (i + 1);

            count = (count + (int) (p1 * ((A + 1) / p2)) + (int) Math.max(((A + 1) % p2) - p1, 0)) % 1000000007;
        }

        return count % 1000000007;
    }

}
