from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # A hash map to store numbers we've seen and their indices.
        # Format: {number: index}
        seen_map = {}

        # Use enumerate for a more Pythonic way to get both index and value.
        for i, num in enumerate(nums):
            complement = target - num
            # Check if the complement exists in our map. This is faster and cleaner.
            if complement in seen_map:
                return [seen_map[complement], i]
            # If not found, add the current number and its index to the map.
            seen_map[num] = i