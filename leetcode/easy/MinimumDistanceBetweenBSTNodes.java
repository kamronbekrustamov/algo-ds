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
 * Solution for LeetCode problem "Minimum Distance Between BST Nodes".
 * This class provides a method to find the minimum difference between the values of any two different nodes in a Binary Search Tree (BST).
 */
class Solution {
    // `prev_val` stores the value of the previously visited node during in-order traversal.
    // Initialized to null to handle the first node.
    private Integer prev_val;
    // `min_diff` stores the minimum difference found so far.
    // Initialized to a very large value.
    private int min_diff;

    /**
     * Finds the minimum difference between the values of any two different nodes in a Binary Search Tree (BST).
     *
     * The key property of a BST is that an in-order traversal visits nodes in ascending order of their values.
     * Therefore, the minimum difference between any two nodes must occur between adjacent nodes in the in-order traversal sequence.
     *
     * This method performs an in-order traversal of the BST, keeping track of the previously visited node's value.
     * At each step, it calculates the difference between the current node's value and the previous node's value,
     * and updates the `min_diff` if a smaller difference is found.
     *
     * @param root The root of the Binary Search Tree.
     * @return The minimum difference between the values of any two different nodes in the BST.
     */
    public int minDiffInBST(TreeNode root) {
        // Reset `prev_val` and `min_diff` for each new call to `minDiffInBST`.
        prev_val = null;
        min_diff = Integer.MAX_VALUE;
        // Start the in-order traversal from the root.
        inorder_traverse(root);
        return min_diff;
    }

    /**
     * Performs an in-order traversal of the BST to find the minimum difference between adjacent node values.
     *
     * @param node The current node being visited in the traversal.
     */
    private void inorder_traverse(TreeNode node) {
        // Base case: if the current node is null, return.
        if (node == null) {
            return;
        }

        // Traverse the left subtree.
        inorder_traverse(node.left);

        // Process the current node:
        // If `prev_val` is not null (meaning this is not the very first node visited),
        // calculate the difference and update `min_diff`.
        if (prev_val != null) {
            min_diff = Math.min(min_diff, node.val - prev_val);
        }
        // Update `prev_val` to the current node's value for the next comparison.
        prev_val = node.val;

        // Traverse the right subtree.
        inorder_traverse(node.right);
    }
}
