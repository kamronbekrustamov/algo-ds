/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Solution for LeetCode problem "Linked List Cycle".
 * This class provides a method to detect if a cycle exists in a singly-linked list.
 */
class Solution {

    /**
     * Detects if a cycle is present in the given singly-linked list using Floyd's Cycle-Finding Algorithm
     * (also known as the "tortoise and hare" algorithm).
     *
     * The algorithm uses two pointers, a 'slow' pointer and a 'fast' pointer.
     * The slow pointer moves one step at a time, while the fast pointer moves two steps at a time.
     * If there is a cycle in the linked list, the fast pointer will eventually catch up to the slow pointer.
     * If there is no cycle, the fast pointer (or fast.next) will reach the end of the list (null).
     *
     * @param head The head of the singly-linked list.
     * @return true if a cycle is detected, false otherwise.
     */
    public boolean hasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer) {
                return true;
            }
        }

        // If the loop finishes, it means the fast pointer reached the end of the list,
        // indicating no cycle was found.
        return false;
    }
}
