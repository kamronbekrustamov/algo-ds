from typing import List

class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        """
        Checks for duplicates by comparing the length of the list
        with the length of a set created from it.
        """
        return len(set(nums)) != len(nums)