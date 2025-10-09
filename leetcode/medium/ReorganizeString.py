from collections import Counter

class Solution:
    def reorganizeString(self, s: str) -> str:
        """
        Rearranges the characters of a string so that no two adjacent characters are the same.

        If a valid rearrangement is not possible, returns an empty string.

        The approach is a greedy one:
        1. Count the frequency of each character in the string.
        2. Check if a solution is possible. A solution is impossible if the count of the
           most frequent character is greater than (N + 1) / 2, where N is the
           length of the string. This is because there wouldn't be enough other
           characters to place between the instances of the most frequent character.
        3. Place the characters into a result array. To ensure no two same characters
           are adjacent, we place them greedily starting with the most frequent ones.
           We fill the even-indexed positions first (0, 2, 4, ...), and then the
           odd-indexed positions (1, 3, 5, ...). This strategy naturally separates
           the most frequent characters as much as possible.

        Time Complexity: O(N), where N is the length of the string. This is because
                         counting characters is O(N) and filling the result is also O(N).
                         The sorting of characters by frequency is O(K log K), where K is
                         the number of unique characters (a constant, max 52), so it
                         doesn't dominate the complexity.
        Space Complexity: O(N) to store the result string.
        """
        # Step 1: Count the frequency of each character.
        counter = Counter(s)
        n = len(s)

        # Step 2: Check if a reorganization is possible.
        # Get the most common character and its count.
        # counter.most_common(1) returns a list like [('a', 5)]
        most_common = counter.most_common(1)
        if most_common:
            _, most_frequent_count = most_common[0]
            if most_frequent_count > (n + 1) // 2:
                return ""

        # Step 3: Greedily place characters into the result array.
        res = [""] * n
        index = 0

        # Iterate through characters sorted by frequency in descending order.
        for char, count in counter.most_common():
            for _ in range(count):
                # Place the character at the current even index.
                res[index] = char
                index += 2
                # If we've gone past the end of the array,
                # wrap around to the first odd index.
                if index >= n:
                    index = 1
        
        # Join the characters in the list to form the final string.
        return "".join(res)