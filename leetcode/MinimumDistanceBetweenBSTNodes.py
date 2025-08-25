from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    @staticmethod
    def minDiffInBST(root: Optional[TreeNode]) -> int:
        """
        Finds the minimum absolute difference between the values of any two nodes in a BST.

        The key property of a BST is that an in-order traversal visits the nodes
        in ascending sorted order. Therefore, the minimum difference must occur
        between two adjacent nodes in this sorted sequence.

        This solution performs an in-order traversal, keeping track of the previously
        visited node's value to calculate the difference with the current node.
        """
        min_diff = float('inf')
        prev_val = None

        def inorder_traverse(node: Optional[TreeNode]):
            nonlocal min_diff, prev_val
            if not node:
                return

            # 1. Traverse the left subtree
            inorder_traverse(node.left)

            # 2. Process the current node
            if prev_val is not None:
                min_diff = min(min_diff, node.val - prev_val)
            prev_val = node.val

            # 3. Traverse the right subtree
            inorder_traverse(node.right)

        inorder_traverse(root)
        return min_diff