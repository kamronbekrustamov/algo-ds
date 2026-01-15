from typing import List

class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        """
        Generates the Power Set (all possible subsets) of a given integer array.
        
        Complexity:
        - Time: O(n * 2^n) - There are 2^n subsets, and we iterate up to n times for each.
        - Space: O(n * 2^n) - To store all subsets in the result list.
        """
        n = len(nums)
        # The total number of subsets is 2^n
        subsets_count = 1 << n 
        result = []

        # Iterate through every number from 0 to 2^n - 1
        for i in range(subsets_count):
            current_subset = []
            # Check each bit position j
            for j in range(n):
                # If the j-th bit of 'i' is set, include nums[j]
                if (i >> j) & 1:
                    current_subset.append(nums[j])
            result.append(current_subset)

        return result