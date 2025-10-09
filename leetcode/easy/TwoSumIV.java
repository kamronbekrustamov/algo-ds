import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
     * Finds if there exist two elements in the BST such that their sum is equal to k.
     *
     * This solution uses a Breadth-First Search (BFS) traversal combined with a HashSet
     * to achieve O(n) time and O(n) space complexity.
     *
     * Algorithm:
     * 1. Use a Queue for BFS traversal and a HashSet to store the values of nodes visited so far.
     * 2. Start the BFS with the root node.
     * 3. For each node visited:
     *    a. Calculate the complement required to reach the target `k` (i.e., `k - node.val`).
     *    b. Check if this complement exists in the HashSet. If it does, a pair is found, return true.
     *    c. If the complement is not found, add the current node's value to the HashSet.
     *    d. Add the node's left and right children to the queue if they exist.
     * 4. If the traversal completes without finding a pair, return false.
     *
     * @param root The root of the Binary Search Tree.
     * @param k The target sum.
     * @return True if a pair with the given sum exists, false otherwise.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each node is visited once.
     * Space Complexity: O(N) in the worst case for the HashSet and the Queue.
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> seenValues = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (seenValues.contains(k - node.val)) {
                return true;
            }
            seenValues.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return false;
    }
}
