"""
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
"""

class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if not head:
            return None
        
        # --- Pass 1: Interweave the original and new nodes ---
        # Iterate through the original list. For each node, create a copy
        # and place it immediately after the original node.
        # Original: A -> B -> C
        # After Pass 1: A -> A' -> B -> B' -> C -> C'
        curr = head
        while curr:
            # Create a new node with the same value as the current node.
            new_node = Node(curr.val)
            # Link the new node into the list.
            new_node.next = curr.next
            curr.next = new_node
            # Move to the next original node (skipping over the new one we just added).
            curr = curr.next.next
        
        # --- Pass 2: Set the random pointers for the new nodes ---
        # Now that every new node is next to its original, we can easily set the random pointers.
        curr = head
        while curr:
            # If the original node has a random pointer...
            if curr.random:
                # ...the new node's random pointer should point to the copy of the original's random target.
                # `curr.random.next` is the copied node corresponding to `curr.random`.
                curr.next.random = curr.random.next
            # Move to the next original node.
            curr = curr.next.next
        
        # --- Pass 3: Unlink the two lists ---
        # Separate the interwoven list into the original list and the new copied list.
        new_head = head.next
        curr_old = head
        curr_new = head.next

        while curr_old:
            # Restore the 'next' pointer of the original list.
            curr_old.next = curr_old.next.next
            # Set the 'next' pointer for the new list, if it's not the last node.
            if curr_new.next:
                curr_new.next = curr_new.next.next
            # Move to the next pair of old and new nodes.
            curr_old = curr_old.next
            curr_new = curr_new.next

        # Return the head of the newly created deep copy.
        return new_head

        