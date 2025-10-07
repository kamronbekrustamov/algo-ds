from typing import List


class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        """Calculates the median of two sorted arrays.

        This function implements the efficient O(log(min(m, n))) algorithm, which uses
        binary search to find the correct partition in the smaller of the two arrays.
        The goal is to partition the combined conceptual array into two equal halves
        such that all elements in the left half are less than or equal to all elements
        in the right half.

        Args:
            nums1: The first sorted array.
            nums2: The second sorted array.

        Returns:
            The median of the two combined arrays.
        """
        # Ensure nums1 is the smaller array to optimize the binary search range.
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1

        m, n = len(nums1), len(nums2)
        total_len = m + n
        # 'half_len' is the size of the combined left partition.
        half_len = total_len // 2

        # Binary search for the correct partition in the smaller array (nums1).
        low = 0
        high = m  # The partition can be before the first element or after the last.

        while low <= high:
            # 'partition1' is the index where nums1 is split. It represents the number
            # of elements from nums1 in the combined left partition.
            partition1 = (low + high) // 2
            # 'partition2' is the corresponding split index for nums2.
            partition2 = half_len - partition1

            # Get the four key elements that border the partitions.
            # max_left1 is the largest element in the left part of nums1.
            # min_right1 is the smallest element in the right part of nums1.
            max_left1 = nums1[partition1 - 1] if partition1 > 0 else float('-inf')
            min_right1 = nums1[partition1] if partition1 < m else float('inf')

            max_left2 = nums2[partition2 - 1] if partition2 > 0 else float('-inf')
            min_right2 = nums2[partition2] if partition2 < n else float('inf')

            # Check if we have found the correct partition.
            # The condition is that the max element on the left of the partition must be
            # less than or equal to the min element on the right for both arrays.
            if max_left1 <= min_right2 and max_left2 <= min_right1:
                # If the total number of elements is odd, the median is the smallest
                # element in the right partition.
                if total_len % 2 == 1:
                    return min(min_right1, min_right2)
                # If the total is even, the median is the average of the largest element
                # in the left partition and the smallest element in the right partition.
                else:
                    return (max(max_left1, max_left2) + min(min_right1, min_right2)) / 2
            
            # If the partition is incorrect, adjust the binary search range.
            elif max_left1 > min_right2:
                # The partition in nums1 is too large. Move to the left half.
                high = partition1 - 1
            else: # max_left2 > min_right1
                # The partition in nums1 is too small. Move to the right half.
                low = partition1 + 1
