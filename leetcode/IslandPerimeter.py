from typing import List


class Solution:
    def islandPerimeter(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        perimeter = 0
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == 0:
                    continue
                
                # Assume every land cell adds 4 to the perimeter.
                perimeter += 4

                # If the cell above is also land, they share an edge.
                # This edge is not part of the perimeter, so subtract 2.
                if r > 0 and grid[r - 1][c] == 1:
                    perimeter -= 2
                # Do the same for the cell to the left.
                if c > 0 and grid[r][c - 1] == 1:
                    perimeter -= 2
        return perimeter
