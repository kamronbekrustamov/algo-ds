# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseBetween(self, head: Optional[ListNode], left: int, right: int) -> Optional[ListNode]:
        # If the list is empty or there's nothing to reverse, return the original list.
        if not head or left == right:
            return head
        
        # Use a dummy node to simplify edge cases, like reversing from the head of the list (left=1).
        dummy = ListNode(0, head)

        # 1. Traverse to the node just before the sublist to be reversed.
        # `left_prev` will be the node at position `left - 1`.
        left_prev = dummy
        for _ in range(left - 1):
            left_prev = left_prev.next
        
        # `sublist_head` is the first node of the sublist to be reversed (at position `left`).
        # We need to keep a reference to it because it will become the tail of the reversed sublist.
        sublist_head = left_prev.next
        
        # 2. Reverse the sublist from `left` to `right`.
        # This is the standard iterative linked list reversal.
        prev = None
        curr = sublist_head
        # The loop runs `right - left + 1` times to reverse all nodes in the sublist.
        for _ in range(right - left + 1):
            next_node = curr.next
            curr.next = prev
            prev = curr
            curr = next_node
            
        # 3. Connect the reversed sublist back to the main list.
        # `left_prev.next` should now point to the new head of the reversed sublist (`prev`).
        left_prev.next = prev
        # The original head of the sublist (`sublist_head`) is now the tail of the reversed part.
        # Its `next` should point to the node that was originally after the sublist (`curr`).
        sublist_head.next = curr
        
        # Return the head of the modified list, which is `dummy.next`.
        return dummy.next