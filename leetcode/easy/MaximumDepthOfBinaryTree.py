from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        # Base case: If the node is None, we've reached the end of a branch,
        # so its depth is 0.
        if not root:
            return 0

        # The depth of the tree is defined recursively:
        # 1 (for the current node) + the maximum depth of its subtrees.
        left_depth = self.maxDepth(root.left)
        right_depth = self.maxDepth(root.right)

        return 1 + max(left_depth, right_depth)