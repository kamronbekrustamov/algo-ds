from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def init(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        # If the tree is empty, no path exists.
        if not root:
            return False

        # Check if the current node is a leaf.
        is_leaf = not root.left and not root.right

        # If it's a leaf, the path is valid only if the node's value equals the remaining targetSum.
        if is_leaf:
            return root.val == targetSum

        # If it's not a leaf, recurse on the children.
        # Subtract the current node's value from the targetSum for the next level.
        return self.hasPathSum(root.left, targetSum - root.val) or \
               self.hasPathSum(root.right, targetSum - root.val)