class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        """
        Calculates the number of unique paths a robot can take to get from the
        top-left corner to the bottom-right corner of an m x n grid.
        The robot can only move either down or right at any point in time.

        This is solved using dynamic programming with space optimization.
        """
        # The core idea is that the number of paths to reach cell (i, j) is the
        # sum of paths to reach (i-1, j) and paths to reach (i, j-1).
        #
        # To optimize space from O(m*n) to O(n), we only need to store the
        # results of the previous row to calculate the current row.
        
        # We use a 1D array `dp` of size `n`. Initialize it with all 1s.
        # This represents the base case for the first row, where there's only
        # one way to reach each cell (by moving right).
        dp = [1] * n
        
        # Iterate through the grid, starting from the second row (i=1).
        for i in range(1, m):
            # For each row, iterate through the columns, starting from the
            # second column (j=1).
            for j in range(1, n):
                # dp[j] is the cell above, dp[j-1] is the cell to the left.
                dp[j] = dp[j] + dp[j-1]
                
        # The last element of the `dp` array will hold the total number of paths.
        return dp[n-1]
