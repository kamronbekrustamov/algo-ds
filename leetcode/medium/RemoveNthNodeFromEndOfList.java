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
     * Removes the n-th node from the end of the list and returns the head of the list.
     * * This method uses a two-pointer approach (slow and fast) to solve the problem
     * in a single pass, achieving O(L) time complexity, where L is the length of the list.
     * The slow pointer is maintained at a distance of 'n' nodes behind the fast pointer.
     * When the fast pointer reaches the end of the list, the slow pointer will be 
     * pointing to the node *before* the one to be removed.
     *
     * @param head The head of the singly-linked list.
     * @param n The position from the end of the list (1-indexed) of the node to be removed.
     * @return The head of the modified list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. Create a dummy node to handle edge cases, particularly removing the head.
        // The dummy node's next pointer points to the actual head.
        ListNode dummy = new ListNode(0, head);
        
        // Initialize the fast pointer at the dummy node.
        ListNode fast = dummy;

        // 2. Move the 'fast' pointer 'n' steps ahead.
        // This creates a gap of 'n' nodes between 'slow' (which starts at dummy) and 'fast'.
        // Since 'fast' starts at 'dummy' and we want a gap of 'n' between the *actual* // node to be removed and the end, moving 'n' steps from 'dummy' works perfectly.
        for (int i = 0; i < n; i++) {
            // It's guaranteed that 'n' is valid, so we don't need null checks here
            // based on problem constraints.
            fast = fast.next;
        }

        // Initialize the 'slow' pointer at the dummy node.
        ListNode slow = dummy;
        
        // 3. Move both pointers until the 'fast' pointer reaches the end of the list.
        // We stop when 'fast.next' is null, meaning 'fast' is the last node.
        // This ensures that when the loop terminates, 'slow' is exactly one node 
        // before the target node (the n-th node from the end).
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 4. Skip the target node.
        // 'slow.next' is the node to be removed.
        // 'slow.next.next' is the node *after* the one to be removed (or null).
        slow.next = slow.next.next;

        // 5. The new head of the list is 'dummy.next'.
        // If the original head was removed, 'dummy.next' will now point to the second node.
        // If the original head was not removed, 'dummy.next' is still the original head.
        return dummy.next;
    }
}