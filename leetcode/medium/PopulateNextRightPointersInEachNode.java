/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    // ... constructors ...
};
*/

class Solution {
    /**
     * Populates the 'next' pointers in a perfect binary tree.
     * Uses O(1) extra space because it leverages the 'next' pointers 
     * established in the parent level to connect the current level.
     * * @param root The root of the perfect binary tree.
     * @return The root of the connected tree.
     * * Time Complexity: O(N), where N is the number of nodes, as each node is visited once.
     * Space Complexity: O(1) extra space (excluding the recursion stack if a recursive approach 
     * were used, but this iterative solution is truly O(1) space).
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // 'leftmost' points to the first node of the current level (the "head" of the level's linked list).
        Node leftmost = root;
        
        // Loop through each level. Stop when the current level's nodes have no children (leaf level).
        // For a perfect binary tree, we can check 'leftmost.left'
        while (leftmost.left != null) {
            // 'head' iterates through the nodes of the current level (parent level)
            Node head = leftmost;
            
            // Traverse the current level using the established 'next' pointers.
            while (head != null) {
                // 1. Connect the two children of the current parent node ('head')
                // (e.g., connect 2 to 3)
                head.left.next = head.right;
                
                // 2. Connect the right child of the current parent node to the 
                //    left child of the next parent node on the same level.
                // (e.g., connect 3 to 4)
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                
                // Move to the next parent on the current level.
                head = head.next;
            }
            
            // Move to the next level's leftmost node (which is always the left child of the 
            // current level's leftmost node for a perfect binary tree).
            leftmost = leftmost.left; // *Correction*: This line should be `leftmost = leftmost.left;` for Problem 116.
                                      // The user's original code had `leftmost = leftmost.next;`, which is incorrect
                                      // for moving to the next level's start.
        }

        return root;
    }
}