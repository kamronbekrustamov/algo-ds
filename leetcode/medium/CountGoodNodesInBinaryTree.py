# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def goodNodes(self, root: TreeNode) -> int:
        """
        Counts the number of "good" nodes in a binary tree.

        A node is considered "good" if on the path from the root to the node,
        there are no nodes with a value greater than the node's value.

        Args:
            root: The root of the binary tree.

        Returns:
            The number of good nodes in the tree.
        """
        
        def preorder(node, largest):
            """
            Performs a preorder traversal to count good nodes.

            Args:
                node: The current node in the traversal.
                largest: The largest value encountered on the path from the root to this node.

            Returns:
                The number of good nodes in the subtree rooted at the current node.
            """
            # Base case: if the node is null, there are no good nodes here.
            if not node:
                return 0
            
            # A node is good if its value is greater than or equal to the largest value
            # seen on the path from the root to its parent.
            count = 1 if node.val >= largest else 0
            
            # Update the largest value for the children.
            largest = max(node.val, largest)
            
            # Recursively count good nodes in the left and right subtrees.
            return count + preorder(node.left, largest) + preorder(node.right, largest)
        
        # Start the traversal from the root with the smallest possible value as the initial largest.
        return preorder(root, float("-inf"))
        