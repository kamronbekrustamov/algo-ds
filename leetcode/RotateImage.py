from typing import List


class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Rotates an n x n 2D matrix 90 degrees clockwise in-place.

        The rotation is achieved in two steps:
        1. Transpose the matrix: Swap elements across the main diagonal.
        2. Reverse each row: After transposing, reversing each row completes the rotation.
        """
        length = len(matrix)

        # Step 1: Transpose the matrix.
        # This swaps the element at (i, j) with the element at (j, i).
        for i in range(length):
            # We only need to iterate up to the main diagonal (j starts from i).
            for j in range(i, length):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]

        # Step 2: Reverse each row.
        for i in range(length):
            # We iterate from the start of the row to the middle.
            for j in range(0, length // 2):
                # Swap the j-th element from the start with the j-th element from the end.
                matrix[i][j], matrix[i][length - 1 - j] = matrix[i][length - 1 - j], matrix[i][j]
