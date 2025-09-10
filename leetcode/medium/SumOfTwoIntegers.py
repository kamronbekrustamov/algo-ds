class Solution:
    def getSum(self, a: int, b: int) -> int:
        # 32-bit mask for handling Python's arbitrary precision integers
        # This simulates a 32-bit signed integer environment.
        MASK = 0xFFFFFFFF
        # Max 32-bit signed integer
        MAX_INT = 0x7FFFFFFF

        while b != 0:
            # Calculate sum without considering carry (XOR)
            # Apply mask to keep it within 32 bits
            sum_without_carry = (a ^ b) & MASK
            
            # Calculate carry (AND and left shift)
            # Apply mask to keep it within 32 bits
            carry = ((a & b) << 1) & MASK
            
            # Update a to be the sum without carry
            a = sum_without_carry
            # Update b to be the carry for the next iteration
            b = carry
        
        # If the result (a) is a negative number in 2's complement (i.e., its most
        # significant bit is 1, making it larger than MAX_INT when treated as unsigned),
        # convert it back to a Python negative integer.
        if a > MAX_INT:
            return ~(a ^ MASK)
        else:
            return a