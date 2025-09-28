from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        """
        Searches for a target value in a rotated sorted array that may contain duplicates.

        The algorithm uses a modified binary search to find the target.
        The presence of duplicates adds a complication: if nums[left] == nums[middle],
        we can't determine which half is sorted. In this case, we simply increment `left`
        and continue. This can degrade the performance to O(n) in the worst case
        (e.g., an array full of the same element), but for most cases, it remains O(log n).

        Args:
            nums: A list of integers that is sorted, rotated, and may contain duplicates.
            target: The integer to search for.

        Returns:
            True if the target is found, otherwise False.
        """
        left, right = 0, len(nums) - 1

        while left <= right:
            middle = (left + right) // 2

            if nums[middle] == target:
                return True

            # If the left and middle elements are the same, we can't determine
            # which half is sorted. So, we just move the left pointer forward.
            if nums[left] == nums[middle]:
                left += 1
                continue

            # Check if the left half is sorted
            if nums[left] < nums[middle]:
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
        
        return False