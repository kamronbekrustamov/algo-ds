class Solution:
    def longestPalindrome(self, s: str) -> str:
        """
        Finds the longest palindromic substring in a given string.

        This solution uses the "Expand from Center" approach, which has a time
        complexity of O(n^2) and a space complexity of O(1).

        The algorithm iterates through each character of the string, considering
        it as a potential center of a palindrome. For each center, it expands
        outwards as long as the characters match.

        Two cases are handled for each center `i`:
        1. An odd-length palindrome centered at `i` (e.g., "racecar").
        2. An even-length palindrome centered between `i` and `i+1` (e.g., "aabbaa").

        Args:
            s: The input string.

        Returns:
            The longest palindromic substring.
        """
        if not s:
            return ""

        n = len(s)
        # start and end indices of the longest palindrome found so far.
        start, end = 0, 0

        for i in range(n):
            # --- Case 1: Odd-length palindrome with center at i ---
            # Expand from the center i.
            len1 = self._expand_from_center(s, i, i)
            
            # --- Case 2: Even-length palindrome with center between i and i+1 ---
            # Expand from the center i, i+1.
            len2 = self._expand_from_center(s, i, i + 1)
            
            # Find the maximum length from the two cases.
            max_len = max(len1, len2)
            
            # If we found a new longest palindrome, update its boundaries.
            if max_len > (end - start):
                # Calculate the new start and end indices based on the center and length.
                start = i - (max_len - 1) // 2
                end = i + max_len // 2

        return s[start : end + 1]

    def _expand_from_center(self, s: str, left: int, right: int) -> int:
        """
        Helper function to expand from a center and find the length of the palindrome.
        """
        # Expand outwards as long as the pointers are within the bounds of the
        # string and the characters at the pointers match.
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        
        # The length of the palindrome is right - left - 1.
        # (e.g., if s="aba", l=-1, r=3, length = 3 - (-1) - 1 = 3)
        return right - left - 1

# --- Alternative O(n) Linear Time Solution using Manacher's Algorithm ---
#
# class Solution:
#     def longestPalindrome_manacher(self, s: str) -> str:
#         """
#         Finds the longest palindromic substring using Manacher's Algorithm.
#
#         This is a linear time O(n) solution that uses dynamic programming.
#         The standard "expand from center" approach is O(n^2). Manacher's
#         algorithm cleverly avoids redundant comparisons by reusing information
#         from previously computed palindromes.
#
#         Algorithm Breakdown:
#         1. Transform the String: To handle both odd and even length palindromes
#            uniformly, the string is transformed. e.g., "aba" -> "#a#b#a#" and
#            "abba" -> "#a#b#b#a#". Every character (original or '#') in the
#            new string `T` is a center of a potential palindrome.
#
#         2. LPS Array (P): We use an array `P` where `P[i]` stores the radius of
#            the palindrome centered at `T[i]`. The radius is the length from the
#            center to the edge of the palindrome.
#
#         3. Center and Right Boundary: The algorithm maintains a `center` (C) and
#            a `right_boundary` (R) of the palindrome that extends furthest to the
#            right in the string `T` found so far.
#
#         4. Iteration and Optimization: We iterate through the transformed string `T`.
#            For each position `i`, we find its "mirror" position `i_mirror` with
#            respect to the current `center` C.
#            - The key insight: If `i` is within the `right_boundary` R, we can
#              initialize `P[i]` using the value from its mirror `P[i_mirror]`,
#              capped by the distance to the `right_boundary`. This avoids
#              re-calculating from scratch.
#            - We then try to expand the palindrome centered at `i` beyond the
#              value we initialized from the mirror.
#            - If the palindrome at `i` expands beyond the current `right_boundary` R,
#              we update `center` C and `right_boundary` R to this new, further-
#              reaching palindrome.
#
#         5. Find the Max Palindrome: After filling the `P` array, we find the
#            maximum value in `P`. This corresponds to the center and radius of
#            the longest palindromic substring. We use these to extract the
#            substring from the original string `s`.
#
#         Time Complexity: O(n), because each character is visited and compared a
#                          constant number of times on average.
#         Space Complexity: O(n) for the transformed string and the P array.
#         """
#         # 1. Transform the string `s`.
#         # If s = "abba", T = "#a#b#b#a#"
#         # Add sentinels `^` and `$` to avoid boundary checks during expansion.
#         T = '#'.join('^{}$'.format(s))
#         n = len(T)
#         # 2. Create the LPS array `P`.
#         P = [0] * n
#         # 3. Initialize Center and Right Boundary.
#         C = R = 0
#
#         # 4. Iterate through the transformed string.
#         # We start at 1 and end at n-1 to avoid the sentinels.
#         for i in range(1, n - 1):
#             # Find the mirror of i with respect to the center C.
#             i_mirror = 2 * C - i
#
#             # If i is within the right boundary R, we can reuse information.
#             # P[i] is at least the minimum of the mirror's palindrome length
#             # and the distance from i to the right boundary.
#             if R > i:
#                 P[i] = min(R - i, P[i_mirror])
#             else:
#                 # i is outside the current right boundary, so we have no
#                 # information. Initialize with 0.
#                 P[i] = 0
#
#             # Attempt to expand palindrome centered at i.
#             # We check characters T[i + 1 + P[i]] and T[i - 1 - P[i]].
#             while T[i + 1 + P[i]] == T[i - 1 - P[i]]:
#                 P[i] += 1
#
#             # If the palindrome at i expands past the current right boundary R,
#             # update the center C and the right boundary R.
#             if i + P[i] > R:
#                 C = i
#                 R = i + P[i]
#
#         # 5. Find the maximum element in P.
#         max_len = 0
#         center_index = 0
#         for i in range(1, n - 1):
#             if P[i] > max_len:
#                 max_len = P[i]
#                 center_index = i
#
#         # Calculate the start index and length in the original string `s`.
#         # The center_index in T corresponds to a start in s.
#         # The length of the palindrome in `s` is `max_len`.
#         start = (center_index - max_len) // 2
#         return s[start : start + max_len]
