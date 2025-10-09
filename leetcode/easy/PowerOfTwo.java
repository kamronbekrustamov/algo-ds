class Solution {
    /**
     * Checks if an integer is a power of two.
     *
     * This solution uses a clever bitwise trick.
     * A positive integer `n` is a power of two if and only if it has exactly one set bit
     * in its binary representation.
     *
     * Algorithm:
     * 1. Handle edge cases:
     *    - Powers of two must be positive integers. Any number less than or equal to 0 cannot be a power of two.
     * 2. Bitwise check:
     *    - The expression `n & (n - 1)` clears the least significant set bit of `n`.
     *    - If `n` is a power of two, then `n - 1` will have all bits to the right of the single set bit of `n`
     *      set to 1, and that single set bit will be 0. Thus, `n & (n - 1)` will be 0.
     *    - If `n` is not a power of two, it has multiple '1' bits, and `n & (n - 1)` will not be 0.
     *
     * @param n The integer to check.
     * @return True if `n` is a power of two, false otherwise.
     *
     * Time Complexity: O(1), as it involves a constant number of bitwise operations.
     * Space Complexity: O(1).
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}
