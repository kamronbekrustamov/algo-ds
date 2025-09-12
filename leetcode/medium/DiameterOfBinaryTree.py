from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        """
        Calculates the diameter of a binary tree.

        The diameter is the length of the longest path between any two nodes in a tree.
        This path may or may not pass through the root. The length is the number of edges.

        This solution uses a recursive Depth-First Search (DFS) approach. For each node,
        we calculate two things:
        1. The depth of the node: The longest path (number of edges) from this node
           downwards to a leaf. This value is returned to the parent caller.
        2. The diameter passing through the node: This is the sum of the depths of its
           left and right subtrees. This value is a candidate for the overall maximum
           diameter and is used to update a global tracker.
        """
        # Use an instance variable to store the maximum diameter found.
        self.diameter = 0

        def dfs(node: Optional[TreeNode]) -> int:
            """
            Recursively calculates the depth of a subtree.
            Returns: The depth of the subtree rooted at `node` (number of edges).
            Updates: `self.diameter` with the longest path found so far.
            """
            # The depth of a null tree is -1. This base case makes the calculation
            # for a leaf node (1 + max(-1, -1)) correctly result in 0.
            if not node:
                return -1

            # Recursively find the depth of the left and right subtrees.
            left_depth = dfs(node.left)
            right_depth = dfs(node.right)

            # The diameter at the current node is the sum of the depths of its left and right children, plus the two edges connecting them to this node.
            self.diameter = max(self.diameter, left_depth + right_depth + 2)

            # The depth of the current node is 1 + the depth of its deeper child.
            return 1 + max(left_depth, right_depth)

        dfs(root)
        return self.diameter