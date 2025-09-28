from typing import List

class Solution:
    def maxArea(self, heights: List[int]) -> int:
        """
        Finds two lines that together with the x-axis form a container,
        such that the container contains the most water.

        The problem is solved using the "two-pointer" approach.
        We start with two pointers, one at the beginning and one at the end of the array.
        The area formed by these two lines is calculated.
        Then, we move the pointer pointing to the shorter line inwards.
        This is because moving the taller line inwards will never increase the height
        (it might decrease or stay the same), but it will definitely decrease the width.
        Moving the shorter line inwards might find a taller line, potentially increasing the area.

        Args:
            heights: A list of non-negative integers representing the heights of the vertical lines.

        Returns:
            The maximum amount of water that can be contained.
        """
        
        # Initialize two pointers: 'left' at the beginning and 'right' at the end of the array.
        left, right = 0, len(heights) - 1
        
        # Initialize 'max_area' to store the maximum area found so far.
        max_area = 0
        
        # Continue as long as the left pointer is less than the right pointer.
        while left < right:
            # Calculate the current area: width * height.
            # Width is the distance between the two pointers (right - left).
            # Height is limited by the shorter of the two lines (min(heights[left], heights[right])).
            current_area = (right - left) * min(heights[left], heights[right])
            
            # Update max_area if the current_area is greater.
            max_area = max(max_area, current_area)
            
            # Move the pointer that points to the shorter line inwards.
            # This strategy is crucial because to potentially increase the area,
            # we need to find a taller line. Moving the shorter line gives us
            # a chance to find a taller line, while moving the taller line
            # would only decrease the width without guarantee of increasing height.
            if heights[left] > heights[right]:
                right -= 1
            else:
                left += 1
                
        return max_area