class Solution {
    /**
     * Checks if a positive integer has alternating bits in its binary representation.
     *
     * This solution uses a clever bit manipulation trick for a concise and
     * efficient O(1) check.
     *
     * Algorithm Breakdown:
     * 1. Let's take an example, n = 5 (binary "101").
     *
     * 2. Create a new number by XORing n with n right-shifted by one bit.
     *    n      = 101
     *    n >> 1 = 010
     *    -----------------
     *    x      = 111  (n ^ (n >> 1))
     *
     *    If `n` has alternating bits, this operation `n ^ (n >> 1)` results in a
     *    number where all the significant bits are set to 1.
     *    Another example: n = 10 (binary "1010")
     *    n      = 1010
     *    n >> 1 = 0101
     *    -----------------
     *    x      = 1111
     *
     * 3. Now, we need to check if `x` is a number with all its bits set to 1
     *    (e.g., 1, 3, 7, 15, 31, ...). These numbers have a special property:
     *    `x & (x + 1)` will be equal to 0.
     *
     *    Example: x = 7 (binary "111")
     *    x      = 0111
     *    x + 1  = 1000
     *    -----------------
     *    &      = 0000
     *
     *    This works because adding 1 to a number of all 1s flips all bits to 0
     *    and sets the next higher bit to 1, meaning there are no overlapping
     *    set bits between `x` and `x + 1`.
     *
     * Time Complexity: O(1), as it involves a fixed number of bitwise operations.
     * Space Complexity: O(1).
     *
     * @param n The integer to check.
     * @return true if the bits are alternating, false otherwise.
     */
    public boolean hasAlternatingBits(int n) {
        // Step 2: XOR the number with itself right-shifted by one.
        // We cast n to a long before adding 1 in the next step. This is to
        // prevent confusion with integer overflow if n happens to be a value
        // like 0b010101... that results in x = Integer.MAX_VALUE.
        long x = n ^ (n >> 1);

        // Step 3: Check if x is a number with all bits set to 1.
        return (x & (x + 1)) == 0;
    }
}
