from collections import deque


class Solution:
    """
    Determines which political party, 'Radiant' (R) or 'Dire' (D), will achieve
    victory in the Senate.

    The game simulates a process where each senator has the power to ban one
    senator from the opposite party. The turn order is determined by the
    initial position in the 'senate' string. When a senator votes, they ban
    the *next* available opponent senator in the circular queue of senators.
    The party that has senators remaining when the other party is fully banned
    wins.
    """
    def predictPartyVictory(self, senate: str) -> str:
        """
        Simulates the party ban process using two queues to represent the
        turn order for each party.

        The key optimization is that instead of tracking who is banned, we
        track whose *turn* is next. A senator is added back to their party's
        queue with an increased index (by N) if they successfully ban an
        opponent, simulating their next turn in the *next round*.

        Args:
            senate: A string representing the initial order of senators,
                    e.g., "RDRD", where 'R' is Radiant and 'D' is Dire.

        Returns:
            'Radiant' or 'Dire', indicating the winning party.
        """
        n = len(senate)
        radiant_queue = deque()
        dire_queue = deque()

        # 1. Initialize queues with the original indices of each senator.
        # These indices serve as the basis for determining the turn order.
        for idx, party in enumerate(senate):
            if party == 'R':
                radiant_queue.append(idx)
            else:
                dire_queue.append(idx)

        # 2. Simulate the ban process round by round until one queue is empty.
        # The senator with the *smaller index* (earlier turn) gets to ban
        # the opponent and secure a spot in the next round.
        while radiant_queue and dire_queue:
            # Pop the senator with the earliest turn from each party
            radiant_member_idx = radiant_queue.popleft()
            dire_member_idx = dire_queue.popleft()

            # The senator with the smaller index acts first (bans the opponent)
            # and is thus added back to the queue for the next round (index + n).
            if radiant_member_idx < dire_member_idx:
                # Radiant acts first, bans Dire, and survives to the next round.
                radiant_queue.append(radiant_member_idx + n)
            else:
                # Dire acts first, bans Radiant, and survives to the next round.
                dire_queue.append(dire_member_idx + n)

        # 3. The party with senators remaining in their queue wins.
        return 'Radiant' if radiant_queue else 'Dire'