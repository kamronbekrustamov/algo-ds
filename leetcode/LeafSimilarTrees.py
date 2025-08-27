from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def leafSimilar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        # These lists will store the sequence of leaf values for each tree.
        leaves_of_tree1 = []
        leaves_of_tree2 = []

        def find_leaf_value_sequence(node: Optional[TreeNode], leaf_values: list):
            """
            Recursively traverses the tree (pre-order) to find all leaf nodes
            and appends their values to the provided list.
            """
            # Base case: If the node is None, there's nothing to do.
            if not node:
                return

            # A node is a leaf if it has no left and no right child.
            if not node.left and not node.right:
                leaf_values.append(node.val)

            # Recursively search in the left and right subtrees.
            find_leaf_value_sequence(node.left, leaf_values)
            find_leaf_value_sequence(node.right, leaf_values)

        # 1. Find the leaf value sequence for the first tree.
        find_leaf_value_sequence(root1, leaves_of_tree1)
        # 2. Find the leaf value sequence for the second tree.
        find_leaf_value_sequence(root2, leaves_of_tree2)
        # 3. Compare the two sequences. If they are identical, the trees are leaf-similar.
        return leaves_of_tree1 == leaves_of_tree2
        