import java.util.ArrayList;
import java.util.List;

/**
 * Generates the n-bit Gray code sequence.
 * A Gray code is a sequence of binary numbers where any two successive values 
 * differ in only one bit (a single-bit change).
 */
class Solution {
    /**
     * Computes the Gray code sequence for n bits.
     * The sequence has 2^n numbers, starting from 0.
     * * The Gray code of a number 'i' can be mathematically derived as i ^ (i / 2).
     * This method leverages the reflective property and iterative construction.
     * * @param n The number of bits (1 <= n <= 16 typically, though higher values work).
     * @return A List of Integers representing the n-bit Gray code sequence.
     */
    public List<Integer> grayCode(int n) {
        // Initialize the list with the base case: 0-bit Gray code is [0].
        // For n=1, the sequence is [0, 1].
        List<Integer> result = new ArrayList<>();
        result.add(0);
        
        // 'head' is the value to be added to the reflected sequence.
        // It starts at 1 and represents 2^(i-1) in the i-th iteration.
        int head = 1;
        
        // The loop iterates 'n' times, from generating 1-bit code up to n-bit code.
        for (int i = 0; i < n; i++) {
            // Get the current size of the list (2^i).
            int currentSize = result.size();
            
            // Reflective step: add the existing sequence in reverse order, 
            // but with 'head' (which is 2^i) added to each element.
            // This is the core principle of iterative Gray code generation.
            // G(i) = G(i-1) followed by (G_reflected(i-1) + 2^(i-1))
            for (int j = currentSize - 1; j >= 0; j--) {
                result.add(result.get(j) + head);
            }
            
            // Double the head value for the next iteration (head = 2^(i+1)).
            head <<= 1; // Same as head = head * 2;
        }
        
        return result;
    }
}