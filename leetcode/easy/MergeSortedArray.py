from typing import List

# Time: O(m + n)
# Space: O(1)
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Merges two sorted arrays, nums1 and nums2, into a single sorted array in-place.
        The final sorted array is stored in nums1.

        The function uses a three-pointer approach, starting from the end of the arrays
        to avoid overwriting elements in nums1 that have not yet been compared.

        :param nums1: The first sorted array, with enough space to hold elements from nums2.
        :param m: The number of initialized elements in nums1.
        :param nums2: The second sorted array.
        :param n: The number of elements in nums2.
        """
        # Pointer for the last element of the initialized part of nums1.
        p1 = m - 1
        # Pointer for the last element of nums2.
        p2 = n - 1
        # Pointer for the last position in the merged nums1 array.
        p_merged = m + n - 1

        # Iterate backwards from the end of both arrays.
        while p1 >= 0 and p2 >= 0:
            # Compare the elements from both arrays and place the larger one
            # at the end of the nums1 array.
            if nums1[p1] > nums2[p2]:
                nums1[p_merged] = nums1[p1]
                p1 -= 1
            else:
                nums1[p_merged] = nums2[p2]
                p2 -= 1
            p_merged -= 1

        # If there are any remaining elements in nums2 (meaning they are all smaller
        # than the initial elements of nums1), copy them to the front of nums1.
        # This handles cases where n > m or where all elements of nums2 are smaller.
        # No need to handle remaining elements in nums1 (p1 >= 0) because they are
        # already in their correct sorted position.
        while p2 >= 0:
            nums1[p_merged] = nums2[p2]
            p2 -= 1
            p_merged -= 1