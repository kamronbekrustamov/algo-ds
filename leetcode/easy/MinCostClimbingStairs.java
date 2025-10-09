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
        int costTwoStepsBack = 0;
        int costOneStepBack = 0;

        for (int currentCost : cost) {
            int minCostToCurrent = Math.min(costOneStepBack, costTwoStepsBack) + currentCost;
            costTwoStepsBack = costOneStepBack;
            costOneStepBack = minCostToCurrent;
        }

        return Math.min(costOneStepBack, costTwoStepsBack);
    }
}
