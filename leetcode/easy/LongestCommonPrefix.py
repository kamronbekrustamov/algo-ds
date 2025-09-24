from typing import List

class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        # If the input list is empty, there's no common prefix.
        if not strs:
            return ""

        # We can use the first string in the list as the reference for the prefix.
        # We will iterate through its characters.
        for i, char in enumerate(strs[0]):
            # Now, for each character, check if it's the same in all other strings.
            for s in strs[1:]:
                # The prefix ends if:
                # 1. We've reached the end of another string (`i >= len(s)`).
                # 2. The character at the current position does not match.
                if i >= len(s) or s[i] != char:
                    # If we find a mismatch, the common prefix is the part of the
                    # first string up to the current index `i`.
                    return strs[0][:i]
        
        # If the loop completes without returning, it means the entire first string
        # is a common prefix for all other strings.
        return strs[0]