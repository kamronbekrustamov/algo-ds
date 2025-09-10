from typing import List


class Solution:
    def largeGroupPositions(self, s: str) -> List[List[int]]:
        # A large group requires at least 3 characters.
        if len(s) < 3:
            return []

        result = []
        start_index = 0
        # We iterate up to len(s) to handle the last group gracefully.
        for i in range(1, len(s) + 1):
            # A group ends if we're at the end of the string or the character changes.
            if i == len(s) or s[i] != s[i - 1]:
                # Check if the group we just passed is large.
                if i - start_index >= 3:
                    result.append([start_index, i - 1])
                # Start a new group at the current position.
                start_index = i
        return result
