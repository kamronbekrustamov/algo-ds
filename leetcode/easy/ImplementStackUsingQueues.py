from collections import deque

class MyStack:
    """
    Implements a Last-In, First-Out (LIFO) stack using only one queue.
    The most recently added element is always kept at the front of the queue.
    """

    def __init__(self):
        """
        Initializes the stack with an empty queue.
        """
        self.queue = deque()

    def push(self, x: int) -> None:
        """
        Pushes an element x onto the top of the stack.

        To maintain the LIFO order with a FIFO queue, we add the new element
        to the queue and then rotate the queue so that the new element moves
        from the back to the front.

        Time complexity: O(n), where n is the number of elements in the stack.
        """
        # Add the new element to the back of the queue.
        self.queue.append(x)
        # Rotate the queue to move the new element to the front.
        # The number of rotations is one less than the size of the queue.
        for _ in range(len(self.queue) - 1):
            self.queue.append(self.queue.popleft())

    def pop(self) -> int:
        """
        Removes and returns the element on top of the stack.

        Since the most recently pushed element is at the front of the queue,
        we can simply pop from the left of the deque.

        Time complexity: O(1).
        """
        return self.queue.popleft()

    def top(self) -> int:
        """
        Returns the element on top of the stack without removing it.

        The top element is at the front of the queue.

        Time complexity: O(1).
        """
        return self.queue[0]

    def empty(self) -> bool:
        """
        Returns whether the stack is empty.

        Time complexity: O(1).
        """
        return len(self.queue) == 0

# Your MyStack object will be instantiated and called as such:
# obj = MyStack()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.top()
# param_4 = obj.empty()
