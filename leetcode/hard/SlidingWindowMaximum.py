import collections
from typing import List

class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        """Finds the maximum value in each sliding window of size k.

        This function uses the optimal O(n) approach with a double-ended queue (deque).
        The deque stores indices of the numbers in the current window. It is maintained
        in such a way that it is always monotonically decreasing based on the values
        at those indices. This ensures that the index of the maximum element in the
        current window is always at the front of the deque.

        Args:
            nums: A list of integers.
            k: The size of the sliding window.

        Returns:
            A list of integers, where each element is the maximum of a sliding window.
        """
        if not nums or k == 0:
            return []

        result = []
        # The deque stores indices of elements in `nums`.
        deque = collections.deque()

        # Iterate through the array, with `i` representing the right edge of the window.
        for i in range(len(nums)):
            # 1. Remove indices from the front (left) of the deque that are no longer
            #    part of the current window (i.e., they are to the left of `i - k + 1`).
            if deque and deque[0] <= i - k:
                deque.popleft()

            # 2. Remove indices from the back (right) of the deque whose corresponding
            #    values are less than the current element `nums[i]`. This is because
            #    if `nums[j] < nums[i]` and `j < i`, `nums[j]` can never be the maximum
            #    in any future window that includes `nums[i]`.
            while deque and nums[deque[-1]] < nums[i]:
                deque.pop()

            # 3. Add the current element's index to the back of the deque.
            deque.append(i)

            # 4. The front of the deque always holds the index of the maximum element
            #    for the current window. Once the window is full (i.e., `i >= k - 1`),
            #    we can start adding the maximums to our result list.
            if i >= k - 1:
                result.append(nums[deque[0]])

        return result
