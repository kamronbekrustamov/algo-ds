# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        """
        Finds the lowest common ancestor (LCA) of two given nodes in a Binary Search Tree (BST).

        The LCA is defined as the lowest node in the tree that has both p and q as descendants
        (where we allow a node to be a descendant of itself).

        This solution leverages the properties of a BST:
        - If both p and q are smaller than the current node, the LCA must be in the left subtree.
        - If both p and q are larger than the current node, the LCA must be in the right subtree.
        - Otherwise, the current node is the LCA.
        """
        # Start from the root of the tree
        current = root

        # Traverse the tree until we find the LCA
        while current:
            # If both p and q are greater than the current node's value,
            # the LCA must be in the right subtree.
            if p.val > current.val and q.val > current.val:
                current = current.right
            # If both p and q are smaller than the current node's value,
            # the LCA must be in the left subtree.
            elif p.val < current.val and q.val < current.val:
                current = current.left
            # If the paths to p and q diverge, or if one of them is the current node,
            # then the current node is the LCA.
            else:
                return current