import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int largestRectangleArea(int[] heights) {
        // Stack holds {index, height} pairs, maintained in increasing height order
        Deque<int[]> stack = new ArrayDeque<>();
        int max = 0;

        for (int i = 0; i < heights.length; i++) {
            int curr = heights[i];
            // Current bar is shorter than the bar on top of the stack:
            // that means the stack-top bar just found its right boundary (index i).
            while (!stack.isEmpty() && curr < stack.peek()[1]) {
                int topHeight = stack.pop()[1];
                // After popping, the new top (if any) is the nearest bar to the
                // left that's shorter than topHeight — its left boundary.
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek()[0];
                // Width = distance strictly between the two boundaries.
                max = Math.max(max, topHeight * (i - leftBoundary - 1));
            }
            stack.push(new int[] {i, curr});
        }

        // Drain remaining bars: none of them found a shorter bar to their right,
        // so their right boundary is effectively heights.length.
        while (!stack.isEmpty()) {
            int topHeight = stack.pop()[1];
            int leftBoundary = stack.isEmpty() ? -1 : stack.peek()[0];
            max = Math.max(max, topHeight * (heights.length - leftBoundary - 1));
        }
        return max;
    }
}