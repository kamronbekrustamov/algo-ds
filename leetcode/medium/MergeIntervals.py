from typing import List

class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        """
        Merges all overlapping intervals in a given list of intervals.

        This solution uses a greedy approach combined with sorting.
        The key insight is that if the intervals are sorted by their start times,
        then any overlapping intervals must be adjacent or close to each other.

        Algorithm Breakdown:
        1. Handle edge cases: If there are fewer than two intervals, no merging is needed.
        2. Sort the input `intervals` list based on the start time of each interval.
           This step is crucial for the greedy approach to work correctly.
        3. Initialize an empty list `merged_intervals` to store the non-overlapping
           intervals.
        4. Iterate through each `current_interval` in the sorted `intervals` list:
           a. If `merged_intervals` is empty, or if the `current_interval` does not
              overlap with the `last_merged_interval` (i.e., `current_interval.start > last_merged_interval.end`),
              then add `current_interval` as a new, non-overlapping interval to `merged_intervals`.
           b. If the `current_interval` *does* overlap with the `last_merged_interval`
              (i.e., `current_interval.start <= last_merged_interval.end`), then merge
              them by updating the end time of the `last_merged_interval` to be the
              maximum of its current end time and the `current_interval.end` time.
        5. Return the `merged_intervals` list.

        Time Complexity: O(n log n), dominated by the sorting step.
                         The iteration through the sorted intervals is O(n).
        Space Complexity: O(n) for storing the sorted intervals (if a new list is
                          created by `sorted()`) and for the `merged_intervals` list.

        Args:
            intervals: A list of intervals, where each interval is a list of two
                       integers [start, end].

        Returns:
            A list of non-overlapping intervals that cover all the intervals
            in the input.
        """
        # 1. Handle edge cases: If there are fewer than two intervals, no merging is needed.
        if not intervals or len(intervals) < 2:
            return intervals

        # 2. Sort the intervals based on their start times.
        # This is crucial for the greedy approach to work.
        intervals.sort(key=lambda interval: interval[0])

        # 3. Initialize a list to store the merged intervals.
        merged_intervals = []

        # 4. Iterate through each interval in the sorted list.
        for current_interval in intervals:
            # Case A: `merged_intervals` is empty, or the current interval does not
            # overlap with the last merged interval.
            # This means the current interval starts after the last merged interval ends.
            if not merged_intervals or current_interval[0] > merged_intervals[-1][1]:
                merged_intervals.append(current_interval)
            # Case B: The current interval overlaps with the last merged interval.
            else:
                # Merge them by updating the end time of the last merged interval.
                # The new end time is the maximum of the current end and the new interval's end.
                merged_intervals[-1][1] = max(merged_intervals[-1][1], current_interval[1])
        
        # 5. Return the list of merged intervals.
        return merged_intervals
