from typing import Optional


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        """
        Determines if a linked list has a cycle in it.

        This solution uses Floyd's Tortoise and Hare algorithm, a classic
        and highly efficient method for cycle detection.

        The core idea is to use two pointers:
        1. A 'slow' pointer that moves one step at a time.
        2. A 'fast' pointer that moves two steps at a time.

        If there is a cycle, the fast pointer will eventually "lap" the slow
        pointer, and they will meet at the same node. If there is no cycle,
        the fast pointer will reach the end of the list (None).
        """
        slow_pointer, fast_pointer = head, head

        # The loop continues as long as the fast pointer and the node after it are valid.
        while fast_pointer and fast_pointer.next:
            slow_pointer = slow_pointer.next
            fast_pointer = fast_pointer.next.next
            if slow_pointer == fast_pointer:
                return True  # Cycle detected

        return False  # No cycle found