from typing import Optional, List
from collections import deque

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []
        queue = deque([root])
        result = []
        while queue:
            # The number of nodes at the current level.
            level_length = len(queue)
            
            # The last node in the queue for the current level is the rightmost one.
            result.append(queue[-1].val)

            # Dequeue all nodes of the current level and enqueue all nodes of the next level.
            for _ in range(level_length):
                node = queue.popleft() # O(1) operation with deque
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
        return result