import java.util.Set;
import java.util.HashSet;

class Solution {
    /**
     * Checks if there is a continuous subarray of size at least two 
     * whose elements sum up to a multiple of k.
     * * The core idea uses the property of prefix sums and modulo arithmetic:
     * SubarraySum[j...i] = PrefixSum[i] - PrefixSum[j-1].
     * If SubarraySum[j...i] is a multiple of k, then:
     * PrefixSum[i] % k == PrefixSum[j-1] % k.
     * * We use a set to store remainders encountered so far, ensuring that 
     * the remainder from PrefixSum[i] is compared only with remainders 
     * from PrefixSum[j-1] where the indices are at least two apart (i - (j-1) >= 2),
     * which is achieved by delaying the addition of the current remainder.
     *
     * @param nums The input array of non-negative integers.
     * @param k The divisor.
     * @return true if such a subarray exists, false otherwise.
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        // Edge case: A subarray of size >= 2 requires at least 2 elements.
        if (nums == null || nums.length < 2) {
            return false;
        }

        // The set stores the remainders (PrefixSum % k) encountered up to two steps ago.
        Set<Integer> seenRemainders = new HashSet<>();
        
        // previousRemainder will hold (PrefixSum up to i-1) % k 
        // which will be added to the set for the next iteration's check.
        int previousRemainder = 0; 
        
        // currentPrefixSum tracks the running sum of elements.
        int currentPrefixSum = 0;

        // Iterate through the array to calculate prefix sums.
        for (int num : nums) {
            currentPrefixSum += num;
            
            // Calculate the current remainder. Note: The problem statement implies k >= 1.
            // For a robust solution for negative numbers and k, you might use:
            // int currentRemainder = (currentPrefixSum % k + k) % k;
            // Since nums[i] >= 0 and k >= 1, the standard % is sufficient.
            int currentRemainder = currentPrefixSum % k;
            
            // 1. Check for the condition:
            // If the currentRemainder matches one in the set, it means
            // currentPrefixSum % k == PrefixSum[j-1] % k.
            // Since seenRemainders only contains remainders added two steps ago (or more),
            // this guarantees a subarray of length at least 2.
            if (seenRemainders.contains(currentRemainder)) {
                return true;
            }
            
            // 2. Maintain the one-step lag:
            // Add the remainder from the prefix sum ending at the *previous* index (i-1)
            // to the set. This remainder will be checked in the *next* iteration (i+1).
            // This ensures the current remainder (from index i) is compared against
            // remainders from index i-2 or earlier, guaranteeing length >= 2.
            seenRemainders.add(previousRemainder);
            
            // 3. Update for the next iteration:
            previousRemainder = currentRemainder;
        }
        
        return false;
    }
}