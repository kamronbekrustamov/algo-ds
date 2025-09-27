class Solution:
    def myPow(self, x: float, n: int) -> float:
        """
        Calculates x raised to the power n (x^n).
        This implementation uses the "exponentiation by squaring" (binary exponentiation) algorithm,
        which is efficient with a time complexity of O(log n).

        Args:
            x: The base, a floating-point number.
            n: The exponent, an integer.

        Returns:
            The result of x^n as a floating-point number.
        """
        
        # Initialize the result to 1.
        res = 1.0
        
        # Handle negative exponents: x^(-n) = 1 / (x^n).
        # We convert n to its positive equivalent and remember to invert the result later.
        if n < 0:
            x = 1 / x  # If n is negative, we can invert x and make n positive.
            n = -n     # Convert n to its positive counterpart.
        
        # Perform exponentiation by squaring.
        # This algorithm processes the exponent 'n' bit by bit.
        # If the current bit of 'n' is 1, we multiply 'res' by 'x'.
        # In each step, 'x' is squared, effectively handling higher powers.
        while n > 0:
            # If the least significant bit of 'n' is 1 (n is odd),
            # it means this power of x contributes to the final result.
            if (n & 1) == 1:
                res *= x
            
            # Square x for the next iteration. This prepares x for the next bit position (x^2, x^4, x^8, ...).
            x *= x
            
            # Right-shift n by 1 (equivalent to n = n // 2).
            # This moves to the next bit of the exponent.
            n >>= 1
            
        return res