from typing import List


class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        size = len(nums)
        found = False
        for index, val in enumerate(nums):
            if val == 1:
                found = True
            elif val < 1 or val > size:
                nums[index] = 1
        
        if not found:
            return 1

        for index, val in enumerate(nums):
            nums[abs(val) - 1] = -1 * abs(nums[abs(val) - 1])
        
        for index, val in enumerate(nums):
            if val > 0:
                return index + 1

        return size + 1 
        
        
        