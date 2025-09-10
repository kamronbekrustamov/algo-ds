from typing import List

class Solution:
    def calPoints(self, operations: List[str]) -> int:
        """
        Calculates the total score after performing a series of operations.
        This solution uses a list as a stack to keep track of the scores.
        """
        # The list `scores` will act as a stack to record valid rounds.
        scores = []
        for op in operations:
            if op == '+':
                # Add the sum of the last two scores.
                scores.append(scores[-1] + scores[-2])
            elif op == 'D':
                # Double the last score.
                scores.append(scores[-1] * 2)
            elif op == 'C':
                # Invalidate and remove the last score.
                scores.pop()
            else:
                # A new score, convert to integer and add to the record.
                scores.append(int(op))
        return sum(scores)
