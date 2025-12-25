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
     * Reorders the singly-linked list in-place to the format L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
     * The process involves three main steps:
     * 1. Find the middle of the linked list using the Tortoise and Hare (slow/fast pointer) approach.
     * 2. Reverse the second half of the list.
     * 3. Merge the first half and the reversed second half alternately.
     *
     * Time Complexity: O(n) - Each of the three steps traverses the list (or half of it) once.
     * Space Complexity: O(1) - All operations are done in-place with a constant number of extra pointers.
     *
     * @param head The head of the singly-linked list to be reordered.
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // --- Step 1: Find the middle of the linked list ---
        // 'slow' will stop at the node just before the start of the second half
        // For a list 1->2->3->4, slow stops at 2. For 1->2->3->4->5, slow stops at 3.
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 'secondHead' is the start of the second half.
        ListNode secondHalfHead = slow.next;
        // Break the list into two halves.
        slow.next = null;
        
        

        // --- Step 2: Reverse the second half of the list ---
        ListNode current = secondHalfHead;
        ListNode previous = null;
        
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = previous; // Reverse the link
            previous = current;      // Move previous one step forward
            current = nextNode;      // Move current one step forward
        }
        
        // 'secondHalfHead' now points to the head of the reversed second half (Ln)
        secondHalfHead = previous;

        

        // --- Step 3: Merge the two halves alternately ---
        ListNode firstHalfCurrent = head; // L0, L1, L2, ...
        ListNode secondHalfCurrent = secondHalfHead; // Ln, Ln-1, Ln-2, ...
        
        while (secondHalfCurrent != null) {
            // Save the next nodes before overwriting links
            ListNode firstHalfNext = firstHalfCurrent.next;
            ListNode secondHalfNext = secondHalfCurrent.next;

            // Reorder: first -> second
            firstHalfCurrent.next = secondHalfCurrent;
            // Reorder: second -> first's next
            secondHalfCurrent.next = firstHalfNext;

            // Move pointers to the saved next nodes
            firstHalfCurrent = firstHalfNext;
            secondHalfCurrent = secondHalfNext;
        }
        // Note: The loop terminates when secondHalfCurrent is null, meaning all nodes
        // from the second half have been merged. The first half pointer (firstHalfCurrent)
        // will naturally be null or point to the node whose 'next' is null (if the list had an odd length).
        // Since 'slow.next' was set to null in Step 1, the new tail of the list is correctly set.
    }
}