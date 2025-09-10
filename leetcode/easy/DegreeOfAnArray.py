from typing import List

class Solution:
    def findShortestSubArray(self, nums: List[int]) -> int:
        """
        Finds the length of the shortest, contiguous subarray that has the same degree as nums.
        The degree of an array is the maximum frequency of any one of its elements.

        This solution uses a single pass to find the first occurrence, count, and shortest
        subarray length simultaneously.
        """
        # Dictionaries to store the first occurrence and frequency of each number.
        first_occurrence = {}
        counts = {}

        # Variables to track the degree and the length of the shortest subarray found so far.
        degree = 0
        min_length = 0

        for i, num in enumerate(nums):
            # Record the first time we see a number.
            first_occurrence.setdefault(num, i)

            # Increment the count for the current number.
            counts[num] = counts.get(num, 0) + 1

            # Check if this number has become the new mode (highest frequency).
            if counts[num] > degree:
                degree = counts[num]
                min_length = i - first_occurrence[num] + 1
            elif counts[num] == degree:
                # If it's a tie for the degree, check if this subarray is shorter.
                min_length = min(min_length, i - first_occurrence[num] + 1)

        return min_length
