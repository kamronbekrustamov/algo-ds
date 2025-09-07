from typing import List


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        """
        Calculates the fewest number of coins needed to make up a given amount.
        This is solved using bottom-up dynamic programming.
        """
        # dp[i] will store the minimum number of coins to make amount i.
        # Initialize with a value representing infinity. `float('inf')` is a
        # clear way to represent an unreachable state.
        dp = [float('inf')] * (amount + 1)

        # Base case: 0 coins are needed to make an amount of 0.
        dp[0] = 0

        # Sorting the coins allows for a small optimization in the inner loop.
        coins.sort()

        # Iterate through all amounts from 1 to `amount` to fill the DP table.
        for i in range(1, amount + 1):
            # For each amount, try every coin.
            for coin in coins:
                # If the current coin is larger than the target amount `i`,
                # all subsequent coins will also be larger, so we can stop checking.
                if coin > i:
                    break
                # Update the DP table for amount `i`. The number of coins is
                # 1 (the current coin) + the minimum coins for the remaining amount.
                dp[i] = min(dp[i], 1 + dp[i - coin])

        # If dp[amount] is still infinity, it means the amount cannot be formed.
        result = dp[amount]
        return int(result) if result != float('inf') else -1