from typing import Optional


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def middleNode(self, head: Optional[ListNode]) -> Optional[ListNode]:
        """
        Finds the middle node of a singly linked list.
        If there are two middle nodes, it returns the second middle node.

        This is solved using the classic "slow and fast pointer" technique.
        The slow pointer moves one step at a time, while the fast pointer
        moves two steps. When the fast pointer reaches the end of the list,
        the slow pointer will be at the middle.
        """
        slow = fast = head

        # The loop continues as long as the fast pointer and the node after it are valid.
        # This elegantly handles both odd and even length lists.
        while fast and fast.next:
            # Move slow pointer one step.
            slow = slow.next
            # Move fast pointer two steps.
            fast = fast.next.next

        # When the loop ends, `slow` is at the middle node.
        return slow
