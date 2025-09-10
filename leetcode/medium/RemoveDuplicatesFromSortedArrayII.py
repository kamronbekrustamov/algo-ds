from typing import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        # 'k' is the slow pointer. It represents both the index for the next
        # valid element and the length of the resulting array.
        k = 0
        for num in nums:
            # The condition to place 'num' at index 'k' is:
            # 1. We have placed fewer than 2 elements so far (k < 2).
            # 2. The current number 'num' is different from the number at nums[k-2].
            #    This prevents adding a third duplicate of a number.
            if k < 2 or num != nums[k - 2]:
                nums[k] = num
                k += 1
        return k