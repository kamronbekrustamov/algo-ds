/**
 * Definition for singly-linked list.
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
     * Rotates the linked list to the right by k places.
     * Time Complexity: O(n) - where n is the number of nodes (we visit each node max twice).
     * Space Complexity: O(1) - we only use a few pointers.
     */
    public ListNode rotateRight(ListNode head, int k) {
        // Edge case: Empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Compute the length and locate the tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Adjust k and check if rotation is effectively zero
        k = k % length;
        if (k == 0) return head;

        // 3. Close the loop (make it circular)
        tail.next = head;

        // 4. Find the new tail: (length - k - 1) steps from the current head
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        // 5. Set the new head and break the circular link
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}