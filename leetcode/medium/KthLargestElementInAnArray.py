import random
from typing import List

class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        """
        Finds the k-th largest element in an unsorted array.

        This implementation uses the Quickselect algorithm, which is a selection
        algorithm to find the k-th smallest/largest element in an unordered list.
        It is a modification of the Quicksort algorithm.

        The average time complexity is O(n), with a worst-case complexity of O(n^2).
        The worst-case can be avoided by using a good pivot selection strategy,
        such as picking a random pivot.

        Args:
            nums: A list of integers.
            k: The k-th largest element to find.

        Returns:
            The k-th largest element in the array.
        """
        # We are looking for the k-th largest element, which is the same as the
        # (len(nums) - k)-th smallest element.
        k_smallest = len(nums) - k
        return self.quickselect(nums, 0, len(nums) - 1, k_smallest)

    def quickselect(self, nums: List[int], left: int, right: int, k: int) -> int:
        """
        A recursive helper function to perform the Quickselect algorithm.
        """
        if left == right:
            return nums[left]

        # Select a random pivot to avoid worst-case O(n^2) performance
        pivot_index = random.randint(left, right)
        pivot_index = self.partition(nums, left, right, pivot_index)

        if k == pivot_index:
            return nums[k]
        elif k < pivot_index:
            return self.quickselect(nums, left, pivot_index - 1, k)
        else:
            return self.quickselect(nums, pivot_index + 1, right, k)

    def partition(self, nums: List[int], left: int, right: int, pivot_index: int) -> int:
        """
        Partitions the array around a pivot element.
        """
        pivot = nums[pivot_index]
        # Move pivot to the end
        nums[pivot_index], nums[right] = nums[right], nums[pivot_index]
        
        store_index = left
        for i in range(left, right):
            if nums[i] < pivot:
                nums[store_index], nums[i] = nums[i], nums[store_index]
                store_index += 1
        
        # Move pivot to its final sorted position
        nums[right], nums[store_index] = nums[store_index], nums[right]
        return store_index