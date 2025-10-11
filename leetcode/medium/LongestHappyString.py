import heapq

class Solution:
    def longestDiverseString(self, a: int, b: int, c: int) -> str:
        """
        Constructs the longest possible "happy" string given the counts of 'a', 'b', and 'c'.

        A "happy" string is one that does not contain "aaa", "bbb", or "ccc" as a substring.

        This solution uses a greedy approach with a max-heap to prioritize the character
        with the highest remaining count at each step, while ensuring the "happy"
        string condition is not violated.

        Args:
            a: The number of 'a' characters available.
            b: The number of 'b' characters available.
            c: The number of 'c' characters available.

        Returns:
            The longest possible happy string.
        """
        result = []
        # Python's heapq is a min-heap, so we store negative counts to simulate a max-heap.
        # The heap will store tuples of (negative_count, character).
        max_heap = []
        if a > 0:
            heapq.heappush(max_heap, (-a, 'a'))
        if b > 0:
            heapq.heappush(max_heap, (-b, 'b'))
        if c > 0:
            heapq.heappush(max_heap, (-c, 'c'))

        while max_heap:
            # Get the character with the highest remaining count.
            count, char = heapq.heappop(max_heap)

            # --- Greedy Choice ---
            # Check if adding this character would violate the happy string rule
            # (i.e., create a sequence of three identical characters).
            if len(result) >= 2 and result[-1] == char and result[-2] == char:
                # If we can't use the most frequent character, we must use the second most frequent.
                # If there's no second most frequent, we can't add any more characters.
                if not max_heap:
                    break

                # Get the second most frequent character.
                count2, char2 = heapq.heappop(max_heap)
                
                # Add the second most frequent character to the result.
                result.append(char2)

                # Decrement its count and push it back to the heap if it's still available.
                if count2 + 1 < 0:
                    heapq.heappush(max_heap, (count2 + 1, char2))
                
                # Crucially, push the most frequent character (that we couldn't use)
                # back onto the heap so it can be used in the next iteration.
                heapq.heappush(max_heap, (count, char))

            else:
                # It's safe to add the most frequent character.
                result.append(char)

                # Decrement its count and push it back to the heap if it's still available.
                if count + 1 < 0:
                    heapq.heappush(max_heap, (count + 1, char))

        return "".join(result)
