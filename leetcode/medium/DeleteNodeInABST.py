from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        """
        Deletes a node with the given key from a Binary Search Tree (BST).

        The function recursively traverses the BST to find the node to delete.
        Once the node is found, it handles the deletion based on three cases:
        1. The node is a leaf (no children): It is simply removed.
        2. The node has one child: The node is replaced by its child.
        3. The node has two children: The node's value is replaced by the value of its
           in-order successor (the smallest value in its right subtree). Then, the
           in-order successor is recursively deleted from the right subtree.

        Args:
            root: The root of the BST.
            key: The key of the node to be deleted.

        Returns:
            The root of the modified BST.
        """
        # Base case: If the tree is empty, return None.
        if not root:
            return None

        # Recursively search for the node to delete.
        if key < root.val:
            # The key is in the left subtree.
            root.left = self.deleteNode(root.left, key)
        elif key > root.val:
            # The key is in the right subtree.
            root.right = self.deleteNode(root.right, key)
        else:
            # The current node is the one to be deleted.

            # Case 1: Node has no left child.
            # Replace the node with its right child (or None if it has no right child).
            if not root.left:
                return root.right
            
            # Case 2: Node has no right child.
            # Replace the node with its left child.
            elif not root.right:
                return root.left
            
            # Case 3: Node has two children.
            # Find the in-order successor (the smallest node in the right subtree).
            successor = self._find_min(root.right)
            
            # Replace the current node's value with the successor's value.
            root.val = successor.val
            
            # Recursively delete the successor from the right subtree.
            root.right = self.deleteNode(root.right, successor.val)
            
        return root

    def _find_min(self, node: TreeNode) -> TreeNode:
        """
        Finds the node with the minimum value in a given subtree (the leftmost node).
        """
        current = node
        while current and current.left:
            current = current.left
        return current