class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private int max_sum;

    /**
     * Finds the maximum path sum in a binary tree. A path can start and end at any
     * node in the tree and must contain at least one node.
     *
     * This solution uses a recursive Depth-First Search (DFS) approach.
     * The core idea is to solve for each node:
     * 1. What is the maximum path sum that can be extended *upwards* to its parent?
     *    This is a "straight" path from the node downwards.
     * 2. What is the maximum path sum that includes the current node as the highest
     *    point (the "root" of the path)? This path can "split" and go down both
     *    left and right subtrees. This value is a candidate for the overall max sum.
     *
     * @param root The root of the binary tree.
     * @return The maximum path sum found in the tree.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each node is visited once.
     * Space Complexity: O(H), where H is the height of the tree, due to the recursion stack.
     *                   In the worst case (skewed tree), H can be N. In the best case (balanced tree), H is logN.
     */
    public int maxPathSum(TreeNode root) {
        max_sum = Integer.MIN_VALUE; // Initialize with smallest possible integer value
        dfs(root);
        return max_sum;
    }

    /**
     * Recursively computes path sums.
     * Returns: The maximum path sum starting at `node` and going downwards.
     * Updates: `this.max_sum` with the maximum path sum found anywhere in the subtree.
     *
     * @param node The current node being processed.
     * @return The maximum path sum that can be extended upwards from `node`.
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Get the max gain from the left and right subtrees.
        // We use Math.max(..., 0) because we don't want to include sub-paths with negative sums.
        int left_gain = Math.max(dfs(node.left), 0);
        int right_gain = Math.max(dfs(node.right), 0);

        // Update the overall maximum path sum. This path includes the current node
        // and can potentially "split" across its left and right children.
        max_sum = Math.max(max_sum, node.val + left_gain + right_gain);

        // Return the maximum gain for a path that can be extended upwards.
        // This path cannot split, so it must include the node and at most one child.
        return node.val + Math.max(left_gain, right_gain);
    }
}
