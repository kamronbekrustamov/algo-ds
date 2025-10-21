class Solution {
    /**
     * Sets the entire row and column to 0 if an element is 0.
     * This is done in-place using O(1) extra space complexity.
     * The first row and first column are used as markers to track which
     * rows and columns need to be zeroed out.
     *
     * Time Complexity: O(M * N) where M is the number of rows and N is the number of columns.
     * Space Complexity: O(1) extra space.
     *
     * @param matrix The 2D integer matrix to be modified.
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return;
        int cols = matrix[0].length;
        
        // Use a boolean to track if the first column contains a zero, 
        // as matrix[0][0] will be used to track the first row's zero status.
        boolean firstColHasZero = false;

        // Step 1: Use the first row and first column as markers.
        // Check for zeros in the entire matrix (excluding the first column temporarily).
        for (int i = 0; i < rows; i++) {
            // Check if the first column has a zero (must be done before potentially overwriting matrix[i][0]).
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
            }
            
            // Start checking from the second column (j=1)
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    // Mark the corresponding first-row element
                    matrix[0][j] = 0; 
                    // Mark the corresponding first-column element
                    matrix[i][0] = 0; 
                }
            }
        }

        // Step 2: Zero out the interior cells (rows i > 0 and columns j > 0)
        // based on the markers set in the first row and first column.
        // We iterate from (1, 1) to avoid modifying the markers themselves prematurely.
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                // If the row marker (matrix[i][0]) or the column marker (matrix[0][j]) is 0,
                // set the current cell to 0.
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 3: Zero out the first row if its marker (matrix[0][0]) is 0.
        // This is done after the interior to ensure the marker is used correctly.
        if (matrix[0][0] == 0) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 4: Zero out the first column if the separate marker indicates it had a zero.
        if (firstColHasZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}