class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        """
        Finds the length of the longest substring without repeating characters.
        This is solved using the sliding window technique.
        """
        # char_map stores the last seen index of each character.
        char_map = {}
        max_length = 0
        # `left` is the starting index of the current window.
        left = 0

        # `right` is the ending index of the current window, expanded one char at a time.
        for right, char in enumerate(s):
            # If the current character is already in our window (i.e., we've seen it
            # at an index greater than or equal to `left`), we must shrink the window.
            if char in char_map and char_map[char] >= left:
                # Move the left pointer to the position right after the last
                # occurrence of the current character.
                left = char_map[char] + 1

            # Update the last seen index for the current character.
            char_map[char] = right

            # Calculate the length of the current valid window and update the max if needed.
            current_length = right - left + 1
            max_length = max(max_length, current_length)

        return max_length