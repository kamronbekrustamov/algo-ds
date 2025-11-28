import java.util.ArrayList;
import java.util.List;

/**
 * Solution class for traversing a 2D matrix in a spiral order.
 */
class Solution {
    /**
     * Given an m x n matrix, return all elements of the matrix in spiral order.
     * * @param matrix The input 2D integer array (matrix).
     * @return A List of Integers containing all elements of the matrix 
     * in spiral order.
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // Initialize an empty list to store the result
        List<Integer> result = new ArrayList<>();
        
        // Handle edge case where the matrix is null or empty
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Define boundaries for the spiral traversal
        int top = 0;       // Top row index
        int bottom = rows - 1; // Bottom row index
        int left = 0;      // Left column index
        int right = cols - 1;  // Right column index

        // The loop continues as long as the boundaries haven't crossed
        while (top <= bottom && left <= right) {
            
            // 1. Traverse Right (from left to right along the top row)
            for (int c = left; c <= right; c++) {
                result.add(matrix[top][c]);
            }
            top++; // Move the top boundary down

            // 2. Traverse Down (from top to bottom along the rightmost column)
            if (top <= bottom) { // Check if the top boundary hasn't crossed the bottom
                for (int r = top; r <= bottom; r++) {
                    result.add(matrix[r][right]);
                }
                right--; // Move the right boundary left
            }

            // 3. Traverse Left (from right to left along the bottom row)
            if (top <= bottom && left <= right) { // Check if boundaries haven't crossed
                for (int c = right; c >= left; c--) {
                    result.add(matrix[bottom][c]);
                }
                bottom--; // Move the bottom boundary up
            }

            // 4. Traverse Up (from bottom to top along the leftmost column)
            if (top <= bottom && left <= right) { // Check if boundaries haven't crossed
                for (int r = bottom; r >= top; r--) {
                    result.add(matrix[r][left]);
                }
                left++; // Move the left boundary right
            }
        }

        return result;
    }
}