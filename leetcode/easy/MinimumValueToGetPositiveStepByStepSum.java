class Solution {
    public int minStartValue(int[] nums) {
        int min_running_sum = 0;
        int running_sum = 0;

        for (int num : nums) {
            running_sum += num;
            min_running_sum = Math.min(min_running_sum, running_sum);
        }

        return 1 - min_running_sum;
    }
}
