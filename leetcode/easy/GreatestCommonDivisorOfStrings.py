class Solution:
    def _gcd(self, a: int, b: int) -> int:
        """
        Calculates the greatest common divisor (GCD) of two non-negative integers
        using the Euclidean algorithm.
        """
        while b:
            a, b = b, a % b
        return a

    def gcdOfStrings(self, str1: str, str2: str) -> str:
        """
        Finds the largest string X such that X divides both str1 and str2.
        A string X divides string S if S can be formed by concatenating X with itself one or more times.

        Args:
            str1: The first input string.
            str2: The second input string.

        Returns:
            The largest string X that divides both str1 and str2, or an empty string if no such string exists.
        """
        
        # Handle cases where one or both strings are empty.
        # If either string is empty, there cannot be a common non-empty divisor string.
        if not str1 or not str2:
            return ""

        # Condition for existence of a common divisor string:
        # If a common divisor string X exists, then str1 = X + X + ... + X (a times)
        # and str2 = X + X + ... + X (b times).
        # This implies that str1 + str2 must be equal to str2 + str1.
        # If this condition is not met, no common divisor string exists.
        if str1 + str2 != str2 + str1:
            return ""
        
        # If a common divisor string exists, its length must be the greatest common divisor
        # of the lengths of str1 and str2.
        
        # Calculate the greatest common divisor of the lengths of str1 and str2
        # using the custom _gcd function.
        gcd_length = self._gcd(len(str1), len(str2))
        
        # The greatest common divisor string is the prefix of str1 (or str2)
        # with the calculated gcd_length.
        return str1[:gcd_length]
