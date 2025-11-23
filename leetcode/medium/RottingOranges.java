import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    /**
     * Finds the minimum number of minutes until all fresh oranges are rotten.
     * The rot spreads to adjacent fresh oranges every minute.
     * * @param grid The grid representing the state: 0 (empty), 1 (fresh), 2 (rotten).
     * @return The minimum number of minutes, or -1 if some fresh oranges can never rot.
     */
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        // Queue stores the coordinates of rotten oranges: [row, col]
        Queue<int[]> queue = new ArrayDeque<>();
        int freshOranges = 0;

        // 1. Initialize the queue with all initially rotten oranges (2) 
        //    and count the total number of fresh oranges (1).
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshOranges++;
                }
            }
        }

        // If there are no fresh oranges to begin with, the time is 0.
        if (freshOranges == 0) {
            return 0;
        }

        // Stores the elapsed time (number of minutes).
        int minutes = 0;

        // The four possible directions: up, down, left, right.
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 2. Perform BFS. The queue size at the start of the loop represents 
        //    all oranges that will rot a *new* set of fresh oranges in the current minute.
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean newlyRotten = false; // Flag to track if any fresh orange rotted in this minute

            for (int i = 0; i < size; i++) {
                int[] rotten = queue.poll();
                int r = rotten[0];
                int c = rotten[1];

                // Check the four neighbors
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Check boundaries and if the neighbor is a fresh orange (1)
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        // The orange rots
                        grid[nr][nc] = 2;
                        queue.offer(new int[]{nr, nc});
                        freshOranges--;
                        newlyRotten = true;
                    }
                }
            }
            
            // Increment the minute count only if rot successfully spread in this level.
            // This prevents counting an extra minute at the very end when the queue is populated
            // but all fresh oranges have already rotted.
            if (newlyRotten) {
                minutes++;
            }
        }

        // 3. Final Check
        // If freshOranges is 0, all oranges rotted, return the total time.
        // Otherwise, some fresh oranges are unreachable, return -1.
        return freshOranges == 0 ? minutes : -1;
    }
}