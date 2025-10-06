class MyQueue:
    """
    Implements a First-In, First-Out (FIFO) queue using two stacks.

    This implementation uses an input stack and an output stack to achieve
    queue functionality. The `push` operation is O(1), while `pop` and `peek`
    have an amortized time complexity of O(1).
    """

    def __init__(self):
        """
        Initializes the queue with two empty stacks (implemented as lists).
        `stack_in` is used for push operations.
        `stack_out` is used for pop and peek operations.
        """
        self.stack_in = []
        self.stack_out = []

    def push(self, x: int) -> None:
        """
        Pushes an element x to the back of the queue.

        This operation simply appends the element to the `stack_in`.

        Time complexity: O(1).
        """
        self.stack_in.append(x)

    def pop(self) -> int:
        """
        Removes and returns the element from the front of the queue.

        If `stack_out` is empty, it first moves all elements from `stack_in`
        to `stack_out`. This reversal of elements ensures FIFO order.

        Amortized time complexity: O(1).
        """
        self.move()
        return self.stack_out.pop()

    def peek(self) -> int:
        """
        Returns the element at the front of the queue without removing it.

        Similar to `pop`, it ensures `stack_out` has the front element.

        Amortized time complexity: O(1).
        """
        self.move()
        return self.stack_out[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.

        The queue is empty if and only if both stacks are empty.

        Time complexity: O(1).
        """
        return not self.stack_in and not self.stack_out

    def move(self) -> None:
        """
        Moves elements from `stack_in` to `stack_out` if `stack_out` is empty.

        This operation is the core of the queue implementation. It reverses the
        order of elements from `stack_in` and places them in `stack_out`,
        so the first element pushed into `stack_in` becomes the top of `stack_out`.
        """
        if not self.stack_out:
            while self.stack_in:
                self.stack_out.append(self.stack_in.pop())

# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
