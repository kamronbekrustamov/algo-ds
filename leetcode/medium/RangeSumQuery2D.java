/**
 * A utility class to perform efficient range sum queries on a 2D immutable matrix.
 * Uses the 2D Prefix Sum algorithm (Inclusion-Exclusion Principle).
 */
public class NumMatrix {
    
    // Using a 1-based index approach for the prefix array to simplify boundary checks.
    // prefixSum[i][j] stores the sum of the sub-matrix from (0,0) to (i-1, j-1).
    private final int[][] prefixSum;
    private final boolean isEmpty;

    /**
     * Initializes the NumMatrix with the given integer matrix.
     * Pre-computes the prefix sums in O(m * n) time.
     *
     * @param matrix The 2D input matrix.
     */
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            this.isEmpty = true;
            this.prefixSum = null;
            return;
        }

        this.isEmpty = false;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Size is (rows + 1) x (cols + 1) to handle 0-index edge cases gracefully without "if" checks.
        this.prefixSum = new int[rows + 1][cols + 1];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Formula: Current Cell + Area Above + Area Left - Overlap (Top-Left diagonal)
                this.prefixSum[r + 1][c + 1] = matrix[r][c] 
                                             + this.prefixSum[r][c + 1] 
                                             + this.prefixSum[r + 1][c] 
                                             - this.prefixSum[r][c];
            }
        }
    }

    /**
     * Calculates the sum of the elements inside the rectangle defined by its
     * upper-left corner (row1, col1) and lower-right corner (row2, col2).
     *
     * @param row1 The row index of the upper-left corner.
     * @param col1 The column index of the upper-left corner.
     * @param row2 The row index of the lower-right corner.
     * @param col2 The column index of the lower-right corner.
     * @return The sum of the region. Returns 0 if the matrix is empty.
     * @throws IllegalArgumentException if indices are out of bounds.
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (this.isEmpty) {
            return 0;
        }
        
        // Optional: Input validation for robustness
        validateIndices(row1, col1, row2, col2);

        // Retrieve pre-computed sums
        // prefixSum[row2 + 1][col2 + 1] represents sum from (0,0) to (row2, col2)
        int totalArea   = this.prefixSum[row2 + 1][col2 + 1];
        int areaAbove   = this.prefixSum[row1][col2 + 1];
        int areaLeft    = this.prefixSum[row2 + 1][col1];
        int areaOverlap = this.prefixSum[row1][col1];

        // Apply Inclusion-Exclusion Principle
        return totalArea - areaAbove - areaLeft + areaOverlap;
    }

    private void validateIndices(int r1, int c1, int r2, int c2) {
        int R = prefixSum.length - 1;
        int C = prefixSum[0].length - 1;
        if (r1 < 0 || c1 < 0 || r2 >= R || c2 >= C || r1 > r2 || c1 > c2) {
             throw new IllegalArgumentException("Invalid sub-matrix indices.");
        }
    }
}