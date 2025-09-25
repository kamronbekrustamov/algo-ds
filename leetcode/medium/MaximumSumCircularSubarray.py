from typing import List

class Solution:
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        """
        Finds the maximum possible sum of a non-empty subarray of a circular array.
        A circular array means the end of the array connects to the beginning.

        This problem can be broken down into two main cases:
        1. The maximum subarray is a non-wrapping subarray (i.e., it's a contiguous
           block within the linear version of the array).
        2. The maximum subarray is a wrapping subarray (i.e., it includes elements
           from both the beginning and the end of the array).

        Kadane's Algorithm is used to solve both cases.
        """
        if not nums:
            return 0

        # --- Case 1: Find the maximum sum of a non-wrapping subarray ---
        # This is the standard Kadane's Algorithm.
        # `global_max` stores the overall maximum sum found so far.
        # `current_max` stores the maximum sum of a subarray ending at the current position.
        global_max = nums[0]
        current_max = nums[0]

        # Iterate through the array starting from the second element.
        for num in nums[1:]:
            # For `current_max`, we decide whether to extend the previous subarray
            # or start a new one. If `current_max` (the sum of the subarray ending
            # at the previous position) is negative, it's better to start a new
            # subarray from the current `num`. Otherwise, extend the existing subarray.
            # This line effectively resets `current_max` to 0 if it was negative,
            # then adds the current `num`.
            current_max = max(0, current_max) + num
            
            # Update the overall maximum sum found so far.
            global_max = max(global_max, current_max)
        
        # --- Case 2: Find the maximum sum of a wrapping subarray ---
        # A wrapping subarray's sum can be found by:
        # (Total sum of all elements) - (Minimum sum of a non-wrapping subarray)
        # This is because the elements *not* included in the wrapping subarray
        # form a non-wrapping subarray with the minimum possible sum.

        # Calculate the total sum of all elements.
        total_sum = sum(nums)

        # `global_min` stores the overall minimum sum found so far.
        # `current_min` stores the minimum sum of a subarray ending at the current position.
        global_min = nums[0]
        current_min = nums[0]

        # Iterate through the array starting from the second element to find the minimum subarray sum.
        for num in nums[1:]:
            # Similar to `current_max`, but for minimums:
            # If `current_min` (the sum of the subarray ending at the previous position)
            # is positive, it's better to start a new subarray from the current `num`
            # to find a smaller sum. Otherwise, extend the existing subarray.
            # This line effectively resets `current_min` to 0 if it was positive,
            # then adds the current `num`.
            current_min = min(0, current_min) + num
            
            # Update the overall minimum sum found so far.
            global_min = min(global_min, current_min)
        
        # --- Final Result ---
        # Edge case: If all numbers are negative (e.g., [-1, -2, -3]),
        # `global_max` will correctly be the largest (least negative) number.
        # In this scenario, `total_sum - global_min` would be 0 (e.g., -6 - (-6) = 0),
        # which is incorrect. So, if `global_max` is negative, it means all numbers
        # are negative, and the answer is `global_max`.
        if global_max < 0:
            return global_max

        # Otherwise, the answer is the maximum of the two cases:
        # 1. The maximum non-wrapping subarray sum (`global_max`).
        # 2. The maximum wrapping subarray sum (`total_sum - global_min`).
        return max(global_max, total_sum - global_min)
