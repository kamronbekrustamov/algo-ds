class Solution {
    /**
     * Increments a large integer represented as an array of digits by one.
     *
     * This solution iterates through the array from right to left, simulating
     * the manual process of adding one with a carry. It modifies the array
     * in-place for efficiency and handles the edge case of a leading carry
     * (e.g., 999 + 1 = 1000).
     *
     * Algorithm:
     * 1. Iterate through the digits from the last to the first.
     * 2. If a digit is less than 9, simply increment it and return the array,
     *    as there is no carry to propagate.
     * 3. If a digit is 9, set it to 0 and continue to the next digit to the
     *    left (this implicitly handles the "carry").
     * 4. If the loop completes (meaning all digits were 9s, e.g., [9, 9, 9]),
     *    it means we have a carry-out. We need to create a new array with an
     *    additional digit at the beginning, setting it to '1' and all other
     *    digits to '0'.
     *
     * @param digits The array of digits representing the large integer.
     * @return The array of digits after incrementing by one.
     *
     * Time Complexity: O(n), where n is the number of digits.
     * Space Complexity: O(1) for in-place modification, or O(n) in the worst case
     *                   when a new array needs to be created (e.g., [9,9,9] -> [1,0,0,0]).
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        // If we reach here, it means all digits were 9s (e.g., [9,9,9])
        // We need to create a new array with an additional digit at the beginning.
        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;
        // The rest of the elements are already 0 by default in Java for new int arrays.
        return newDigits;
    }
}
