/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

/**
 * Solution class for the Guess Number Higher or Lower problem.
 * This class extends GuessGame which provides the guess(int num) API.
 */
public class Solution extends GuessGame {
    /**
     * Finds the secret number picked by the system using binary search.
     *
     * @param n The upper bound of the range in which the number is picked.
     * @return The secret number.
     */
    public int guessNumber(int n) {
        // Initialize the search space for binary search.
        // 'low' is the lower bound, and 'high' is the upper bound.
        int low = 1;
        int high = n;

        // Perform binary search until the search space is exhausted.
        while (low <= high) {
            // Calculate the middle of the current search range.
            // Using (low + (high - low) / 2) prevents potential integer overflow
            // compared to (low + high) / 2.
            int middle = low + (high - low) / 2;
            
            // Call the predefined guess API to get feedback on the middle number.
            int result = guess(middle);

            if (result == 0) {
                // The guess is correct. We found the number.
                return middle;
            } else if (result == 1) {
                // The guess is too low, so the secret number must be in the upper half.
                // Adjust the lower bound to search in the range [middle + 1, high].
                low = middle + 1;
            } else { // result == -1
                // The guess is too high, so the secret number must be in the lower half.
                // Adjust the upper bound to search in the range [low, middle - 1].
                high = middle - 1;
            }
        }

        // This line is unreachable given the problem constraints that the number
        // is always within the range [1, n]. It's included to satisfy the
        // method's signature which requires a return value.
        return -1;
    }
}