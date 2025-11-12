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
     * Checks if a binary tree is a valid Binary Search Tree (BST).
     *
     * @param root The root node of the binary tree.
     * @return true if the tree is a valid BST, false otherwise.
     */
    public boolean isValidBST(TreeNode root) {
        // Start the recursive validation with no bounds (null represents -infinity and +infinity).
        // This correctly handles all possible integer values, including MIN_VALUE and MAX_VALUE.
        return validate(root, null, null);
    }

    /**
     * Helper function to recursively validate the tree within a given range.
     *
     * A node is valid if its value is strictly between the min and max bounds
     * imposed by its ancestors.
     *
     * @param node The current node to validate.
     * @param min The *exclusive* lower bound (node.val must be > min). A null value means no lower bound.
     * @param max The *exclusive* upper bound (node.val must be < max). A null value means no upper bound.
     * @return true if the subtree rooted at 'node' is valid, false otherwise.
     */
    private boolean validate(TreeNode node, Integer min, Integer max) {
        // Base case: An empty (null) node is a valid BST.
        if (node == null) {
            return true;
        }

        // Check 1: Validate against the lower bound.
        // If 'min' is not null, the node's value must be strictly greater than it.
        if (min != null && node.val <= min) {
            return false;
        }

        // Check 2: Validate against the upper bound.
        // If 'max' is not null, the node's value must be strictly less than it.
        if (max != null && node.val >= max) {
            return false;
        }

        // Recursively check the left and right subtrees, tightening the bounds.
        return
            // For the left child, the new *max* bound is this node's value.
            validate(node.left, min, node.val) &&
            // For the right child, the new *min* bound is this node's value.
            validate(node.right, node.val, max);
    }
}