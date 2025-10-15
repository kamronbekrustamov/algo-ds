from typing import List

class Solution:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        # 1. Combine position and speed, then sort by position in DESCENDING order.
        # This is the O(N log N) step, which is the dominant factor for time complexity.
        # zip() pairs them, list() converts the zip object to a list of tuples, and sort() orders them.
        # Sorting is done based on the first element (position) by default.
        sorted_cars = sorted(zip(position, speed), reverse=True)
        
        # Handle the trivial case (though N=0 is typically handled by problem constraints)
        if not sorted_cars:
            return 0
        
        fleet_count = 0
        
        # time_for_lead_car will track the time of the *current* fleet's leader.
        # Initializing it to 0.0 is fine, as the first car's time will always be > 0.
        time_for_lead_car = 0.0
        
        # 2. Iterate through the cars from the one closest to the target.
        for pos, sp in sorted_cars:
            # Calculate the time for the current car to reach the target.
            time = (target - pos) / sp
            
            # 3. Apply the Greedy/Stack-like logic.
            # A new fleet is formed *only if* the current car takes longer to reach the target 
            # than the car immediately ahead of it (the current fleet leader).
            # If time <= time_for_lead_car, the current car will catch up and merge, 
            # becoming part of the existing fleet.
            if time > time_for_lead_car:
                time_for_lead_car = time
                fleet_count += 1
                
        return fleet_count