class Solution:
    def rangeBitwiseAnd(self, left: int, right: int) -> int:
        # The problem asks for the bitwise AND of all numbers in the range [left, right].
        #
        # Key Observation:
        # When performing a bitwise AND operation on a range of numbers,
        # the result will have common leading bits. Any bit position where 'left'
        # and 'right' differ will result in a '0' for that bit position and all
        # subsequent less significant bit positions in the final AND result.
        # This is because if 'left' and 'right' differ at a certain bit position,
        # there must be at least one number in the range [left, right] that has a '0'
        # at that bit position and at least one number that has a '1' at that bit position.
        # Therefore, the AND of all numbers in the range will have a '0' at that bit position.
        #
        # The most significant common prefix of 'left' and 'right' will be the answer.
        # We can find this by repeatedly right-shifting both 'left' and 'right'
        # until they become equal. The number of shifts tells us how many trailing
        # zeros we need to append to the common prefix.

        shift = 0
        # Keep right-shifting both numbers until they become equal.
        # This effectively removes the differing trailing bits.
        while left != right:
            left >>= 1
            right >>= 1
            shift += 1
        
        # Once left and right are equal, they represent the common prefix.
        # Left-shift this common prefix by 'shift' positions to restore the
        # original magnitude and append the necessary trailing zeros.
        return left << shift