from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        if not height:
            return 0

        left, right = 0, len(height) - 1
        max_left, max_right = height[left], height[right]
        total_water = 0

        while left < right:
            # The amount of water is limited by the shorter of the two max walls.
            if max_left < max_right:
                # The left wall is the bottleneck. Process the left pointer.
                left += 1
                # Update the max_left wall seen so far.
                max_left = max(max_left, height[left])
                # The trapped water at this position is the difference between the
                # containing wall (max_left) and the current bar's height.
                total_water += max_left - height[left]
            else:
                # The right wall is the bottleneck. Process the right pointer.
                right -= 1
                max_right = max(max_right, height[right])
                total_water += max_right - height[right]

        return total_water

