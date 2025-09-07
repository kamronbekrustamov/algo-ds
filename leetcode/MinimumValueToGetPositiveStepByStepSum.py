from typing import List


class Solution:
    def minStartValue(self, nums: List[int]) -> int:
        """
        Calculates the minimum positive startValue such that the step-by-step sum
        is never less than 1.

        This is solved by finding the minimum prefix sum during a single pass.
        The startValue must be large enough to ensure this minimum sum is at least 1.
        """
        # We need to find the minimum possible value of the running sum.
        # Let's track the running sum and its minimum value encountered so far.
        min_running_sum = 0  # Initialize to 0, handles cases where all sums are positive.
        running_sum = 0

        for num in nums:
            running_sum += num
            min_running_sum = min(min_running_sum, running_sum)

        # Let the minimum start value be `s`.
        # We need the condition `s + running_sum >= 1` to hold for all steps.
        # This is equivalent to `s + min_running_sum >= 1`.
        # Therefore, the minimum `s` must be `1 - min_running_sum`.
        #
        # If `min_running_sum` is 0 (or positive), the result is 1.
        # If `min_running_sum` is negative (e.g., -5), the result is `1 - (-5) = 6`.
        return 1 - min_running_sum
