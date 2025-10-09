class Solution {
    /**
     * Reverses only the letters in a string, leaving non-alphabetic characters
     * in their original positions.
     *
     * This solution uses a two-pointer approach:
     * - `left` pointer starts from the beginning of the string.
     * - `right` pointer starts from the end of the string.
     *
     * Algorithm:
     * 1. Convert the input string `s` into a character array `result` to allow in-place modification.
     * 2. Initialize `left` to 0 and `right` to `s.length() - 1`.
     * 3. Loop as long as `left` is less than `right`:
     *    a. If the character at `result[left]` is not a letter, increment `left`.
     *    b. Else if the character at `result[right]` is not a letter, decrement `right`.
     *    c. Else (both `result[left]` and `result[right]` are letters):
     *       i. Swap `result[left]` and `result[right]`.
     *       ii. Increment `left` and decrement `right`.
     * 4. After the loop, convert the `result` character array back to a `String` and return it.
     *
     * @param s The input string.
     * @return The string with only its letters reversed.
     *
     * Time Complexity: O(N), where N is the length of the string, as we iterate through it once.
     * Space Complexity: O(N) to convert the string to a character array.
     */
    public String reverseOnlyLetters(String s) {
        char[] result = s.toCharArray();
        int left = 0;
        int right = result.length - 1;

        while (left < right) {
            if (!Character.isLetter(result[left])) {
                left++;
            } else if (!Character.isLetter(result[right])) {
                right--;
            } else {
                char temp = result[left];
                result[left] = result[right];
                result[right] = temp;
                left++;
                right--;
            }
        }

        return new String(result);
    }
}
