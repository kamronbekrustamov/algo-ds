/**
 * Solves the "Minimum Number of Operations" problem (often titled "Minimum End").
 * * Problem Statement:
 * Find the n-th smallest integer y >= x such that (y & x) == x.
 * * Logic:
 * The condition (y & x) == x means y must act as a superset of x.
 * 1. All bits set to 1 in x must remain 1 in y.
 * 2. The bits set to 0 in x are "free positions" or "holes".
 * 3. To find the n-th smallest number, we effectively map the binary 
 * representation of (n - 1) into these "free positions" in increasing order of significance.
 */
class Solution {

    /**
     * @param n The rank (1-based) of the number to find.
     * @param x The base number which acts as a bitmask.
     * @return The n-th smallest number y satisfying the condition.
     */
    public long minEnd(int n, int x) {
        // We need to inject (n-1) into the zero-bits of x.
        // We use a long to prevent overflow since the result can exceed Integer.MAX_VALUE.
        long result = x;
        long remaining = n - 1;
        long mask = 1L; // Starts at the 0-th bit (1)

        // Iterate as long as we have bits from (n-1) left to place
        while (remaining > 0) {
            // Check if the current bit position in x is a "free spot" (0)
            if ((x & mask) == 0) {
                // If the current bit of the remaining count is 1, set this bit in the result
                if ((remaining & 1) == 1) {
                    result |= mask;
                }
                // Move to the next bit of (n-1) essentially "consuming" it
                remaining >>= 1;
            }
            
            // Move the mask to check the next bit position
            mask <<= 1;
        }

        return result;
    }
}