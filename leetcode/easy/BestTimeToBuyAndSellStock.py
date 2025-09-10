from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        """
        Calculates the maximum profit that can be achieved by buying a stock on one day
        and selling it on a later day.

        This solution uses a single pass (O(n)) approach to find the maximum profit.
        It keeps track of the minimum price encountered so far and calculates the
        potential profit at each day.
        """
        min_price = float('inf')
        max_profit = 0

        for price in prices:
            # Update the minimum price seen so far.
            min_price = min(min_price, price)
            # Calculate potential profit and update the maximum profit.
            max_profit = max(max_profit, price - min_price)

        return max_profit
        