class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        result = list(s)
        left, right = 0, len(result) - 1

        while left < right:
            # If the character at the left pointer is not a letter, move right.
            if not result[left].isalpha():
                left += 1
            # If the character at the right pointer is not a letter, move left.
            elif not result[right].isalpha():
                right -= 1
            # If both are letters, swap them and move both pointers.
            else:
                result[left], result[right] = result[right], result[left]
                left += 1
                right -= 1
        
        return "".join(result)