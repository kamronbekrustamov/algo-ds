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

    /** Tracks the maximum path length found so far (in number of edges). */
    private int maxPathLength = 0;

    /**
     * Finds the length of the longest path where each node in the path has the same value.
     * The length of the path is defined by the number of edges between the nodes.
     *
     * @param root The root of the binary tree.
     * @return The number of edges in the longest univalue path.
     */
    public int longestUnivaluePath(TreeNode root) {
        maxPathLength = 0; // Reset global state
        calculateUnivalueEdgeLength(root);
        return maxPathLength;
    }

    /**
     * A post-order DFS traversal that calculates the length of the univalue path 
     * extending downwards from the current node.
     * * Side Effect: Updates the global 'maxPathLength' if the path passing *through* * the current node (left child -> node -> right child) is the longest seen so far.
     *
     * @param node The current node being visited.
     * @return The length (edges) of the longest univalue branch extending from this node down to a leaf.
     */
    private int calculateUnivalueEdgeLength(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively get the univalue path lengths from children
        int leftLen = calculateUnivalueEdgeLength(node.left);
        int rightLen = calculateUnivalueEdgeLength(node.right);

        // Check if children extend the path from the current node
        int leftArrow = 0;
        int rightArrow = 0;

        // If left child exists and matches value, extend the left path by 1 edge
        if (node.left != null && node.left.val == node.val) {
            leftArrow = leftLen + 1;
        }

        // If right child exists and matches value, extend the right path by 1 edge
        if (node.right != null && node.right.val == node.val) {
            rightArrow = rightLen + 1;
        }

        // Update the global maximum. This accounts for the path that goes:
        // Left Child <-> Current Node <-> Right Child
        maxPathLength = Math.max(maxPathLength, leftArrow + rightArrow);

        // Return the longest single branch extending downwards from this node
        // (This allows the parent of the current node to potentially extend the path further)
        return Math.max(leftArrow, rightArrow);
    }
}