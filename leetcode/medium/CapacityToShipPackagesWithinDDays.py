from typing import List

class Solution:
    """
    This class provides a solution for the "Capacity To Ship Packages Within D Days" problem.
    """
    def shipWithinDays(self, weights: List[int], days: int) -> int:
        """
        Calculates the minimum ship capacity required to ship all packages within a given number of days.

        The problem is solved using binary search on the ship's capacity. The capacity can range
        from the weight of the heaviest package to the sum of all package weights.

        Args:
            weights: A list of integers representing the weights of the packages.
            days: An integer representing the number of days within which to ship all packages.

        Returns:
            The minimum ship capacity required. Returns -1 if days is 0.
        """
        if days == 0:
            return -1

        # The lower bound for the binary search is the maximum weight of a single package,
        # as the ship must be able to carry at least the heaviest package.
        # The upper bound is the sum of all weights, which corresponds to shipping all packages in a single day.
        low, high = max(weights), sum(weights)

        # Binary search for the minimum capacity.
        while low < high:
            capacity = (low + high) // 2
            
            # Simulate the shipping process to find the number of days required for the current capacity.
            days_count = 1  # Start with the first day.
            capacity_left = capacity
            
            for weight in weights:
                if weight > capacity_left:
                    # If the current package exceeds the remaining capacity for the day,
                    # a new day is needed.
                    days_count += 1
                    capacity_left = capacity
                capacity_left -= weight

            # Adjust the search range based on the number of days required.
            if days_count > days:
                # If the required days are more than the allowed days, the capacity is too small.
                # Increase the capacity by setting the lower bound to capacity + 1.
                low = capacity + 1
            else:
                # If the required days are within the allowed limit, this capacity is feasible.
                # Try to find a smaller capacity by setting the upper bound to the current capacity.
                high = capacity
        
        # The loop terminates when low == high, which is the minimum capacity found.
        return low