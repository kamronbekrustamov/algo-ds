class Solution {
    /**
     * Calculates the bitwise AND of all numbers in a given range [left, right].
     *
     * This solution uses a two-pointer approach by finding the common prefix of the
     * binary representations of left and right. The result of the AND operation
     * across a range will be this common prefix, with all trailing bits being 0.
     *
     * Time Complexity: O(log(max(left, right))) - In the worst case, we shift
     *                   until the numbers become 0, which takes at most the number
     *                   of bits in the larger number.
     * Space Complexity: O(1) - We only use a few extra variables.
     *
     * @param left The starting number of the range (inclusive).
     * @param right The ending number of the range (inclusive).
     * @return The bitwise AND of all numbers in the range.
     */
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;

        // Right-shift both left and right until they are equal.
        // This effectively strips off the differing trailing bits.
        while (left != right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }

        // Once left and right are equal, they represent the common prefix.
        // Left-shift this common prefix back to its original position.
        // The trailing bits will be filled with zeros, which is the correct result.
        return left << shift;
    }
}