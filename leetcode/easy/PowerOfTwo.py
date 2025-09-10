class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        """
        Checks if an integer is a power of two.
        """
        # Powers of two must be positive integers.
        # Any number less than or equal to 0 cannot be a power of two.
        if n < 1:
            return False

        # This uses a clever bitwise trick.
        # A power of two in binary has exactly one '1' bit (e.g., 8 is 1000, 4 is 0100).
        # Subtracting 1 from a power of two flips this '1' to a '0' and all following '0's to '1's.
        #   n   = 8 (1000)
        #   n-1 = 7 (0111)
        # The bitwise AND (&) of these two numbers will always be 0.
        #   1000 & 0111 = 0000
        # If n is not a power of two, it has multiple '1' bits, and n & (n-1) will not be 0.
        return not (n & (n - 1))
        