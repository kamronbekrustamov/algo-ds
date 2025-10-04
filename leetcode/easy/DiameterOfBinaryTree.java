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
    // This variable will store the maximum diameter found anywhere in the tree.
    // It's initialized to 0 because a single node has a diameter of 0.
    private int maxDiameter = 0;

    /**
     * Calculates the diameter of a binary tree.
     * The diameter of a binary tree is the length of the longest path between
     * any two nodes in the tree. This path may or may not pass through the root.
     *
     * This solution uses a single-pass Depth-First Search (DFS) approach.
     * For each node, we recursively calculate the height of its left and right
     * subtrees. While doing so, we also calculate the diameter passing through
     * the current node and update a global maximum diameter.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each
     *                  node is visited exactly once.
     * Space Complexity: O(H), where H is the height of the tree, for the recursion
     *                   stack. In the worst case (a skewed tree), this can be O(N).
     *
     * @param root The root node of the binary tree.
     * @return The length of the longest path between any two nodes.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // Reset maxDiameter for each new call to ensure correctness if called multiple times.
        maxDiameter = 0;
        // Call the helper function which calculates height and updates maxDiameter.
        calculateHeight(root);
        return maxDiameter;
    }

    /**
     * A recursive helper function that calculates the height of a subtree
     * and simultaneously updates the maximum diameter found so far.
     *
     * This function performs a post-order traversal: it first processes children
     * to get their heights, then processes the current node.
     *
     * @param node The current node being processed.
     * @return The height of the subtree rooted at the current node.
     */
    private int calculateHeight(TreeNode node) {
        // Base case: An empty tree has a height of 0.
        if (node == null) {
            return 0;
        }

        // Recursively calculate the height of the left subtree.
        int leftHeight = calculateHeight(node.left);
        // Recursively calculate the height of the right subtree.
        int rightHeight = calculateHeight(node.right);

        // Calculate the diameter passing through the current node.
        // This is the sum of the heights of its left and right subtrees.
        // Update the global maximum diameter if this path is longer.
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return the height of the current node's subtree.
        // The height of a node is 1 (for the node itself) + the maximum height
        // of its left or right subtree.
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
