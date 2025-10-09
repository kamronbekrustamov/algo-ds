// class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

class Solution {
    /**
     * Removes all the nodes of a linked list that has Node.val == val.
     * This solution uses a dummy head and a single pointer with a lookahead.
     *
     * Algorithm:
     * 1. Create a `dummy_head` node. This node simplifies handling cases where the head
     *    of the original list needs to be removed. Its `next` pointer initially points to the original `head`.
     * 2. Initialize a `current` pointer to `dummy_head`. This pointer will always point
     *    to the node *before* the one we are currently inspecting for removal.
     * 3. Iterate through the list using the `current` pointer, checking `current.next`.
     * 4. If `current.next` (the node to be potentially removed) has a value equal to `val`:
     *    a. Bypass `current.next` by setting `current.next = current.next.next`.
     *       This effectively removes the node from the list without advancing `current`,
     *       as the new `current.next` might also need to be removed.
     * 5. If `current.next` does *not* have a value equal to `val`:
     *    a. Advance `current` one step forward: `current = current.next`.
     * 6. Continue until `current.next` is null, meaning we have processed the entire list.
     * 7. Return `dummy_head.next`, which is the head of the modified list.
     *
     * @param head The head of the singly-linked list.
     * @param val The value to be removed from the list.
     * @return The head of the list after removing all occurrences of `val`.
     *
     * Time Complexity: O(N), where N is the number of nodes in the linked list, as we traverse the list once.
     * Space Complexity: O(1), as we only use a few extra pointers.
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy_head = new ListNode(0, head);
        ListNode current = dummy_head;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return dummy_head.next;
    }
}
