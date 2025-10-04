import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
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
 * Solution class for binary tree in-order traversal.
 * 
 * In-order traversal follows the pattern: Left -> Root -> Right
 * 
 * This implementation uses an iterative approach with a stack to simulate the
 * recursive call stack, avoiding potential stack overflow issues with deep trees.
 */
class Solution {
    /**
     * Performs an in-order traversal of a binary tree using an iterative approach.
     * 
     * Time Complexity: O(n) where n is the number of nodes in the tree.
     * Each node is visited exactly once.
     * 
     * Space Complexity: O(h) where h is the height of the tree.
     * In the worst case (a skewed tree), this could be O(n).
     * In a balanced tree, it's O(log n).
     * 
     * @param root The root node of the binary tree.
     * @return A list of integers representing the in-order traversal of the tree.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // Initialize result list to store the traversal order
        List<Integer> result = new ArrayList<>();
        
        // Handle the edge case of an empty tree
        if (root == null) {
            return result;
        }
        
        // Using Deque interface with LinkedList implementation as a stack
        // Deque is preferred over the legacy Stack class because:
        // 1. Stack class is synchronized (thread-safe) which adds unnecessary overhead
        // 2. Deque provides a more complete and consistent set of LIFO operations
        // 3. It's the recommended approach in modern Java
        Deque<TreeNode> stack = new LinkedList<>();
        
        // Start with the root node
        TreeNode currentNode = root;

        // Continue traversal as long as there are nodes to process or nodes in the stack
        // The condition ensures we process all nodes in the tree
        while (currentNode != null || !stack.isEmpty()) {
            // Phase 1: Traverse to the leftmost node, pushing all nodes along the path
            // This ensures we visit nodes in the correct in-order sequence
            while (currentNode != null) {
                stack.push(currentNode); // Add current node to stack
                currentNode = currentNode.left; // Move to left child
            }

            // Phase 2: Process the node at the top of the stack
            // This is the "Root" part of the Left -> Root -> Right pattern
            currentNode = stack.pop(); // Remove and get the top node from stack
            result.add(currentNode.val); // Add node's value to result list

            // Phase 3: Move to the right subtree to continue the process
            // This ensures we process the right subtree after visiting the root
            currentNode = currentNode.right;
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
     * @return A list of integers representing the in-order traversal of the tree.
     */
    public List<Integer> inorderTraversalMorris(TreeNode root) {
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
                    curr = curr.left;
                } else {
                    // Temporary link already exists, remove it and process current node
                    predecessor.right = null;
                    result.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        
        return result;
    }
}