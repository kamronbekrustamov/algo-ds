# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        dummy_head = ListNode(0)
        current = dummy_head
        carry = 0

        # Loop until we've processed all digits from both lists and any final carry.
        while l1 or l2 or carry:
            # Get the values of the current nodes, or 0 if a list is exhausted.
            val1 = l1.val if l1 else 0
            val2 = l2.val if l2 else 0

            # Use divmod to get both the new carry and the digit in one step.
            # It's a Pythonic way to get both quotient (carry) and remainder (digit).
            carry, digit = divmod(val1 + val2 + carry, 10)

            # Create a new node with the calculated digit and append it.
            current.next = ListNode(digit)
            current = current.next

            # Move to the next nodes in the input lists, if they exist.
            l1 = l1.next if l1 else None
            l2 = l2.next if l2 else None

        return dummy_head.next