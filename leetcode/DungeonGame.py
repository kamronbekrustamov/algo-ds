from typing import List


class Solution:
    def calculateMinimumHP(self, dungeon: List[List[int]]) -> int:
        """
        Calculates the minimum initial health required for the knight to rescue the princess.
        This is solved using dynamic programming, working backward from the princess's location.

        The DP state `dp[i][j]` represents the minimum health the knight must have upon
        entering cell (i, j) to survive and reach the princess. The input `dungeon`
        array is modified in-place to serve as the DP table.
        """
        m, n = len(dungeon), len(dungeon[0])

        # We iterate from the bottom-right corner (princess) to the top-left (start).
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                # Determine the minimum health required for the next step (either down or right).
                # The knight will choose the path that requires less initial health.
                if i == m - 1 and j == n - 1:
                    # Base case: At the princess's cell, the knight's health after the
                    # encounter must be at least 1.
                    min_health_on_exit = 1
                elif i == m - 1:
                    # On the last row, the only move is right.
                    # The required health is determined by the cell to the right.
                    min_health_on_exit = dungeon[i][j + 1]
                elif j == n - 1:
                    # On the last column, the only move is down.
                    # The required health is determined by the cell below.
                    min_health_on_exit = dungeon[i + 1][j]
                else:
                    # For any other cell, choose the path (down or right) that requires
                    # less health to start with.
                    min_health_on_exit = min(dungeon[i + 1][j], dungeon[i][j + 1])

                # Calculate the health needed *before* entering cell (i, j).
                # Let H be the health needed. Then H + dungeon[i][j] >= min_health_on_exit.
                # So, H >= min_health_on_exit - dungeon[i][j].
                # Since health must always be at least 1, we take max(1, ...).
                health_needed = max(1, min_health_on_exit - dungeon[i][j])

                # Store this required health in our DP table (the dungeon grid itself).
                dungeon[i][j] = health_needed

        # The value at (0, 0) is the minimum initial health required to start the journey.
        return dungeon[0][0]
