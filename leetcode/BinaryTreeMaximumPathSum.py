from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        """
        Finds the maximum path sum in a binary tree. A path can start and end at any
        node in the tree and must contain at least one node.

        This solution uses a recursive Depth-First Search (DFS) approach.
        The core idea is to solve for each node:
        1. What is the maximum path sum that can be extended *upwards* to its parent?
           This is a "straight" path from the node downwards.
        2. What is the maximum path sum that includes the current node as the highest
           point (the "root" of the path)? This path can "split" and go down both
           left and right subtrees. This value is a candidate for the overall max sum.
        """
        # Use an instance variable to store the maximum path sum found so far.
        # Initialize with negative infinity to handle trees with all negative values.
        self.max_sum = float('-inf')

        def dfs(node: Optional[TreeNode]) -> int:
            """
            Recursively computes path sums.
            Returns: The maximum path sum starting at `node` and going downwards.
            Updates: `self.max_sum` with the maximum path sum found anywhere in the subtree.
            """
            if not node:
                return 0

            # Get the max gain from the left and right subtrees.
            # We use max(..., 0) because we don't want to include sub-paths with negative sums.
            left_gain = max(dfs(node.left), 0)
            right_gain = max(dfs(node.right), 0)

            # Update the overall maximum path sum. This path includes the current node
            # and can potentially "split" across its left and right children.
            self.max_sum = max(self.max_sum, node.val + left_gain + right_gain)

            # Return the maximum gain for a path that can be extended upwards.
            # This path cannot split, so it must include the node and at most one child.
            return node.val + max(left_gain, right_gain)

        dfs(root)
        return self.max_sum
