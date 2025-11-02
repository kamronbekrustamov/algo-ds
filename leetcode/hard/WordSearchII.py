from typing import List, Dict, Optional
from collections import defaultdict

# Using a more concise TrieNode by leveraging defaultdict
class TrieNode:
    """A node in the Trie (Prefix Tree)."""
    def __init__(self):
        # Dictionary to store children nodes, keyed by character.
        # defaultdict allows cleaner character checking/insertion.
        self.children: Dict[str, TrieNode] = defaultdict(TrieNode)
        # Stores the complete word if this node marks the end of a word.
        # Storing the word string here avoids reconstructing it during DFS.
        self.word: Optional[str] = None

class Solution:
    """
    Finds all words in a 2D board that are present in a given list of words.
    Uses a combination of Trie and Depth First Search (DFS).
    """

    def _insert(self, root: TrieNode, word: str) -> None:
        """Inserts a word into the Trie."""
        node = root
        for char in word:
            # defaultdict handles node creation if char is not present
            node = node.children[char]
        node.word = word

    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        """
        Main function to find words on the board.
        
        Args:
            board: The m x n grid of characters.
            words: The list of words to search for.
        
        Returns:
            A list of all words found on the board.
        """
        # 1. Build the Trie
        root = TrieNode()
        for word in words:
            self._insert(root, word)
        
        # 2. Setup
        M, N = len(board), len(board[0])
        found_words: List[str] = []

        # Optimization: Directions for neighboring cells (Right, Left, Down, Up)
        DIRECTIONS = [(0, 1), (0, -1), (1, 0), (-1, 0)]

        # 3. Depth First Search with Trie Traversal and Pruning
        
        def dfs(r: int, c: int, parent_node: TrieNode) -> None:
            """
            Performs DFS on the board while simultaneously traversing the Trie.
            
            Args:
                r: Current row index.
                c: Current column index.
                parent_node: The TrieNode *before* consuming the current board[r][c] character.
            """
            # Boundary Check and Trie Check
            if not (0 <= r < M and 0 <= c < N):
                return
            
            char = board[r][c]
            
            # If the current character is not a path in the Trie, stop.
            if char not in parent_node.children:
                return

            # Move to the next Trie Node
            current_node = parent_node.children[char]

            # A. Word Found Check and Pruning
            if current_node.word:
                found_words.append(current_node.word)
                # Key Optimization: Mark the word as found (None) to prevent
                # duplicates and excessive additions, then **immediately prune** # if this node has no other path (children).
                current_node.word = None  # Prevents re-adding this word

            # B. Mark the cell as visited
            board[r][c] = '#'

            # C. DFS on Neighbors
            for dr, dc in DIRECTIONS:
                dfs(r + dr, c + dc, current_node)

            # D. Backtrack: Restore the cell value
            board[r][c] = char

            # E. **Pruning Optimization (After Backtracking)**
            # If the current_node is now a leaf node (has no children and no word),
            # it means all words passing through this path have been found.
            # We can delete this node from its parent to save future traversal time.
            if not current_node.children and not current_node.word:
                del parent_node.children[char]

        # 4. Start DFS from every cell on the board
        for r in range(M):
            for c in range(N):
                dfs(r, c, root)

        return found_words