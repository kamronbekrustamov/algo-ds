from typing import List
    

class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        # The problem is to find a contiguous subarray of size k. This is
        # equivalent to finding the optimal starting index for this subarray.
        # The possible starting indices for a window of size k are from 0 to len(arr) - k.
        # We can use binary search to find this optimal starting index.

        # `low` and `high` define the search space for the starting index of our result.
        low = 0
        high = len(arr) - k

        while low < high:
            mid = (low + high) // 2
            # We compare the window starting at `mid` with the one starting at `mid + 1`.
            # The elements that differ are arr[mid] and arr[mid+k].
            # If we move right, we drop arr[mid] and pick up arr[mid+k].
            # We should move right if arr[mid+k] is a better choice than arr[mid].
            # This happens if x is closer to arr[mid+k] than to arr[mid].
            # The condition for this is `x - arr[mid] > arr[mid+k] - x`.
            if x - arr[mid] > arr[mid + k] - x:
                # `x` is closer to `arr[mid+k]`, so the optimal window is to the right of `mid`.
                # We update our search space to `[mid + 1, high]`.
                low = mid + 1
            else:
                # `x` is closer to `arr[mid]` (or equally close), so the optimal window
                # starts at `mid` or to its left. We update our search space to `[low, mid]`.
                high = mid

        # The loop terminates when `low == high`, which is the optimal starting index.
        return arr[low:low + k]