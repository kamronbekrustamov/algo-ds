from typing import List


class Solution:
    def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
        """
        Finds the k closest elements to a given value x in a sorted array.

        The problem asks for k elements, and since the array is sorted, the
        result will be a contiguous subarray of length k. This means the problem
        is equivalent to finding the optimal starting index for this subarray.

        This solution uses binary search to efficiently find this optimal start index.

        Algorithm Breakdown:
        1. Define a "search space" for the starting index of our k-sized window.
           The window can start anywhere from index 0 to `len(arr) - k`.
        2. Binary search within this range of indices.
        3. In each step of the binary search, we consider a potential window
           starting at `mid`. We compare it with the window immediately to its
           right (starting at `mid + 1`).
        4. The comparison is based on which window is "closer" to x. When we
           shift the window from `mid` to `mid + 1`, we are essentially dropping
           the element `arr[mid]` and adding the element `arr[mid + k]`.
        5. We decide whether to move our window right based on which of these two
           elements (`arr[mid]` or `arr[mid + k]`) is closer to x.
           - If `arr[mid + k]` is closer to x, it's better to shift the window right.
           - If `arr[mid]` is closer to x (or they are equally close), it's better
             to keep the current window or move left.
        6. The binary search narrows down the search space until we find the single
           best starting index.

        Time Complexity: O(log(n-k) + k). The binary search takes O(log(n-k)),
                         and slicing the final array takes O(k).
        Space Complexity: O(k) for the returned list.

        Args:
            arr: A sorted list of integers.
            k: The number of closest elements to find.
            x: The target value.

        Returns:
            A list containing the k closest elements to x.
        """
        # The search space `[low, high]` represents the possible starting indices
        # for our window of size k.
        low = 0
        high = len(arr) - k

        # Binary search to find the best starting index `low`.
        while low < high:
            mid = (low + high) // 2
            
            # Compare the element we would drop `arr[mid]` vs. the one we would
            # pick up `arr[mid + k]` if we were to shift the window one step to the right.
            #
            # We want to check if the element `arr[mid+k]` is a better choice
            # (i.e., closer to x) than `arr[mid]`.
            #
            # The condition `x - arr[mid] > arr[mid + k] - x` checks if the distance
            # from x to arr[mid] is greater than the distance from x to arr[mid+k].
            # Note: `abs()` is not needed because `arr` is sorted. If `x` is to the
            # left of `arr[mid]`, `x - arr[mid]` is negative, and the condition will
            # be false, correctly indicating we should not move right.
            if x - arr[mid] > arr[mid + k] - x:
                # If the distance to `arr[mid]` is larger, it means `arr[mid+k]` is
                # a better element to have in our window. Therefore, the optimal
                # window must start at or after `mid + 1`.
                # We shrink the search space to the right half: [mid + 1, high].
                low = mid + 1
            else:
                # If `arr[mid]` is closer to x (or they are equally close), then
                # the window starting at `mid` is better than or equal to the one
                # starting at `mid + 1`. So, the optimal window must start at `mid`
                # or to its left.
                # We shrink the search space to the left half: [low, mid].
                high = mid

        # The loop terminates when `low == high`, which pinpoints the optimal
        # starting index for the k-sized window.
        return arr[low : low + k]
