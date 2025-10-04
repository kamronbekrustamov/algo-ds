/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * Adds two numbers represented by linked lists. The digits are stored in
     * reverse order, and each node contains a single digit.
     *
     * This solution simulates the manual process of adding two numbers,
     * digit by digit, from right to left, while handling any carry-over.
     *
     * Algorithm Breakdown:
     * 1. Initialize a `dummyHead` node (with value 0) to simplify the creation
     *    of the result linked list. A `currentNode` pointer will traverse this
     *    new list.
     * 2. Initialize a `carry` variable to 0.
     * 3. Loop as long as there are digits remaining in either `l1` or `l2`,
     *    or if there's a `carry` from the previous addition.
     * 4. In each iteration:
     *    a. Get the value of the current digit from `l1` (or 0 if `l1` is null).
     *    b. Get the value of the current digit from `l2` (or 0 if `l2` is null).
     *    c. Calculate the `sum` of these two digits plus the `carry`.
     *    d. Update the `carry` for the next iteration (`sum / 10`).
     *    e. Create a new `ListNode` with the digit `sum % 10` and append it
     *       to the `currentNode.next` of the result list.
     *    f. Advance the `currentNode` pointer to the newly created node.
     *    g. Advance `l1` and `l2` pointers to their next nodes (if not null).
     * 5. After the loop, the `dummyHead.next` will point to the head of the
     *    result linked list.
     *
     * Time Complexity: O(max(N, M)), where N and M are the lengths of `l1` and `l2`
     *                  respectively, as we iterate through both lists once.
     * Space Complexity: O(max(N, M)), as the length of the new linked list can be
     *                   at most `max(N, M) + 1`.
     *
     * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the linked list representing the sum of the two numbers.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. Initialize a dummy head node to simplify result list construction.
        // `currentNode` will be used to traverse and build the new list.
        ListNode dummyHead = new ListNode(0);
        ListNode currentNode = dummyHead;
        
        // 2. Initialize carry to 0.
        int carry = 0;
        
        // 3. Loop as long as there are digits in either list or a carry exists.
        while (l1 != null || l2 != null || carry > 0) {
            // 4a. Get the value of the current digit from l1. If l1 is null, treat as 0.
            int val1 = (l1 != null) ? l1.val : 0;
            // 4b. Get the value of the current digit from l2. If l2 is null, treat as 0.
            int val2 = (l2 != null) ? l2.val : 0;
            
            // 4c. Calculate the sum of the current digits and the carry.
            int sum = val1 + val2 + carry;
            
            // 4d. Update the carry for the next iteration.
            carry = sum / 10;
            
            // 4e. Create a new node with the digit (sum % 10) and append it to the result list.
            currentNode.next = new ListNode(sum % 10);
            
            // 4f. Advance the `currentNode` pointer to the newly added node.
            currentNode = currentNode.next;
            
            // 4g. Advance l1 and l2 pointers to their next nodes if they exist.
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        
        // 5. Return the next node of dummyHead, which is the actual head of the result list.
        return dummyHead.next;
    }
}
