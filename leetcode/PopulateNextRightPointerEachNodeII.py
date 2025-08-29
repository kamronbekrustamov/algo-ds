"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""

class Solution:
    def connect(self, root: 'Node') -> 'Node':
        level_head = root

        while level_head:
            level_iteration = level_head
            connector = None
            next_node = None
            while level_iteration:
                if level_iteration.left:
                    if connector is None:
                        next_node = level_iteration.left
                    else:
                        connector.next = level_iteration.left
                    connector = level_iteration.left
                if level_iteration.right:
                    if connector is None:
                        next_node = level_iteration.right
                    else:
                        connector.next = level_iteration.right
                    connector = level_iteration.right
                level_iteration = level_iteration.next
            level_head = next_node
        return root
        
        