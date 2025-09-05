from typing import List


class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        # The triangle inequality theorem states that for a triangle with side lengths
        # a, b, and c, the sum of any two sides must be greater than the third.
        # (a + b > c, a + c > b, b + c > a).
        # By sorting the array, if we ensure a <= b <= c, we only need to check
        # the condition a + b > c, as the other two will be implicitly true.
        nums.sort()

        count = 0
        # We iterate from the end of the array, fixing the longest side 'c' (nums[i]).
        # The loop goes down to index 2 because we need at least two other numbers (a, b)
        # to form a triangle.
        for i in range(len(nums) - 1, 1, -1):
            # For each fixed 'c' (nums[i]), we use two pointers to find pairs (a, b)
            # in the subarray nums[0...i-1].
            left = 0
            right = i - 1
            while left < right:
                # Check if the pair (nums[left], nums[right]) can form a triangle with nums[i].
                if nums[left] + nums[right] > nums[i]:
                    # If nums[left] + nums[right] > nums[i], it means that nums[right]
                    # combined with any element from nums[left] to nums[right-1] will also
                    # form a valid triangle. There are `right - left` such elements.
                    count += right - left
                    # We've found all valid pairs for the current `nums[right]`, so we
                    # move `right` to the left to try a smaller second side.
                    right -= 1
                else:
                    # The sum is too small, so we need a larger first side.
                    # We move `left` to the right to increase the sum.
                    left += 1
        return count