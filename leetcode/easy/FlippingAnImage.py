from typing import List


class Solution:
    def flipAndInvertImage(self, image: List[List[int]]) -> List[List[int]]:
        for row in image:
            first = 0
            last = len(row) - 1
            while first <= last:
                # Swap the elements and invert them using the XOR operator.
                row[first], row[last] = row[last] ^ 1, row[first] ^ 1
                first += 1
                last -= 1
        return image
