from typing import List

class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        """
        Calculates the number of unique paths from the top-left to the
        bottom-right corner of a grid, considering obstacles.

        This is solved using dynamic programming with O(n) space optimization.
        """
        m = len(obstacleGrid)
        n = len(obstacleGrid[0])

        # If the starting or ending cell is an obstacle, no path is possible.
        if obstacleGrid[0][0] == 1 or obstacleGrid[m - 1][n - 1] == 1:
            return 0

        # dp[j] will store the number of unique paths to reach cell (i, j).
        # We only need one row's worth of information to calculate the next.
        dp = [0] * n
        
        # Base case: There is 1 way to reach the starting cell (0, 0).
        dp[0] = 1

        # Iterate through each cell of the grid to fill the DP table.
        for i in range(m):
            for j in range(n):
                # If the current cell is an obstacle, no paths can go through it.
                if obstacleGrid[i][j] == 1:
                    dp[j] = 0
                    continue

                # For any other cell, the number of paths is the sum of paths
                # from the cell above and the cell to the left.
                if j > 0:
                    dp[j] += dp[j - 1]

        # The last element in the dp array will be the number of paths to the
        # bottom-right corner.
        return dp[n - 1]