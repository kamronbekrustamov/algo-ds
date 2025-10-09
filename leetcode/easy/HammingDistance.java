/**
 * Solution class for the Hamming Distance problem.
 */
class Solution {
    /**
     * Calculates the Hamming distance between two integers.
     * The Hamming distance is the number of positions at which the corresponding bits are different.
     *
     * @param x The first integer.
     * @param y The second integer.
     * @return The Hamming distance between x and y.
     */
    public int hammingDistance(int x, int y) {
        // Step 1: Perform XOR on x and y.
        // The result of XOR (x ^ y) will have a bit set to 1 only at the positions
        // where the bits of x and y are different. For example, if x=1 (0001) and y=4 (0100),
        // x ^ y = 5 (0101). The set bits in the result correspond to the differing positions.
        int xorResult = x ^ y;

        // Step 2: Count the number of set bits (1s) in the XOR result.
        // This count is the Hamming distance. We use Brian Kernighan's algorithm,
        // which is an efficient way to count set bits.
        int distance = 0;
        while (xorResult != 0) {
            // The operation (xorResult & (xorResult - 1)) cleverly unsets the
            // rightmost (least significant) '1' bit of a number.
            // Example: If xorResult is 5 (0101),
            // xorResult - 1 is 4 (0100).
            // 0101 & 0100 = 0100. The rightmost '1' is gone.
            // We increment the distance for each set bit we clear.
            xorResult = xorResult & (xorResult - 1);
            distance++;
        }

        return distance;
    }
}