class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        """
        Finds the length of the longest substring containing the same letter after
        at most 'k' replacements.

        This solution uses the sliding window technique. The core idea is to
        maintain a window [left, right] and a count of characters within that
        window. We expand the window by moving 'right'.

        The key condition to check is:
        `window_size - max_frequency > k`

        - `window_size` is the current length of our substring (`right - left + 1`).
        - `max_frequency` is the count of the most frequent character in the window.
        - `window_size - max_frequency` therefore represents the number of characters
          we would need to replace to make the whole window uniform.

        If this number exceeds `k`, our window is invalid, and we must shrink it
        by moving the `left` pointer forward.

        Args:
            s: The input string.
            k: The maximum number of replacements allowed.

        Returns:
            The length of the longest valid substring.
        """
        left = 0
        max_freq = 0
        max_length = 0
        # A map to store the frequency of characters in the current window.
        window_counts = {}

        for right, char in enumerate(s):
            # Expand the window by including the character at the right pointer.
            window_counts[char] = window_counts.get(char, 0) + 1
            # Update the frequency of the most common character in the window.
            max_freq = max(max_freq, window_counts[char])

            # Check if the current window is invalid.
            # The number of characters to replace is `(right - left + 1) - max_freq`.
            if (right - left + 1) - max_freq > k:
                # If the window is invalid, shrink it from the left.
                window_counts[s[left]] -= 1
                left += 1
            
            # After potentially shrinking, the window is valid. Update the max length.
            max_length = max(max_length, right - left + 1)
            
        return max_length