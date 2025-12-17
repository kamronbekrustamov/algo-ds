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
     * Removes all leaf nodes from the given binary tree that have a value equal to the target.
     * The process is recursive and works bottom-up (post-order traversal), meaning a non-leaf
     * node can become a new leaf node and subsequently be removed if its value matches the target
     * and its children were removed. 
     *
     * @param root The root of the binary tree.
     * @param target The value of leaf nodes to be removed.
     * @return The root of the resulting binary tree after the removal, or null if the entire tree is removed.
     * * @time_complexity O(N), where N is the number of nodes in the tree. Every node is visited exactly once.
     * @space_complexity O(H), where H is the height of the tree. This is due to the recursion stack. 
     * In the worst case (a skewed tree), H can be N. In the best/average case (a balanced tree), H is log(N).
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // Base case: if the current node is null, return null.
        if (root == null) {
            return null;
        }

        // 1. Recurse on the left and right subtrees (Post-order traversal)
        // This ensures the children are processed and pruned *before* the current node.
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        // 2. Check if the current node is now a leaf node with the target value
        // The children pointers will be null if they were removed in the recursive calls above.
        // A node is a leaf if both its left and right children are null.
        if (root.left == null && root.right == null && root.val == target) {
            // If it meets the criteria, remove it by returning null to its parent.
            return null;
        }

        // 3. If the node is not removed, return the node itself.
        return root;
    }
}