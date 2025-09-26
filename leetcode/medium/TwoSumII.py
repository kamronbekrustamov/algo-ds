from typing import List

# Time: O(n)
# Space: O(1)
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        """
        Finds two numbers in a sorted array that add up to a specific target.

        This function uses the two-pointer technique, which is highly efficient for
        sorted arrays. One pointer starts at the beginning (left) and the other at
        the end (right) of the array.

        :param numbers: A 1-indexed sorted list of integers.
        :param target: The target sum.
        :return: A list containing the 1-based indices of the two numbers.
        """
        # Initialize two pointers, one at the start and one at the end of the array.
        left, right = 0, len(numbers) - 1

        # Loop until the two pointers meet.
        while left < right:
            # Calculate the sum of the values at the two pointers.
            current_sum = numbers[left] + numbers[right]

            if current_sum == target:
                # If the sum is the target, we found our pair.
                # The problem asks for 1-based indices, so we add 1 to each.
                return [left + 1, right + 1]
            elif current_sum > target:
                # If the sum is too large, we need to decrease it.
                # Since the array is sorted, we move the right pointer to a smaller value.
                right -= 1
            else: # current_sum < target
                # If the sum is too small, we need to increase it.
                # Since the array is sorted, we move the left pointer to a larger value.
                left += 1