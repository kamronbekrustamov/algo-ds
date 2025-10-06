from typing import List

class Solution:
    """
    This class provides a solution for the "Split Array Largest Sum" problem.
    """
    def splitArray(self, nums: List[int], k: int) -> int:
        """
        Given an array of non-negative integers and an integer k, this function
        splits the array into k non-empty continuous subarrays to minimize the
        largest sum among these subarrays.

        The problem is solved using binary search on the answer. The possible values for
        the largest sum range from the maximum element in the array to the total sum
        of all elements in the array.

        Args:
            nums: A list of non-negative integers.
            k: The number of subarrays to split the array into.

        Returns:
            The minimized largest sum among the subarrays.
        """
        # The lower bound for the binary search is the maximum single element in the array,
        # as any subarray sum must be at least this large.
        # The upper bound is the total sum of the array, which is the case for k=1.
        low = max(nums)
        high = sum(nums)
        min_largest_sum = high

        # Binary search for the minimum possible value of the largest subarray sum.
        while low <= high:
            mid = (low + high) // 2
            
            # Check if the array can be split with `mid` as the largest sum.
            subarrays_count = 1
            current_sum = 0
            for num in nums:
                current_sum += num
                if current_sum > mid:
                    # If the current sum exceeds mid, start a new subarray.
                    subarrays_count += 1
                    current_sum = num
            
            if subarrays_count <= k:
                # If we can split the array into k or fewer subarrays,
                # it means `mid` is a potential answer. We try for an even smaller sum.
                min_largest_sum = mid
                high = mid - 1
            else:
                # If we need more than k subarrays, `mid` is too small.
                # We need to allow for a larger subarray sum.
                low = mid + 1

        return min_largest_sum