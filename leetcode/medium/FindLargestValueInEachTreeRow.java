import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

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
     * Finds the largest value in each level of a binary tree.
     * Implements a Breadth-First Search (BFS) for level-order traversal.
     *
     * @param root The root node of the binary tree.
     * @return A list of integers where the i-th element is the largest value in the i-th level.
     * * Time Complexity: O(N), where N is the number of nodes, as every node is processed once.
     * Space Complexity: O(W), where W is the maximum width of the tree (for the queue).
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        // Handle the edge case of an empty tree.
        if (root == null) {
            // Using Collections.emptyList() is slightly cleaner and avoids unnecessary object creation
            // if an empty list is returned frequently.
            return Collections.emptyList();
        }

        // Deque is preferred over Queue interface for concrete implementation like ArrayDeque.
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        // Perform Level-Order Traversal (BFS)
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // Initialize max for the current level to the smallest possible integer value.
            int max = Integer.MIN_VALUE;

            // Process all nodes at the current level.
            for (int i = 0; i < levelSize; i++) {
                // Remove the node from the front of the queue.
                TreeNode current = queue.poll();
                
                // Update the maximum value for the current level.
                max = Math.max(max, current.val);

                // Enqueue the children for the next level's traversal.
                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            // After processing the entire level, add the maximum value to the result list.
            result.add(max);
        }

        return result;
    }
}