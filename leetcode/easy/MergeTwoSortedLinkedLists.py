from typing import Optional


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        """
        Merges two sorted linked lists into one single sorted linked list.

        This solution uses an iterative approach with a dummy head node to build
        the new list. This avoids handling the edge case of an empty result list.

        Algorithm:
        1. Create a `dummy` node to serve as the starting point of the merged list.
        2. Create a `current` pointer, initialized to `dummy`, to track the tail of the merged list.
        3. Loop as long as both `list1` and `list2` have nodes.
        4. In each iteration, compare the values of the current nodes of `list1` and `list2`.
        5. Append the smaller node to `current.next` and advance the pointer of the list from which the node was taken.
        6. Move the `current` pointer to the newly added node.
        7. After the loop, one of the lists may still have remaining nodes. Append the
           non-empty list to the end of the merged list.
        8. Return `dummy.next`, which is the head of the merged list.

        Time Complexity: O(m + n), where m and n are the lengths of the two lists.
        Space Complexity: O(1), as we are just rearranging pointers, not creating new nodes.
        """
        # A dummy node acts as a placeholder to simplify the code,
        # avoiding edge cases for an empty merged list.
        dummy = ListNode()
        # `current` points to the last node in the merged list.
        current = dummy

        # Loop until one of the lists becomes empty.
        while list1 and list2:
            if list1.val < list2.val:
                # Append the smaller node from list1.
                current.next = list1
                # Move the list1 pointer forward.
                list1 = list1.next
            else:
                # Append the smaller or equal node from list2.
                current.next = list2
                # Move the list2 pointer forward.
                list2 = list2.next
            # Move the `current` pointer to the newly added node.
            current = current.next

        # Append the remaining part of the non-empty list.
        # This also correctly handles cases where one or both lists were initially empty.
        current.next = list1 or list2

        # The merged list starts at the node after the dummy head.
        return dummy.next