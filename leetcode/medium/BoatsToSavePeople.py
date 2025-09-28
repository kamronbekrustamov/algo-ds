from typing import List

class Solution:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        """
        Calculates the minimum number of boats required to carry all people,
        given that each boat can carry at most two people and has a weight limit.

        This problem is solved using a greedy approach with two pointers after sorting.
        The strategy is to always try to pair the heaviest person with the lightest person.

        Args:
            people: A list of integers representing the weight of each person.
            limit: The maximum weight a single boat can carry.

        Returns:
            The minimum number of boats required.
        """
        
        # Step 1: Sort the people by their weight.
        # This is crucial for the greedy two-pointer approach to work.
        # Time complexity: O(N log N)
        people.sort()
        
        # Initialize pointers: 'left' for the lightest person, 'right' for the heaviest person.
        left, right = 0, len(people) - 1
        
        # Initialize 'count' to keep track of the number of boats used.
        count = 0
        
        # Step 2: Use a two-pointer approach to pair people.
        # The loop continues as long as the left pointer is less than or equal to the right pointer.
        # Time complexity: O(N)
        while left <= right:
            # Increment boat count for each trip.
            count += 1
            
            # If the lightest person and the heaviest person can fit in the same boat,
            # they go together. Move the 'left' pointer to the next lightest person.
            if people[left] + people[right] <= limit:
                left += 1
            
            # The heaviest person always takes a boat (either alone or with the lightest).
            # Move the 'right' pointer to the next heaviest person.
            right -= 1
            
        return count

