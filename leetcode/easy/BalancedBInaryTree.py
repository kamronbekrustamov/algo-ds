from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        """
        Determines if a binary tree is height-balanced.

        A height-balanced binary tree is a binary tree in which the depth of the two
        subtrees of every node never differs by more than one.

        This solution uses a recursive, bottom-up Depth-First Search (DFS) approach.
        The helper function `dfs` serves a dual purpose:
        1. It calculates the height of a subtree.
        2. It signals if a subtree is unbalanced by returning a special value (-1).

        This is more efficient than the naive approach because it stops the recursion
        as soon as an imbalance is found.
        """
        def dfs(node: Optional[TreeNode]) -> int:
            """
            Recursively checks the height and balance of a subtree.
            Returns the height of the subtree if it's balanced, otherwise returns -1.
            """
            # Base case: An empty tree is balanced and has a height of 0.
            if not node:
                return 0

            # Recursively get the height of the left subtree.
            left_height = dfs(node.left)
            # If the left subtree is unbalanced, propagate the unbalanced signal (-1) up.
            if left_height == -1:
                return -1

            # Recursively get the height of the right subtree.
            right_height = dfs(node.right)
            # If the right subtree is unbalanced, propagate the signal up.
            if right_height == -1:
                return -1

            # Check if the current node is unbalanced.
            if abs(left_height - right_height) > 1:
                return -1

            # If balanced, return the height of the current subtree.
            return 1 + max(left_height, right_height)

        # The tree is balanced if the final result from the root is not -1.
        return dfs(root) != -1