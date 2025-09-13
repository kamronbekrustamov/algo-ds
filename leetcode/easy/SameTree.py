from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        """
        Checks if two binary trees are structurally identical and have the same node values.

        This solution uses a recursive, depth-first approach. Two trees are considered
        the same if their root nodes have the same value, their left subtrees are the
        same, and their right subtrees are the same.

        Algorithm:
        1.  Base Case 1: If both nodes `p` and `q` are None, they are identical. Return True.
        2.  Base Case 2: If one of the nodes is None but the other is not, they are
            not identical. Return False.
        3.  Value Check: If the values of the current nodes `p` and `q` are different,
            the trees are not identical. Return False.
        4.  Recursive Step: If the above checks pass, recursively call the function
            on the left children (`p.left`, `q.left`) and the right children
            (`p.right`, `q.right`). The trees are only identical if both of these
            recursive calls return True.

        Time Complexity: O(N), where N is the number of nodes in the smaller tree.
        Space Complexity: O(H), where H is the height of the tree, due to the recursion stack.
        """
        # Base case: If both nodes are None, they are the same.
        if not p and not q:
            return True
        # If one node is None, or if their values are different, they are not the same.
        if not p or not q or p.val != q.val:
            return False

        # Recursively check the left and right subtrees.
        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)