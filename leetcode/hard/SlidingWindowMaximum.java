import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Finds the maximum value in each sliding window of size k.
     *
     * This function uses the optimal O(n) approach with a double-ended queue (deque).
     * The deque stores indices of the numbers in the current window. It is maintained
     * in such a way that it is always monotonically decreasing based on the values
     * at those indices. This ensures that the index of the maximum element in the
     * current window is always at the front of the deque.
     *
     * Algorithm:
     * 1. Initialize an empty `result` list to store the maximums of each window.
     * 2. Initialize an empty `Deque<Integer>` (e.g., `ArrayDeque`) to store indices.
     * 3. Iterate through the `nums` array with `i` representing the right edge of the window.
     * 4. **Remove outdated indices:** If the index at the front of the deque (`deque.peekFirst()`) is
     *    no longer part of the current window (i.e., `deque.peekFirst() <= i - k`), remove it from the front.
     * 5. **Maintain decreasing order:** Remove indices from the back of the deque (`deque.peekLast()`) whose
     *    corresponding values (`nums[deque.peekLast()]`) are less than the current element `nums[i]`.
     *    This is because if `nums[j] < nums[i]` and `j < i`, `nums[j]` can never be the maximum
     *    in any future window that includes `nums[i]`.
     * 6. **Add current index:** Add the current element's index `i` to the back of the deque.
     * 7. **Record maximum:** Once the window is fully formed (i.e., `i >= k - 1`), the element at the
     *    index `deque.peekFirst()` is the maximum for the current window. Add `nums[deque.peekFirst()]`
     *    to the `result` list.
     * 8. Convert the `result` list to an `int[]` array and return it.
     *
     * @param nums A list of integers.
     * @param k The size of the sliding window.
     * @return A list of integers, where each element is the maximum of a sliding window.
     *
     * Time Complexity: O(N), where N is the length of `nums`. Each element is added and removed from the deque at most once.
     * Space Complexity: O(K), as the deque stores at most `k` elements.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // 1. Remove indices from the front (left) of the deque that are no longer
            //    part of the current window.
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 2. Remove indices from the back (right) of the deque whose corresponding
            //    values are less than the current element `nums[i]`.
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. Add the current element's index to the back of the deque.
            deque.offerLast(i);

            // 4. The front of the deque always holds the index of the maximum element
            //    for the current window. Once the window is full (i.e., `i >= k - 1`),
            //    we can start adding the maximums to our result list.
            if (i >= k - 1) {
                result.add(nums[deque.peekFirst()]);
            }
        }

        // Convert List<Integer> to int[]
        int[] finalResult = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            finalResult[i] = result.get(i);
        }
        return finalResult;
    }
}
