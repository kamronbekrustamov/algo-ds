import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList; // For alternative solutions

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
     * Performs a post-order traversal of a binary tree using an iterative approach.
     *
     * The post-order traversal algorithm follows the pattern: Left -> Right -> Root.
     *
     * This iterative solution is a clever modification of the pre-order traversal.
     * A standard pre-order traversal is Root -> Left -> Right. If we modify it to
     * visit Root -> Right -> Left and then reverse the result, we get Left -> Right -> Root,
     * which is post-order.
     *
     * Algorithm Breakdown:
     * 1. Use a Stack and push the root node.
     * 2. While the stack is not empty, pop a node.
     * 3. Add the node's value to the *front* of the result list. A LinkedList is
     *    used for an efficient O(1) `addFirst` operation.
     * 4. Push the node's left child, then its right child to the stack.
     *    (Because the stack is LIFO, the right child will be processed before the
     *    left child in the next iterations, generating the `Root -> Right -> Left` sequence).
     * 5. By adding to the front of the list each time, we are implicitly reversing
     *    the `Root -> Right -> Left` sequence, resulting in the correct post-order.
     *
     * Time Complexity: O(N), as each node is visited once.
     * Space Complexity: O(H), where H is the height of the tree, for the stack.
     *
     * @param root The root node of the binary tree.
     * @return A list of integers representing the post-order traversal of the tree.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // A LinkedList allows for efficient O(1) insertion at the beginning.
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // Add the "root" to the front of the list.
            result.addFirst(node.val);

            // Push left child first, then right. This means right will be processed
            // before left, giving us the (Root -> Right -> Left) order.
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }
}

/*
--- Alternative 1: Classic Recursive Solution ---

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        // 1. Traverse the left subtree.
        postorderHelper(node.left, result);
        // 2. Traverse the right subtree.
        postorderHelper(node.right, result);
        // 3. Visit the root node.
        result.add(node.val);
    }
}
*/

/*
--- Alternative 2: Iterative Solution with One Stack and Visited Tracking ---
This approach performs a "true" post-order traversal without reversing. It's more
complex as it needs to distinguish between returning from a left child (and needing
to go right) versus returning from a right child (and needing to process the parent).

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        TreeNode lastVisitedNode = null;

        while (currentNode != null || !stack.isEmpty()) {
            // Go down to the leftmost node.
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            
            // Peek at the node on top of the stack.
            TreeNode peekNode = stack.peek();
            
            // If the node has a right child that we haven't visited yet,
            // move to the right child.
            if (peekNode.right != null && peekNode.right != lastVisitedNode) {
                currentNode = peekNode.right;
            } else {
                // Otherwise, process the node.
                // It has no right child or the right child has already been visited.
                stack.pop();
                result.add(peekNode.val);
                lastVisitedNode = peekNode;
            }
        }
        return result;
    }
}
*/
