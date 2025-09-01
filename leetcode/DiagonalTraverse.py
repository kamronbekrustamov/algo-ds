from typing import List


class Solution:
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        if not mat or not mat[0]:
            return []

        m, n = len(mat), len(mat[0])
        result: List[int] = []
        row, col = 0, 0

        # True for moving up-right, False for moving down-left.
        going_up = True

        for _ in range(m * n):
            result.append(mat[row][col])

            if going_up:
                # When moving up, we prioritize checking the right boundary, then the top boundary.
                # This correctly handles the top-right corner.
                if col == n - 1:  # Hit the right wall
                    going_up = False
                    row += 1
                elif row == 0:  # Hit the top wall
                    going_up = False
                    col += 1
                else:  # Normal up-right move
                    row -= 1
                    col += 1
            else:  # Moving down
                # When moving down, we prioritize checking the bottom boundary, then the left boundary.
                # This correctly handles the bottom-left corner.
                if row == m - 1:  # Hit the bottom wall
                    going_up = True
                    col += 1
                elif col == 0:  # Hit the left wall
                    going_up = True
                    row += 1
                else:  # Normal down-left move
                    row += 1
                    col -= 1
        return result
