class Solution {
    /**
     * Determines whether an integer is a palindrome.
     *
     * An integer is a palindrome when it reads the same backward as forward.
     * This solution avoids converting the integer to a string.
     *
     * Algorithm:
     * 1. Handle edge cases:
     *    - Negative numbers are not palindromes.
     *    - If the number ends in 0 (and is not 0 itself), it cannot be a palindrome.
     * 2. Reverse the second half of the number.
     *    - In a loop, extract the last digit of `x` and append it to `reverted_number`.
     *    - Simultaneously, remove the last digit from `x`.
     *    - The loop continues as long as `x` is greater than `reverted_number`.
     * 3. Compare `x` and `reverted_number`:
     *    - If the original number has an even number of digits, `x` and `reverted_number` will be equal.
     *    - If the original number has an odd number of digits, `x` will be equal to `reverted_number / 10`
     *      (to remove the middle digit from `reverted_number`).
     *
     * @param x The integer to check.
     * @return True if `x` is a palindrome, false otherwise.
     *
     * Time Complexity: O(log10(x)), as we divide the number by 10 in each step.
     * Space Complexity: O(1).
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return x == revertedNumber || x == revertedNumber / 10;
    }
}
