import java.util.Deque;
import java.util.ArrayDeque;

/**
 * StockSpanner is designed to calculate the span of a stock's price for each new price quotation.
 * The span of a stock's price today is defined as the maximum number of consecutive days
 * (starting from today and going backward) for which the stock price was less than or equal to today's price.
 * * This solution uses a Monotonic Stack (specifically, a decreasing stack) to achieve an amortized O(1) time complexity.
 */
class StockSpanner {
    // The stack stores pairs: [price, span]. 
    // The prices in the stack will always be in decreasing order (a monotonic decreasing stack).
    private Deque<int[]> stack;

    /**
     * Initializes the data structure.
     */
    public StockSpanner() {
        stack = new ArrayDeque<>();
    }
    
    /**
     * Calculates and returns the span for the given stock price.
     * * The method processes a new price by popping all previous prices from the stack
     * that are less than or equal to the current price, accumulating their spans.
     * * @param price The current day's stock price.
     * @return The span for the current price.
     */
    public int next(int price) {
        // 'currentSpan' starts at 1 (for the current day itself).
        int currentSpan = 1;

        // While the stack is not empty AND the price at the top of the stack is 
        // less than or equal to the current price:
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            // Pop the element and add its accumulated span to the current span.
            // These popped elements represent all consecutive, less-than-or-equal prices 
            // that are now covered by the new, higher 'price'.
            currentSpan += stack.pop()[1];
        }

        // Push the current price and its calculated span onto the stack.
        // The price at the top of the stack is now strictly greater than 'price' 
        // (or the stack is empty), maintaining the decreasing order property.
        stack.push(new int[]{price, currentSpan});

        return currentSpan;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */