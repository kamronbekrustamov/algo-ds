/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    /**
     * Copies a linked list with next and random pointers.
     * This method uses an O(1) space, three-pass approach to avoid a HashMap.
     * * Time Complexity: O(N), where N is the number of nodes. (Three passes over the list).
     * Space Complexity: O(1), excluding the space for the new list.
     *
     * @param head The head of the original linked list.
     * @return The head of the new, deep-copied linked list.
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // --- Pass 1: Interweave the new nodes into the original list ---
        // For each original node 'curr', create a copy and place it right after 'curr'.
        // Original: N1 -> N2 -> N3
        // After Pass 1: N1 -> N1_copy -> N2 -> N2_copy -> N3 -> N3_copy
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next; // copy.next points to N(i+1)
            curr.next = copy;      // curr.next now points to N(i)_copy
            curr = copy.next;      // Advance 'curr' to the original next node N(i+1)
        }

        // --- Pass 2: Set the 'random' pointers for the new nodes ---
        // If an original node 'curr' has a random pointer to 'R', then its copy 
        // 'curr.next' must have a random pointer to 'R.next' (which is R_copy).
        curr = head;
        while (curr != null) {
            Node copy = curr.next;
            if (curr.random != null) {
                // curr.random is the original node R. R.next is the copy node R_copy.
                copy.random = curr.random.next; 
            }
            // Advance to the next original node (skip over the copy)
            curr = curr.next.next; 
        }

        // --- Pass 3: Separate the original and new lists and restore the original list ---
        Node newHead = head.next;
        Node newCurr = newHead;
        curr = head;

        while (curr != null) {
            // Restore original link: N(i) -> N(i+1)
            curr.next = newCurr.next;
            curr = curr.next; // Advance original pointer

            if (curr != null) {
                // Separate new list link: N(i)_copy -> N(i+1)_copy
                newCurr.next = curr.next;
                newCurr = newCurr.next; // Advance new pointer
            }
        }

        return newHead;
    }
}