from typing import List

class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        """
        Finds the missing number in a sequence from 0 to n.
        This is done by calculating the expected sum of the sequence
        and subtracting the actual sum of the given numbers.
        """
        n = len(nums)
        
        # The sum of numbers from 0 to n is n * (n + 1) / 2
        expected_sum = n * (n + 1) // 2
        
        # The actual sum of the numbers in the list
        actual_sum = sum(nums)
        
        # The difference is the missing number
        return expected_sum - actual_sum

        
                