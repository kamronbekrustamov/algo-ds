from typing import Optional


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def insertIntoBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
        """
        Inserts a value into a Binary Search Tree (BST).

        This solution uses a recursive approach, which is a natural fit for tree
        traversals and manipulations. The logic leverages the core property of a BST:
        - Values in the left subtree are smaller than the root's value.
        - Values in the right subtree are larger than the root's value.

        Algorithm:
        1.  Base Case: If the current node (`root`) is None, we have found the correct
            position to insert the new value. We create a new `TreeNode` with `val`
            and return it.
        2.  Recursive Step:
            - If `val` is greater than the current node's value, it belongs in the
              right subtree. We recursively call `insertIntoBST` on the right child
              (`root.right`) and assign the result back to `root.right`.
            - If `val` is less than the current node's value, it belongs in the left
              subtree. We do the same for the left child (`root.left`).
        3.  Return the (potentially modified) `root` of the current subtree.

        Time Complexity: O(H), where H is the height of the tree. O(log N) for a balanced
        tree, O(N) for a skewed tree.
        Space Complexity: O(H) due to the recursion stack.
        """
        # Base case: If we've reached an empty spot, insert the new node here.
        if not root:
            return TreeNode(val)

        # Recursive step: Traverse down the tree to find the correct position.
        if val > root.val:
            root.right = self.insertIntoBST(root.right, val)
        else:  # val < root.val
            root.left = self.insertIntoBST(root.left, val)

        return root