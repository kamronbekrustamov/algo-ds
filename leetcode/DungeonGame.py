from typing import List


class Solution:
    def calculateMinimumHP(self, dungeon: List[List[int]]) -> int:
        m, n = len(dungeon), len(dungeon[0])
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                # check i + 1, j and i, j + 1
                if i == m - 1 and j == n - 1:
                    min_val = 1
                elif i == m - 1:
                    min_val = dungeon[i][j + 1]
                elif j == n - 1:
                    min_val = dungeon[i + 1][j]
                else:
                    min_val = min(dungeon[i + 1][j], dungeon[i][j + 1])
                
                if dungeon[i][j] > 0:
                    if dungeon[i][j] >= min_val:
                        dungeon[i][j] = 1
                    else:
                        dungeon[i][j] = min_val - dungeon[i][j]
                else:
                    dungeon[i][j] = min_val + abs(dungeon[i][j])
        return dungeon[0][0]          

