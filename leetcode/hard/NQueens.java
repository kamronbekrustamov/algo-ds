import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private List<List<String>> result;
    private int n;
    private int[] board; // board[c] = r, where c is the column and r is the row of the queen.
    private Set<Integer> occupiedRows; // Stores occupied rows
    private Set<Integer> posDiagonals; // Stores r + c for positive diagonals
    private Set<Integer> negDiagonals; // Stores r - c for negative diagonals

    /**
     * Solves the N-Queens puzzle to place N non-attacking queens on an N×N chessboard.
     *
     * This solution uses a backtracking algorithm with sets for O(1) attack checks.
     * The `backtrack` function recursively places queens column by column.
     *
     * Algorithm:
     * 1. Initialize `result` (list of solutions), `n` (board size), `board` (1D array to store queen positions),
     *    and three `HashSet`s: `occupiedRows`, `posDiagonals`, `negDiagonals` for efficient conflict checking.
     * 2. The `backtrack(col)` function:
     *    a. Base Case: If `col == n`, all queens have been successfully placed. Call `createBoardSnapshot()`
     *       to convert the current `board` state into the required 2D string format and add it to `result`.
     *    b. Recursive Step: Iterate through each `row` from 0 to `n-1` for the current `col`.
     *       i. Check for conflicts: If the current position `(row, col)` is attacked (i.e., `row` is in `occupiedRows`,
     *          `row + col` is in `posDiagonals`, or `row - col` is in `negDiagonals`), skip this position.
     *       ii. If safe, "place" the queen:
     *           - Set `board[col] = row`.
     *           - Add `row`, `row + col`, and `row - col` to their respective sets.
     *       iii. Recursively call `backtrack(col + 1)` to place the next queen.
     *       iv. "Backtrack": After the recursive call returns, remove the queen by undoing the changes:
     *           - Remove `row`, `row + col`, and `row - col` from their respective sets.
     *           - Reset `board[col] = -1`.
     * 3. The `createBoardSnapshot()` function converts the 1D `board` array into a `List<String>` representation
     *    of the chessboard, where 'Q' denotes a queen and '.' denotes an empty square.
     *
     * @param n The size of the chessboard (N x N).
     * @return A list of lists of strings, representing all distinct solutions to the N-Queens puzzle.
     *
     * Time Complexity: Roughly O(N!), as in the worst case, it explores N! permutations.
     * Space Complexity: O(N^2) for storing the board and O(N) for the recursion stack and sets.
     */
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        result = new ArrayList<>();
        board = new int[n];
        occupiedRows = new HashSet<>();
        posDiagonals = new HashSet<>();
        negDiagonals = new HashSet<>();

        // Initialize board with -1 to indicate no queen placed
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }

        backtrack(0);
        return result;
    }

    private void createBoardSnapshot() {
        List<String> snapshot = new ArrayList<>();
        for (int r : board) {
            StringBuilder rowString = new StringBuilder();
            for (int c = 0; c < n; c++) {
                if (c == r) { // board[col] = row, so if c == r, it means queen is at (r, c)
                    rowString.append('Q');
                } else {
                    rowString.append('.');
                }
            }
            snapshot.add(rowString.toString());
        }
        result.add(snapshot);
    }

    private void backtrack(int col) {
        // Base case: If all queens are placed, we have a valid solution.
        if (col == n) {
            createBoardSnapshot();
            return;
        }

        // Try placing a queen in each row of the current column.
        for (int row = 0; row < n; row++) {
            // Check if the current position (row, col) is under attack in O(1) time.
            if (occupiedRows.contains(row) || posDiagonals.contains(row + col) || negDiagonals.contains(row - col)) {
                continue; // This position is not safe, try the next row.
            }

            // Place the queen.
            board[col] = row;
            occupiedRows.add(row);
            posDiagonals.add(row + col);
            negDiagonals.add(row - col);

            // Recurse to the next column.
            backtrack(col + 1);

            // Backtrack: Remove the queen to explore other possibilities.
            occupiedRows.remove(row);
            posDiagonals.remove(row + col);
            negDiagonals.remove(row - col);
            board[col] = -1;
        }
    }
}
