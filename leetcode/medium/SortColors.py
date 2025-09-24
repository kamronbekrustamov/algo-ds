from typing import List

class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        This problem is a classic example of the Dutch National Flag problem.
        We use a three-pointer approach to sort the array in a single pass.
        
        - `low`:  Points to the position where the next 0 should go.
        - `mid`:  Points to the current element being considered.
        - `high`: Points to the position where the next 2 should go.
        
        The array is partitioned into four sections:
        1. nums[0...low-1]:    Contains all 0s.
        2. nums[low...mid-1]:  Contains all 1s.
        3. nums[mid...high]:   The unsorted section we are processing.
        4. nums[high+1...n-1]: Contains all 2s.
        """
        low, mid, high = 0, 0, len(nums) - 1

        # The loop continues as long as there are unsorted elements to process.
        while mid <= high:
            if nums[mid] == 0:
                # If the current element is 0, it belongs in the `low` section.
                # Swap it with the element at the `low` pointer.
                nums[low], nums[mid] = nums[mid], nums[low]
                # Both `low` and `mid` pointers advance because we've placed a 0
                # correctly and the element we swapped into `mid` (from `low`)
                # is guaranteed to be a 1 (or we just started).
                low += 1
                mid += 1
            elif nums[mid] == 2:
                # If the current element is 2, it belongs in the `high` section.
                # Swap it with the element at the `high` pointer.
                nums[high], nums[mid] = nums[mid], nums[high]
                # The `high` pointer moves left as we've placed a 2 correctly.
                high -= 1
                # We do NOT advance `mid` because the new element at `nums[mid]`
                # came from the unsorted section and needs to be processed.
            else: # nums[mid] == 1
                # If the current element is 1, it's already in the correct
                # potential spot. We just move on to the next element.
                mid += 1
