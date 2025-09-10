from typing import List


class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        height = len(board)
        width = len(board[0])

        def dfs(r, c, index):
            """
            Performs a depth-first search to find the word starting from (r, c).
            """
            # Base case for success: if we have found all characters in the word.
            if index == len(word):
                return True

            # Base cases for failure:
            # 1. Out of bounds.
            # 2. Current board cell doesn't match the character we're looking for.
            if not (0 <= r < height and 0 <= c < width and board[r][c] == word[index]):
                return False

            # Mark the cell as visited by temporarily changing it. This avoids using
            # an extra 'visited' matrix and is O(1) space for the path.
            temp = board[r][c]
            board[r][c] = '#'  # Any character not in the original board works

            # Explore neighbors (up, down, left, right) recursively.
            found = False
            for dr, dc in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                if dfs(r + dr, c + dc, index + 1):
                    found = True
                    break  # Early exit if path is found

            # Backtrack: restore the original character so it can be used in other paths.
            board[r][c] = temp

            return found

        # Start a DFS from every cell in the board.
        for r in range(height):
            for c in range(width):
                # We only need to start the search if the first character matches.
                if board[r][c] == word[0] and dfs(r, c, 0):
                    return True

        return False
