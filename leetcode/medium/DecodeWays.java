/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Given a non-empty string `s` containing only digits, determine the total number of ways to decode it.
 *
 * This problem is solved using Dynamic Programming (DP) with space optimization.
 * The core idea is that the number of ways to decode a string up to index `i` (let's call it `dp[i]`)
 * depends on the number of ways to decode up to `i-1` and `i-2`.
 *
 * The transition is:
 * dp[i] = (s[i] decodes alone) * dp[i-1] + (s[i-1]s[i] decodes together) * dp[i-2]
 */
class Solution {
    /**
     * Calculates the number of ways to decode a string of digits.
     *
     * @param s The non-empty string containing only digits.
     * @return The total number of ways to decode the string.
     */
    public int numDecodings(String s) {
        // --- 1. Base Case / Edge Checks ---
        // If the string is null, empty, or starts with '0' (which cannot be decoded), return 0.
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        // --- 2. Initialize DP Variables ---
        // 'twoBack' represents dp[i-2]: The number of ways to decode the substring ending at index i-2.
        // For i=1, i-2 is -1. We treat dp[-1] as 1 (one way to decode an empty prefix).
        int twoBack = 1;

        // 'oneBack' represents dp[i-1]: The number of ways to decode the substring ending at index i-1.
        // Since s.charAt(0) is not '0', dp[0] is 1 (one way to decode the first digit).
        int oneBack = 1;

        // --- 3. DP Iteration ---
        // Iterate from the second digit (index 1) up to the end of the string.
        for (int i = 1; i < s.length(); i++) {
            // 'current' will store dp[i]: The number of ways to decode the substring ending at index i.
            int current = 0;

            // Get the current single digit and the two-digit combination (last two digits).
            char digit1 = s.charAt(i);     // The single digit at index i
            char digit2 = s.charAt(i - 1); // The digit at index i-1

            // --- Case A: Single Digit Decoding (s[i]) ---
            // If the current digit is not '0', it can be decoded independently (1-9).
            // This adds dp[i-1] ways to the total count (current = oneBack).
            if (digit1 != '0') {
                current = oneBack;
            }
            // Note: If s[i] is '0', current remains 0 from this step.

            // --- Case B: Two-Digit Decoding (s[i-1]s[i]) ---
            // Check if the two-digit number formed by s[i-1]s[i] is between 10 and 26 (inclusive).
            // A simple way to check this *without* creating a String/Integer object for slicing:
            // 1. If s[i-1] is '1', the value is 10-19 (always valid).
            // 2. If s[i-1] is '2', s[i] must be '0' through '6' (20-26).

            boolean isValidTwoDigit = (digit2 == '1') || (digit2 == '2' && digit1 >= '0' && digit1 <= '6');

            if (isValidTwoDigit) {
                // If valid, this two-digit group can be decoded, adding dp[i-2] ways (twoBack)
                // to the total count.
                current += twoBack;
            }
            
            // If `current` is 0 at this point, it means no valid decoding exists up to index i.
            // This happens if:
            // 1. s[i] is '0' AND s[i-1]s[i] is not 10 or 20. (e.g., '30', '40', etc.)
            // 2. s[i] is NOT '0' AND s[i-1]s[i] is invalid (e.g., '31', '27', etc.)
            // In a standard DP, `current` being 0 here would lead to the final result being 0.
            // Since we're reassigning variables, we don't explicitly check and return 0,
            // but the subsequent steps will naturally lead to a final result of 0 if a '0' is encountered.

            // --- 4. Update DP Variables ---
            // Move the window:
            // The number of ways ending at i-1 (oneBack) becomes the new i-2 (twoBack).
            twoBack = oneBack;
            // The number of ways ending at i (current) becomes the new i-1 (oneBack).
            oneBack = current;
        }

        // --- 5. Final Result ---
        // 'oneBack' holds the result for dp[s.length() - 1], which is the total number of ways.
        return oneBack;
    }
}