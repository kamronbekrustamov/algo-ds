import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Solves the problem of finding the minimum number of extra characters left
 * over when segmenting a string into dictionary words.
 *
 * This implementation uses Dynamic Programming (DP) combined with a
 * **Reversed Trie** for efficient O(1) character-by-character word matching
 * during the DP transition.
 */
class Solution {

    /**
     * Represents a node in the Trie data structure.
     * The Trie stores dictionary words in reverse order to facilitate
     * efficient suffix matching (i.e., finding a dictionary word ending at
     * the current position 'i' in the string 's').
     */
    private class TrieNode {
        // Flag indicating if a valid dictionary word (in reverse) ends at this node.
        public boolean isWordEnd = false;
        // Array of child nodes for the 26 lowercase English letters.
        public TrieNode[] children = new TrieNode[26];

        /**
         * Adds a string (typically a reversed dictionary word) to the Trie.
         * @param reversedWord The word to add, which should be in reverse order.
         */
        public void insert(String reversedWord) {
            TrieNode currentNode = this;
            for (char c : reversedWord.toCharArray()) {
                int index = c - 'a';
                if (currentNode.children[index] == null) {
                    currentNode.children[index] = new TrieNode();
                }
                currentNode = currentNode.children[index];
            }
            currentNode.isWordEnd = true;
        }
    }

    /**
     * Calculates the minimum number of characters that are not part of any word
     * in the dictionary when segmenting the string 's'.
     *
     * The time complexity is O(N^2 + L_dict), where N is the length of 's',
     * and L_dict is the total length of all words in the dictionary.
     * Space complexity is O(N + L_dict).
     *
     * @param s The input string to be segmented.
     * @param dictionary An array of valid words.
     * @return The minimum number of extra characters.
     */
    public int minExtraChar(String s, String[] dictionary) {
        // 1. Build the Reversed Trie
        TrieNode trieRoot = new TrieNode();

        // The words are reversed and inserted into the Trie. This allows us to
        // efficiently check if s[j...i-1] is a valid word by traversing the Trie
        // from the end of the substring (index i-1) backwards to the start (index j).
        for (String word : dictionary) {
            // Using StringBuilder for efficient reversal.
            trieRoot.insert(new StringBuilder(word).reverse().toString());
        }

        int n = s.length();
        // minExtra[i] represents the minimum extra characters found in the prefix s[0...i-1] (length i).
        // The array size is n + 1 for indices 0 to n.
        int[] minExtra = new int[n + 1];
        minExtra[0] = 0; // The empty prefix has 0 extra characters.

        // 2. Dynamic Programming
        // i: The current end index (exclusive) of the prefix being considered, s[0...i-1].
        for (int i = 1; i <= n; i++) {
            // **Base Case (Worst Case):**
            // Assume the i-th character (s[i-1]) is an extra character.
            // Cost is: minExtra for previous prefix (i-1) + 1 (for the new extra char).
            minExtra[i] = minExtra[i - 1] + 1;

            TrieNode currentNode = trieRoot;
            // j: The potential start index (inclusive) of the word ending at i-1.
            // We traverse the string s backwards from i-1 down to 0, simultaneously
            // traversing the Reversed Trie from its root.
            for (int j = i - 1; j >= 0; j--) {
                char currentChar = s.charAt(j);
                int index = currentChar - 'a';

                // Check for character match in the Reversed Trie.
                if (currentNode.children[index] == null) {
                    // Mismatch: No dictionary word can match the substring s[j...i-1].
                    // We can stop checking for this 'i' at this point 'j'.
                    break;
                }

                // Move to the next node in the Reversed Trie.
                currentNode = currentNode.children[index];

                // Check if a dictionary word (in reverse) ends at this node.
                if (currentNode.isWordEnd) {
                    // A full word has been matched: s[j...i-1] is a dictionary word.
                    // The cost to reach minExtra[i] through this path is:
                    // minExtra[j] (cost of prefix s[0...j-1]) + 0 (cost of the matched word).
                    minExtra[i] = Math.min(minExtra[i], minExtra[j]);
                }
                
                // Note: We don't need to consider the "Word Not Found" case here
                // because the base case (minExtra[i] = minExtra[i-1] + 1) and
                // the full string DP from the previous version already cover it.
                // Any unmatched substring s[j...i-1] must have its cost derived
                // from the base case or a smaller valid word ending at some index k < i.
                // The Trie only helps find valid words to set minExtra[i] = minExtra[j].
            }
        }

        // minExtra[n] holds the minimum extra characters for the full string s.
        return minExtra[n];
    }
}