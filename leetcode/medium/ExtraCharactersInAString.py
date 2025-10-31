from collections import defaultdict
from typing import List

class Solution:
    """
    LeetCode 2707: Extra Characters in a String.
    
    This solution uses Dynamic Programming (DP) optimized with a 
    Reversed Trie (Prefix Tree) to achieve O(N^2) time complexity, 
    where N is the length of the string s.
    """

    class TrieNode:
        """Represents a node in the Trie."""
        def __init__(self) -> None:
            # Using defaultdict is slightly cleaner than `if char not in` checks
            self.children = defaultdict(lambda: Solution.TrieNode())
            self.is_end_of_word = False

    def build_reversed_trie(self, dictionary: List[str]) -> TrieNode:
        """
        Builds a Trie by inserting the reverse of each word in the dictionary.
        This allows efficient lookup of dictionary words *ending* at the current index i.
        """
        root = self.TrieNode()
        for word in dictionary:
            node = root
            # Insert the word in reverse order
            for char in reversed(word):
                node = node.children[char]
            node.is_end_of_word = True
        return root

    def minExtraChar(self, s: str, dictionary: List[str]) -> int:
        n = len(s)
        if n == 0:
            return 0
        
        # Step 1: Build the Reversed Trie
        trie_root = self.build_reversed_trie(dictionary)

        # Step 2: Initialize DP Array
        # dp[i] stores the minimum number of extra characters for the prefix s[0...i-1].
        # Length is n + 1 for indices 0 to n.
        # Initialize with the worst-case scenario (all characters are extra).
        dp = [0] * (n + 1)
        # dp[0] is already 0 (empty prefix has 0 extra chars).

        # Step 3: DP Calculation
        for i in range(1, n + 1):
            
            # Choice A: The current character s[i-1] is an extra character.
            # Cost = cost up to the previous position + 1 (for the current extra char).
            # This is the initial and baseline cost for dp[i].
            dp[i] = dp[i-1] + 1
            
            # Choice B: Try to end a dictionary word at s[i-1].
            # We traverse the reversed string s[0...i-1] backward using the Trie.
            trie_node = trie_root
            for j in range(i - 1, -1, -1):
                char = s[j]
                
                # Try to move down the Trie with s[j] (which is the first/next character
                # when reading the potential word backward).
                if char not in trie_node.children:
                    # No dictionary word can be formed by starting this segment at index j.
                    # Break the backward search for efficiency.
                    break
                
                trie_node = trie_node.children[char]
                
                # If we've found the end of a word:
                if trie_node.is_end_of_word:
                    # The word is s[j...i-1]. The cost is dp[j] (cost of the prefix before the word).
                    # We compare this cost with the current minimum cost for dp[i].
                    dp[i] = min(dp[i], dp[j])

        # The result is the minimum extra characters for the entire string s[0...n-1].
        return dp[n]