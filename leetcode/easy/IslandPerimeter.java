/**
 * Solution for LeetCode problem "Island Perimeter".
 * This class provides a method to calculate the perimeter of an island in a grid.
 * The grid is composed of 0s (water) and 1s (land).
 * There is exactly one island (or the land cells form one connected component).
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
 */
class Solution {

    /**
     * Calculates the perimeter of the island described by the grid.
     * The grid represents a map where 1s are land and 0s are water.
     * Each cell is a square with side length 1.
     *
     * The algorithm iterates through each cell of the grid.
     * If a cell is land (1), it initially contributes 4 to the perimeter.
     * Then, it checks its top and left neighbors.
     * If a neighbor is also land, it means there's a shared edge.
     * A shared edge reduces the total perimeter by 2 (one side from the current cell, one from the neighbor).
     * By only checking top and left neighbors, we avoid double-counting shared edges
     * (e.g., when processing cell (r, c), its right neighbor (r, c+1) will check its left neighbor (r, c)).
     *
     * @param grid A 2D integer array representing the grid. 1 for land, 0 for water.
     * @return The total perimeter of the island.
     */
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;

        // Iterate over each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If the current cell is water, it doesn't contribute to the perimeter
                if (grid[r][c] == 0) {
                    continue;
                }

                // If the current cell is land, it initially contributes 4 to the perimeter
                perimeter += 4;

                // Check the top neighbor
                // If the cell above is also land, subtract 2 from the perimeter (shared edge)
                if (r > 0 && grid[r - 1][c] == 1) {
                    perimeter -= 2;
                }

                // Check the left neighbor
                // If the cell to the left is also land, subtract 2 from the perimeter (shared edge)
                if (c > 0 && grid[r][c - 1] == 1) {
                    perimeter -= 2;
                }
            }
        }
        return perimeter;
    }
}

