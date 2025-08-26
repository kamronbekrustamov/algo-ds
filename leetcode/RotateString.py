class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        # A string `goal` is a rotation of `s` if and only if:
        # 1. They have the same length.
        # 2. `goal` is a substring of `s` concatenated with itself (`s + s`).
        # This also correctly handles the case of empty strings.
        return len(s) == len(goal) and goal in s + s