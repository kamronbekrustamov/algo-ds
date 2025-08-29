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
        head = root

        while head:
            # A dummy node to represent the start of the next level.
            dummy = Node(0)
            # `tail` will be used to build the `next` pointers for the next level.
            tail = dummy

            # `curr` iterates through the nodes of the current level.
            curr = head
            while curr:
                # If a left child exists, append it to the next level's list.
                if curr.left:
                    tail.next = curr.left
                    tail = tail.next
                # If a right child exists, append it to the next level's list.
                if curr.right:
                    tail.next = curr.right
                    tail = tail.next
                # Move to the next node in the current level.
                curr = curr.next
            
            # The next level starts at `dummy.next`.
            head = dummy.next

        return root
