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
     * Calculates the total sum of all root-to-leaf numbers in a binary tree.
     * Each path from the root to a leaf forms a number by concatenating the node values.
     * * @param root The root node of the binary tree.
     * @return The total sum of all root-to-leaf numbers. Returns 0 if the root is null.
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Start the Depth First Search (DFS) from the root with an initial path sum of 0.
        return dfs(root, 0);
    }

    /**
     * Helper function for Depth First Search (DFS) to traverse the tree and calculate path sums.
     * * @param node The current node being processed.
     * @param currentSum The number formed by the path from the root down to the parent of the current node.
     * @return The sum of all root-to-leaf numbers originating from the current node's subtree.
     */
    private int dfs(TreeNode node, int currentSum) {
        // Base case: Should not happen if called correctly, but ensures safety.
        if (node == null) {
            return 0;
        }

        // 1. Update the number formed by the path up to the current node.
        // Multiply by 10 to shift existing digits and add the current node's value.
        int newSum = currentSum * 10 + node.val;

        // 2. Base Case: If the current node is a leaf, return the newly formed number.
        if (node.left == null && node.right == null) {
            return newSum;
        }

        // 3. Recursive Step: Recursively calculate the sums for the left and right subtrees
        // and return their total.
        return dfs(node.left, newSum) + dfs(node.right, newSum);
    }
}