from typing import List


class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        """
        Finds the minimum path sum from the top-left to the bottom-right corner of a grid.
        You can only move either down or right at any point in time.

        This solution uses a bottom-up dynamic programming approach, modifying the grid
        in-place to store the minimum path sum to reach each cell. This results in
        O(1) extra space complexity.

        Algorithm:
        1. The value `grid[i][j]` will be updated to represent the minimum path sum
           to reach the cell at `(i, j)`.
        2. Initialize the first row: The path to any cell in the first row can only
           come from the left. So, `grid[0][j] += grid[0][j-1]`.
        3. Initialize the first column: The path to any cell in the first column can
           only come from above. So, `grid[i][0] += grid[i-1][0]`.
        4. For the rest of the cells `(i, j)`, the minimum path must come from either
           the cell above (`grid[i-1][j]`) or the cell to the left (`grid[i][j-1]`).
           We update `grid[i][j]` with its own value plus the minimum of these two.
        5. The final answer is the value in the bottom-right corner, `grid[m-1][n-1]`.

        Time Complexity: O(m * n), where m and n are the dimensions of the grid.
        Space Complexity: O(1), as we modify the grid in-place.
        """
        if not grid or not grid[0]:
            return 0

        m, n = len(grid), len(grid[0])

        # Initialize the first row (paths can only come from the left).
        for j in range(1, n):
            grid[0][j] += grid[0][j - 1]

        # Initialize the first column (paths can only come from above).
        for i in range(1, m):
            grid[i][0] += grid[i - 1][0]

        # Fill in the rest of the grid using the DP relation.
        for i in range(1, m):
            for j in range(1, n):
                grid[i][j] += min(grid[i - 1][j], grid[i][j - 1])

        # The bottom-right corner now holds the minimum path sum.
        return grid[-1][-1]