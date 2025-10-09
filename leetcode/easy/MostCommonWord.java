import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solution for LeetCode problem "Most Common Word".
 * This class provides a method to find the most frequently occurring word in a paragraph
 * that is not in a given list of banned words.
 */
class Solution {

    /**
     * Finds the most frequently occurring word in a given paragraph that is not in a list of banned words.
     *
     * The process involves several steps:
     * 1. Convert the `banned` array into a `HashSet` for efficient lookup (O(1) average time complexity).
     * 2. Convert the `paragraph` to lowercase and split it into individual words.
     *    The splitting is done using a regular expression `\\W+` which matches one or more non-word characters.
     *    This effectively removes punctuation and spaces.
     * 3. Iterate through the extracted words:
     *    - For each word, check if it is present in the `banned_set`.
     *    - If the word is not banned, update its frequency in a `HashMap` (`word_counts`).
     * 4. Iterate through the `word_counts` map to find the word with the highest frequency.
     *
     * @param paragraph The input string representing the paragraph.
     * @param banned An array of strings representing the banned words.
     * @return The most common word that is not banned. It is guaranteed that there is at least one non-banned word,
     *         and it is unique.
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        // Convert the banned words array into a HashSet for efficient O(1) average time complexity lookups.
        Set<String> banned_set = new HashSet<>(Arrays.asList(banned));

        // Convert the paragraph to lowercase and split it into words.
        // The regex "\\W+" splits by any non-word character (punctuation, spaces, etc.).
        // This effectively cleans the words from punctuation.
        String[] words = paragraph.toLowerCase().split("\\W+");

        // Use a HashMap to store the frequency of each non-banned word.
        Map<String, Integer> word_counts = new HashMap<>();

        // Iterate through the cleaned words.
        for (String word : words) {
            // Ensure the word is not empty (can happen with multiple delimiters) and not banned.
            if (!word.isEmpty() && !banned_set.contains(word)) {
                // Increment the count for the current word. If it's not in the map, default to 0.
                word_counts.put(word, word_counts.getOrDefault(word, 0) + 1);
            }
        }

        // The problem guarantees at least one non-banned word, so word_counts will not be empty.
        // Find the word with the maximum count.
        String most_common_word = "";
        int max_count = 0;

        // Iterate through the entries in the word_counts map.
        for (Map.Entry<String, Integer> entry : word_counts.entrySet()) {
            // If the current word's count is greater than the current maximum count,
            // update the most common word and the maximum count.
            if (entry.getValue() > max_count) {
                max_count = entry.getValue();
                most_common_word = entry.getKey();
            }
        }

        return most_common_word;
    }
}
