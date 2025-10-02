from typing import List

class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        """
        Calculates the minimum time required to execute all tasks with a cooling-down period.

        This solution uses a mathematical formula based on a greedy approach.
        The key idea is to determine the schedule based on the most frequent task.

        Algorithm Breakdown:
        1. Count the frequency of each task.
        2. Find the frequency of the most frequent task, let's call it `max_freq`.
        3. The most frequent task will determine the overall length of the schedule.
           There will be `max_freq - 1` intervals between executions of this task.
           Each interval must be at least `n` time units long.
           This gives a baseline of `(max_freq - 1) * n` idle slots.
           Example: A, A, A, n=2 -> A, _, _, A, _, _, A
           The schedule frame is determined by 'A': (A, _, _) (A, _, _) (A)
           Frame size = n+1. Number of frames = max_freq-1.
        4. We then fill these idle slots with the other tasks.
        5. The total time will be the length of the task list plus any remaining
           idle slots that could not be filled.
        6. An edge case is when there are no idle slots needed (e.g., tasks are
           very diverse). In this case, the total time is simply the number of tasks.

        Time Complexity: O(N), where N is the number of tasks, for the initial count.
                         The rest of the operations are on a fixed-size array (26).
        Space Complexity: O(1), as the frequency map has a constant size of 26.

        Args:
            tasks: A list of characters representing tasks.
            n: The cooling-down period between two identical tasks.

        Returns:
            The minimum time units required to complete all tasks.
        """
        # 1. Count the frequency of each task.
        frequencies = [0] * 26
        for task in tasks:
            frequencies[ord(task) - ord('A')] += 1
        
        # Sort the frequencies to easily find the most frequent ones.
        frequencies.sort()
        
        # 2. Find the frequency of the most frequent task.
        max_freq = frequencies[25]
        
        # 3. Calculate the number of idle slots required by the most frequent task.
        # There are `max_freq - 1` chunks of tasks separated by idle time.
        # Each chunk requires `n` idle slots after it.
        idle_time = (max_freq - 1) * n
        
        # 4. Iterate backwards from the second most frequent task to fill idle slots.
        # The last element is the max_freq task itself, so we go up to 24.
        for i in range(24, -1, -1):
            if frequencies[i] == 0:
                break # No more tasks to fill slots with.
            
            # The number of tasks we can place in the idle slots is limited by
            # the number of chunks available (`max_freq - 1`).
            # For example, if we have A,A,A and B,B,B, the schedule is A,B,_,A,B,_,A,B.
            # The third 'B' doesn't fill an idle slot, it extends the schedule.
            # So, we can fill at most `max_freq - 1` slots with each other task type.
            idle_time -= min(frequencies[i], max_freq - 1)
            
        # If idle_time is negative, it means we had more than enough tasks to
        # fill the idle slots. This happens when the schedule is not limited by
        # the cooling period but by the sheer number of tasks. In this case,
        # no idle time is needed, and the total time is just the number of tasks.
        idle_time = max(0, idle_time)
        
        return len(tasks) + idle_time

# --- Alternative Solution using a Priority Queue (Max-Heap) ---
#
# import heapq
# from collections import Counter
#
# class Solution:
#     def leastInterval_pq(self, tasks: List[str], n: int) -> int:
#         """
#         Calculates the minimum time using a simulation approach with a Priority Queue.
#
#         Algorithm Breakdown:
#         1. Count task frequencies.
#         2. Use a max-heap to store the frequencies of the tasks. This allows us
#            to always process the most frequent available task first.
#         3. Simulate the time steps. In each "round", we try to execute `n+1` tasks.
#         4. In one round:
#            a. Dequeue up to `n+1` tasks from the max-heap (the ones with the
#               highest frequencies).
#            b. Decrement their frequencies.
#            c. Add them to a temporary list.
#            d. After the round, add the tasks from the temporary list whose
#               frequencies are still greater than 0 back to the max-heap.
#         5. The total time is the sum of the lengths of these rounds. If the heap
#            becomes empty mid-round, the time taken is just the number of tasks
#            processed in that final round.
#
#         Time Complexity: O(N * log(M)), where N is the number of tasks and M is the
#                          number of unique tasks (at most 26). The log factor comes
#                          from the heap operations.
#         Space Complexity: O(M) or O(1) for the frequency counter and heap.
#         """
#         # 1. Count frequencies of each task.
#         task_counts = Counter(tasks)
#
#         # 2. Create a max-heap. In Python, heapq is a min-heap, so we store
#         #    negative counts to simulate a max-heap.
#         max_heap = [-count for count in task_counts.values()]
#         heapq.heapify(max_heap)
#
#         time = 0
#         # Continue as long as there are tasks to process.
#         while max_heap:
#             # A temporary list to hold tasks processed in the current round.
#             processed_in_round = []
#             # A round's length is n+1 (1 task + n cooling slots).
#             round_length = n + 1
#
#             # 4a. Process up to n+1 tasks from the heap.
#             for _ in range(round_length):
#                 if max_heap:
#                     # Get the most frequent task.
#                     count = heapq.heappop(max_heap)
#                     # 4b. Decrement its count.
#                     count += 1
#                     # 4c. Add to temp list if it still needs processing.
#                     if count < 0:
#                         processed_in_round.append(count)
#
#             # 4d. Add the processed tasks back to the heap.
#             for item in processed_in_round:
#                 heapq.heappush(max_heap, item)
#
#             # 5. Update the total time.
#             if not max_heap:
#                 # If the heap is empty, the last round was not a full n+1 cycle.
#                 time += len(processed_in_round)
#             else:
#                 # If the heap is not empty, it means we used a full n+1 cycle.
#                 time += round_length
#
#         return time