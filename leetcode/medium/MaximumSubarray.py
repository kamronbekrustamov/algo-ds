from typing import List

class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        """
        Finds the contiguous subarray within a one-dimensional array of numbers
        which has the largest sum. This is solved using Kadane's Algorithm.
        
        Kadane's Algorithm works by iterating through the array and maintaining
        two variables:
        1. `current_max`: The maximum sum of a subarray ending at the current position.
        2. `global_max`: The overall maximum sum found anywhere in the array so far.
        """
        # Handle the edge case of an empty array.
        # Although problem constraints usually guarantee a non-empty array,
        # it's good practice for robustness.
        if not nums:
            return 0

        # Initialize `global_max` and `current_max` with the first element.
        # This ensures correct behavior even if all numbers are negative,
        # in which case the largest single negative number is the answer.
        global_max = nums[0]
        current_max = nums[0]

        # Iterate through the array starting from the second element.
        for num in nums[1:]:
            # For each number, decide whether to extend the current subarray
            # or start a new one.
            # If `current_max` (the sum of the subarray ending at the previous position)
            # is negative, it's better to start a new subarray from the current `num`.
            # Otherwise, extend the existing subarray.
            # This line effectively resets `current_max` to 0 if it was negative,
            # then adds the current `num`.
            current_max = max(0, current_max) + num
            
            # Update `global_max` if the `current_max` (the maximum sum ending
            # at the current position) is greater than the overall maximum found so far.
            global_max = max(global_max, current_max)
            
        # After iterating through all numbers, `global_max` holds the largest sum.
        return global_max
