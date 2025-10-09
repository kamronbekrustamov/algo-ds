from typing import List

class Solution:
    """Solution for LeetCode problem 'Maximum Product Subarray'."""

    def maxProduct(self, nums: List[int]) -> int:
        """Finds the maximum product of a contiguous subarray within the given array.

        This problem is solved using dynamic programming. The key insight is that
        the maximum product subarray ending at a given position `i` can be formed
        either by extending the maximum product subarray ending at `i-1`, or by
        extending the minimum product subarray ending at `i-1` (if `nums[i]` is negative),
        or by starting a new subarray at `nums[i]`.

        We need to track both the maximum and minimum product ending at the current position
        because a negative number multiplied by another negative number can yield a large positive product.

        Args:
            nums: A list of integers.

        Returns:
            The maximum product of a contiguous subarray.
        """
        if not nums:
            return 0

        # Initialize maxProductEndingHere, minProductEndingHere, and the overall result
        # with the first element of the array.
        maxProductEndingHere = nums[0]
        minProductEndingHere = nums[0]
        overallMaxProduct = nums[0]

        # Iterate through the array starting from the second element.
        for i in range(1, len(nums)):
            currentNum = nums[i]
            
            # When multiplying by a negative number, the roles of max and min products swap.
            # We need to store the previous maxProductEndingHere before it's updated
            # to correctly calculate the new minProductEndingHere.
            tempMax = maxProductEndingHere
            
            # The new maximum product ending at 'i' is the maximum of three possibilities:
            # 1. The current number itself (starting a new subarray).
            # 2. The product of the previous maximum product and the current number.
            # 3. The product of the previous minimum product and the current number
            #    (important if currentNum is negative, turning a large negative into a large positive).
            maxProductEndingHere = max(currentNum, tempMax * currentNum, minProductEndingHere * currentNum)
            
            # The new minimum product ending at 'i' is the minimum of the same three possibilities.
            minProductEndingHere = min(currentNum, tempMax * currentNum, minProductEndingHere * currentNum)
            
            # Update the overall maximum product found so far.
            overallMaxProduct = max(overallMaxProduct, maxProductEndingHere)

        return overallMaxProduct