from collections import deque

class MinStack:
    """
    A stack that supports push, pop, top, and retrieving the minimum element in constant time.

    This implementation uses a single stack where each element is a tuple containing the value
    and the minimum value in the stack up to that point.
    """

    def __init__(self):
        """
        Initializes the MinStack with an empty stack.
        The stack will store tuples of (value, current_minimum).
        """
        self.stack = deque()

    def push(self, val: int) -> None:
        """
        Pushes an element val onto the stack.

        When pushing a new value, we also store the minimum value seen so far.
        If the stack is empty, the minimum is the value itself.
        Otherwise, it's the minimum of the new value and the previous minimum.

        Time complexity: O(1).
        """
        if self.stack:
            current_min = self.stack[-1][1]
            self.stack.append((val, min(val, current_min)))
        else:
            self.stack.append((val, val))

    def pop(self) -> None:
        """
        Removes the element on top of the stack.

        Time complexity: O(1).
        """
        if self.stack:
            self.stack.pop()

    def top(self) -> int:
        """
        Returns the top element of the stack without removing it.

        Time complexity: O(1).
        """
        if self.stack:
            return self.stack[-1][0]

    def getMin(self) -> int:
        """
        Retrieves the minimum element in the stack.

        The minimum element is stored as the second item in the tuple at the top of the stack.

        Time complexity: O(1).
        """
        if self.stack:
            return self.stack[-1][1]

# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
