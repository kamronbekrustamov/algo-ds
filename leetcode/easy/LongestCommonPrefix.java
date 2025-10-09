/**
 * Solution for LeetCode problem "Longest Common Prefix".
 * This class provides a method to find the longest common prefix string amongst an array of strings.
 */
class Solution {

    /**
     * Finds the longest common prefix string amongst an array of strings.
     * If there is no common prefix, returns an empty string.
     *
     * This method uses a vertical scanning approach:
     * 1. It takes the first string in the array as a reference.
     * 2. It iterates through each character of the first string.
     * 3. For each character, it compares it with the character at the same position in all other strings.
     * 4. If a mismatch is found, or if any string is shorter than the current character index,
     *    it means the common prefix ends at the previous character. The substring of the first string
     *    up to the current character index (exclusive) is then returned.
     * 5. If the loop completes without finding any mismatches, it means the entire first string is
     *    the common prefix, and it is returned.
     *
     * @param strs An array of strings.
     * @return The longest common prefix string, or an empty string if no common prefix exists.
     */
    public String longestCommonPrefix(String[] strs) {
        // Handle edge cases: if the array is null or empty, there's no common prefix.
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Iterate through each character of the first string.
        // This character will be compared against all other strings.
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            // Compare the current character 'c' with the character at the same position
            // in all other strings in the array.
            for (int j = 1; j < strs.length; j++) {
                // Two conditions for a mismatch:
                // 1. The current string (strs[j]) is shorter than the current character index 'i'.
                //    This means the common prefix cannot extend further than the length of strs[j].
                // 2. The character at position 'i' in strs[j] does not match 'c'.
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    // If a mismatch is found, the common prefix is the substring of strs[0]
                    // from index 0 up to the current character index 'i' (exclusive).
                    return strs[0].substring(0, i);
                }
            }
        }

        // If the loop completes, it means all characters of strs[0] are common prefixes
        // to all other strings. So, strs[0] itself is the longest common prefix.
        return strs[0];
    }
}
