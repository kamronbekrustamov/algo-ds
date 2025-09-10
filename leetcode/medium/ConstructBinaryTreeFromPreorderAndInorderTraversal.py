from typing import List, Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        """
        Constructs a binary tree from its preorder and inorder traversal sequences.

        The core idea relies on the properties of these traversals:
        1. The first element in the preorder traversal is always the root of the tree/subtree.
        2. In the inorder traversal, all elements to the left of the root belong to the
           left subtree, and all elements to the right belong to the right subtree.

        This optimized solution avoids the O(N) cost of `list.index()` and list slicing
        by using a hash map for inorder indices and passing pointers to define sub-arrays.
        """
        # Create a map for O(1) lookups of inorder indices.
        inorder_map = {val: i for i, val in enumerate(inorder)}
        
        # A variable to track the current root in the preorder traversal.
        # Using an instance variable to be accessible and modifiable by the helper.
        self.preorder_index = 0

        def build(inorder_left: int, inorder_right: int) -> Optional[TreeNode]:
            """
            Recursively builds the tree for the inorder segment from `inorder_left` to `inorder_right`.
            """
            # Base case: if the inorder segment is empty, there's no subtree to build.
            if inorder_left > inorder_right:
                return None

            # The current root is the next element in the preorder traversal.
            root_val = preorder[self.preorder_index]
            self.preorder_index += 1
            root = TreeNode(root_val)

            # Find the root's position in the inorder traversal to split into subtrees.
            inorder_root_idx = inorder_map[root_val]

            # Recursively build the left and right subtrees.
            root.left = build(inorder_left, inorder_root_idx - 1)
            root.right = build(inorder_root_idx + 1, inorder_right)
            
            return root

        return build(0, len(inorder) - 1)
