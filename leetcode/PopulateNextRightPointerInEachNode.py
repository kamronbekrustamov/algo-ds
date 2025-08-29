from typing import Optional

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
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root:
            return root
        
        # `leftmost` is the starting node of the current level we are processing.
        leftmost = root

        # The loop continues as long as there is a next level to process.
        # For a perfect binary tree, if a node has a left child, it also has a right one.
        while leftmost.left:
            # `head` is used to traverse the current level using the `next` pointers.
            head = leftmost
            while head:
                # 1. Connect the left child to the right child of the same parent.
                head.left.next = head.right

                # 2. Connect the right child to the left child of the adjacent parent on the same level.
                if head.next:
                    head.right.next = head.next.left
                
                # Move to the next node on the current level.
                head = head.next
            
            # Move to the start of the next level.
            leftmost = leftmost.left

        return root