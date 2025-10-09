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
    private Integer prev_val;
    private int min_diff;

    public int minDiffInBST(TreeNode root) {
        prev_val = null;
        min_diff = Integer.MAX_VALUE;
        inorder_traverse(root);
        return min_diff;
    }

    private void inorder_traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder_traverse(node.left);

        if (prev_val != null) {
            min_diff = Math.min(min_diff, node.val - prev_val);
        }
        prev_val = node.val;

        inorder_traverse(node.right);
    }
}
