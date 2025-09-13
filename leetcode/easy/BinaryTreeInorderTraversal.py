from typing import Optional, List

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        """
        Performs an inorder traversal of a binary tree and returns the node values.

        Inorder traversal follows the sequence: Left -> Root -> Right.
        This recursive solution is a direct implementation of that sequence.

        Algorithm:
        1. Create an empty list `result` to store the node values.
        2. Define a recursive helper function `dfs(node)`.
        3. In the helper function:
           - If the current node is None, return (base case).
           - Recursively call `dfs` on the left child.
           - Append the current node's value to the `result` list.
           - Recursively call `dfs` on the right child.
        4. Start the process by calling `dfs(root)`.
        5. Return the `result` list.

        Time Complexity: O(n), where n is the number of nodes in the tree.
        Space Complexity: O(n) in the worst case (for a skewed tree) due to the recursion stack.
        """
        result = []

        def dfs(node: Optional[TreeNode]):
            """Helper function to perform recursive inorder traversal."""
            if not node:
                return
            # 1. Traverse the left subtree.
            dfs(node.left)
            # 2. Visit the root node.
            result.append(node.val)
            # 3. Traverse the right subtree.
            dfs(node.right)
        dfs(root)

        return result
        