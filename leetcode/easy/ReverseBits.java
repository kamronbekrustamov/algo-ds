class Solution {
    /**
     * Reverses the bits of a given 32-bit unsigned integer.
     *
     * Algorithm:
     * 1. Initialize `res` to 0, which will store the reversed bits.
     * 2. Iterate 32 times, as we need to process all 32 bits of the integer.
     * 3. In each iteration:
     *    a. Left shift `res` by 1 (`res <<= 1`). This makes space for the next bit
     *       from `n` at the least significant position of `res`.
     *    b. Get the least significant bit (LSB) of `n` using a bitwise AND with 1 (`n & 1`).
     *    c. Add this LSB to `res` using a bitwise OR (`res |= last_bit`). This effectively
     *       appends the bit to the end of `res`.
     *    d. Right shift `n` by 1 (`n >>>= 1`). This discards the LSB of `n` and exposes
     *       the next bit for the following iteration. We use unsigned right shift `>>>`
     *       to handle potential negative numbers correctly as per the problem's unsigned integer context.
     * 4. After 32 iterations, `res` will contain the reversed 32-bit integer.
     *
     * @param n The 32-bit unsigned integer to reverse.
     * @return The integer with its bits reversed.
     *
     * Time Complexity: O(1), as it performs a fixed number of 32 iterations.
     * Space Complexity: O(1).
     */
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            int lastBit = n & 1;
            res |= lastBit;
            n >>>= 1; // Use unsigned right shift
        }
        return res;
    }
}
