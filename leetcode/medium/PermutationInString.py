class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        """
        Checks if a permutation of s1 exists as a substring in s2.

        This solution uses a sliding window approach with a fixed-size window
        equal to the length of s1. Instead of comparing full frequency maps for
        each window, it uses a single array `freq_diff` to track the difference
        in character frequencies between s1 and the current window in s2.

        The core idea is to maintain a `matches` count, which tracks how many
        characters have a frequency difference of zero. When `matches` equals 26,
        it means the character counts in s1 and the current window are identical,
        and thus a permutation has been found. This allows for O(1) updates
        per window slide.

        The overall time complexity is O(len(s2)), and space complexity is O(1).

        Args:
            s1: The pattern string.
            s2: The string to search within.

        Returns:
            True if a permutation of s1 is a substring of s2, False otherwise.
        """
        if len(s1) > len(s2):
            return False

        # freq_diff will store the difference in frequencies.
        # A value of 0 means the frequency is the same in s1 and the window.
        freq_diff = [0] * 26
        s1_len = len(s1)

        # Initialize the first window and the frequency difference map.
        for i in range(s1_len):
            freq_diff[ord(s1[i]) - ord('a')] += 1
            freq_diff[ord(s2[i]) - ord('a')] -= 1

        # Count how many character frequencies are already perfectly matched.
        matches = 0
        for freq in freq_diff:
            if freq == 0:
                matches += 1
        
        if matches == 26:
            return True

        # Slide the window across s2.
        for i in range(s1_len, len(s2)):
            # Character entering the window from the right.
            right_char_idx = ord(s2[i]) - ord('a')
            # Character leaving the window from the left.
            left_char_idx = ord(s2[i - s1_len]) - ord('a')

            # --- Update for the entering character (right_char) ---
            # This character is now in our window, so we decrement its count
            # from the perspective of the s1 map.
            
            # If its frequency diff was 0, we are losing a match.
            if freq_diff[right_char_idx] == 0:
                matches -= 1
            freq_diff[right_char_idx] -= 1
            # If its frequency diff is now 0, we have gained a match.
            if freq_diff[right_char_idx] == 0:
                matches += 1

            # --- Update for the leaving character (left_char) ---
            # This character is leaving our window, so we add its count back
            # from the perspective of the s1 map.

            # If its frequency diff was 0, we are losing a match.
            if freq_diff[left_char_idx] == 0:
                matches -= 1
            freq_diff[left_char_idx] += 1
            # If its frequency diff is now 0, we have gained a match.
            if freq_diff[left_char_idx] == 0:
                matches += 1

            # Check for a full match after the updates.
            if matches == 26:
                return True

        return False
