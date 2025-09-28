import heapq
from typing import List

class KthLargest:
    """
    Design a class to find the k-th largest element in a stream of numbers.

    This class uses a min-heap to efficiently keep track of the k largest
    elements seen so far. The root of the min-heap is the k-th largest element.

    Attributes:
        min_heap: A min-heap of size k.
        k: The value of k.
    """

    def __init__(self, k: int, nums: List[int]):
        """
        Initializes the KthLargest object with the value of k and an initial
        stream of numbers.

        Args:
            k: The k-th largest element to find.
            nums: An initial list of numbers in the stream.
        """
        self.k = k
        self.min_heap = nums
        heapq.heapify(self.min_heap)
        
        # Keep only the k largest elements in the heap
        while len(self.min_heap) > k:
            heapq.heappop(self.min_heap)

    def add(self, val: int) -> int:
        """
        Adds a new number to the stream and returns the current k-th largest element.

        Args:
            val: The new number to add to the stream.

        Returns:
            The k-th largest element in the stream after adding the new value.
        """
        if len(self.min_heap) < self.k:
            heapq.heappush(self.min_heap, val)
        elif val > self.min_heap[0]:
            heapq.heapreplace(self.min_heap, val)
        
        return self.min_heap[0]