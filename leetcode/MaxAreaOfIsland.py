from typing import List

class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        if not grid or not grid[0]:
            return 0

        height = len(grid)
        width = len(grid[0])

        def dfs(row: int, col: int) -> int:
            # Boundary and visited check.
            if not (0 <= row < height and 0 <= col < width and grid[row][col] == 1):
                return 0

            # Mark the cell as visited by setting it to 0 to prevent recounting.
            grid[row][col] = 0

            # Explore all 4 directions and sum up the area.
            current_area = 1
            for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                current_area += dfs(row + dr, col + dc)
            return current_area

        max_area = 0
        for r in range(height):
            for c in range(width):
                if grid[r][c] == 1:
                    max_area = max(max_area, dfs(r, c))
        return max_area
