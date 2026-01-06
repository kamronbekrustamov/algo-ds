import java.util.Set;

/**
 * Counts the number of "good" rotated digits X such that 1 <= X <= N.
 * A number is "good" if its 180-degree rotation is a valid number Y and Y != X.
 * * Time Complexity: O(N * log10(N)) - Acceptable for N up to 10^4.
 */
class Solution {
    
    // Digits that result in an invalid number upon rotation: {3, 4, 7}
    private static final Set<Character> INVALID_DIGITS = Set.of('3', '4', '7');
    
    // Digits that rotate to a different valid digit, ensuring Y != X: {2, 5, 6, 9}
    private static final Set<Character> CHANGING_DIGITS = Set.of('2', '5', '6', '9');

    /**
     * @param n The upper limit N.
     * @return The count of good rotated numbers X such that 1 <= X <= N.
     */
    public int rotatedDigits(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Checks if a number X is a "good" rotated number.
     * A number is "good" if:
     * 1. It contains NO INVALID_DIGITS (3, 4, 7).
     * 2. It contains AT LEAST ONE CHANGING_DIGIT (2, 5, 6, 9).
     * * @param num The number to check.
     * @return true if the number is a "good" rotated number, false otherwise.
     */
    private boolean isGood(int num) {
        String s = String.valueOf(num);
        boolean containsChangingDigit = false;

        for (char c : s.toCharArray()) {
            // Condition 1: Check for invalid digits. If found, it's not a good number.
            if (INVALID_DIGITS.contains(c)) {
                return false;
            }
            
            // Condition 2: Check for changing digits.
            if (CHANGING_DIGITS.contains(c)) {
                containsChangingDigit = true;
            }
        }
        
        // Must pass Condition 1 (no return false) AND meet Condition 2 (containsChangingDigit == true).
        return containsChangingDigit;
    }
}