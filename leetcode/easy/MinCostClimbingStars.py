from typing import List

class Solution:
    @staticmethod
    def minCostClimbingStairs(cost: List[int]) -> int:
        """
        Calculates the minimum cost to reach the top of the floor, given that you can
        climb one or two steps at a time.

        This solution uses a bottom-up dynamic programming approach with constant space.
        We iterate through the costs, keeping track of the minimum cost to reach the
        previous two steps.
        """
        # Initialize costs for the steps before the start (the "floor").
        # cost_two_steps_back: min cost to reach step i-2
        # cost_one_step_back: min cost to reach step i-1
        cost_two_steps_back, cost_one_step_back = 0, 0

        for current_cost in cost:
            # The cost to reach the current step is its own cost plus the minimum
            # of the costs to reach the previous two steps.
            min_cost_to_current = min(cost_one_step_back, cost_two_steps_back) + current_cost
            cost_two_steps_back, cost_one_step_back = cost_one_step_back, min_cost_to_current

        # The final answer is the minimum cost to reach one of the last two steps,
        # as we can take a final hop to the top from either of them.
        return min(cost_one_step_back, cost_two_steps_back)