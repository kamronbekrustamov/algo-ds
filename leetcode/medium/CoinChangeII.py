from typing import List


class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        """
        Calculates the number of combinations of coins that make up a given amount.
        This is a classic "unbounded knapsack" problem solved with bottom-up dynamic programming.

        Algorithm:
        1.  Create a DP array `dp` of size `amount + 1`, where `dp[i]` will store the
            number of combinations to make amount `i`.
        2.  Initialize `dp[0] = 1`. This is the base case: there is exactly one way
            to make an amount of 0 (by using no coins).
        3.  Iterate through each `coin` in the `coins` list (the outer loop).
        4.  For each `coin`, iterate through all amounts `i` from the `coin`'s value
            up to the target `amount` (the inner loop).
        5.  For each amount `i`, update `dp[i]` by adding `dp[i - coin]`. This means:
            "The number of ways to make amount `i` is increased by the number of ways
            we could make the amount `i - coin`."

        The key is the order of the loops. By iterating through coins first, we ensure
        that we are building combinations in a fixed order, which prevents counting
        permutations (e.g., we add all combinations involving coin `c1` before ever
        considering coin `c2`, preventing `(c1, c2)` and `(c2, c1)` from being counted separately).
        """
        dp = [0] * (amount + 1)
        dp[0] = 1

        for coin in coins:
            for i in range(coin, amount + 1):
                dp[i] += dp[i - coin]

        return dp[amount]