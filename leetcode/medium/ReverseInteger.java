/**
 * Reverses the digits of a 32-bit signed integer.
 * This version separates the sign and operates on the absolute magnitude.
 * If the reversed integer overflows, it returns 0.
 */
class Solution {
    /**
     * Reverses the digits of an integer $x$.
     * * @param x The 32-bit signed integer to reverse.
     * @return The reversed integer. Returns 0 if the reversed value overflows.
     */
    public int reverse(int x) {
        // Step 1: Handle the sign and the Integer.MIN_VALUE edge case.
        int sign = 1;
        if (x < 0) {
            sign = -1;
        }

        // Integer.MIN_VALUE (-2,147,483,648) cannot be safely converted to its 
        // positive equivalent (2,147,483,648) because it exceeds 
        // Integer.MAX_VALUE (2,147,483,647). If we encounter it, the reversed 
        // value (8463847412) will certainly overflow. Return 0 immediately.
        if (x == Integer.MIN_VALUE) {
            return 0;
        }

        // Convert to a non-negative number for reversal.
        int magnitude = Math.abs(x); 
        int reversedMagnitude = 0;

        // Step 2: Reverse the positive magnitude.
        while (magnitude != 0) {
            int digit = magnitude % 10;
            
            // --- Overflow Check Logic (Since we are only dealing with positive numbers) ---
            
            // 1. Check if reversing 'reversedMagnitude * 10' will exceed Integer.MAX_VALUE.
            // If the current reversed number is already larger than the largest safe
            // prefix (Integer.MAX_VALUE / 10), then it's an overflow.
            if (reversedMagnitude > Integer.MAX_VALUE / 10) {
                return 0;
            }
            
            // 2. Check the edge case for the final digit (7).
            // If reversedMagnitude is exactly 214748364 and the next digit is > 7, it overflows.
            if (reversedMagnitude == Integer.MAX_VALUE / 10 && digit > 7) {
                return 0;
            }
            
            // --- End of Overflow Check ---

            // Build the new reversed integer safely.
            reversedMagnitude = reversedMagnitude * 10 + digit;
            magnitude /= 10;
        }

        // Step 3: Apply the original sign back.
        // The check against MAX_VALUE also guarantees that multiplying by -1 will not 
        // result in underflow, since the magnitude of the result is within the 
        // range of valid 32-bit integers.
        return sign * reversedMagnitude;
    }
}