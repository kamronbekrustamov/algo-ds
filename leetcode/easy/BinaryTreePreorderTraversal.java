import java.util.ArrayList;
import java.util.Collections;
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
     * Performs a pre-order traversal of a binary tree using an iterative approach.
     *
     * The pre-order traversal algorithm follows the pattern:
     * 1. Visit the root node.
     * 2. Traverse the left subtree.
     * 3. Traverse the right subtree.
     *
     * This solution uses a Stack to simulate the recursive behavior of the traversal.
     *
     * Algorithm Breakdown:
     * 1. Handle the edge case of an empty tree.
     * 2. Initialize a Stack and push the root node onto it.
     * 3. Loop as long as the stack is not empty.
     * 4. In each iteration, pop a node from the stack. This is the "visit" step.
     * 5. Add the popped node's value to the result list.
     * 6. Crucially, push the right child onto the stack first, then the left child.
     *    This ensures that the left child will be on top of the stack and will be
     *    processed next, correctly following the pre-order sequence.
     *
     * Time Complexity: O(N), where N is the number of nodes, as each node is
     *                  pushed and popped exactly once.
     * Space Complexity: O(H), where H is the height of the tree, for the stack.
     *                   In the worst case (a skewed tree), this can be O(N).
     *
     * @param root The root node of the binary tree.
     * @return A list of integers representing the pre-order traversal of the tree.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            // 4. Pop a node from the stack to visit it.
            TreeNode currentNode = stack.pop();
            // 5. Add the node's value to the result.
            result.add(currentNode.val);

            // 6. Push the right child first, so the left child is processed first.
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
        return result;
    }
}

/*
--- Alternative Recursive Solution ---

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        // Base case: If the node is null, do nothing.
        if (node == null) {
            return;
        }

        // 1. Visit the root node.
        result.add(node.val);
        // 2. Traverse the left subtree.
        preorderHelper(node.left, result);
        // 3. Traverse the right subtree.
        preorderHelper(node.right, result);
    }
}
*/
