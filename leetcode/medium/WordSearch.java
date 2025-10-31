/**
 * Solution class for the Word Search problem.
 * It uses Depth-First Search (DFS) with backtracking to find if a word exists
 * in a 2D grid of characters (the board).
 */
class Solution {

    // The dimensions of the board and the character array of the word are now local
    // to the exist method or passed into the recursive function for better encapsulation,
    // reducing the number of class-level fields.

    /**
     * Checks if a given word exists in the grid of characters.
     * The word can be constructed from letters of sequentially adjacent cells,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     *
     * @param board The 2D grid of characters (the board).
     * @param word The word to search for.
     * @return true if the word exists, false otherwise.
     */
    public boolean exist(char[][] board, String word) {
        // --- Input Validation and Edge Cases ---
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        if (word == null || word.isEmpty()) {
            return true;
        }

        final int height = board.length;
        final int width = board[0].length;
        final char[] wordArray = word.toCharArray();

        // Optimization: Early exit if the word is longer than the total number of cells
        if (wordArray.length > height * width) {
             return false;
        }

        // --- DFS Initiation ---
        // Iterate over every cell to start the DFS for the first letter of the word
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Start DFS only if the current cell matches the first character
                if (board[i][j] == wordArray[0] && dfs(board, i, j, 0, height, width, wordArray)) {
                    return true;
                }
            }
        }

        return false;
    }

    // ---
    /**
     * Performs a Depth-First Search (DFS) with backtracking to find the word.
     * This function mutates the board in place ('#' character) to mark visited cells,
     * and then restores the character (backtracking).
     *
     * @param board The 2D character grid.
     * @param row The current row index.
     * @param col The current column index.
     * @param index The current index in the word being searched for.
     * @param height The height of the board (cached for boundary checks).
     * @param width The width of the board (cached for boundary checks).
     * @param wordArray The word as a character array (cached for access).
     * @return true if the word path is found starting from (row, col) at this index, false otherwise.
     */
    private boolean dfs(char[][] board, int row, int col, int index, final int height, final int width, final char[] wordArray) {
        // Base case 1: All characters in the word have been successfully matched.
        if (index == wordArray.length) {
            return true;
        }

        // Base case 2: Current cell is out of bounds OR the character doesn't match the required letter
        // OR the cell has already been visited (marked as '#').
        if (row < 0 || row >= height || col < 0 || col >= width || board[row][col] != wordArray[index]) {
            return false;
        }

        // Mark the current cell as visited to prevent reuse in the current path.
        // This is the in-place tracking/mutation technique.
        char originalChar = board[row][col];
        board[row][col] = '#';

        // Recursive step: Try all four adjacent directions (up, down, left, right).
        // The search continues for the NEXT character in the word (index + 1).
        boolean found = dfs(board, row + 1, col, index + 1, height, width, wordArray) || // Down
                        dfs(board, row - 1, col, index + 1, height, width, wordArray) || // Up
                        dfs(board, row, col + 1, index + 1, height, width, wordArray) || // Right
                        dfs(board, row, col - 1, index + 1, height, width, wordArray);   // Left

        // Backtracking: Restore the character in the cell.
        // This is crucial so that other starting paths can use this cell.
        board[row][col] = originalChar;

        return found;
    }
}