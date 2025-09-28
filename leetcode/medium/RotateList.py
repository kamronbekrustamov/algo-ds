from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        """
        Rotates a singly-linked list to the right by k places.

        Args:
            head: The head of the singly-linked list.
            k: The number of places to rotate the list to the right.

        Returns:
            The head of the rotated singly-linked list.
        """
        
        # Handle edge cases: empty list or single-node list.
        if not head or not head.next:
            return head

        # Step 1: Calculate the size of the linked list and find the last node.
        size = 0
        current = head
        while current:
            size += 1
            if not current.next: # Found the last node
                last_node = current
            current = current.next
        
        # Step 2: Adjust k.
        # k can be larger than the list size. We only care about k % size.
        # If k is a multiple of size, the list remains unchanged.
        k = k % size
        
        # If k is 0 after modulo, no rotation is needed.
        if k == 0:
            return head

        # The new head will be at (size - k)th position from the original head.
        # The new tail will be at (size - k - 1)th position from the original head.
        # We need to find the node that will become the new tail.
        # This node is 'size - k - 1' steps from the head.
        
        # Step 3: Find the new tail (the node before the new head).
        # Traverse 'size - k - 1' steps from the head.
        new_tail_position = size - k -1
        current = head
        for _ in range(new_tail_position):
            current = current.next
        
        # 'current' is now the new tail.
        new_head = current.next # The node after the new tail is the new head.
        current.next = None     # Break the link to form the new tail.
        
        # Step 4: Connect the original last node to the original head.
        last_node.next = head
        
        return new_head

        
        
        

        