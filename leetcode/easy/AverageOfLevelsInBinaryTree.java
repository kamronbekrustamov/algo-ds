import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
class Solution {
    /**
     * Calculates the average value of the nodes on each level of a binary tree.
     *
     * This solution uses Breadth-First Search (BFS) to traverse the tree level by level.
     * A queue is used to keep track of the nodes to visit.
     *
     * Algorithm Breakdown:
     * 1. Handle the edge case of an empty tree.
     * 2. Initialize a queue and add the root node to it.
     * 3. Initialize a list to store the resulting averages.
     * 4. Loop as long as the queue is not empty. Each iteration of this outer loop
     *    processes one full level of the tree.
     * 5. In each iteration, get the number of nodes currently in the queue (`levelSize`).
     *    This is the number of nodes at the current level.
     * 6. Initialize a `levelSum` for the current level.
     * 7. Loop `levelSize` times to process each node at the current level.
     *    a. Dequeue a node.
     *    b. Add its value to `levelSum`.
     *    c. Enqueue its left and right children if they exist.
     * 8. After processing all nodes at the level, calculate the average (`levelSum / levelSize`)
     *    and add it to the result list.
     * 9. Return the list of averages.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each node
     *                  is visited exactly once.
     * Space Complexity: O(W), where W is the maximum width of the tree. This is the
     *                   maximum number of nodes that can be in the queue at any one time.
     *
     * @param root The root node of the binary tree.
     * @return A list of doubles representing the average of each level.
     */
    public List<Double> averageOfLevels(TreeNode root) {
        // 1. Handle edge case of an empty tree.
        if (root == null) {
            return Collections.emptyList();
        }

        // 3. Initialize a list to store the resulting averages.
        List<Double> result = new ArrayList<>();
        // 2. Initialize a queue for BFS and add the root.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 4. Loop while there are still nodes to process.
        while (!queue.isEmpty()) {
            // 5. Get the number of nodes at the current level.
            int levelSize = queue.size();
            // 6. Initialize sum for the current level. Use double for precision.
            double levelSum = 0;

            // 7. Process all nodes at the current level.
            for (int i = 0; i < levelSize; i++) {
                // a. Dequeue a node.
                TreeNode currentNode = queue.poll();
                // b. Add its value to the level sum.
                levelSum += currentNode.val;

                // c. Enqueue children for the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // 8. Calculate the average for the level and add to the result list.
            result.add(levelSum / levelSize);
        }

        // 9. Return the final list of averages.
        return result;
    }
}
