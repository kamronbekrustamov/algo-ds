from typing import List

class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        """
        Calculates the sum of XOR totals for all possible subsets of the input array.

        This solution uses a recursive (backtracking) approach to explore all subsets.
        For each element in the array, we make two choices:
        1. Include the element in the current subset.
        2. Exclude the element from the current subset.

        The function recursively builds the XOR total for all subsets and sums them up.

        Time Complexity: O(2^n), as we explore every possible subset.
        Space Complexity: O(n) due to the recursion depth.

        Args:
            nums: A list of integers.

        Returns:
            The sum of the XOR totals of all subsets.
        """

        def _calculate_sum(index: int, current_xor_total: int) -> int:
            """
            A recursive helper function to calculate the sum of XOR totals.

            Args:
                index: The current index in the `nums` array to consider.
                current_xor_total: The XOR total of the subset built so far.

            Returns:
                The sum of XOR totals for all subsets that can be formed from this point.
            """
            # Base case: If we have considered all numbers, the XOR total of the
            # current subset is complete. Return it to be added to the overall sum.
            if index == len(nums):
                return current_xor_total

            # --- Recursive Step ---
            # For each element `nums[index]`, we explore two branches:

            # 1. Include `nums[index]` in the current subset.
            #    We update the XOR total by XORing it with the current number.
            #    Then, we recurse to the next element.
            sum_with_current = _calculate_sum(index + 1, current_xor_total ^ nums[index])

            # 2. Exclude `nums[index]` from the current subset.
            #    The XOR total remains the same.
            #    Then, we recurse to the next element.
            sum_without_current = _calculate_sum(index + 1, current_xor_total)

            # The total sum from this point is the sum of the results from both branches.
            return sum_with_current + sum_without_current

        # Start the recursion from the first element (index 0) with an initial XOR total of 0.
        return _calculate_sum(0, 0)


# --- Alternative O(n) Linear Time Solution ---
#
# class Solution:
#     def subsetXORSum_linear(self, nums: List[int]) -> int:
#         """
#         Calculates the sum of XOR totals for all subsets in linear time.
#
#         Mathematical Insight:
#         Consider the contribution of a single bit position (e.g., the i-th bit)
#         to the final sum across all subsets.
#
#         Let's say at least one number in `nums` has the i-th bit set.
#         - For a subset's XOR total to have the i-th bit set, an ODD number of
#           elements in that subset must have their i-th bit set.
#         - Let `n` be the total number of elements. There are `2^n` subsets in total.
#         - It can be shown that if at least one number has the i-th bit set,
#           exactly half of the subsets (`2^(n-1)`) will have an odd number of
#           elements with that bit set.
#         - Therefore, for `2^(n-1)` subsets, their XOR total will have the i-th bit set.
#
#         The contribution of the i-th bit to the final sum is:
#         (Number of subsets where this bit is 1) * (Value of this bit)
#         = (2^(n-1)) * (2^i)
#
#         The final sum is the sum of these contributions for all bit positions `i`
#         that are set in at least one number in `nums`.
#
#         This is equivalent to:
#         (OR of all numbers) * (2^(n-1))
#
#         The OR of all numbers (`bitwise_or_total`) gives us a number where the
#         i-th bit is 1 if and only if the i-th bit was set in at least one of the
#         original numbers.
#
#         Time Complexity: O(n) to compute the bitwise OR of all numbers.
#         Space Complexity: O(1).
#         """
#         n = len(nums)
#         bitwise_or_total = 0
#         for num in nums:
#             bitwise_or_total |= num
#
#         # The multiplier is 2^(n-1)
#         multiplier = 1 << (n - 1)
#
#         return bitwise_or_total * multiplier