from typing import List

class TrieNode:
    def __init__(self) -> None:
        self.is_word = False
        self.chars = {}

    def add(self, word):
        temp = self
        for char in word:
            if char not in temp.chars:
                temp.chars[char] = TrieNode()
            temp = temp.chars[char]
        temp.is_word = True

class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        # 1. Build the Trie with reversed words
        root = TrieNode()
        for word in wordDict:
            root.add(word[::-1])
        
        n = len(s)
        # 2. Initialize DP array
        # dp[i] will be True if s[:i] is a valid sentence
        dp = [False] * (n + 1)
        dp[0] = True
        
        # 3. Iterate through the string
        for i in range(1, n + 1):
            # For each position i, look backwards using the Trie
            curr = root
            # Traverse backwards from i-1 down to 0
            for j in range(i - 1, -1, -1):
                char = s[j]
                
                # If the character doesn't match a path in the Trie,
                # then no word ends at i starting from/before j.
                if char not in curr.chars:
                    break
                
                curr = curr.chars[char]
                
                # If we found a word in the Trie (meaning s[j:i] is in wordDict)
                # AND the prefix before this word (s[:j]) was valid...
                if curr.is_word and dp[j]:
                    dp[i] = True
                    break # Optimization: found a valid break for i, move to next i
        
        return dp[n]