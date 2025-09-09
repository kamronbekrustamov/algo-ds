class Solution:
    def addBinary(self, a: str, b: str) -> str:
        """
        Adds two binary strings and returns their sum as a binary string.

        This solution simulates manual binary addition from right to left,
        handling the carry at each step.
        """
        # Pointers for the end of each string.
        i, j = len(a) - 1, len(b) - 1
        carry = 0
        result = []

        # Loop until we have processed both strings and any remaining carry.
        while i >= 0 or j >= 0 or carry:
            # Get the current digit from string 'a', or 0 if we're past the beginning.
            digit_a = int(a[i]) if i >= 0 else 0
            # Get the current digit from string 'b', or 0 if we're past the beginning.
            digit_b = int(b[j]) if j >= 0 else 0

            # Calculate the sum for the current bit position.
            total = digit_a + digit_b + carry

            # The result bit is the remainder of the sum divided by 2 (total % 2).
            result.append(str(total % 2))
            # The new carry is the quotient of the sum divided by 2 (total // 2).
            carry = total // 2

            # Move pointers to the left for the next iteration.
            i -= 1
            j -= 1

        # The result list was built in reverse order, so we reverse it before joining.
        return "".join(reversed(result))