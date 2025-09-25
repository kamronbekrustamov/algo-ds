from typing import List

class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        # This list will store the final merged intervals.
        result = []
        
        # Iterate through each interval in the input list.
        for i, current_interval in enumerate(intervals):
            # Case 1: The new interval is completely after the current interval.
            # This means the current interval does not overlap and comes before the new one.
            if newInterval[0] > current_interval[1]:
                result.append(current_interval)
            
            # Case 2: The new interval is completely before the current interval.
            # This means we have found the correct insertion point.
            elif newInterval[1] < current_interval[0]:
                # Add the (potentially merged) new interval.
                result.append(newInterval)
                # Then, add all the remaining intervals from the original list,
                # as they are all guaranteed to be after the new interval.
                return result + intervals[i:]
            
            # Case 3: There is an overlap between the new interval and the current one.
            # We merge them by updating the new interval to encompass the current one.
            else:
                newInterval[0] = min(newInterval[0], current_interval[0])
                newInterval[1] = max(newInterval[1], current_interval[1])

        # After the loop, the (potentially merged) new interval still needs to be added.
        # This handles cases where the new interval is inserted at the end of the list
        # or the input `intervals` list was empty.
        result.append(newInterval)
        
        return result
