from collections import deque
from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minDepth(self, root: Optional[TreeNode]) -> int:
        # If the tree is empty, the depth is 0.
        if not root:
            return 0

        # Use a queue for Breadth-First Search (BFS).
        # Store tuples of (node, current_depth).
        queue = deque([(root, 1)])

        while queue:
            node, depth = queue.popleft()

            # If we find a leaf node, this is the first one we've encountered
            # at the shallowest level, so we can return its depth immediately.
            if not node.left and not node.right:
                return depth

            # Add children to the queue to explore the next level.
            if node.left:
                queue.append((node.left, depth + 1))
            if node.right:
                queue.append((node.right, depth + 1))