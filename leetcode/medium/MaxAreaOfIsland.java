/**
 * This class solves the "Max Area of Island" problem (LeetCode 695).
 * It finds the largest connected component (island) of '1's in a 2D grid,
 * where an island is formed by '1's connected 4-directionally (up, down, left, right).
 */
class Solution {

    /**
     * Main function to find the maximum area of an island in the given grid.
     *
     * It iterates through each cell of the grid. If a '1' (land) is found
     * that hasn't been visited, it triggers a Depth-First Search (DFS)
     * to explore the entire island connected to that cell and calculate its area.
     * The grid is modified in-place (visited '1's are set to '0') to prevent recounting.
     *
     * @param grid A 2D integer array where '1' represents land and '0' represents water.
     * @return The area (number of '1's) of the largest island. Returns 0 if
     * the grid is empty or no islands are present.
     */
    public int maxAreaOfIsland(int[][] grid) {
        // Robustness check for an empty or non-existent grid
        if (grid == null || grid.length == 0) {
            return 0;
        }

        // Cache grid dimensions for readability and efficiency
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        // Iterate through every cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If we find land ('1') that we haven't visited yet
                if (grid[r][c] == 1) {
                    // Start a DFS to find the area of this island
                    int currentIslandArea = dfs(grid, r, c, rows, cols);
                    // Update maxArea if this island is larger than the current max
                    maxArea = Math.max(maxArea, currentIslandArea);
                }
            }
        }

        return maxArea;
    }

    /**
     * A recursive Depth-First Search (DFS) helper function to calculate the
     * area of a single, connected island.
     *
     * This function explores all 4-directionally connected land ('1') cells
     * starting from the given (r, c) coordinate. It "sinks" visited land cells
     * by setting them to '0' to avoid cycles and redundant counting.
     *
     * @param grid The grid map (which is modified in-place).
     * @param r    The current row index to explore.
     * @param c    The current column index to explore.
     * @param rows The total number of rows in the grid (passed for efficiency).
     * @param cols The total number of columns in the grid (passed for efficiency).
     * @return The total area of the connected island starting from (r, c).
     * Returns 0 if the starting cell is invalid, out of bounds, or water ('0').
     */
    private int dfs(int[][] grid, int r, int c, int rows, int cols) {
        // --- Base Case (Stop condition) ---
        // Check for out-of-bounds (up, down, left, or right)
        // OR if the current cell is water or already visited ('0')
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 0) {
            return 0;
        }

        // --- Action (Visit) ---
        // Mark the current cell as visited by "sinking" it (setting to 0)
        // This is crucial to prevent infinite loops and recounting.
        grid[r][c] = 0;

        // --- Recursive Step (Explore) ---
        // The area of the current island is 1 (for this cell)
        // plus the area of all connected neighbors.
        return 1 + dfs(grid, r + 1, c, rows, cols)  // Explore Down
                 + dfs(grid, r - 1, c, rows, cols)  // Explore Up
                 + dfs(grid, r, c + 1, rows, cols)  // Explore Right
                 + dfs(grid, r, c - 1, rows, cols); // Explore Left
    }
}