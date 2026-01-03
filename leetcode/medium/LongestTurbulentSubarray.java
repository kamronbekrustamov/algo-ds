/**
 * Finds the length of the longest turbulent subarray.
 * A subarray is turbulent if the comparison signs alternate: 
 * (arr[i] > arr[i+1] < arr[i+2] > arr[i+3] ...) or 
 * (arr[i] < arr[i+1] > arr[i+2] < arr[i+3] ...).
 * * This solution uses a single-pass approach to maintain the length of the current 
 * turbulent window, achieving O(N) time complexity.
 */
class Solution {
    /**
     * Calculates the maximum length of a turbulent subarray in the given array.
     * * @param arr The input array of integers.
     * @return The length of the longest turbulent subarray.
     */
    public int maxTurbulenceSize(int[] arr) {
        // Handle null array case.
        if (arr == null) {
            return 0;
        }

        int length = arr.length;
        // Base cases: an array of length 0 or 1 is always turbulent.
        if (length < 2) {
            return length;
        }

        // maxLen: The overall maximum length of a turbulent subarray found so far.
        int maxLen = 1;
        
        // currentLen: The length of the turbulent subarray ending at the current index i.
        int currentLen = 1;

        // prevSign: Stores the sign of the comparison arr[i-2] vs arr[i-1].
        // 1 if arr[i-2] < arr[i-1] (upward trend)
        // -1 if arr[i-2] > arr[i-1] (downward trend)
        // 0 if arr[i-2] == arr[i-1] (flat or not enough history)
        int prevSign = 0;

        // Iterate from the second element (i=1) to compare arr[i-1] with arr[i].
        for (int i = 1; i < length; i++) {
            int currentSign = 0;
            
            // Determine the comparison sign for the current pair (arr[i-1], arr[i]).
            if (arr[i - 1] < arr[i]) {
                currentSign = 1; // Upward: arr[i-1] < arr[i]
            } else if (arr[i - 1] > arr[i]) {
                currentSign = -1; // Downward: arr[i-1] > arr[i]
            }
            // If arr[i-1] == arr[i], currentSign remains 0.

            // Case 1: Alternating signs (Turbulence continues)
            // The condition for continuing a turbulent subarray of length L is:
            // currentSign != 0 AND currentSign == -prevSign
            // For example, if prevSign was 1 (<), we need currentSign to be -1 (>)
            if (currentSign != 0 && currentSign == -prevSign) {
                // The current element extends the turbulent subarray.
                currentLen++;
            } 
            // Case 2: Signs do not alternate (Turbulence breaks or comparison is flat)
            else {
                if (currentSign == 0) {
                    // Current pair is flat (arr[i-1] == arr[i]). 
                    // This resets the turbulent subarray to the single element arr[i]
                    // (if arr[i] is the end of the array) or arr[i] and all subsequent 
                    // equal elements. maxLen is already 1 for single elements, so 
                    // we reset currentLen to 1, as the next comparison starts fresh.
                    currentLen = 1;
                } else {
                    // Non-alternating but not flat (e.g., prev: <, current: < or prev: >, current: >)
                    // The turbulence breaks. The new turbulent subarray starts with 
                    // arr[i-1] and arr[i], giving a length of 2.
                    currentLen = 2;
                }
            }
            
            // Update the overall maximum length found.
            maxLen = Math.max(maxLen, currentLen);
            
            // Prepare for the next iteration: the current sign becomes the previous sign.
            prevSign = currentSign;
        }

        return maxLen;
    }
}