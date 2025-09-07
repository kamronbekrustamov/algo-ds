from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        """
        Searches for a target value in a sorted array of integers.
        If the target is found, its index is returned. Otherwise, -1 is returned.
        This is a classic iterative binary search implementation.
        """
        left, right = 0, len(nums) - 1

        while left <= right:
            # Calculate the middle index.
            # Using `left + (right - left) // 2` prevents potential integer overflow
            # in languages with fixed-size integers, and is a good practice.
            mid = left + (right - left) // 2

            # Check if the middle element is the target.
            if nums[mid] == target:
                return mid
            # If the target is smaller, it must be in the left half.
            # We can discard the right half, including the middle element.
            elif nums[mid] > target:
                right = mid - 1
            # If the target is larger, it must be in the right half.
            # We can discard the left half, including the middle element.
            else:
                left = mid + 1

        # If the loop finishes, the target was not found in the array.
        return -1