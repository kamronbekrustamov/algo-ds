from typing import List

class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        result = [] # This will store all valid combinations.
        
        # The backtracking function to generate combinations.
        # 'start' is the number from which we start considering elements for the current combination.
        # 'current_combination' is the list holding the combination being built.
        def backtrack(start, current_combination):
            # Base case: if the current combination has 'k' elements, it's a valid combination.
            if len(current_combination) == k:
                result.append(current_combination[:]) # Add a copy of the combination to the result.
                return

            # Optimization: Prune the search space.
            # If the remaining elements (n - start + 1) plus the elements already in
            # current_combination (len(current_combination)) are less than k,
            # then it's impossible to form a combination of size k.
            # This check helps to avoid unnecessary recursive calls.
            if len(current_combination) + (n - start + 1) < k:
                return

            # Iterate from 'start' to 'n' to choose the next element.
            for i in range(start, n + 1):
                current_combination.append(i) # Include the current number 'i'.
                backtrack(i + 1, current_combination) # Recurse with the next number.
                current_combination.pop() # Backtrack: remove the last added number to explore other possibilities.

        # Start the backtracking process from number 1 with an empty combination.
        backtrack(1, [])
        return result
            
            
        