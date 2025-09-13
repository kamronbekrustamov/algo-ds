from typing import List


class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        """
        Traverses an m x n matrix in spiral order.

        This solution uses four pointers to define the boundaries of the matrix:
        `top`, `bottom`, `left`, and `right`. We traverse the outer layer of the
        current boundaries and then shrink them inward until the pointers cross.

        Algorithm:
        1. Initialize boundary pointers: `top=0`, `bottom=m-1`, `left=0`, `right=n-1`.
        2. Loop as long as `top <= bottom` and `left <= right`.
        3. In each iteration of the loop, traverse one full layer:
           a. Traverse Right: Go from `left` to `right` along the `top` row. Increment `top`.
           b. Traverse Down: Go from `top` to `bottom` along the `right` column. Decrement `right`.
           c. Traverse Left: Go from `right` to `left` along the `bottom` row. Decrement `bottom`.
           d. Traverse Up: Go from `bottom` to `top` along the `left` column. Increment `left`.
        4. Add checks before the "Traverse Left" and "Traverse Up" steps to handle
           matrices that are not square (e.g., single rows or columns).

        Time Complexity: O(m * n), as we visit each element exactly once.
        Space Complexity: O(1), excluding the space for the result array.
        """
        if not matrix or not matrix[0]:
            return []

        m, n = len(matrix), len(matrix[0])
        result = []
        top, bottom = 0, m - 1
        left, right = 0, n - 1

        while top <= bottom and left <= right:
            # 1. Traverse from left to right along the top row.
            for j in range(left, right + 1):
                result.append(matrix[top][j])
            top += 1  # Move the top boundary down.

            # 2. Traverse from top to bottom along the right column.
            for i in range(top, bottom + 1):
                result.append(matrix[i][right])
            right -= 1  # Move the right boundary left.

            # Check if there's still a valid row to traverse.
            if top <= bottom:
                # 3. Traverse from right to left along the bottom row.
                for j in range(right, left - 1, -1):
                    result.append(matrix[bottom][j])
                bottom -= 1  # Move the bottom boundary up.

            # Check if there's still a valid column to traverse.
            if left <= right:
                # 4. Traverse from bottom to top along the left column.
                for i in range(bottom, top - 1, -1):
                    result.append(matrix[i][left])
                left += 1  # Move the left boundary right.

        return result
