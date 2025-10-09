import heapq
from typing import List

class Solution:
    def findMaximizedCapital(self, k: int, w: int, profits: List[int], capital: List[int]) -> int:
        """
        Finds the maximized capital after finishing at most k distinct projects.

        This problem is solved using a greedy approach. At each step, we want to invest
        in the most profitable project that we can currently afford. This suggests a max-heap.

        The overall strategy is:
        1. Combine all projects (capital requirement and profit) into a single list of tuples.
        2. Sort all projects by their capital requirement. This allows us to efficiently
           find all affordable projects at any given time.
        3. Iterate up to k times (for each investment opportunity).
        4. In each iteration, add all newly affordable projects (based on current capital `w`)
           to a max-heap of profits.
        5. Greedily select the most profitable project from the heap, add its profit to our
           capital, and repeat.

        Args:
            k: The maximum number of projects to invest in.
            w: The initial capital.
            profits: An array of profits for each project.
            capital: An array of capital requirements for each project.

        Returns:
            The final maximized capital.
        """
        n = len(profits)
        
        # Step 1: Combine capital and profits into a single structure.
        # projects = [(capital[i], profits[i]) for i in range(n)]
        projects = []
        for i in range(n):
            projects.append((capital[i], profits[i]))

        # Step 2: Sort the projects based on their capital requirement.
        # This allows us to iterate through them once in an efficient manner.
        projects.sort(key=lambda x: x[0])

        # Step 3: Use a max-heap to store the profits of all currently affordable projects.
        # Python's heapq is a min-heap, so we store negative profits to simulate a max-heap.
        max_heap = []
        
        project_index = 0  # Pointer for the sorted projects array.

        # Step 4: Loop up to k times to make investments.
        for _ in range(k):
            # Add all projects that we can now afford (capital <= w) to the max-heap.
            while project_index < n and projects[project_index][0] <= w:
                heapq.heappush(max_heap, -projects[project_index][1])  # Store negative profit
                project_index += 1

            # If the heap is empty, it means there are no affordable projects left to invest in.
            if not max_heap:
                break

            # Greedily invest in the most profitable available project.
            w += -heapq.heappop(max_heap)  # Add the profit (which was stored as negative)

        return w
