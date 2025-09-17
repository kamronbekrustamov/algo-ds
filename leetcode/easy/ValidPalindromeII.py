class Solution:
    def validPalindrome(self, s: str) -> bool:
        """
        Determines if a string can be a palindrome by deleting at most one character.

        This solution uses a two-pointer approach. We scan the string from both
        ends inward. If we find a pair of characters that do not match, we are
        faced with two possibilities to make the string a palindrome:
        1. Delete the character at the left pointer.
        2. Delete the character at the right pointer.

        We then check if the remaining substring is a palindrome in either of these
        two cases. If at least one of them is a palindrome, the original string
        can be considered a valid palindrome II. If the initial scan completes
        without any mismatches, the string is already a palindrome.

        Args:
            s: The input string.

        Returns:
            True if the string can be a palindrome after at most one deletion,
            False otherwise.

        Time Complexity: O(N), where N is the length of the string.
        Space Complexity: O(1).
        """
        def is_palindrome_range(i: int, j: int) -> bool:
            """Helper function to check if the substring s[i:j+1] is a palindrome."""
            while i < j:
                if s[i] != s[j]:
                    return False
                i += 1
                j -= 1
            return True

        left, right = 0, len(s) - 1
        while left < right:
            if s[left] != s[right]:
                # Found a mismatch. We must use our one deletion.
                # Check if the string is a palindrome if we skip the left character...
                # ...OR if we skip the right character.
                return is_palindrome_range(left + 1, right) or is_palindrome_range(left, right - 1)
            # Characters match, move pointers inward.
            left += 1
            right -= 1
        # If the loop completes, the string is already a palindrome.
        return True
