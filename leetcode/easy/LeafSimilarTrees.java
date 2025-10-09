import java.util.ArrayList;
import java.util.List;

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
 * Solution for LeetCode problem "Leaf-Similar Trees".
 * This class provides a method to determine if two binary trees are leaf-similar.
 * Two binary trees are considered leaf-similar if their leaf value sequences are the same.
 * A leaf value sequence is the values of all leaf nodes ordered from left to right.
 */
class Solution {

    /**
     * Determines if two binary trees are leaf-similar.
     * This is achieved by performing a depth-first traversal on both trees
     * to collect their respective leaf value sequences and then comparing these sequences.
     *
     * @param root1 The root of the first binary tree.
     * @param root2 The root of the second binary tree.
     * @return true if the two trees are leaf-similar, false otherwise.
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // Lists to store the leaf value sequences for each tree.
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        // Populate the leaf value sequence for the first tree.
        findLeafValueSequence(root1, leaves1);
        // Populate the leaf value sequence for the second tree.
        findLeafValueSequence(root2, leaves2);

        // Compare the two leaf value sequences. They must be identical for the trees to be leaf-similar.
        return leaves1.equals(leaves2);
    }

    /**
     * Performs a depth-first traversal (DFS) to find the leaf nodes of a tree
     * and appends their values to the provided list in left-to-right order.
     *
     * @param node The current node being visited in the traversal.
     * @param leafValues The list to which leaf values will be added.
     */
    private void findLeafValueSequence(TreeNode node, List<Integer> leafValues) {
        // Base case: if the node is null, there are no leaves to add.
        if (node == null) {
            return;
        }

        // If the node is a leaf (no left or right children), add its value to the list.
        if (node.left == null && node.right == null) {
            leafValues.add(node.val);
        }

        // Recursively traverse the left subtree.
        findLeafValueSequence(node.left, leafValues);
        // Recursively traverse the right subtree.
        findLeafValueSequence(node.right, leafValues);
    }
}
