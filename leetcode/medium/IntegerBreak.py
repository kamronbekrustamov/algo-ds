class Solution:
    def integerBreak(self, n: int) -> int:
        """
        Calculates the maximum product of breaking an integer into the sum of at
        least two positive integers.

        This solution uses dynamic programming.

        Let dp[i] be the maximum product that can be obtained by breaking the
        integer i. The goal is to find dp[n].

        The recurrence relation is derived by considering all possible ways to
        make the first break. If we break `i` into `j` and `i-j`, the product
        can be either `j * (i-j)` (if we don't break `i-j` further) or
        `j * dp[i-j]` (if we break `i-j` further for a potentially larger product).
        We take the maximum over all possible first breaks `j`.

        Time Complexity: O(n^2)
        Space Complexity: O(n)

        Args:
            n: The integer to break.

        Returns:
            The maximum product.
        """
        # Base cases for small n, as we need at least two integers.
        if n <= 3:
            return n - 1

        # dp[i] will store the maximum product for integer i.
        dp = [0] * (n + 1)

        # Initialize base cases for the DP table.
        # For subproblems, the number `i` itself can be a factor.
        # So, dp[i] stores the maximum of `i` or the product of its breaks.
        dp[1] = 1
        dp[2] = 2
        dp[3] = 3

        # Fill the DP table from 4 to n.
        for i in range(4, n + 1):
            max_product = 0
            # Iterate through all possible first numbers 'j' of the break.
            for j in range(1, i // 2 + 1):
                # For a given break i = j + (i-j), the product is dp[j] * dp[i-j].
                # We use dp[i-j] because breaking i-j further might yield a
                # larger product than using (i-j) as a single factor.
                product = dp[j] * dp[i - j]
                max_product = max(max_product, product)
            dp[i] = max_product
            
        return dp[n]

    # --- Alternative O(1) Mathematical Solution ---
    #
    # def integerBreak_math(self, n: int) -> int:
    #     """
    #     This solution is based on the mathematical observation that the maximum
    #     product is achieved by breaking the number into as many 3s as possible.
    #
    #     Explanation:
    #     - If we can break a number `x >= 4` into `2` and `x-2`, the product is
    #       `2 * (x-2) = 2x - 4`. This is greater than or equal to `x` itself.
    #       This shows that we should not have factors of 4 or more.
    #     - We can always replace a factor of 4 with 2+2 (product 4), and a factor
    #       of 5 with 2+3 (product 6 > 5), etc. So all factors must be 2s or 3s.
    #     - Between 2s and 3s, 3s are better. For a sum of 6, `3+3` gives product 9,
    #       while `2+2+2` gives product 8. So, we prefer 3s over 2s.
    #
    #     The strategy is to use as many 3s as possible. We only need to handle
    #     the remainder when n is divided by 3.
    #
    #     Time Complexity: O(log n) due to the power function, effectively O(1).
    #     Space Complexity: O(1)
    #     """
    #     # Base cases: if n is 2 or 3, we must break it, resulting in 1*1=1 and 1*2=2.
    #     if n <= 3:
    #         return n - 1
    #
    #     # Case 1: n is perfectly divisible by 3 (e.g., n=6 -> 3*3).
    #     if n % 3 == 0:
    #         return 3 ** (n // 3)
    #
    #     # Case 2: n has a remainder of 1 when divided by 3 (e.g., n=7 -> 3+3+1).
    #     # Instead of a factor of 1, it's better to "borrow" from a 3 to make 4.
    #     # So, 3+1 becomes 2+2. The product changes from 3*1=3 to 2*2=4.
    #     # Example: n=10 -> 3+3+3+1 -> 3+3+2+2. Product is 3**(n//3 - 1) * 4.
    #     if n % 3 == 1:
    #         return 3 ** (n // 3 - 1) * 4
    #
    #     # Case 3: n has a remainder of 2 when divided by 3 (e.g., n=5 -> 3+2).
    #     # We are left with a factor of 2, which is fine.
    #     # Example: n=8 -> 3+3+2. Product is 3**(n//3) * 2.
    #     # This is equivalent to `if n % 3 == 2:`
    #     return 3 ** (n // 3) * 2