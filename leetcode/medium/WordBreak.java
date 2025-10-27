import java.util.List;

/**
 * Solves the Word Break problem (LeetCode 139) using Dynamic Programming and a Trie.
 * <p>
 * The problem asks if a given string <code>s</code> can be segmented into
 * one or more words from a dictionary <code>wordDict</code>.
 *
 * @see <a href="https://leetcode.com/problems/word-break/">LeetCode 139: Word Break</a>
 */
class Solution {

    /**
     * A node in the Trie (Prefix Tree).
     * This implementation assumes all words contain only lowercase English letters 'a'-'z'.
     */
    private static class TrieNode {
        /** * Flag to indicate if a complete word ends at this node.
         */
        boolean isWord;
        
        /** * Links to child nodes, one for each letter of the alphabet.
         * The array is <code>final</code>, but its contents are mutable.
         */
        final TrieNode[] children;

        /**
         * Constructs a new, empty Trie node.
         */
        public TrieNode() {
            this.isWord = false;
            this.children = new TrieNode[26]; // For 'a' through 'z'
        }
    }

    /**
     * Inserts a word into the given Trie.
     *
     * @param root The root node of the Trie.
     * @param word The word to insert.
     */
    private static void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        // Mark the end of the word
        curr.isWord = true;
    }

    /**
     * Checks if the string <code>s</code> can be segmented into words from <code>wordDict</code>.
     *
     * <p><b>Approach: Dynamic Programming with a Trie</b></p>
     *
     * <p>This solution uses a boolean DP array, <code>dp[i]</code>, which is <code>true</code>
     * if the prefix of <code>s</code> of length <code>i</code> (i.e., <code>s[0...i-1]</code>)
     * can be segmented, and <code>false</code> otherwise.
     *
     * <p>The base case is <code>dp[0] = true</code>, representing an empty string
     * (a prefix of length 0), which is always "segmentable".
     *
     * <p>To compute <code>dp[i]</code>, we look for a split point <code>j</code> (where <code>0 <= j < i</code>)
     * such that:
     * <ol>
     * <li><code>dp[j]</code> is <code>true</code> (meaning <code>s[0...j-1]</code> can be segmented).</li>
     * <li>The substring <code>s[j...i-1]</code> is a word in the dictionary.</li>
     * </ol>
     * If such a <code>j</code> exists, <code>dp[i]</code> becomes <code>true</code>.
     *
     * <p><b>Trie Optimization:</b>
     * To efficiently check condition (2) for all possible <code>j</code> values, we use a Trie.
     * However, we insert all dictionary words <strong>in reverse</strong> into the Trie.
     *
     * <p>Then, to calculate <code>dp[i]</code>, we iterate <code>j</code> *backward* from <code>i-1</code> down to <code>0</code>.
     * As we iterate, we traverse the reversed-word Trie using the characters <code>s[j]</code>,
     * <code>s[j-1]</code>, etc.
     *
     * <p>If at any point <code>j</code>:
     * <ul>
     * <li>The Trie path ends (<code>temp == null</code>), we stop early (pruning).</li>
     * <li>The current Trie node <code>temp</code> has <code>isWord == true</code> (meaning <code>s[j...i-1]</code>
     * is a reversed word) AND <code>dp[j]</code> is <code>true</code> (meaning the prefix
     * <code>s[0...j-1]</code> is segmentable), then we've found a valid segmentation
     * for <code>s[0...i-1]</code>. We set <code>dp[i] = true</code> and break the inner loop.
     * </ul>
     *
     * <p><b>Further Optimization (Max Length):</b>
     * We track the <code>maxLen</code> (maximum word length) from the dictionary. The inner
     * loop for <code>j</code> only needs to check back <code>maxLen</code> characters,
     * so we add the condition <code>j >= i - maxLen</code>.
     *
     * <p><b>Time Complexity:</b> O(N * M + W * L)
     * <ul>
     * <li><code>N</code> = length of string <code>s</code>.</li>
     * <li><code>M</code> = <b>maximum</b> length of a word in <code>wordDict</code>.</li>
     * <li><code>W</code> = number of words in <code>wordDict</code>.</li>
     * <li><code>L</code> = <b>average</b> length of a word in <code>wordDict</code>.</li>
     * </ul>
     * Building the Trie takes O(W * L).
     * The DP part has an O(N) outer loop and an O(M) inner loop (due to the
     * <code>maxLen</code> optimization). Each step in the inner loop is an O(1) Trie lookup.
     * Total DP time is O(N * M).
     *
     * <p><b>Space Complexity:</b> O(N + W * L)
     * <ul>
     * <li>O(N) for the <code>dp</code> array and <code>charArray</code>.</li>
     * <li>O(W * L) for the Trie (worst case, if words share no prefixes).</li>
     * </ul>
     *
     * @param s The input string to segment.
     * @param wordDict The list of dictionary words.
     * @return <code>true</code> if <code>s</code> can be segmented, <code>false</code> otherwise.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // Handle null or empty string input
        if (s == null || s.isEmpty()) {
            return true;
        }

        final TrieNode root = new TrieNode();
        int maxLen = 0; // Optimization: track max word length

        // Build the Trie with REVERSED words and find maxLen
        for (String word : wordDict) {
            if (word != null && !word.isEmpty()) {
                insert(root, new StringBuilder(word).reverse().toString());
                maxLen = Math.max(maxLen, word.length());
            }
        }

        // If dictionary was empty or only contained empty strings,
        // we can't segment the non-empty string s.
        if (maxLen == 0) {
            return false;
        }

        char[] charArray = s.toCharArray();
        int n = s.length();
        
        // dp[i] will be true if s[0...i-1] (prefix of length i) can be segmented
        boolean[] dp = new boolean[n + 1];
        
        // Base case: An empty string (prefix of length 0) is always segmentable
        dp[0] = true;

        // i represents the length of the prefix we are checking (s[0...i-1])
        for (int i = 1; i <= n; i++) {
            TrieNode temp = root;
            
            // Iterate j backward from the end of the current prefix (i-1)
            // We are checking if the suffix s[j...i-1] is a word in the dict
            //
            // OPTIMIZATION: We only need to check back as far as the max word length.
            // Any j < i - maxLen would correspond to a word s[j...i-1]
            // that is longer than any word in the dictionary.
            for (int j = i - 1; j >= 0 && j >= i - maxLen; j--) {
                
                // Traverse the Trie using the characters of s in reverse
                int index = charArray[j] - 'a';
                temp = temp.children[index];

                if (temp == null) {
                    // This path (reversed s[j...i-1]) doesn't exist in the Trie
                    // No word ends at i-1 with a start index <= j
                    break;
                }

                // If s[0...j-1] is segmentable (dp[j] is true)
                // AND s[j...i-1] is a word in the dict (temp.isWord is true)
                if (dp[j] && temp.isWord) {
                    // Then s[0...i-1] is also segmentable
                    dp[i] = true;
                    
                    // Found a valid segmentation, no need to check smaller j's
                    break;
                }
            }
            // If the inner loop finishes without setting dp[i] = true,
            // it remains false by default.
        }

        // The final answer is whether the full string s[0...n-1] is segmentable
        return dp[n];
    }
}