from typing import Optional


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeElements(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
        """
        Removes all the nodes of a linked list that has Node.val == val.
        This solution uses a dummy head and a single pointer with a lookahead.
        """
        # A dummy node helps handle cases where the head itself needs to be removed.
        dummy_head = ListNode(next=head)

        # `current` will act as the pointer to the node *before* the one we're inspecting.
        current = dummy_head

        # We look ahead to `current.next` to decide whether to remove it.
        while current.next:
            if current.next.val == val:
                # If the next node has the target value, bypass it by linking
                # `current` to the node after the next one.
                current.next = current.next.next
            else:
                # If the next node is fine, move `current` one step forward.
                current = current.next

        # The list starting from `dummy_head.next` is now correctly filtered.
        return dummy_head.next