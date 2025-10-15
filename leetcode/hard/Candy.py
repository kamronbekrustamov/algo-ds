from typing import List

class Solution:
    """
    Solves the 'Candy' distribution problem using a greedy two-pass approach.
    This method guarantees the minimum total candies while satisfying two constraints:
    1. Every child gets at least one candy.
    2. A higher-rated child must get more candies than their neighbor(s).
    """
    def candy(self, ratings: List[int]) -> int:
        n = len(ratings)
        
        # Initialize the result array. Constraint 1: Every child gets at least 1 candy.
        # This array will store the minimum candies required for each child.
        result = [1] * n
        
        # --- First Pass: Left-to-Right Traversal ---
        # Purpose: Satisfy the rule for the LEFT neighbor.
        # If ratings[i] > ratings[i-1], the i-th child MUST have more candies 
        # than their left neighbor. We greedily set their candy count to be 
        # exactly 1 more than their neighbor to ensure minimality.
        for i in range(1, n):
            if ratings[i] > ratings[i - 1]:
                # Update: Candies are based solely on the left neighbor's requirement
                result[i] = result[i - 1] + 1
        
        # --- Second Pass: Right-to-Left Traversal ---
        # Purpose: Satisfy the rule for the RIGHT neighbor and merge with the left requirement.
        # We iterate backward to check the right neighbor condition.
        for i in range(n - 2, -1, -1):
            if ratings[i] > ratings[i + 1]:
                # Requirement based on the right neighbor: 1 + result[i + 1]
                
                # Crucial Step (The max()): 
                # The i-th child must satisfy *both* the left-neighbor requirement (already in result[i]) 
                # AND the right-neighbor requirement. We take the maximum of the two to satisfy both.
                # If ratings[i] > ratings[i+1], result[i] must be AT LEAST 1 + result[i+1].
                result[i] = max(result[i], result[i + 1] + 1)

        # The minimum total number of candies is the sum of the final, merged candy counts.
        return sum(result)