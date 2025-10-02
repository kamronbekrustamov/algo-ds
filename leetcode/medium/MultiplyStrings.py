class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        """
        Multiplies two non-negative integers represented as strings.

        This solution simulates the "schoolbook" multiplication algorithm.

        Algorithm Breakdown:
        1. Handle the edge case where one number is "0".
        2. Reverse both number strings to simplify processing from least-significant
           digit to most-significant digit.
        3. Create an integer array, `product_digits`, to store the result of the
           multiplication. The max length of the product is `len(num1) + len(num2)`.
        4. Iterate through each digit of `num1` and `num2` (now reversed).
        5. For each pair of digits `d1` from `num1` and `d2` from `num2` at
           indices `i` and `j` respectively, their product belongs in the result
           at index `i + j`. We add the product to this position and handle the carry.
        6. After all multiplications, the `product_digits` array holds the result
           in reverse order.
        7. Convert the array back to a string, handling any leading zeros and
           reversing it to get the final answer.

        Time Complexity: O(m * n), where m and n are the lengths of num1 and num2.
        Space Complexity: O(m + n) for the result array.
        """
        if num1 == "0" or num2 == "0":
            return "0"

        # Reverse strings to make it like grade-school multiplication
        num1_reversed = num1[::-1]
        num2_reversed = num2[::-1]
        len1, len2 = len(num1_reversed), len(num2_reversed)

        # The result of multiplication will have at most len1 + len2 digits.
        product_digits = [0] * (len1 + len2)

        # Multiply each digit of num1 with each digit of num2
        for i in range(len1):
            digit1 = int(num1_reversed[i])
            for j in range(len2):
                digit2 = int(num2_reversed[j])
                
                # The product of digits at indices i and j contributes to the
                # sum at index i+j in the result array.
                sum_at_position = digit1 * digit2 + product_digits[i + j]
                
                # The units digit of the sum is the new digit at this position.
                product_digits[i + j] = sum_at_position % 10
                
                # The carry is added to the next position (i+j+1).
                product_digits[i + j + 1] += sum_at_position // 10

        # --- Format the final result string ---

        # The result is in reverse order in `product_digits`. We need to remove
        # any leading zeros from the end of the array before reversing.
        # Example: 99 * 99 = 9801. Array is [1,0,8,9,0,0]. We need to trim trailing 0s.
        most_significant_digit_idx = len(product_digits) - 1
        while most_significant_digit_idx >= 0 and product_digits[most_significant_digit_idx] == 0:
            most_significant_digit_idx -= 1

        # This case handles if the result was 0, though our initial check should prevent this.
        if most_significant_digit_idx < 0:
            return "0"

        # Join the digits and reverse the string to get the final answer.
        result_str = "".join(map(str, product_digits[0 : most_significant_digit_idx + 1]))
        return result_str[::-1]