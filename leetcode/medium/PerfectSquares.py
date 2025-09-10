class Solution:
    def numSquares(self, n: int) -> int:
        """
        Calculates the least number of perfect square numbers that sum to n.
        This is solved using a more efficient bottom-up dynamic programming approach.

        The DP state `dp[i]` represents the minimum number of perfect squares that sum to `i`.
        The recurrence relation is:
        dp[i] = 1 + min(dp[i - j*j]) for all j where j*j <= i.
        """
        # Initialize the DP table. dp[i] will store the result for number i.
        # We can initialize with a value like `n` as the worst case is n = 1+1+...+1.
        dp = [n] * (n + 1)

        # Base case: 0 requires 0 perfect squares.
        dp[0] = 0

        # Iterate from 1 to n to fill the DP table.
        for i in range(1, n + 1):
            # For each number i, try subtracting all possible perfect squares (j*j).
            j = 1
            while j * j <= i:
                # Update dp[i] with the minimum value found so far.
                dp[i] = min(dp[i], 1 + dp[i - j * j])
                j += 1

        return dp[n]
