/**
 * Solution for LeetCode problem "Greatest Common Divisor of Strings".
 * This class provides a method to find the largest string X such that X divides
 * both str1 and str2. A string X divides string S if S = X + X + ... + X
 * (i.e., X is concatenated with itself one or more times).
 */
class Solution {

    /**
     * Computes the greatest common divisor (GCD) of two non-negative integers using the Euclidean algorithm.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The greatest common divisor of a and b.
     */
    private int gcd(int a, int b) {
        // Euclidean algorithm: gcd(a, b) = gcd(b, a % b)
        // The process continues until b becomes 0, at which point a is the GCD.
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Finds the largest string X such that X divides both str1 and str2.
     *
     * The core idea is based on two properties:
     * 1. If a common divisor string X exists for str1 and str2, then str1 + str2 must be equal to str2 + str1.
     *    This is because if str1 = X * n and str2 = X * m, then str1 + str2 = X * (n + m) and str2 + str1 = X * (m + n).
     *    If they are not equal, no such common divisor string exists.
     * 2. If a common divisor string exists, its length must be the greatest common divisor of the lengths of str1 and str2.
     *    Let len(str1) = L1 and len(str2) = L2. If X is the common divisor string, then len(X) must divide both L1 and L2.
     *    To find the *largest* such X, we need the *largest* possible length for X, which is gcd(L1, L2).
     *
     * @param str1 The first string.
     * @param str2 The second string.
     * @return The largest string X that divides both str1 and str2, or an empty string if no such string exists.
     */
    public String gcdOfStrings(String str1, String str2) {
        // If str1 and str2 do not have a common divisor string, then str1 + str2 will not be equal to str2 + str1.
        // For example, if str1 = "ABC", str2 = "AB", then str1 + str2 = "ABCAB", str2 + str1 = "ABABC". They are not equal.
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // If a common divisor string exists, its length must be the GCD of the lengths of str1 and str2.
        // For example, if str1 = "ABCABC", str2 = "ABC", then gcd(6, 3) = 3. The result is "ABC".
        int gcdLength = gcd(str1.length(), str2.length());

        // The common divisor string is the prefix of str1 (or str2) with the calculated gcdLength.
        return str1.substring(0, gcdLength);
    }
}
