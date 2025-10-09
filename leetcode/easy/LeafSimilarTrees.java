import java.util.ArrayList;
import java.util.List;

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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        findLeafValueSequence(root1, leaves1);
        findLeafValueSequence(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    private void findLeafValueSequence(TreeNode node, List<Integer> leafValues) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leafValues.add(node.val);
        }
        findLeafValueSequence(node.left, leafValues);
        findLeafValueSequence(node.right, leafValues);
    }
}
