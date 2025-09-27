class Solution:
    def minEnd(self, n: int, x: int) -> int:
        # The problem asks to find the n-th smallest number 'y' such that (x & y) == x.
        # This condition implies that 'y' must have all the bits set that are set in 'x'.
        # The bits that are not set in 'x' can be either 0 or 1 in 'y'.
        # We are essentially looking for the (n-1)-th combination of these "free" bits.

        # Initialize 'ans' with 'x'. This ensures all bits set in 'x' are also set in 'ans'.
        ans = x
        # Decrement 'n' by 1 because 'x' itself is the 1st number that satisfies the condition.
        n -= 1

        # Iterate through bit positions from 0 (least significant) upwards.
        # We will use the bits of 'n' to fill the "free" bit positions in 'ans'.
        bit_pos = 0
        while n > 0:
            # Check if the current bit position in 'x' is 0.
            # If it's 0, this bit position is "free" and can be set based on 'n'.
            if (x >> bit_pos) & 1 == 0:
                # If the least significant bit of 'n' is 1, set the current bit position in 'ans' to 1.
                if (n & 1) == 1:
                    ans |= (1 << bit_pos)
                # Move to the next bit of 'n'.
                n >>= 1
            # Move to the next bit position for 'x' and 'ans'.
            bit_pos += 1
            
        return ans
