/**
 * Given a binary tree root, a node X in the tree is named a "Good Node" 
 * if in the path from the root to X, there are no nodes with a value greater than X.
 * * This solution uses a Depth First Search (DFS), specifically a preorder traversal, 
 * to efficiently count all good nodes.
 *
 * Time Complexity: O(N), where N is the number of nodes in the binary tree. 
 * Every node is visited exactly once.
 * Space Complexity: O(H), where H is the height of the tree. This is the 
 * space required for the recursion stack. In the worst case 
 * (skewed tree), H can be N, and in the best case (balanced tree), 
 * H is log(N).
 */
class Solution {
    
    /**
     * Public method to initiate the count of good nodes.
     * * @param root The root node of the binary tree.
     * @return The total count of good nodes in the tree.
     */
    public int goodNodes(TreeNode root) {
        // Start the traversal from the root. 
        // The maximum value encountered on the path to the root's parent is conceptually 
        // Integer.MIN_VALUE, as the root is always a good node.
        return countGoodNodesDFS(root, Integer.MIN_VALUE);
    }

    /**
     * Private helper method that performs the DFS (preorder traversal).
     * * @param node The current node being processed.
     * @param maxValPath The maximum value encountered in the path from the root 
     * down to the current node's parent.
     * @return The total count of good nodes in the subtree rooted at 'node'.
     */
    private int countGoodNodesDFS(TreeNode node, int maxValPath) {
        // Base case: If the current node is null, there are no good nodes in this branch.
        if (node == null) {
            return 0;
        }

        // 1. Check if the current node is a "Good Node".
        // A node is good if its value is greater than or equal to the maximum 
        // value encountered on the path *above* it.
        int count = 0;
        if (node.val >= maxValPath) {
            // This is a "Good Node", so increment the count for this branch.
            count = 1;
        }

        // 2. Determine the new maximum value to pass down to the children.
        // The new maximum is the greater of the current node's value and the 
        // maximum value passed down from the parent.
        int newMaxValPath = Math.max(node.val, maxValPath);

        // 3. Recurse on the left and right children and accumulate the counts.
        // The total count is: (current node's count) + (left subtree's count) + (right subtree's count).
        count += countGoodNodesDFS(node.left, newMaxValPath);
        count += countGoodNodesDFS(node.right, newMaxValPath);

        return count;
    }
}