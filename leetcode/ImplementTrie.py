from typing import Optional


class TrieNode:
    """A node in the Trie data structure."""
    def __init__(self):
        # A dictionary mapping characters to child TrieNodes.
        self.children: dict = {}
        # A boolean flag to indicate if this node marks the end of a word.
        self.is_end_of_word = False


class Trie:
    """
    Implementation of a Trie (Prefix Tree) for efficient string storage and retrieval.
    Supports inserting words, searching for full words, and checking for prefixes.
    """

    def __init__(self):
        """Initializes the Trie with a single root node."""
        self.root = TrieNode()

    def insert(self, word: str) -> None:
        """Inserts a word into the trie."""
        current_node = self.root
        for char in word:
            # If the character is not a child of the current node, create a new node.
            # setdefault is a concise way to get the value or insert it if not present.
            current_node = current_node.children.setdefault(char, TrieNode())
        current_node.is_end_of_word = True

    def _find_node(self, prefix: str) -> Optional[TrieNode]:
        """
        Private helper method to traverse the trie for a given prefix.
        Returns the node corresponding to the end of the prefix, or None if not found.
        """
        current_node = self.root
        for char in prefix:
            if char not in current_node.children:
                return None
            current_node = current_node.children[char]
        return current_node

    def search(self, word: str) -> bool:
        """Returns True if the word is in the trie."""
        node = self._find_node(word)
        # The word exists if a node is found AND it's marked as the end of a word.
        return node is not None and node.is_end_of_word

    def startsWith(self, prefix: str) -> bool:
        """Returns True if there is any word in the trie that starts with the given prefix."""
        node = self._find_node(prefix)
        # The prefix exists if a node is found (it doesn't need to be the end of a word).
        return node is not None


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)