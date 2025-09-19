# The guess API is already defined for you.
# @param num, your guess
# @return -1 if num is higher than the picked number
#          1 if num is lower than the picked number
#          otherwise return 0
# def guess(num: int) -> int:

class Solution:
    def guessNumber(self, n: int) -> int:
        low, high = 1, n
        while low <= high:
            middle = low + (high - low) // 2
            res = guess(middle)
            if res == 0:
                return middle
            elif res == 1:
                low = middle + 1
            elif res == -1:
                high = middle - 1
        