/**
 * Solution class for finding the smallest letter greater than a target.
 */
class Solution {
    /**
     * Finds the smallest character in a sorted array of characters that is strictly greater than a given target character.
     * The array is circular, meaning if the target is greater than or equal to the last character, the result is the first character in the array.
     *
     * @param letters A sorted array of characters.
     * @param target  A character to compare against.
     * @return The smallest character in the array that is greater than the target.
     */
    public char nextGreatestLetter(char[] letters, char target) {
        // The problem guarantees letters.length >= 2.
        int n = letters.length;

        // We use binary search to find the smallest character that is larger than the target.
        // The search space is [0, n]. 'high' is exclusive.
        int low = 0;
        int high = n;

        // The loop continues as long as there is a search space.
        while (low < high) {
            // Calculate the middle index to avoid potential integer overflow.
            int mid = low + (high - low) / 2;

            if (letters[mid] > target) {
                // If the middle character is greater than the target, it's a potential answer.
                // We then search in the left half (including mid) for a possibly smaller character
                // that is still greater than the target.
                high = mid;
            } else {
                // If the middle character is less than or equal to the target,
                // the answer must be in the right half.
                low = mid + 1;
            }
        }

        // After the loop, 'low' points to the index of the smallest character greater than the target.
        // If 'low' is equal to 'n', it means all characters in the array are less than or equal to the target.
        // In this case, due to the circular nature of the array, we should return the first character.
        // The modulo operator '%' elegantly handles this wrap-around case.
        // For example, if low is n, low % n will be 0, returning letters[0].
        return letters[low % n];
    }
}
