from typing import List


class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        """
        Finds all unique combinations in `candidates` that sum up to `target`.
        Each number in `candidates` may only be used once in the combination.
        The solution set must not contain duplicate combinations.

        This is solved using a standard backtracking algorithm with optimizations
        to handle duplicates.
        """
        result = []
        
        # Sorting is crucial to handle duplicates and for pruning.
        candidates.sort()

        def backtrack(remaining_sum: int, combination: List[int], start_index: int):
            """
            Recursively builds combinations.
            - remaining_sum: The target sum we still need to reach.
            - combination: The current list of numbers being built.
            - start_index: The index in `candidates` to start searching from.
            """
            # Base case: A valid combination is found.
            if remaining_sum == 0:
                result.append(list(combination))
                return

            # Explore candidates starting from `start_index`.
            for i in range(start_index, len(candidates)):
                candidate = candidates[i]

                # Pruning: If the current candidate is greater than the remaining sum,
                # all subsequent candidates will also be too large (since the list is sorted).
                if candidate > remaining_sum:
                    break
                
                # *** Key optimization to skip duplicates ***
                # If the current candidate is the same as the previous one, and we are not
                # at the start of the loop for this level of recursion, skip it.
                # This ensures that we only consider the first occurrence of a duplicate
                # number at each level, thus avoiding duplicate combinations.
                if i > start_index and candidate == candidates[i - 1]:
                    continue
                
                combination.append(candidate)
                # Recurse. Move to the next index `i + 1` because each number can be used only once.
                backtrack(remaining_sum - candidate, combination, i + 1)
                # Backtrack: remove the last element to explore other possibilities.
                combination.pop()

        backtrack(target, [], 0)
        return result