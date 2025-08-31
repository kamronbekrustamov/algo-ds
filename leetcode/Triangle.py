from typing import List


class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        """
        Calculates the minimum path sum from top to bottom by modifying the
        triangle in-place, using a bottom-up dynamic programming approach.
        This approach has O(1) extra space complexity.
        """
        # Start from the second-to-last row and move upwards.
        for i in range(len(triangle) - 2, -1, -1):
            # For each element in the current row, update it with the minimum path sum
            # starting from that element.
            for j in range(len(triangle[i])):
                # The minimum path from triangle[i][j] is its own value plus the
                # minimum of the two adjacent elements in the row below.
                triangle[i][j] += min(triangle[i + 1][j], triangle[i + 1][j + 1])

        # After the loops, the top element of the triangle will contain the
        # overall minimum path sum from the original top to the bottom.
        return triangle[0][0]