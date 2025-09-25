class Solution:
    def tribonacci(self, n: int) -> int:
        """
        Calculates the nth Tribonacci number.
        The Tribonacci sequence is defined as:
        T(0) = 0
        T(1) = 1
        T(2) = 1
        T(n+3) = T(n) + T(n+1) + T(n+2) for n >= 0.
        """
        
        # Base cases for the first three numbers in the sequence.
        if n == 0:
            return 0
        if n <= 2:
            return 1
            
        # Initialize the first three values of the sequence.
        # t0, t1, t2 will always hold T(i-3), T(i-2), and T(i-1) respectively.
        t0, t1, t2 = 0, 1, 1
        
        # Iterate from 3 up to n to calculate the nth Tribonacci number.
        for _ in range(3, n + 1):
            # Calculate the next Tribonacci number and slide the window forward.
            t0, t1, t2 = t1, t2, t0 + t1 + t2
            
        # After the loop, t2 holds the value for T(n).
        return t2
