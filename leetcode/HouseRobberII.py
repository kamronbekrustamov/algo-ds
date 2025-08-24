from typing import List

class Solution:
    def rob(self, nums: List[int]) -> int:
        # Handle edge cases: no houses, or only one house (no circle).
        if not nums:
            return 0
        if len(nums) == 1:
            return nums[0]

        # Since the first and last houses are adjacent, we can't rob both.
        # This breaks the problem into two independent subproblems:
        # 1. Rob houses from 0 to n-2 (i.e., `nums[:-1]`).
        # 2. Rob houses from 1 to n-1 (i.e., `nums[1:]`).
        # The final answer is the maximum loot from these two scenarios.
        # We use a helper function that solves the standard linear House Robber problem.
        return max(self._rob_linear(nums[:-1]), self._rob_linear(nums[1:]))

    def _rob_linear(self, houses: List[int]) -> int:
        """
        Helper function to solve the original House Robber problem on a linear array
        using constant-space dynamic programming.
        """
        rob_prev, rob_curr = 0, 0
        for value in houses:
            # The new max loot is the greater of:
            # 1. Robbing the current house: value + loot from two houses ago (rob_prev)
            # 2. Skipping the current house: loot from the previous house (rob_curr)
            rob_prev, rob_curr = rob_curr, max(value + rob_prev, rob_curr)
        return rob_curr