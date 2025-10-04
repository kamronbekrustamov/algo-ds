import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque; // More efficient than Stack for LIFO operations
import java.util.ArrayList;

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
 * Solution class for binary tree post-order traversal.
 * 
 * Post-order traversal follows the pattern: Left -> Right -> Root
 * 
 * This implementation uses an iterative approach with a stack to simulate the
 * recursive call stack, avoiding potential stack overflow issues with deep trees.
 */
class Solution {
    /**
     * Performs a post-order traversal of a binary tree using an iterative approach.
     * 
     * This solution uses a clever modification of the pre-order traversal.
     * A standard pre-order traversal is Root -> Left -> Right. If we modify it to
     * visit Root -> Right -> Left and then reverse the result, we get Left -> Right -> Root,
     * which is post-order.
     * 
     * Algorithm Breakdown:
     * 1. Use a Deque (as a stack) and push the root node.
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
     * In the worst case (a skewed tree), this could be O(N).
     * In a balanced tree, it's O(log N).
     * 
     * @param root The root node of the binary tree.
     * @return A list of integers representing the post-order traversal of the tree.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // A LinkedList allows for efficient O(1) insertion at the beginning.
        // This is crucial for our approach of building the result in reverse order.
        LinkedList<Integer> result = new LinkedList<>();
        
        // Handle the edge case of an empty tree
        if (root == null) {
            return result;
        }

        // Using Deque interface with ArrayDeque implementation as a stack
        // Deque is preferred over the legacy Stack class because:
        // 1. Stack class is synchronized (thread-safe) which adds unnecessary overhead
        // 2. Deque provides a more complete and consistent set of LIFO operations
        // 3. ArrayDeque is generally faster than Stack or LinkedList for stack operations
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        // Continue processing until all nodes have been visited
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            // Add the "root" to the front of the list.
            // This is the key to our approach - by always adding to the front,
            // we're effectively reversing the traversal order.
            result.addFirst(node.val);

            // Push left child first, then right. This means right will be processed
            // before left, giving us the (Root -> Right -> Left) order.
            // This is the reverse of what we want, but since we're adding to the
            // front of the list, the final result will be correct (Left -> Right -> Root).
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }
    
    /**
     * Alternative implementation using an iterative approach with one stack and visited tracking.
     * 
     * This approach performs a "true" post-order traversal without reversing. It's more
     * complex as it needs to distinguish between returning from a left child (and needing
     * to go right) versus returning from a right child (and needing to process the parent).
     * 
     * Time Complexity: O(N), as each node is visited once.
     * Space Complexity: O(H), where H is the height of the tree, for the stack.
     * 
     * @param root The root node of the binary tree.
     * @return A list of integers representing the post-order traversal of the tree.
     */
    public List<Integer> postorderTraversalIterativeWithVisited(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        // Using Deque instead of Stack for better performance
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode currentNode = root;
        TreeNode lastVisitedNode = null;

        // Continue traversal as long as there are nodes to process or nodes in the stack
        while (currentNode != null || !stack.isEmpty()) {
            // Go down to the leftmost node, pushing all nodes along the path
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            
            // Peek at the node on top of the stack without removing it
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
    
    /**
     * Alternative implementation using Morris Traversal.
     * 
     * This approach modifies the tree temporarily to create links back to ancestors,
     * allowing traversal without using extra space for a stack.
     * 
     * Time Complexity: O(N) - each node is visited a constant number of times
     * Space Complexity: O(1) - no additional space proportional to tree size
     * 
     * Note: This method temporarily modifies the tree structure but restores it
     * before returning. It's more complex but saves space for very deep trees.
     * 
     * @param root The root node of the binary tree.
     * @return A list of integers representing the post-order traversal of the tree.
     */
    public List<Integer> postorderTraversalMorris(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode curr = dummy;
        
        while (curr != null) {
            if (curr.left == null) {
                curr = curr.right;
            } else {
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    // Process nodes between curr.left and predecessor
                    TreeNode temp = predecessor;
                    LinkedList<Integer> tempList = new LinkedList<>();
                    while (temp != curr) {
                        tempList.addFirst(temp.val);
                        temp = temp.right;
                    }
                    result.addAll(tempList);
                    
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
        
        return result;
    }
}