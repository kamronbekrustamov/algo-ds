/**
 * Given a string s and an integer k, return the length of the longest substring 
 * you can get after performing at most k character replacements.
 * The string s consists only of uppercase English letters.
 */
class Solution {
    /**
     * Finds the length of the longest substring with the same characters after at most k replacements.
     * Uses the Sliding Window technique.
     *
     * @param s The input string, consisting only of uppercase English letters.
     * @param k The maximum number of characters that can be replaced.
     * @return The length of the longest repeating character substring.
     */
    public int characterReplacement(String s, int k) {
        // Since the string contains only uppercase English letters ('A'-'Z'), 
        // we can use a fixed-size array of 26 to store character frequencies 
        // instead of a HashMap for slight performance and space optimization.
        // The index is calculated by char - 'A'.
        int[] frequency = new int[26]; 
        int maxLength = 0; // The result: the length of the longest valid substring found so far.
        int maxFreq = 0;   // The maximum frequency of any character in the current window (s[left...right]).
        int left = 0;      // The left pointer of the sliding window.

        // Iterate with the right pointer to expand the window.
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // 1. Expand the window and update frequency.
            // (currentChar - 'A') gives an index from 0 to 25.
            frequency[currentChar - 'A']++; 
            
            // 2. Update the maximum frequency in the current window.
            // This is crucial: we only care about the *highest* frequency 
            // because replacing the *other* characters is the most efficient strategy.
            maxFreq = Math.max(maxFreq, frequency[currentChar - 'A']);

            // 3. Check the window validity (Shrink if invalid).
            // A window is invalid if: (Window Length - Max Frequency) > k
            // The term (right - left + 1) is the current window length.
            // (right - left + 1 - maxFreq) is the count of characters that are NOT the most frequent one.
            // If this count is greater than k, we need more than k replacements, so the window is invalid.
            if (right - left + 1 - maxFreq > k) {
                // Shrink the window from the left.
                char leftChar = s.charAt(left);
                // Decrement the frequency of the character leaving the window.
                frequency[leftChar - 'A']--; 
                // Move the left pointer to the right.
                left++;
                
                // NOTE: We don't need to recalculate maxFreq after shrinking the window.
                // Why? Even if maxFreq is now lower in the smaller window, any *longer* // valid substring will have a length greater than or equal to the *current* maxLength. 
                // The maxLength will only be updated when the window *expands* to a new maximum 
                // length, which only happens when maxFreq increases or stays the same 
                // relative to the window size. Therefore, maxFreq only needs to track 
                // the historical maximum *within any window of length maxLength or greater*.
            }
            
            // 4. Update the overall maximum length.
            // This happens on every iteration, but it will only increase when the window is valid 
            // and has expanded beyond the previous maxLength.
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}