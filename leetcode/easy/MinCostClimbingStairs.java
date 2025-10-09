class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int cost_two_steps_back = 0;
        int cost_one_step_back = 0;

        for (int current_cost : cost) {
            int min_cost_to_current = Math.min(cost_one_step_back, cost_two_steps_back) + current_cost;
            cost_two_steps_back = cost_one_step_back;
            cost_one_step_back = min_cost_to_current;
        }

        return Math.min(cost_one_step_back, cost_two_steps_back);
    }
}
