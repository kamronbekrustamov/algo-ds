class Solution {
    /**
     * Reshapes a given `mat` (matrix) into a new matrix with `r` rows and `c` columns.
     * If the reshape operation is impossible (i.e., the total number of elements
     * in the original matrix does not match the total number of elements in the
     * new matrix), the original matrix is returned.
     *
     * Algorithm:
     * 1. Get the dimensions of the original matrix: `row_count` (number of rows)
     *    and `col_count` (number of columns).
     * 2. Check if the total number of elements in the original matrix (`row_count * col_count`)
     *    is equal to the total number of elements in the target matrix (`r * c`).
     *    If they are not equal, return the original matrix `mat` as reshaping is impossible.
     * 3. Create a new matrix `reshaped_matrix` with `r` rows and `c` columns.
     * 4. Iterate through the new `reshaped_matrix` using indices `i` for rows and `j` for columns.
     * 5. To map the 2D indices `(i, j)` of the new matrix back to the 2D indices of the original matrix:
     *    a. Calculate a linear index: `linear_index = i * c + j`.
     *    b. The row in the original matrix is `original_row = linear_index / col_count`.
     *    c. The column in the original matrix is `original_col = linear_index % col_count`.
     * 6. Assign the element `mat[original_row][original_col]` to `reshaped_matrix[i][j]`.
     * 7. Return the `reshaped_matrix`.
     *
     * @param mat The original matrix.
     * @param r The desired number of rows for the new matrix.
     * @param c The desired number of columns for the new matrix.
     * @return The reshaped matrix, or the original matrix if reshaping is impossible.
     *
     * Time Complexity: O(R * C), where R and C are the dimensions of the new matrix,
     *                   as we iterate through each element of the new matrix once.
     * Space Complexity: O(R * C) to store the new reshaped matrix.
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int row_count = mat.length;
        int col_count = mat[0].length;

        if (row_count * col_count != r * c) {
            return mat;
        }

        int[][] reshaped_matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int linear_index = i * c + j;
                int original_row = linear_index / col_count;
                int original_col = linear_index % col_count;
                reshaped_matrix[i][j] = mat[original_row][original_col];
            }
        }
        return reshaped_matrix;
    }
}
