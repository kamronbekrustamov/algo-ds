from typing import List


class Solution:
    # Morse code representations for 'a' through 'z'.
    # Defined at the class level so it's not recreated on every method call.
    MORSE = [".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
             "....", "..", ".---", "-.-", ".-..", "--", "-.",
             "---", ".--.", "--.-", ".-.", "...", "-", "..-",
             "...-", ".--", "-..-", "-.--", "--.."]

    def uniqueMorseRepresentations(self, words: List[str]) -> int:
        """
        Calculates the number of unique Morse code transformations for a list of words.
        """
        # Use a set comprehension for a concise and efficient solution.
        # 1. For each `word` in `words`:
        # 2.   Transform the word to Morse code by joining the mapped characters.
        # 3.   The generator expression `(self.MORSE[ord(c) - ord('a')] for c in word)` is efficient.
        # 4.   The resulting set contains only the unique transformations.
        return len({"".join(self.MORSE[ord(c) - ord('a')] for c in word) for word in words})