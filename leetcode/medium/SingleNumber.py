from typing import List


class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        # This solution leverages the properties of the bitwise XOR operator (^).
        #
        # Key Properties:
        # 1. A number XORed with itself is 0 (e.g., a ^ a = 0).
        # 2. A number XORed with 0 is the number itself (e.g., a ^ 0 = a).
        # 3. XOR is commutative and associative (a ^ b ^ a = a ^ a ^ b = 0 ^ b = b).
        #
        # By XORing all numbers in the list, every number that appears twice
        # will cancel itself out, leaving only the single, unique number.
        result = 0
        for num in nums:
            result ^= num
        return result