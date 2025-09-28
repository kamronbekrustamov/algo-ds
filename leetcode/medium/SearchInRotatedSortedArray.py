from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        """
        Searches for a target value in a rotated sorted array.
        The array is sorted in ascending order, but then rotated at some pivot unknown to you beforehand.
        (e.g., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
        This problem is solved using a two-phase binary search approach.

        Args:
            nums: A list of integers representing the rotated sorted array.
            target: The integer value to search for.

        Returns:
            The index of the target if found, otherwise -1.
        """
        
        # Phase 1: Find the pivot point (index of the minimum element).
        # This is essentially the same logic as finding the minimum in a rotated sorted array.
        left, right = 0, len(nums) - 1
        while left < right:
            middle = (left + right) // 2
            # If nums[middle] > nums[right], it means the pivot is in the right half (middle + 1 to right).
            if nums[middle] > nums[right]:
                left = middle + 1
            # Otherwise, the pivot is in the left half (left to middle).
            else:
                right = middle
        
        # At this point, 'left' (or 'right') is the index of the minimum element (the pivot).
        pivot = left
        
        # Phase 2: Determine which sorted subarray to search in and perform standard binary search.
        # If the target is greater than or equal to the last element, it must be in the right sorted part.
        if nums[-1] >= target:
            left, right = pivot, len(nums) - 1
        # Otherwise, the target must be in the left sorted part.
        else:
            left, right = 0, pivot - 1
        
        # Perform a standard binary search on the identified subarray.
        while left <= right:
            middle = (left + right) // 2
            num = nums[middle]
            
            if num == target:
                return middle
            elif num > target:
                right = middle - 1
            else:
                left = middle + 1
                
        # If the target is not found after binary search, return -1.
        return -1