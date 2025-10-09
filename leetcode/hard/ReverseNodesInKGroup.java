// class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

class Solution {
    /**
     * Reverses the nodes of a linked list k at a time and returns the modified list's head.
     * If the number of nodes is not a multiple of k, then left-out nodes in the end should remain as is.
     *
     * This solution uses an iterative approach with a dummy node to simplify edge cases.
     * It identifies each k-group, reverses it, and then connects it back to the main list.
     *
     * Algorithm:
     * 1. Create a dummy node and point its `next` to the `head` of the original list.
     *    This dummy node simplifies handling the new head of the list.
     * 2. Initialize `prevGroupEnd` to the dummy node. This pointer will always point
     *    to the node *before* the current k-group being processed.
     * 3. Loop indefinitely until the end of the list is reached:
     *    a. Identify the start of the current k-group (`groupStart = prevGroupEnd.next`).
     *    b. Find the end of the current k-group (`groupEnd`). Traverse `k-1` steps from `groupStart`.
     *       If `groupEnd` becomes `null` before `k-1` steps, it means there are fewer than `k` nodes
     *       remaining, so break the loop (these nodes should not be reversed).
     *    c. Store the node immediately after the current k-group (`nextGroupStart = groupEnd.next`).
     *    d. Disconnect the k-group from the rest of the list: `groupEnd.next = null`.
     *    e. Reverse the k-group: Call a helper function `reverseList(groupStart)` which
     *       reverses the sublist and returns its new head (which was the original `groupEnd`)
     *       and its new tail (which was the original `groupStart`).
     *    f. Connect the reversed k-group back:
     *       - `prevGroupEnd.next` should point to the new head of the reversed group.
     *       - The new tail of the reversed group should point to `nextGroupStart`.
     *    g. Update `prevGroupEnd` to the new tail of the reversed group (which is `groupStart`).
     * 4. Return `dummy.next`, which is the head of the fully modified list.
     *
     * @param head The head of the linked list.
     * @param k The size of the group to reverse.
     * @return The head of the modified linked list.
     *
     * Time Complexity: O(N), where N is the total number of nodes in the linked list.
     *                  Each node is visited and modified a constant number of times.
     * Space Complexity: O(1), as we only use a few extra pointers.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode prevGroupEnd = dummy;

        while (true) {
            // 1. Check if there are at least k nodes remaining to reverse.
            ListNode groupStart = prevGroupEnd.next;
            ListNode groupEnd = prevGroupEnd;
            for (int i = 0; i < k; i++) {
                if (groupEnd.next == null) {
                    return dummy.next; // Fewer than k nodes, stop reversing.
                }
                groupEnd = groupEnd.next;
            }

            // 2. Store the next group's start.
            ListNode nextGroupStart = groupEnd.next;

            // 3. Disconnect the current k-group.
            groupEnd.next = null;

            // 4. Reverse the k-group.
            // The `reverseList` function returns the new head of the reversed list.
            // After reversal, `groupStart` becomes the new tail of this reversed group.
            ListNode newGroupHead = reverseList(groupStart);

            // 5. Connect the reversed k-group back to the main list.
            prevGroupEnd.next = newGroupHead; // Connect previous part to new head.
            groupStart.next = nextGroupStart;   // Connect new tail to next part.

            // 6. Update `prevGroupEnd` to the end of the just-reversed group (which is `groupStart`).
            prevGroupEnd = groupStart;
        }
    }

    /**
     * Helper function to reverse a single linked list.
     * Returns the new head of the reversed list.
     *
     * @param head The head of the linked list to reverse.
     * @return The new head of the reversed list.
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
