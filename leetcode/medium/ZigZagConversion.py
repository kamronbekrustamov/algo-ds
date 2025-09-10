class Solution:
    def convert(self, s: str, numRows: int) -> str:
        # If numRows is 1, or greater than the length of the string,
        # the zig-zag pattern doesn't alter the string.
        if numRows <= 1 or numRows >= len(s):
            return s

        # Create a list of strings to represent each row of the zig-zag pattern.
        rows = [""] * numRows
        current_row = 0
        going_down = False

        # Iterate through each character of the string, placing it in the correct row.
        for char in s:
            rows[current_row] += char
            
            # If we are at the top or bottom row, we must reverse direction.
            if current_row == 0 or current_row == numRows - 1:
                going_down = not going_down
            
            # Move to the next row based on the current direction.
            current_row += 1 if going_down else -1

        # Join the rows to form the final converted string.
        return "".join(rows)