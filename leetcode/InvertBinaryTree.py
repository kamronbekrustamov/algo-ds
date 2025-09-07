from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        """
        Inverts a binary tree by swapping the left and right children of every node.
        This is solved using a recursive, depth-first approach.
        """
        # Base case: If the node is None, we've reached the end of a branch.
        if not root:
            return None

        # This is a concise, one-line recursive swap.
        # 1. Recursively call invertTree on the original right child.
        # 2. Recursively call invertTree on the original left child.
        # 3. Assign the results to the left and right children of the current root, respectively.
        root.left, root.right = self.invertTree(root.right), self.invertTree(root.left)

        return root