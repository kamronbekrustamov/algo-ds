import collections

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        """
        Finds the minimum window substring of `s` which contains all the characters of `t`.

        This solution uses the sliding window technique.

        1. Create a frequency map (`t_counts`) for the target string `t`.
        2. Use two pointers, `left` and `right`, to define a window in `s`.
        3. Expand the window by moving `right`. As we add characters to the window,
           we track how many required characters from `t` we have satisfied in a
           `have` counter. `need` is the number of unique characters in `t`.
        4. Once `have == need`, the window is valid (it contains all characters from `t`).
           We then try to shrink the window from the `left` to find the smallest
           possible valid window.
        5. We keep track of the smallest valid window found so far and return it at the end.

        The time complexity is O(len(s) + len(t)) because each pointer moves
        across the string at most once. The space complexity is O(K) where K is
        the number of unique characters in `t`.

        Args:
            s: The string to search within.
            t: The string containing the characters to find.

        Returns:
            The minimum window substring, or "" if no such window exists.
        """
        if not t or not s:
            return ""

        # 1. Create the frequency map for the target string 't'.
        t_counts = collections.Counter(t)
        
        # 2. Initialize window variables.
        window_counts = {}
        left = 0
        
        # `have` tracks how many unique characters in `t` we have satisfied.
        # `need` is the total number of unique characters in `t` we need to satisfy.
        have, need = 0, len(t_counts)
        
        # Variables to store the result: length and indices of the min window.
        res_len = float("inf")
        res_indices = [-1, -1]

        # 3. Expand the window by moving the right pointer.
        for right, char in enumerate(s):
            # Add the new character to our window's frequency map.
            window_counts[char] = window_counts.get(char, 0) + 1

            # If the character is needed and we have now met its required frequency,
            # increment our `have` count.
            if char in t_counts and window_counts[char] == t_counts[char]:
                have += 1

            # 4. Once the window is valid, try to shrink it from the left.
            while have == need:
                # a. The window is valid, check if it's the smallest so far.
                current_len = right - left + 1
                if current_len < res_len:
                    res_len = current_len
                    res_indices = [left, right]

                # b. Shrink the window by moving the left pointer.
                left_char = s[left]
                window_counts[left_char] -= 1
                left += 1

                # c. If removing the left character made the window invalid,
                #    decrement our `have` count.
                if left_char in t_counts and window_counts[left_char] < t_counts[left_char]:
                    have -= 1
        
        # 5. Return the minimum window found.
        l, r = res_indices
        return s[l : r + 1] if res_len != float("inf") else ""