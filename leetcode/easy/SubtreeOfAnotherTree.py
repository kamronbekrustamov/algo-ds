from typing import Optional



# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
            
            def isSameTree(p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
                # Base case: If both nodes are None, they are the same.
                if not p and not q:
                    return True
                # If one node is None, or if their values are different, they are not the same.
                if not p or not q or p.val != q.val:
                    return False

                # Recursively check the left and right subtrees.
                return isSameTree(p.left, q.left) and isSameTree(p.right, q.right)
            
            def dfs(root: Optional[TreeNode]) -> bool:
                if not root:
                    return False
                if isSameTree(root, subRoot):
                    return True
                return dfs(root.left) or dfs(root.right)
            
            return dfs(root)
            
            
            