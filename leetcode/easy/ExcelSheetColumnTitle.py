class Solution:
    def convertToTitle(self, columnNumber: int) -> str:
        """
        Converts a 1-based integer column number to its corresponding Excel-style column title.

        This problem is analogous to converting a number to a base-26 system.
        However, standard base-26 uses digits 0-25, while Excel columns are 1-indexed
        (A=1, Z=26). This requires a slight modification to the standard base conversion algorithm.

        Algorithm:
        1. Loop as long as the columnNumber is greater than 0.
        2. In each iteration, we determine the rightmost character.
           - We subtract 1 from `columnNumber` to map the range [1, 26] to [0, 25].
           - We take the result modulo 26 (`(columnNumber - 1) % 26`) to get the
             0-indexed character code (0 for 'A', 25 for 'Z').
           - We convert this code to a character and append it to our result list.
        3. We then update `columnNumber` for the next iteration by integer dividing
           the decremented value by 26 (`(columnNumber - 1) // 26`). This effectively
           shifts to the next "digit" to the left.
        4. Since we build the string from right to left, we reverse it at the end.

        Example: columnNumber = 28
        - 1st loop: (28-1)%26=1 -> 'B'. result=['B']. columnNumber=(28-1)//26=1.
        - 2nd loop: (1-1)%26=0 -> 'A'. result=['B', 'A']. columnNumber=(1-1)//26=0.
        - Loop ends. Join reversed list: "AB".
        """
        result = []
        while columnNumber > 0:
            # Map 1-26 to 0-25 for modulo operation.
            remainder = (columnNumber - 1) % 26
            # Convert to character and append. 'A' is ASCII 65.
            result.append(chr(65 + remainder))
            # Update columnNumber for the next "digit".
            columnNumber = (columnNumber - 1) // 26

        # The result was built in reverse, so reverse it before joining.
        return "".join(reversed(result))
