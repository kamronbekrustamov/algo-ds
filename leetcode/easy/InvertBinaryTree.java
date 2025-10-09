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
 * Solution class for the Invert Binary Tree problem.
 */
class Solution {
    /**
     * Inverts a binary tree by swapping the left and right children of every node.
     * This is solved using a recursive, depth-first approach.
     *
     * @param root The root of the binary tree to invert.
     * @return The root of the inverted binary tree.
     */
    public TreeNode invertTree(TreeNode root) {
        // Base case: If the node is null, we've reached the end of a branch.
        if (root == null) {
            return null;
        }

        // Recursively call invertTree on the original left and right children.
        // It's important to complete the recursive calls before swapping the children
        // of the current node.
        TreeNode invertedRight = invertTree(root.right);
        TreeNode invertedLeft = invertTree(root.left);

        // Swap the left and right children of the current node.
        root.left = invertedRight;
        root.right = invertedLeft;

        return root;
    }
}
