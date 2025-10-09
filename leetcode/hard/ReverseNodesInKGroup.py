from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        """
        Reverses the nodes of a linked list k at a time and returns the modified list's head.
        If the number of nodes is not a multiple of k, then left-out nodes in the end should remain as is.

        This solution uses an iterative approach with a dummy node to simplify edge cases.
        It identifies each k-group, reverses it, and then connects it back to the main list.

        Algorithm:
        1. Create a dummy node and point its `next` to the `head` of the original list.
           This dummy node simplifies handling the new head of the list.
        2. Initialize `prev_group_end` to the dummy node. This pointer will always point
           to the node *before* the current k-group being processed.
        3. Loop indefinitely until the end of the list is reached:
           a. Identify the start of the current k-group (`group_start = prev_group_end.next`).
           b. Find the end of the current k-group (`group_end`). Traverse `k-1` steps from `group_start`.
              If `group_end` becomes `None` before `k-1` steps, it means there are fewer than `k` nodes
              remaining, so break the loop (these nodes should not be reversed).
           c. Store the node immediately after the current k-group (`next_group_start = group_end.next`).
           d. Disconnect the k-group from the rest of the list: `group_end.next = None`.
           e. Reverse the k-group: Call a helper function `_reverse_list(group_start)` which
              reverses the sublist and returns its new head (which was the original `group_end`)
              and its new tail (which was the original `group_start`).
           f. Connect the reversed k-group back:
              - `prev_group_end.next` should point to the new head of the reversed group.
              - The new tail of the reversed group should point to `next_group_start`.
           g. Update `prev_group_end` to the new tail of the reversed group (which is `group_start`).
        4. Return `dummy.next`, which is the head of the fully modified list.

        Time Complexity: O(N), where N is the total number of nodes in the linked list.
                         Each node is visited and modified a constant number of times.
        Space Complexity: O(1), as we only use a few extra pointers.
        """
        dummy = ListNode(0, head)
        prev_group_end = dummy

        while True:
            # 1. Check if there are at least k nodes remaining to reverse.
            group_start = prev_group_end.next
            group_end = prev_group_end
            for _ in range(k):
                if not group_end.next:
                    return dummy.next  # Fewer than k nodes, stop reversing.
                group_end = group_end.next

            # 2. Store the next group's start.
            next_group_start = group_end.next

            # 3. Disconnect the current k-group.
            group_end.next = None

            # 4. Reverse the k-group.
            # The `_reverse_list` function returns the new head of the reversed list.
            # After reversal, `group_start` becomes the new tail of this reversed group.
            new_group_head = self._reverse_list(group_start)

            # 5. Connect the reversed k-group back to the main list.
            prev_group_end.next = new_group_head  # Connect previous part to new head.
            group_start.next = next_group_start   # Connect new tail to next part.

            # 6. Update `prev_group_end` to the end of the just-reversed group (which is `group_start`).
            prev_group_end = group_start

    def _reverse_list(self, head: ListNode) -> ListNode:
        """
        Helper function to reverse a single linked list.
        Returns the new head of the reversed list.
        """
        prev = None
        curr = head
        while curr:
            next_temp = curr.next
            curr.next = prev
            prev = curr
            curr = next_temp
        return prev
