import java.util.Arrays;

/**
 * Solves the Unique Paths problem using Space-Optimized Dynamic Programming.
 */
class Solution {

    /**
     * Calculates the number of unique paths from the top-left to bottom-right corner.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The total number of unique paths.
     */
    public int uniquePaths(int m, int n) {
        // Base case: If the grid is 1x1 or invalid, return appropriate value (usually 1 or 0)
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 || n == 1) return 1;

        // Optimization: Ensure we always allocate the smaller dimension to save space.
        // If rows (m) are fewer than cols (n), we swap them.
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }

        // dp[j] represents the number of paths to reach the cell at current row and column j.
        int[] dp = new int[n];
        
        // Initialize the first row (or column technically, due to swap).
        // There is always exactly 1 way to reach any cell in the first row (move right only).
        Arrays.fill(dp, 1);

        // Iterate starting from the second row
        for (int i = 1; i < m; i++) {
            // Iterate through the columns
            for (int j = 1; j < n; j++) {
                // The number of paths to current cell (i, j) is the sum of:
                // 1. Paths from the cell above (currently stored in dp[j])
                // 2. Paths from the cell to the left (stored in dp[j-1])
                dp[j] = dp[j] + dp[j - 1];
            }
        }

        // The last element contains the paths to the bottom-right corner
        return dp[n - 1];
    }
}