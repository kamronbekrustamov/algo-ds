from typing import List


class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        n = len(nums)

        # Use cycle sort to place positive integers in their correct positions.
        # The number `k` should be at index `k-1`.
        # For example, `1` at index `0`, `2` at index `1`, etc.
        i = 0
        while i < n:
            # The correct index for the value nums[i] is nums[i] - 1.
            correct_idx = nums[i] - 1

            # If the number is in the valid range [1, n] and is not in its correct place,
            # swap it. We also check `nums[i] != nums[correct_idx]` to avoid infinite loops
            # when there are duplicate numbers.
            if 0 <= correct_idx < n and nums[i] != nums[correct_idx]:
                nums[i], nums[correct_idx] = nums[correct_idx], nums[i]
            else:
                # If the number is out of range, negative, or already in the correct place,
                # move to the next element.
                i += 1

        # After sorting, find the first position where the index `i` does not hold `i+1`.
        for i in range(n):
            if nums[i] != i + 1:
                return i + 1

        # If all numbers from 1 to n are present and in their correct positions,
        # the first missing positive is n + 1.
        return n + 1