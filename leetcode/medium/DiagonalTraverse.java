/**
 * Solution for Diagonal Traverse of a Matrix.
 */
class Solution {

    /**
     * Traverses a matrix in a diagonal order (Zigzag).
     *
     * @param mat The input 2D integer matrix (m x n).
     * @return An integer array containing the diagonal traversal.
     */
    public int[] findDiagonalOrder(int[][] mat) {
        // 1. Edge Case Handling: Return empty array for null or empty inputs
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return new int[0];
        }

        // 2. Initialization
        int m = mat.length;      // Rows
        int n = mat[0].length;   // Cols
        int[] result = new int[m * n];
        
        int r = 0; // Current row
        int c = 0; // Current col
        boolean goingUp = true; // Direction flag

        // 3. Iterate through every element in the matrix
        for (int i = 0; i < result.length; i++) {
            result[i] = mat[r][c];

            // 4. Calculate next coordinates based on current direction
            if (goingUp) {
                // --- Strategy: Moving Up-Right ---
                // Priority 1: If we hit the Right Wall, we must move DOWN.
                // (Note: This check must come before checking the Top Wall 
                // to handle the top-right corner correctly).
                if (c == n - 1) {
                    r++;
                    goingUp = false;
                } 
                // Priority 2: If we hit the Top Wall, we must move RIGHT.
                else if (r == 0) {
                    c++;
                    goingUp = false;
                } 
                // Priority 3: Standard diagonal move (Up and Right).
                else {
                    r--;
                    c++;
                }
            } else {
                // --- Strategy: Moving Down-Left ---
                // Priority 1: If we hit the Bottom Wall, we must move RIGHT.
                // (Check this before Left Wall to handle bottom-left corner).
                if (r == m - 1) {
                    c++;
                    goingUp = true;
                } 
                // Priority 2: If we hit the Left Wall, we must move DOWN.
                else if (c == 0) {
                    r++;
                    goingUp = true;
                } 
                // Priority 3: Standard diagonal move (Down and Left).
                else {
                    r++;
                    c--;
                }
            }
        }

        return result;
    }
}