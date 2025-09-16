from typing import List


class Solution:
    def reverseString(self, s: List[str]) -> None:
        """
        Reverses a list of characters in-place using a two-pointer approach.

        This method initializes two pointers, one at the beginning (`left`) and one
        at the end (`right`) of the list. It then iterates as long as the left
        pointer is less than the right pointer, swapping the elements at these
        positions and moving the pointers towards the center.

        Args:
            s: A list of strings (characters) to be reversed.

        Time Complexity: O(N), where N is the number of elements in the list.
        Space Complexity: O(1), as the reversal is done in-place.
        """
        # Initialize pointers at the start and end of the list.
        left, right = 0, len(s) - 1

        # Loop until the pointers meet or cross.
        while left < right:
            # Swap the elements at the left and right pointers.
            s[left], s[right] = s[right], s[left]
            # Move the left pointer one step to the right.
            left += 1
            # Move the right pointer one step to the left.
            right -= 1
        