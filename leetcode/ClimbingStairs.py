class Solution:
    def climbStairs(self, n: int) -> int:
        """
        Calculates the number of distinct ways to climb to the top of n stairs.
        You can either climb 1 or 2 steps at a time.

        This problem is a classic example of dynamic programming and is equivalent
        to calculating the (n+1)-th Fibonacci number.

        Let ways(i) be the number of ways to reach step i.
        ways(i) = ways(i-1) + ways(i-2)

        Base cases:
        ways(1) = 1 (one step)
        ways(2) = 2 (one step + one step, or two steps)

        This solution uses a bottom-up approach with O(1) space.
        """
        # We only need to store the ways to reach the previous two steps.
        # two_steps_back: ways to reach step i-2. Initialize for step 0 (1 way: do nothing).
        # one_step_back: ways to reach step i-1. Initialize for step 1 (1 way).
        two_steps_back, one_step_back = 1, 1

        # Iterate from step 2 up to n. This loop runs n-1 times.
        for _ in range(n - 1):
            two_steps_back, one_step_back = one_step_back, two_steps_back + one_step_back

        # After the loop, one_step_back holds the number of ways for step `n`.
        return one_step_back