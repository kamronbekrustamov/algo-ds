/**
 * Solution for counting the number of islands in a 2D binary grid.
 * An island is a group of '1's (land) connected horizontally or vertically.
 * The solution uses the Depth-First Search (DFS) algorithm.
 */
class Solution {
    // Array defining the four possible movements (right, down, left, up).
    // Used to explore adjacent cells during DFS.
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * Counts the number of distinct islands in the grid.
     *
     * @param grid The 2D array of characters ('1' for land, '0' for water).
     * @return The total number of islands.
     */
    public int numIslands(char[][] grid) {
        // Handle edge cases: null or empty grid
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int islandsCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Iterate through every cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If a '1' (land) is found, it marks the start of a new, unvisited island.
                if (grid[r][c] == '1') {
                    islandsCount++;
                    // Use DFS to mark all connected '1's as visited ('0').
                    dfs(grid, r, c);
                }
            }
        }
        return islandsCount;
    }

    /**
     * Helper function to perform Depth-First Search (DFS).
     * It recursively visits all connected land cells ('1') starting from (row, col)
     * and marks them as visited by changing their value to '0'.
     *
     * @param grid The mutable 2D grid.
     * @param r The current row index.
     * @param c The current column index.
     */
    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Base case: check boundary conditions or if the cell is water ('0').
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
            return;
        }

        // Mark the current cell as visited (converting '1' to '0')
        // to prevent recounting and infinite recursion.
        grid[r][c] = '0';

        // Recursively visit all four neighboring cells (up, down, left, right).
        for (int[] direction: DIRECTIONS) {
            dfs(grid, r + direction[0], c + direction[1]);
        }
    }
}