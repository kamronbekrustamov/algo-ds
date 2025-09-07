from typing import List


class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        """
        Searches for a value in an m x n matrix with two special properties:
        1. Integers in each row are sorted from left to right.
        2. The first integer of each row is greater than the last integer of the previous row.

        These properties allow us to treat the 2D matrix as a single, flattened
        sorted array and perform an efficient binary search.
        """
        if not matrix or not matrix[0]:
            return False

        m, n = len(matrix), len(matrix[0])
        # Treat the matrix as a 1D array of size m * n.
        left, right = 0, m * n - 1

        while left <= right:
            # `mid_idx` is the index in the conceptual 1D array.
            mid_idx = left + (right - left) // 2

            # Convert the 1D index back to 2D matrix coordinates.
            row = mid_idx // n
            col = mid_idx % n
            mid_element = matrix[row][col]

            if mid_element == target:
                return True
            elif mid_element < target:
                left = mid_idx + 1
            else:
                right = mid_idx - 1

        return False
