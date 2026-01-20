import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (newInterval[0] > currentInterval[1]) {
                result.add(currentInterval);
            }
            else if (newInterval[1] < currentInterval[0]) {
                result.add(newInterval);
                for (int j = i; j < intervals.length; j++) {
                    result.add(intervals[j]);
                }
                return result.stream().toArray(int[][]::new);
            }
            else {
                newInterval[0] = Math.min(newInterval[0], currentInterval[0]);
                newInterval[1] = Math.max(newInterval[1], currentInterval[1]);
            }
        }
        result.add(newInterval);
        return result.stream().toArray(int[][]::new);
    }
}