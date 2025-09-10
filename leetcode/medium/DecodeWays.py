class Solution:
    def numDecodings(self, s: str) -> int:
        """
        Calculates the number of ways a string of digits can be decoded.
        'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".

        This is solved using a bottom-up dynamic programming approach with O(1) space.
        The number of ways to decode up to index `i` depends on the ways to decode
        up to `i-1` and `i-2`.
        """
        # If the string is empty or starts with '0', it's impossible to decode.
        if not s or s[0] == '0':
            return 0

        # `two_back` represents the number of ways to decode the string up to index i-2.
        # `one_back` represents the number of ways to decode the string up to index i-1.
        # Initialize for the first character.
        two_back = 1  # Represents the base case for an empty prefix (1 way).
        one_back = 1  # One way to decode the first character (since s[0] != '0').

        # Iterate from the second character to the end of the string.
        for i in range(1, len(s)):
            current_ways = 0

            # Case 1: Decode the current digit as a single number (1-9).
            # If the current digit is not '0', we can extend all the ways from `one_back`.
            if s[i] != '0':
                current_ways = one_back

            # Case 2: Decode the last two digits as a two-digit number (10-26).
            # If the two-digit number is valid, we can extend all the ways from `two_back`.
            two_digit_val = int(s[i-1:i+1])
            if 10 <= two_digit_val <= 26:
                current_ways += two_back

            # Update our pointers for the next iteration.
            two_back, one_back = one_back, current_ways

        # `one_back` now holds the total number of ways for the entire string.
        return one_back
