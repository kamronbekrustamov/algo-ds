from typing import List

class Solution:
    """
    Finds the length of the longest contiguous subarray containing only 1s 
    after flipping at most k 0s to 1s.

    This uses the Sliding Window technique.
    """
    def longestOnes(self, nums: List[int], k: int) -> int:
        """
        :param nums: A list of 0s and 1s.
        :param k: The maximum number of 0s that can be flipped to 1s.
        :return: The length of the longest subarray with at most k zeros.
        """
        # left: The left pointer of the sliding window.
        left = 0
        # zero_count: Tracks the number of zeros currently within the window [left, right].
        zero_count = 0
        # max_length: Stores the maximum length of a valid window found so far.
        max_length = 0
        
        # 'right' implicitly serves as the right pointer of the window.
        for right in range(len(nums)):
            # 1. Expand the window to the right and update the zero count.
            if nums[right] == 0:
                zero_count += 1
            
            # 2. Shrink the window from the left if the zero count exceeds k.
            # The loop (or 'while' loop) continues to slide the left boundary 
            # until the window is valid again (zero_count <= k).
            while zero_count > k:
                # If the element leaving the window is a zero, decrement the count.
                if nums[left] == 0:
                    zero_count -= 1
                # Move the left boundary of the window one step to the right.
                left += 1
            
            # 3. Update the maximum length found for the current valid window.
            # The current valid window length is (right - left + 1).
            # This step is implicitly handled by the nature of the 'while' loop. 
            # If the window has expanded (right moved) and zero_count is still <= k, 
            # we've found a new potential maximum.
            # By tracking max_length here, we ensure we capture the largest valid window.
            max_length = max(max_length, right - left + 1)
            
        return max_length