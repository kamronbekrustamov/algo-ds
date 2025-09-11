from typing import List


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # Count the frequency of each number.
        frequency = {}
        for num in nums:
            frequency[num] = frequency.get(num, 0) + 1
        
        # Sort the numbers by frequency in descending order
        sorted_numbers = sorted(frequency.keys(), key=lambda x: frequency[x], reverse=True)
        
        # Return the top k frequent numbers
        return sorted_numbers[:k]

