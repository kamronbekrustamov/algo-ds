from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def convertBST(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        # We use a reverse in-order traversal (Right -> Root -> Left) to visit
        # nodes in descending order. A running sum is maintained to update node values.
        current_sum = 0

        def traverse(node: Optional[TreeNode]):
            nonlocal current_sum
            if not node:
                return

            # 1. Traverse the right subtree first (all values are greater).
            traverse(node.right)

            # 2. Visit the root. Update the sum and the node's value.
            current_sum += node.val
            node.val = current_sum

            # 3. Traverse the left subtree (all values are smaller).
            traverse(node.left)

        traverse(root)
        return root