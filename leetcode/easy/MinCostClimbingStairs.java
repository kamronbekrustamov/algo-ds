/**
 * Solution for LeetCode problem "Min Cost Climbing Stairs".
 * This class provides a method to calculate the minimum cost to reach the top of the floor.
 */
class Solution {

    /**
     * Calculates the minimum cost to reach the top of the floor.
     * You are given an integer array `cost` where `cost[i]` is the cost of `i`-th step.
     * Once you pay the cost, you can either climb one or two steps.
     * You can either start from step with index 0, or the step with index 1.
     *
     * This problem can be solved using dynamic programming. The key idea is that
     * the minimum cost to reach the current step `i` is the `cost[i]` plus the minimum
     * of the costs to reach step `i-1` or step `i-2`.
     *
     * We can optimize space by only keeping track of the costs for the previous two steps.
     * Let `cost_two_steps_back` be the minimum cost to reach `i-2`.
     * Let `cost_one_step_back` be the minimum cost to reach `i-1`.
     *
     * @param cost An array of integers where `cost[i]` is the cost of the `i`-th step.
     * @return The minimum cost to reach the top of the floor.
     */
    public int minCostClimbingStairs(int[] cost) {
        // `cost_two_steps_back` stores the minimum cost to reach the step two steps before the current one.
        // Initially, to reach step -2 (before index 0), the cost is 0.
        int cost_two_steps_back = 0;
        // `cost_one_step_back` stores the minimum cost to reach the step one step before the current one.
        // Initially, to reach step -1 (before index 0), the cost is 0.
        int cost_one_step_back = 0;

        // Iterate through each step's cost in the array.
        for (int current_cost : cost) {
            // The minimum cost to reach the current step is its own cost plus
            // the minimum cost to reach either the step one step back or two steps back.
            int min_cost_to_current = Math.min(cost_one_step_back, cost_two_steps_back) + current_cost;

            // Update `cost_two_steps_back` to what `cost_one_step_back` was.
            cost_two_steps_back = cost_one_step_back;
            // Update `cost_one_step_back` to the newly calculated `min_cost_to_current`.
            cost_one_step_back = min_cost_to_current;
        }

        // After iterating through all steps, `cost_one_step_back` will hold the minimum cost
        // to reach the last step, and `cost_two_steps_back` will hold the minimum cost
        // to reach the second to last step. The top of the floor can be reached from either
        // the last step or the second to last step. So, we return the minimum of these two.
        return Math.min(cost_one_step_back, cost_two_steps_back);
    }
}
