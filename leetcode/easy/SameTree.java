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
     * Checks if two binary trees are structurally identical and have the same node values.
     *
     * This solution uses a recursive, depth-first approach. Two trees are considered
     * the same if their root nodes have the same value, their left subtrees are the
     * same, and their right subtrees are the same.
     *
     * Algorithm:
     * 1. Base Case 1: If both nodes `p` and `q` are null, they are identical. Return true.
     * 2. Base Case 2: If one of the nodes is null but the other is not, or if their values
     *    are different, they are not identical. Return false.
     * 3. Recursive Step: If the above checks pass, recursively call the function
     *    on the left children (`p.left`, `q.left`) and the right children
     *    (`p.right`, `q.right`). The trees are only identical if both of these
     *    recursive calls return true.
     *
     * @param p The root of the first binary tree.
     * @param q The root of the second binary tree.
     * @return True if the two trees are identical, false otherwise.
     *
     * Time Complexity: O(N), where N is the number of nodes in the smaller tree,
     *                  as we visit each node at most once.
     * Space Complexity: O(H), where H is the height of the tree, due to the recursion stack.
     *                   In the worst case (skewed tree), H can be N. In the best case (balanced tree), H is logN.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: If both nodes are null, they are the same.
        if (p == null && q == null) {
            return true;
        }
        // If one node is null, or if their values are different, they are not the same.
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        // Recursively check the left and right subtrees.
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
