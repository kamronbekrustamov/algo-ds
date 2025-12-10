import java.util.ArrayList;
import java.util.List;

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
     * Finds all root-to-leaf paths where the sum of the node values equals the targetSum.
     *
     * @param root The root node of the binary tree.
     * @param targetSum The specific sum to match.
     * @return A list of lists, where each inner list represents a path from root to leaf.
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Edge case: if tree is empty, return empty list immediately
        if (root == null) {
            return result;
        }

        // Pass 'result' down to avoid using a global/member variable
        backtrack(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    /**
     * Helper method to perform DFS backtracking.
     *
     * @param node The current node being visited.
     * @param remainingSum The target sum remaining after subtracting values of nodes in current path.
     * @param currentPath The current path of values from root to the current node.
     * @param result The collector list for valid paths.
     */
    private void backtrack(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Choose: Add current node to path
        currentPath.add(node.val);

        // Check: Is this a leaf node and does it equal the remaining sum?
        if (node.left == null && node.right == null && remainingSum == node.val) {
            // Found a valid path. make a copy (new ArrayList) because currentPath is mutable
            result.add(new ArrayList<>(currentPath));
        } else {
            // Explore: Recurse on left and right children
            backtrack(node.left, remainingSum - node.val, currentPath, result);
            backtrack(node.right, remainingSum - node.val, currentPath, result);
        }

        // Un-choose (Backtrack): Remove the last element to return to previous state
        currentPath.remove(currentPath.size() - 1);
    }
}