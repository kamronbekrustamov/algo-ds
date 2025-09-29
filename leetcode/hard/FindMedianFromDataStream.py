class MedianFinder:

    def __init__(self):
        self.small_half = []
        self.large_half = []

    def addNum(self, num: int) -> None:
        if len(self.large_half) == 0:
            self.large_half.append(num)
            return
        if len(self.small_half) == 0:
            if num > self.large_half[0]:
                self.small_half.append(self.large_half[0])
                self.large_half[0] = num
            else:
                self.small_half.append(num)
            return
        
        if len(self.small_half) == len(self.large_half):
            if num > self.small_half[0]:
                self.large_half.append(num)
            else:
                self.large_half.append(self.small_half[0])
                self.small_half[0] = num
                self._heapify(self.small_half, 0, len(self.small_half), is_max_heap=True)
            self._heap_up(self.large_half, len(self.large_half) - 1, is_max_heap=False)

        else:
            if num < self.large_half[0]:
                self.small_half.append(num)
            else:
                self.small_half.append(self.large_half[0])
                self.large_half[0] = num
                self._heapify(self.large_half, 0, len(self.large_half), is_max_heap=False)
            self._heap_up(self.small_half, len(self.small_half) - 1, is_max_heap=True)


    def _heap_up(self, heap, i, is_max_heap=True):
        while i > 0:
            parent = (i - 1) // 2
            if heap[parent] < heap[i] and is_max_heap:
                heap[i], heap[parent] = heap[parent], heap[i]
                i = parent
            elif heap[parent] > heap[i] and (not is_max_heap):
                heap[i], heap[parent] = heap[parent], heap[i]
                i = parent                
            else:
                break

    def findMedian(self) -> float:
        if len(self.large_half) > len(self.small_half):
            return self.large_half[0]
        
        if len(self.large_half) == 0:
            return 0.0
        return (self.small_half[0] + self.large_half[0]) / 2.0


    def _heapify(self, heap, i, n, is_max_heap=True):
        parent = i
        while True:
            largest = parent
            left, right = parent * 2 + 1, parent * 2 + 2

            if is_max_heap:
                if left < n and heap[largest] < heap[left]:
                    largest = left
                if right < n and heap[largest] < heap[right]:
                    largest = right
            else:
                if left < n and heap[largest] > heap[left]:
                    largest = left

                if right < n and heap[largest] > heap[right]:
                    largest = right

            if parent == largest:
                return

            heap[parent], heap[largest] = heap[largest], heap[parent]
            parent = largest
