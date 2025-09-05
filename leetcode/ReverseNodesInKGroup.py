from typing import Optional


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        temp = head
        count = 0
        while temp:
            count += 1
            temp = temp.next

        prev_node = None
        curr_node = head

        for i in range(count - count % k):
            next_node = curr_node.next
            curr_node.next = prev_node
            prev_node = curr_node
            curr_node = next_node
            

        curr_node, prev_node = prev_node, curr_node
        for i in range(count // k):
            temp = curr_node
            for j in range(k):
                if j % k == k - 1:
                    next_node = temp.next
                    temp.next = prev_node
                    prev_node = curr_node
                    curr_node = next_node
                else:
                    temp = temp.next
        return prev_node



