from typing import List

class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        """
        Calculates the weight of the last remaining stone after repeatedly
        smashing the two heaviest stones together.

        This implementation uses a manual, iterative max-heap to efficiently 
        find the two heaviest stones in each step.

        The time complexity is O(n log n) due to the heap operations.
        The space complexity is O(1) as the heap is built in-place.

        Args:
            stones: A list of integers representing the weights of the stones.

        Returns:
            The weight of the last remaining stone, or 0 if no stones are left.
        """
        
        # Build a max-heap from the stones list.
        self._build_max_heap(stones)

        while len(stones) > 1:
            # Extract the two largest stones.
            first = self._heappop(stones)
            second = self._heappop(stones)

            if first != second:
                # If the stones have different weights, calculate the difference
                # and add the new stone back to the heap.
                new_stone = first - second
                self._heappush(stones, new_stone)

        # If there is one stone left, return its weight. Otherwise, return 0.
        return stones[0] if stones else 0

    def _build_max_heap(self, heap: List[int]):
        """Builds a max-heap in-place."""
        n = len(heap)
        for i in range(n // 2 - 1, -1, -1):
            self._heapify_down(heap, i, n)

    def _heapify_down(self, heap: List[int], i: int, n: int):
        """Maintains the max-heap property by moving an element down iteratively."""
        parent = i
        while True:
            largest = parent
            left = 2 * parent + 1
            right = 2 * parent + 2

            if left < n and heap[left] > heap[largest]:
                largest = left
            
            if right < n and heap[right] > heap[largest]:
                largest = right

            if largest == parent:
                break
            
            heap[parent], heap[largest] = heap[largest], heap[parent]
            parent = largest


    def _heappop(self, heap: List[int]) -> int:
        """Pops the largest element from the max-heap."""
        if not heap:
            return 0
        
        # Swap the root (largest element) with the last element.
        heap[0], heap[-1] = heap[-1], heap[0]
        largest = heap.pop()
        
        # Restore the heap property.
        self._heapify_down(heap, 0, len(heap))
        
        return largest

    def _heappush(self, heap: List[int], val: int):
        """Pushes a new value onto the max-heap."""
        heap.append(val)
        i = len(heap) - 1
        while i > 0:
            parent = (i - 1) // 2
            if heap[i] > heap[parent]:
                heap[i], heap[parent] = heap[parent], heap[i]
                i = parent
            else:
                break
