from typing import List

class Solution:
    def countBits(self, n: int) -> List[int]:
        """
        Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
        ans[i] is the number of 1's in the binary representation of i.

        This solution uses dynamic programming based on the highest power of two.
        """
        # ans[i] will store the number of set bits for integer i. This is a DP array.
        ans = [0] * (n + 1)

        # Iterate from 1 to n to fill the DP array.
        for i in range(1, n + 1):
            # The number of set bits in `i` can be derived from a smaller subproblem.
            # `i >> 1` is equivalent to `i // 2`. The number of bits in `i` is the
            # number of bits in `i // 2` plus the last bit of `i`.
            # `i & 1` gives the last bit of `i` (0 or 1).
            # Example: count(7) [111] = count(3) [011] + 1 = 2 + 1 = 3
            ans[i] = ans[i >> 1] + (i & 1)

        return ans
