/**
 * Given a non-empty array of integers 'nums', every element appears twice except for one.
 * Find that single one.
 *
 * Constraints:
 * - The algorithm should have a linear runtime complexity (O(n)).
 * - The solution should use only constant extra space (O(1)).
 */
class Solution {
    /**
     * Finds the single number that appears once in an array where all other
     * elements appear exactly twice.
     *
     * This method utilizes the properties of the Bitwise XOR (^) operation:
     * 1. A ^ 0 = A (Identity property)
     * 2. A ^ A = 0 (Self-inverse property)
     * 3. A ^ B ^ A = B (Commutative and associative properties)
     *
     * By XORing all elements in the array, all pairs (A ^ A) cancel out to 0,
     * leaving only the single, non-paired number (0 ^ SingleNumber = SingleNumber).
     *
     * @param nums The input array of integers.
     * @return The single number that appears only once.
     */
    public int singleNumber(int[] nums) {
        // Initialize the result accumulator to 0.
        // The XOR identity element is 0 (any_number ^ 0 = any_number).
        int singleNumberResult = 0;

        // Iterate through every number in the array.
        for (int num : nums) {
            // XOR the current number with the running result.
            // When a number appears twice, the two operations will be num ^ num = 0,
            // effectively removing it from the result.
            singleNumberResult ^= num;
        }

        // The final result will be the single number.
        return singleNumberResult;
    }
}