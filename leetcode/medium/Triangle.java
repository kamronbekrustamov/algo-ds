import java.util.List;

/**
 * Solves the Minimum Path Sum in a Triangle problem using Dynamic Programming.
 * The solution modifies the input triangle in-place to store intermediate minimum path sums,
 * effectively reducing the required extra space to O(1).
 */
class Solution {
    /**
     * Calculates the minimum path sum from the top to the bottom of the triangle.
     *
     * @param triangle A list of lists representing the triangle of numbers.
     * @return The minimum total path sum.
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // The algorithm uses Dynamic Programming, starting from the second-to-last row
        // and working its way up to the top. This is a bottom-up approach.

        // Start from the second-to-last row (index triangle.size() - 2)
        // because the last row (base) is the starting point for the sums.
        for (int i = triangle.size() - 2; i >= 0; i--) {
            // Iterate through each element in the current row 'i'.
            // Row 'i' has 'i + 1' elements.
            for (int j = 0; j <= i; j++) {
                // Get the current element's value.
                int currentVal = triangle.get(i).get(j);

                // Find the minimum path sum from the two adjacent elements in the row below (i+1).
                // The adjacent elements are at indices 'j' and 'j + 1'.
                int minBelow = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));

                // The new value for triangle.get(i).get(j) is the sum of its original value
                // and the minimum path sum found from its two children in the row below.
                // This updates the element in-place to store the minimum total sum
                // from that point down to the base.
                int newMinTotal = currentVal + minBelow;

                // Update the triangle with the new minimum total sum.
                triangle.get(i).set(j, newMinTotal);
            }
        }

        // After the loops complete, the top element (0, 0) will hold the
        // minimum total sum from the top to the bottom of the triangle.
        return triangle.get(0).get(0);
    }
}