/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Solution for LeetCode problem "Merge Two Sorted Lists".
 * This class provides a method to merge two sorted singly-linked lists into a single sorted linked list.
 */
class Solution {

    /**
     * Merges two sorted singly-linked lists into a single sorted linked list.
     * The new list should be made by splicing together the nodes of the first two lists.
     *
     * This method uses an iterative approach with a dummy head node.
     * It compares the values of the current nodes from both lists and appends the smaller one
     * to the merged list, then advances the pointer of the list from which the node was taken.
     * After one list is exhausted, it appends the remaining nodes of the other list.
     *
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The head of the merged sorted linked list.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to simplify the handling of the head of the merged list.
        ListNode dummy = new ListNode();
        // 'current' pointer will be used to build the merged list.
        ListNode current = dummy;

        // Iterate while both lists have nodes.
        while (list1 != null && list2 != null) {
            // Compare the values of the current nodes from both lists.
            if (list1.val < list2.val) {
                // If list1's current node is smaller, append it to the merged list.
                current.next = list1;
                // Move to the next node in list1.
                list1 = list1.next;
            } else {
                // If list2's current node is smaller or equal, append it to the merged list.
                current.next = list2;
                // Move to the next node in list2.
                list2 = list2.next;
            }
            // Advance the 'current' pointer in the merged list.
            current = current.next;
        }

        // If one of the lists still has remaining elements, append them all to the merged list.
        // Since both input lists are sorted, the remaining elements are already in sorted order.
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // The merged list starts from dummy.next (skipping the dummy node itself).
        return dummy.next;
    }
}
