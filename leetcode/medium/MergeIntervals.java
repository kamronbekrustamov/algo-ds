import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    /**
     * Merges all overlapping intervals.
     *
     * @param intervals An array of intervals where intervals[i] = [start, end].
     * @return An array of non-overlapping intervals that cover all the intervals in the input.
     */
    public int[][] merge(int[][] intervals) {
        // Edge case: Empty or single interval requires no merging.
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Step 1: Sort intervals by their start time.
        // Time Complexity: O(N log N)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        
        // Initialize with the first interval
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        // Step 2: Iterate through the remaining intervals
        // Time Complexity: O(N)
        for (int[] nextInterval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = nextInterval[0];
            int nextEnd = nextInterval[1];

            // Overlap condition: The next interval starts before (or when) the current one ends.
            if (nextStart <= currentEnd) {
                // Merge: Extend the current end to the max of both ends.
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No Overlap: Move to the next interval and add it to our list.
                currentInterval = nextInterval;
                merged.add(currentInterval);
            }
        }

        // Step 3: Convert List to Array
        return merged.toArray(new int[merged.size()][]);
    }
}