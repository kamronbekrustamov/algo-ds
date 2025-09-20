from typing import List

class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        """
        Given a sorted array of distinct integers and a target value, return the index if the target is found.
        If not, return the index where it would be if it were inserted in order.

        This function implements a binary search algorithm to achieve O(log n) runtime complexity.

        Args:
            nums: A sorted list of distinct integers.
            target: The integer to search for.

        Returns:
            The index of the target if found, otherwise the index where it should be inserted.
        """
        low, high = 0, len(nums) - 1

        # Perform binary search to find the target or the insertion point.
        while low <= high:
            # Calculate the middle index to divide the array into two halves.
            middle = low + (high - low) // 2

            if nums[middle] == target:
                # If the middle element is the target, we have found it.
                return middle
            elif nums[middle] < target:
                # If the middle element is less than the target,
                # the target must be in the right half of the array.
                low = middle + 1
            else:
                # If the middle element is greater than the target,
                # the target must be in the left half of the array.
                high = middle - 1

        # If the loop finishes, the target was not found.
        # The `low` pointer is now at the position where the target should be inserted.
        return low