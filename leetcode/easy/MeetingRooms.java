import java.util.Collections;
import java.util.Comparator;

/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

/**
 * Solution for LeetCode problem "Meeting Rooms".
 * This class provides a method to determine if a person can attend all given meetings.
 */
class Solution {

    /**
     * Determines if a person can attend all given meetings without any overlaps.
     *
     * The approach is as follows:
     * 1. Sort the intervals based on their start times.
     * 2. Iterate through the sorted intervals and check if any meeting's start time
     *    is before the previous meeting's end time. If such an overlap is found,
     *    it's impossible to attend all meetings, so return false.
     * 3. If no overlaps are found after checking all intervals, return true.
     *
     * @param intervals A list of Interval objects, where each interval represents a meeting's start and end time.
     * @return true if the person can attend all meetings, false otherwise.
     */
    public boolean canAttendMeetings(List<Interval> intervals) {

        // Handle edge case: if there are no intervals or only one interval, no conflicts are possible.
        if (intervals == null || intervals.size() == 0) {
            return true;
        }

        // Sort the intervals by their start times.
        // This is crucial because we need to check for overlaps in chronological order.
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        // Iterate through the sorted intervals, starting from the second interval.
        // Compare each interval with its preceding one.
        for (int i = 1; i < intervals.size(); i++) {
            // If the current meeting starts before the previous meeting ends,
            // there is an overlap, and it's impossible to attend all meetings.
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }

        // If no overlaps were found after checking all intervals, all meetings can be attended.
        return true;
    }
}