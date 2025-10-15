# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        """
        Removes the nth node from the end of a singly-linked list.

        This solution uses a two-pointer approach with a dummy head to handle edge cases
        gracefully, such as removing the first node of the list.

        Args:
            head: The head of the linked list.
            n: The position of the node to remove from the end.

        Returns:
            The head of the modified linked list.
        """
        # Create a dummy node to simplify edge cases, like removing the head.
        # The dummy node points to the original head of the list.
        dummy = ListNode(0, head)
        
        # Initialize two pointers, 'slow' and 'fast', both starting at the dummy node.
        slow = dummy
        fast = dummy

        # Move the 'fast' pointer n + 1 steps ahead.
        # This creates a gap of n nodes between 'slow' and 'fast'.
        for _ in range(n + 1):
            fast = fast.next

        # Now, move both 'slow' and 'fast' pointers one step at a time
        # until the 'fast' pointer reaches the end of the list (None).
        # When 'fast' is at the end, 'slow' will be positioned just before
        # the node that needs to be removed.
        while fast:
            slow = slow.next
            fast = fast.next

        # 'slow' is now at the (n+1)th node from the end.
        # To remove the nth node from the end, we bypass it by updating
        # the 'next' pointer of the 'slow' node.
        slow.next = slow.next.next

        # Return the head of the modified list, which is the 'next' of the dummy node.
        return dummy.next