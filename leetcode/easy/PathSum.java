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
    /**
     * Checks if there is a root-to-leaf path in a binary tree such that adding up
     * all the values along the path equals the given targetSum.
     *
     * This solution uses a recursive Depth-First Search (DFS) approach.
     *
     * Algorithm:
     * 1. Base Case: If the current node is null, it means we've gone past a leaf
     *    or started with an empty tree, so no path exists from this point. Return false.
     * 2. Check for Leaf Node: Determine if the current node is a leaf node (i.e., it has
     *    no left and no right children).
     * 3. Leaf Node Condition: If it is a leaf node, check if its value is equal to the
     *    remaining `targetSum`. If they are equal, a valid path has been found, return true.
     * 4. Recursive Step: If it's not a leaf node, recursively call `hasPathSum` for its
     *    left child and right child. For each recursive call, subtract the current node's
     *    value from the `targetSum` to represent the remaining sum needed.
     * 5. Combine Results: Return true if either the left subtree call or the right subtree
     *    call returns true (meaning a valid path was found in at least one branch).
     *
     * @param root The root of the binary tree.
     * @param targetSum The target sum to find.
     * @return True if such a path exists, false otherwise.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each node is visited once.
     * Space Complexity: O(H), where H is the height of the tree, due to the recursion stack.
     *                   In the worst case (skewed tree), H can be N. In the best case (balanced tree), H is logN.
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        boolean isLeaf = (root.left == null && root.right == null);

        if (isLeaf) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }
}
