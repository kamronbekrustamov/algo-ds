import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

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
 * Solution for performing a level-order traversal (Breadth-First Search) on a binary tree.
 */
class Solution {
    /**
     * Returns the values of the nodes in a binary tree, level by level.
     *
     * This implementation uses a queue to perform a standard Breadth-First Search (BFS).
     * It processes one tree level at a time before moving to the next.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree. Each node is visited exactly once.
     * Space Complexity: O(M), where M is the maximum number of nodes at any single level
     *                  (the maximum width of the tree). In the worst case, this is O(N).
     *
     * @param root The root node of the binary tree.
     * @return A list of lists, where each inner list contains the values of the nodes at that level.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Optimization 1: Handle the edge case of an empty tree.
        // If the root is null, there are no levels to traverse, so we return an empty list.
        // This prevents a NullPointerException later in the code.
        if (root == null) {
            return Collections.emptyList();
        }

        // The main list to store the result, containing a list for each level.
        List<List<Integer>> result = new ArrayList<>();

        // A queue is the ideal data structure for BFS. We use ArrayDeque for its efficiency.
        Queue<TreeNode> queue = new ArrayDeque<>();

        // Start by adding the root node to the queue.
        queue.offer(root);

        // Continue the loop as long as there are nodes to process in the current level.
        while (!queue.isEmpty()) {
            // Create a new list to store the values of the nodes for the current level.
            List<Integer> currentLevel = new ArrayList<>();

            // Get the number of nodes in the current level. This is crucial because
            // we only want to process these nodes in this iteration, not the children
            // we are about to add to the queue.
            int levelSize = queue.size();

            // Iterate through all nodes of the current level.
            for (int i = 0; i < levelSize; i++) {
                // Remove the node from the front of the queue.
                TreeNode currentNode = queue.poll();

                // Add the node's value to our current level's list.
                currentLevel.add(currentNode.val);

                // Optimization 2: Add children to the queue for the next level's processing.
                // We check for null to avoid adding empty nodes to the queue.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // After processing all nodes in the current level, add the list of values to our final result.
            result.add(currentLevel);
        }

        return result;
    }
}