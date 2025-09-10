class Solution:
    def numTrees(self, n: int) -> int:
        # This problem can be solved using dynamic programming. The number of unique BSTs
        # for n nodes is the n-th Catalan number.

        # dp[i] will store the number of unique BSTs that can be formed with i nodes.
        dp = [0] * (n + 1)
        # Base case: There is one way to form a BST with 0 nodes (an empty tree).
        dp[0] = 1

        # Calculate the number of unique BSTs for i nodes, from 1 to n.
        for i in range(1, n + 1):
            # To calculate dp[i], we consider each number from 1 to i as the root.
            # If we pick a root, the remaining nodes form the left and right subtrees.
            # The number of unique BSTs is the product of the possibilities for the
            # left and right subtrees, summed over all possible root choices.
            # The inner loop variable 'j' represents the number of nodes in the left subtree.
            for j in range(i):
                # Number of nodes in left subtree: j
                # Number of nodes in right subtree: i - 1 - j
                # The number of combinations is dp[left_nodes] * dp[right_nodes].
                dp[i] += dp[j] * dp[i - 1 - j]

        # The final answer is the number of unique BSTs for n nodes.
        return dp[n]