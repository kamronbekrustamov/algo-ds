import java.util.ArrayDeque;
import java.util.Deque;

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
 * Solution for LeetCode problem "Minimum Depth of Binary Tree".
 * This class provides a method to calculate the minimum depth of a binary tree.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
class Solution {

    /**
     * Calculates the minimum depth of a binary tree using an iterative Breadth-First Search (BFS) approach.
     *
     * BFS is suitable for finding the shortest path in an unweighted graph (like a tree) because it explores
     * all nodes at the current depth level before moving on to the nodes at the next depth level.
     * The first leaf node encountered during a BFS traversal will necessarily be at the minimum depth.
     *
     * @param root The root of the binary tree.
     * @return The minimum depth of the binary tree.
     */
    public int minDepth(TreeNode root) {
        // If the root is null, the tree is empty, so its minimum depth is 0.
        if (root == null) {
            return 0;
        }

        // Use a Deque (double-ended queue) to implement the BFS queue for tree nodes.
        Deque<TreeNode> nodeDeque = new ArrayDeque<>();
        // Use another Deque to keep track of the depth of each node in the `nodeDeque`.
        Deque<Integer> depthDeque = new ArrayDeque<>();

        // Start BFS by adding the root node and its depth (1) to the queues.
        nodeDeque.add(root);
        depthDeque.add(1);

        // Continue BFS as long as there are nodes to visit.
        while (!nodeDeque.isEmpty()) {
            // Dequeue the current node and its corresponding depth.
            TreeNode node = nodeDeque.poll();
            int depth = depthDeque.poll();

            // If the current node is a leaf node (no left or right children),
            // then this is the first leaf encountered in BFS, so its depth is the minimum depth.
            if (node.left == null && node.right == null) {
                return depth;
            }

            // If the left child exists, enqueue it and its incremented depth.
            if (node.left != null) {
                nodeDeque.add(node.left);
                depthDeque.add(depth + 1);
            }
            // If the right child exists, enqueue it and its incremented depth.
            if (node.right != null) {
                nodeDeque.add(node.right);
                depthDeque.add(depth + 1);
            }
        }
        // This line should theoretically not be reached if the tree is valid and not empty,
        // as a leaf node will always be found.
        return 0; 
    }
}
