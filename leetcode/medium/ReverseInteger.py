class Solution:
    def reverse(self, x: int) -> int:
        # Define the positive 32-bit integer limit.
        INT_MAX = 2**31 - 1

        sign = 1 if x >= 0 else -1
        x = abs(x)

        result = 0
        while x != 0:
            digit = x % 10
            
            # Before updating the result, check for potential overflow.
            # This check ensures that `result * 10 + digit` will not exceed INT_MAX.
            if result > INT_MAX // 10 or (result == INT_MAX // 10 and digit > 7):
                return 0
            
            result = result * 10 + digit
            x //= 10

        # The result is guaranteed to be within the 32-bit signed integer range
        # due to the checks inside the loop.
        return sign * result