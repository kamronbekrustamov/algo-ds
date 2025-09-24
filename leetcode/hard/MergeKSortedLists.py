from typing import List, Optional

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        """
        Merges k sorted linked lists into one sorted linked list using a
        divide and conquer approach.
        """
        if not lists:
            return None
        
        num_lists = len(lists)
        
        # The loop continues as long as we have more than one list to merge.
        # In each iteration, we merge adjacent pairs of lists, reducing the
        # total number of lists by about half.
        while num_lists > 1:
            # Iterate through the current lists, taking them two at a time.
            for i in range(0, num_lists, 2):
                list1 = lists[i]
                
                # If there's a second list in the pair, get it. Otherwise, it's None.
                list2 = lists[i + 1] if (i + 1) < num_lists else None
                
                # Merge the pair and store the result back into the first slot
                # of the pair's original position in the list array.
                # e.g., merge(lists[0], lists[1]) -> lists[0]
                #       merge(lists[2], lists[3]) -> lists[1]
                lists[i // 2] = self.merge_two_lists(list1, list2)
            
            # Calculate the new number of lists for the next iteration.
            # This is equivalent to ceil(num_lists / 2).
            num_lists = (num_lists + 1) // 2

        # After the loop, the final merged list is at the first position.
        return lists[0]
    

    def merge_two_lists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        """Helper function to merge two sorted linked lists."""
        # Base cases: if one list is empty, return the other.
        if not list1:
            return list2
        if not list2:
            return list1
        
        # Use a dummy node to simplify the head of the new list.
        dummy = ListNode()
        # `current` will be the tail of the merged list as we build it.
        current = dummy
        
        # Traverse both lists, appending the smaller node to the merged list.
        while list1 and list2:
            if list1.val < list2.val:
                current.next = list1
                list1 = list1.next
            else:
                current.next = list2
                list2 = list2.next
            current = current.next
            
        # At least one list is now exhausted. Append the remainder of the
        # non-empty list to the end of the merged list.
        if list1:
            current.next = list1
        elif list2:
            current.next = list2
            
        # The merged list starts at dummy.next.
        return dummy.next
