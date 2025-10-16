from typing import List

class Solution:
    """
    Given a binary array nums, return the maximum number of consecutive 1's in the array.
    This solution uses a single pass, which is the most efficient approach possible, 
    with O(n) time complexity and O(1) space complexity.
    """
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        """
        Finds the maximum count of consecutive 1's in the input list.

        Args:
            nums: A list of integers (binary array containing only 0s and 1s).

        Returns:
            The maximum number of consecutive 1's found.
        """
        # max_c tracks the overall maximum count of consecutive ones found so far.
        max_c = 0
        
        # curr_c tracks the current count of consecutive ones in the ongoing sequence.
        curr_c = 0
        
        # Iterate through each element in the array.
        for num in nums:
            if num == 1:
                # If the element is 1, increment the current consecutive count.
                curr_c += 1
            else:
                # If the element is 0, the sequence of 1's is broken.
                
                # 1. Update the maximum count if the current count is larger.
                max_c = max(max_c, curr_c)
                
                # 2. Reset the current count to 0 for the new sequence.
                curr_c = 0
        
        # After the loop finishes, one last check is needed. 
        # This handles the case where the array ends with a sequence of 1's 
        # (e.g., [1,1,0,1,1,1]), where max_c wouldn't have been updated 
        # with the final curr_c inside the loop.
        return max(max_c, curr_c)