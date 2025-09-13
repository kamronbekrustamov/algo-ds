from typing import List

class Solution:
    def transpose(self, matrix: List[List[int]]) -> List[List[int]]:
        """
        Computes the transpose of a matrix.

        The transpose of a matrix is a new matrix whose rows are the columns of the
        original, and whose columns are the rows of the original.

        Algorithm:
        1. Get the dimensions (number of rows and columns) of the original matrix.
        2. Create a new result matrix with the dimensions swapped (columns x rows).
        3. Iterate through each element of the original matrix using its row and column index.
        4. Place the element from `matrix[row][col]` into the new position
           `result[col][row]`, effectively swapping the row and column indices.

        Time Complexity: O(M * N), where M is the number of rows and N is the number of columns.
        Space Complexity: O(M * N) to store the new transposed matrix.
        """
        # Get the dimensions of the original matrix.
        num_rows = len(matrix)
        num_cols = len(matrix[0])

        # Create the new matrix with swapped dimensions, initialized with zeros.
        result = [[0 for _ in range(num_rows)] for _ in range(num_cols)]

        # Iterate through each element of the original matrix.
        for row_idx in range(num_rows):
            for col_idx in range(num_cols):
                # The core transpose operation: swap row and column indices.
                result[col_idx][row_idx] = matrix[row_idx][col_idx]
        return result