class Solution {
    /**
     * Calculates the number of unique paths from the top-left corner to the 
     * bottom-right corner of a grid with obstacles.
     * * Movement is restricted to only 'down' or 'right'.
     * * @param obstacleGrid The grid representing the maze. 0 is an empty space, 1 is an obstacle.
     * @return The number of unique paths.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // --- Step 1: Handle the starting cell (Base Case) ---
        // If the starting cell is an obstacle, no paths exist.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        // Set the starting cell as the base case: 1 way to reach it (by staying put).
        obstacleGrid[0][0] = 1;

        // --- Step 2: Initialize the first column ---
        // Fill the rest of the first column: A cell can be reached only if the cell 
        // immediately above it is reachable.
        for (int i = 1; i < R; i++) {
            // Check if the current cell is an obstacle OR if the cell above it is blocked (value 0)
            if (obstacleGrid[i][0] == 1 || obstacleGrid[i - 1][0] == 0) {
                obstacleGrid[i][0] = 0; // Set paths to 0 if it's an obstacle or blocked from above
            } else {
                obstacleGrid[i][0] = 1; // Otherwise, there's 1 path through the top cell
            }
        }

        // --- Step 3: Initialize the first row ---
        // Fill the rest of the first row: A cell can be reached only if the cell 
        // immediately to the left of it is reachable.
        for (int j = 1; j < C; j++) {
            // Check if the current cell is an obstacle OR if the cell to the left of it is blocked
            if (obstacleGrid[0][j] == 1 || obstacleGrid[0][j - 1] == 0) {
                obstacleGrid[0][j] = 0; // Set paths to 0 if it's an obstacle or blocked from the left
            } else {
                obstacleGrid[0][j] = 1; // Otherwise, there's 1 path through the left cell
            }
        }

        // --- Step 4: Fill the rest of the grid (Main DP Recurrence) ---
        // The value at obstacleGrid[i][j] now represents DP[i][j]: 
        // number of unique paths to reach (i, j).
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 1) {
                    // Obstacle: No paths can go through this cell.
                    obstacleGrid[i][j] = 0;
                } else {
                    // Recurrence relation: DP[i][j] = DP[i-1][j] + DP[i][j-1]
                    // Sum of paths from the cell above and the cell to the left.
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }

        // --- Step 5: Return the result ---
        // The bottom-right cell holds the total number of unique paths.
        return obstacleGrid[R - 1][C - 1];
    }
}