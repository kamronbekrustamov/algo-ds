class Solution {
    /**
     * Computes the transpose of a matrix.
     *
     * The transpose of a matrix is a new matrix whose rows are the columns of the
     * original, and whose columns are the rows of the original.
     *
     * Algorithm:
     * 1. Get the dimensions (number of rows and columns) of the original matrix.
     * 2. Create a new result matrix with the dimensions swapped (columns x rows).
     * 3. Iterate through each element of the original matrix using its row and column index.
     * 4. Place the element from `matrix[row][col]` into the new position
     *    `result[col][row]`, effectively swapping the row and column indices.
     *
     * Time Complexity: O(M * N), where M is the number of rows and N is the number of columns.
     * Space Complexity: O(M * N) to store the new transposed matrix.
     */
    public int[][] transpose(int[][] matrix) {
        int num_rows = matrix.length;
        int num_cols = matrix[0].length;
        int[][] result = new int[num_cols][num_rows];
        for (int row_idx = 0; row_idx < num_rows; row_idx++) {
            for (int col_idx = 0; col_idx < num_cols; col_idx++) {
                result[col_idx][row_idx] = matrix[row_idx][col_idx];
            }
        }
        return result;
    }
}
