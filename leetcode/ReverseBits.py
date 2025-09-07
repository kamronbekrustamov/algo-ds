class Solution:
    def reverseBits(self, n: int) -> int:
        """
        Reverses the bits of a given 32-bit unsigned integer.
        """
        res = 0
        # We need to process all 32 bits of the integer.
        for _ in range(32):
            # 1. Left shift the result to make space for the next bit from n.
            res <<= 1

            # 2. Get the least significant bit (LSB) of n using a bitwise AND with 1.
            last_bit = n & 1

            # 3. Add this LSB to our result using a bitwise OR.
            # This effectively appends the bit to the end of our result.
            res |= last_bit

            # 4. Right shift n to discard the LSB and expose the next bit
            # for the following iteration.
            n >>= 1

        return res
        