class Solution:
    """
    This class provides a solution for the "Find in Mountain Array" problem.
    """
    def findInMountainArray(self, target: int, mountainArr: 'MountainArray') -> int:
        """
        Finds the index of the target value in a mountain array.

        The algorithm consists of three main steps:
        1. Find the peak of the mountain array. The peak is the element with the largest value.
        2. Perform a binary search for the target in the increasing part of the array (from the start to the peak).
        3. If the target is not found, perform another binary search for the target in the decreasing part of the array (from the peak to the end).

        Args:
            target: The integer value to search for.
            mountainArr: An object that represents the mountain array and provides `get` and `length` methods.

        Returns:
            The index of the target value if found, otherwise -1.
        """
        length = mountainArr.length()

        # 1. Find the peak of the mountain array.
        # The peak is the index of the largest element.
        low, high = 0, length - 1
        while low < high:
            mid = (low + high) // 2
            if mountainArr.get(mid) < mountainArr.get(mid + 1):
                # We are in the increasing part of the array, so the peak is to the right.
                low = mid + 1
            else:
                # We are in the decreasing part of the array, or at the peak.
                # The peak could be the current mid, so we include it in the search space.
                high = mid
        peak = low

        # 2. Search for the target in the increasing part of the array (from the start to the peak).
        low, high = 0, peak
        while low <= high:
            mid = (low + high) // 2
            m = mountainArr.get(mid)
            if m == target:
                return mid
            elif m < target:
                low = mid + 1
            else:
                high = mid - 1

        # 3. If not found, search for the target in the decreasing part of the array (from the peak to the end).
        low, high = peak, length - 1
        while low <= high:
            mid = (low + high) // 2
            m = mountainArr.get(mid)
            if m == target:
                return mid
            elif m > target:
                # In a decreasing array, if the mid element is greater than the target,
                # the target must be in the right part of the array.
                low = mid + 1
            else:
                high = mid - 1

        # Target was not found in either part of the array.
        return -1
