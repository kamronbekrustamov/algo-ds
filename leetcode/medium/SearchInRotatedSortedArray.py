from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        """
        Searches for a target value in a rotated sorted array.

        The algorithm uses a modified binary search to find the target in O(log n) time.
        At each iteration, it identifies which half of the array is sorted and
        narrows down the search space based on whether the target is in that sorted half.

        Args:
            nums: A list of integers that is sorted and rotated.
            target: The integer to search for.

        Returns:
            The index of the target if found, otherwise -1.
        """
        left, right = 0, len(nums) - 1

        while left <= right:
            middle = (left + right) // 2

            if nums[middle] == target:
                return middle

            # Check if the left half is sorted
            if nums[left] <= nums[middle]:
                # If the target is in the sorted left half
                if nums[left] <= target < nums[middle]:
                    right = middle - 1
                else:
                    left = middle + 1
            # Otherwise, the right half must be sorted
            else:
                # If the target is in the sorted right half
                if nums[middle] < target <= nums[right]:
                    left = middle + 1
                else:
                    right = middle - 1
        
        return -1