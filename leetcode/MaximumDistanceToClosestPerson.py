from typing import List


class Solution:
    def maxDistToClosest(self, seats: List[int]) -> int:
        """
        Finds the maximum distance to the closest person in a row of seats
        in a single pass.
        """
        n = len(seats)
        max_dist = 0
        # `last_person` stores the index of the last person encountered.
        last_person = -1

        for i, seat in enumerate(seats):
            if seat == 1:
                if last_person == -1:
                    # Case 1: Empty seats at the beginning of the row.
                    # The max distance is the index of this first person.
                    max_dist = i
                else:
                    # Case 2: Empty seats between two people.
                    # The best position is in the middle of the gap.
                    distance = (i - last_person) // 2
                    max_dist = max(max_dist, distance)
                # Update the index of the last person seen.
                last_person = i

        # Case 3: Empty seats at the end of the row.
        # The distance is from the last person to the end of the array.
        trailing_dist = n - 1 - last_person
        max_dist = max(max_dist, trailing_dist)

        return max_dist
