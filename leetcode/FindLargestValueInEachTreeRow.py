from typing import Optional, List

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def largestValues(self, root: Optional[TreeNode]) -> List[int]:
        result = []

        def dfs(node: Optional[TreeNode], level: int):
            if not node:
                return

            # If this is the first node seen at this level, its value is the largest so far.
            if level == len(result):
                result.append(node.val)
            # Otherwise, a value for this level already exists. Update it if necessary.
            else:
                result[level] = max(result[level], node.val)

            # Recurse for children at the next level.
            dfs(node.left, level + 1)
            dfs(node.right, level + 1)

        dfs(root, 0)
        return result
