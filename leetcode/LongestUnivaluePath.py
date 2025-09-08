from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def longestUnivaluePath(self, root: Optional[TreeNode]) -> int:
        """
        Finds the length of the longest path where each node in the path has the same value.
        The length of a path is the number of edges.

        This solution uses a recursive Depth-First Search (DFS) approach. The core idea
        is similar to "Binary Tree Maximum Path Sum". For each node, we calculate two things:
        1. The length of the longest univalue path that starts at the node and goes downwards
           (an "arm"). This is the value returned to the parent.
        2. The length of the longest univalue path that has the current node as its highest
           point (it can "split" and go down both left and right). This value is a candidate
           for the overall maximum and is used to update a global tracker.
        """
        # Use an instance variable to store the maximum path length (number of edges).
        self.max_len = 0

        def dfs(node: Optional[TreeNode]) -> int:
            """
            Recursively calculates the longest univalue path.
            Returns: The length of the longest univalue "arm" starting at `node` and going downwards.
            Updates: `self.max_len` with the longest univalue path found anywhere.
            """
            if not node:
                return 0

            # Get the length of the univalue arms from the left and right children.
            left_arm_len = dfs(node.left)
            right_arm_len = dfs(node.right)

            # Calculate the length of the arms extending from the current node.
            # If a child's value doesn't match, the arm length from that side is 0.
            left_path = 1 + left_arm_len if node.left and node.left.val == node.val else 0
            right_path = 1 + right_arm_len if node.right and node.right.val == node.val else 0

            # Update the overall maximum length. The path might "split" at the current node,
            # so its length would be the sum of the left and right arms.
            self.max_len = max(self.max_len, left_path + right_path)

            # Return the length of the longest single arm that can be extended upwards to the parent.
            return max(left_path, right_path)

        dfs(root)
        return self.max_len