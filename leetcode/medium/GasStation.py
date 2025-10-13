from typing import List

class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        """
        Finds the starting gas station's index if you can travel around the circuit
        once in the clockwise direction, otherwise returns -1.

        This problem can be solved using a greedy approach in a single pass (O(n)).
        The logic relies on two key insights:

        1. If the total amount of gas available is less than the total cost to travel
           the entire circuit, it's impossible to complete the trip. We can check
           this first. `sum(gas) < sum(cost)`.

        2. If we start at station `A` and run out of gas trying to get to station `C`
           (meaning our tank becomes negative at some station `B` between `A` and `C`),
           then no station from `A` to `B` can be a valid starting point. This is
           because if we started at any station between `A` and `B`, we would have
           arrived at `B` with even less gas than when we started at `A`, and would
           still fail. Therefore, the next potential starting point must be `B+1`.

        Combining these, we iterate through the stations, keeping track of a running
        `tank` total. If the tank drops below zero, we know our current starting
        point is invalid, and we set the next station as the new potential start.
        Because of insight #1, if a solution exists, this process is guaranteed to find it.

        Args:
            gas: A list of integers where gas[i] is the amount of gas at station i.
            cost: A list of integers where cost[i] is the cost to travel from
                  station i to i+1.

        Returns:
            The index of the starting gas station, or -1 if no solution exists.
        """
        # Insight 1: Check if a solution is possible at all.
        if sum(gas) < sum(cost):
            return -1

        # Insight 2: Find the starting point using a greedy approach.
        tank = 0
        start_station = 0

        for i in range(len(gas)):
            # The net change in gas after visiting station `i` and traveling to `i+1`.
            tank += gas[i] - cost[i]

            # If the tank becomes negative, we can't reach station `i+1` from our
            # current `start_station`.
            if tank < 0:
                # Based on insight #2, none of the stations from `start_station`
                # to `i` can be the answer. So, the next potential start is `i+1`.
                start_station = i + 1
                # Reset the tank for the new journey starting from `i+1`.
                tank = 0
        
        # If the loop completes, the `start_station` found is the correct one.
        # This is guaranteed because we already know a solution exists (from insight #1).
        return start_station
