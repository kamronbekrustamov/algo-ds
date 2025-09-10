from typing import List


class Solution:
    def matrixReshape(self, mat: List[List[int]], r: int, c: int) -> List[List[int]]:
        row_count = len(mat)
        col_count = len(mat[0])

        if row_count * col_count != r * c:
            return mat
        
        matrix = [[0 for _ in range(c)] for _ in range(r)]

        for i in range(r):
            for j in range(c):
                matrix[i][j] = mat[(i * c + j) // col_count][(i * c + j)  % col_count]
        return matrix





        