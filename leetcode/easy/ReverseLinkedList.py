from typing import List, Optional

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        # We can solve this iteratively by keeping track of two pointers:
        # `prev_node`: the node that comes before the current one (starts as None).
        # `current_node`: the node we are currently processing.
        prev_node = None
        current_node = head

        while current_node:
            # 1. Store the next node before we overwrite the pointer.
            next_node = current_node.next
            # 2. Reverse the `next` pointer of the current node to point to the previous one.
            current_node.next = prev_node
            # 3. Move `prev_node` and `current_node` one step forward for the next iteration.
            prev_node = current_node
            current_node = next_node

        # At the end of the loop, `current_node` is None, and `prev_node` is the new head.
        return prev_node