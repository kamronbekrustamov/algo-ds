import re
from collections import Counter
from typing import List


class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        """
        Finds the most frequent word in a paragraph that is not in a list of banned words.
        """
        # 1. Create a set of banned words for O(1) lookup time.
        banned_set = set(banned)

        # 2. Use a regular expression to find all "word" characters.
        #    - paragraph.lower() converts the text to lowercase.
        #    - re.findall(r'\w+', ...) finds all sequences of one or more
        #      alphanumeric characters, effectively splitting the paragraph
        #      into words and removing punctuation in a single, efficient pass.
        words = re.findall(r'\w+', paragraph.lower())

        # 3. Use a Counter to count the frequency of each word, excluding banned words.
        #    This is done efficiently with a generator expression.
        word_counts = Counter(word for word in words if word not in banned_set)

        # 4. The most_common(1) method returns a list containing the most
        #    common element and its count, e.g., [('ball', 2)].
        #    We extract the word from this tuple.
        if not word_counts:
            return ""  # Handle cases where no valid words are found.
            
        return word_counts.most_common(1)[0][0]
