from typing import Optional



# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        """
        Checks if a binary tree `subRoot` is a subtree of another binary tree `root`.

        A subtree of a tree `T` is a tree consisting of a node in `T` and all of its
        descendants in `T`.

        Algorithm:
        1. The main function `isSubtree` traverses the `root` tree recursively.
        2. For each node in `root`, it calls a helper function `_isSameTree` to check
           if the subtree starting at that node is identical to `subRoot`.
        3. If an identical subtree is found, we return True.
        4. If not, we continue the search by recursively calling `isSubtree` on the
           left and right children of the current node.
        5. The recursion stops when we run out of nodes in the `root` tree.

        Time Complexity: O(M * N), where M is the number of nodes in `root` and N is
        the number of nodes in `subRoot`. In the worst case, we call `_isSameTree` (O(N))
        for every node in `root` (O(M)).
        Space Complexity: O(H), where H is the height of the `root` tree, due to the
        recursion stack.
        """
        # Base case: An empty tree is a subtree of any tree.
        if not subRoot:
            return True
        # Base case: If the main tree is empty but subRoot is not, it's impossible.
        if not root:
            return False

        # Check if the tree starting at the current `root` node is the same as `subRoot`.
        if self._isSameTree(root, subRoot):
            return True

        # If not, recursively check if `subRoot` is a subtree of the left OR right child.
        return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)

    def _isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        """Helper function to check if two trees are structurally and valuably identical."""
        # If both nodes are None, they are identical.
        if not p and not q:
            return True
        # If one node is None, or their values differ, they are not identical.
        if not p or not q or p.val != q.val:
            return False

        # Recursively check the left and right subtrees.
        return self._isSameTree(p.left, q.left) and self._isSameTree(p.right, q.right)