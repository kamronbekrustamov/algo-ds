/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    /**
     * Connects all nodes at the same level in a perfect binary tree using the 'next' pointer.
     * This is an optimized level order traversal that uses the 'next' pointers 
     * established in the previous level to process the current level, achieving O(1) extra space complexity.
     * 
     *
     * @param root The root of the binary tree.
     * @return The root of the modified tree.
     */
    public Node connect(Node root) {
        // Base case: if the tree is empty, return null.
        if (root == null) {
            return null;
        }

        // 'leftmost' points to the first node of the current level being processed.
        Node leftmost = root;

        // Iterate while there is a next level to process (i.e., the current level's first node is not null).
        while (leftmost != null) {
            // 'curr' will traverse the nodes of the current level (L) using their 'next' pointers.
            Node curr = leftmost;

            // 'dummy' and 'tail' are used to build the connections for the *next* level (L+1).
            // 'dummy' acts as a sentinel node for the head of the next level.
            Node dummy = new Node();
            // 'tail' tracks the last connected node in the next level (L+1).
            Node tail = dummy;

            // Traverse all nodes in the current level (L).
            while (curr != null) {
                
                // 1. Connect children of the current node (curr)
                
                // Connect the left child to the 'next' level's chain.
                if (curr.left != null) {
                    tail.next = curr.left;
                    tail = tail.next;
                }
                
                // Connect the right child to the 'next' level's chain.
                if (curr.right != null) {
                    tail.next = curr.right;
                    tail = tail.next;
                }
                
                // Move to the next node in the current level (L) via the already established 'next' pointer.
                curr = curr.next;
            }
            
            // After processing the entire level L, the head of the next level (L+1) is dummy.next.
            // Update 'leftmost' to start the traversal for the next level.
            leftmost = dummy.next;
        }
        
        return root;
    }
}