from typing import List


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        """
        Generates all possible subsets (the power set) from a list of unique integers.
        This is solved using a standard backtracking algorithm.
        """
        result = []
        current_subset = []

        def backtrack(start_index: int):
            """
            Recursively builds subsets. `start_index` is used to avoid duplicate combinations.
            """
            # First, add a copy of the current subset to the result.
            # This captures the subset at its current state (e.g., [], [1], [1, 2], etc.).
            result.append(list(current_subset))

            # Explore adding new elements to the current subset.
            for i in range(start_index, len(nums)):
                # Include the element nums[i].
                current_subset.append(nums[i])
                # Recurse with the next starting index to build upon the new subset.
                backtrack(i + 1)
                # Backtrack: remove the element to explore subsets without it.
                current_subset.pop()

        backtrack(0)
        return result