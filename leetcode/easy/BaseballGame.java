import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Calculates the total score of a baseball game based on a list of operations.
     *
     * This solution simulates the game round by round, using a data structure
     * that acts like a stack to keep track of the valid scores from previous rounds.
     * An ArrayList is used for this purpose as it provides efficient access to the
     * end of the list.
     *
     * Algorithm Breakdown:
     * 1. Initialize an ArrayList `scores` to store the record of valid points.
     * 2. Iterate through each operation in the input array.
     * 3. Use an enhanced switch statement (available in modern Java) to handle
     *    the four types of operations.
     * 4. After processing all operations, iterate through the `scores` list and
     *    sum up all the values to get the total score.
     *
     * Time Complexity: O(N), where N is the number of operations, as we process
     *                  each operation once.
     * Space Complexity: O(N), as in the worst case, we might store a score for
     *                   each operation.
     *
     * @param operations An array of strings representing the game's operations.
     * @return The total score after all operations.
     */
    public int calPoints(String[] operations) {
        // Use an ArrayList to simulate a stack, holding the record of valid scores.
        List<Integer> scores = new ArrayList<>();

        // 2. Iterate through each operation.
        for (String op : operations) {
            // 3. Handle the different operation types using an enhanced switch statement.
            switch (op) {
                case "C" -> {
                    // "C" (Cancel): Remove the last valid score.
                    if (!scores.isEmpty()) {
                        scores.remove(scores.size() - 1);
                    }
                }
                case "D" -> {
                    // "D" (Double): Double the last valid score and record it.
                    if (!scores.isEmpty()) {
                        scores.add(2 * scores.get(scores.size() - 1));
                    }
                }
                case "+" -> {
                    // "+": Sum the last two valid scores and record it.
                    if (scores.size() >= 2) {
                        scores.add(scores.get(scores.size() - 1) + scores.get(scores.size() - 2));
                    }
                }
                default ->
                    // Integer: Parse the number and record it as a new score.
                    scores.add(Integer.parseInt(op));
            }
        }

        // 4. Sum up all the scores in the record.
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }

        return totalScore;
    }
}