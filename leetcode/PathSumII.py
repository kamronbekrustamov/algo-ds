from typing import List, Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        result = []

        def dfs(node, remaining_sum, path):
            if not node:
                return

            # Add the current node to the path we are building.
            path.append(node.val)

            # Check if the current node is a leaf and if the path sum is correct.
            is_leaf = not node.left and not node.right
            if is_leaf and remaining_sum == node.val:
                # Found a valid path. Add a *copy* of the current path to the result.
                # A copy is needed because `path` will be modified during backtracking.
                result.append(list(path))

            # Recurse on the left and right children.
            dfs(node.left, remaining_sum - node.val, path)
            dfs(node.right, remaining_sum - node.val, path)

            # Backtrack: remove the current node from the path to explore other branches.
            path.pop()

        dfs(root, targetSum, [])
        return result