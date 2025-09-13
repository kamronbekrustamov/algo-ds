from typing import List


class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        """
        Increments a large integer represented as an array of digits by one.

        This solution iterates through the list from right to left, simulating
        the manual process of adding one with a carry. It modifies the list
        in-place for efficiency and handles the edge case of a leading carry
        (e.g., 999 + 1 = 1000).

        Algorithm:
        1. Iterate through the digits from the last to the first.
        2. If a digit is less than 9, simply increment it and return, as there
           is no carry to propagate.
        3. If a digit is 9, set it to 0 and continue to the next digit to the
           left (this implicitly handles the "carry").
        4. If the loop completes (meaning all digits were 9s), it means we
           have a carry-out. We insert a '1' at the beginning of the list.

        Time Complexity: O(n), where n is the number of digits.
        Space Complexity: O(1) for the in-place modification.
        """
        n = len(digits)
        # Iterate from the last digit to the first.
        for i in range(n - 1, -1, -1):
            # If the current digit is less than 9, we can just increment it and we're done.
            if digits[i] < 9:
                digits[i] += 1
                return digits
            # If the digit is 9, it becomes 0, and we carry over to the next digit.
            digits[i] = 0
        # If the loop finishes, it means all digits were 9s (e.g., [9, 9, 9]).
        # We need to add a new leading 1.
        return [1] + digits