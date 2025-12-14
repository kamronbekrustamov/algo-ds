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

    // Accumulator to store the sum of all nodes visited so far.
    // Since we traverse in Reverse In-Order, this represents the sum of all keys >= current node.
    private int runningSum = 0;

    /**
     * Converts a Binary Search Tree (BST) into a Greater Tree.
     * In a Greater Tree, every key is changed to the original key plus the sum of all keys
     * greater than the original key in the BST.
     *
     * @param root The root of the Binary Search Tree.
     * @return The root of the converted Greater Tree.
     */
    public TreeNode convertBST(TreeNode root) {
        // Reset sum for safety if the instance is reused (though usually a new instance is created per test)
        runningSum = 0; 
        reverseInOrderTraversal(root);
        return root;
    }

    /**
     * Helper method to perform Reverse In-Order Traversal (Right -> Root -> Left).
     *
     * @param node The current node being processed.
     */
    private void reverseInOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        // 1. Visit the Right Subtree (All values here are greater than the current node)
        reverseInOrderTraversal(node.right);

        // 2. Process the Current Node
        runningSum += node.val;  // Add current node's value to the running total
        node.val = runningSum;   // Update current node with the new sum

        // 3. Visit the Left Subtree (All values here are smaller than the current node)
        reverseInOrderTraversal(node.left);
    }
}