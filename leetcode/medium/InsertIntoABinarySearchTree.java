/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    /**
     * Inserts a new value into a Binary Search Tree (BST) while maintaining the BST property.
     *
     * This implementation uses an iterative approach to find the insertion location, 
     * which optimizes space complexity by avoiding the recursion call stack overhead.
     *
     * Time Complexity: O(h), where h is the height of the tree.
     * - O(log n) for a balanced tree, O(n) for a skewed (worst-case) tree.
     * Space Complexity: O(1), as only a constant number of pointers are used (no recursion stack).
     *
     * @param root The root of the BST. Can be null if the tree is empty.
     * @param val The value to be inserted into the BST.
     * @return The root of the BST after insertion.
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If the tree is empty, create a new node and return it as the root.
        if (root == null) {
            return new TreeNode(val);
        }

        // Initialize pointers for traversal.
        TreeNode current = root;
        TreeNode parent = null;

        // Traverse the tree to find the correct leaf node location.
        while (current != null) {
            parent = current;
            
            // Move left if the new value is smaller.
            if (val < current.val) {
                current = current.left;
            } 
            // Move right if the new value is larger or equal (BST standard).
            else {
                current = current.right;
            }
        }

        // 'parent' is now the node to which the new node must be attached.
        TreeNode newNode = new TreeNode(val);
        
        // Determine whether to attach the new node to the left or right of 'parent'.
        if (val < parent.val) {
            parent.left = newNode; // Attach as the left child.
        } else {
            parent.right = newNode; // Attach as the right child.
        }

        // Return the original root.
        return root;
    }
}