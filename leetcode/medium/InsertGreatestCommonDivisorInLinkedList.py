from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def insertGreatestCommonDivisors(self, head: Optional[ListNode]) -> Optional[ListNode]:
        """
        Inserts a new node containing the greatest common divisor (GCD)
        between every pair of adjacent nodes in a singly-linked list.

        Args:
            head: The head of the singly-linked list.

        Returns:
            The head of the modified singly-linked list with GCD nodes inserted.
        """

        # If the list is empty, there's nothing to do.
        if not head:
            return None

        def gcd(num1: int, num2: int) -> int:
            """
            Calculates the greatest common divisor (GCD) of two non-negative integers
            using the Euclidean algorithm.

            Args:
                num1: The first integer.
                num2: The second integer.

            Returns:
                The greatest common divisor of num1 and num2.
            """
            while num2: # Continue as long as num2 is not zero
                num1, num2 = num2, num1 % num2 # Apply the Euclidean algorithm
            return num1 # When num2 is zero, num1 is the GCD
        
        curr = head
        # 'next_node' is used to avoid shadowing the built-in 'next' function.
        next_node = head.next 

        # Traverse the linked list until 'next_node' becomes None (end of list).
        while next_node:
            # Calculate the GCD of the current node's value and the next node's value.
            gcd_val = gcd(curr.val, next_node.val)
            
            # Create a new ListNode with the calculated GCD value.
            new_gcd_node = ListNode(gcd_val)
            
            # Insert the new GCD node between 'curr' and 'next_node'.
            curr.next = new_gcd_node
            new_gcd_node.next = next_node
            
            # Move 'curr' to the 'next_node' (skipping the newly inserted GCD node)
            # to prepare for the next pair of adjacent nodes.
            curr = next_node
            # Move 'next_node' to the node after the current 'next_node'.
            next_node = next_node.next

        return head