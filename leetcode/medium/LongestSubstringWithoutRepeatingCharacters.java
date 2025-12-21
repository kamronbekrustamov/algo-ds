import java.util.HashMap;
import java.util.Map;

/**
 * Solution for the Longest Substring Without Repeating Characters problem.
 * This implementation uses the Sliding Window technique with a HashMap.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, a)), where 'a' is the size of the character set (e.g., 256 for ASCII).
 */
class Solution {
    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * The algorithm uses a sliding window defined by two pointers, 'left' (start of the window)
     * and 'right' (end of the window). A HashMap stores the last seen index of each character.
     * When a repeating character is found, the 'left' pointer is moved to the position
     * immediately after the last occurrence of that character to maintain the non-repeating
     * property of the current window.
     *
     * 
     *
     * @param s The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstring(String s) {
        // Handle null or empty string edge case
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        // Map to store the last seen index of each character in the current window.
        // Key: Character, Value: Index of last occurrence.
        Map<Character, Integer> charIndexMap = new HashMap<>();
        
        // 'left' pointer: marks the start of the current non-repeating substring (window).
        int left = 0;

        // 'right' pointer: marks the end of the current window, iterating through the string.
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 1. Check for repetition
            // If the character is already in the map AND its last seen index (charIndexMap.get(currentChar))
            // is within or after the current window (>= left), it means a repetition occurred within the window.
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                // Move the 'left' pointer to the position right after the last occurrence
                // of the repeating character to shrink the window and exclude the repetition.
                left = charIndexMap.get(currentChar) + 1;
            }

            // 2. Update the character's index
            // Store or update the index of the current character.
            charIndexMap.put(currentChar, right);

            // 3. Update the maximum length
            // The length of the current non-repeating substring is (right - left + 1).
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}