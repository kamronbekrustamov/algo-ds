from typing import List

class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        """
        Restores all possible valid IP address combinations from a given string `s`.
        A valid IP address consists of exactly four integers, each integer ranging from 0 to 255,
        separated by single dots. Each integer cannot have leading zeros unless it is the number 0 itself.

        Args:
            s: The input string containing only digits.

        Returns:
            A list of all possible valid IP address strings.
        """
        
        n = len(s)
        # A valid IP address has a minimum length of 4 (e.g., "0.0.0.0")
        # and a maximum length of 12 (e.g., "255.255.255.255").
        if not (4 <= n <= 12):
            return []
        
        result = [] # This list will store all valid IP addresses found.

        # The backtracking function to explore combinations.
        # `start_index`: The starting index in the string `s` for the current segment.
        # `segments`: A list storing the current IP address segments.
        def backtrack(start_index: int, segments: List[str]):
            # Base case 1: If we have formed 4 segments.
            if len(segments) == 4:
                # If we have used all characters in `s`, then it's a valid IP address.
                if start_index == n:
                    result.append(".".join(segments))
                return # Either valid and added, or invalid (not all chars used).

            # Base case 2: If we have used all characters but haven't formed 4 segments.
            if start_index == n:
                return

            # Try to form the next segment (1, 2, or 3 digits long).
            # A segment can be 1 digit long.
            if start_index < n:
                segment = s[start_index : start_index + 1]
                segments.append(segment)
                backtrack(start_index + 1, segments)
                segments.pop() # Backtrack

            # A segment can be 2 digits long.
            # It cannot have a leading zero (e.g., "01" is invalid).
            if start_index + 1 < n and s[start_index] != '0':
                segment = s[start_index : start_index + 2]
                segments.append(segment)
                backtrack(start_index + 2, segments)
                segments.pop() # Backtrack

            # A segment can be 3 digits long.
            # It cannot have a leading zero and its value must be between 0 and 255.
            if start_index + 2 < n and s[start_index] != '0':
                segment = s[start_index : start_index + 3]
                if 0 <= int(segment) <= 255:
                    segments.append(segment)
                    backtrack(start_index + 3, segments)
                    segments.pop() # Backtrack

        # Start the backtracking process from the beginning of the string with an empty list of segments.
        backtrack(0, [])
        return result            

