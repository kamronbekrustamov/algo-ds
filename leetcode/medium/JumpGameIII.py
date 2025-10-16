from collections import deque
from typing import List

class Solution:
    """
    Determines if an index with a value of 0 is reachable from the starting index
    in the array, using the jump rules.
    
    This solution uses Breadth-First Search (BFS), which is the optimal approach
    for reachability problems in unweighted graphs.
    """
    def canReach(self, arr: List[int], start: int) -> bool:
        n = len(arr)
        
        # 1. Initialization
        # Use a set for 'visited' for potentially faster O(1) lookups/insertions 
        # (though a boolean list is also O(1) and often slightly faster for fixed size N).
        visited = set() 
        queue = deque([start])
        visited.add(start)

        # 2. BFS Traversal
        while queue:
            current_index = queue.popleft()
            
            # Check for the goal condition immediately upon dequeuing
            if arr[current_index] == 0:
                return True
            
            jump_value = arr[current_index]
            
            # Calculate the two possible next positions
            next_positions = [
                current_index - jump_value,  # Jump backward
                current_index + jump_value   # Jump forward
            ]
            
            # 3. Explore Neighbors
            for next_pos in next_positions:
                # Check boundaries and ensure the index hasn't been visited
                if 0 <= next_pos < n and next_pos not in visited:
                    visited.add(next_pos)
                    queue.append(next_pos)
                    
        # If the queue is empty and the target (arr[i] == 0) was not found
        return False