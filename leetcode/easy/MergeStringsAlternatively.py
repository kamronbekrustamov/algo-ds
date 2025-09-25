class Solution:
    def mergeAlternately(self, word1: str, word2: str) -> str:
        """
        Merges two strings by adding letters in alternating order,
        starting with the first word. If one string is longer than the other,
        the additional letters are appended to the end of the merged string.
        """
        # Use a list to build the result string efficiently, as string
        # concatenation in a loop can be slow in Python.
        result = []
        
        # Get the lengths of both words for boundary checks.
        n1, n2 = len(word1), len(word2)
        
        # Use two pointers, one for each word.
        p1, p2 = 0, 0

        # Loop as long as there are characters left in either word.
        while p1 < n1 or p2 < n2:
            # If the pointer for word1 is still in bounds, append its character.
            if p1 < n1:
                result.append(word1[p1])
                p1 += 1
            
            # If the pointer for word2 is still in bounds, append its character.
            if p2 < n2:
                result.append(word2[p2])
                p2 += 1
        
        # Join the list of characters into a single string for the final output.
        return "".join(result)