from typing import List

class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        # 1. Determine the maximum location (destination) to size the timeline array.
        max_location = 0
        for _, _, to in trips:
            max_location = max(max_location, to)
        
        # 2. Initialize the timeline array (Difference Array).
        # We need size max_location + 1 because locations are 0-indexed,
        # and we need to account for the location 'max_location' itself.
        # This potentially uses less memory than a hardcoded [0] * 1001 if max_location is small.
        timeline = [0] * (max_location + 1)

        # 3. Populate the timeline (Sweep-line logic).
        for passengers, fro, to in trips:            
            # Passengers getting ON at 'fro'
            timeline[fro] += passengers
            # Passengers getting OFF at 'to'
            timeline[to] -= passengers
        
        # 4. Process the timeline (Prefix Sum / Sweep-line).
        current_passengers = 0
        for change in timeline:
            current_passengers += change
            
            # The required passengers at any point must not exceed the capacity.
            if current_passengers > capacity:
                return False

        return True