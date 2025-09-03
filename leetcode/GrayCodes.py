from typing import List


class Solution:
    def grayCode(self, n: int) -> List[int]:
        res = [0, 1]
        size = 2 ** n - 1
        count = 2
        while count < size:
            for i in range(count):
                res.append(res[count - i - 1] + count)
            count *= 2
        return res