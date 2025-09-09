from collections import deque

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string using level-order traversal (BFS).

        This method traverses the tree level by level. For each node encountered,
        its value is added to the result. If a node is None, a special marker '#'
        is used to signify a null child. This ensures the tree structure is
        fully preserved.

        :type root: TreeNode
        :rtype: str
        """
        if not root:
            return ""

        queue = deque([root])
        result = []

        while queue:
            node = queue.popleft()
            if node:
                result.append(str(node.val))
                # Add children to the queue regardless of whether they are None.
                queue.append(node.left)
                queue.append(node.right)
            else:
                # Append a null marker for None nodes.
                result.append("#")

        return ",".join(result)

    def deserialize(self, data):
        """Decodes your encoded data to a tree.

        This method reconstructs the tree from a string that was created using
        level-order traversal. It uses a queue to build the tree level by level,
        mirroring the serialization process. An iterator is used to consume
        the node values from the input string, which is cleaner than manual
        index management.

        :type data: str
        :rtype: TreeNode
        """
        if not data:
            return None

        # Use an iterator for cleaner traversal of serialized values.
        vals_iter = iter(data.split(","))
        root = TreeNode(int(next(vals_iter)))
        queue = deque([root])

        # Continue building the tree as long as there are parent nodes in the queue.
        while queue:
            node = queue.popleft()

            # Reconstruct the left child.
            left_val = next(vals_iter)
            if left_val != "#":
                node.left = TreeNode(int(left_val))
                queue.append(node.left)

            # Reconstruct the right child.
            right_val = next(vals_iter)
            if right_val != "#":
                node.right = TreeNode(int(right_val))
                queue.append(node.right)

        return root

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))