/**
 * A highly-optimized Trie implementation for a fixed, small alphabet
 * (e.g., only lowercase English letters 'a-z').
 */
class Trie {

    private static class TrieNode {
        boolean isWord;
        // Use an array of size 26 for 'a' through 'z'
        final TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26]; // 26 letters in the alphabet
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Map 'a'->0, 'b'->1, ...
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isWord = true;
    }
    
    /** Helper to find the node for a given string */
    private TrieNode findNode(String str) {
        TrieNode curr = root;
        for (char c : str.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return null; // Path doesn't exist
            }
            curr = curr.children[index];
        }
        return curr;
    }

    public boolean search(String word) {
        TrieNode node = findNode(word);
        return (node != null && node.isWord);
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = findNode(prefix);
        return (node != null);
    }
}