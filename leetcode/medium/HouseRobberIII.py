from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def rob(self, root: Optional[TreeNode]) -> int:
        """
        Calculates the maximum amount of money that can be robbed from a binary tree of houses.
        The constraint is that we cannot rob two directly linked houses.
        """
        
        def dfs(node: Optional[TreeNode]) -> tuple[int, int]:
            """
            Performs a depth-first search to determine the maximum loot from a subtree.

            Args:
                node: The root of the subtree.

            Returns:
                A tuple containing two values:
                1. The maximum loot if we rob the current node.
                2. The maximum loot if we do not rob the current node.
            """
            # Base case: an empty node contributes no loot.
            if not node:
                return (0, 0)

            # Recursively call on left and right children to get their loot values.
            left_with, left_without = dfs(node.left)
            right_with, right_without = dfs(node.right)

            # If we rob the current node, we cannot rob its immediate children.
            # The loot is the current node's value plus the loot from not robbing its children.
            with_node = node.val + left_without + right_without

            # If we do not rob the current node, we are free to choose the best option for its children.
            # The maximum loot is the sum of the maximums from the left and right subtrees.
            without_node = max(left_with, left_without) + max(right_with, right_without)
            
            return (with_node, without_node)

        # The final result is the maximum of either robbing or not robbing the root node.
        return max(dfs(root))