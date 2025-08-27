from typing import List


class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        ans = []

        indices = []
        start_index = 0
        while True:
            index = s[start_index:].find(c)
            if index == -1:
                break
            indices.append(start_index + index)
            start_index = start_index + index + 1

        first = indices.pop(0)
        second = first

        for i in range(0, len(s)):
            ans.append(min(abs(first - i), abs(second - i)))
            if i >= second and indices:
                first, second = second, indices.pop(0)

        return ans
