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
     * Checks if a binary tree `subRoot` is a subtree of another binary tree `root`.
     *
     * A subtree of a tree `T` is a tree consisting of a node in `T` and all of its
     * descendants in `T`.
     *
     * Algorithm:
     * 1. The main function `isSubtree` traverses the `root` tree recursively.
     * 2. For each node in `root`, it calls a helper function `isSameTree` to check
     *    if the subtree starting at that node is identical to `subRoot`.
     * 3. If an identical subtree is found, we return true.
     * 4. If not, we continue the search by recursively calling `isSubtree` on the
     *    left and right children of the current node.
     * 5. The recursion stops when we run out of nodes in the `root` tree.
     *
     * @param root The root of the main binary tree.
     * @param subRoot The root of the potential subtree.
     * @return True if `subRoot` is a subtree of `root`, false otherwise.
     *
     * Time Complexity: O(M * N), where M is the number of nodes in `root` and N is
     *                  the number of nodes in `subRoot`. In the worst case, we call
     *                  `isSameTree` (O(N)) for every node in `root` (O(M)).
     * Space Complexity: O(H), where H is the height of the `root` tree, due to the
     *                   recursion stack.
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base case: An empty tree is a subtree of any tree.
        if (subRoot == null) {
            return true;
        }
        // Base case: If the main tree is empty but subRoot is not, it's impossible.
        if (root == null) {
            return false;
        }

        // Check if the tree starting at the current `root` node is the same as `subRoot`.
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // If not, recursively check if `subRoot` is a subtree of the left OR right child.
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /**
     * Helper function to check if two trees are structurally and valuably identical.
     *
     * @param p The root of the first tree.
     * @param q The root of the second tree.
     * @return True if the two trees are identical, false otherwise.
     */
    private boolean isSameTree(TreeNode p, TreeNode q) {
        // If both nodes are null, they are identical.
        if (p == null && q == null) {
            return true;
        }
        // If one node is null, or their values differ, they are not identical.
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        // Recursively check the left and right subtrees.
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
