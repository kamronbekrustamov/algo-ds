from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        """
        Calculates the sum of all root-to-leaf numbers in a binary tree.
        Each root-to-leaf path represents a number.
        """

        def dfs(node: Optional[TreeNode], current_number: int) -> int:
            """
            Recursively calculates the sum of path numbers for the subtree at `node`.
            `current_number` is the number formed by the path from the root to the parent of `node`.
            """
            # Base case: If the node is null, this path contributes nothing to the sum.
            if not node:
                return 0

            # Update the number for the current path.
            current_number = current_number * 10 + node.val

            # If it's a leaf node, we've completed a number, so return it.
            if not node.left and not node.right:
                return current_number

            # Otherwise, return the sum of numbers from the left and right subtrees.
            return dfs(node.left, current_number) + dfs(node.right, current_number)

        return dfs(root, 0)