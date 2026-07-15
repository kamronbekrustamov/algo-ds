import java.util.*;

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] outdegree = new int[rows][cols];
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        // outdegree[r][c] = number of neighbors with strictly greater value
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                            && matrix[nr][nc] > matrix[r][c]) {
                        outdegree[r][c]++;
                    }
                }
            }
        }

        // Start from local maxima (outdegree 0)
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (outdegree[r][c] == 0) queue.offer(new int[]{r, c});
            }
        }

        int length = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            length++;
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int r = cell[0], c = cell[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                            && matrix[nr][nc] < matrix[r][c]) {
                        // decrement neighbor's outdegree; if it hits 0, it's now a "leaf"
                        if (--outdegree[nr][nc] == 0) {
                            queue.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return length;
    }
}