class Solution {
    /**
     * Given an array of non-negative integers and an integer k, this function
     * splits the array into k non-empty continuous subarrays to minimize the
     * largest sum among these subarrays.
     *
     * The problem is solved using binary search on the answer. The possible values for
     * the largest sum range from the maximum element in the array to the total sum
     * of all elements in the array.
     *
     * Algorithm:
     * 1. Determine the search space for the binary search:
     *    - `low`: The minimum possible value for the largest subarray sum is the maximum
     *             single element in the `nums` array (since each element must belong to a subarray).
     *    - `high`: The maximum possible value for the largest subarray sum is the total sum
     *              of all elements in the `nums` array (this occurs when `k=1`).
     * 2. Initialize `min_largest_sum` to `high` (the worst-case largest sum).
     * 3. Perform binary search within the range `[low, high]`:
     *    a. Calculate `mid = low + (high - low) / 2`.
     *    b. Check if it's possible to split the array into `k` or fewer subarrays such that
     *       the sum of each subarray does not exceed `mid`. This is done by iterating through `nums`:
     *       i. Initialize `subarrays_count = 1` and `current_sum = 0`.
     *       ii. For each `num` in `nums`:
     *           - Add `num` to `current_sum`.
     *           - If `current_sum` exceeds `mid`, it means we must start a new subarray.
     *             Increment `subarrays_count` and set `current_sum = num`.
     *    c. If `subarrays_count <= k`:
     *       - It means `mid` is a feasible maximum sum, and we might be able to achieve an even smaller sum.
     *       - Update `min_largest_sum = mid` and set `high = mid - 1` to search in the lower half.
     *    d. Else (`subarrays_count > k`):
     *       - It means `mid` is too small; we need to allow for a larger subarray sum.
     *       - Set `low = mid + 1` to search in the upper half.
     * 4. Return `min_largest_sum`.
     *
     * @param nums A list of non-negative integers.
     * @param k The number of subarrays to split the array into.
     * @return The minimized largest sum among the subarrays.
     *
     * Time Complexity: O(N log(Sum)), where N is the length of `nums` and Sum is the total sum of `nums`.
     *                  The binary search performs `log(Sum)` iterations, and each iteration takes O(N) time.
     * Space Complexity: O(1).
     */
    public int splitArray(int[] nums, int k) {
        long low = 0;
        long high = 0;
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        long min_largest_sum = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            int subarrays_count = 1;
            long current_sum = 0;
            for (int num : nums) {
                current_sum += num;
                if (current_sum > mid) {
                    subarrays_count++;
                    current_sum = num;
                }
            }

            if (subarrays_count <= k) {
                min_largest_sum = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) min_largest_sum;
    }
}
