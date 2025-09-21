from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def removeLeafNodes(self, root: Optional[TreeNode], target: int) -> Optional[TreeNode]:
        """
        Removes all leaf nodes from a binary tree that have a specific target value.
        This process might cause parent nodes to become leaves, which are then also
        removed if they have the target value. This continues until no more such
        leaf nodes can be removed.

        The function uses a post-order traversal (DFS) approach. By processing
        the children first, we ensure that when we evaluate a node, its subtrees
        have already been pruned. If a node becomes a leaf after its children
        are processed and its value matches the target, it can be safely removed.

        Args:
            root: The root of the binary tree.
            target: The value of the leaf nodes to be removed.

        Returns:
            The root of the modified binary tree.
        """
        # Base case: if the node is None, there's nothing to do.
        if not root:
            return None

        # Recursively process the left and right subtrees.
        # The returned (potentially modified) subtrees are re-assigned.
        root.left = self.removeLeafNodes(root.left, target)
        root.right = self.removeLeafNodes(root.right, target)

        # After processing the children, check if the current node has become a leaf
        # and if its value matches the target.
        if not root.left and not root.right and root.val == target:
            # If so, this node should be removed. We do this by returning None
            # to its parent, which will effectively disconnect it from the tree.
            return None

        # If the node is not a target leaf, we keep it.
        return root