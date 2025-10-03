import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
     * Performs an in-order traversal of a binary tree.
     *
     * This solution uses a recursive approach, which is a natural fit for tree
     * traversal problems due to their recursive structure.
     *
     * The in-order traversal algorithm follows the pattern:
     * 1. Traverse the left subtree.
     * 2. Visit the root node.
     * 3. Traverse the right subtree.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each
     *                  node is visited exactly once.
     * Space Complexity: O(H) for the recursion stack, where H is the height of the
     *                   tree. In the worst case (a skewed tree), this can be O(N).
     *
     * @param root The root node of the binary tree.
     * @return A list of integers representing the in-order traversal of the tree.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    /**
     * A recursive helper function to perform the in-order traversal.
     *
     * @param node   The current node being visited.
     * @param result The list to which the node values are added.
     */
    private void inorderHelper(TreeNode node, List<Integer> result) {
        // Base case: If the node is null, we've reached the end of a branch.
        if (node == null) {
            return;
        }

        // 1. Traverse the left subtree.
        inorderHelper(node.left, result);

        // 2. Visit the root node (add its value to the list).
        result.add(node.val);

        // 3. Traverse the right subtree.
        inorderHelper(node.right, result);
    }
}

/*
--- Alternative Iterative Solution using a Stack ---

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // A stack is used to simulate the recursion.
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;

        // Loop as long as there are nodes to process or nodes in the stack.
        while (currentNode != null || !stack.isEmpty()) {
            // 1. Traverse to the leftmost node of the current subtree.
            // Push all nodes encountered along the way onto the stack.
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            // At this point, `currentNode` is null, and the stack top is the
            // leftmost node we haven't visited yet.

            // 2. Visit the node at the top of the stack.
            currentNode = stack.pop();
            result.add(currentNode.val);

            // 3. Move to the right subtree to start the process over.
            currentNode = currentNode.right;
        }

        return result;
    }
}
*/
