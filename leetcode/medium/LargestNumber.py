from typing import List
import functools

class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        def compare(a: str, b: str):
            ab = a + b
            ba = b + a
            if ab > ba:
                return 1
            elif ba > ab:
                return -1
            else:
                return 0
        result = [str(num) for num in nums]
        result.sort(reverse=True, key=functools.cmp_to_key(compare))
        res = "".join(result).lstrip("0")
        return res if res else "0"
