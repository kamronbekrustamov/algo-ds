class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        """
        Checks if a positive integer has alternating bits in its binary representation.

        This solution uses a clever bit manipulation trick for a concise and efficient check.
        
        The logic is as follows:
        1. Let's take an example n = 5 (binary 101).
        2. Create a new number by XORing n with n right-shifted by 1.
           n      = 101
           n >> 1 = 010
           ----------------
           x = n ^ (n >> 1) = 111
        3. If n has alternating bits, x will be a number with all its bits set to 1
           (e.g., 1, 3, 7, 15, ...).
        4. A number with all bits set to 1 has a special property: adding 1 to it
           results in a power of two (e.g., 7+1=8, 15+1=16).
        5. A number is a power of two if and only if (num & (num - 1)) == 0.
        6. Therefore, we check if (x & (x + 1)) == 0.
        """
        x = n ^ (n >> 1)
        return (x & (x + 1)) == 0