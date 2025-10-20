/**
 * Definition for a binary tree node.
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
     * Deletes a node with the given key from the Binary Search Tree (BST).
     * The method handles three cases:
     * 1. Node is not found (root is null).
     * 2. Node has 0 or 1 child.
     * 3. Node has 2 children (replaces node's value with its inorder successor).
     *
     * @param root The root of the current (sub)tree.
     * @param key The value of the node to be deleted.
     * @return The root of the (sub)tree after deletion.
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        // Base case: If the tree is empty or key is not found
        if (root == null) {
            return null;
        }

        // 1. Search for the node to delete (standard BST search)
        if (key < root.val) {
            // Traverse left subtree
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // Traverse right subtree
            root.right = deleteNode(root.right, key);
        }
        // 2. Found the node to be deleted (key == root.val)
        else {
            // Case 1: Node has 0 or 1 child
            
            // If the node has no left child (or is a leaf node)
            if (root.left == null) {
                // Replace the current node with its right child (could be null)
                return root.right; 
            }
            // If the node has no right child
            if (root.right == null) {
                // Replace the current node with its left child
                return root.left;
            }

            // Case 2: Node has 2 children
            
            // Find the inorder successor (smallest node in the right subtree)
            int inorderSuccessorVal = findInorderSuccessor(root);
            
            // Replace the current node's value with the successor's value
            root.val = inorderSuccessorVal;
            
            // Delete the original inorder successor from the right subtree
            // The successor is now redundant as its value has been moved up.
            root.right = deleteNode(root.right, inorderSuccessorVal);
        }
        
        // Return the (potentially modified) root of the current subtree
        return root;
    }

    /**
     * Finds the value of the inorder successor for a node that has two children.
     * The inorder successor is the smallest value in the node's right subtree.
     *
     * @param rootToDelete The node whose successor needs to be found. 
     * It must have a non-null right child.
     * @return The integer value of the inorder successor.
     */
    private int findInorderSuccessor(TreeNode rootToDelete) {
        // Start from the right child of the node to be deleted
        TreeNode tempNode = rootToDelete.right;
        
        // The inorder successor is the leftmost node in the right subtree
        while (tempNode.left != null) {
            tempNode = tempNode.left;
        }
        return tempNode.val;
    }
}