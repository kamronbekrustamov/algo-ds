class Solution {
    /**
     * Rotates a square matrix 90 degrees clockwise *in place*.
     * The rotation is achieved by the standard two-step process:
     * 1. Transpose the matrix (reflection across the main diagonal).
     * 2. Reverse each row (reflection across the vertical midline).
     *
     * @param matrix The square matrix of integers to be rotated.
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Handle edge cases for 0x0 or 1x1 matrix, no rotation needed.
        if (n <= 1) {
            return;
        }

        // --- Step 1: Transpose the matrix (Reflection across the main diagonal) ---
        // Swap matrix[row][col] with matrix[col][row].
        // The inner loop starts at 'row' (col = row) to ensure each pair is swapped only once.
        for (int row = 0; row < n; row++) {
            for (int col = row; col < n; col++) {
                // Swap the element at (row, col) with its transposed counterpart at (col, row)
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;

                // Note: The 'if (row != col)' check is technically unnecessary since
                // swapping an element with itself (on the diagonal) is harmless.
            }
        }

        // --- Step 2: Reverse each row (Reflection across the vertical midline) ---
        // This final step completes the 90-degree clockwise rotation.
        for (int row = 0; row < n; row++) {
            // Use two pointers, 'left' starting at the start of the row and
            // 'right' starting at the end of the row, to swap elements.
            int left = 0;
            int right = n - 1;

            while (left < right) {
                // Swap the elements at the left and right pointers
                int temp = matrix[row][left];
                matrix[row][left] = matrix[row][right];
                matrix[row][right] = temp;

                // Move pointers toward the center
                left++;
                right--;
            }
        }
    }
}