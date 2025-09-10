from typing import List


class Solution:
    def jump(self, nums: List[int]) -> int:
        """
        Calculates the minimum number of jumps to reach the last index.
        This is solved using a single-pass greedy algorithm.
        """
        n = len(nums)
        if n <= 1:
            return 0

        # `jumps` counts the number of jumps we've made.
        jumps = 0
        # `current_reach` is the farthest index we can get to with the current number of jumps.
        current_reach = 0
        # `farthest_reach` is the maximum index we can reach from any position within the current jump's range.
        farthest_reach = 0

        # We iterate up to n-1 because we don't need to jump from the last element.
        for i in range(n - 1):
            # Update the farthest possible reach from the current position.
            farthest_reach = max(farthest_reach, i + nums[i])

            # If we've reached the end of the current jump's range...
            if i == current_reach:
                # ...we must take another jump.
                jumps += 1
                # The new range is defined by the farthest reach we've found so far.
                current_reach = farthest_reach

        return jumps
