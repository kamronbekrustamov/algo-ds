/**
 * Implements a method to compute x raised to the power n ($x^n$).
 *
 * This uses the efficient "Exponentiation by Squaring" (Binary Exponentiation)
 * algorithm, which has a time complexity of O(log |n|) and a space complexity
 * of O(1).
 */
class Solution {
    /**
     * Computes $x^n$ (x raised to the power n).
     *
     * @param x The base number, a double.
     * @param n The exponent, an integer.
     * @return The result of $x^n$.
     */
    public double myPow(double x, int n) {
        // Initialize the result to 1.0.
        double result = 1.0;
        
        // Use a long for the exponent to safely handle the edge case 
        // n = Integer.MIN_VALUE, where -n would overflow a standard int.
        long pow = n;
        
        // 1. Handle Negative Exponents
        // If the exponent is negative, we use the property $x^{-n} = (1/x)^n$.
        if (pow < 0) {
            x = 1.0 / x;
            // Convert to a positive exponent for the main loop.
            pow = -pow; 
        }
        
        // 2. Main Binary Exponentiation Loop
        // The loop terminates when the power becomes 0.
        while (pow > 0) {
            // Check if the least significant bit of 'pow' is 1 (i.e., if 'pow' is odd).
            // If it is odd, it means the current 'x' (which represents $x^{2^k}$ at this step)
            // must be included in the final product.
            if ((pow & 1) == 1) {
                result *= x;
            }
            
            // Square the base: $x \to x^2 \to x^4 \to x^8 \dots$
            // This prepares 'x' for the next bit of the exponent.
            x *= x;
            
            // Right shift 'pow' by 1: $n \to n/2 \to n/4 \dots$
            // This effectively moves to the next higher-order bit of the exponent.
            pow >>= 1;
        }
        
        return result;
    }
}