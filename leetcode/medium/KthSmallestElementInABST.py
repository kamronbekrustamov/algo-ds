from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        """
        Finds the k-th smallest element in a Binary Search Tree (BST).

        The key property of a BST is that an in-order traversal visits the nodes
        in ascending order of their values. This solution uses an iterative
        in-order traversal to find the k-th smallest element.

        The algorithm uses a stack to simulate the recursion of an in-order traversal.
        It traverses down the left subtree, adding nodes to the stack. When it can't
        go left anymore, it pops a node, processes it (this is the next smallest
        element), and then moves to its right subtree.

        Time Complexity: O(H + k), where H is the height of the tree. In the worst
        case of a skewed tree, this is O(N).
        Space Complexity: O(H) for the stack. In the worst case, O(N).

        Args:
            root: The root of the BST.
            k: The rank of the element to find (1-indexed).

        Returns:
            The value of the k-th smallest element.
        """
        stack = []
        curr = root
        
        while curr or stack:
            # Traverse as far left as possible, adding nodes to the stack.
            while curr:
                stack.append(curr)
                curr = curr.left
            
            # At this point, we have reached the leftmost node of the current subtree.
            # Pop it from the stack. This is the next smallest element in the in-order traversal.
            curr = stack.pop()
            
            # Decrement k. If k becomes 0, we have found our element.
            k -= 1
            if k == 0:
                return curr.val
            
            # Move to the right subtree to continue the in-order traversal.
            curr = curr.right
