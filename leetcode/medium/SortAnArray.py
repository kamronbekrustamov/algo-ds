import random
from typing import List

class Solution:
    def sortArray(self, nums: List[int]) -> List[int]:
        """
        Sorts an array of integers in ascending order using the Mergesort algorithm.

        This implementation uses a recursive, top-down Mergesort. It divides the
        array into two halves, sorts them recursively, and then merges them back
        together. This guarantees a time complexity of O(n log n).

        Args:
            nums: The list of integers to sort.

        Returns:
            The sorted list of integers.

        Time Complexity: O(n log n) for all cases (best, average, worst).
        Space Complexity: O(n) due to the temporary array used in the merge step.
        """
        self.mergesort(nums, 0, len(nums) - 1)
        return nums
    
    def mergesort(self, nums: List[int], low: int, high: int):
        """
        Recursively sorts a sub-array `nums[low..high]` using the Mergesort algorithm.
        """
        # Base case: If the sub-array has 0 or 1 elements, it's already sorted.
        if low < high:
            # Find the middle point to divide the array into two halves.
            mid = (low + high) // 2
            
            # Recursively sort the first half.
            self.mergesort(nums, low, mid)
            # Recursively sort the second half.
            self.mergesort(nums, mid + 1, high)
            
            # Merge the two sorted halves.
            self.merge(nums, low, mid, high)

    def merge(self, nums: List[int], low: int, mid: int, high: int):
        """
        Merges two sorted sub-arrays: nums[low..mid] and nums[mid+1..high].
        """
        # Create a temporary array to store the merged result.
        temp = [0] * (high - low + 1)
        
        # Initialize pointers for the two sub-arrays and the temporary array.
        i, j, k = low, mid + 1, 0

        # Compare elements from both sub-arrays and copy the smaller one to temp.
        while i <= mid and j <= high:
            if nums[i] <= nums[j]:
                temp[k] = nums[i]
                i += 1
            else:
                temp[k] = nums[j]
                j += 1
            k += 1
        
        # If there are any remaining elements in the first sub-array, copy them.
        while i <= mid:
            temp[k] = nums[i]
            i += 1
            k += 1
            
        # If there are any remaining elements in the second sub-array, copy them.
        while j <= high:
            temp[k] = nums[j]
            j += 1
            k += 1
            
        # Copy the sorted elements from the temporary array back into the original array.
        for i in range(low, high + 1):
            nums[i] = temp[i - low]
