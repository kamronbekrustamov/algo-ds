import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {

    /**
     * Reconstructs a binary tree from preorder and inorder traversal arrays.
     *
     * @param preorder The preorder traversal of the tree (Root -> Left -> Right).
     * @param inorder  The inorder traversal of the tree (Left -> Root -> Right).
     * @return The root node of the constructed binary tree.
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Optimization: Use a HashMap to store the index of each value in the inorder array.
        // This allows us to find the root's position in O(1) time instead of O(N).
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildRecursive(preorder, inorderIndexMap, 0, 0, inorder.length - 1);
    }

    /**
     * Recursive helper to build subtrees.
     *
     * @param preorder        The global preorder array.
     * @param inorderIndexMap Map for O(1) lookup of indices in the inorder array.
     * @param preStart        The starting index of the current subtree in the preorder array.
     * @param inStart         The starting index of the current subtree in the inorder array.
     * @param inEnd           The ending index of the current subtree in the inorder array.
     * @return The root of the current subtree.
     */
    private TreeNode buildRecursive(int[] preorder, Map<Integer, Integer> inorderIndexMap, 
                                    int preStart, int inStart, int inEnd) {
        // Base case: If the inorder pointers cross, there are no elements left to construct.
        if (inStart > inEnd) {
            return null;
        }

        // 1. Identify the Root
        // The first element in the current preorder range is always the root.
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // 2. Find Root in Inorder Array
        // This split point determines which elements belong to the left vs right subtree.
        int rootIndexInInorder = inorderIndexMap.get(rootValue);

        // 3. Calculate Left Subtree Size
        // We need this size to determine where the Right Subtree starts in the Preorder array.
        int leftSubtreeSize = rootIndexInInorder - inStart;

        // 4. Recurse
        
        // Build Left Child:
        // - Preorder Start: Next element (preStart + 1)
        // - Inorder Range: From start (inStart) up to the root (rootIndex - 1)
        root.left = buildRecursive(preorder, inorderIndexMap, 
                                   preStart + 1, 
                                   inStart, 
                                   rootIndexInInorder - 1);

        // Build Right Child:
        // - Preorder Start: Jump over the left subtree (preStart + leftSubtreeSize + 1)
        // - Inorder Range: From after the root (rootIndex + 1) to the end (inEnd)
        root.right = buildRecursive(preorder, inorderIndexMap, 
                                    preStart + leftSubtreeSize + 1, 
                                    rootIndexInInorder + 1, 
                                    inEnd);

        return root;
    }
}