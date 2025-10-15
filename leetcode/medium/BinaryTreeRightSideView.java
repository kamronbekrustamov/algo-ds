import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solution for the "Binary Tree Right Side View" problem using a space-efficient Depth-First Search (DFS).
 */
class Solution {
    /**
     * Returns the right side view of a binary tree using DFS.
     *
     * This DFS approach traverses the tree recursively, prioritizing the right child.
     * It keeps track of the current depth. When we visit a node at a depth that
     * we haven't seen before, we add its value to our result list. This ensures
     * that the first node we encounter at each depth is the rightmost one.
     *
     * Time Complexity: O(N), where N is the number of nodes. Each node is visited once.
     * Space Complexity: O(H), where H is the height of the tree, due to the recursion stack.
     *                  In the worst case (a skewed tree), this is O(N). For a balanced tree, it's O(log N).
     *
     * @param root The root node of the binary tree.
     * @return A list of node values representing the right side view.
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    /**
     * Helper method to perform the DFS traversal.
     *
     * @param node   The current node being visited.
     * @param level  The current depth/level of the node.
     * @param result The list to store the right-side view values.
     */
    private void dfs(TreeNode node, int level, List<Integer> result) {
        // Base case: if the node is null, return.
        if (node == null) {
            return;
        }

        // If the result list's size is equal to the current level, it means we
        // are visiting this level for the first time.
        if (result.size() == level) {
            result.add(node.val);
        }

        // Optimization: Recurse on the right child FIRST. This ensures that
        // we see the rightmost node at any given level before any other node at that level.
        dfs(node.right, level + 1, result);
        
        // Then, recurse on the left child.
        dfs(node.left, level + 1, result);
    }
}