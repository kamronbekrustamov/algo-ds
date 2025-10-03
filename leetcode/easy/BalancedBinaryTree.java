/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * Checks if a binary tree is height-balanced.
     * A height-balanced binary tree is a binary tree in which the depth of the
     * two subtrees of every node never differs by more than one.
     *
     * This solution uses a single-pass, post-order traversal (DFS) approach,
     * which is optimal with O(n) time complexity.
     *
     * @param root The root of the binary tree.
     * @return true if the tree is height-balanced, false otherwise.
     */
    public boolean isBalanced(TreeNode root) {
        // The helper function `getHeight` returns the height of the tree if it's balanced,
        // or -1 if it's not. We just need to check the final result.
        return getHeight(root) != -1;
    }

    /**
     * A helper function that performs a post-order traversal to both check for
     * balance and calculate the height of a subtree.
     *
     * This function has a dual purpose:
     * 1. It returns the height of the node if its subtree is balanced.
     * 2. It returns -1 as a special signal if it detects that the subtree is unbalanced.
     *
     * This "early exit" signal (-1) avoids redundant computations that would occur
     * in a naive top-down approach.
     *
     * @param node The root of the subtree to check.
     * @return The height of the subtree if balanced, otherwise -1.
     */
    private int getHeight(TreeNode node) {
        // Base case: An empty tree is balanced and has a height of 0.
        if (node == null) {
            return 0;
        }

        // Recursively find the height of the left subtree.
        // If the left subtree is already unbalanced, propagate the -1 signal up.
        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        // Recursively find the height of the right subtree.
        // If the right subtree is already unbalanced, propagate the -1 signal up.
        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        // Check if the current node is balanced.
        // The difference in heights of its subtrees must not be more than 1.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Signal that the tree is unbalanced at this node.
        }

        // If the current node is balanced, return its height.
        // The height of a node is 1 + the height of its tallest subtree.
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
