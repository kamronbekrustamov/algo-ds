import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> banned_set = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.toLowerCase().split("\\W+");
        Map<String, Integer> word_counts = new HashMap<>();

        for (String word : words) {
            if (!banned_set.contains(word)) {
                word_counts.put(word, word_counts.getOrDefault(word, 0) + 1);
            }
        }

        if (word_counts.isEmpty()) {
            return "";
        }

        String most_common_word = "";
        int max_count = 0;
        for (Map.Entry<String, Integer> entry : word_counts.entrySet()) {
            if (entry.getValue() > max_count) {
                max_count = entry.getValue();
                most_common_word = entry.getKey();
            }
        }

        return most_common_word;
    }
}
