import java.util.ArrayDeque;
import java.util.Deque;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> nodeDeque = new ArrayDeque<>();
        Deque<Integer> depthDeque = new ArrayDeque<>();

        nodeDeque.add(root);
        depthDeque.add(1);

        while (!nodeDeque.isEmpty()) {
            TreeNode node = nodeDeque.poll();
            int depth = depthDeque.poll();

            if (node.left == null && node.right == null) {
                return depth;
            }

            if (node.left != null) {
                nodeDeque.add(node.left);
                depthDeque.add(depth + 1);
            }
            if (node.right != null) {
                nodeDeque.add(node.right);
                depthDeque.add(depth + 1);
            }
        }
        return 0; // Should not be reached
    }
}
