/**
 * Searches for a target value in an m x n matrix with the following properties:
 * 1. Each row is sorted in non-decreasing order.
 * 2. The first integer of each row is greater than the last integer of the previous row.
 *
 * The solution treats the matrix as a single, sorted 1D array and applies binary search.
 */
class Solution {
    /**
     * Determines if a target value exists in the sorted matrix.
     *
     * @param matrix The m x n matrix to search. It is assumed to be sorted
     * row-wise and that the first element of each row is greater
     * than the last element of the previous row.
     * @param target The integer value to search for.
     * @return true if the target is found, false otherwise.
     * @timeComplexity O(log(m * n)), where m is the number of rows and n is the number of columns.
     * This is due to the binary search on m * n elements.
     * @spaceComplexity O(1), as only a few extra variables are used.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // Handle the case of an empty matrix, though constraints usually prevent this.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // m = number of rows, n = number of columns
        int m = matrix.length;
        int n = matrix[0].length;

        // Binary search range (indices for the conceptual 1D array)
        // low = 0 (index of matrix[0][0])
        int low = 0;
        // high = m * n - 1 (index of matrix[m-1][n-1])
        int high = m * n - 1;

        // Standard binary search loop
        while (low <= high) {
            // Calculate the middle index for the conceptual 1D array
            int mid = low + (high - low) / 2;

            // Convert the 1D index (mid) back to 2D coordinates (row, col)
            // For an index 'i' in a 1D array of size 'n' columns per row:
            // row = i / n
            int row = mid / n;
            // col = i % n
            int col = mid % n;

            int midValue = matrix[row][col];

            // Compare the middle value with the target
            if (midValue > target) {
                // Target is smaller, search the left half
                high = mid - 1;
            } else if (midValue < target) {
                // Target is larger, search the right half
                low = mid + 1;
            } else {
                // Target found
                return true;
            }
        }

        // Target not found after exhausting the search space
        return false;
    }
}