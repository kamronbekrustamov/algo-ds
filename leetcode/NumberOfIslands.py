from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        """
        Counts the number of islands in a 2D grid. An island is surrounded by water
        and is formed by connecting adjacent lands horizontally or vertically.
        """
        if not grid or not grid[0]:
            return 0

        rows, cols = len(grid), len(grid[0])
        island_count = 0

        def dfs(r: int, c: int):
            """
            Performs a Depth-First Search to find and "sink" an entire island.
            It marks visited land cells as '0' to avoid recounting.
            """
            # Boundary check: If we are out of bounds or on water, stop the search.
            if not (0 <= r < rows and 0 <= c < cols and grid[r][c] == '1'):
                return

            # Mark the current cell as visited by changing it to '0'.
            grid[r][c] = '0'

            # Explore all 4 adjacent cells (up, down, left, right).
            for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                dfs(r + dr, c + dc)

        # Iterate through each cell in the grid.
        for r in range(rows):
            for c in range(cols):
                # If we find a '1', it's the start of a new, unvisited island.
                if grid[r][c] == '1':
                    # Increment the island count and start a DFS to sink the entire island.
                    island_count += 1
                    dfs(r, c)

        return island_count