/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

/**
 * Finds the Lowest Common Ancestor (LCA) of two given nodes in a Binary Search Tree (BST).
 * * The Lowest Common Ancestor (LCA) is defined as the lowest node in the tree that has
 * both p and q as descendants (where we allow a node to be a descendant of itself).
 * * This solution leverages the unique properties of a BST for an O(h) time complexity, 
 * where 'h' is the height of the tree (which is O(log n) for a balanced tree, 
 * or O(n) in the worst case). Space complexity is O(1) for the iterative approach.
 * * 
 */
class Solution {
    /**
     * Finds the LCA of nodes p and q in the BST rooted at 'root'.
     * * @param root The root of the BST.
     * @param p One of the two nodes to find the LCA for.
     * @param q The other node to find the LCA for.
     * @return The LCA of p and q.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Start the traversal from the root.
        TreeNode current = root;

        // Iterate until 'current' becomes null (which should not happen if p and q are guaranteed to be in the tree)
        while (current != null) {
            // Case 1: Both p and q are in the left subtree.
            // If the current node's value is greater than *both* p's value and q's value,
            // the LCA must be in the left subtree.
            if (current.val > p.val && current.val > q.val) {
                current = current.left;
            }
            // Case 2: Both p and q are in the right subtree.
            // If the current node's value is less than *both* p's value and q's value,
            // the LCA must be in the right subtree.
            else if (current.val < p.val && current.val < q.val) {
                current = current.right;
            }
            // Case 3: We have found the split point (the LCA).
            // This occurs when:
            // 1. The current node's value is between p.val and q.val (inclusive of the endpoints), OR
            // 2. The current node is either p or q.
            // In either sub-case, the current node is the LCA because p and q are split 
            // across its subtrees (or one of them is the current node itself).
            else {
                return current;
            }
        }

        // Return null if p and q were not found, though the problem typically guarantees 
        // they are present in the tree.
        return null;
    }
}