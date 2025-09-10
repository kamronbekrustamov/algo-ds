class Solution:
    def rotatedDigits(self, n: int) -> int:
        count = 0
        # Digits that invalidate the number if present.
        invalid_digits = {'3', '4', '7'}
        # Digits that must be present for the number to be "good" (i.e., rotate to a different number).
        changing_digits = {'2', '5', '6', '9'}

        for i in range(1, n + 1):
            s = str(i)

            # A valid number must not contain any invalid digits.
            # We can use `any` with a generator for an efficient check that short-circuits.
            if any(digit in invalid_digits for digit in s):
                continue

            # A "good" number must rotate to a different number,
            # which means it must contain at least one of the changing digits.
            if any(digit in changing_digits for digit in s):
                count += 1
        return count

        