import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Definition for a binary tree node.
 * (Assuming this class exists or is provided externally)
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
     * Finds the k-th smallest element in a Binary Search Tree (BST).
     *
     * This method uses an iterative in-order traversal, which visits nodes in ascending order.
     * The traversal is stopped early once the k-th element is found, optimizing the process.
     *
     * @param root The root node of the Binary Search Tree.
     * @param k The rank of the element to find (1-indexed, e.g., k=1 is the smallest).
     * @return The value of the k-th smallest element, or -1 if the tree has fewer than k nodes.
     *
     * Time Complexity: O(H + k), where H is the height of the tree.
     * In the worst case (skewed tree), H = N (total nodes), resulting in O(N).
     * Space Complexity: O(H) for the stack, where H is the height of the tree.
     */
    public int kthSmallest(TreeNode root, int k) {
        // Deque (Double Ended Queue) is the preferred interface for stack operations in modern Java.
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        // Perform iterative in-order traversal: Left -> Root -> Right
        while (curr != null || !stack.isEmpty()) {
            // 1. Traverse all the way to the leftmost (smallest) node, pushing nodes onto the stack.
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // 2. Process the current smallest node (pop from stack).
            curr = stack.pop();

            // 3. Decrement k and check if this is the target k-th element.
            k--;
            if (k == 0) {
                // Found the k-th smallest element.
                return curr.val;
            }

            // 4. Move to the right subtree to find the next largest element.
            curr = curr.right;
        }

        // Should not be reached if k is valid (1 <= k <= total number of nodes),
        // but included for robustness in case k is greater than the total number of nodes.
        return -1;
    }
}