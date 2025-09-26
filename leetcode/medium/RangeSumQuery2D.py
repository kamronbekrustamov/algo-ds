
from typing import List

# Time: O(m*n) for __init__, O(1) for sumRegion
# Space: O(m*n) for the prefix sum matrix
class NumMatrix:
    """
    This class allows for fast calculation of the sum of elements within a 2D rectangular region.
    It uses a 2D prefix sum matrix (also known as a summed-area table) to achieve this.
    After an initial O(m*n) pre-computation, region sums can be calculated in O(1) time.
    """

    def __init__(self, matrix: List[List[int]]):
        """
        Initializes the data structure by building a 2D prefix sum matrix.

        :param matrix: A 2D list of integers (the input matrix).
        """
        if not matrix or not matrix[0]:
            # Handle empty or invalid input matrix
            self.prefix = None
            return

        rows, cols = len(matrix), len(matrix[0])
        
        # Initialize a prefix sum matrix with an extra row and column of zeros.
        # This padding simplifies the calculation logic in both __init__ and sumRegion.
        self.prefix = [[0] * (cols + 1) for _ in range(rows + 1)]

        # Build the 2D prefix sum matrix.
        for r in range(rows):
            for c in range(cols):
                # The value at prefix[r+1][c+1] is the sum of the rectangle
                # from (0,0) to (r,c) in the original matrix.
                # Formula: Sum(ABCD) = Sum(D) - Sum(B) - Sum(C) + Sum(A)
                # where D is the current rectangle, B and C are adjacent rectangles,
                # and A is the top-left overlapping rectangle.
                # Rearranging gives: Sum(D) = Val(D) + Sum(B) + Sum(C) - Sum(A)
                self.prefix[r + 1][c + 1] = matrix[r][c] + self.prefix[r][c + 1] + self.prefix[r + 1][c] - self.prefix[r][c]

    def sumRegion(self, row1: int, col1: int, row2: int, col2: int) -> int:
        """
        Calculates the sum of the elements in the rectangle defined by the top-left
        corner (row1, col1) and the bottom-right corner (row2, col2).

        :param row1: The starting row index of the region.
        :param col1: The starting column index of the region.
        :param row2: The ending row index of the region.
        :param col2: The ending column index of the region.
        :return: The sum of the elements in the specified region.
        """
        if self.prefix is None:
            return 0

        # The sum of a region is calculated using the pre-computed prefix sums.
        # Let the desired region be a rectangle with corners at (row1, col1) and (row2, col2).
        # The sum is: (sum up to bottom-right) - (sum up to area above) - (sum up to area to the left) + (sum of overlapping top-left area).
        # This corresponds to: prefix[row2+1][col2+1] - prefix[row1][col2+1] - prefix[row2+1][col1] + prefix[row1][col1].
        
        # All coordinates are increased by 1 due to the padding in the prefix matrix.
        bottom_right = self.prefix[row2 + 1][col2 + 1]
        above = self.prefix[row1][col2 + 1]
        left = self.prefix[row2 + 1][col1]
        top_left = self.prefix[row1][col1]
        
        return bottom_right - above - left + top_left


# Your NumMatrix object will be instantiated and called as such:
# obj = NumMatrix(matrix)
# param_1 = obj.sumRegion(row1,col1,row2,col2)
