from typing import List

class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        """
        Finds the smallest character in the list that is larger than the target.
        The letters list is sorted and wraps around.

        This solution uses a manual binary search implementation.
        """
        # If the target is greater than or equal to the last element, the answer
        # must wrap around to the first element of the list.
        if target >= letters[-1]:
            return letters[0]

        # Standard binary search to find the smallest letter > target.
        left, right = 0, len(letters) - 1
        ans = letters[0]  # Default answer if target is smaller than all elements

        while left <= right:
            mid = left + (right - left) // 2
            if letters[mid] > target:
                # This is a potential answer, store it and check for a better one to the left.
                ans = letters[mid]
                right = mid - 1
            else:
                # The answer must be to the right.
                left = mid + 1
        return ans