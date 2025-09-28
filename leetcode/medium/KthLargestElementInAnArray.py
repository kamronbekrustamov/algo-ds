import random
from typing import List

class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        """
        Finds the k-th largest element in an unsorted array using 3-way partition Quickselect.

        This implementation uses the Quickselect algorithm with a 3-way partition
        scheme (also known as Dutch National Flag problem). This is particularly
        efficient when the array contains many duplicate elements.

        The 3-way partition divides the array into three sections:
        - Elements less than the pivot
        - Elements equal to the pivot
        - Elements greater than the pivot

        The average time complexity is O(n), with a worst-case complexity of O(n^2).
        A random pivot selection strategy is used to avoid the worst-case scenario.

        Args:
            nums: A list of integers.
            k: The k-th largest element to find.

        Returns:
            The k-th largest element in the array.
        """
        # We are looking for the k-th largest element, which is the same as the
        # (len(nums) - k)-th smallest element.
        k_smallest = len(nums) - k
        
        left, right = 0, len(nums) - 1
        while left <= right:
            # Select a random pivot to avoid worst-case O(n^2) performance
            pivot_index = random.randint(left, right)
            pivot = nums[pivot_index]

            # 3-way partition
            lt, i, gt = left, left, right
            while i <= gt:
                if nums[i] < pivot:
                    nums[lt], nums[i] = nums[i], nums[lt]
                    lt += 1
                    i += 1
                elif nums[i] > pivot:
                    nums[i], nums[gt] = nums[gt], nums[i]
                    gt -= 1
                else: # nums[i] == pivot
                    i += 1
            
            # After partitioning, the elements equal to the pivot are in the range [lt, gt].
            if k_smallest < lt:
                right = lt - 1
            elif k_smallest > gt:
                left = gt + 1
            else: # k_smallest is within the range of pivot elements
                return pivot