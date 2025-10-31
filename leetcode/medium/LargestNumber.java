import java.util.Arrays;

/**
 * Solution for the "Largest Number" problem.
 * This class provides a method to rearrange a list of non-negative integers
 * into the largest possible number.
 */
class Solution {

    /**
     * Rearranges the given non-negative integers to form the largest possible number.
     *
     * The core idea is to sort the numbers based on a custom comparison logic:
     * for any two numbers 'a' and 'b', 'a' comes before 'b' if the string
     * concatenation 'b + a' results in a lexicographically smaller string than 'a + b'.
     * This ensures that when concatenated, they form the largest prefix possible.
     *
     * @param nums An array of non-negative integers.
     * @return The largest possible number formed by the digits, returned as a string.
     */
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }

        // 1. Convert the integer array to a list of strings.
        // This is necessary for the custom string-based comparison.
        String[] numStrings = Arrays.stream(nums)
                                    .mapToObj(String::valueOf)
                                    .toArray(String[]::new);

        // 2. Custom Sort: Sort the strings using a custom comparator.
        // The comparison (b + a) vs (a + b) determines the optimal order.
        // Example: a="3", b="30". (b+a)="303", (a+b)="330". Since "303" > "330" is false,
        // it means (b+a).compareTo(a+b) < 0, so 'a' comes first for descending order.
        // It should be (b+a).compareTo(a+b) > 0 for 'b' to come first.
        // The comparator should place the string 'a' before 'b' if 'a' is "larger" in this custom sense.
        // The current logic using (b + a).compareTo(a + b) achieves a descending sort
        // based on the custom "largest number" criteria.
        Arrays.sort(numStrings, (a, b) -> {
            String order1 = a + b;
            String order2 = b + a;
            // We want descending order, so order2 (b+a) is compared to order1 (a+b).
            // If order2 is lexicographically greater than order1, 'b' should come before 'a',
            // and the comparator should return a positive value.
            return order2.compareTo(order1);
        });

        // 3. Handle the all-zero case.
        // If the largest number is "000..." (e.g., input was {0, 0}), the result should be "0".
        // This check is the most efficient way to handle this edge case.
        if (numStrings[0].equals("0")) {
            return "0";
        }

        // 4. Concatenate the sorted strings to form the final result.
        // Since we used an array for sorting, we can use String.join or a StringBuilder.
        return String.join("", numStrings);
    }
}