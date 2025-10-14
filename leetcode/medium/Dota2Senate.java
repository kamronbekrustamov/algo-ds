import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public String predictPartyVictory(String senate) {
        // Create two queues to store the indices of Radiant and Dire senators
        Queue<Integer> radiantQueue = new ArrayDeque<>();
        Queue<Integer> direQueue = new ArrayDeque<>();
        int n = senate.length();

        // Populate the queues with the initial indices of the senators
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiantQueue.offer(i);
            } else {
                direQueue.offer(i);
            }
        }

        // While both parties have senators remaining
        while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
            // Get the indices of the next Radiant and Dire senators
            int radiantIndex = radiantQueue.poll();
            int direIndex = direQueue.poll();

            // The senator with the smaller index gets to vote in this round.
            // The other senator is banned for this round.
            // The winning senator's index is enqueued back to their respective queue
            // with an increment of n to signify that they will vote in the next round.
            if (radiantIndex < direIndex) {
                radiantQueue.offer(radiantIndex + n);
            } else {
                direQueue.offer(direIndex + n);
            }
        }

        // The party with senators remaining in their queue wins
        return radiantQueue.isEmpty() ? "Dire" : "Radiant";
    }
}