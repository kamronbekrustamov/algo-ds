class Solution {
    /**
     * Reverses a character array (string) in-place using a two-pointer approach.
     *
     * This method initializes two pointers, one at the beginning (`left`) and one
     * at the end (`right`) of the array. It then iterates as long as the left
     * pointer is less than the right pointer, swapping the elements at these
     * positions and moving the pointers towards the center.
     *
     * Algorithm:
     * 1. Initialize `left` pointer to 0 and `right` pointer to `s.length - 1`.
     * 2. Loop while `left` is less than `right`:
     *    a. Swap the characters at `s[left]` and `s[right]`.
     *    b. Increment `left` by 1.
     *    c. Decrement `right` by 1.
     * 3. The array `s` will be reversed in-place.
     *
     * @param s The character array (string) to be reversed.
     *
     * Time Complexity: O(N), where N is the number of elements in the array, as we iterate through half of it.
     * Space Complexity: O(1), as the reversal is done in-place.
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
