from typing import List

class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        """
        Finds the pivot index in an array.
        A pivot index is the index where the sum of all the numbers strictly to the left
        of the index is equal to the sum of all the numbers strictly to the index's right.
        If no such index exists, return -1.
        """
        # Initialize 'left_sum' to 0, representing the sum of elements to the left of the current index.
        # Initialize 'right_sum' to the total sum of all elements, representing the sum of elements
        # to the right of the current index (before subtracting the current element).
        left_sum, right_sum = 0, sum(nums)
        
        for i, num in enumerate(nums):
            # Subtract the current number from 'right_sum' to get the sum of elements strictly to its right.
            right_sum -= num
            
            # If 'left_sum' equals 'right_sum', we found the pivot index.
            if left_sum == right_sum:
                return i
            
            # Add the current number to 'left_sum' for the next iteration.
            # This prepares 'left_sum' to be the sum of elements strictly to the left of the next element.
            left_sum += num
            
        # If the loop completes without finding a pivot, return -1.
        return -1
