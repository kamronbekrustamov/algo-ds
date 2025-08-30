from typing import List


class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        """
        Finds the duplicate number using Floyd's Tortoise and Hare (cycle detection) algorithm.
        This approach uses O(1) extra space and runs in O(N) time.
        """
        # Phase 1: Find the intersection point in the cycle.
        # The slow pointer moves one step at a time, while the fast pointer moves two steps.
        slow = nums[0]
        fast = nums[0]
        
        while True:
            slow = nums[slow]
            fast = nums[nums[fast]]
            if slow == fast:
                break

        # Phase 2: Find the entrance of the cycle.
        # Reset one pointer to the start. The other stays at the intersection.
        # Move both pointers one step at a time. The point where they meet is the cycle's entrance.
        slow = nums[0]
        while slow != fast:
            slow = nums[slow]
            fast = nums[fast]
            
        return slow