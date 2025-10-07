from typing import List

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        """
        Calculates the maximum profit that can be obtained by buying and selling a stock multiple times.

        The problem allows for multiple transactions (buy one share, sell it, buy again).
        The key insight is that the total profit can be calculated by accumulating the profit
        from every consecutive day where the price increases. This is a greedy approach.

        For example, if prices are [1, 5, 8], buying at 1 and selling at 8 (profit 7)
        is the same as buying at 1, selling at 5 (profit 4), and then immediately buying
        at 5 and selling at 8 (profit 3). Total profit = 4 + 3 = 7.

        This simplifies the problem to just summing up all positive price differences
        between consecutive days.

        Args:
            prices: A list of integers representing the stock price on consecutive days.

        Returns:
            The maximum possible profit.
        """
        # Handle the edge case where there are fewer than 2 days to make a transaction.
        if not prices or len(prices) < 2:
            return 0

        max_profit = 0
        # Iterate through the prices starting from the second day.
        for i in range(1, len(prices)):
            # If the current day's price is higher than the previous day's price,
            # it represents a profitable transaction (buy yesterday, sell today).
            if prices[i] > prices[i - 1]:
                max_profit += prices[i] - prices[i - 1]

        return max_profit