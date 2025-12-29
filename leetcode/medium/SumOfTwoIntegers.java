/**
 * Calculates the sum of two integers (a and b) without using the built-in 
 * addition, subtraction, multiplication, or division operators.
 * * The algorithm uses bitwise operations (XOR and AND) to simulate addition 
 * based on the following principles:
 * * 1. Sum without carry: a XOR b
 * - The XOR operation (^) performs the addition of bits without considering 
 * the carry. (e.g., 0+0=0, 1+0=1, 0+1=1, but 1+1=0 for XOR).
 * * 2. The carry: (a AND b) << 1
 * - The AND operation (&) identifies where a carry is generated (1 & 1 = 1).
 * - The left shift (<< 1) moves the generated carry to the next significant 
 * bit position, preparing it for the next iteration of addition.
 * * The process repeats until there is no carry (b becomes 0).
 */
class Solution {
    /**
     * Adds two integers using bitwise operations.
     * * @param a The first integer.
     * @param b The second integer.
     * @return The sum of a and b.
     */
    public int getSum(int a, int b) {
        // Continue the loop as long as there is a carry to process (b != 0).
        while (b != 0) {
            // 1. Calculate the sum of bits without considering the carry.
            // This is the exclusive OR (XOR) operation.
            int sumWithoutCarry = a ^ b;
            
            // 2. Calculate the carry.
            // a AND b finds the bits that are both 1 (where a carry is generated).
            // Left shifting by 1 (<< 1) moves the carry to the next position.
            int carry = (a & b) << 1;
            
            // 3. Update 'a' to be the sum without carry for the next iteration.
            a = sumWithoutCarry;
            
            // 4. Update 'b' to be the carry. This will be added to 'a' in 
            // the next iteration, effectively completing the addition of 
            // the current carry.
            b = carry;
        }
        
        // When 'b' is 0, there is no more carry to add. The final result is in 'a'.
        return a;
    }
}