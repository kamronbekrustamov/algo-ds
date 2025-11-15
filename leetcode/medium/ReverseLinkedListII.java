/**
 * Definition for singly-linked list (provided for context).
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * Reverses the nodes of the linked list from position 'left' to position 'right' (1-indexed).
     *
     * @param head The head of the singly-linked list.
     * @param left The starting position (1-indexed) of the sub-list to reverse.
     * @param right The ending position (1-indexed) of the sub-list to reverse.
     * @return The head of the modified linked list.
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 1. Create a dummy node to handle the case where the reversal starts at the head (left = 1).
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 2. Locate the node *before* the start of the reversal (prevNode)
        // and the node *at* the start of the reversal (leftNode).
        // prevNode will be the node whose 'next' pointer must be updated later.
        ListNode prevNode = dummy; // Node *before* the 'left' position
        ListNode leftNode = head;  // Node *at* the 'left' position

        // Traverse to the node just before the 'left' position.
        // The loop runs 'left - 1' times.
        for (int i = 1; i < left; i++) {
            prevNode = prevNode.next;
            leftNode = leftNode.next;
        }

        // At this point:
        // prevNode is the node just before the sub-list to be reversed.
        // leftNode is the starting node of the sub-list (the one that will end up at the 'right' position).

        // 3. Reverse the sub-list from 'left' to 'right'.
        // This uses the standard iterative reversal with three pointers (prev, curr, next).
        
        ListNode prev = null; // Used to build the reversed list (will become the new 'left' node)
        ListNode curr = leftNode; // Current node being processed
        
        // Loop runs 'right - left + 1' times (the length of the sub-list).
        for (int i = left; i <= right; i++) {
            ListNode next = curr.next; // Store the next node before overwriting curr.next
            curr.next = prev;          // Reverse the pointer
            prev = curr;               // Move 'prev' one step forward
            curr = next;               // Move 'curr' one step forward
        }

        // After the loop:
        // 'prev' is the new head of the reversed sub-list (the node that was originally at 'right').
        // 'curr' is the node *after* the reversed sub-list (the node that was originally at 'right' + 1).

        // 4. Connect the three parts: (1) Head part, (2) Reversed part, (3) Tail part.
        
        // a. Connect the node *before* the reversed section (prevNode) to the *new head* of the reversed section (prev).
        prevNode.next = prev;

        // b. Connect the *new tail* of the reversed section (leftNode, which was the original 'left' node)
        // to the node *after* the reversed section (curr).
        leftNode.next = curr;

        // 5. Return the head of the list (which is dummy.next).
        return dummy.next;
    }
}