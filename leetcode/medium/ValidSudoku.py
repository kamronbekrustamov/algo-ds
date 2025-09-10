from typing import List


class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        # Use sets to track seen numbers in each row, column, and 3x3 box.
        # This allows for a single pass through the board with O(1) lookups.
        rows = [set() for _ in range(9)]
        cols = [set() for _ in range(9)]
        boxes = [set() for _ in range(9)]

        for r in range(9):
            for c in range(9):
                num = board[r][c]

                # Skip empty cells
                if num == ".":
                    continue

                # Calculate the index for the 3x3 box (0-8)
                box_idx = (r // 3) * 3 + (c // 3)

                # Check if the number is already present in the current row, col, or box
                if num in rows[r] or num in cols[c] or num in boxes[box_idx]:
                    return False

                # Add the number to the corresponding sets for future checks
                rows[r].add(num)
                cols[c].add(num)
                boxes[box_idx].add(num)

        return True