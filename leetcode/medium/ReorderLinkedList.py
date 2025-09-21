from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reorderList(self, head: Optional[ListNode]) -> None:
        """
        Reorders a singly linked list in-place.
        
        Given a list L0 -> L1 -> ... -> Ln-1 -> Ln, it reorders it to
        L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...

        The solution follows three steps:
        1. Find the middle of the linked list to split it into two halves.
        2. Reverse the second half of the list.
        3. Merge the first half and the reversed second half.
        
        This is done in O(N) time and O(1) space.

        Args:
            head: The head of the linked list. The list is modified in-place.
        """
        if not head or not head.next:
            return

        # Step 1: Find the middle of the linked list.
        # 'slow' will point to the end of the first half.
        # 'fast' is used to find the middle.
        slow, fast = head, head.next
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        # Split the list into two halves.
        # 'second_head' is the head of the second half.
        second_head = slow.next
        slow.next = None

        # Step 2: Reverse the second half of the list.
        prev = None
        curr = second_head
        while curr:
            next_node = curr.next
            curr.next = prev
            prev = curr
            curr = next_node
        # 'prev' is now the new head of the reversed second half.
        second_head = prev

        # Step 3: Merge the first and the reversed second halves.
        first_head = head
        while second_head:
            # Save the next nodes of both lists before modifying pointers.
            temp1 = first_head.next
            temp2 = second_head.next

            # Interleave the nodes.
            first_head.next = second_head
            second_head.next = temp1

            # Move to the next pair of nodes to be merged.
            first_head = temp1
            second_head = temp2