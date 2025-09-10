from typing import Optional
from collections import deque

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def findTarget(self, root: Optional[TreeNode], k: int) -> bool:
        """
        Finds if there exist two elements in the BST such that their sum is equal to k.

        This solution uses a Breadth-First Search (BFS) traversal combined with a hash set
        to achieve O(n) time and O(n) space complexity.
        """
        if not root:
            return False

        seen_values = set()
        queue = deque([root])

        while queue:
            node = queue.popleft()
            if k - node.val in seen_values:
                return True
            seen_values.add(node.val)

            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)

        return False
