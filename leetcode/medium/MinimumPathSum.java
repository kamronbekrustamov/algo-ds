/**
 * Solution for the Minimum Path Sum problem.
 * This class uses Dynamic Programming to find the path with the minimum sum
 * from the top-left corner to the bottom-right corner of a grid,
 * where movement is restricted to only down or right.
 * The solution modifies the input grid in-place to store the minimum path sums,
 * achieving O(1) extra space complexity.
 */
class Solution {
    /**
     * Finds the minimum path sum from the top-left (0, 0) to the bottom-right (m-1, n-1)
     * of the grid.
     *
     * @param grid The m x n grid containing non-negative integers. It is modified in-place.
     * @return The minimum path sum. Returns 0 if the grid is null or empty.
     */
    public int minPathSum(int[][] grid) {
        // 1. Handle edge case: null or empty grid
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // The core idea is Dynamic Programming:
        // grid[i][j] will store the minimum path sum from (i, j) to the bottom-right.
        // DP relation: grid[i][j] = grid[i][j] + min(grid[i+1][j], grid[i][j+1])
        // We compute this from bottom-right towards top-left.

        // 2. Initialize the bottom-right cell (Base Case) - No change needed here, it's the start/end point.

        // 3. Initialize the last column (m-2 to 0)
        // grid[i][n-1] can only come from grid[i+1][n-1]
        for (int i = m - 2; i >= 0; i--) {
            grid[i][n - 1] += grid[i + 1][n - 1];
        }

        // 4. Initialize the last row (n-2 to 0)
        // grid[m-1][j] can only come from grid[m-1][j+1]
        for (int j = n - 2; j >= 0; j--) {
            grid[m - 1][j] += grid[m - 1][j + 1];
        }

        // 5. Fill the rest of the grid (m-2, n-2 to 0, 0)
        // Apply the general DP relation: grid[i][j] = current_value + min(down_path, right_path)
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                grid[i][j] += Math.min(grid[i + 1][j], grid[i][j + 1]);
            }
        }

        // 6. The minimum path sum from (0, 0) to (m-1, n-1) is now stored in grid[0][0]
        return grid[0][0];
    }
}