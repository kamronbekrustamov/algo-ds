from typing import List

class Solution:
    def findMin(self, nums: List[int]) -> int:
        """
        Finds the minimum element in a rotated sorted array.
        The array is sorted in ascending order, but then rotated at some pivot unknown to you beforehand.
        (e.g., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
        This problem is solved using a modified binary search approach.

        Args:
            nums: A list of integers representing the rotated sorted array.

        Returns:
            The minimum element in the array.
        """
        
        # Initialize left and right pointers for binary search.
        left, right = 0, len(nums) - 1
        
        # The loop continues as long as left < right.
        # When left == right, we have found the minimum element.
        while left < right:
            # Calculate the middle index.
            middle = (left + right) // 2
            
            # If the middle element is greater than the rightmost element,
            # it means the minimum element is in the right half (including middle + 1).
            # This implies that the rotation point is to the right of 'middle'.
            if nums[middle] > nums[right]:
                left = middle + 1
            # Otherwise (nums[middle] <= nums[right]), the minimum element is in the left half
            # (including middle). This implies the rotation point is at or to the left of 'middle'.
            else:
                right = middle
                
        # When the loop terminates, 'left' (and 'right') will point to the minimum element.
        return nums[left]