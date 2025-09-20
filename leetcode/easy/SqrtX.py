class Solution:
    def mySqrt(self, x: int) -> int:
        """
        Computes the integer square root of a non-negative integer x.

        The function returns the largest integer `i` such that `i*i <= x`.

        This implementation uses binary search to find the square root,
        which is much more efficient than a linear search. The time
        complexity is O(log x).

        Args:
            x: A non-negative integer.

        Returns:
            The integer square root of x.
        """
        # Handle the base case for 0.
        if x == 0:
            return 0

        # The search space for the square root is from 1 to x.
        low, high = 1, x
        ans = 0

        while low <= high:
            # Calculate the middle of the search range.
            mid = low + (high - low) // 2
            
            # To avoid potential overflow with mid*mid, we can check if mid > x / mid.
            if mid > x / mid:
                # mid is too large, so the square root must be
                # in the left half of the range.
                high = mid - 1
            else:
                # mid could be the integer square root.
                # We store it and continue searching for a larger one
                # in the right half of the range.
                ans = mid
                low = mid + 1
        
        return ans
