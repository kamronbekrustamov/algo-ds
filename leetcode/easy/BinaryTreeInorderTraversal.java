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
     * Performs an in-order traversal of a binary tree using an iterative approach.
     *
     * This solution avoids recursion by using an explicit Stack to simulate the
     * function call stack. This can be beneficial for very deep trees where
     * recursion might lead to a StackOverflowError.
     *
     * The in-order traversal algorithm follows the pattern:
     * 1. Traverse the left subtree.
     * 2. Visit the root node.
     * 3. Traverse the right subtree.
     *
     * Algorithm Breakdown (Iterative):
     * 1. Initialize an empty result list and an empty stack.
     * 2. Start with the root node as the `currentNode`.
     * 3. Loop as long as `currentNode` is not null or the stack is not empty.
     * 4. Inside the loop, push all left children onto the stack until you reach
     *    the leftmost node (`currentNode` becomes null).
     * 5. Once at the leftmost node, pop from the stack. This is the node to be
     *    "visited". Add its value to the result list.
     * 6. Move to the right child of the popped node and repeat the process.
     *
     * Time Complexity: O(N), where N is the number of nodes.
     * Space Complexity: O(H), where H is the height of the tree, for the stack.
     *
     * @param root The root node of the binary tree.
     * @return A list of integers representing the in-order traversal of the tree.
     */
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
            // node that should be visited next according to in-order rules.

            // 2. Visit the node at the top of the stack.
            currentNode = stack.pop();
            result.add(currentNode.val);

            // 3. Move to the right subtree to start the process over for the right side.
            currentNode = currentNode.right;
        }

        return result;
    }
}

/*
--- Alternative Recursive Solution ---

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

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
*/