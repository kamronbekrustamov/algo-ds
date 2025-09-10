from typing import List

class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        m, n = len(matrix), len(matrix[0])
        
        # Use first row and first column as markers.
        # Need a separate variable to track if the first column itself needs to be zeroed.
        first_col_has_zero = False

        # Step 1: Mark rows/cols that need to be zeroed using the first row/col as markers.
        # Also, check if the first column itself needs to be zeroed.
        for i in range(m):
            if matrix[i][0] == 0:
                first_col_has_zero = True
            for j in range(1, n): # Start from 1 to avoid double-counting matrix[i][0]
                if matrix[i][j] == 0:
                    matrix[i][0] = 0  # Mark row i
                    matrix[0][j] = 0  # Mark column j

        # Step 2: Use the markers to set elements to zero (excluding the first row/col for now).
        # Iterate from (1,1) to avoid overwriting markers needed for first row/col.
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0

        # Step 3: Handle the first row based on its marker.
        # This must be done after processing the rest of the matrix to avoid
        # using a zeroed marker to zero out other cells prematurely.
        if matrix[0][0] == 0:
            for j in range(n):
                matrix[0][j] = 0

        # Step 4: Handle the first column based on its separate marker.
        # This must be done last to avoid affecting the first row's marker.
        if first_col_has_zero:
            for i in range(m):
                matrix[i][0] = 0