/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Solution for LeetCode problem "Middle of the Linked List".
 * This class provides a method to find the middle node of a singly-linked list.
 */
class Solution {

    /**
     * Finds the middle node of a singly-linked list.
     * If the list has an even number of nodes, the second middle node is returned.
     *
     * This method uses the "fast and slow pointer" approach:
     * - A `slow` pointer moves one step at a time.
     * - A `fast` pointer moves two steps at a time.
     * When the `fast` pointer reaches the end of the list (or `null`), the `slow` pointer
     * will be at the middle of the list.
     *
     * @param head The head of the singly-linked list.
     * @return The middle node of the linked list.
     */
    public ListNode middleNode(ListNode head) {
        // Initialize both slow and fast pointers to the head of the list.
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list with two pointers.
        // The loop continues as long as the fast pointer and its next node are not null,
        // ensuring valid moves for the fast pointer.
        while (fast != null && fast.next != null) {
            // Move slow pointer one step.
            slow = slow.next;
            // Move fast pointer two steps.
            fast = fast.next.next;
        }

        // When the fast pointer reaches the end, the slow pointer will be at the middle node.
        return slow;
    }
}
