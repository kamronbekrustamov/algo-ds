import heapq
from typing import List

class Solution:
    def getOrder(self, tasks: List[List[int]]) -> List[int]:
        """Determines the order in which a single-threaded CPU will process a given set of tasks.

        The CPU processes tasks based on the following rules:
        1. If the CPU is idle and no tasks are in the queue, it waits.
        2. When tasks are available, it chooses the one with the shortest processing time.
        3. If there's a tie in processing time, it chooses the one with the smaller original index.

        This implementation simulates this process efficiently using sorting and a min-heap.

        Args:
            tasks: A list of lists where tasks[i] = [enqueueTime, processingTime].

        Returns:
            A list representing the order of tasks processed by the CPU.
        """
        # Step 1: Augment tasks with their original indices and sort by enqueue time.
        # We need to preserve the original index of each task before sorting.
        # The format will be (enqueueTime, processingTime, originalIndex).
        indexed_tasks = sorted([(tasks[i][0], tasks[i][1], i) for i in range(len(tasks))])

        result_order = []
        # The min-heap stores available tasks as (processingTime, originalIndex).
        # Python's heapq naturally handles tie-breaking by comparing the second element
        # of the tuple if the first elements are equal.
        available_tasks_heap = []
        
        task_index = 0      # Pointer for the sorted indexed_tasks list
        current_time = 0    # Represents the current time of the CPU
        n = len(tasks)

        # Main simulation loop: continues as long as there are tasks to enqueue or process.
        while task_index < n or available_tasks_heap:
            # If the heap is empty, it means the CPU is idle. We must jump time forward.
            # The CPU will be idle until the next task's enqueue time.
            if not available_tasks_heap and current_time < indexed_tasks[task_index][0]:
                current_time = indexed_tasks[task_index][0]

            # Add all tasks that have become available by the current time to the min-heap.
            while task_index < n and indexed_tasks[task_index][0] <= current_time:
                enqueue_time, processing_time, original_index = indexed_tasks[task_index]
                heapq.heappush(available_tasks_heap, (processing_time, original_index))
                task_index += 1

            # If there are tasks in the queue, process the one with the highest priority.
            if available_tasks_heap:
                processing_time, original_index = heapq.heappop(available_tasks_heap)

                # Add the task's original index to the result and advance time.
                result_order.append(original_index)
                current_time += processing_time

        return result_order
