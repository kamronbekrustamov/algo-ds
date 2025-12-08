class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLength = nums.length + 1;
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }
        return minLength == nums.length + 1 ? 0 : minLength;
    }
}