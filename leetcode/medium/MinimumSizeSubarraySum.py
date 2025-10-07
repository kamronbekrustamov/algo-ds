import math
from typing import List


class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        """Finds the minimal length of a contiguous subarray whose sum is >= target.

        This function uses the sliding window technique (two pointers) to achieve an
        optimal O(n) time complexity.

        The 'right' pointer expands the window by including new elements, and the 'left'
        pointer shrinks the window from the beginning once the sum condition is met.
        This avoids the need for nested loops (which would be O(n^2)).

        Args:
            target: The target sum to be met or exceeded.
            nums: A list of positive integers.

        Returns:
            The minimal length of a subarray satisfying the condition. Returns 0 if
            no such subarray is found.
        """
        if not nums:
            return 0

        # Initialize min_length to a value larger than any possible result.
        # Using float('inf') is a clean way to handle this in Python.
        min_length = math.inf
        current_sum = 0
        left = 0

        # The 'right' pointer iterates through the array, expanding the window.
        for right in range(len(nums)):
            # Add the current element to the window's sum.
            current_sum += nums[right]

            # Once the window's sum meets or exceeds the target, try to shrink the
            # window from the left to find the smallest possible valid window.
            while current_sum >= target:
                # Update the minimum length found so far.
                min_length = min(min_length, right - left + 1)

                # Shrink the window by removing the leftmost element and moving the pointer.
                current_sum -= nums[left]
                left += 1

        # If min_length was never updated from its initial infinite value, it means
        # no subarray met the target sum. In that case, return 0 as per the problem.
        return min_length if min_length != math.inf else 0