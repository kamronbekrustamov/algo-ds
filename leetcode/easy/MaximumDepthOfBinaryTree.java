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

/**
 * Solution for LeetCode problem "Maximum Depth of Binary Tree".
 * This class provides a method to calculate the maximum depth of a binary tree.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
class Solution {

    /**
     * Calculates the maximum depth of a binary tree using a recursive Depth-First Search (DFS) approach.
     *
     * The depth of a node is the number of edges from the root to the node.
     * The height of a node is the number of edges on the longest path from the node to a leaf.
     * The maximum depth of the tree is the height of the root node.
     *
     * The recursive definition is:
     * - If the root is null, the depth is 0.
     * - Otherwise, the depth is 1 (for the current node) plus the maximum of the depths of its left and right subtrees.
     *
     * @param root The root of the binary tree.
     * @return The maximum depth of the binary tree.
     */
    public int maxDepth(TreeNode root) {
        // Base case: If the current node is null, it means we've gone past a leaf node,
        // so its contribution to depth is 0.
        if (root == null) {
            return 0;
        }

        // Recursively calculate the maximum depth of the left subtree.
        int left_depth = maxDepth(root.left);
        // Recursively calculate the maximum depth of the right subtree.
        int right_depth = maxDepth(root.right);

        // The maximum depth of the current tree is 1 (for the current node) plus
        // the maximum depth found in its left or right subtrees.
        return 1 + Math.max(left_depth, right_depth);
    }
}
