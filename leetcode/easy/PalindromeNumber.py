class Solution:
    def isPalindrome(self, x: int) -> bool:
        # Edge cases:
        # 1. Negative numbers are not palindromes.
        # 2. If the last digit is 0, the first digit must also be 0.
        #    Only 0 itself satisfies this condition.
        if x < 0 or (x % 10 == 0 and x != 0):
            return False

        reverted_number = 0
        # Revert the second half of the number.
        # The loop stops when we've processed half the digits.
        while x > reverted_number:
            reverted_number = reverted_number * 10 + x % 10
            x //= 10

        # For numbers with an odd number of digits (e.g., 12321),
        # the middle digit (3) will be at the end of `reverted_number`.
        # We can get rid of it by integer division.
        # For even digits (e.g., 1221), `x` and `reverted_number` will be equal (12 == 12).
        return x == reverted_number or x == reverted_number // 10
