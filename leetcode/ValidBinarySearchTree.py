# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

from typing import Optional

class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        """
        Determines if a binary tree is a valid Binary Search Tree (BST).

        A valid BST has the following properties:
        1. The left subtree of a node contains only nodes with values less than the node's value.
        2. The right subtree of a node contains only nodes with values greater than the node's value.
        3. Both the left and right subtrees must also be binary search trees.

        This solution uses a recursive approach, passing down the valid range (low, high)
        for each node's value.
        """
        def validate(node: Optional[TreeNode], low: float, high: float) -> bool:
            """
            Recursively validates the BST property for a subtree.
            - node: The current node to check.
            - low: The lower bound (exclusive) for the node's value.
            - high: The upper bound (exclusive) for the node's value.
            """
            # An empty node is a valid part of a BST.
            if not node:
                return True

            # The node's value must be within the valid range defined by its ancestors.
            if not (low < node.val < high):
                return False

            # Recursively check the left and right subtrees with updated bounds.
            # For the left child, the new upper bound is the current node's value.
            # For the right child, the new lower bound is the current node's value.
            return validate(node.left, low, node.val) and validate(node.right, node.val, high)

        # Start the validation from the root with an infinite range.
        return validate(root, -float("inf"), float("inf"))