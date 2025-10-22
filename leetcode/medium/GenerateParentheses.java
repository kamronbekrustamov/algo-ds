import java.util.ArrayList;
import java.util.List;

/**
 * Generates all combinations of well-formed parentheses for a given number of pairs 'n'.
 * The problem is solved using a backtracking approach.
 */
class Solution {

    /**
     * Generates all combinations of well-formed parentheses.
     *
     * @param n The number of pairs of parentheses (e.g., n=3 for "((()))", "(()())", etc.).
     * @return A list of strings, each representing a well-formed parentheses combination.
     */
    public List<String> generateParenthesis(int n) {
        // The list to store all valid combinations.
        List<String> result = new ArrayList<>();
        
        // A StringBuilder is used to efficiently build the current parenthesis string during backtracking.
        // It avoids creating many temporary String objects.
        StringBuilder currentString = new StringBuilder();
        
        // Start the backtracking process.
        // openCount and closeCount are initialized to 0.
        generate(result, currentString, n, 0, 0);
        
        return result;
    }

    /**
     * The core recursive backtracking method.
     * It explores the search space and prunes invalid branches.
     *
     * @param result The list to which valid strings are added.
     * @param currentString The current string being built.
     * @param maxPairs The total number of parenthesis pairs (the input 'n').
     * @param openCount The current count of open parentheses ('(') placed.
     * @param closeCount The current count of close parentheses (')') placed.
     */
    private void generate(List<String> result, StringBuilder currentString, int maxPairs, int openCount, int closeCount) {
        
        // 1. Base Case: A valid combination is found when we have placed 'maxPairs' closing parentheses.
        if (closeCount == maxPairs) {
            result.add(currentString.toString());
            return; // Stop recursion for this path
        }

        // 2. Recursive Step 1: Add an Open Parenthesis '('
        // We can place an open parenthesis if we haven't reached the maximum allowed number of open parentheses.
        if (openCount < maxPairs) {
            // Choose: Add '('
            currentString.append('(');
            
            // Explore: Recurse with an incremented open count
            generate(result, currentString, maxPairs, openCount + 1, closeCount);
            
            // Unchoose (Backtrack): Remove the last character to explore other possibilities
            currentString.deleteCharAt(currentString.length() - 1);
        }

        // 3. Recursive Step 2: Add a Close Parenthesis ')'
        // We can place a close parenthesis if and only if it won't create an invalid prefix.
        // This means the number of close parentheses must be less than the number of open parentheses.
        if (closeCount < openCount) {
            // Choose: Add ')'
            currentString.append(')');
            
            // Explore: Recurse with an incremented close count
            generate(result, currentString, maxPairs, openCount, closeCount + 1);
            
            // Unchoose (Backtrack): Remove the last character
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }
}