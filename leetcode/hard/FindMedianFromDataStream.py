class MedianFinder:
    """
    A data structure that finds the median from a data stream of integers.

    This implementation uses two heaps to find the median:
    1. A max-heap (`small_half`) to store the smaller half of the numbers.
    2. A min-heap (`large_half`) to store the larger half of the numbers.

    The heaps are intended to be balanced such that their sizes differ by at most 1.
    """

    def __init__(self):
        """Initializes the MedianFinder object."""
        # `small_half` is implemented as a max-heap to store the smaller half of numbers.
        self.small_half = []
        # `large_half` is implemented as a min-heap to store the larger half of numbers.
        self.large_half = []

    def addNum(self, num: int) -> None:
        """
        Adds an integer to the data structure, attempting to keep the two heaps balanced.
        """
        # --- Initial insertion logic for the first two numbers ---
        if len(self.large_half) == 0:
            self.large_half.append(num)
            return
        
        if len(self.small_half) == 0:
            # If the new number is larger than the single element in large_half,
            # swap them to maintain the property that large_half contains larger numbers.
            if num > self.large_half[0]:
                self.small_half.append(self.large_half[0])
                self.large_half[0] = num
            else:
                self.small_half.append(num)
            return

        # --- Balancing logic when both heaps are populated ---

        # Case 1: The heaps are currently equal in size.
        # After this operation, `large_half` should have one more element.
        if len(self.small_half) == len(self.large_half):
            # If the new number is larger than the top of the max-heap (the largest
            # element in the small half), it belongs in the large_half.
            if num > self.small_half[0]:
                self.large_half.append(num)
            # Otherwise, the new number belongs in the small_half. To make room,
            # the largest element from small_half is moved to large_half.
            else:
                self.large_half.append(self.small_half[0])
                self.small_half[0] = num
                # Re-heapify small_half after replacing its root.
                self._heapify(self.small_half, 0, len(self.small_half), is_max_heap=True)
            # Sift up the newly added element in large_half to its correct position.
            self._heap_up(self.large_half, len(self.large_half) - 1, is_max_heap=False)

        # Case 2: The heaps are currently unequal.
        # This implies `large_half` has one more element than `small_half`.
        # After this operation, both heaps should be equal in size.
        else:
            # If the new number is smaller than the top of the min-heap (the smallest
            # element in the large half), it belongs in the small_half.
            if num < self.large_half[0]:
                self.small_half.append(num)
            # Otherwise, the new number belongs in the large_half. To make room,
            # the smallest element from large_half is moved to small_half.
            else:
                self.small_half.append(self.large_half[0])
                self.large_half[0] = num
                # Re-heapify large_half after replacing its root.
                self._heapify(self.large_half, 0, len(self.large_half), is_max_heap=False)
            # Sift up the newly added element in small_half to its correct position.
            self._heap_up(self.small_half, len(self.small_half) - 1, is_max_heap=True)


    def _heap_up(self, heap, i, is_max_heap=True):
        """
        Moves an element up the heap to its correct position (sift-up).
        This is used after inserting a new element at the end of the heap.
        """
        while i > 0:
            parent = (i - 1) // 2
            # For a max-heap, swap if the child is greater than the parent.
            if is_max_heap and heap[parent] < heap[i]:
                heap[i], heap[parent] = heap[parent], heap[i]
                i = parent
            # For a min-heap, swap if the child is smaller than the parent.
            elif (not is_max_heap) and heap[parent] > heap[i]:
                heap[i], heap[parent] = heap[parent], heap[i]
                i = parent                
            else:
                # The element is in its correct position.
                break

    def findMedian(self) -> float:
        """
        Returns the median of all elements added so far.
        """
        # If the heaps are empty.
        if len(self.large_half) == 0 and len(self.small_half) == 0:
            return 0.0
        
        # If the heaps have equal size, the total count is even.
        # The median is the average of the two middle elements.
        if len(self.large_half) == len(self.small_half):
            # The middle elements are the top of the max-heap and the top of the min-heap.
            return (self.small_half[0] + self.large_half[0]) / 2.0
        
        # If the heaps have unequal size, the total count is odd.
        # By design, `large_half` will have the extra element, which is the median.
        return float(self.large_half[0])


    def _heapify(self, heap, i, n, is_max_heap=True):
        """
        Restores the heap property by moving an element down (sift-down).
        This is used after replacing the root element of the heap.
        """
        parent = i
        while True:
            # Assume the current parent is the largest (for max-heap) or smallest (for min-heap).
            node_to_swap = parent
            left, right = parent * 2 + 1, parent * 2 + 2

            if is_max_heap:
                # In a max-heap, find the largest among parent, left, and right child.
                if left < n and heap[node_to_swap] < heap[left]:
                    node_to_swap = left
                if right < n and heap[node_to_swap] < heap[right]:
                    node_to_swap = right
            else: # is_min_heap
                # In a min-heap, find the smallest among parent, left, and right child.
                if left < n and heap[node_to_swap] > heap[left]:
                    node_to_swap = left
                if right < n and heap[node_to_swap] > heap[right]:
                    node_to_swap = right

            # If the parent is already in the correct place, stop.
            if parent == node_to_swap:
                return

            # Swap the parent with the child and continue sifting down.
            heap[parent], heap[node_to_swap] = heap[node_to_swap], heap[parent]
            parent = node_to_swap