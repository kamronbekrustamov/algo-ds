import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// java.util.Map.Entry is not strictly needed for the enhanced for loop over entrySet()
// We also don't need java.util.Arrays for the logic, but it's often imported.

/**
 * Solves the "Top K Frequent Elements" problem using the Bucket Sort technique.
 * This method achieves an optimal time complexity of O(N), where N is the number
 * of elements in the input array.
 */
class Solution {
    /**
     * Finds the k most frequently occurring elements in the array nums.
     *
     * @param nums The input array of integers.
     * @param k The number of most frequent elements to find.
     * @return An array containing the k most frequent elements.
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Calculate the frequency of each number (Time: O(N))
        // The key is the number, and the value is its frequency (count).
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            // Use getOrDefault to simplify the frequency counting logic.
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. Initialize a Bucket Array (Time: O(N))
        // The index represents the frequency, and the list at that index
        // stores the numbers that have that exact frequency.
        // The maximum possible frequency is nums.length.
        // We use an array of Lists, where the size is N + 1 (for frequencies 0 to N).
        // Using an array of List<Integer> and initializing elements as null is also an option,
        // but initializing all lists upfront simplifies step 3's logic.
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 3. Place numbers into the correct frequency bucket (Time: O(M), where M is the number of unique elements, M <= N)
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int number = entry.getKey();
            int freq = entry.getValue();
            // Place the number into the bucket corresponding to its frequency.
            buckets[freq].add(number);
        }

        // 4. Collect the results from the buckets, starting from the highest frequency (Time: O(N))
        List<Integer> resultList = new ArrayList<>();
        // Iterate backwards from the largest possible frequency (nums.length) down to 1.
        for (int i = nums.length; i >= 1; i--) {
            // Check if the current frequency bucket is non-empty.
            if (!buckets[i].isEmpty()) {
                // Add all numbers in the current bucket to the result.
                for (int number : buckets[i]) {
                    if (k > 0) {
                        resultList.add(number);
                        k--;
                    } else {
                        // Optimization: if k reaches 0, we have found the top k elements.
                        break;
                    }
                }
            }
            // If k reaches 0 after processing a bucket, we stop the outer loop.
            if (k == 0) {
                break;
            }
        }

        // 5. Convert the List<Integer> to int[] (Time: O(K))
        // Instead of using streams, a simple loop is generally faster and avoids boxing/unboxing overhead.
        int[] finalResult = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            finalResult[i] = resultList.get(i);
        }

        return finalResult;
    }
}