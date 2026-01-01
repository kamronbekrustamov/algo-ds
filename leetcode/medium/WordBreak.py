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
        head = TrieNode()
        for word in wordDict:
            head.add(word[::-1])
        
        dp[]