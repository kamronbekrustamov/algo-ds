/**
 * Solution for LeetCode problem "Merge Strings Alternately".
 * This class provides a method to merge two strings by adding letters alternately, starting with `word1`.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 */
class Solution {

    /**
     * Merges two strings by adding letters alternately, starting with `word1`.
     * If a string is longer than the other, the remaining characters of the longer string
     * are appended to the end of the merged string.
     *
     * This method uses a `StringBuilder` for efficient string concatenation and two pointers
     * to keep track of the current position in each word.
     *
     * @param word1 The first string.
     * @param word2 The second string.
     * @return The merged string.
     */
    public String mergeAlternately(String word1, String word2) {
        // Use StringBuilder for efficient string concatenation, as it avoids creating
        // multiple intermediate String objects.
        StringBuilder result = new StringBuilder();

        // Get lengths of both words.
        int n1 = word1.length();
        int n2 = word2.length();

        // Initialize pointers for both words.
        int p1 = 0, p2 = 0;

        // Continue merging as long as there are characters left in either word.
        while (p1 < n1 || p2 < n2) {
            // Append character from word1 if available.
            if (p1 < n1) {
                result.append(word1.charAt(p1));
                p1++;
            }
            // Append character from word2 if available.
            if (p2 < n2) {
                result.append(word2.charAt(p2));
                p2++;
            }
        }

        // Convert the StringBuilder content to a String and return.
        return result.toString();
    }
}
