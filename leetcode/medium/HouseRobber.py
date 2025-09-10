from typing import List

class Solution:
    def rob(self, nums: List[int]) -> int:
        # We can solve this using dynamic programming with constant space.
        # At each house, the max loot is the greater of:
        # 1. Robbing the current house: current_value + loot from two houses ago.
        # 2. Skipping the current house: loot from the previous house.

        # We'll track the max loot from the previous two steps.
        # `rob_two_steps_ago` is the max loot from i-2.
        # `rob_one_step_ago` is the max loot from i-1.
        rob_two_steps_ago, rob_one_step_ago = 0, 0

        for current_value in nums:
            # The new `rob_one_step_ago` is the max of our two choices.
            # The old `rob_one_step_ago` becomes the new `rob_two_steps_ago`.
            rob_two_steps_ago, rob_one_step_ago = rob_one_step_ago, max(current_value + rob_two_steps_ago, rob_one_step_ago)

        return rob_one_step_ago
        