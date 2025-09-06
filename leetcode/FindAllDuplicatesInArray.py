from typing import List


class Solution:
    def findDuplicates(self, nums: List[int]) -> List[int]:
        """
        Finds all the integers that appear twice in an array of integers.
        The input array contains integers in the range [1, n] where n is the length of the array.

        This solution uses a clever in-place marking technique that achieves O(n) time
        and O(1) extra space complexity.
        """
        result = []
        for num in nums:
            # The numbers are in the range [1, n], so they can be mapped to indices [0, n-1].
            # The index corresponding to the current number `num` is `abs(num) - 1`.
            # We use abs() because the value at an index might have been negated by a previous step.
            index = abs(num) - 1

            # Check the sign of the number at the target index.
            if nums[index] < 0:
                # If the number is already negative, it means we have encountered `abs(num)` before.
                # This indicates that `abs(num)` is a duplicate.
                result.append(abs(num))
            else:
                # If the number is positive, this is the first time we've seen `abs(num)`.
                # We mark it as "seen" by negating the number at its corresponding index.
                nums[index] = -nums[index]

        return result
