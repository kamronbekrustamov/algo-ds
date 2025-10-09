// class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

class Solution {
    /**
     * Reverses a singly linked list iteratively.
     *
     * This solution uses an iterative approach with two pointers:
     * `prev_node`: the node that comes before the current one (starts as null).
     * `current_node`: the node we are currently processing (starts as head).
     *
     * Algorithm:
     * 1. Initialize `prev_node` to `null` and `current_node` to `head`.
     * 2. Loop as long as `current_node` is not `null`:
     *    a. Store the `next_node` of `current_node` before modifying `current_node.next`.
     *    b. Reverse the `next` pointer of `current_node` to point to `prev_node`.
     *    c. Move `prev_node` to `current_node`.
     *    d. Move `current_node` to `next_node`.
     * 3. After the loop, `current_node` will be `null`, and `prev_node` will be the new head of the reversed list.
     *
     * @param head The head of the singly linked list.
     * @return The new head of the reversed linked list.
     *
     * Time Complexity: O(N), where N is the number of nodes in the linked list, as we traverse the list once.
     * Space Complexity: O(1), as we only use a few extra pointers.
     */
    public ListNode reverseList(ListNode head) {
        ListNode prevNode = null;
        ListNode currentNode = head;

        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }

        return prevNode;
    }
}
