class Solution:
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        jewel_set = set(jewels)
        # Use a generator expression with sum() for a concise, one-line solution.
        return sum(stone in jewel_set for stone in stones)
        