from typing import List


class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        """
        Finds all unique combinations in `candidates` where the numbers sum to `target`.
        The same number may be chosen from `candidates` an unlimited number of times.

        This is solved using a standard backtracking algorithm.
        """
        result = []
        
        # Sorting the candidates allows for an important pruning optimization.
        candidates.sort()

        def backtrack(remaining_sum: int, combination: List[int], start_index: int):
            """
            Recursively builds combinations.
            - remaining_sum: The target sum we still need to reach.
            - combination: The current list of numbers being built.
            - start_index: The index in `candidates` to start searching from. This
                           prevents duplicate combinations.
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
                
                combination.append(candidate)
                # Recurse. We can reuse the same element, so the next call also starts from `i`.
                backtrack(remaining_sum - candidate, combination, i)
                # Backtrack: remove the last element to explore other possibilities.
                combination.pop()

        backtrack(target, [], 0)
        return result
