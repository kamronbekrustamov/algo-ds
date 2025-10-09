class Solution {
    /**
     * Calculates the Hamming weight (number of set bits or '1's) of an integer.
     *
     * This solution uses Brian Kernighan's algorithm, which is an efficient
     * way to count set bits. The loop runs exactly as many times as there are
     * set bits, making it faster than checking every bit for sparse numbers.
     *
     * Algorithm:
     * The loop continues as long as n is not 0 (i.e., there are still set bits).
     * The operation `n & (n - 1)` cleverly clears the least significant
     * (rightmost) '1' bit of n.
     * Example:
     *   n   = 12 (1100)
     *   n-1 = 11 (1011)
     *   -----------------
     *   n & (n-1) = 8 (1000) -> The rightmost '1' has been cleared.
     * Since we clear one set bit in each iteration, we increment the count.
     *
     * @param n The integer whose set bits are to be counted.
     * @return The number of set bits in the given integer.
     *
     * Time Complexity: O(k), where k is the number of set bits in the integer.
     * Space Complexity: O(1).
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
