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
     * Inserts a new node containing the Greatest Common Divisor (GCD) 
     * between every two adjacent nodes in a linked list.
     * * Time Complexity: O(N * log(min(a, b))), where N is the number of nodes.
     * Space Complexity: O(1) extra space (excluding the nodes created).
     */
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        // Base case: If list is empty or has only one node, no pairs exist.
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;

        // Traverse until we reach the last node
        while (curr.next != null) {
            // Calculate GCD of the current pair
            int gcdValue = calculateGCD(curr.val, curr.next.val);
            
            // Create and insert the new node
            // curr -> newNode -> curr.next
            ListNode newNode = new ListNode(gcdValue, curr.next);
            curr.next = newNode;

            // Move pointer to the node that was originally 'next'
            // We skip the newly inserted node.
            curr = newNode.next;
        }

        return head;
    }

    /**
     * Helper to find GCD using the Euclidean Algorithm (Iterative).
     */
    private int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}