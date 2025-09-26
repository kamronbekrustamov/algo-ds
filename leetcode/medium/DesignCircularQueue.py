class MyCircularQueue:
    """
    A circular queue implementation using a fixed-size list.
    This design uses a size counter and modulus arithmetic for efficient
    and clean state management.
    """

    def __init__(self, k: int):
        # The underlying list to store queue elements.
        self.queue = [0] * k
        # The maximum number of elements the queue can hold.
        self.capacity = k
        # The number of elements currently in the queue.
        self.count = 0
        # `front` pointer: index of the first element in the queue.
        self.front = 0
        # `rear` pointer: index of the next available slot for a new element.
        self.rear = 0

    def enQueue(self, value: int) -> bool:
        """Inserts an element into the circular queue. Returns true if the operation is successful."""
        if self.isFull():
            return False
        
        # Place the new value at the rear pointer's location.
        self.queue[self.rear] = value
        # Move the rear pointer to the next available slot, wrapping around if necessary.
        self.rear = (self.rear + 1) % self.capacity
        # Increment the count of elements.
        self.count += 1
        return True

    def deQueue(self) -> bool:
        """Deletes an element from the circular queue. Returns true if the operation is successful."""
        if self.isEmpty():
            return False
        
        # Move the front pointer to the next element, effectively "removing" the current one.
        self.front = (self.front + 1) % self.capacity
        # Decrement the count of elements.
        self.count -= 1
        return True

    def Front(self) -> int:
        """Gets the front item from the queue."""
        if self.isEmpty():
            return -1
        # The front pointer always indicates the first element.
        return self.queue[self.front]

    def Rear(self) -> int:
        """Gets the last item from the queue."""
        if self.isEmpty():
            return -1
        # The last element is at the position just before the rear pointer.
        # We use modulus to handle the wrap-around case where rear is 0.
        last_element_index = (self.rear - 1 + self.capacity) % self.capacity
        return self.queue[last_element_index]

    def isEmpty(self) -> bool:
        """Checks whether the circular queue is empty or not."""
        return self.count == 0

    def isFull(self) -> bool:
        """Checks whether the circular queue is full or not."""
        return self.count == self.capacity