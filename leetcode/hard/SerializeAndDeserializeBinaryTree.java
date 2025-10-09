import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Codec {

    /**
     * Encodes a tree to a single string using level-order traversal (BFS).
     *
     * This method traverses the tree level by level. For each node encountered,
     * its value is added to the result. If a node is null, a special marker "#"
     * is used to signify a null child. This ensures the tree structure is
     * fully preserved.
     *
     * @param root The root of the binary tree.
     * @return A string representation of the tree.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each node is visited once.
     * Space Complexity: O(N) in the worst case (e.g., a complete binary tree), for the queue and the result string.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#"; // Represent an empty tree as a single null marker
        }

        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        result.append(root.val);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                result.append(",").append(node.left.val);
                queue.offer(node.left);
            } else {
                result.append(",#");
            }

            if (node.right != null) {
                result.append(",").append(node.right.val);
                queue.offer(node.right);
            } else {
                result.append(",#");
            }
        }
        return result.toString();
    }

    /**
     * Decodes your encoded data to a tree.
     *
     * This method reconstructs the tree from a string that was created using
     * level-order traversal. It uses a queue to build the tree level by level,
     * mirroring the serialization process.
     *
     * @param data The string representation of the tree.
     * @return The root of the reconstructed binary tree.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree, as each node is processed once.
     * Space Complexity: O(N) in the worst case, for the queue and the array of string values.
     */
    public TreeNode deserialize(String data) {
        if (data.equals("#")) {
            return null;
        }

        String[] vals = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < vals.length) {
            TreeNode node = queue.poll();

            // Reconstruct left child
            if (!vals[i].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(node.left);
            }
            i++;

            // Reconstruct right child
            if (i < vals.length && !vals[i].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
