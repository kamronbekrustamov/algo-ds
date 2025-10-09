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
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.toLowerCase().split("\\W+");
        Map<String, Integer> wordCounts = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty() && !bannedSet.contains(word)) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        String mostCommonWord = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonWord = entry.getKey();
            }
        }

        return most_common_word;
    }
}
