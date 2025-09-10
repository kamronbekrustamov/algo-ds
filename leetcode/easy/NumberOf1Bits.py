class Solution:
    def hammingWeight(self, n: int) -> int:
        """
        Calculates the Hamming weight (number of set bits or '1's) of an integer.

        This solution uses Brian Kernighan's algorithm, which is an efficient
        way to count set bits. The loop runs exactly as many times as there are
        set bits, making it faster than checking every bit for sparse numbers.
        """
        count = 0
        # The loop continues as long as n is not 0 (i.e., there are still set bits).
        while n:
            # The operation `n & (n - 1)` cleverly clears the least significant
            # (rightmost) '1' bit of n.
            # Example:
            #   n   = 12 (1100)
            #   n-1 = 11 (1011)
            #   -----------------
            #   n & (n-1) = 8 (1000) -> The rightmost '1' has been cleared.
            n &= (n - 1)

            # Since we clear one set bit in each iteration, we increment the count.
            count += 1
        return count
