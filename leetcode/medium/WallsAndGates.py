from collections import deque
from typing import List

class Solution:
    def islandsAndTreasure(self, grid: List[List[int]]) -> None:
        """
        Modifies a 2D grid in-place to fill each empty room with its shortest
        distance to the nearest treasure.

        The grid contains:
        -  0: A treasure (a "gate" or "source")
        - -1: An island (a "wall" or "obstacle")
        -  INF (2147483647): An empty room (or "water")

        This function uses a Multi-Source BFS, starting from all treasure
        cells simultaneously to guarantee the shortest path.

        Args:
            grid: The m x n grid, which will be modified in-place.

        Returns:
            None
        """
        
        # --- 1. Initialization and Edge Case Handling ---

        # Check for an empty grid or a grid with no columns
        if not grid or not grid[0]:
            return

        rows = len(grid)
        cols = len(grid[0])
        
        # Define constants for clarity
        INF = 2**31 - 1
        TREASURE = 0
        
        # Define 4-directional movements (up, down, left, right)
        DIRECTIONS = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
        queue = deque()

        # --- 2. Find All Sources (Treasures) ---
        
        # Add all treasure cells (0) to the queue as the starting points
        # for the multi-source BFS. Their distance to a treasure is 0.
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == TREASURE:
                    queue.append((r, c))

        # --- 3. Multi-Source BFS Traversal ---
        
        while queue:
            # Get the next cell to process
            row, col = queue.popleft()
            
            # The value in the grid *is* the current distance
            current_dist = grid[row][col]

            # Explore all 4 neighbors
            for dr, dc in DIRECTIONS:
                nr, nc = row + dr, col + dc  # nr = new_row, nc = new_col

                # --- Check if the neighbor is a valid cell to update ---
                
                # 1. Is it within the grid boundaries?
                if 0 <= nr < rows and 0 <= nc < cols:
                    
                    # 2. Is it an unvisited empty room (INF)?
                    #    We only update cells that are INF. This check implicitly
                    #    handles walls (-1), treasures (0), and rooms
                    #    that have already been reached by a shorter path.
                    if grid[nr][nc] == INF:
                        
                        # Update the room's distance
                        grid[nr][nc] = current_dist + 1
                        
                        # Add this newly visited room to the queue
                        queue.append((nr, nc))

        # No return value is needed as the grid is modified in-place.