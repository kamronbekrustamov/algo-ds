class Solution {
    /**
     * Calculates the minimum initial health required for the knight to rescue the princess.
     * This is solved using dynamic programming, working backward from the princess's location.
     *
     * The DP state `dp[i][j]` represents the minimum health the knight must have upon
     * entering cell (i, j) to survive and reach the princess. The input `dungeon`
     * array is modified in-place to serve as the DP table.
     *
     * Algorithm:
     * 1. Get the dimensions of the dungeon: `m` rows and `n` columns.
     * 2. Iterate through the grid from the bottom-right corner (princess's location) to the top-left (start).
     *    (i from `m-1` down to `0`, j from `n-1` down to `0`).
     * 3. For each cell `(i, j)`:
     *    a. Determine `min_health_on_exit`, which is the minimum health required upon exiting
     *       the current cell to successfully reach the princess. This is derived from the
     *       DP values of the adjacent cells (down `(i+1, j)` or right `(i, j+1)`).
     *       - If at the princess's cell `(m-1, n-1)`, `min_health_on_exit` is 1 (must survive).
     *       - If on the last row `(i == m-1)`, the only path is right, so `min_health_on_exit = dungeon[i][j+1]`.
     *       - If on the last column `(j == n-1)`, the only path is down, so `min_health_on_exit = dungeon[i+1][j]`.
     *       - Otherwise, `min_health_on_exit = min(dungeon[i+1][j], dungeon[i][j+1])`.
     *    b. Calculate `health_needed` for the current cell `(i, j)`:
     *       `health_needed = max(1, min_health_on_exit - dungeon[i][j])`.
     *       This ensures that after accounting for the current cell's value, the health
     *       is at least `min_health_on_exit`, and also never drops below 1.
     *    c. Store `health_needed` in `dungeon[i][j]`, effectively updating the DP table.
     * 4. The value at `dungeon[0][0]` will be the minimum initial health required to start the journey.
     *
     * @param dungeon The 2D array representing the dungeon grid.
     * @return The minimum initial health required.
     *
     * Time Complexity: O(M * N), where M is the number of rows and N is the number of columns, as we iterate through the grid once.
     * Space Complexity: O(1), as we modify the input `dungeon` array in-place.
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minHealthOnExit;
                if (i == m - 1 && j == n - 1) {
                    minHealthOnExit = 1;
                } else if (i == m - 1) {
                    minHealthOnExit = dungeon[i][j + 1];
                } else if (j == n - 1) {
                    minHealthOnExit = dungeon[i + 1][j];
                } else {
                    minHealthOnExit = Math.min(dungeon[i + 1][j], dungeon[i][j + 1]);
                }
                int healthNeeded = Math.max(1, minHealthOnExit - dungeon[i][j]);
                dungeon[i][j] = healthNeeded;
            }
        }
        return dungeon[0][0];
    }
}
