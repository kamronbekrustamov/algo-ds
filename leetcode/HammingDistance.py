class Solution:
    def hammingDistance(self, x: int, y: int) -> int:
        # The Hamming distance between two integers is the number of positions
        # at which the corresponding bits are different.
        # This is equivalent to counting the number of set bits (1s)
        # in the XOR result of the two numbers.
        xor = x ^ y

        # In Python 3.10+, int.bit_count() is the most efficient way
        # to count the number of set bits in an integer.
        return xor.bit_count()