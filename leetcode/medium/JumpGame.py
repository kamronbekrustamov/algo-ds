from typing import List


class Solution:
    def canJump(self, nums: List[int]) -> bool:
        """
        Determines if the last index can be reached using a greedy approach
        by working backward from the goal.
        """
        # The goal is to reach the last index.
        goal = len(nums) - 1

        # Iterate from the second-to-last index down to the start.
        for i in range(len(nums) - 2, -1, -1):
            # If we can jump from the current index 'i' to or beyond the 'goal',
            # it means this index 'i' is a new, closer "good" position.
            # We can now update our goal to be this index.
            if i + nums[i] >= goal:
                goal = i

        # After the loop, if the goal has been moved to index 0,
        # it means the starting position can successfully reach the end.
        return goal == 0