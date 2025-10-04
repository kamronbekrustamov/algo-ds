class Solution:
    def fib(self, n: int) -> int:
        """
        Calculates the n-th Fibonacci number.

        The Fibonacci sequence is defined as:
        F(0) = 0
        F(1) = 1
        F(n) = F(n-1) + F(n-2) for n > 1.

        This solution uses a space-optimized dynamic programming approach to
        calculate the Fibonacci number iteratively. Instead of storing the entire
        sequence in an array, we only need to keep track of the last two numbers
        to compute the next one.

        Time Complexity: O(n), as we iterate from 2 up to n.
        Space Complexity: O(1), as we only use a constant number of variables.

        Args:
            n: A non-negative integer.

        Returns:
            The n-th Fibonacci number.
        """
        # Base cases for F(0) and F(1).
        if n < 2:
            return n

        # Initialize two variables to store the state for F(i-2) and F(i-1).
        two_steps_back = 0  # Represents F(0)
        one_step_back = 1   # Represents F(1)

        # Iterate from 2 up to n to calculate the Fibonacci numbers up to F(n).
        for _ in range(2, n + 1):
            # Calculate the current Fibonacci number.
            current_fib = two_steps_back + one_step_back
            
            # Update the two state variables for the next iteration.
            two_steps_back = one_step_back
            one_step_back = current_fib

        # After the loop, one_step_back holds the value for F(n).
        return one_step_back