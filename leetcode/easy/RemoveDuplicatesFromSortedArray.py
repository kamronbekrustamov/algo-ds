from typing import List

class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        slow = 0
        for num in nums:
            if slow < 1 or num != nums[slow - 1]:
                nums[slow] = num
                slow += 1
        return slow