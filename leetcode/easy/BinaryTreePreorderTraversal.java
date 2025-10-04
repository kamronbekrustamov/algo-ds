import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
 * Solution class for binary tree preorder traversal.
 * 
 * Preorder traversal follows the pattern: Root -> Left -> Right
 * 
 * This implementation uses an iterative approach with a stack to simulate the
 * recursive call stack, avoiding potential stack overflow issues with deep trees.
 */
class Solution {
    /**
     * Performs a preorder traversal of a binary tree using an iterative approach.
     * 
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Each node is visited exactly once.
     * 
     * Space Complexity: O(h) where h is the height of the tree.
     * In the worst case (a skewed tree), this could be O(n).
     * In a balanced tree, it's O(log n).
     * 
     * @param root The root node of the binary tree.
     * @return A list of integers representing the preorder traversal of the tree.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // Handle the edge case of an empty tree
        if (root == null) {
            return Collections.emptyList();
        }

        // Result list to store the traversal order
        List<Integer> result = new ArrayList<>();
        
        // Using Deque interface with ArrayDeque implementation as a stack
        // ArrayDeque is preferred over LinkedList for stack operations because:
        // 1. Better performance for push/pop operations (O(1) amortized)
        // 2. Lower memory overhead (no node objects with pointers)
        // 3. Better cache locality due to contiguous memory allocation
        // 4. No capacity constraints (resizes automatically)
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        // Initialize the stack with the root node to start traversal
        stack.push(root);

        // Continue processing until all nodes have been visited
        while (!stack.isEmpty()) {
            // Pop the top node from the stack (LIFO order)
            TreeNode currentNode = stack.pop();
            
            // Process the current node by adding its value to the result list
            // This is the "Visit" step in the Root -> Left -> Right pattern
            result.add(currentNode.val);

            // For preorder traversal, we need to process left subtree before right subtree
            // Since stack is LIFO, we push right child first so that left child is on top
            // This ensures left subtree is processed before right subtree
            
            // Push right child first (will be processed after left subtree)
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            
            // Push left child last (will be processed next)
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
        
        return result;
    }
    
    /**
     * Alternative implementation using Morris Traversal.
     * 
     * This approach modifies the tree temporarily to create links back to ancestors,
     * allowing traversal without using extra space for a stack.
     * 
     * Time Complexity: O(n) - each node is visited a constant number of times
     * Space Complexity: O(1) - no additional space proportional to tree size
     * 
     * Note: This method temporarily modifies the tree structure but restores it
     * before returning. It's more complex but saves space for very deep trees.
     * 
     * @param root The root node of the binary tree.
     * @return A list of integers representing the preorder traversal of the tree.
     */
    public List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left == null) {
                // If no left child, process current node and move to right
                result.add(curr.val);
                curr = curr.right;
            } else {
                // Find the inorder predecessor (rightmost node in left subtree)
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Create a temporary link back to current node
                    predecessor.right = curr;
                    // Process current node before moving to left (preorder)
                    result.add(curr.val);
                    curr = curr.left;
                } else {
                    // Temporary link already exists, remove it and move to right
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
        
        return result;
    }
}