from typing import List

class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        result = []
        # Sets for O(1) attack checks.
        occupied_rows = set()
        pos_diagonals = set()  # For a diagonal, r + c is constant.
        neg_diagonals = set()  # For an anti-diagonal, r - c is constant.

        # board[c] = r, where c is the column and r is the row of the queen.
        board = [-1] * n

        def create_board_snapshot():
            """Converts the 1D board representation to a 2D list of strings."""
            snapshot = [("." * n) for _ in range(n)]
            for c, r in enumerate(board):
                # board[c] = r, so place 'Q' at snapshot[r][c]
                snapshot[r] = "." * c + "Q" + "." * (n - c - 1)
            result.append(snapshot)

        def backtrack(col):
            """Recursively place queens column by column."""
            # Base case: If all queens are placed, we have a valid solution.
            if col == n:
                create_board_snapshot()
                return

            # Try placing a queen in each row of the current column.
            for row in range(n):
                # Check if the current position (row, col) is under attack in O(1) time.
                if row in occupied_rows or (row + col) in pos_diagonals or (row - col) in neg_diagonals:
                    continue  # This position is not safe, try the next row.

                # Place the queen.
                board[col] = row
                occupied_rows.add(row)
                pos_diagonals.add(row + col)
                neg_diagonals.add(row - col)

                # Recurse to the next column.
                backtrack(col + 1)

                # Backtrack: Remove the queen to explore other possibilities.
                occupied_rows.remove(row)
                pos_diagonals.remove(row + col)
                neg_diagonals.remove(row - col)
                board[col] = -1

        backtrack(0)
        return result
