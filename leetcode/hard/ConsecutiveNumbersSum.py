class Solution:
    def consecutiveNumbersSum(self, n: int) -> int:
        """
        Calculates the number of ways 'n' can be expressed as a sum of 
        consecutive positive integers. 
        
        This is equivalent to finding the count of odd divisors of 'n'.
        Let N = 2^a * O, where O is the odd part. The answer is the number 
        of divisors of O.
        """
        
        if n <= 0:
            return 0
        
        # --- 1. Isolate the Odd Part (O) ---
        # Odd divisors are independent of the power of 2.
        # Dividing out all factors of 2 leaves 'n' as its largest odd factor.
        while n % 2 == 0:
            n //= 2
            
        # --- 2. Count Divisors of the Odd Part (O) ---
        # The number of divisors of O = (e1 + 1)(e2 + 1)...
        
        count = 1       # Stores the total number of divisors of the remaining odd 'n'
        prime = 3       # Start checking for odd prime factors (3, 5, 7, ...)
        
        # Iterate up to the square root of the remaining odd number 'n'
        while prime * prime <= n:
            exponent = 0
            
            # Check if 'prime' is a factor of 'n'
            if n % prime == 0:
                # Count the power (exponent) of the prime factor
                while n % prime == 0:
                    exponent += 1
                    n //= prime
                
                # Apply the divisor formula: multiply by (exponent + 1)
                count *= (exponent + 1)
                
            # Move to the next odd number to check
            prime += 2
            
        # --- 3. Handle the Remaining Factor ---
        # If n > 1, the remaining 'n' must be a prime number itself (exponent is 1)
        if n > 1:
            count *= 2  # Equivalent to (1 + 1)
            
        return count