from typing import List


class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        ans = []
        stack = []  # Use a list as a stack to build the current combination

        def backtrack(open_count, close_count):
            # Base case: if the combination is complete
            if open_count == n and close_count == n:
                ans.append("".join(stack))
                return

            # Add an open parenthesis if we can
            if open_count < n:
                stack.append("(")
                backtrack(open_count + 1, close_count)
                stack.pop()  # Backtrack

            # Add a close parenthesis if it's valid to do so
            if close_count < open_count:
                stack.append(")")
                backtrack(open_count, close_count + 1)
                stack.pop()  # Backtrack

        backtrack(0, 0)
        return ans
