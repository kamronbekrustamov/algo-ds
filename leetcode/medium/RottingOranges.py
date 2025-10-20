from typing import List
from collections import deque


class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        """
        Calculates the minimum time (minutes) required to rot all fresh oranges
        in a grid.

        This function uses a Multi-Source Breadth-First Search (BFS) to simulate
        the rotting process layer by layer, where each layer represents one minute.

        Grid values are defined as:
        - 0: Empty cell
        - 1: Fresh orange
        - 2: Rotten orange

        Args:
            grid: An m x n grid representing the state of oranges.

        Returns:
            - The minimum minutes required to rot all oranges.
            - -1 if some fresh oranges are unreachable and cannot be rotted.
            -  0 if there are no fresh oranges to begin with.
        """

        # --- 1. Initialization ---

        # Check for an empty grid or a grid with no columns
        if not grid or not grid[0]:
            return 0

        rows = len(grid)
        cols = len(grid[0])

        # Define constants for grid values (improves readability)
        FRESH = 1
        ROTTEN = 2

        # 4-directional movements: up, down, left, right
        DIRECTIONS = [(-1, 0), (1, 0), (0, -1), (0, 1)]

        queue = deque()
        fresh_oranges_count = 0

        # --- 2. First Pass: Find Sources and Count Fresh Oranges ---
        
        # Scan the grid ONCE to:
        # 1. Add all initially rotten oranges (sources) to the queue.
        # 2. Count the total number of fresh oranges.
        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == ROTTEN:
                    # Add rotten oranges as starting points for the BFS
                    queue.append((r, c))
                elif grid[r][c] == FRESH:
                    # Keep track of the total fresh oranges
                    fresh_oranges_count += 1

        # --- 3. Handle Edge Case: No Fresh Oranges ---
        
        # If there are no fresh oranges, no time is needed.
        if fresh_oranges_count == 0:
            return 0

        minutes_elapsed = 0

        # --- 4. Multi-Source BFS Traversal (Simulate Rotting) ---

        # Continue the BFS as long as there are:
        # 1. Rotten oranges still in the queue to spread rot.
        # 2. Fresh oranges left to be rotted.
        while queue and fresh_oranges_count > 0:

            # 'size' represents all oranges rotting at this specific minute.
            # Processing level by level ensures we track time correctly.
            size = len(queue)

            for _ in range(size):
                row, col = queue.popleft()

                # Check all 4 neighbors
                for dr, dc in DIRECTIONS:
                    nr, nc = row + dr, col + dc  # (new_row, new_col)

                    # Check if the neighbor is in bounds AND is a fresh orange
                    if 0 <= nr < rows and 0 <= nc < cols and grid[nr][nc] == FRESH:
                        
                        # 1. Rot the orange
                        grid[nr][nc] = ROTTEN
                        
                        # 2. Decrement the fresh count
                        fresh_oranges_count -= 1
                        
                        # 3. Add it to the queue to spread rot in the *next* minute
                        queue.append((nr, nc))

            # After processing all nodes at the current level (minute),
            # increment the minute counter.
            minutes_elapsed += 1

        # --- 5. Final Result ---

        # After the BFS, if fresh_oranges_count is 0, all oranges were rotted.
        # Otherwise, some were unreachable.
        return minutes_elapsed if fresh_oranges_count == 0 else -1