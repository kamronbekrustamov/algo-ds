from typing import List, Tuple, Dict

class DetectSquares:
    """
    Optimized implementation of DetectSquares.

    This version uses tuples as dictionary keys, which is more Pythonic and
    efficient than using string keys. The core logic of finding diagonal
    partners remains the same.

    Time Complexity:
    - add: O(1) on average.
    - count: O(N), where N is the number of unique points added.
    Space Complexity: O(N) to store all unique points.
    """

    def __init__(self):
        # Using a tuple (x, y) as the key is more efficient than a string.
        self.points_count: Dict[Tuple[int, int], int] = {}

    def add(self, point: List[int]) -> None:
        """Adds a point and its count to the dictionary."""
        point_tuple = tuple(point)
        self.points_count[point_tuple] = self.points_count.get(point_tuple, 0) + 1

    def count(self, point: List[int]) -> int:
        """
        Counts squares by finding diagonal points for the given query point.
        """
        total_squares = 0
        x1, y1 = point

        # Iterate through all stored points and their counts.
        # Using .items() is more efficient as it avoids a second dictionary lookup.
        for (x2, y2), count_p2 in self.points_count.items():
            # Check if the current stored point can form a diagonal with the query point.
            # A diagonal requires:
            # 1. x and y coordinates are different.
            # 2. The side lengths are equal (abs(dx) == abs(dy)).
            if x1 == x2 or y1 == y2 or abs(x1 - x2) != abs(y1 - y2):
                continue

            # If they form a diagonal, the other two corners are (x1, y2) and (x2, y1).
            p3 = (x1, y2)
            p4 = (x2, y1)

            # Get the counts of the other two corners.
            count_p3 = self.points_count.get(p3, 0)
            count_p4 = self.points_count.get(p4, 0)

            # The number of squares is the product of the counts of the three
            # other corners.
            total_squares += count_p2 * count_p3 * count_p4
            
        return total_squares
