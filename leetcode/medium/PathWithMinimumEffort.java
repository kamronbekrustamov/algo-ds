import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solution for finding the Minimum Effort Path in a grid.
 * * Problem: Find a path from the top-left to bottom-right such that the maximum 
 * absolute difference in height between consecutive cells is minimized.
 */
class Solution {

    /**
     * A lightweight helper class to store grid coordinates and the effort 
     * required to reach them.
     */
    private static class Cell {
        int row;
        int col;
        int effort;

        Cell(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
    }

    /**
     * Calculates the minimum effort path using Dijkstra's Algorithm.
     * * @param heights A 2D grid representing cell heights.
     * @return The minimum effort required to travel from (0,0) to (rows-1, cols-1).
     */
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // DP/Distance array to store the minimum effort to reach each cell.
        // Initialize with Infinity to represent unvisited/unreachable.
        int[][] minEfforts = new int[rows][cols];
        for (int[] row : minEfforts) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Priority Queue acting as the min-heap for Dijkstra's.
        // Orders cells by their 'effort' in ascending order.
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.effort));

        // Start at (0,0) with 0 effort
        minEfforts[0][0] = 0;
        pq.offer(new Cell(0, 0, 0));

        // Directions array for moving: Right, Down, Left, Up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            Cell current = pq.poll();

            // Optimization: If we found a shorter path to this cell closer to the start
            // and processed it already, skip this stale entry.
            if (current.effort > minEfforts[current.row][current.col]) {
                continue;
            }

            // If we reached the bottom-right corner, return the effort immediately.
            // Because we are using a Min-Heap, the first time we reach the target
            // is guaranteed to be the optimal path.
            if (current.row == rows - 1 && current.col == cols - 1) {
                return current.effort;
            }

            // Explore neighbors
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                // Boundary check
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    
                    // Calculate the absolute difference (effort) to step to the neighbor
                    int diff = Math.abs(heights[current.row][current.col] - heights[newRow][newCol]);
                    
                    // The effort of a path is the MAX difference encountered along that path
                    int newEffort = Math.max(current.effort, diff);

                    // Relaxation: If this new path offers a lower max-effort than previously known
                    if (newEffort < minEfforts[newRow][newCol]) {
                        minEfforts[newRow][newCol] = newEffort;
                        pq.offer(new Cell(newRow, newCol, newEffort));
                    }
                }
            }
        }
        return 0; // Should be unreachable code given constraints
    }
}