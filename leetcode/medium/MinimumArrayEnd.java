/**
 * Finds the n-th smallest number y >= 0 such that (x AND y) == x.
 * * The condition (x AND y) == x implies that y must have all the bits set 
 * that are set in x. The remaining bits (where x has a 0) can be chosen freely.
 * To find the n-th smallest such number y, we effectively use the binary 
 * representation of (n - 1) to determine the values of these "free" bits in y,
 * starting from the least significant free bit.
 */
class Solution {
    /**
     * @param n The rank (1-based) of the number to find.
     * @param x The base number for the bitwise AND condition.
     * @return The n-th smallest number y such that (x AND y) == x.
     */
    public long minEnd(int n, int x) {
        // Since we are looking for the n-th smallest number (1-based), 
        // we work with (n - 1) to represent the 0-based index.
        long nMinus1 = n - 1;
        long answer = x; // Start with x, which ensures (x AND x) = x.
        int bitPos = 0;

        // Iterate through the bits of x and nMinus1 simultaneously.
        // The loop continues as long as there are bits in nMinus1 to place.
        while (nMinus1 > 0) {
            // 1. Find the current bit of x: (x >> bitPos) & 1
            // 2. Find the current bit of nMinus1: nMinus1 & 1

            // Check if the current bit of x is 0. 
            // If x's bit is 0, y's bit is a "free" bit, which we can set 
            // based on the corresponding bit of nMinus1 to maintain the ordering.
            if (( (x >> bitPos) & 1 ) == 0) {
                // Get the current bit of nMinus1
                long bitOfN = nMinus1 & 1;

                // Set the bit in 'answer' (which is y) if the corresponding 
                // bit in nMinus1 is 1. We use the OR operation: answer |= (bitOfN << bitPos).
                if (bitOfN == 1) {
                    answer |= (1L << bitPos);
                }

                // Move to the next bit of nMinus1
                nMinus1 >>= 1;
            } 
            
            // If the current bit of x is 1, the corresponding bit of y (answer) 
            // *must* also be 1 for the condition (x AND y) == x to hold.
            // Since 'answer' was initialized to 'x', this bit is already 1.
            // We do *not* consume a bit from nMinus1 in this case.

            // Move to the next bit position for both x and answer.
            bitPos++;
        }

        return answer;
    }
}