from typing import List

class Solution:
    def findDisappearedNumbers(self, nums: List[int]) -> List[int]:
        # Mark each number's presence by negating the value at its corresponding index.
        # For a number `n`, we negate the value at index `n-1`.
        # We use abs() because a value at an index might have already been negated.
        for num in nums:
            index_to_mark = abs(num) - 1
            # Use -abs() to ensure the value at the index becomes negative.
            # This is idempotent, meaning running it multiple times has no extra effect.
            nums[index_to_mark] = -abs(nums[index_to_mark])

        # Now, find all indices where the value is still positive. These correspond
        # to the numbers that were never seen in the input array.
        return [i + 1 for i, num in enumerate(nums) if num > 0]
        