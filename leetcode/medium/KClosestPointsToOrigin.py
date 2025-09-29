from typing import List


class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        """
        Finds the k closest points to the origin (0, 0).

        This solution uses a manual max-heap of size k to keep track of the k closest
        points found so far. The point with the largest distance from the origin
        will be at the root of the heap.

        The algorithm proceeds as follows:
        1. It takes the first k points from the input list to create an initial list.
        2. It builds a max-heap from this initial list.
        3. It then iterates through the rest of the points in the input list.
        4. For each point, it checks if its distance to the origin is less than the
           distance of the root of the max-heap (the farthest point currently in the heap).
        5. If the new point is closer, it replaces the root with the new point and
           re-heapifies to maintain the max-heap property.
        6. After checking all the points, the heap contains the k closest points.

        Args:
            points: A list of points, where each point is a list of two integers [x, y].
            k: The number of closest points to return.

        Returns:
            A list containing the k closest points to the origin.
        """
        
        # Helper function to calculate the squared Euclidean distance from the origin.
        # Using the squared distance is sufficient for comparison and avoids
        # the computationally expensive square root operation.
        def squared_distance(point: List[int]) -> int:
            return point[0] ** 2 + point[1] ** 2


        # This function maintains the max-heap property for a subtree.
        # It ensures that the parent node is always larger than its children.
        def _max_heapify_down(heap: List[List[int]], parent_index: int, heap_size: int):
            # The loop continues as long as the parent node might be smaller than its children.
            while True:
                # Assume the parent is the largest initially.
                # The variable name 'largest_index' is used to track the index of the
                # largest element among the parent and its children.
                largest_index = parent_index
                left_child_index = parent_index * 2 + 1
                right_child_index = parent_index * 2 + 2
                
                # Check if the left child exists and is farther from the origin than the current largest.
                if left_child_index < heap_size and squared_distance(heap[largest_index]) < squared_distance(heap[left_child_index]):
                    largest_index = left_child_index
                
                # Check if the right child exists and is farther from the origin than the current largest.
                if right_child_index < heap_size and squared_distance(heap[largest_index]) < squared_distance(heap[right_child_index]):
                    largest_index = right_child_index
                
                # If the largest element is still the parent, the subtree is a valid max-heap.
                if largest_index == parent_index:
                    break
                
                # If a child is larger, swap it with the parent.
                heap[parent_index], heap[largest_index] = heap[largest_index], heap[parent_index]
                # Continue heapifying from the new position of the parent.
                parent_index = largest_index
        
        # --- Main Logic ---

        # 1. Create an initial list with the first k points. This will become our max-heap.
        max_heap = points[:k]
        
        # 2. Build the max-heap from the initial list.
        # We start from the last non-leaf node and heapify down to the root.
        for i in range(k // 2 - 1, -1, -1):
            _max_heapify_down(max_heap, i, k)
        
        # 3. Iterate through the rest of the points.
        for i in range(k, len(points)):
            # 4. If the current point is closer than the farthest point in the heap (the root).
            if squared_distance(points[i]) < squared_distance(max_heap[0]):
                # 5. Replace the farthest point with the current point.
                max_heap[0] = points[i]
                # And restore the max-heap property by sifting the new root down.
                _max_heapify_down(max_heap, 0, k)
                
        # 6. The heap now contains the k closest points.
        return max_heap
