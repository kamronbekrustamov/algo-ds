from typing import List

# Time: O(n)
# Space: O(1)
class Solution:
    def _reverse(self, nums: List[int], start: int, end: int) -> None:
        """Helper function to reverse a portion of the array in-place."""
        while start < end:
            nums[start], nums[end] = nums[end], nums[start]
            start += 1
            end -= 1

    def rotate(self, nums: List[int], k: int) -> None:
        """
        Rotates an array to the right by k steps in-place.

        This optimal solution uses a three-step reversal algorithm:
        1. Reverse the entire array.
        2. Reverse the first k elements.
        3. Reverse the remaining n-k elements.

        Example: nums = [1,2,3,4,5,6,7], k = 3
        1. Reverse all: [7,6,5,4,3,2,1]
        2. Reverse first k=3: [5,6,7,4,3,2,1]
        3. Reverse last n-k=4: [5,6,7,1,2,3,4] -> Correctly rotated!

        :param nums: The array of numbers to rotate.
        :param k: The number of steps to rotate right.
        """
        n = len(nums)
        
        # Handle cases where k is larger than the array length.
        # The effective rotation is k % n.
        k %= n

        if k == 0:
            # If k is 0, no rotation is needed.
            return

        # Step 1: Reverse the entire array.
        self._reverse(nums, 0, n - 1)
        
        # Step 2: Reverse the first k elements.
        self._reverse(nums, 0, k - 1)
        
        # Step 3: Reverse the remaining n-k elements.
        self._reverse(nums, k, n - 1)