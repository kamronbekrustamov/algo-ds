# The guess API is already defined for you.
# @param num, your guess
# @return -1 if num is higher than the picked number
#          1 if num is lower than the picked number
#          otherwise return 0
# def guess(num: int) -> int:

class Solution:
    def guessNumber(self, n: int) -> int:
        # Initialize the search space for binary search.
        # The picked number is guaranteed to be between 1 and n, inclusive.
        low, high = 1, n

        # Loop until the search space is exhausted (low > high).
        while low <= high:
            # Calculate the middle of the current range.
            # This formula is used to prevent potential integer overflow in other languages,
            # though it's not strictly necessary in Python.
            middle = low + (high - low) // 2
            
            # Call the guess API to check if the middle number is the target.
            res = guess(middle)

            # If guess(middle) returns 0, we've found the number.
            if res == 0:
                return middle
            # If guess(middle) returns 1, our guess is too low,
            # so the target must be in the upper half of the range.
            elif res == 1:
                low = middle + 1
            # If guess(middle) returns -1, our guess is too high,
            # so the target must be in the lower half of the range.
            else:  # res == -1
                high = middle - 1
        