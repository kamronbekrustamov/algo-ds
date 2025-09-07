class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        """
        Checks if two strings are anagrams of each other.
        """
        if len(s) != len(t):
            return False

        char_map = [0] * 26
        for char in s:
            char_map[ord(char) - ord('a')] += 1

        for char in t:
            char_map[ord(char) - ord('a')] -= 1
        
        for count in char_map:
            if count != 0:
                return False
        
        return True

