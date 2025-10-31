import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Solution {

    /**
     * Solves the Word Break II problem using Dynamic Programming.
     *
     * This method determines all possible ways to segment a string 's' into a
     * sequence of words, where each word is present in the dictionary 'wordDict'.
     *
     * @param s The input string to be segmented.
     * @param wordDict A list of words that can be used for segmentation.
     * @return A list of all possible segmented sentences, or an empty list if none exist.
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 1. Handle edge case: If the input string is null or empty, return an empty list.
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. Optimization: Convert the word dictionary to a HashSet for O(1) average time complexity lookups.
        Set<String> wordSet = new HashSet<>(wordDict);

        int n = s.length();

        // 3. Dynamic Programming Array:
        // dp[i] stores a List of all valid sentence breakups for the substring s[0...i-1].
        // Size is n + 1 to account for the empty prefix (index 0) and the full string (index n).
        @SuppressWarnings("unchecked")
        List<String>[] dp = new List[n + 1];

        // 4. Initialize the DP array.
        for (int i = 0; i <= n; i++) {
            dp[i] = new ArrayList<>();
        }

        // Base Case: An empty prefix (length 0) can be considered "breakable"
        // and is represented by an empty string, which acts as the starting point for concatenation.
        dp[0].add("");

        // 5. Populate the DP table from prefix length 1 up to n.
        for (int i = 1; i <= n; i++) { // 'i' is the end index (exclusive) of the current substring s[0...i-1]
            // 'j' is the split point, ranging from 0 to i-1.
            for (int j = 0; j < i; j++) {
                // Get the potential word: s[j...i-1]
                String currentWord = s.substring(j, i);

                // Check two conditions:
                // a) The prefix s[0...j-1] (represented by dp[j]) must be breakable (dp[j] is not empty AND dp[j].get(0) != null is checked by the inner loop)
                // b) The current segment (currentWord) must exist in the dictionary.
                if (!dp[j].isEmpty() && wordSet.contains(currentWord)) {

                    // If both conditions met, combine all valid sentences from dp[j]
                    // with the currentWord and store them in dp[i].
                    for (String sentence : dp[j]) {
                        // The base case (dp[0] = [""]) handles the first word gracefully:
                        // If sentence is "", result is "word".
                        // If sentence is "word1 word2", result is "word1 word2 word3".
                        if (sentence.isEmpty()) {
                            dp[i].add(currentWord);
                        } else {
                            dp[i].add(sentence + " " + currentWord);
                        }
                    }
                }
            }
        }

        // 6. The result for the full string s is stored at the last index.
        return dp[n];
    }
}