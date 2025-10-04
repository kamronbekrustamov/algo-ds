/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Solution class for the "Delete Node in a Linked List" problem.
 *
 * The core challenge of this problem is that we are not given access to the head of the list,
 * only a direct reference to the node that needs to be removed. This prevents us from
 * traversing the list to find the *previous* node, which is the standard way to delete a node.
 */
class Solution {
    /**
     * Deletes a node from a singly-linked list, given only a reference to that node.
     *
     * This method employs a clever trick to solve the problem without access to the head.
     * Since we cannot update the `next` pointer of the *previous* node, we instead
     * transform the current node into the next node and then delete the original next node.
     *
     * Algorithm:
     * 1. Copy the value from the next node (`node.next`) into the current node (`node`).
     * 2. Update the current node's `next` pointer to skip over the next node, effectively
     *    removing it from the list.
     *
     * For example, given the list `4 -> 5 -> 9` and `node` is the one with value `5`:
     *   - After step 1, the list conceptually becomes `4 -> 9 -> 9`.
     *   - After step 2, the list becomes `4 -> 9`. The node containing `5` is gone.
     *
     * The problem guarantees that the node to be deleted is not the tail of the list,
     * which ensures that `node.next` is always a valid, non-null reference.
     *
     * Time Complexity: O(1) - The operation performs a constant number of steps, regardless of the list's size.
     * Space Complexity: O(1) - No additional data structures are used that scale with input size.
     *
     * @param node The node to be deleted from the linked list. It is guaranteed not to be the tail node.
     */
    public void deleteNode(ListNode node) {
        // --- Step 1: Copy the data from the subsequent node ---
        // Overwrite the value of the current node with the value of its next node.
        // After this line, the current node is a "clone" of the next node in terms of value.
        node.val = node.next.val;

        // --- Step 2: Remove the subsequent node from the list ---
        // Update the current node's 'next' pointer to point to the node *after* the next one.
        // This bypasses the original next node, making it eligible for garbage collection.
        // The list is now re-linked, and the target node is effectively removed.
        node.next = node.next.next;
    }
}