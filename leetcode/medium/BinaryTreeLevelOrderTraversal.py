from typing import Optional, List
import collections

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        """
        Performs a level order traversal of a binary tree (Breadth-First Search).

        This solution uses a queue to keep track of nodes to visit at each level.
        Using `collections.deque` is crucial for an efficient O(1) pop operation
        from the left of the queue.

        Algorithm:
        1. Handle the edge case of an empty tree.
        2. Initialize a queue (`collections.deque`) with the root node.
        3. Initialize an empty list `result` to store the levels.
        4. Loop as long as the queue is not empty:
           a. Get the number of nodes at the current level (`len(queue)`).
           b. Create an empty list `level` to store the values of nodes at this level.
           c. Loop `len(queue)` times to process all nodes for the current level.
              i. Dequeue a node from the front of the queue.
              ii. Append its value to the `level` list.
              iii. If the node has left/right children, enqueue them.
           d. Append the `level` list to the `result`.
        5. Return the `result`.

        Time Complexity: O(N), where N is the number of nodes, as each node is visited once.
        Space Complexity: O(W), where W is the maximum width of the tree. In the worst
        case (a complete binary tree), this can be O(N).
        """
        if not root:
            return []

        result = []
        # Use collections.deque for an efficient O(1) queue implementation.
        queue = collections.deque([root])

        while queue:
            # Get the number of nodes at the current level.
            level_size = len(queue)
            level = []

            # Process all nodes for the current level.
            for _ in range(level_size):
                # Dequeue the node from the front. O(1) operation.
                node = queue.popleft()
                level.append(node.val)

                # Enqueue children for the next level.
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

            result.append(level)
        return result