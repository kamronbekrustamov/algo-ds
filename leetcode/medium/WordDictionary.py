class TrieNode:
    """A node in the Trie data structure for the WordDictionary."""
    def __init__(self):
        self.children: dict = {}
        self.is_end_of_word = False


class WordDictionary:
    """
    A data structure that supports adding new words and finding if a string
    matches any previously added string. The search can include a wildcard
    character '.' which matches any single letter.
    """

    def __init__(self):
        """Initializes the WordDictionary with a single root TrieNode."""
        self.root = TrieNode()
        

    def addWord(self, word: str) -> None:
        """Adds a word to the data structure."""
        current_node = self.root
        for char in word:
            current_node = current_node.children.setdefault(char, TrieNode())
        current_node.is_end_of_word = True
        

    def search(self, word: str) -> bool:
        """
        Searches for a word that matches the given pattern.
        The pattern can contain '.' which matches any single character.
        """
        def dfs(index: int, node: TrieNode) -> bool:
            """
            Recursively searches for the word from a given index and node.
            """
            # Base case: If we have processed all characters in the word.
            if index == len(word):
                return node.is_end_of_word

            char = word[index]

            if char == '.':
                # If the character is a wildcard, try all possible children.
                # We only need one of them to lead to a successful match.
                for child_node in node.children.values():
                    if dfs(index + 1, child_node):
                        return True
                # If no child path leads to a match, return False.
                return False
            else:
                # If the character is not a wildcard, check if it exists as a child.
                if char not in node.children:
                    return False
                # Continue the search from the next character and the corresponding child node.
                return dfs(index + 1, node.children[char])
        
        return dfs(0, self.root)
        


# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)